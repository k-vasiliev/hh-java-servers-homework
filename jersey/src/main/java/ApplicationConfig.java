
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import Counter.CounterController;
import Counter.StatusController;
import jakarta.ws.rs.core.Application;

public class ApplicationConfig extends Application {
	public Set<Class<?>> getClasses() {
		return new HashSet<Class<?>>(Arrays.asList(CounterController.class, StatusController.class));
	}
}
// 