import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {

  private static Server createServer() {
    Server server = new Server(8081);
    Counter counter = new Counter();
    ServletContextHandler contextHandler = new ServletContextHandler();
    server.setHandler(contextHandler);
    ServletHolder servletHolder = contextHandler.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("javax.ws.rs.Application", "CounterConfigService");
    return server;
  }

  public static void main(String[] args) throws Exception {
    Server server = createServer();
    server.start();
    server.join();
  }
}

