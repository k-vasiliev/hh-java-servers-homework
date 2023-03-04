package resources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.Instant;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class CounterResource {
  private static final AtomicInteger counter = new AtomicInteger();
  private static final ObjectMapper mapper = new ObjectMapper();

  private boolean cookieHaveValid(String value) {
    return Objects.nonNull(value) && value.length() >= 10;
  }

  private ObjectNode createJson() {
    return mapper.createObjectNode()
      .put("value", counter.get())
      .put("date", Instant.now().toString());
  }

  @GET
  @Path("/status")
  public Response getStatus() {
    return Response.ok()
      .status(Response.Status.OK)
      .build();
  }

  @GET
  @Path("/counter")
  public Response getCounterAndDateValue() {
    return Response.ok().entity(createJson()).build();
  }

  @POST
  @Path("/counter")
  public void incrementCounter() {
    counter.incrementAndGet();
  }

  @DELETE
  @Path("/counter")
  public void decrementByHeaderValue(@Context HttpHeaders headers) {
    int headerValue = Integer
      .parseInt(headers.getHeaderString("Subtraction-Value"));

    for (int i = 0; i < headerValue; i++) {
      counter.decrementAndGet();
    }
  }

  @POST
  @Path("/counter/clear")
  public Response clearCounter(@CookieParam("hh-auth") String value) {
    if (cookieHaveValid(value)) {
      counter.getAndSet(0);
      return Response.ok().status(Response.Status.OK).build();
    }
    return Response.ok().status(400).build();
  }
}
