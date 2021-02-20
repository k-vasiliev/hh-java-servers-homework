public class JerseyApplication {

    public static void main(String[] args) throws Exception {
        CounterServer server = new CounterServer();
        server.start();
        server.join();
    }
}
