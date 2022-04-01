import filter.AuthFilter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import servlet.ClearCounterServlet;
import servlet.CounterServlet;

public class ServletApplication {

  private static final String APP_PATH = "/counter";
  private static final String CLEAR_PATH = APP_PATH + "/clear";

  public static void main(String[] args) throws Exception {
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletHandler handler = new ServletHandler();
    handler.addServletWithMapping(CounterServlet.class, APP_PATH);
    handler.addServletWithMapping(ClearCounterServlet.class, CLEAR_PATH);
    handler.addFilterWithMapping(AuthFilter.class, CLEAR_PATH, 0);
    server.setHandler(handler);
    return server;
  }
}
