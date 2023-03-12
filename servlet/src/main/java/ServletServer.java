import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
public class ServletServer {
    private final Server server;
    public ServletServer(int port) {
        ServletHandler servletHandler = new ServletHandler();

        servletHandler.addServletWithMapping(StatusServlet.class, "/status");
        servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
        servletHandler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");

        server = new Server(port);
        server.setHandler(servletHandler);
    }

    public void run() throws Exception {
        server.start();
        server.join();
    }
}
