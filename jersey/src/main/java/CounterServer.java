import java.time.Clock;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class CounterServer extends Server {

    public static final int DEFAULT_PORT = 8081;

    private Counter counter;

    public CounterServer() {
        this(Clock.systemDefaultZone());
    }

    public CounterServer(Clock clock) {
        super(DEFAULT_PORT);
        counter = new Counter();
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{getContextHandler(clock), new DefaultHandler()});
        setHandler(handlers);
    }

    private ServletContextHandler getContextHandler(Clock clock) {
        ServletContextHandler context = new ServletContextHandler();
        context.setAttribute("counter", counter);
        context.setAttribute("clock", clock);
        ServletHolder holder = context.addServlet(ServletContainer.class, "/*");
        holder.setInitOrder(1);
        holder.setInitParameter("javax.ws.rs.Application", "CounterServiceConfig");

        return context;
    }
}
