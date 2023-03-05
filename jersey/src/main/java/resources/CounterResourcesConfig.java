package resources;


import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/")
public class CounterResourcesConfig extends ResourceConfig {

    public CounterResourcesConfig() {
        register(CounterResource.class);
        register(StatusResource.class);
    }
}
