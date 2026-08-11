package com.hazelcast.isocs.xml;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.zip.GZIPInputStream;

/** Loads a plain or gzip-compressed XML sample into memory before timed operations begin. */
public final class SampleXmlLoader {
    private SampleXmlLoader() {
    }

    public static byte[] load(String externalPath, String classpathResource) {
        String sourceName;
        try (InputStream raw = open(externalPath, classpathResource)) {
            sourceName = externalPath == null || externalPath.isBlank() ? classpathResource : externalPath;
            InputStream input = sourceName.endsWith(".gz") ? new GZIPInputStream(raw, 1 << 20) : raw;
            try (input; ByteArrayOutputStream output = new ByteArrayOutputStream(192 * 1024 * 1024)) {
                input.transferTo(output);
                return output.toByteArray();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load pain.001 sample " + sourceName(externalPath, classpathResource), e);
        }
    }

    private static InputStream open(String externalPath, String classpathResource) throws IOException {
        if (externalPath != null && !externalPath.isBlank()) {
            return Files.newInputStream(Path.of(externalPath));
        }
        return Objects.requireNonNull(
                SampleXmlLoader.class.getClassLoader().getResourceAsStream(classpathResource),
                "Missing classpath sample: " + classpathResource);
    }

    private static String sourceName(String externalPath, String classpathResource) {
        return externalPath == null || externalPath.isBlank() ? classpathResource : externalPath;
    }
}

