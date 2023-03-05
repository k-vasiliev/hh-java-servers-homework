package config;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import resource.ApplicationResource;
import resource.CounterResource;

import java.util.Set;

@ApplicationPath("/")
public class ApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    return Set.of(
        ApplicationResource.class,
        CounterResource.class
    );
  }
}
