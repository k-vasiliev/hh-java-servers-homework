import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Path("counter")
public class Resource {

  @GET
  @Produces("application/json")
  public Response get() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode respBody = mapper.createObjectNode();

    ZonedDateTime now = ZonedDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
    respBody.put("date", formatter.format(now));
    respBody.put("value", JerseyApplication.counter.get());

    String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(respBody);
    return Response.ok(json).build();
  }

  @POST
  public Response post() {
    JerseyApplication.counter.incrementAndGet();
    return Response.noContent().build();
  }

  @DELETE
  public Response delete(@HeaderParam("Subtraction-Value") String sValue) {
    try {
      int value = Integer.parseInt(sValue);
      JerseyApplication.counter.addAndGet(-value);
      return Response.noContent().build();
    } catch (NumberFormatException e) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }

  @POST
  @Path(value = "/clear")
  public Response clear(@CookieParam("hh-auth") String auth) {
    if (auth != null && auth.length() > 10) {
      JerseyApplication.counter.set(0);
      return Response.noContent().build();
    } else {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }
}
