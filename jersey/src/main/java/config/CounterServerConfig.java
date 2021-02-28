package config;

import servlets.CounterServlet;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class CounterServerConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(CounterServlet.class);
        return classes;
    }
}
