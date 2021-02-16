import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class EmbeddedJetty {

    private static Server createServer() {
        Server server = new Server(8080);
        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.setAttribute("count", new Count());
        servletContextHandler.setContextPath("/");
        servletContextHandler.addServlet(ServletCounter.class, "/counter");
        servletContextHandler.addServlet(ServletCounterClear.class, "/counter/clear");
        server.setHandler(servletContextHandler);
        return server;
    }

    public static void main(String[] args) throws Exception {
        Server server = createServer();
        server.start();
        server.join();
    }
}
