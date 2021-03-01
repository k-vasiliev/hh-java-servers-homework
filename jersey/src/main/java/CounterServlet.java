import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/counter")
public class CounterServlet {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() throws Exception{
        return Response.ok(new ObjectMapper().writeValueAsString(Counter.getInstance())).build();
    }

    @POST
    public Response incCounter() {
        Counter.getInstance().increment();
        return Response.noContent().build();
    }

    @DELETE
    public Response subtractCounter(@HeaderParam("Subtraction-Value") Integer val) {
        Counter.getInstance().subtract(val);
        return Response.noContent().build();
    }

    @POST
    @Path("/clear")
    public Response resetCounter(@CookieParam("hh-auth") String hhAuth) {
        if (hhAuth.length() > 10) {
            Counter.getInstance().reset();
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

    }
}
