package com.hh.jersey.app.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.time.Instant;

@Path("/counter")
public class CounterResource {
    private static final String HEADER_SUBTRACTION = "Subtraction-Value";
    private static final ObjectMapper mapper = create();
    @Inject
    private CounterService counterService;

    public CounterResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() throws JsonProcessingException {
        long count = counterService.getCounter();
        CounterInfo statistic = new CounterInfo();
        statistic.setDateTime(Instant.now());
        statistic.setValue(count);

        return Response.ok(mapper.writeValueAsString(statistic)).build();
    }

    @POST
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response incrementCounter() {
        counterService.increment();
        return Response.noContent().build();
    }

    @POST()
    @Path(value = "clear")
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response clearCounter(@CookieParam("hh-auth") final String auth) {
        if (auth != null && new String(auth.getBytes(StandardCharsets.UTF_8)).length() > 10) {
            counterService.reset();
            return Response.noContent().build();
        }
        return Response.status(Response.Status.UNAUTHORIZED).build();
    }

    @DELETE
    @Produces(value = MediaType.APPLICATION_JSON)
    public Response deleteCounter(@HeaderParam(HEADER_SUBTRACTION) final Long subtraction) {
        if (subtraction != null) {
            counterService.decrementByValue(subtraction);
            return Response.noContent().build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    public static ObjectMapper create() {
        return new ObjectMapper()
                .findAndRegisterModules()
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }
}
