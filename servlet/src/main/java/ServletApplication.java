import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public class ServletApplication {
  private static Counter count = new Counter(0);
  
  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = createServer(8081);
    server.start();
    server.join();
  }
  
  public static Server createServer(int port) {
    Server server = new Server(port);
    ServletContextHandler handler = new ServletContextHandler();
    handler.setContextPath("/");
    handler.setAttribute("count", count);
    handler.addServlet(CountServlet.class, "/counter");
    handler.addServlet(ClearServlet.class, "/counter/clear");
    server.setHandler(handler);
    return server;
  }
}