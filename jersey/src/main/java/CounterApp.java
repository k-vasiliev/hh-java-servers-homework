import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import resources.CounterResource;

import java.util.Set;

@ApplicationPath("/")
public class CounterApp  extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    return Set.of(CounterResource.class);
  }

}
