import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;

import java.time.Instant;

@Path("/")
public class CounterResource {
  private static final int HH_AUTH_TOKEN_MIN_LENGTH = 11;

  @GET
  @Path(value = "/counter")
  @Produces("application/json")
  public Response getCounter() {
    CounterValueHolder valueHolder = new CounterValueHolder(
      CounterService.getCounter(), Instant.now());

    return Response.ok(valueHolder).build();
  }

  @POST
  @Path(value = "/counter")
  public Response incrementCounter() {
    return Response.ok(CounterService.incrementCounter()).build();
  }

  @DELETE
  @Path(value = "/counter")
  public Response decrementCounter(@HeaderParam("Subtraction-Value") Integer value) {
    if (value == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    CounterService.decrementCounter(value);
    return Response.noContent().build();
  }

  @POST
  @Path(value = "/counter/clear")
  public Response clearCounter(@CookieParam("hh-auth") String authToken) {
    if (authToken == null || authToken.length() < HH_AUTH_TOKEN_MIN_LENGTH) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }

    CounterService.clearCounter();
    return Response.ok(0).build();
  }
}
