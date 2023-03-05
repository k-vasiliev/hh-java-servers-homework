package dto;

import java.time.ZonedDateTime;

public class CounterDto {

  private ZonedDateTime date;
  private Long counter;

  public CounterDto() {
  }

  public CounterDto(Long counter) {
    date = ZonedDateTime.now();
    this.counter = counter;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public Long getCounter() {
    return counter;
  }
}
