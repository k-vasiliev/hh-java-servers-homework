import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class MyApplication extends Application {
	
	public Set<Class<?>> getClasses() {
		
		Set<Class<?>> classes = new HashSet<>();
		// register root resource
		classes.add(resource.CountServlet.class);
		return classes;
	}
}
