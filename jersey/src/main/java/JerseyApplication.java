import server.CounterServer;

import java.time.Clock;

public class JerseyApplication {

    public static void main(String[] args) {
        final int DEFAULT_PORT = 8081;
        Clock clock = Clock.systemDefaultZone();

        CounterServer server = new CounterServer(DEFAULT_PORT, clock);
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
