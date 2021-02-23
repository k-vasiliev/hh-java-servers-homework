import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class CounterServer extends Server {

    public CounterServer(int port) {
        super(port);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        servletContextHandler.addServlet(CounterServlet.class, "/counter");
        servletContextHandler.addServlet(CounterClearServlet.class, "/counter/clear");
        setHandler(servletContextHandler.getHandler());

    }
}
