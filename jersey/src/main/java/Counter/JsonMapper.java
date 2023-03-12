package Counter;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class JsonMapper {

    public static String getJson() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String json = "Json return fault";
        try {
            CounterDate counterDate = new CounterDate(Counter.getValue(), ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT));
            json = objectMapper.writeValueAsString(counterDate);
        } catch (JsonProcessingException ex){
            System.out.println(ex.getMessage());
        }
        return json;
    }
}
