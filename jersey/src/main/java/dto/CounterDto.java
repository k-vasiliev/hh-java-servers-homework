package dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class CounterDto {
    @JsonProperty("value")
    private final long value;
    @JsonProperty("date")
    private final Date date;

    @JsonCreator
    public CounterDto(long value) {
        this.value = value;
        this.date = new Date();
    }
}
