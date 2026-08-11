package com.hazelcast.isocs;

import com.hazelcast.config.SerializationConfig;
import com.hazelcast.internal.serialization.InternalSerializationService;
import com.hazelcast.internal.serialization.impl.DefaultSerializationServiceBuilder;
import com.hazelcast.internal.serialization.impl.compact.Schema;
import com.hazelcast.internal.serialization.impl.compact.SchemaService;
import com.hazelcast.isocs.xml.Pain001Parser;
import com.hazelcast.isocs.xml.Pain001SampleGenerator;
import com.hazelcast.isocs.xml.SampleXmlLoader;
import com.hazelcast.isocs.serialization.Pain001ExplicitCompactSerializers;
import com.hz.demo.pmt.pain001_03.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pain001CompactSerializationTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void zeroConfigCompactRoundTripsGeneratedJaxbGraph() throws Exception {
        Path output = temporaryDirectory.resolve("pain001.xml.gz");
        Pain001SampleGenerator.generate(output, 40, 4, 256 * 1024L);
        Document original = new Pain001Parser().parse(SampleXmlLoader.load(output.toString(), "unused"));
        InternalSerializationService serializationService = new DefaultSerializationServiceBuilder()
                .setSchemaService(new InMemorySchemaService())
                .build();
        try {
            assertTrue(serializationService.isCompactSerializable(original));
            var data = serializationService.toData(original);
            Document restored = serializationService.toObject(data);

            assertNotSame(original, restored);
            assertEquals("40", restored.getCstmrCdtTrfInitn().getGrpHdr().getNbOfTxs());
            assertEquals(4, restored.getCstmrCdtTrfInitn().getPmtInf().size());
            assertEquals(40, restored.getCstmrCdtTrfInitn().getPmtInf().stream()
                    .mapToInt(paymentInfo -> paymentInfo.getCdtTrfTxInf().size())
                    .sum());
            assertTrue(data.totalSize() > 0);
        } finally {
            serializationService.dispose();
        }
    }

    @Test
    void noCodeAndExplicitCompactRoundTripInOneSchemaNamespace() throws Exception {
        Path output = temporaryDirectory.resolve("pain001.xml.gz");
        Pain001SampleGenerator.generate(output, 40, 4, 256 * 1024L);
        Document original = new Pain001Parser().parse(SampleXmlLoader.load(output.toString(), "unused"));
        InMemorySchemaService sharedSchemas = new InMemorySchemaService();

        InternalSerializationService noCode = new DefaultSerializationServiceBuilder()
                .setSchemaService(sharedSchemas)
                .build();
        SerializationConfig explicitConfig = new SerializationConfig();
        Pain001ExplicitCompactSerializers.register(explicitConfig.getCompactSerializationConfig());
        InternalSerializationService explicit = (InternalSerializationService) new DefaultSerializationServiceBuilder()
                .setConfig(explicitConfig)
                .setSchemaService(sharedSchemas)
                .build();
        try {
            var noCodeData = noCode.toData(original);
            var explicitData = explicit.toData(original);
            Document noCodeRestored = noCode.toObject(noCodeData);
            Document explicitRestored = explicit.toObject(explicitData);

            assertEquivalentGraph(original, noCodeRestored);
            assertEquivalentGraph(original, explicitRestored);
            assertNotEquals(noCodeData, explicitData,
                    "the explicit type-name namespace must not collide with no-code Compact schemas");
            assertTrue(sharedSchemas.size() > 1);
        } finally {
            noCode.dispose();
            explicit.dispose();
        }
    }

    private static void assertEquivalentGraph(Document expected, Document actual) {
        assertNotSame(expected, actual);
        var expectedInitiation = expected.getCstmrCdtTrfInitn();
        var actualInitiation = actual.getCstmrCdtTrfInitn();
        assertEquals(expectedInitiation.getGrpHdr().getCreDtTm(), actualInitiation.getGrpHdr().getCreDtTm());
        assertEquals(expectedInitiation.getPmtInf().size(), actualInitiation.getPmtInf().size());
        assertEquals(expectedInitiation.getPmtInf().get(0).getReqdExctnDt(),
                actualInitiation.getPmtInf().get(0).getReqdExctnDt());
        assertEquals(expectedInitiation.getPmtInf().get(0).getCdtTrfTxInf().size(),
                actualInitiation.getPmtInf().get(0).getCdtTrfTxInf().size());
        assertEquals(expectedInitiation.getPmtInf().get(0).getCdtTrfTxInf().get(0).getRmtInf().getUstrd(),
                actualInitiation.getPmtInf().get(0).getCdtTrfTxInf().get(0).getRmtInf().getUstrd());
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

        private int size() {
            return schemas.size();
        }
    }
}
