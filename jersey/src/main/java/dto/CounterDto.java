package dto;

import java.time.OffsetDateTime;

public class CounterDto {

    private final OffsetDateTime date;
    private final long value;

    public CounterDto(long value) {
        date = OffsetDateTime.now();
        this.value = value;
    }

    public OffsetDateTime getDate() {
        return date;
    }

    public long getValue() {
        return value;
    }
}
