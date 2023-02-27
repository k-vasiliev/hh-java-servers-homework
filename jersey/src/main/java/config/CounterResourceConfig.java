package config;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import resource.CounterClearResource;
import resource.CounterResource;
import resource.StatusResource;

@ApplicationPath("/")
public class CounterResourceConfig extends ResourceConfig {
    public CounterResourceConfig() {
        register(CounterResource.class);
        register(StatusResource.class);
        register(CounterClearResource.class);
    }
}
