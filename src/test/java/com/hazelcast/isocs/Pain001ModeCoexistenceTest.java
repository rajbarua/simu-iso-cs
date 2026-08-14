package com.hazelcast.isocs;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.isocs.serialization.Pain001ExplicitCompactSerializers;
import com.hazelcast.isocs.serialization.Pain001ReflectiveCompactSerializers;
import com.hazelcast.isocs.xml.Pain001Parser;
import com.hazelcast.isocs.xml.Pain001SampleGenerator;
import com.hazelcast.isocs.xml.SampleXmlLoader;
import com.hz.demo.pmt.pain001_03.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Pain001ModeCoexistenceTest {
    @TempDir
    Path temporaryDirectory;

    @Test
    void allClientModesUseOneSerializerFreeCluster() throws Exception {
        Config memberConfig = new Config();
        memberConfig.setClusterName("simu-iso-cs-coexistence");
        memberConfig.getNetworkConfig().setPort(58700).setPortAutoIncrement(true);
        memberConfig.getNetworkConfig().getInterfaces().setEnabled(true).addInterface("127.0.0.1");
        memberConfig.getNetworkConfig().getJoin().getAutoDetectionConfig().setEnabled(false);
        memberConfig.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
        memberConfig.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);

        HazelcastInstance member = Hazelcast.newHazelcastInstance(memberConfig);
        HazelcastInstance noCodeClient = null;
        HazelcastInstance explicitClient = null;
        HazelcastInstance reflectiveClient = null;
        try {
            String address = "127.0.0.1:" + member.getCluster().getLocalMember().getAddress().getPort();
            ClientConfig noCodeConfig = clientConfig(address);
            ClientConfig explicitConfig = clientConfig(address);
            Pain001ExplicitCompactSerializers.register(
                    explicitConfig.getSerializationConfig().getCompactSerializationConfig());
            ClientConfig reflectiveConfig = clientConfig(address);
            Pain001ReflectiveCompactSerializers.register(
                    reflectiveConfig.getSerializationConfig().getCompactSerializationConfig());
            noCodeClient = HazelcastClient.newHazelcastClient(noCodeConfig);
            explicitClient = HazelcastClient.newHazelcastClient(explicitConfig);
            reflectiveClient = HazelcastClient.newHazelcastClient(reflectiveConfig);

            Path sample = temporaryDirectory.resolve("pain001.xml.gz");
            Pain001SampleGenerator.generate(sample, 97, 7, 1024 * 1024L);
            Document document = new Pain001Parser().parse(SampleXmlLoader.load(sample.toString(), "unused"));

            noCodeClient.<String, Document>getMap("pain001-files-nocode").set("file", document);
            explicitClient.<String, Document>getMap("pain001-files-explicit").set("file", document);
            reflectiveClient.<String, Document>getMap("pain001-files-reflective").set("file", document);

            assertShape(noCodeClient.<String, Document>getMap("pain001-files-nocode").get("file"));
            assertShape(explicitClient.<String, Document>getMap("pain001-files-explicit").get("file"));
            assertShape(reflectiveClient.<String, Document>getMap("pain001-files-reflective").get("file"));
        } finally {
            if (noCodeClient != null) noCodeClient.shutdown();
            if (explicitClient != null) explicitClient.shutdown();
            if (reflectiveClient != null) reflectiveClient.shutdown();
            member.shutdown();
        }
    }

    private static ClientConfig clientConfig(String address) {
        ClientConfig config = new ClientConfig();
        config.setClusterName("simu-iso-cs-coexistence");
        config.getNetworkConfig().addAddress(address);
        config.getConnectionStrategyConfig().getConnectionRetryConfig().setClusterConnectTimeoutMillis(5_000);
        return config;
    }

    private static void assertShape(Document document) {
        assertEquals("97", document.getCstmrCdtTrfInitn().getGrpHdr().getNbOfTxs());
        assertEquals(7, document.getCstmrCdtTrfInitn().getPmtInf().size());
        assertEquals(97, document.getCstmrCdtTrfInitn().getPmtInf().stream()
                .mapToInt(paymentInfo -> paymentInfo.getCdtTrfTxInf().size())
                .sum());
    }
}
