import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class ServletConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> s = new HashSet<>();
        s.add(CounterServlet.class);
        return s;
    }
}