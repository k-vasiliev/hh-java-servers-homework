package counter.resource;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/status")
public class StatusResource {

  private final Logger log;

  public StatusResource() {
    log = LoggerFactory.getLogger(StatusResource.class);
  }

  @GET
  public Response getStatus(@Context HttpServletRequest request) {
    log.info("{} {}", request.getMethod(), request.getRequestURI());
    return Response.ok().build();
  }

}
