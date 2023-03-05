package config;

import filter.AuthFilter;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import resource.CounterResource;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class ApplicationConfig extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> res = new HashSet<>();
        res.add(CounterResource.class);
        res.add(AuthFilter.class);
        return res;
    }
}
