package resource;

import counter.Counter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/counter")
public class ServletCounter {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCount() {
        return Response.ok(Map.of("date",String.valueOf(Counter.getDate()),"value",String.valueOf(Counter.getCount()))).build();
    }
    @POST
    public void setCount(){
        Counter.setCount(Counter.getCount()+1);
    }
    @DELETE
    public void deleteCount(@QueryParam("Subtraction-Value")Integer subtractionValue){
        Counter.setCount(subtractionValue);
    }
    @POST
    @Path("/clear")
    public void clearCount(@CookieParam(value = "hh-auth") String cookieValue){
        if(cookieValue.length()>=10)Counter.setCount(0);
    }
}


