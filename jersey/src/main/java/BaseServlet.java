import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.time.format.DateTimeFormatter;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("/counter")
public class BaseServlet extends HttpServlet {

    private final String DECREMENT_HEADER = "Subtraction-Value";

    private final CounterService service;

    private final CounterClock counterClock;

    public BaseServlet() {
        service = CounterService.getInstance();
        counterClock = CounterClock.getInstance();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get() throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        node.put("date", formatter.format(counterClock.getTimestamp()));
        node.put("value", service.get());
        return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(node);
    }

    @POST
    public void increment(){
        service.increment();
    }

    @DELETE
    public void decrement(@HeaderParam(DECREMENT_HEADER) Integer by) throws BadRequestException {
        if (by == null || by <= 0) {
            throw new BadRequestException("invalid decrement");
        }
        service.decrementBy(by);
    }

    @Path("/clear")
    @POST
    public void reset(@CookieParam("hh-auth") String cookie) {
        if (cookie == null || cookie.length() <= 10) {
            throw new ForbiddenException("forbidden");
        }
        else {
            service.reset();
        }
    }
}
