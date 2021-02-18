import java.time.Clock;
import java.time.LocalDateTime;

import javax.servlet.ServletContext;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Path("/counter")
public class CounterServletContainer {

    private Counter counter;

    private Clock clock;

    public CounterServletContainer(@Context ServletContext context) {
        counter = (Counter) context.getAttribute("counter");
        clock = (Clock) context.getAttribute("clock");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounterValue() {
        ObjectNode responseEntity = new ObjectMapper().createObjectNode()
            .put("date", LocalDateTime.now(clock).toString())
            .put("value", counter.get());
        return Response.ok(responseEntity).build();
    }

    @POST
    public Response incrementCounter() {
        counter.increment();
        return Response.ok().build();
    }

    @DELETE
    public Response subtractFromCounter(@HeaderParam("Subtraction-Value") Integer subtractionValue) {
        if (subtractionValue == null)
            return Response.status(Status.BAD_REQUEST).entity("Bad Subtraction Value\n").build();

        counter.subtract(subtractionValue);
        return Response.ok().build();
    }

    @POST
    @Path("/clear")
    public Response clearCounter(@CookieParam("hh-auth") String auth) {
        if (auth != null && auth.length() > 10) {
            counter.set(0);
            return Response.ok().build();
        }
        return Response.status(Status.UNAUTHORIZED).build();
    }
}
