import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import servlets.CounterClearServlet;
import servlets.CounterServlet;
import servlets.StatusServlet;

public class ServletApplication {
  private final static int PORT = 8081;

  public static void main(String[] args) throws Exception {
    Server server = createServer();
    server.start();
    server.join();
  }

  private static Server createServer() {
    Server server = new Server(ServletApplication.PORT);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(StatusServlet.class, "/status");
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");
    server.setHandler(servletHandler);
    return server;
  }
}
