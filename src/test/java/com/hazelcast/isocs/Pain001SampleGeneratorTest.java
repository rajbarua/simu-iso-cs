package com.hazelcast.isocs;

import com.hazelcast.isocs.xml.Pain001Parser;
import com.hazelcast.isocs.xml.Pain001SampleGenerator;
import com.hazelcast.isocs.xml.SampleXmlLoader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Pain001SampleGeneratorTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void generatesTargetSizedJaxbReadableSample() throws Exception {
        Path output = temporaryDirectory.resolve("pain001.xml.gz");
        long targetBytes = 512 * 1024L;
        var result = Pain001SampleGenerator.generate(output, 97, 7, targetBytes);

        byte[] xml = SampleXmlLoader.load(output.toString(), "unused");
        var document = new Pain001Parser().parse(xml);

        assertTrue(result.uncompressedBytes() >= targetBytes - 16);
        assertEquals(result.uncompressedBytes(), xml.length);
        assertEquals(7, document.getCstmrCdtTrfInitn().getPmtInf().size());
        long transactions = document.getCstmrCdtTrfInitn().getPmtInf().stream()
                .mapToLong(paymentInfo -> paymentInfo.getCdtTrfTxInf().size())
                .sum();
        assertEquals(97, transactions);
    }
}

