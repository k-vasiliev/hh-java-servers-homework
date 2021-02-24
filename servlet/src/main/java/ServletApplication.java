public class ServletApplication {

  public ServletApplication() {
  }

  public static void main(String[] args) throws Exception {
    CounterServer server = new CounterServer(8081);
    server.start();
    server.join();
  }
}
