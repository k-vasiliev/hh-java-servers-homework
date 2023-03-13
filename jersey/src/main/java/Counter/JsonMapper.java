package Counter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonMapper {
	private static ObjectMapper objectMapper;

	public static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		}
		return objectMapper;
	}

}
