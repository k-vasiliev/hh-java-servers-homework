import server.JettyServer;

public class JerseyApplication {
  private final static int PORT = 8081;
  public static void main(String[] args) throws Exception {
    // run, Jetty, run!
    JettyServer server = new JettyServer(PORT);
    server.launch();
  }

}
