package com.hh.jersey.app.resource;

import jakarta.inject.Singleton;

import java.util.concurrent.atomic.LongAdder;

@Singleton
public class CounterServiceImpl implements CounterService {
    private LongAdder counter = new LongAdder();

    public CounterServiceImpl() {
    }

    public long getCounter() {
        return counter.longValue();
    }

    public void increment() {
        this.counter.increment();
    }

    public void decrement() {
        this.counter.decrement();
    }

    public void reset() {
        counter.reset();
    }

    public void decrementByValue(long value) {
        this.counter.add(-value);
    }
}
