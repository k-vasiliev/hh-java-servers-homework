import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import counter.Counter;


public class JerseyApplication {
  private static Server createServer(int port) {
    Counter counter = Counter.getInstance();
    Server server = new Server(port);
    ServletContextHandler ctx = new ServletContextHandler();

    ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitParameter("javax.ws.rs.Application", "config.CounterConfig");
    server.setHandler(ctx);
    return server;
  }

  public static void main(String[] args) throws Throwable {
    // run, Jetty, run!
    Server server = createServer(8081);
    server.start();
    server.join();
  }
}
