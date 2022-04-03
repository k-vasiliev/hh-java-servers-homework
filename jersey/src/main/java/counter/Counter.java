package counter;

import java.time.LocalDateTime;

public class Counter {
    private static LocalDateTime date;
    private static int count;

    static {
        date = LocalDateTime.now();
        count = 0;
    }

    public synchronized static LocalDateTime getDate() {
        return date;
    }
    public synchronized static int getCount() {
        return count;
    }

    public synchronized static void setCount(int count) {
        Counter.count = count;
    }
}
