package ru.hh;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class CounterCommonService {
    private final static Logger LOGGER = getLogger(CounterCommonService.class);

    private final Counter counter = new Counter();

    public long getCounterValue() {
        var value = counter.getValue();
        LOGGER.info("Get counter's value: " + value);
        return value;
    }

    public void upCounterValue() {
        counter.add(1L);
        LOGGER.info("UpCounterValue was called");
    }

    public void reduceCounterValueBy(long value) {
        var negativeValue = -1L * value;
        counter.add(negativeValue);
        LOGGER.info("Counter was reduced by " + value);
    }

    public void cleanCounterValue() {
        counter.clean();
        LOGGER.info("Counter was cleaned");
    }
}
