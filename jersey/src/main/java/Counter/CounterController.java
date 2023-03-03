package Counter;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.HeaderParam;

@Path("/counter")
public class CounterController {

	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCounter()  {
		JsonObj js = new JsonObj(ServiceCounter.getCounter());
		return Response.ok(JsonMapper.getJson(js)).build();
	}

	@POST
	public Response addCounter() {
		return Response.ok(ServiceCounter.addCounter()).build();
	}
	@DELETE
	public Response decreaseCounter(@HeaderParam("Subtraction-Value") Integer vl) {
		if (vl == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(ServiceCounter.decreaseCounter(vl)).build();
	}
	
	@POST
	@Path(value = "/clear")
	public Response resetCounter(@CookieParam("hh-auth") String request) {
		if (request == null || request.length() < 10) {
	      return Response.status(Response.Status.BAD_REQUEST).build();
		}
	    return Response.ok(ServiceCounter.resetCounter()).build();
	}
}
