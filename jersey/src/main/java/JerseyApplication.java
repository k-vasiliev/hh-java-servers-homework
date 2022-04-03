import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {


    private static Server createServer(int port) {
        var server = new Server(port);
        var ctx = new ServletContextHandler();
        server.setHandler(ctx);
        var servletHolder = ctx.addServlet(ServletContainer.class,"/*");
        servletHolder.setInitOrder(1);
        servletHolder.setInitParameter("javax.ws.rs.Application","JaxRsActivator");
        return server;
    }

    public static void main(String[] args) throws Exception {

        var port = 8081;
        var server = createServer(port);
        server.start();
        server.join();

    }

}
