package counter.resource;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class CounterResourceConfig extends ResourceConfig {

  public CounterResourceConfig() {
    register(CounterResource.class);
    register(StatusResource.class);
  }

}
