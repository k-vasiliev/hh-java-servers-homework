package dto;

import java.time.ZonedDateTime;

public class CounterDto {

  private ZonedDateTime date;
  private Long value;

  public CounterDto() {
  }

  public CounterDto(Long value) {
    this.value = value;
    this.date = ZonedDateTime.now();
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public void setDate(ZonedDateTime date) {
    this.date = date;
  }

  public Long getValue() {
    return value;
  }

  public void setValue(Long value) {
    this.value = value;
  }
}
