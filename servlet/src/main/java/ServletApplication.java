import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import servlets.CounterClearServlet;
import servlets.CounterServlet;
import servlets.StatusServlet;

public class ServletApplication {

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(StatusServlet.class, "/status");
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");
    server.setHandler(servletHandler);

    return server;
  }

  public static void main(String[] args) throws Exception {
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }
}
