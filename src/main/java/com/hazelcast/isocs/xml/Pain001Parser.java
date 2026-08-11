package com.hazelcast.isocs.xml;

import com.hz.demo.pmt.pain001_03.Document;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.ByteArrayInputStream;

/** JAXB parser equivalent to the pain.001.001.03 parser in streaming-payments. */
public final class Pain001Parser {
    private static final String MODEL_PACKAGE = "com.hz.demo.pmt.pain001_03";

    private final JAXBContext context;

    public Pain001Parser() {
        try {
            context = JAXBContext.newInstance(MODEL_PACKAGE);
        } catch (JAXBException e) {
            throw new IllegalStateException("Cannot initialise pain.001 JAXB context", e);
        }
    }

    public Document parse(byte[] xml) {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Object result = unmarshaller.unmarshal(new ByteArrayInputStream(xml));
            if (result instanceof JAXBElement<?> element) {
                return (Document) element.getValue();
            }
            return (Document) result;
        } catch (JAXBException e) {
            throw new IllegalArgumentException("Invalid pain.001 XML", e);
        }
    }
}

