package config;

import javax.ws.rs.core.Application;
import java.util.Set;
import java.util.HashSet;

import counter.ServletCounter;

public class CounterConfig extends Application {

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> classes = new HashSet<>();
    classes.add(ServletCounter.class);
    return classes;
  }
}
