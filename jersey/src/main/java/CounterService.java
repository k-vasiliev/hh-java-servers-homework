import java.util.concurrent.atomic.AtomicInteger;

public class CounterService {
    private final AtomicInteger count;

    private static CounterService INSTANCE;
    private static final Object mutex = new Object();


    private CounterService() {
        this.count = new AtomicInteger();
    }

    public static CounterService getInstance() {
        CounterService result = INSTANCE;
        if (result == null) {
            synchronized (mutex) {
                result = INSTANCE;
                if (result == null)
                    INSTANCE = result = new CounterService();
            }
        }
        return result;
    }
//    public static CounterService getInstance() {
//        if (INSTANCE == null) {
//            INSTANCE = new CounterService();
//        }
//        return INSTANCE;
//    }

    public int getCount() {
        return count.get();
    }


    public int incrementCounter() {
        return count.incrementAndGet();
    }

    public void subtractCounter(int value) {
        count.addAndGet(-value);
    }

    public void clear() {
        count.set(0);
    }
}
