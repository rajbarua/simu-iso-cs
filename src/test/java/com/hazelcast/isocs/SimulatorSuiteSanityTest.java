package com.hazelcast.isocs;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.isocs.benchmark.AbstractPain001Benchmark;
import com.hazelcast.isocs.benchmark.Pain001ReadBenchmark;
import com.hazelcast.isocs.benchmark.Pain001WriteBenchmark;
import com.hazelcast.isocs.xml.Pain001SampleGenerator;
import com.hazelcast.simulator.common.TestCase;
import com.hazelcast.simulator.hazelcast4plus.HazelcastInstances;
import com.hazelcast.simulator.worker.testcontainer.TestContainer;
import com.hazelcast.simulator.worker.testcontainer.TestContextImpl;
import com.hazelcast.map.IMap;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.hazelcast.simulator.common.TestPhase.LOCAL_TEARDOWN;
import static com.hazelcast.simulator.common.TestPhase.LOCAL_VERIFY;
import static com.hazelcast.simulator.common.TestPhase.SETUP;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimulatorSuiteSanityTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void writeSuitesBindAndCompleteOneSmallLifecycle() throws Exception {
        for (String suite : List.of("pain001_nocode_write_tests.yaml", "pain001_explicit_write_tests.yaml")) {
            TestCase testCase = loadTestCase(suite);
            assertEquals(Pain001WriteBenchmark.class.getName(), testCase.getClassname());
            assertEquals("6", testCase.getProperty("threadCount"));
            runSmallLifecycle(testCase, new Pain001WriteBenchmark());
        }
    }

    @Test
    void readSuitesBindAndCompleteOneSmallLifecycle() throws Exception {
        for (String suite : List.of("pain001_nocode_read_tests.yaml", "pain001_explicit_read_tests.yaml")) {
            TestCase testCase = loadTestCase(suite);
            assertEquals(Pain001ReadBenchmark.class.getName(), testCase.getClassname());
            assertEquals("8", testCase.getProperty("threadCount"));
            runSmallLifecycle(testCase, new Pain001ReadBenchmark());
        }
    }

    private void runSmallLifecycle(TestCase testCase, AbstractPain001Benchmark benchmark) throws Exception {
        Path sample = temporaryDirectory.resolve(testCase.getId() + ".xml.gz");
        Pain001SampleGenerator.generate(sample, 97, 7, 1024 * 1024L);

        testCase.setProperty("mapName", "sanity-" + testCase.getId());
        testCase.setProperty("sampleXmlPath", sample);
        testCase.setProperty("expectedTransactions", 97);
        testCase.setProperty("expectedPaymentInfos", 7);
        testCase.setProperty("minimumXmlSizeMiB", 1);
        testCase.setProperty("keyCount", 3);

        TestContextImpl context = new TestContextImpl(testCase.getId(), "localhost", null);
        HazelcastInstances driver = newInMemoryDriver();
        String previousTestDirectory = System.getProperty("user.dir.test");
        System.setProperty("user.dir.test", temporaryDirectory.toString());
        try {
            TestContainer container = new TestContainer(context, benchmark, testCase, driver);
            container.invoke(SETUP);
            try {
                if (benchmark instanceof Pain001WriteBenchmark writeBenchmark) {
                    writeBenchmark.parseAndSet();
                } else if (benchmark instanceof Pain001ReadBenchmark readBenchmark) {
                    readBenchmark.readPojo();
                } else {
                    throw new AssertionError("Unexpected benchmark type " + benchmark.getClass());
                }
                container.invoke(LOCAL_VERIFY);
            } finally {
                container.invoke(LOCAL_TEARDOWN);
            }
        } finally {
            if (previousTestDirectory == null) {
                System.clearProperty("user.dir.test");
            } else {
                System.setProperty("user.dir.test", previousTestDirectory);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private static HazelcastInstances newInMemoryDriver() {
        Map<Object, Object> values = new ConcurrentHashMap<>();
        IMap<Object, Object> map = (IMap<Object, Object>) Proxy.newProxyInstance(
                IMap.class.getClassLoader(),
                new Class<?>[]{IMap.class},
                (proxy, method, args) -> switch (method.getName()) {
                    case "set" -> {
                        values.put(args[0], args[1]);
                        yield null;
                    }
                    case "get" -> values.get(args[0]);
                    case "delete" -> {
                        values.remove(args[0]);
                        yield null;
                    }
                    case "keySet" -> values.keySet();
                    case "getName" -> "sanity-map";
                    case "toString" -> "InMemoryIMap";
                    case "hashCode" -> System.identityHashCode(proxy);
                    case "equals" -> proxy == args[0];
                    default -> throw new UnsupportedOperationException("Unexpected IMap method " + method);
                });

        HazelcastInstance instance = (HazelcastInstance) Proxy.newProxyInstance(
                HazelcastInstance.class.getClassLoader(),
                new Class<?>[]{HazelcastInstance.class},
                (proxy, method, args) -> switch (method.getName()) {
                    case "getMap" -> map;
                    case "getName" -> "sanity-instance";
                    case "toString" -> "InMemoryHazelcastInstance";
                    case "hashCode" -> System.identityHashCode(proxy);
                    case "equals" -> proxy == args[0];
                    default -> throw new UnsupportedOperationException("Unexpected HazelcastInstance method " + method);
                });
        return new HazelcastInstances(List.of(instance));
    }

    @SuppressWarnings("unchecked")
    private static TestCase loadTestCase(String suiteFile) throws IOException {
        Map<String, Object> suite;
        try (InputStream input = Files.newInputStream(Path.of(suiteFile))) {
            List<Map<String, Object>> suites = new Yaml().load(input);
            assertEquals(1, suites.size());
            suite = suites.get(0);
        }

        String clientArgs = suite.get("client_args").toString();
        assertTrue(clientArgs.contains("-Xlog:gc*:file="));
        assertFalse(clientArgs.contains(":stdout:"));
        assertEquals(1, ((Number) suite.get("loadgenerator_count")).intValue());
        String suiteName = suite.get("name").toString();
        String clientConfig = suite.get("client_hazelcast_xml").toString();
        assertTrue(clientConfig.endsWith(suiteName.contains("explicit")
                ? "client-hazelcast-explicit.xml" : "client-hazelcast.xml"));

        List<Map<String, Object>> tests = (List<Map<String, Object>>) suite.get("test");
        assertEquals(1, tests.size());
        Map<String, Object> properties = tests.get(0);
        TestCase testCase = new TestCase(properties.get("name").toString());
        properties.forEach(testCase::setProperty);
        return testCase;
    }
}
