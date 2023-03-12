import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication {

    public static void main(String[] args) throws Exception {
        // run, Jetty, run!
        Server server = createServer(8081);
        server.start();
        server.join();
    }

    private static Server createServer(int port) {
        Server server = new Server(port);
        ServletHandler servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(StatusServlet.class, "/status");
        servletHandler.addServletWithMapping(CounterServlet.class, "/counter/*");
        server.setHandler(servletHandler);
        return server;
    }
}
