import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CounterDto {
    private Counter counter;

    public CounterDto(Counter counter) {
        this.counter = counter;
    }

    public String getDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        return df.format(new Date());
    }
    public String getValue() {
        return counter.get() + "";
    }
}
