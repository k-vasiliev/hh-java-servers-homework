package res;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;


@Path("counter")
public class MyResource {
    private int counter = 0;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response get() {
        return Response.ok(String.valueOf(counter)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_HTML)
    public Response post(String message) throws JsonMappingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> json = mapper.readValue(message, Map.class);
        counter = Integer.parseInt(json.get("value"));
        return Response.ok(String.valueOf(counter)).build();
    }

    @POST
    @Path("/clear")
    @Produces(MediaType.TEXT_HTML)
    public Response clear(@Context HttpHeaders hh)  {
        Map<String, Cookie> cookies = hh.getCookies();
        Cookie cookie = cookies.get("hh-auth");
        if (cookie != null){
            if (cookie.getValue().length() > 10) counter = 0;
        }
        return Response.ok(String.valueOf(counter)).build();
    }

    @DELETE
    public Response delete(@Context HttpHeaders hh) {
        MultivaluedMap<String, String> headerParams = hh.getRequestHeaders();
        String val = headerParams.getFirst("Subtraction-Value");
        if (val != null) {
            counter -= Integer.parseInt(val);
        }
        return Response.ok(String.valueOf(counter)).build();
    }
}
