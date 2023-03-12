package dto;

import java.time.ZonedDateTime;

public class CounterDto {

  private ZonedDateTime date;
  private Long value;

  public CounterDto() {
  }

  public CounterDto(Long counter) {
    date = ZonedDateTime.now();
    this.value = counter;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public Long getValue() {
    return value;
  }
}
