import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {
  private final static int PORT = 8081;

  public static void main(String[] args) throws Exception {
    Server server = createServer(PORT);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletContextHandler servletContextHandler = new ServletContextHandler();

    server.setHandler(servletContextHandler);
    ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("javax.ws.rs.core.Application", "resource");

    return server;
  }
}
