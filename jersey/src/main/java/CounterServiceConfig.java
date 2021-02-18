import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class CounterServiceConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CounterServletContainer.class);
        return classes;
    }
}
