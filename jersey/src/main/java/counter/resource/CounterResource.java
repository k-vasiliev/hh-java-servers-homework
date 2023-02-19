package counter.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import counter.CounterDto;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Optional;

@Path("/counter")
public class CounterResource {

  private final InMemoryStorage storage;

  private final Logger log;

  public CounterResource() {
    storage = InMemoryStorage.getInstance();
    log = LoggerFactory.getLogger(CounterResource.class);
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public String getCounter(@Context HttpServletRequest request) throws JsonProcessingException {
    logUrlInfo(request);
    ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
    mapper.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
    return mapper.writeValueAsString(new CounterDto(storage.getCounter()));
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public long increaseCounter(@Context HttpServletRequest request) {
    logUrlInfo(request);
    return storage.increaseCounter();
  }

  @POST
  @Path("/clear")
  @Produces(MediaType.TEXT_PLAIN)
  public Response clearCounter(@Context HttpServletRequest request) {
    logUrlInfo(request);
    Optional<Cookie> authCookie =  Optional.ofNullable(request.getCookies())
        .stream()
        .flatMap(Arrays::stream)
        .filter(cookie -> cookie.getName().equals("hh-auth"))
        .filter(cookie -> cookie.getValue().length() > 10)
        .findFirst();
    return Response
        .status((authCookie.isPresent()) ? Response.Status.OK : Response.Status.BAD_REQUEST)
        .entity((authCookie.isPresent()) ? storage.clearCounter() : Response.Status.BAD_REQUEST.getReasonPhrase())
        .build();
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  public long reductionCounter(@Context HttpServletRequest request) {
    logUrlInfo(request);
    long reductionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
    return storage.reductionCounter(reductionValue);
  }

  private void logUrlInfo(HttpServletRequest request) {
    log.info("{} {}", request.getMethod(), request.getRequestURI());
  }

}
