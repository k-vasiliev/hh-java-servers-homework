import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class JerseyApplication extends Application {

  static AtomicInteger counter = new AtomicInteger(0);

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> s = new HashSet<>();
    s.add(Resource.class);
    return s;
  }

  private static Server createServer(int port) {
    Server server = new Server(port);

    ServletContextHandler ctx = new ServletContextHandler();
    server.setHandler(ctx);

    ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(0);
    servletHolder.setInitParameter("javax.ws.rs.Application", "JerseyApplication");
    return server;
  }

  public static void main(String[] args) throws Exception {
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }
}
