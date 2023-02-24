package ru.hh;

public class CounterDao {
    private final static Counter COUNTER = new Counter();

    public void clean() {
        COUNTER.clean();
    }

    public Long getValue() {
        return COUNTER.getValue();
    }

    public void add(long value) {
         COUNTER.add(value);
    }
}
