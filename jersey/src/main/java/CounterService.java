import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import jakarta.inject.Singleton;
import org.eclipse.jetty.server.Response;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;

@Singleton
public class CounterService {
  private final AtomicInteger counter;

  public CounterService() {
    counter = new AtomicInteger(0);
  }

  public String getCounter() {
    Counter counterOb = new Counter();
    counterOb.setDate(Instant.now().toString());
    counterOb.setValue(counter.get());
    return mapObjectToJson(counterOb);
  }

  public void incrementCounter() {
    counter.getAndIncrement();
  }

  public int decrementCounter(String subtraction) {
    if (subtraction != null && subtraction.chars().allMatch(Character::isDigit)) {
      var value = Integer.parseInt(subtraction);
      counter.getAndAdd(-1 * value);
      return Response.SC_OK;
    }
    return Response.SC_BAD_REQUEST;
  }

  public int clear(String auth) {
    if (auth != null && auth.length() > 10) {
      counter.getAndSet(0);
      return Response.SC_OK;
    } else {
      return Response.SC_UNAUTHORIZED;
    }
  }

  private String mapObjectToJson(Object obj) {
    ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    String json;
    try {
      json = ow.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    return json;
  }

}
