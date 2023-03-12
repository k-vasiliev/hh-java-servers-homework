package com.hh.jersey.app;

import com.hh.jersey.app.resource.AppConfig;
import jakarta.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;

import java.net.URI;


public class JerseyApplication {
    private static final String URL = "http://localhost/";

    public static void main(String[] args) throws Exception {
        // run, Jetty, run!

        Server server = startJettyServer(URL, 8081);
        server.start();
        server.join();
    }

    private static Server startJettyServer(String host, int port) {
        URI baseUri = UriBuilder.fromUri(host).port(port).build();
        AppConfig config = new AppConfig();
        return JettyHttpContainerFactory.createServer(baseUri, config, false);
    }
}
