import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class CounterServer extends Server {

    public CounterServer(int port) {
        super(port);
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{getContextHandler(), new DefaultHandler()});
        setHandler(handlers);
    }

    private ServletContextHandler getContextHandler() {
        ServletContextHandler context = new ServletContextHandler();
        ServletHolder holder = context.addServlet(ServletContainer.class, "/*");
        holder.setInitOrder(1);
        holder.setInitParameter("javax.ws.rs.Application", "JerseyApplicationConfig");

        return context;
    }
}
