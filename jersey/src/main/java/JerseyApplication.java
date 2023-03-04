import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication extends ResourceConfig {
  public JerseyApplication() {
    register(ApplicationBinder.class);
  }

  private static Server createServer(int port) {
    Server server = new Server(port);

    ServletContextHandler sch = new ServletContextHandler();
    server.setHandler(sch);

    ServletHolder servletHolder = sch.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("jakarta.ws.rs.Application",
        ApplicationConfig.class.getName());
    return server;
  }

  public static void main(String[] args) {
    var server = createServer(8081);
    try {
      server.start();
      server.join();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    // run, Jetty, run!
  }
}
