package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import resource.CounterClearServlet;
import resource.CounterServlet;
import resource.StatusServlet;

public class JettyServer {

  private final Server server;

  public JettyServer(int port) {
    this.server = new Server(port);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");
    servletHandler.addServletWithMapping(StatusServlet.class, "/status");
    server.setHandler(servletHandler);
  }

  public void launch() throws Exception {
    server.start();
    server.join();
  }

}
