import java.util.logging.Level;
import java.util.logging.Logger;

public class JerseyApplication {

    private static final Logger LOGGER;

    static {
        LOGGER = Logger.getLogger(JerseyApplication.class.getName());
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
