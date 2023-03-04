import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import resources.CounterResource;

import java.util.Set;

@ApplicationPath("/")
public class CounterApp  extends Application {
  private final Set<Class<?>> resources;

  public CounterApp() {
    resources = Set.of(CounterResource.class);
  }

  @Override
  public Set<Class<?>> getClasses() {
    return resources;
  }
}
