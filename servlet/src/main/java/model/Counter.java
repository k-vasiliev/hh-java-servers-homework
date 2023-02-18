package model;

import java.util.concurrent.atomic.LongAdder;

public class Counter {
  private final LongAdder count;

  public Counter() {
    count = new LongAdder();
  }

  public void clear() {
    count.reset();
  }

  public long get() {
    return count.longValue();
  }

  public void increment() {
    count.increment();
  }

  public synchronized void decrement(long delta) {
    count.add(-delta);
  }
}
