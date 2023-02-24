package ru.hh;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public class CounterDto {
    public final Instant date;
    public final long value;

    public CounterDto(long value) {
        this(value, Instant.now());
    }
    @JsonCreator
    public CounterDto(@JsonProperty("value") long value,
                      @JsonProperty("date") Instant date) {
        this.date = date;
        this.value = value;
    }
}
