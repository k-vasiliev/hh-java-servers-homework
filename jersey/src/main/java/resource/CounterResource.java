package resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import model.Counter;

@Path("/counter")
public class CounterResource {
  private final static Counter counter = new Counter();

  @GET
  public Response getCounter() {
    return Response.ok(counter.get()).build();
  }
}
