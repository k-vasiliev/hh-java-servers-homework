package Resources;

import Counter.Counter;
import Counter.JsonMapper;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

@Path("/counter")
public class CounterResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() {
        return Response.ok(JsonMapper.getJson()).build();
    }
    @POST
    public Response incrementCounter() {
        Counter.incrementValue();
        return Response.ok(Counter.getValue()).build();
    }
    @DELETE
    public Response subtractCounter(@HeaderParam("Subtraction-Value") Long subtractionValue) {
        Counter.subtractValue(subtractionValue);
        return Response.ok(Counter.getValue()).build();
    }
    @POST
    @Path("/clear")
    public Response clearCounter(@CookieParam("hh-auth") Cookie cookie) {
        if (cookie != null && cookie.getValue().length() > 10){
            Counter.clearValue();
            return Response.ok(Counter.getValue()).build();
        }
        return Response.status(401).build();
    }
}
