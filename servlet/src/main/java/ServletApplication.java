import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import servlets.CounterServlet;
import servlets.StatusServlet;
import servlets.ClearCounterServlet;

public class ServletApplication {

  public static void main(String[] args) throws Exception {
    Server server = new Server(8081);
    ServletContextHandler servletContextHandler = new ServletContextHandler();
    server.setHandler(servletContextHandler);
    servletContextHandler.addServlet(StatusServlet.class, "/status");
    servletContextHandler.addServlet(CounterServlet.class, "/counter");
    servletContextHandler.addServlet(ClearCounterServlet.class, "/counter/clear");

    server.start();
    server.join();
  }
}
