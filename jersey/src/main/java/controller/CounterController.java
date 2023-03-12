package controller;

import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mapper.CounterMapper;

@Path("/counter")
public class CounterController {
    private static final String SUBTRACTION_HEADER_NAME = "Subtraction-Value";
    private static final String COOKIE_NAME = "hh-auth";
    private final Counter counter = Counter.INSTANCE;
    private final CounterMapper counterMapper = CounterMapper.INSTANCE;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() {
        return Response.ok(counterMapper.map(counter)).build();
    }

    @POST
    public Response incrementCounter() {
        return Response.ok(counter.increment()).build();
    }

    @DELETE
    public Response reduceCounter(@HeaderParam(value = SUBTRACTION_HEADER_NAME) Long value) {
        return Response.ok(counter.add(-value)).build();
    }

    @POST
    @Path(value = "/clear")
    public Response clearCounter(@CookieParam(COOKIE_NAME) String cookieValue) {
        if (cookieValue != null && cookieValue.length() > 10) {
            return Response.ok(counter.reset()).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }
}
