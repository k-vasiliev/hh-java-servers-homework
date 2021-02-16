import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class EmbeddedJetty {

    private static Server createServer(int port) {
        Server server = new Server(port);
        ServletHandler servlethandler = new ServletHandler();
        servlethandler.addServletWithMapping(ServletCounter.class, "/counter");
        servlethandler.addServletWithMapping(ServletCounterClear.class, "/counter/clear");
        server.setHandler(servlethandler);
        return server;
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        Server server = createServer(port);
        server.start();
        server.join();
    }
}
