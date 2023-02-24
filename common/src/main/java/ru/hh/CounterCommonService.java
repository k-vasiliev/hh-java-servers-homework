package ru.hh;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class CounterCommonService {
    private final static Logger LOGGER = getLogger(CounterCommonService.class);

    private final static Counter COUNTER = new Counter();

    public CounterDto getCounterValue() {
        var value = COUNTER.getValue();
        LOGGER.info("Get counter's value: " + value);
        return new CounterDto(value);
    }

    public void upCounterValue() {
        COUNTER.add(1L);
        LOGGER.info("UpCounterValue was called");
    }

    public void reduceCounterValueBy(long value) {
        var negativeValue = -1L * value;
//        counter.add(negativeValue);
        COUNTER.add(-1L * value);
        LOGGER.info("Counter was reduced by " + value);
    }

    public void cleanCounterValue() {
        COUNTER.clean();
        LOGGER.info("Counter was cleaned");
    }
}
