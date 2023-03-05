package com.hh.jersey.app.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.time.Instant;

public class CounterInfo implements Serializable {
    @JsonProperty("date")
    private Instant dateTime;
    private long value;

    public CounterInfo() {
    }


    public Instant getDateTime() {
        return dateTime;
    }

    public void setDateTime(Instant dateTime) {
        this.dateTime = dateTime;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
