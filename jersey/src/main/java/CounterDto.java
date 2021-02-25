import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CounterDto {

    private Integer value;

    private String date;

    private String error;


    public CounterDto(Integer value, String date) {
        this.value = value;
        this.date = date;
    }

    public CounterDto(String error) {
        this.error = error;
    }

    public Integer getValue() {
        return value;
    }

    public String getDate() {
        return date;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
