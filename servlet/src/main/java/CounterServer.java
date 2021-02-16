import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class CounterServer extends Server {

    public static final int DEFAULT_PORT = 8081;

    private Counter counter;

    public CounterServer() {
        super(DEFAULT_PORT);
        counter = new Counter();
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{getContextHandler(), new DefaultHandler()});
        setHandler(handlers);
    }

    private ServletContextHandler getContextHandler() {
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(CounterServlet.class, "/counter");
        context.addServlet(CounterClearServlet.class, "/counter/clear");
        context.setAttribute("counter", counter);

        return context;
    }
}
