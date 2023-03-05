import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/")
public class StatusOkController {
  @GET
  @Path(value = "/status")
  public Response getStatus() {
    return Response.ok().build();
  }
}
