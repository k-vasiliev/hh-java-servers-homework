package counter.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import counter.CounterDto;
import counter.CounterMapper;
import counter.InMemoryStorage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Arrays;
import java.util.Optional;

@Path("/counter")
public class CounterResource {

  private final InMemoryStorage storage = InMemoryStorage.getInstance();

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getCounter() throws JsonProcessingException {
    return CounterMapper.counterDtoToJson(new CounterDto(storage.getCounter()));
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public long increaseCounter() {
    return storage.increaseCounter();
  }

  @POST
  @Path("/clear")
  @Produces(MediaType.TEXT_PLAIN)
  public Response clearCounter(@Context HttpServletRequest request) {
    Optional<Cookie[]> authCookie =  Optional.ofNullable(request.getCookies())
        .filter(s -> Arrays.stream(s).filter(cookie -> cookie.getName().equals("hh-auth"))
            .anyMatch(cookie -> cookie.getValue().length() > 10));
    return Response
        .status((authCookie.isPresent()) ? Response.Status.OK : Response.Status.BAD_REQUEST)
        .entity((authCookie.isPresent()) ? storage.clearCounter() : Response.Status.BAD_REQUEST.getReasonPhrase())
        .build();
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  public long reductionCounter(@Context HttpServletRequest request) {
    long reductionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
    return storage.reductionCounter(reductionValue);
  }

}
