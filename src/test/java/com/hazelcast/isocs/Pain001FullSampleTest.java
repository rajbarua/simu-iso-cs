package com.hazelcast.isocs;

import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.internal.serialization.impl.compact.Schema;
import com.hazelcast.internal.serialization.impl.compact.SchemaService;
import com.hazelcast.isocs.xml.Pain001Parser;
import com.hazelcast.isocs.xml.SampleXmlLoader;
import com.hazelcast.isocs.serialization.Pain001ExplicitCompactSerializers;
import com.hazelcast.isocs.serialization.Pain001ReflectiveCompactSerializers;
import com.hz.demo.pmt.pain001_03.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Explicit, memory-intensive verification of the packaged 180 MiB fixture. */
@EnabledIfSystemProperty(named = "fullSample", matches = "true")
class Pain001FullSampleTest {
    @Test
    void fullFixtureParsesAndCompactRoundTrips() {
        byte[] xml = SampleXmlLoader.load("", "samples/pain001-150k-2k-180MiB.xml.gz");
        assertEquals(180L * 1024 * 1024, xml.length);
        long parseStarted = System.nanoTime();
        Document original = new Pain001Parser().parse(xml);
        long parseNanos = System.nanoTime() - parseStarted;
        assertDocumentShape(original);

        InternalSerializationService noCode = new DefaultSerializationServiceBuilder()
                .setSchemaService(new InMemorySchemaService())
                .build();
        SerializationConfig explicitConfig = new SerializationConfig();
        Pain001ExplicitCompactSerializers.register(explicitConfig.getCompactSerializationConfig());
        InternalSerializationService explicit = (InternalSerializationService) new DefaultSerializationServiceBuilder()
                .setConfig(explicitConfig)
                .setSchemaService(new InMemorySchemaService())
                .build();
        SerializationConfig reflectiveConfig = new SerializationConfig();
        Pain001ReflectiveCompactSerializers.register(reflectiveConfig.getCompactSerializationConfig());
        InternalSerializationService reflective = (InternalSerializationService) new DefaultSerializationServiceBuilder()
                .setConfig(reflectiveConfig)
                .setSchemaService(new InMemorySchemaService())
                .build();
        try {
            int noCodeBytes = runRoundTrip("no-code", noCode, original, xml.length, parseNanos);
            int reflectiveBytes = runRoundTrip("reflective-explicit", reflective, original, xml.length, parseNanos);
            int explicitBytes = runRoundTrip("generated-explicit", explicit, original, xml.length, parseNanos);
            assertEquals(noCodeBytes, reflectiveBytes, "reflective payload must represent the same graph");
            assertEquals(noCodeBytes, explicitBytes, "generated payload must represent the same graph");
        } finally {
            noCode.dispose();
            explicit.dispose();
            reflective.dispose();
        }
    }

    private static int runRoundTrip(String mode, InternalSerializationService serializationService,
                                    Document original, int xmlBytes, long parseNanos) {
        long serializeStarted = System.nanoTime();
        var data = serializationService.toData(original);
        long serializeNanos = System.nanoTime() - serializeStarted;
        assertTrue(data.totalSize() > 100 * 1024 * 1024);
        long deserializeStarted = System.nanoTime();
        Document restored = serializationService.toObject(data);
        long deserializeNanos = System.nanoTime() - deserializeStarted;
        assertDocumentShape(restored);
        assertEquals(original.getCstmrCdtTrfInitn().getGrpHdr().getCreDtTm(),
                restored.getCstmrCdtTrfInitn().getGrpHdr().getCreDtTm());
        System.out.printf(
                "Full sample (%s): XML=%,d bytes, Compact=%,d bytes, parse=%.3f s, serialize=%.3f s, deserialize=%.3f s%n",
                mode, xmlBytes, data.totalSize(), parseNanos / 1_000_000_000.0,
                serializeNanos / 1_000_000_000.0, deserializeNanos / 1_000_000_000.0);
        return data.totalSize();
    }

    private static void assertDocumentShape(Document document) {
        assertEquals("150000", document.getCstmrCdtTrfInitn().getGrpHdr().getNbOfTxs());
        assertEquals(2_000, document.getCstmrCdtTrfInitn().getPmtInf().size());
        assertEquals(150_000, document.getCstmrCdtTrfInitn().getPmtInf().stream()
                .mapToInt(paymentInfo -> paymentInfo.getCdtTrfTxInf().size())
                .sum());
    }

    private static final class InMemorySchemaService implements SchemaService {
        private final ConcurrentHashMap<Long, Schema> schemas = new ConcurrentHashMap<>();

        @Override
        public Schema get(long schemaId) {
            return schemas.get(schemaId);
        }

        @Override
        public void put(Schema schema) {
            schemas.put(schema.getSchemaId(), schema);
        }

        @Override
        public void putLocal(Schema schema) {
            put(schema);
        }
    }
}
