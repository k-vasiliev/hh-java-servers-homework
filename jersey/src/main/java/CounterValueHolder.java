public class CounterValueHolder {
  private final Integer value;
  private final String date;

  CounterValueHolder(int value, String date) {
    this.value = value;
    this.date = date;
  }

  public Integer getValue() {
    return value;
  }

  public String getDate() {
    return date;
  }
}
