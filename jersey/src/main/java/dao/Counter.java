package dao;

import java.util.concurrent.atomic.LongAdder;

public final class Counter {

    private static final Counter counterInstance = new Counter();
    private static final LongAdder counter = new LongAdder();

    private Counter() {
    }

    public LongAdder get() {
        return counter;
    }

    public void increment() {
        counter.increment();
    }

    public void decrement(long decrement) {
        counter.add(decrement * -1);
    }


    public static Counter getInstance() {
        return counterInstance;
    }

    public void clear() {
        counter.reset();
    }
}
