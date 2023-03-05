package util;

public class Counter {
    private int count = 0;
    private static Counter counter;

    private final Object monitor = new Object();
    private Counter() {}
    static public Counter getInstance() {
        if (counter == null) {
            counter = new Counter();
        }
        return counter;
    }

    public int getCount() {
        return count;
    }

    public void increase() {
        synchronized (monitor) {
            this.count++;
        }
    }

    @SuppressWarnings("unused")
    public void increaseBy(int value) {
        synchronized (monitor) {
            this.count += value;
        }
    }

    @SuppressWarnings("unused")
    public void decrease() {
        synchronized (monitor) {
            this.count--;
        }
    }

    public void decreaseBy(int value) {
        synchronized (monitor) {
            this.count -= value;
        }
    }

    public void clear() {
        count = 0;
    }
}
