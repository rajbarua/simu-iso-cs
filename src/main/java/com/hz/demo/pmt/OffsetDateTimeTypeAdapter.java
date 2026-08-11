package com.hz.demo.pmt;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

import java.time.OffsetDateTime;

/** Matches the date/time binding used by streaming-payments. */
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

