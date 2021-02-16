import java.util.logging.Level;
import java.util.logging.Logger;

public class ServletApplication {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(ServletApplication.class.getName());
    }

    public static void main(String[] args) {
        CounterServer server = new CounterServer();
        try {
            server.start();
            server.join();
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, "Server bootup failed", e);
        }
        finally {
            stopServer(server);
        }
    }

    private static void stopServer(CounterServer server) {
        try {
            server.stop();
        }
        catch (Exception e) {
            LOGGER.log(Level.WARNING, "Server shutdown failed", e);
        }
    }
}
