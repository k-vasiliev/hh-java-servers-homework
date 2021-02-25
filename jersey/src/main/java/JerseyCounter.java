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
public class JerseyCounter {

  @GET
  @Produces("application/json")
  public Response get() throws JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    ObjectNode responseBuffer = mapper.createObjectNode();
    
    responseBuffer.put("date", DateTimeFormatter.ISO_DATE_TIME.format(ZonedDateTime.now()));
    responseBuffer.put("value", JerseyApplication.getCounter());

    String response = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseBuffer);
    return Response.ok(response).build();
  }

  @POST
  public Response post() {
    JerseyApplication.changeCounter(1);
    return Response.noContent().build();
  }

  @DELETE
  public Response delete(@HeaderParam("Subtraction-Value") String value) {
    try {
      JerseyApplication.changeCounter(-Integer.parseInt(value));
      return Response.noContent().build();
    } catch (NumberFormatException e) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }

  @POST
  @Path(value = "/clear")
  public Response clear(@CookieParam("hh-auth") String auth) {
    if (auth != null && auth.length() > 10) {
      JerseyApplication.setCounter(0);
      return Response.noContent().build();
    } else {
      return Response.status(Response.Status.UNAUTHORIZED).build();
    }
  }

}
