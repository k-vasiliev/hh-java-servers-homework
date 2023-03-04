import jakarta.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class JerseyApplication {
  private static final int PORT = 8081;
  private static final String URI = "http://localhost/";

  private static Server createServer() {
    URI baseUri = UriBuilder.fromUri(URI).port(PORT).build();
    ResourceConfig config = new ResourceConfig(new CounterApp().getClasses());
    return JettyHttpContainerFactory.createServer(baseUri, config);
  }

  public static void main(String[] args) throws Exception {
    Server server = createServer();
    server.start();
    server.join();
  }
}
