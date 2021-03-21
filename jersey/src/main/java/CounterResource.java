import javax.servlet.ServletContext;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/counter")
public class CounterResource {
    @Context
    private ServletContext servletContext;

    private Counter getCounter() {
        return (Counter)servletContext.getAttribute("counter");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return Response.ok(
            mapper.writeValueAsString(new CounterDto(getCounter()))
        ).build();
    }

    @POST
    public Response post() {
        return Response.ok(getCounter().increment()).build();
    }

    @DELETE
    public Response delete(@HeaderParam("Subtraction-Value") String strVal) {
        try {
            int val = Integer.parseInt(strVal);
            return Response.ok(getCounter().decrement(val)).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Invalid header Subtraction-Value")
                .build();
        }
    }

    @POST
    @Path (value = "/clear")
    public Response clear(@CookieParam("hh-auth") String hhAuth) {
        if (hhAuth != null && hhAuth.length() > 10) {
            return Response.ok(getCounter().clear()).build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
            .entity("Unauthorized")
            .build();
    }
}
