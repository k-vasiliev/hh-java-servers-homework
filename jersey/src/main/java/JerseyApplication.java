import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.glassfish.jersey.servlet.ServletProperties;
import resource.CounterResource;

import javax.ws.rs.core.Application;
import java.util.Set;

public class JerseyApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        return Set.of(CounterResource.class);
    }

    private static Server createServer(int port) {
        Server server = new Server(port);
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);

        ServletHolder servletHolder = ctx.addServlet(ServletContainer.class, "/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter(ServletProperties.JAXRS_APPLICATION_CLASS, "JerseyApplication");
        return server;
    }

    public static void main(String[] args) throws Exception {
        int port = 8081;
        Server server = createServer(port);
        server.start();
        server.join();
    }

}
