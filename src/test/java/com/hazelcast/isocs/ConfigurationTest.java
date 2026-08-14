package com.hazelcast.isocs;

import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.InMemoryFormat;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.isocs.serialization.Pain001ExplicitCompactSerializers;
import com.hazelcast.isocs.serialization.Pain001ReflectiveCompactSerializers;
import com.hazelcast.isocs.serialization.GeneratedPain001CompactSerializer;
import com.hazelcast.isocs.serialization.ReflectivePain001CompactSerializer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConfigurationTest {
    @Test
    void clientAndMemberXmlAreValid() throws Exception {
        var noCodeClientConfig = new XmlClientConfigBuilder("client-hazelcast.xml").build();
        assertEquals("hz-primary", noCodeClientConfig.getClusterName());
        assertNotNull(noCodeClientConfig.getSerializationConfig().getCompactSerializationConfig());

        var explicitClientConfig = new XmlClientConfigBuilder("client-hazelcast-explicit.xml").build();
        assertEquals("hz-primary", explicitClientConfig.getClusterName());
        assertNotNull(explicitClientConfig.getSerializationConfig().getCompactSerializationConfig());
        assertEquals(66, Pain001ExplicitCompactSerializers.all().length);
        assertTrue(java.util.Arrays.stream(Pain001ExplicitCompactSerializers.all())
                .allMatch(GeneratedPain001CompactSerializer.class::isInstance));

        var reflectiveClientConfig = new XmlClientConfigBuilder("client-hazelcast-reflective.xml").build();
        assertEquals("hz-primary", reflectiveClientConfig.getClusterName());
        assertNotNull(reflectiveClientConfig.getSerializationConfig().getCompactSerializationConfig());
        assertEquals(66, Pain001ReflectiveCompactSerializers.all().length);
        assertTrue(java.util.Arrays.stream(Pain001ReflectiveCompactSerializers.all())
                .allMatch(ReflectivePain001CompactSerializer.class::isInstance));

        var memberConfig = new XmlConfigBuilder("hazelcast.xml").build();
        assertEquals("hz-primary", memberConfig.getClusterName());
        assertEquals(InMemoryFormat.NATIVE, memberConfig.getMapConfig("pain001-files-nocode").getInMemoryFormat());
        assertEquals(InMemoryFormat.NATIVE, memberConfig.getMapConfig("pain001-files-explicit").getInMemoryFormat());
        assertEquals(InMemoryFormat.NATIVE, memberConfig.getMapConfig("pain001-files-reflective").getInMemoryFormat());
        assertEquals(1, memberConfig.getMapConfig("pain001-files-nocode").getBackupCount());
        assertTrue(memberConfig.getNativeMemoryConfig().isEnabled());
        assertEquals(4L, memberConfig.getNativeMemoryConfig().getCapacity().gigaBytes());
    }
}
