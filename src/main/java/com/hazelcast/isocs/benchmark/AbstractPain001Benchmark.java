package com.hazelcast.isocs.benchmark;

import com.hazelcast.isocs.xml.Pain001Parser;
import com.hazelcast.isocs.xml.SampleXmlLoader;
import com.hazelcast.map.IMap;
import com.hazelcast.simulator.hz.HazelcastTest;
import com.hazelcast.simulator.probes.LatencyProbe;
import com.hazelcast.simulator.test.annotations.Setup;
import com.hazelcast.simulator.test.annotations.Teardown;
import com.hz.demo.pmt.pain001_03.Document;
import com.hz.demo.pmt.pain001_03.PaymentInstructionInformation3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/** Common sample, key-ring, measurement, validation, and cleanup logic for the isolated benchmarks. */
public abstract class AbstractPain001Benchmark extends HazelcastTest {
    private static final String BENCHMARK_KEY_PREFIX = "pain001-benchmark-";

    public String mapName = "pain001-files-nocode";
    public String sampleResource = "samples/pain001-150k-2k-180MiB.xml.gz";
    public String sampleXmlPath = "";
    public int expectedTransactions = 150_000;
    public int expectedPaymentInfos = 2_000;
    public int minimumXmlSizeMiB = 175;
    public int keyCount = 12;
    public boolean cleanupStaleKeysOnSetup = true;
    public boolean cleanupKeysOnExit = true;

    private IMap<String, Document> map;
    private Pain001Parser parser;
    private byte[] sampleXml;
    private List<String> mapKeys;
    private LatencyProbe xmlToPojoProbe;
    private LatencyProbe pojoSetProbe;
    private LatencyProbe parseAndSetServiceProbe;
    private LatencyProbe pojoGetProbe;
    private final AtomicLong writeSequence = new AtomicLong();
    private final AtomicLong readSequence = new AtomicLong();
    private final LongAdder writes = new LongAdder();
    private final LongAdder reads = new LongAdder();
    private final LongAdder missingReads = new LongAdder();
    private final AtomicBoolean fullReadValidated = new AtomicBoolean();

    @Setup
    public final void setup() {
        validateConfiguration();
        map = targetInstance.getMap(mapName);
        if (cleanupStaleKeysOnSetup) {
            int deleted = 0;
            for (String key : map.keySet()) {
                if (key.startsWith(BENCHMARK_KEY_PREFIX)) {
                    map.delete(key);
                    deleted++;
                }
            }
            logger.info("Deleted {} stale pain.001 benchmark keys before setup", deleted);
        }
        parser = new Pain001Parser();
        sampleXml = SampleXmlLoader.load(sampleXmlPath, sampleResource);

        String keyPrefix = BENCHMARK_KEY_PREFIX + sanitise(testContext.getTestId()) + '-'
                + sanitise(testContext.getPublicIpAddress()) + "-file-";
        mapKeys = new ArrayList<>(keyCount);
        for (int i = 0; i < keyCount; i++) {
            mapKeys.add(keyPrefix + i);
        }

        Document seed = parser.parse(sampleXml);
        validateDocument(seed);
        if (seedMapBeforeRun()) {
            for (String key : mapKeys) {
                map.set(key, seed);
            }
        }

        initialiseProbes();
        logger.info("Loaded pain.001 sample: benchmark={}, xmlBytes={}, transactions={}, paymentInfos={}, "
                        + "keyPrefix={}, keyCount={}, seeded={}",
                getClass().getSimpleName(), sampleXml.length, expectedTransactions, expectedPaymentInfos,
                keyPrefix, keyCount, seedMapBeforeRun());
    }

    protected abstract boolean seedMapBeforeRun();

    protected abstract void initialiseProbes();

    protected final void initialiseWriteProbes() {
        // The enclosing @TimeStep accounts for throughput; component probes must not double-count it.
        xmlToPojoProbe = testContext.getLatencyProbe("xmlToPojo", false);
        pojoSetProbe = testContext.getLatencyProbe("pojoSet", false);
        parseAndSetServiceProbe = testContext.getLatencyProbe("parseAndSetService", false);
    }

    protected final void initialiseReadProbes() {
        pojoGetProbe = testContext.getLatencyProbe("pojoGet", false);
    }

    protected final void writeOnce() {
        long operationStarted = System.nanoTime();
        long parseStarted = System.nanoTime();
        Document document = parser.parse(sampleXml);
        xmlToPojoProbe.recordValue(System.nanoTime() - parseStarted);

        long setStarted = System.nanoTime();
        map.set(nextKey(writeSequence), document);
        pojoSetProbe.recordValue(System.nanoTime() - setStarted);
        parseAndSetServiceProbe.recordValue(System.nanoTime() - operationStarted);
        writes.increment();
    }

    protected final void readOnce() {
        long getStarted = System.nanoTime();
        Document document = map.get(nextKey(readSequence));
        pojoGetProbe.recordValue(System.nanoTime() - getStarted);
        reads.increment();

        if (document == null) {
            missingReads.increment();
            return;
        }
        if (document.getCstmrCdtTrfInitn() == null
                || document.getCstmrCdtTrfInitn().getPmtInf().size() != expectedPaymentInfos) {
            throw new IllegalStateException("Read returned a malformed pain.001 POJO");
        }
        if (fullReadValidated.compareAndSet(false, true)) {
            validateDocument(document);
        }
    }

    protected final void verifyWrites() {
        if (writes.sum() == 0L) {
            throw new IllegalStateException("Expected write operations");
        }
        validateDocument(map.get(mapKeys.get(0)));
        logger.info("Verified pain.001 write benchmark: writes={}, xmlBytes={}", writes.sum(), sampleXml.length);
    }

    protected final void verifyReads() {
        if (reads.sum() == 0L) {
            throw new IllegalStateException("Expected read operations");
        }
        if (missingReads.sum() != 0L) {
            throw new IllegalStateException("Observed " + missingReads.sum() + " missing pain.001 reads");
        }
        if (!fullReadValidated.get()) {
            throw new IllegalStateException("No read returned a fully validated pain.001 POJO");
        }
        logger.info("Verified pain.001 read benchmark: reads={}, xmlBytes={}", reads.sum(), sampleXml.length);
    }

    @Teardown
    public final void teardown() {
        if (cleanupKeysOnExit && map != null && mapKeys != null) {
            for (String key : mapKeys) {
                map.delete(key);
            }
        }
    }

    private String nextKey(AtomicLong sequence) {
        return mapKeys.get((int) Math.floorMod(sequence.getAndIncrement(), keyCount));
    }

    private void validateConfiguration() {
        if (mapName == null || mapName.isBlank()) {
            throw new IllegalArgumentException("mapName must not be blank");
        }
        if ((sampleXmlPath == null || sampleXmlPath.isBlank())
                && (sampleResource == null || sampleResource.isBlank())) {
            throw new IllegalArgumentException("Configure sampleXmlPath or sampleResource");
        }
        if (expectedTransactions <= 0 || expectedPaymentInfos <= 0 || minimumXmlSizeMiB <= 0 || keyCount <= 0) {
            throw new IllegalArgumentException("Expected counts, minimumXmlSizeMiB, and keyCount must be positive");
        }
    }

    private void validateDocument(Document document) {
        if (document == null || document.getCstmrCdtTrfInitn() == null
                || document.getCstmrCdtTrfInitn().getGrpHdr() == null) {
            throw new IllegalStateException("Sample did not produce a pain.001 document");
        }
        var initiation = document.getCstmrCdtTrfInitn();
        if (initiation.getPmtInf().size() != expectedPaymentInfos) {
            throw new IllegalStateException("Expected " + expectedPaymentInfos + " PmtInf blocks, found "
                    + initiation.getPmtInf().size());
        }
        long transactions = 0L;
        for (PaymentInstructionInformation3 paymentInfo : initiation.getPmtInf()) {
            transactions += paymentInfo.getCdtTrfTxInf().size();
        }
        if (transactions != expectedTransactions) {
            throw new IllegalStateException("Expected " + expectedTransactions + " transactions, found " + transactions);
        }
        long minimumBytes = (long) minimumXmlSizeMiB * 1024 * 1024;
        if (sampleXml.length < minimumBytes) {
            throw new IllegalStateException("Sample is only " + sampleXml.length + " bytes; expected at least "
                    + minimumBytes);
        }
    }

    private static String sanitise(String value) {
        if (value == null || value.isBlank()) {
            return "unknown";
        }
        return value.replaceAll("[^A-Za-z0-9_.-]", "_");
    }
}
