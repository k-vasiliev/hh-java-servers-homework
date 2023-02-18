package dto;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CounterDto {
  private String date;
  private Long value;

  public CounterDto() {
  }

  public CounterDto(Long value) {
    date = ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
        .format(DateTimeFormatter.ISO_INSTANT);
    this.value = value;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }
}
