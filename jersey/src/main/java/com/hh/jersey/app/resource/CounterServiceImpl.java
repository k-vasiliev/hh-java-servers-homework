package com.hh.jersey.app.resource;

import jakarta.inject.Singleton;

@Singleton
public class CounterServiceImpl implements CounterService {
    private long counter = 0L;

    public CounterServiceImpl() {
    }

    public long getCounter() {
        return counter;
    }

    public void increment() {
        this.counter++;
    }

    public void decrement() {
        this.counter--;
    }

    public void reset() {
        this.counter = 0;
    }

    public void decrementByValue(long value) {
        this.counter -= value;
    }
}
