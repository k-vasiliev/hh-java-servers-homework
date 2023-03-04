import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication {
  private static final int PORT = 8081;

  private static Server createServer() {
    Server server = new Server(PORT);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(CounterServlet.class, "/");
    server.setHandler(servletHandler);
    return server;
  }

  public static void main(String[] args) throws Exception {
    Server server = createServer();
    server.start();
    server.join();
  }
}
