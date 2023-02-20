package counter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.LongAdder;

public class InMemoryStorage {

  private static final LongAdder counter = new LongAdder();

  private static final Logger log = LoggerFactory.getLogger(InMemoryStorage.class);

  private static final InMemoryStorage instance = new InMemoryStorage();

  public static InMemoryStorage getInstance() {
    return instance;
  }

  public long getCounter() {
    log.info("Current counter value: {}", counter.longValue());
    return counter.longValue();
  }

  public long increaseCounter() {
    counter.increment();
    return getCounter();
  }

  public long reductionCounter(long reductionValue) {
    counter.add(-1L * reductionValue);
    return getCounter();
  }

  public long clearCounter() {
    counter.reset();
    return getCounter();
  }

}
