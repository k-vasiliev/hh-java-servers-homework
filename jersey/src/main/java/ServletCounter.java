import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/counter")
public class ServletCounter {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCount() {
        return getCounterDtoSuccess();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response incCounter() {
        Count.inc();
        return getCounterDtoSuccess();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public Response subtractCounter(@HeaderParam("Subtraction-Value") Integer value) {
        if (value == null) {
            return Response
                    .status(HttpServletResponse.SC_BAD_REQUEST)
                    .entity(new CounterDto("subtraction value is empty"))
                    .build();
        }

        Count.subtract(value);
        return getCounterDtoSuccess();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/clear")
    public Response clearCounter(@CookieParam("hh-auth") String value) {

        if (value == null || value.length() < 10) {
            return Response
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .entity(new CounterDto("auth cookies is invalid"))
                    .build();
        }

        Count.setValue(0);
        return getCounterDtoSuccess();
    }

    private Response getCounterDtoSuccess() {
        return Response
                .ok(new CounterDto(Count.getValue(), LocalDateTime.now().toString()))
                .build();
    }
}
