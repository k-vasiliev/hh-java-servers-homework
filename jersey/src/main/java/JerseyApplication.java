import config.JerseyApplicationConfig;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

public class JerseyApplication {
  private final static int PORT = 8081;

  public static void main(String[] args) throws Exception {
    Server server = createServer(PORT);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);

    ServletContextHandler ctx = new ServletContextHandler();

    server.setHandler(ctx);

    ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitParameter(
        ServletProperties.JAXRS_APPLICATION_CLASS,
        JerseyApplicationConfig.class.getName()
    );

    return server;
  }
}
