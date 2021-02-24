import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class CounterServer extends Server {
    public CounterServer(int port) {
        super(port);

        ServletContextHandler servletContextHandler = new ServletContextHandler();
        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, "/*");
        setHandler(servletContextHandler);
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("javax.ws.rs.Application", "CounterConfig");

    }
}
