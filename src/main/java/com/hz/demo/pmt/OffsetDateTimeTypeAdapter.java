package com.hz.demo.pmt;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.OffsetDateTime;

/** JAXB adapter used by the generated ISO 20022 model. */
public final class OffsetDateTimeTypeAdapter extends XmlAdapter<String, OffsetDateTime> {
    @Override
    public OffsetDateTime unmarshal(String value) {
        return OffsetDateTime.parse(value);
    }

    @Override
    public String marshal(OffsetDateTime value) {
        return value.toString();
    }
}
