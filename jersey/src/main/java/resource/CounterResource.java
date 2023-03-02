package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.CounterDto;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Optional;
import storage.CounterStorage;

@Path("/counter")
public class CounterResource {

  private final ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);


  private final CounterStorage counterStorage = CounterStorage.getInstance();
  private final static String SUBTRACTION_VALUE = "Subtraction-Value";
  private final String HH_AUTH = "hh-auth";


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getCounter() throws JsonProcessingException {
    return Response
        .ok(mapper.writeValueAsString(new CounterDto(counterStorage.getCounter())))
        .status(200)
        .build();
  }

  @POST
  @Produces(MediaType.TEXT_PLAIN)
  public Response increment() {
    return Response
        .ok(counterStorage.increment())
        .status(200).build();
  }

  @POST
  @Path(("/clear"))
  @Produces(MediaType.TEXT_PLAIN)
  public Response clear(@CookieParam(HH_AUTH) String auth) {
    boolean isAuth = Optional.ofNullable(auth).map(a -> a.length() > 10).orElse(false);
    if (isAuth) {
      counterStorage.reset();
      return Response.ok().status(200).build();
    }
    return Response.status(401).build();
  }

  @DELETE
  @Produces(MediaType.TEXT_PLAIN)
  public Response subtract(@HeaderParam(SUBTRACTION_VALUE) long subtractionValue) {
    return Response
        .ok(counterStorage.subtract(subtractionValue))
        .status(200).build();
  }

}
