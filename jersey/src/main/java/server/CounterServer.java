package server;

import counter.Counter;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;

import java.time.Clock;

public class CounterServer extends Server {

    private Counter counter;

    public CounterServer(int port, Clock clock) {
        super(port);
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
        holder.setInitParameter("javax.ws.rs.Application", "CounterServerConfig");
        return context;
    }
}

