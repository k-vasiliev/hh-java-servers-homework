package service;

import model.Counter;

public class CounterService {
  private final static Counter counter = new Counter();

  public CounterService() {
  }

  public Long getCounterValue() {
    return counter.get();
  }
}
