package resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import service.CounterService;

@Path("/counter")
public class CounterResource {
  private final CounterService counterService;

  public CounterResource() {
    counterService = new CounterService() ;
  }

  @GET
  public Response getCounter() {
    return Response.ok(counterService.getCounterValue()).build();
  }
}
