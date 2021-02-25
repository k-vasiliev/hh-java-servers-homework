import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ServletApplication {

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

  private static Server initServer(int port_id) {
    Server s = new Server(port_id);
    return addHandlers(s);
  }

  private static Server addHandlers(Server s) {
    ServletHandler sh = new ServletHandler();
    
    sh.addServletWithMapping(ServletCounter.class, "/counter");
    sh.addServletWithMapping(ServletClear.class, "/counter/clear");
    
    s.setHandler(sh);
    
    return s;
  }

  public static void main(String[] args) throws Exception {
    Server s = initServer(8081);
    s.start();
    s.join();
  }
}
