import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class JettyServer {

    private final Server server;

    public JettyServer(int port) {
        server = new Server(port);
        ResourceConfig config = new ResourceConfig(CounterResource.class);
        ServletContainer servletContainer = new ServletContainer(config);
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servletHolder, "/*");
    }

    public void run() throws Exception {
        server.start();
        server.join();
    }
}
