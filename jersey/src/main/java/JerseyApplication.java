import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class JerseyApplication extends Application {

  private static int counter = 0; 

  public static int getCounter() {
    return counter;
  }

  public static void setCounter(int value) {
    counter = value;
  }

  public static void changeCounter(int difference) {
    counter += difference;
  }

  @Override
  public Set<Class<?>> getClasses() {
    Set<Class<?>> s = new HashSet<>();
    s.add(JerseyCounter.class);
    return s;
  }

  private static Server initServer(int port_id) {
    Server s = new Server(port_id);

    ServletContextHandler sch = new ServletContextHandler();
    s.setHandler(sch);

    ServletHolder sh = sch.addServlet(ServletContainer.class, "/*");
    sh.setInitOrder(0);
    sh.setInitParameter("javax.ws.rs.Application", "JerseyApplication");
    
    return s;
  }

  public static void main(String[] args) throws Exception {
    Server s = initServer(8081);
    s.start();
    s.join();
  }

} 
