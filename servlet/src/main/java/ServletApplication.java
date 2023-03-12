import filter.AuthFilter;
import jakarta.servlet.DispatcherType;
import listener.CounterAppContextListener;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlet.CounterCleanerServlet;
import servlet.CounterServlet;
import servlet.StatusServlet;

import java.util.EnumSet;

public class ServletApplication {

  private static Server createServer(int port) {
    Server server = new Server(port);

    ServletContextHandler ctx = new ServletContextHandler();


    ctx.addEventListener(new CounterAppContextListener());
    server.setHandler(ctx);

    ctx.addServlet(CounterServlet.class, "/counter");
    ctx.addServlet(StatusServlet.class, "/status");
    ctx.addServlet(CounterCleanerServlet.class, "/counter/clear");
    ctx.addFilter(AuthFilter.class, "/counter/clear", EnumSet.of(DispatcherType.REQUEST));

    return server;
  }

  public static void main(String[] args) throws Exception {
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }
}
