public class ServletApplication {

  public static void main(String[] args) throws Exception {
    ServletServer servletServer = new ServletServer(8081);
    servletServer.run();
  }
}
