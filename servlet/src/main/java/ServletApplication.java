import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication extends org.eclipse.jetty.server.Server{

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletHandler servlethandler = new ServletHandler();
    servlethandler.addServletWithMapping(CounterServlet.class, "/counter");
    servlethandler.addServletWithMapping(ClearWithCookiesCounterServlet.class, "/counter/clear");
    server.setHandler(servlethandler);
    return server;
  }

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }

}
