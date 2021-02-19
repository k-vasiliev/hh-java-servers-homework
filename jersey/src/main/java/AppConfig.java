import service.ServletContainer;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class AppConfig extends Application {
    @Override
    public Set<Object> getSingletons()
    {
        Set<Object> s = new HashSet<>();
        s.add(ServletContainer.getInstance());
        return s;
    }
}
