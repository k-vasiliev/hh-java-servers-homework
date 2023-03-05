package storage;

import java.util.concurrent.atomic.LongAdder;

public class StorageCounter {

  private StorageCounter() {}

  private final static LongAdder counter = new LongAdder();

  public static StorageCounter getInstance() {
    return new StorageCounter();
  }

  public Long getCounter() {
    return counter.longValue();
  }

  public Long increment() {
    counter.increment();
    return counter.longValue();
  }

  public Long decrement(Long value) {
    counter.add(-value);
    return counter.longValue();
  }

  public void reset() {
    counter.reset();
  }
}
