import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class CounterConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classSet = new HashSet<>();
        classSet.add(CounterResource.class);
        return classSet;
    }
}
