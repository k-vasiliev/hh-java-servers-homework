package com.hh.jersey.app.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Path("/counter")
public class CounterResource {
    private static final String HEADER_SUBTRACTION = "Subtraction-Value";
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    @Inject
    private CounterService counterService;

    public CounterResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() throws JsonProcessingException {
        long count = counterService.getCounter();
        Statistic statistic = new Statistic();
        LocalDateTime localDate = LocalDateTime.now();
        localDate.format(DateTimeFormatter.ISO_DATE);
        statistic.setTime(localDate);
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
            counterService.increment();
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
}
