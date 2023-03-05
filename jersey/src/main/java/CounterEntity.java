import java.time.Instant;

public class CounterEntity {
  private final Integer value;
  private final Instant date;

  CounterEntity(int value, Instant date) {
    this.value = value;
    this.date = date;
  }

  public Integer getValue() {
    return value;
  }

  public Instant getDate() {
    return date;
  }
}
