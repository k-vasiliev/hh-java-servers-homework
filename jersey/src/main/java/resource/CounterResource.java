package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.CounterDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.CounterService;

@Path("/counter")
public class CounterResource {

  private static final CounterService counterService = CounterService.getInstance();
  private static final ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .setDateFormat(new StdDateFormat().withColonInTimeZone(true));

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCounter() throws JsonProcessingException {
    CounterDto counterDto = new CounterDto(counterService.getCounter());
    String response = mapper.writeValueAsString(counterDto);

    return Response.ok(response).build();
  }

  @POST
  public Response incrementCounter() {
    counterService.incrementCounter();

    return Response.ok().build();
  }

  @DELETE
  public Response subtractCounter(@Context HttpServletRequest request) {
    Long subtractionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
    counterService.subtractCounter(subtractionValue);

    return Response.ok().build();
  }

  @POST
  @Path("/clear")
  public Response resetCounter(@CookieParam("hh-auth") String cookieValue) {
    if (cookieValue == null || cookieValue.length() <= 10) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    counterService.resetCounter();

    return Response.ok().build();
  }
}
