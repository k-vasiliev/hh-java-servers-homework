package resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.jetty.util.StringUtil;
import service.CounterService;

@Path("/counter")
public class CounterResource {
  private final CounterService counterService;

  public CounterResource() {
    counterService = CounterService.getInstance();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCounter() {
    return Response.ok(counterService.getCounterValue()).build();
  }

  @POST
  public Response incrementCounter() {
    counterService.incrementCounter();
    return Response.ok().build();
  }

  @POST
  @Path("/clear")
  public Response clearCounter(@CookieParam(value = "hh-auth") String cookie) {
    if (StringUtil.isNotBlank(cookie) && cookie.length() > 10) {
      counterService.clearCounter();
      return Response.ok().build();
    }
    return Response.status(Response.Status.FORBIDDEN).build();
  }

  @DELETE
  public Response decrementCounter(@HeaderParam(value = "Subtraction-Value") String deltaValue) {
    if (StringUtil.isNotBlank(deltaValue)) {
      try {
        counterService.decrementCounter(Long.parseLong(deltaValue));
        return Response.ok().build();
      } catch (NumberFormatException err) {
        return Response
            .status(Response.Status.BAD_REQUEST)
            .entity("Bad number format.")
            .build();
      }
    }
    return Response
        .status(Response.Status.BAD_REQUEST)
        .entity("Number is absent.")
        .build();
  }
}
