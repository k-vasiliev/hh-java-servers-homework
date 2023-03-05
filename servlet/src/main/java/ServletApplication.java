import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import servlets.ClearCounterServlet;
import servlets.CounterServlet;
import servlets.StatusServlet;

public class ServletApplication {
    private static final int port = 8081;

    public static void main(String[] args) throws Exception {
        getServerInstance().start();
    }

    private static Server getServerInstance() {
        final Server server = new Server(port);
        ServletHandler servletHandler = new ServletHandler();

        servletHandler.addServletWithMapping(StatusServlet.class, "/status");
        servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
        servletHandler.addServletWithMapping(ClearCounterServlet.class, "/counter/clear");

        server.setHandler(servletHandler);
        return server;
    }
}
