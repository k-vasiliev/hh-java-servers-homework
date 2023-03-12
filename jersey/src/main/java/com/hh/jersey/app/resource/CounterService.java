package com.hh.jersey.app.resource;

import java.util.concurrent.atomic.LongAdder;

public interface CounterService {
    public long getCounter();

    public void increment();

    public void decrement();

    public void reset();

    public void decrementByValue(long value);
}
