import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/counter")
public class ServletCounter {

    private static final String HEADER_SUBTRACTION_VALUE = "Subtraction-Value";
    private static final String ERR_SUBTRACTION_VALUE_EMPTY = "subtraction value is empty";

    private static final String COOKIE_PARAM_HH_AUTH = "hh-auth";
    private static final int MIN_HH_AUTH_LENGTH = 10;
    private static final String ERR_HH_AUTH_INVALID = "auth cookies is invalid";

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
    public Response subtractCounter(@HeaderParam(HEADER_SUBTRACTION_VALUE) Integer value) {
        if (value == null) {
            return Response
                    .status(HttpServletResponse.SC_BAD_REQUEST)
                    .entity(new CounterDto(ERR_SUBTRACTION_VALUE_EMPTY))
                    .build();
        }

        Count.subtract(value);
        return getCounterDtoSuccess();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/clear")
    public Response clearCounter(@CookieParam(COOKIE_PARAM_HH_AUTH) String value) {

        if (value == null || value.length() < MIN_HH_AUTH_LENGTH) {
            return Response
                    .status(HttpServletResponse.SC_UNAUTHORIZED)
                    .entity(new CounterDto(ERR_HH_AUTH_INVALID))
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
