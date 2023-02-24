package ru.hh;

import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class CounterService {
    private final static Logger LOGGER = getLogger(CounterService.class);

    private final CounterDao counterDao = new CounterDao();

    public CounterDto getCounterValue() {
        var value = counterDao.getValue();
        LOGGER.info("Get counter's value: " + value);
        return new CounterDto(value);
    }

    public void upCounterValue() {
        counterDao.add(1L);
        LOGGER.info("UpCounterValue was called");
    }

    public void reduceCounterValueBy(long value) {
        var negativeValue = -1L * value;
        counterDao.add(negativeValue);
        LOGGER.info("Counter was reduced by " + value);
    }

    public void cleanCounterValue() {
        counterDao.clean();
        LOGGER.info("Counter was cleaned");
    }
}
