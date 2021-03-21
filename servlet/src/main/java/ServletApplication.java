import java.util.EnumSet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import jakarta.servlet.DispatcherType;

public class ServletApplication {
  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletContextHandler context = new ServletContextHandler();
    context.addServlet(CounterServlet.class, "/counter");
    context.addServlet(CounterClearServlet.class, "/counter/clear");
    context.addFilter(AuthFilter.class, "/counter/clear", EnumSet.of(DispatcherType.REQUEST));
    context.setAttribute("counter", new Counter());
    server.setHandler(context);
    return server;
  }

  public static void main(String[] args) throws Exception {
    System.out.println("run, Jetty, run!");
    Server server = createServer(8081);
    server.start();
  }
}
