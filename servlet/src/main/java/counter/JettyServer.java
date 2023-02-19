package counter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.ServletMapping;

public class JettyServer {

  private final Server server;

  public JettyServer(int port) throws Exception {
    ServletHandler servletHandler = new ServletHandler();
    ServletHolder cunterServletHolder = new ServletHolder(new CounterServlet());
    ServletMapping servletMapping = new ServletMapping();
    servletMapping.setServletName(cunterServletHolder.getName());
    servletMapping.setPathSpecs(new String[]{"/counter", "/counter/clear"});
    servletHandler.addServlet(cunterServletHolder);
    servletHandler.addServletMapping(servletMapping);
    servletHandler.addServletWithMapping(StatusServlet.class, "/status");
    server = new Server(port);
    server.setHandler(servletHandler);
  }

  public void run() throws Exception {
    server.start();
    server.join();
  }

}
