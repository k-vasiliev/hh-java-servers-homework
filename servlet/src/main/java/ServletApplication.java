import server.CounterServer;

public class ServletApplication {

    public static void main(String[] args) {

        final int DEFAULT_PORT = 8081;

        CounterServer server = new CounterServer(DEFAULT_PORT);
        try {
            server.start();
            System.out.println("Starting server!");
            server.join();
        } catch (Exception e) {
            System.out.println("Error.");
            e.printStackTrace();
        }
    }
}
