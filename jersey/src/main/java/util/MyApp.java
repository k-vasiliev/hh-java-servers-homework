package util;

import org.glassfish.jersey.server.ResourceConfig;


public class MyApp extends ResourceConfig {
    public MyApp() {
        packages("resource");
    }
}
