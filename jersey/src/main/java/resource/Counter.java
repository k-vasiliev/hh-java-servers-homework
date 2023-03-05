package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dto.CounterDto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import storage.StorageCounter;

import java.util.Optional;

@Path("/counter")
public class Counter {

  private final StorageCounter storageCounter = StorageCounter.getInstance();
  private final int REQUIREDLENGTH = 10;

  private final ObjectMapper mapper = new ObjectMapper()
      .registerModule(new JavaTimeModule())
      .setDateFormat(new StdDateFormat().withColonInTimeZone(true))
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

  @GET
  public Response getCounter() throws JsonProcessingException {
    return Response
        .ok(mapper.writeValueAsString(new CounterDto(storageCounter.getCounter())))
        .status(200).build();
  }

  @POST
  public Response postCounter(){
    return Response.ok(storageCounter.increment()).status(201).build();
  }

  @POST
  @Path("/clear")
  public Response clearCounter(@CookieParam("hh-auth") String auth){
    boolean isAuth = Optional.ofNullable(auth)
        .map(s -> s.length() > REQUIREDLENGTH)
        .orElse(false);
    if (isAuth){
      storageCounter.reset();
      return Response.status(204).build();
    } else {
      return Response.status(401).build();
    }
  }

  @DELETE
  public Response subtract(@HeaderParam("Subtraction-Value") long value) {
    return Response.ok(storageCounter.decrement(value)).build();
  }
}
