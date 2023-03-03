package resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.*;
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
  private static AtomicInteger counter = new AtomicInteger(0);
  private static final ObjectMapper mapper = new ObjectMapper();

//  private static ObjectNode getJson() {
//    return mapper.createObjectNode();
//  }

  public boolean cookieHasValid(String cookie) {
    return Objects.nonNull(cookie) &&
      cookie.length() >= 10;
  }

  @GET
  @Path("/counter")
  public Response getCounterValue() {
    ObjectNode json = mapper.createObjectNode();
//    json.putPOJO("date", Instant.now());
    json.put("value", counter.get())
        .put("date", Instant.now().toString());


//    ObjectNode json = getJson()
//      .putPOJO("date", Instant.now())
//      .put("value", counter.incrementAndGet());
    return Response.ok().entity(json).build();
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
    if (cookieHasValid(value)) {
      counter.getAndSet(0);
      return Response.ok().status(200).build();
    }
    return Response.ok().status(400).build();
  }

  @GET
  @Path("/status")
  public Response getStatus() {
    return Response.ok().entity("Status OK!").build();
  }
}
