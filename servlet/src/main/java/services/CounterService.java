package services;

import java.util.concurrent.atomic.AtomicLong;

public class CounterService {

  private final static CounterService INSTANCE = new CounterService();
  private final AtomicLong counter;

  public CounterService() {
    counter = new AtomicLong();
  }

  public static CounterService getInstance() {
    return INSTANCE;
  }

  public long getCounter() {
    return counter.longValue();
  }

  public void incrementCounter() {
    counter.incrementAndGet();
  }

  public void subtractCounter(Long subtractionValue) {
    if (subtractionValue != null) {
      counter.addAndGet(-1 * subtractionValue);
    }
  }

  public void resetCounter() {
    counter.set(0);
  }
}
