import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import resource.Counter;
import resource.Status;


public class JerseyApplication {

  public static void main(String[] args) throws Exception{
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);

    ResourceConfig resourceConfig = new ResourceConfig();
    resourceConfig.register(Counter.class);
    resourceConfig.register(Status.class);
    ServletContainer servletContainer = new ServletContainer(resourceConfig);
    ServletHolder servletHolder = new ServletHolder(servletContainer);
    servletHolder.setInitOrder(1);
    ServletContextHandler context = new ServletContextHandler(server, "/");
    context.addServlet(servletHolder, "/*");
    return server;
  }

}
