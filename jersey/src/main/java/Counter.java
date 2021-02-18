import java.util.concurrent.atomic.AtomicInteger;

public class Counter {

    private AtomicInteger counter;

    public Counter() {
        counter = new AtomicInteger();
    }

    public int get() {
        return counter.get();
    }

    public void set(int newValue) {
        counter.set(newValue);
    }

    public void increment() {
        counter.incrementAndGet();
    }

    public void decrement() {
        counter.decrementAndGet();
    }

    public void add(int addValue) {
        counter.addAndGet(addValue);
    }

    public void subtract(int subtractionValue) {
        counter.addAndGet(-subtractionValue);
    }
}
