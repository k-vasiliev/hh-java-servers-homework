package ru.hh.school;

import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ru.hh.school.utils.CounterMapper;
import ru.hh.school.utils.CounterResource;
import ru.hh.school.utils.StatusResource;

import java.net.URI;

public class JerseyApplication {

  private static final String HOST = "http://localhost:8081";

  public static void main(String[] args) throws Exception {
    Server server = createServer();
    server.start();
    server.join();
  }

  private static Server createServer() {
    ResourceConfig config = new ResourceConfig(
        CounterResource.class,
        StatusResource.class,
        CounterMapper.class);
    return JettyHttpContainerFactory.createServer(URI.create(HOST), config);
  }
}