package controller;

import java.util.concurrent.atomic.AtomicLong;

public enum Counter {
    INSTANCE;
    public static final long INITIAL_VALUE = 0L;
    private final AtomicLong counter = new AtomicLong();

    public long increment() {
        return counter.incrementAndGet();
    }

    public long add(long value) {
        return counter.addAndGet(value);
    }

    public long reset() {
        counter.set(INITIAL_VALUE);
        return INITIAL_VALUE;
    }

    public long getCounter() {
        return counter.get();
    }
}
