import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class getData {
    private final String date;
    private final Integer value;
    getData(int value) {
        this.date = ZonedDateTime.now(ZoneId.of("Europe/Moscow"))
                .format(DateTimeFormatter.ISO_INSTANT);
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public String  getDate() {
        return date;
    }
}
