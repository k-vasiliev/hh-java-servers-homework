import org.glassfish.jersey.server.ResourceConfig;

public class JaxRsActivator extends ResourceConfig {
    public JaxRsActivator() {
        packages("resource");
    }
}

