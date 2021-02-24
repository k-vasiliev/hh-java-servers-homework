import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

public class JerseyApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(BaseServlet.class);
        return classes;
    }
}
