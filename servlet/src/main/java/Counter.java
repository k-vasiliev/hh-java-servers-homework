import java.time.LocalDateTime;

public class Counter {
    private static int count;
    static {
        count = 0;
    }

    public synchronized static int getCount() {
        return count;
    }

    public synchronized static void setCount(int count) {
        Counter.count = count;
    }
}
