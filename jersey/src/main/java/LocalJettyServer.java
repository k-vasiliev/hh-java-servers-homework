import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;

public class LocalJettyServer {
    private final Server server;

    private LocalJettyServer(int port) {
        var handler = new ServletContextHandler();
        handler.addServlet(
                    ServletContainer.class,
                    "/*")
                .setInitParameter(
                        ServletProperties.JAXRS_APPLICATION_CLASS,
                        ComponentsConfig.class.getName());

        server = new Server(port);
        server.setHandler(handler);
    }

    public void run() throws Exception {
        server.start();
        server.join();
    }

    public static LocalJettyServer create(int port) {
        return new LocalJettyServer(port);
    }
}

