import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import resource.CounterResource;

import java.net.URI;

@ApplicationPath("/")
public class JerseyApplication extends Application {

  private static Server createServer() {
    URI baseUri = UriBuilder.fromUri("http://localhost/").port(8081).build();
    ResourceConfig config = new ResourceConfig(CounterResource.class);
    return JettyHttpContainerFactory.createServer(baseUri, config);
  }

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = createServer();
    server.start();
    server.join();
  }
}
