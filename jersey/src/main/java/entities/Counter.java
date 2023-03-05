package entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class Counter {

  @JsonFormat(shape = JsonFormat.Shape.STRING)
  private Instant date;
  private int value;

  public Counter(Instant date, int value) {
    this.date = date;
    this.value = value;
  }

  public Instant getDate() {
    return date;
  }

  public void setDate(Instant date) {
    this.date = date;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

}
