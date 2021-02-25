import java.util.concurrent.atomic.AtomicInteger;

public final class Count {
    private final static AtomicInteger value = new AtomicInteger(0);

    public static Integer getValue() {
        return value.intValue();
    }

    public static void setValue(Integer newValue) {
        value.set(newValue);
    }

    public static void inc() {
        value.incrementAndGet();
    }

    public static void subtract(Integer sub) {
        value.addAndGet(-sub);
    }
}
