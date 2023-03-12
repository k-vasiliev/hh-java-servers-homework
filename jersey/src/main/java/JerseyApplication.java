import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import util.MyApp;

public class JerseyApplication {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8081);
    ServletContextHandler servletContextHandler = new ServletContextHandler();
    ServletHolder servletHolder = new ServletHolder(new ServletContainer(new MyApp()));
    servletContextHandler.addServlet(servletHolder, "/*");
    server.setHandler(servletContextHandler);
    server.start();
    server.join();
  }
}
