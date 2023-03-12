package config;

import controller.CounterController;
import controller.StatusController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

import java.util.Set;

@ApplicationPath("/")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(CounterController.class,
                StatusController.class);
    }
}
