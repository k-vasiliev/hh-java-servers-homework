package dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

public class CounterDTO {

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final LocalDateTime date;
    private final int value;

    public CounterDTO(LocalDateTime creationDate, int value) {
        this.date = creationDate;
        this.value = value;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public int getValue() {
        return value;
    }
}
