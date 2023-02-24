import jakarta.ws.rs.core.Application;
import resource.CounterResource;
import resource.StatusResource;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ComponentsConfig  extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Stream.of(
                    StatusResource.class,
                    CounterResource.class
                ).collect(Collectors.toSet());
    }
}
