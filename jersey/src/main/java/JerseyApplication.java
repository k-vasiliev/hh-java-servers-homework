import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {
  
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
    server.setHandler(handler);
    
    ServletHolder servletHolder = handler.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("javax.ws.rs.Application", "MyApplication");
    
    return server;
  }
}
