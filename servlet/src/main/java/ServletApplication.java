import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;


public class ServletApplication {

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(StatusServlet.class, "/status");
    servletHandler.addServletWithMapping(ClearServlet.class, "/counter/clear");
    server.setHandler(servletHandler);
    return server;
  }

  public static void main(String[] args) {
    var server = createServer(8081);
    try {
      server.start();
      server.join();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
