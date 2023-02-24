import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class LocalJettyServer {
    private final Server server;

    private LocalJettyServer(int port) {
        server = new Server(port);
        var servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(StatusServlet.class, "/status");
        servletHandler.addServletWithMapping(CounterServlet.class, "/counter/*");
        server.setHandler(servletHandler);
    }

    public void run() throws Exception {
        server.start();
        server.join();
    }

    public static LocalJettyServer create(int port) {
        return new LocalJettyServer(port);
    }
}
