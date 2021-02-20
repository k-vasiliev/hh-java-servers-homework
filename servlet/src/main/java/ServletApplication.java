
import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;


public class ServletApplication {

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = new Server(7070);
    ServletContextHandler handler = new ServletContextHandler(server, "/");
    handler.addServlet(MyServlet.class, "/counter");
    handler.addServlet(MyServlet.class, "/counter/clear");
    server.start();
  }
}
