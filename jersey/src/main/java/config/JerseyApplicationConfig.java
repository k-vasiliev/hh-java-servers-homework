package config;

import jakarta.ws.rs.core.Application;
import resource.CounterResource;
import resource.StatusResource;

import java.util.HashSet;
import java.util.Set;

public class JerseyApplicationConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> resources = new HashSet<>();
    resources.add(StatusResource.class);
    resources.add(CounterResource.class);

    return resources;
  }
}
