import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import servlets.ServletCounter;

public class ServletApplication {

  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = new Server(8081);
    ServletContextHandler handler = new ServletContextHandler(server, "/");
    handler.addServlet(ServletCounter.class, "/counter");
    handler.addServlet(ServletCounter.class, "/counter/clear");
    server.start();
    server.join();
  }
}
