package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import util.Counter;
import util.CounterGetResponse;

import java.util.Calendar;
@Path("counter")
public class CounterResource {
    @GET
    @Produces("application/json")
    public Response getCounter() {
        CounterGetResponse counterResponse =  new CounterGetResponse(
                Calendar.getInstance().getTime(), Counter.getInstance().getCount());
        try {
            return Response.ok(new ObjectMapper().writeValueAsString(counterResponse)).build();
        } catch (Exception exception) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    public Response incrementCounter() {
        Counter.getInstance().increase();
        return Response.ok().build();
    }

    @DELETE
    public Response decreaseCounterByValue(@Context HttpHeaders context) {
        String headerStringValue = context.getHeaderString("Subtraction-Value");
        try {
            int headerValue = Integer.parseInt(headerStringValue);
            Counter.getInstance().decreaseBy(headerValue);
            return Response.ok().build();
        } catch (NumberFormatException exception) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Path("clear")
    @POST
    public Response clearCounter(@Context HttpHeaders context) {
        Cookie cookie = context.getCookies().get("hh-auth");
        if (cookie != null && cookie.getValue().length() > 10) {
            Counter.getInstance().clear();
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
