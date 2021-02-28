package counter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private final AtomicInteger counter;

    public Counter() {
        counter = new AtomicInteger();
    }

    public int getValue() {
        return counter.get();
    }

    public void setValue(int value) {
        counter.set(value);
    }

    public int increment() {
        return counter.incrementAndGet();
    }

    public int decrement() {
        return counter.decrementAndGet();
    }

    public int addValue(int value) {
        return counter.addAndGet(value);
    }

    public int subtractValue(int value) {
        return counter.addAndGet((-1) * value);
    }
}
