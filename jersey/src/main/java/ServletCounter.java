import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;

@Path("/counter")
public class ServletCounter {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCount() {
        return Response
                .ok(new ObjectMapper().createObjectNode()
                        .put("date", LocalDateTime.now().toString())
                        .put("value", Count.getValue()))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response incCounter() {
        Count.inc();
        return Response
                .ok(new ObjectMapper().createObjectNode()
                        .put("date", LocalDateTime.now().toString())
                        .put("value", Count.getValue()))
                .build();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response subtractCounter(@HeaderParam("Subtraction-Value") Integer value) {
        if (value == null) {
            return Response
                    .status(HttpServletResponse.SC_BAD_REQUEST)
                    .entity(new ObjectMapper().createObjectNode()
                            .put("error", "subtraction value is empty"))
                    .build();
        }

        Count.subtract(value);
        return Response
                .ok(new ObjectMapper().createObjectNode()
                        .put("date", LocalDateTime.now().toString())
                        .put("value", Count.getValue()))
                .build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/clear")
    public Response clearCounter(@CookieParam("hh-auth") String value) {

        if (value == null || value.length() < 10) {
            return Response
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .entity(new ObjectMapper().createObjectNode()
                            .put("error", "auth cookies is invalid"))
                    .build();
        }

        Count.setValue(0);
        return Response
                .ok(new ObjectMapper().createObjectNode()
                        .put("date", LocalDateTime.now().toString())
                        .put("value", Count.getValue()))
                .build();
    }
}
