package counter;
import java.util.concurrent.atomic.AtomicInteger;

public class Counter {
  private static final Counter INSTANCE = new Counter();
  private final AtomicInteger counter;

  public Counter() {
    counter = new AtomicInteger();
  }

  public static Counter getInstance() {
    return INSTANCE;
  }

  public int getValue() {
    return counter.get();
  }

  public int addValue(int value) {
    return counter.addAndGet(value);
  }

  public void resetValue() {
    counter.set(0);
  }

  public int increment() {
    return counter.getAndIncrement();
  }
}