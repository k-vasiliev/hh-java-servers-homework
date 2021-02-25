import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static javax.ws.rs.core.Response.*;
import static javax.ws.rs.core.Response.Status.*;
import static javax.ws.rs.core.Response.status;

@Path("/counter")
public class CounterServlet {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounterValue() {
        ObjectNode node = new ObjectMapper().createObjectNode()
                .put("date", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
                .put("value", Counter.getValue());
        return ok(node).build();
    }

    @POST
    public Response post() {
        Counter.inc();
        return ok().build();
    }

    @DELETE
    public Response delete(@HeaderParam("Subtraction-Value") String value) {
            Counter.sub(Integer.parseInt(value));
            return ok().build();
    }

    @POST
    @Path("/clear")
    public Response clear(@CookieParam("hh-auth") String cookie) {
        if(cookie == null){
            return status(BAD_REQUEST).build();
        }else if (cookie.length()<10){
            return status(UNAUTHORIZED).build();

        }
        else{
            Counter.drop();
            return ok().build();
        }
    }
}