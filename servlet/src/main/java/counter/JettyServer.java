package counter;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

import java.util.EnumSet;

public class JettyServer {

  private final Server server;

  public JettyServer(int port) {
    ServletHandler servletHandler = new ServletHandler();

    servletHandler.addServletWithMapping(StatusServlet.class, "/status");
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");
    servletHandler.addFilterWithMapping(LogFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

    server = new Server(port);
    server.setHandler(servletHandler);
  }

  public void run() throws Exception {
    server.start();
    server.join();
  }

}
