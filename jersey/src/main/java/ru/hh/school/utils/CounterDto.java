package ru.hh.school.utils;

import java.time.Instant;

public class CounterDto {
  private final Integer value;
  private final Instant date;

  CounterDto(int value, Instant date) {
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

