package ru.hh;

import java.time.Instant;

public class CounterDto {
    public final Instant date;
    public final long value;

    public CounterDto(long value) {
        this(value, Instant.now());
    }
    public CounterDto(long value, Instant date) {
        this.date = date;
        this.value = value;
    }
}
