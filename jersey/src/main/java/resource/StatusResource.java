package resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("status")
public class StatusResource {
    @GET
    public Response getStatus() {
        return Response.ok().build();
    }
}
