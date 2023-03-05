package service;

import dto.CounterDto;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CounterMapper {

    public static CounterDto map(Long counter){
        String date = ZonedDateTime.now( ZoneOffset.UTC ).format( DateTimeFormatter.ISO_INSTANT );
        return new CounterDto(date, counter);
    }
}
