package counter;

import java.time.ZonedDateTime;

public class CounterDto {

  private ZonedDateTime date;

  private long value;

  public CounterDto(long value) {
    date = ZonedDateTime.now();
    this.value = value;
  }

  public ZonedDateTime getDate() {
    return date;
  }

  public long getValue() {
    return value;
  }

}
