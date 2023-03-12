import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import resources.CounterResourcesConfig;

public class JerseyApplication {
    private static final int port = 8081;

    public static void main(String[] args) throws Exception {
        getServerInstance().start();
    }

    private static Server getServerInstance() {
        final Server server = new Server(port);

        ServletContainer servletContainer = new ServletContainer(new CounterResourcesConfig());
        ServletHolder servletHolder = new ServletHolder(servletContainer);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(servletHolder, "/*");

        return server;
    }
}
