package resources;

import entities.Counter;
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

  private boolean isCookieValid(String value) {
    return Objects.nonNull(value) && value.length() > 10;
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
  public Counter getCounterAndDateValue() {
    return new Counter(Instant.now(), counter.get());
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
    if (isCookieValid(value)) {
      counter.getAndSet(0);
      return Response.ok()
        .status(Response.Status.OK)
        .build();
    }
    return Response.ok()
      .status(Response.Status.BAD_REQUEST)
      .build();
  }

}
