import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.concurrent.atomic.AtomicInteger;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Counter {
    private AtomicInteger value;
    private static Counter counter;

    private Counter() {
        value = new AtomicInteger(0);
    }

    public static Counter getInstance() {
        if (counter == null) {
            counter = new Counter();
        }
        return counter;
    }

    public Integer getValue() {
        return value.get();
    }

    public String getDate() {
        return LocalDateTime.now().toString();
    }

    public void increment() {
        value.incrementAndGet();
    }

    public void subtract(Integer val) {
        value.addAndGet(-val);
    }

    public void reset() {
        value.set(0);
    }
}
