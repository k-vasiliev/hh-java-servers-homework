import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import service.CounterService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Path("/counter")
public class ServletContainer {

    private final CounterService service;
    private final ObjectMapper mapper = new ObjectMapper();


    public ServletContainer() {
        this.service = CounterService.getInstance();
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public Response incrementByOne() {
        service.incrementByOne();
        return Response.ok("Counter incremented by 1").build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public Response decrementByN(@QueryParam("subtraction") Integer n) {
        if (n == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        service.decrementByN(n);
        return Response.ok("Successfully incremented").build();
    }

    @POST
    @Path(value = "/clear")
    @Produces(MediaType.TEXT_PLAIN)
    public Response reset(@CookieParam("hh-auth") String cookie) {
        if (cookie != null && cookie.length() > 10) {
            service.reset();
            return Response.ok("Successfully reset").build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() throws JsonProcessingException {
        ObjectNode node = mapper.createObjectNode();

        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        node.put("date", formatter.format(now));
        node.put("value", service.getCount());
        String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        return Response.ok(json).build();
    }
}
