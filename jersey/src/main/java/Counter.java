import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private static final Counter COUNTER_INSTANCE = new Counter();
    private final AtomicInteger counter;

    private Counter() {
        counter = new AtomicInteger();
    }

    public static Counter getCounterInstance() {
        return COUNTER_INSTANCE;
    }
    @JsonGetter("value")
    public AtomicInteger getCounter() {
        return counter;
    }

    public void incrementCounter() {
        counter.getAndIncrement();
    }

    public void decrementCounter(int num) {
        counter.addAndGet(-num);
    }

    public void resetCounter() {
        counter.set(0);
    }
}
