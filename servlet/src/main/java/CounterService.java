import java.util.concurrent.atomic.AtomicInteger;

public class CounterService {
  private static final AtomicInteger counter = new AtomicInteger();

  public static int getCounter() {
    return counter.get();
  }

  public static int incrementCounter() {
    return counter.incrementAndGet();
  }

  public static void decrementCounter(int subtrahend) {
    counter.addAndGet(-subtrahend);
  }

  public static void clearCounter() {
    counter.set(0);
  }
}
