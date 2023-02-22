package ru.hh;

import java.util.concurrent.atomic.LongAdder;

public class Counter {
    private final LongAdder total = new LongAdder();

    public void clean() {
        total.reset();
    }

    public Long getValue() {
        return total.longValue();
    }

    public void add(long value) {
       total.add(value);
    }
}
