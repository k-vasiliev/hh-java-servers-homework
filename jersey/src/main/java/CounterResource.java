import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Path("/")
public class CounterResource {
    CounterService counterService = CounterService.getInstance();

    @GET
    @Path(value = "/counter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCounter() throws JsonProcessingException {
        getData getData = new getData(counterService.getCount());
        return Response.ok(Mapper.counterDtoToJson(getData)).build();
    }

    @POST
    @Path(value = "/counter")
    public Response incrCounter() {
        return Response.ok(counterService.incrementCounter()).build();
    }


    @DELETE
    @Path(value = "/counter")
    public Response subtractCounter(@HeaderParam("Subtraction-Value") Integer value) {
        if (value == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try {
            counterService.subtractCounter(value);
            return Response.ok().build();
        }
        catch (RuntimeException e){
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).entity("Bad number format.").build();
        }
    }


    @GET
    @Path(value = "/status")
    public Response getStatus() {
        return Response.ok().build();
    }

    @POST
    @Path(value = "/counter/clear")
    public Response clearCounter(@CookieParam("hh-auth") String cookie) {
        if (cookie == null || cookie.length()<10){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        counterService.clear();
        return Response.ok(0).build();
    }

}
