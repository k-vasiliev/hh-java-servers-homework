import java.time.Instant;

public class CounterValueHolder {
  private final Integer value;
  private final Instant date;

  CounterValueHolder(int value, Instant date) {
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
