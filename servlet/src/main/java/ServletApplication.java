import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.CounterClearServlet;
import servlets.CounterOperationsServlet;

public class ServletApplication {
    public ServletApplication() {
    }

    public static void main(String[] args) {
        ServletContextHandler context = new ServletContextHandler(1);
        context.setContextPath("/");
        context.addServlet(new ServletHolder(new CounterOperationsServlet()), "/counter");
        context.addServlet(new ServletHolder(new CounterClearServlet()), "/counter/clear");
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8081);
        server.addConnector(connector);
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Throwable var5) {
            var5.printStackTrace(System.err);
        }

    }
}
