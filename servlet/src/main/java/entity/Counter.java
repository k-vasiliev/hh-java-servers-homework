package entity;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private Counter() {}

    private static final AtomicInteger COUNTER = new AtomicInteger(0);

    public static int get() {
        return COUNTER.get();
    }

    public static int incrementAndGet() {
        return COUNTER.incrementAndGet();
    }

    public static int addAndGet(int delta) {
        return COUNTER.addAndGet(delta);
    }

    public static void set(int value) {
        COUNTER.set(value);
    }

}
