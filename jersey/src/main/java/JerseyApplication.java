import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class JerseyApplication  {

  private static Server createServer() {
    ResourceConfig config = new ResourceConfig(
      CounterResource.class,
      StatusResource.class,
      ObjectMapperCustom.class);

    return JettyHttpContainerFactory.createServer(URI.create("http://localhost:8081/"), config);
  }

  public static void main(String[] args) throws Exception {
    Server server = createServer();
    server.start();
    server.join();
  }
}
