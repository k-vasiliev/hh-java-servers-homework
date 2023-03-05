package resources;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import dao.Counter;
import dto.CounterDto;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jetty.http.HttpStatus;

@Path("/counter")
public final class CounterResource {
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setDateFormat(new StdDateFormat().withColonInTimeZone(true));
    private static final Counter counter = Counter.getInstance();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCounter() throws JsonProcessingException {
        return objectMapper.writeValueAsString(new CounterDto(counter.get().longValue()));
    }

    @POST
    public Response increaseCounter() {
        counter.increment();
        return Response
                .status(HttpStatus.OK_200)
                .build();
    }

    @DELETE
    public Response reductionCounter(@HeaderParam("Subtraction-Value") String decrement) {
        if (decrement == null) {
            return Response
                    .status(HttpStatus.BAD_REQUEST_400)
                    .build();
        }

        counter.decrement(Long.parseLong(decrement));
        return Response
                .status(HttpStatus.OK_200)
                .build();
    }

    @Path("/clear")
    @POST()
    public Response clearCounter(@CookieParam("hh-auth") String cookie) {
        if (cookie == null || cookie.length() < 10) {
            return Response
                    .status(HttpStatus.BAD_REQUEST_400)
                    .build();
        }

        counter.clear();
        return Response
                .status(HttpStatus.OK_200)
                .build();
    }
}
