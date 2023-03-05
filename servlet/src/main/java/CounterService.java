import java.util.concurrent.atomic.LongAdder;

public class CounterService {
    private LongAdder counter = new LongAdder();

    CounterService() {
    }

    public LongAdder getCounter() {
        return counter;
    }

    public void setCounter(LongAdder counter) {
        this.counter = counter;
    }

    public void increment() {
        getCounter().increment();
    }

    public void decrement() {
        getCounter().decrement();
    }

    public void reset() {
        getCounter().reset();
    }

    public void decrementByValue(long value) {
        if (value == 0) {
            return;
        }
        getCounter().add((-1) * value);
    }

}
