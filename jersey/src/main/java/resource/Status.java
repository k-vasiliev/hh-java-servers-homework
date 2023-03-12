package resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/status")
public class Status {

  @GET
  public Response status(){
    return Response.ok("OK").build();
  }
}
