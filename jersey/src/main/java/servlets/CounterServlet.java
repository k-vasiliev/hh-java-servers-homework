package servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import counter.Counter;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.time.Clock;
import java.time.LocalDateTime;

@Path("/counter")
public class CounterServlet {

    public static final String SUBTRUCTION_VALUE_HEADER = "Subtraction-Value";

    public static final String AUTH_COOKIE_NAME = "hh-auth";

    private Counter counter;

    private Clock clock;

    public CounterServlet(@Context ServletContext context) {
        counter = (Counter) context.getAttribute("counter");
        clock = (Clock) context.getAttribute("clock");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounterValue() {
        ObjectNode responseEntity = new ObjectMapper().createObjectNode()
            .put("date", LocalDateTime.now(clock).toString())
            .put("value", counter.getValue());
        return Response.ok(responseEntity).build();
    }

    @POST
    public Response incrementCounter() {
        counter.increment();
        return Response.ok().build();
    }

    @DELETE
    public Response subtractFromCounter(@HeaderParam(SUBTRUCTION_VALUE_HEADER) Integer subtractionValue) {
        if (subtractionValue == null) {
            return Response.status(Status.BAD_REQUEST).entity("Missing Subtraction-Value Header or something wrong!").build();
        }
        counter.subtractValue(subtractionValue);
        return Response.ok().build();
    }

    @POST
    @Path("/clear")
    public Response clearCounter(@CookieParam(AUTH_COOKIE_NAME) String auth) {
        if (auth != null && auth.length() > 10) {
            counter.setValue(0);
            return Response.ok().build();
        }
        return Response.status(Status.UNAUTHORIZED).build();
    }
}
