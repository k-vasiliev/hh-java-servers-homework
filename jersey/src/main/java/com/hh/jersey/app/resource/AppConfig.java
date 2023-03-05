package com.hh.jersey.app.resource;

import jakarta.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

public class AppConfig extends ResourceConfig {
    public AppConfig() {
        register(CounterResource.class);
        register(new AbstractBinder() {
            @Override
            protected void configure() {
                bind(CounterService.class).to(CounterServiceImpl.class).in(Singleton.class);
            }
        });
        packages("com.hh.jersey.app");
        packages("com.fasterxml.jackson.jaxrs.json");
    }
}
