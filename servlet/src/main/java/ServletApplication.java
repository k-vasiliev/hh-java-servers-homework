import controller.CounterClearServlet;
import controller.CounterServlet;
import controller.StatusServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication {

    public static void main(String[] args) {
        Server server = getServer(8081);
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Server getServer(int port) {
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();
        server.setHandler(handler);
        handler.addServletWithMapping(StatusServlet.class, "/status");
        handler.addServletWithMapping(CounterServlet.class, "/counter");
        handler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");
        return server;
    }
}
