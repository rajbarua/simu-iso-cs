# simu-iso-cs

This Simulator project measures a complete `pain.001.001.03` file stored as one Hazelcast `IMap` value. It compares
three Compact serialization modes against the same generated JAXB model:

| Mode | Client config | Map | Serializer |
| --- | --- | --- | --- |
| No-code | `client-hazelcast.xml` | `pain001-files-nocode` | Hazelcast reflective no-code Compact |
| Generated explicit | `client-hazelcast-explicit.xml` | `pain001-files-explicit` | Direct typed serializers for all 66 non-enum generated classes |
| Reflective diagnostic | `client-hazelcast-reflective.xml` | `pain001-files-reflective` | Explicitly registered serializers backed by cached reflection |

The generated serializers directly invoke JAXB getters/setters and the matching `CompactReader`/`CompactWriter`
methods. They contain no `Field`, `setAccessible`, or runtime field discovery. The retained reflective implementation
is diagnostic only; it distinguishes generated accessor cost from cached reflection and preserves a comparison point
for the no-code behaviour observed before Hazelcast 5.7.1.

The generated and diagnostic serializers use distinct `simu-iso-cs.explicit.*` and
`simu-iso-cs.reflective.*` Compact type names. All three schema families and maps can therefore coexist in one
cluster, so recreating the cluster between modes is not required. The members
intentionally have no explicit serializers registered: these tests use client `set` and `get`, so the member stores
and returns Compact data without materializing the pain.001 POJO. Serializer selection happens in each Simulator
client JVM through the suite-specific client configuration.

The default sample represents a deliberately demanding benchmark workload:

- 150,000 `CdtTrfTxInf` transactions
- 2,000 `PmtInf` blocks (75 transactions each)
- 180 MiB uncompressed XML

With Hazelcast 5.7.1, the full-fixture verification encodes this object graph as a 157,667,456-byte Compact value
(about 150.4 MiB) in all three modes, before IMap record metadata and backups. A local codec-only validation measured
no-code at 0.563/0.823 seconds, reflective-explicit at 0.463/0.367 seconds, and generated-explicit at 0.290/0.294
seconds for serialization/deserialization. XML parsing took 1.751 seconds. These figures validate the implementation
but are not substitutes for the GCP IMap benchmark.

Most size filler is held in schema-mapped `RmtInf/Ustrd` values. It therefore remains in the POJO and affects Compact
serialization instead of merely making the input file larger.

## What is measured

Each write/read pair contains six relevant latency histograms in total. The automatic timestep probes include
coordinated-omission correction: their clock starts at the operation's scheduled time, so they deliberately include
queueing when a single worker cannot keep up. The manual component/service probes start when the operation actually
begins.

| Histogram | Boundary |
| --- | --- |
| `xmlToPojo` | JAXB unmarshal of the in-memory XML bytes into the generated `Document` object graph |
| `pojoSet` | synchronous `IMap.set(key, document)`, including Compact serialization, network transfer, primary update, and configured backup acknowledgement |
| `parseAndSetService` | actual XML-to-POJO-to-acknowledged-map-write service time, excluding time waiting behind earlier writes |
| `pojoGet` | actual synchronous `IMap.get(key)`, including network transfer and Compact deserialization into the JAXB POJO |
| `parseAndSet` | scheduled write response time: queueing delay plus `parseAndSetService` |
| `readPojo` | scheduled read response time: queueing delay plus `pojoGet` |

The XML is decompressed and loaded before warmup, so disk I/O and gzip decompression are excluded. The read suite
parses, validates, and seeds its keys before timing. The write suite starts with empty keys and populates them during
warmup. Timed writes use `set`, not `put`.

Writes and reads use separate suites and are never timed concurrently. The three serialization modes are also separate
suites. Each suite uses one load-generator client and
offers one complete file operation per second. If `loadgenerator_count` is increased, the configured rate applies to
each client and therefore multiplies. The combined-rate metronome keeps the suite at one aggregate operation per
second across all of its worker threads.

One operation per second is the offered rate. If all worker threads are occupied, the suite falls behind. In that case
`parseAndSet`/`readPojo` rise throughout the run even when service time is stable. Use
`parseAndSetService`/`pojoGet` for the cost of one operation, and achieved operation counts/TPS plus the scheduled
response probes to decide whether the one-file-per-second target is sustained.

Each suite rotates through 12 generated file IDs. This avoids a single hot partition and lets the three members
participate, while bounding primary-plus-backup value storage at about 3.5 GiB cluster-wide for the default 150.4 MiB
Compact payload. Setup removes only keys beginning `pain001-benchmark-`, so a previously killed worker cannot leave
stale test values consuming NATIVE memory. Change `keyCount` only after accounting for the configured capacity.

The same tuning is used for all serialization modes and is deliberately bounded by the current `c2-standard-8`
load generator:

| Suite | Threads | Rate | Reason |
| --- | ---: | ---: | --- |
| Write | 6 | 1/sec | measured parse-plus-set service was about 4.2 seconds, leaving one thread of scheduling headroom |
| Read | 8 | 1/sec | one deserialization worker per vCPU; the result determines whether this VM can meet the target |

Adding more read threads than available vCPUs can increase live object graphs and GC without improving throughput.
If the isolated read run cannot sustain 1/sec, resize the load-generator VM before increasing concurrency and heap.

## Build and prepare the sample

Java 21, Maven, and locally built Hazelcast Simulator `2.0-SNAPSHOT` artifacts are expected.

```bash
./scripts/generate-sample
mvn clean verify
./scripts/install-simulator-user-lib
```

The normal test suite uses small fixtures and verifies that all three schema families coexist. To repeat the
memory-intensive JAXB and Compact round trip of the full 180 MiB packaged fixture in all modes, run:

```bash
mvn -DfullSample=true -Dtest=Pain001FullSampleTest -DargLine=-Xmx4g test
```

The generated gzip is packaged inside the shaded benchmark JAR. Its compressed size is small because the benchmark
content is intentionally repetitive; every Simulator process expands it into memory during setup. To use an external
plain or gzip XML instead, set `sampleXmlPath` in the relevant write/read suite. The expected
transaction/payment-info counts and minimum XML size checks remain active.

## GCP/GKE topology

`k8s/deploy.yaml` provisions the complete test environment without starting the benchmark:

| Component | Default |
| --- | --- |
| VPC/subnet | Dedicated `simu-iso-cs-network`; nodes/VMs `172.18.0.0/16`, pods `10.20.0.0/16`, services `10.21.0.0/20` |
| GKE | Three `c2-standard-8` nodes in `asia-south1-a` |
| Hazelcast | Three Enterprise 5.7.1 members, 4 GiB heap and 12 GiB POOLED native memory each |
| Maps | `pain001-files-*`, NATIVE, one synchronous backup; each serialization mode uses a separate map |
| Management Center | 5.11.0, ClusterIP; use `kubectl port-forward` |
| Load generator | One `c2-standard-8` Ubuntu VM in the same VPC |
| Member access | One internal load balancer per member; no public Hazelcast endpoint |

The playbook creates the GKE control plane and then a named persistent node pool. The Ansible GKE cluster module
removes its temporary `default-pool` by design, so the separate node-pool task is required. Deployment waits for all
three nodes to report `Ready` before installing the Hazelcast Helm release.

The load generator receives a public IP only for Simulator installation and SSH control. Its firewall accepts traffic
only from `SIMULATOR_ADMIN_SOURCE_CIDR`. The playbook renders the gitignored `inventory.yaml` from that public IP and
rewrites all three client configurations with the three internal member addresses.
All-member routing preserves direct partition-owner access for the large values.

NATIVE storage requires Hazelcast Enterprise and a valid license. Compact serialization is performed by the clients,
so the member pods do not need the benchmark JAR; the shaded JAR, explicit serializers, and JAXB runtime are installed
on the Simulator VM.

## Provision GCP

Prerequisites:

- `gcloud`, the GKE auth plugin, `kubectl`, Helm, Ansible, Java 21, and Maven
- a GCP project with the Compute Engine (`compute.googleapis.com`) and Kubernetes Engine
  (`container.googleapis.com`) APIs already enabled
- a service account allowed to manage VPCs, firewalls, VMs, GKE, load balancers, and Kubernetes resources
- its JSON key at `~/gcp/credentials.json`, or `GOOGLE_APPLICATION_CREDENTIALS`
- a Hazelcast Enterprise license in `HAZELCAST_LICENSE_KEY` or `~/hazelcast/demo.license`
- an SSH key pair at `~/.ssh/id_ed25519[.pub]`, or `ISO_CS_SSH_PRIVATE_KEY` and `ISO_CS_SSH_PUB_KEY`

Install the Ansible collections and identify the operator workstation with a narrow `/32` CIDR:

```bash
ansible-galaxy collection install -r requirements.yml
export GCP_PROJECT_ID="your-gcp-project-id"
export SIMULATOR_ADMIN_SOURCE_CIDR="$(curl -4 -s https://checkip.amazonaws.com)/32"
echo "$SIMULATOR_ADMIN_SOURCE_CIDR"
```

The resulting value should look like `203.0.113.42/32`. Run the lookup while connected to the VPN or corporate
network that will be used to control Simulator, because its public egress address may differ from the workstation's
normal address. If that public IP changes after deployment, recalculate the variable and update only the firewall:

```bash
export SIMULATOR_ADMIN_SOURCE_CIDR="$(curl -4 -s https://checkip.amazonaws.com)/32"
ansible-playbook k8s/deploy.yaml --tags firewall
```

The deployment deliberately rejects `0.0.0.0/0`.

The service account typically needs Compute Network Admin, Compute Instance Admin, Kubernetes Engine Admin, and
Service Account User (for the GKE node service account). Scope these roles to the benchmark project.

Build the benchmark, place it in Simulator's local `user-lib`, and provision GCP:

```bash
mvn clean package
./scripts/install-simulator-user-lib
ansible-playbook k8s/deploy.yaml
```

The deployment is intentionally not executed by a build or test. Running the playbook creates billable GCP resources.
Override sizing/location variables with `-e` or environment variables; defaults live in `k8s/vars/main.yml`.

For an environment that was already provisioned by an earlier version of this repository, rerun the same deploy
playbook once. It is idempotent: it retains the existing `simu-iso-cs` GKE cluster and VMs, updates the Hazelcast map
configuration to the `pain001-files-*` NATIVE wildcard, and renders all three client configurations. Helm may perform a
rolling restart of the Hazelcast member pods to apply the map configuration; this is not a GKE cluster recreation.

## Install Simulator and run

The deploy playbook creates `inventory.yaml` and all three client configurations. Once it completes, install Java and
Simulator on the generated load-generator VM. Simulator copies the local `user-lib` during its installation:

```bash
source "$HOME/src/hazelcast-simulator/.venv/bin/activate"
inventory install java --hosts loadgenerators
inventory install simulator --hosts loadgenerators
perftest run pain001_nocode_write_tests.yaml
perftest run pain001_nocode_read_tests.yaml
perftest run pain001_explicit_write_tests.yaml
perftest run pain001_explicit_read_tests.yaml
perftest run pain001_reflective_write_tests.yaml
perftest run pain001_reflective_read_tests.yaml
```

Run the six suites one at a time. Use the reflective pair as a diagnostic rather than the primary explicit-CS result.
A cluster restart is not required when switching modes. If benchmark Java, the
explicit serializers, or the packaged sample changes, rebuild, rerun `./scripts/install-simulator-user-lib`, and
reinstall Simulator on the load-generator host before the next run. YAML-only workload changes do not require
reinstalling it.

The current Simulator-client heap is 16 GiB, not a measured minimum. A worker can concurrently hold the
180 MiB XML bytes plus parsed writer, serialization, and read object graphs. Watch GC and process RSS during the first
run and increase the heap if allocation stalls or OOMs occur.

GC logging writes directly to mode-specific files such as `gc-nocode-write.log` and `gc-explicit-read.log` in the
remote worker directory. Do not change it to
`stdout`: Simulator's
worker wrapper sends stdout through `tee`, and verbose unified-GC logging can fill that pipe and freeze the JVM during
a stop-the-world collection. The repository's `simulator.properties` limits post-run completion waiting to 60 seconds
so a genuinely stuck worker fails promptly instead of holding the coordinator for the default five minutes.

Management Center is not exposed publicly:

```bash
kubectl port-forward svc/hz-primary-mancenter 8080:8080
```

Open `http://localhost:8080` while the port-forward is running.

## Tear down GCP

The teardown removes the Helm release, GKE cluster, load-generator VM, firewall rules, subnet, and dedicated VPC:

```bash
ansible-playbook k8s/undeploy.yaml
```

Confirm teardown finishes successfully to avoid retaining billable resources.

Each write report contains `xmlToPojo`, `pojoSet`, `parseAndSetService`, and `parseAndSet`. Each read report contains
`pojoGet` and `readPojo`. Compare equivalent probes between the no-code and generated-explicit runs; use the
reflective run to attribute the no-code implementation overhead. Use the manual probes for
actual service time and the automatic probes plus achieved TPS to see whether the offered rate creates a backlog.

## Regenerate with another shape

The generator is JDK-only and streams output, so it does not construct the large POJO while preparing the fixture:

```bash
java src/main/java/com/hazelcast/isocs/xml/Pain001SampleGenerator.java \
  --transactions 143687 \
  --payment-infos 1990 \
  --target-mib 180 \
  --output src/main/resources/samples/benchmark-maximum.xml.gz
```

Change the matching fields in all six suite files when using another sample.

## License and ISO 20022 attribution

The original code in this repository is licensed under the Apache License 2.0. The bundled
`pain.001.001.03` schema is ISO 20022 repository material and is not relicensed under Apache-2.0.
See `NOTICE` for its source, applicable terms, and the authoritative ISO 20022 website.
