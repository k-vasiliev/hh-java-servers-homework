import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication {

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(StatusOkServlet.class, "/status");
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(CounterClearServlet.class, "/counter/clear");

    server.setHandler(servletHandler);
    return server;
  }
}
