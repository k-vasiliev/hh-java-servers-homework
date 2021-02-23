public class ServletApplication {

  public static void main(String[] args) throws Exception {
    CounterServer counterServer = new CounterServer(8081);

    counterServer.start();
    counterServer.join();
  }
}
