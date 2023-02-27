package service;

import java.util.concurrent.atomic.LongAdder;

public class CounterService {
    private static final LongAdder counter = new LongAdder();
    private static final CounterService counterServiceInstance = new CounterService();

    public static CounterService getInstance() {
        return counterServiceInstance;
    }

    public long getCounter() {
        return counter.longValue();
    }

    public void increment() {
        counter.increment();
    }

    public void substract(long reductionValue) {
        counter.add(-1L * reductionValue);
    }

    public void clearCounter() {
        counter.reset();
    }
}
