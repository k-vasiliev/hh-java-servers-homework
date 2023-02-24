package resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.HeaderParam;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mapper.CounterJsonMapper;
import ru.hh.Constants;
import ru.hh.CounterCommonService;

@Path("/counter")
public class CounterResource {
    private final CounterCommonService service = new CounterCommonService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() throws JsonProcessingException {
        var dto = service.getCounterValue();
        var json = CounterJsonMapper.toJson(dto);
        return Response.ok(json).build();
    }

    @POST
    public Response incrementCounter() {
        service.upCounterValue();
        return Response.ok().build();
    }

    @POST
    @Path("/clear")
    public Response clearCounter(@CookieParam(value = Constants.COOKIE_PARAMETER) String userToken) {
        if (!isAuthorized(userToken))
            return Response.status(Response.Status.UNAUTHORIZED).build();

        service.cleanCounterValue();
        return Response.ok().build();
    }

    public boolean isAuthorized(String value) {
        return value != null && value.length() > 10;
    }

    @DELETE
    public Response reduceCounter(@HeaderParam(value = Constants.HEADER_PARAMETER) String value) {
        try {
            var number = Long.parseLong(value);
            service.reduceCounterValueBy(number);
            return Response.ok().build();
        }
        catch (Exception ex) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
