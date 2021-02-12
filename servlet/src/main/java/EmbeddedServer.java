import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class EmbeddedServer {

    private Server server;

    public Server run() throws Exception {
        server = new Server(8081);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(CounterServlet.class, "/counter");
        server.setHandler(handler);
        server.start();
        server.start();
        return server;
    }

}
