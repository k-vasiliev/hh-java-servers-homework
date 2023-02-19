package dao;

import java.util.concurrent.atomic.LongAdder;

public class CounterDao {
  private final static CounterDao instance = new CounterDao();
  private final LongAdder count = new LongAdder();

  private CounterDao() {
  }

  public static CounterDao getInstance() {
    return instance;
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

  public void decrement(long delta) {
    count.add(delta);
  }
}
