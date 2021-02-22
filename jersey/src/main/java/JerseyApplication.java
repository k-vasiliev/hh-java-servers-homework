import entity.Counter;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {
  
  private static Counter counter = new Counter(0);
  
  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    Server server = createServer();
    
    server.start();
    server.join();
  }
  
  public static Server createServer()
  {
    Server server = new Server(8081);
  
    ServletContextHandler handler = new ServletContextHandler();
//    handler.setContextPath("/");
//    handler.setAttribute("counter", counter);
    server.setHandler(handler);
  
    ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("jersey.config.server.provider.packages", "resource");
    return server;
  }
}
