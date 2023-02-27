import config.CounterResourceConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;


public class JerseyApplication {
  private final static int PORT = 8081;

  public static void main(String[] args) throws Exception {
    Server server = createServer();
    server.start();
    server.join();
  }

  private static Server createServer() {
    Server server = new Server(JerseyApplication.PORT);
    ServletContainer servletContainer = new ServletContainer(new CounterResourceConfig());
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    ServletContextHandler context = new ServletContextHandler(server, "/");
    context.addServlet(servletHolder, "/*");
    return server;
  }
}
