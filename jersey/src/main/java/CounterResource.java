import jakarta.inject.Inject;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/")
public class CounterResource {

  @Inject
  private CounterService service;

  @GET
  @Path("status")
  @Produces(MediaType.TEXT_PLAIN)
  public int getStatus() {
    return org.eclipse.jetty.server.Response.SC_OK;
  }

  @GET
  @Path("counter")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCounter() {
    return Response.ok(service.getCounter()).build();
  }

  @POST
  @Path("counter")
  @Produces(MediaType.TEXT_PLAIN)
  public Response increaseCounter() {
    service.incrementCounter();
    return Response.ok().build();
  }

  @DELETE
  @Path("counter")
  public Response decreaseCounter(@HeaderParam("Subtraction-Value") String subtraction) {
    return Response.status(service.decrementCounter(subtraction)).build();
  }

  @POST
  @Path("counter/clear")
  public Response clear(@CookieParam("hh-auth") String auth) {
    return Response.status(service.clear(auth)).build();
  }

}
