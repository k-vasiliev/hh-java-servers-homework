package server;

import counter.Counter;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlets.CounterClearPathServlet;
import servlets.CounterPathServlet;

public class CounterServer extends Server {

    private Counter counter;

    public CounterServer(int port) {
        super(port);
        counter = new Counter();
        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{getContextHandler(), new DefaultHandler()});
        setHandler(handlers);
    }

    private ServletContextHandler getContextHandler() {
        ServletContextHandler context = new ServletContextHandler();
        context.setAttribute("counter", counter);
        context.setContextPath("/");
        context.addServlet(CounterPathServlet.class, "/counter");
        context.addServlet(CounterClearPathServlet.class, "/counter/clear");
        return context;
    }
}

