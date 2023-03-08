package util;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class CounterGetResponse {
    @SuppressWarnings("unused")
    public String getDate() {
        return date;
    }

    @SuppressWarnings("unused")
    public long getValue() {
        return value;
    }

    private String date;
    private long value;

    @SuppressWarnings("unused")
    public CounterGetResponse() {}
    public CounterGetResponse(Date date, long value) {
        this.value = value;
        this.date = DateTimeFormatter.ISO_INSTANT.format(date.toInstant());
    }
}
