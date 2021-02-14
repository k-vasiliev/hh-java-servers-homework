import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class EmbeddedServer {

    public void run() throws Exception {
        Server server = new Server(8081);
        ServletContextHandler context = new ServletContextHandler();
        server.setHandler(context);
        ServletHolder holder = context.addServlet(ServletContainer.class, "/*");
        holder.setInitOrder(1);
        server.start();
        server.join();
    }


}
