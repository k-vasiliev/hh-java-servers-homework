import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication {

    private static Server createServer(int port) {
        var server = new Server(port);
        var servletHandler = new ServletHandler();
        servletHandler.addServletWithMapping(ServletCounter.class, "/counter");
        servletHandler.addServletWithMapping(ServletCounterClear.class,"/counter/clear");
        server.setHandler(servletHandler);
        return server;
    }

    public static void main(String[] args) throws Exception {
        var port = 8081;
        var server = createServer(port);
        server.start();
        server.join();
    }
}
