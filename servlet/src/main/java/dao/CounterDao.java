package dao;

import java.util.concurrent.atomic.AtomicLong;

public class CounterDao {
  private final static CounterDao instance = new CounterDao();
  private final AtomicLong count = new AtomicLong(0L);

  private CounterDao() {
  }

  public static CounterDao getInstance() {
    return instance;
  }

  public void clear() {
    count.set(0L);
  }

  public long get() {
    return count.longValue();
  }

  public void increment() {
    count.incrementAndGet();
  }

  public void decrement(long delta) {
    count.addAndGet(-delta);
  }
}
