public class ServletApplication {

    public static void main(String[] args) throws Exception {
        CounterServer server = new CounterServer();
        server.start();
        server.join();
    }
}
