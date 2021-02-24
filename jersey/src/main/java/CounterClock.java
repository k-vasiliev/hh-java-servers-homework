import java.time.LocalDateTime;
import java.time.Clock;

public class CounterClock {
    private static CounterClock instance;
    private Clock clock;

    private CounterClock() {
        this(Clock.systemDefaultZone());
    }

    private CounterClock(Clock clockArg) {
        clock = clockArg;
    }

    public static CounterClock getInstance() {
        if (instance == null) {
            instance = new CounterClock();
        }
        return instance;
    }

    public LocalDateTime getTimestamp() {
        return LocalDateTime.now(clock);
    }
}
