package mapper;

import controller.Counter;
import dto.CounterDto;

import java.time.ZonedDateTime;

public enum CounterMapper {
    INSTANCE;

    public CounterDto map(Counter counter) {
        return new CounterDto(ZonedDateTime.now(), counter.getCounter());
    }
}
