import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.w3c.dom.css.Counter;
import service.CounterService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Path("/counter")
public class ServletContainer {

    private CounterService service;

    public ServletContainer() { this.service = CounterService.getInstance(); }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String incrementByOne(@CookieParam("hh-auth") String cookie) {
        if (cookie != null && cookie.length() > 10) {
            service.incrementByOne();
            return "Counter incremented by 1";
        }
        return "Cookie is required";
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String decrementByN(@QueryParam("subtraction") Integer n) {
        if (n == null) {
            return "Decrement required";
        }
        service.decrementByN(n);
        return "Decremented by " + n;
    }

    @Path("/clear")
    @Produces(MediaType.TEXT_PLAIN)
    public String reset(@CookieParam("hh-auth") String cookie) {
        if (cookie.length() > 10) {
            service.reset();
            return "Successfully reset";
        }
        return "Cookie required";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getCounter() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        OffsetDateTime now = OffsetDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        node.put("date", formatter.format(now));
        node.put("value", service.getCount());
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }
}
