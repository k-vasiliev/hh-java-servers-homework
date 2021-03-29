import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {
  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletContextHandler context = new ServletContextHandler();
    context.setAttribute("counter", new Counter());
    server.setHandler(context);

    ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
    servletHolder.setInitOrder(1);
    servletHolder.setInitParameter("javax.ws.rs.Application", "MyApplication");
    return server;
  }

  public static void main(String[] args) throws Exception {
    System.out.println("run, Jersey, run!");
    Server server = createServer(8081);
    server.start();
  }
}
