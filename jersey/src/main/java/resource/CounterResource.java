package resource;

import dto.CounterDto;
import filter.Auth;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import service.CounterService;
import service.CounterServiceFactory;


@Path(value = "/")
public class CounterResource {

    CounterService counterService;

    public CounterResource(){
        this.counterService = CounterServiceFactory.getInstance();
    }

    @GET
    @Path(value = "/status")
    public Response status(){
        return Response.ok().build();
    }

    @GET
    @Path(value = "/counter")
    @Produces(MediaType.APPLICATION_JSON)
    public CounterDto get(){
        return counterService.get();
    }

    @POST
    @Path(value = "/counter")
    public Response increment(){
        try{
            counterService.increment();
        }catch (ArithmeticException e){
            return Response.status(Response.Status.CONFLICT).entity("Increment not possible").build();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path(value = "/counter")
    public Response subtract(@HeaderParam(value = "Subtraction-Value") Long value){
        if(value == null){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        try{
            counterService.subtract(value);
        }catch (ArithmeticException e){
            return Response.status(Response.Status.CONFLICT).entity("Impossible to subtract this value").build();
        }
        return Response.ok().build();
    }

    @POST
    @Path(value = "/counter/clear")
    @Auth
    public Response reset(){
        counterService.reset();
        return Response.ok().build();
    }
}
