package dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.ZonedDateTime;

public final class CounterDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final ZonedDateTime date;
    private final long value;

    public CounterDto(ZonedDateTime date, long value) {
        this.date = date;
        this.value = value;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public long getValue() {
        return value;
    }
}
