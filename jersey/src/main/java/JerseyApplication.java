
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = new Server(7070);
    ServletContextHandler ctxHandler = new ServletContextHandler();

    ServletHolder holder = ctxHandler.addServlet(ServletContainer.class, "/*");
    holder.setInitParameter("javax.ws.rs.Application", App.class.getName());
    server.setHandler(ctxHandler);

    try {
      server.start();
      server.join();
    } catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
    finally {
      server.destroy();
    }

  }
}
