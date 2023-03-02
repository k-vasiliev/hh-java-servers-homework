package storage;

import java.util.concurrent.atomic.LongAdder;

public class CounterStorage {

  private CounterStorage() {
  }

  private final static LongAdder counter = new LongAdder();

  public static CounterStorage getInstance() {
    return new CounterStorage();
  }

  public Long increment() {
    counter.increment();
    return counter.longValue();
  }

  public Long subtract(Long subtrahend) {
    counter.add(-subtrahend);
    return counter.longValue();
  }

  public Long getCounter() {
    return counter.longValue();
  }

  public void reset() {
    counter.reset();
  }

}
