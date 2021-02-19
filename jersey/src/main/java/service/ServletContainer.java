package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Path("/counter")
public class ServletContainer {
    private static ServletContainer instance;

    private int count;

    private ServletContainer() { count = 0; }

    public static ServletContainer getInstance() {
        if (instance == null) {
            instance = new ServletContainer();
        }
        return instance;
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String incrementByOne(@CookieParam("hh-auth") String cookie) {
        if (cookie != null && cookie.length() > 10) {
            count++;
            return "Counter incremented by 1";
        }
        return "Cookie is required";
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String decrementByN(@QueryParam("subtraction") int n) {
        count -= n;
        return "Decremented by " + n;
    }

    @Path("/clear")
    @Produces(MediaType.TEXT_PLAIN)
    public String reset() {
        setCount(0);
        return "Successfully reset";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCounter() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        node.put("date", formatter.format(now));
        node.put("value", count);
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

}
