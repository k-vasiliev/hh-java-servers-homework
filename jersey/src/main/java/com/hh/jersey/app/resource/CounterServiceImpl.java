package com.hh.jersey.app.resource;

import jakarta.inject.Singleton;

@Singleton
public class CounterServiceImpl implements CounterService {
    private volatile long counter = 0L;
    private final Object monitor = new Object();

    public CounterServiceImpl() {
    }

    public long getCounter() {
        synchronized (monitor) {
            return counter;
        }
    }

    public void increment() {
        synchronized (monitor) {
            this.counter++;
        }
    }

    public void decrement() {
        synchronized (monitor) {
            this.counter--;
        }
    }

    public void reset() {
        synchronized (monitor) {
            this.counter = 0;
        }

    }

    public void decrementByValue(long value) {
        synchronized (monitor) {
            this.counter -= value;
        }
    }
}
