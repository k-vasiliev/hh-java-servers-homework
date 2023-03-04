import java.util.concurrent.atomic.AtomicInteger;

public class CounterService  {
    private final AtomicInteger count;

    private static CounterService INSTANCE;

    private CounterService() {
        this.count = new AtomicInteger();
    }

    public static CounterService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CounterService();
        }
        return INSTANCE;
    }

    public int getCount() {
        return count.get();
    }

    public void incrementCounter() {
        count.incrementAndGet();
    }

    public void subtractCounter(int value) {
        count.addAndGet(-value);
    }

    public void clear() {
        count.set(0);
    }

}
