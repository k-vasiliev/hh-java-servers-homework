import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class CounterServer extends Server {

    public CounterServer(int port) {
        super(port);
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{getContextHandler(), new DefaultHandler()});
        setHandler(handlers);
    }

    private ServletContextHandler getContextHandler() {
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(BaseServlet.class, "/counter");
        context.addServlet(ClearServlet.class, "/counter/clear");

        return context;
    }
}
