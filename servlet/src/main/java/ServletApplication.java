import counter.JettyServer;

public class ServletApplication {

  public static void main(String[] args) throws Exception {
    JettyServer jettyServer = new JettyServer(8081);
    jettyServer.run();
  }
}
