import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class getData {
    private final ZonedDateTime date;
    private final Integer value;
    getData(int value) {
        this.date = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public ZonedDateTime  getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "getData{" +
                "date=" + date.format(DateTimeFormatter.ISO_INSTANT) +
                ", value=" + value +
                '}';
    }
}
