package mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import ru.hh.CounterDto;

public class CounterJsonMapper {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setDateFormat(new StdDateFormat().withColonInTimeZone(true));

    public static String toJson(CounterDto dto) throws JsonProcessingException {
        return mapper.writeValueAsString(dto);
    }
}
