import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("counter")
public class CounterResource {

    private final Counter counter = Counter.getCounterInstance();
    private final ObjectMapper objectMapper = new ObjectMapper()
            .setDateFormat(ISO8601DateFormat.getDateTimeInstance());

    @GET
    @Produces("application/json")
    public Response get() throws JsonProcessingException {
        JsonNode jsonNode = objectMapper.valueToTree(counter);
        ((ObjectNode) jsonNode).put("date", objectMapper.getDateFormat().format(new Date()));

        return Response.ok(objectMapper.writeValueAsString(jsonNode)).build();
    }

    @POST
    public Response post() {
        counter.incrementCounter();
        return Response.ok().build();
    }

    @DELETE
    public Response delete(@HeaderParam("Subtraction-Value") String header) {
        if (header == null) return Response
                .status(HttpServletResponse.SC_BAD_REQUEST)
                .build();
        counter.decrementCounter(Integer.parseInt(header));
        return Response.ok().build();
    }

    @POST
    @Path(value = "clear")
    public Response clear(@CookieParam("hh-auth") String cookie) {
        if (cookie != null && cookie.length() > 10) {
            counter.resetCounter();
            return Response.ok().build();
        }
        return Response
                .status(HttpServletResponse.SC_FORBIDDEN)
                .build();
    }
}
