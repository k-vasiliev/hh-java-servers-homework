package util;

import java.util.concurrent.atomic.LongAdder;

public class Counter {
    private final LongAdder longAdder = new LongAdder();
    private static Counter counter;

    private Counter() {}
    static public Counter getInstance() {
        if (counter == null) {
            counter = new Counter();
        }
        return counter;
    }

    public long getCount() {
        return longAdder.longValue();
    }

    public void increase() {
        longAdder.increment();
    }

    @SuppressWarnings("unused")
    public void increaseBy(int value) {
        longAdder.add(value);
    }

    @SuppressWarnings("unused")
    public void decrease() {
        longAdder.decrement();
    }

    public void decreaseBy(int value) {
        longAdder.add(-value);
    }

    public void clear() {
        longAdder.reset();
    }
}
