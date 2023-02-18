package service;

import dao.CounterDao;

public class CounterService {
  private final static CounterDao counterDao = CounterDao.getInstance();
  private final static CounterService instance = new CounterService();

  private CounterService() {
  }

  public static CounterService getInstance() {
    return instance;
  }

  public long getCounterValue() {
    return counterDao.get();
  }

  public void incrementCounter() {
    counterDao.increment();
  }

  public void clearCounter() {
    counterDao.clear();
  }

  public void decrementCounter(long delta) {
    counterDao.decrement(delta);
  }
}
