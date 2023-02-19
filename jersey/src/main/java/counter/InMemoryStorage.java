package counter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.LongAdder;

public class InMemoryStorage {

  private LongAdder counter;

  private static Logger log;

  private static InMemoryStorage instance;

  private InMemoryStorage() {
    counter = new LongAdder();
    log = LoggerFactory.getLogger(InMemoryStorage.class);
  }

  public static InMemoryStorage getInstance() {
    if (instance == null) {
      instance = new InMemoryStorage();
    }
    return instance;
  }

  public long getCounter() {
    logCounterInfo();
    return counter.longValue();
  }

  public long increaseCounter() {
    counter.increment();
    logCounterInfo();
    return counter.longValue();
  }

  public long reductionCounter(long reductionValue) {
    counter.add(-1L * reductionValue);
    logCounterInfo();
    return counter.longValue();
  }

  public long clearCounter() {
    counter.reset();
    logCounterInfo();
    return counter.longValue();
  }

  private void logCounterInfo() {
    log.info("Current counter value: {}", counter.longValue());
  }

}
