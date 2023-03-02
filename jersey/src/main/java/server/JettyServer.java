package server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import resource.CounterResource;
import resource.StatusResource;

public class JettyServer {

  private final Server server;

  public JettyServer(int port) {
    server = new Server(port);
    ResourceConfig resourceConfig = new ResourceConfig();
    resourceConfig.register(CounterResource.class);
    resourceConfig.register(StatusResource.class);
    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    servletHolder.setInitOrder(1);
    ServletContextHandler context = new ServletContextHandler(server, "/");
    context.addServlet(servletHolder, "/*");
  }

  public void launch() throws Exception {
    server.start();
    server.join();
  }

}
