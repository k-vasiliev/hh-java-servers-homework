package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.CounterDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import service.CounterService;


@Path("/counter")
public class CounterResource {
    private static final CounterService counterService = CounterService.getInstance();
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setDateFormat(new StdDateFormat())
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCounter() throws JsonProcessingException {
        return mapper.writeValueAsString(new CounterDto(counterService.getCounter()));
    }

    @POST
    public void increaseCounter() {
        counterService.increment();
    }

    @DELETE
    public void reductionCounter(@Context HttpServletRequest request) {
        long reductionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
        counterService.subsctract(reductionValue);
    }
}
