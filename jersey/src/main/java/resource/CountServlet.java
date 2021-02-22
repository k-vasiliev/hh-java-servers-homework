package resource;

import entity.Counter;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/counter")
public class CountServlet {
	
	private static Counter counter = new Counter(0);

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		counter.setDate(new Date());
		return Response.status(200).entity(counter).build();
	}
	
	@POST
	public Response post() {
		counter.increaseCount();
		return Response.status(200).build();
	}
	
	@DELETE
	public Response delete(@HeaderParam("Subtraction-Value") String num) {
		if (num == null) {
			return Response.status(400).build();
		}
		try {
			counter.decreaseCount(Integer.parseInt(num));
			return Response.status(200).build();
		} catch (NumberFormatException ex) {
			System.out.println("Input correct number");
			return Response.status(400).build();
		}
	}
	
	@POST
	@Path("/clear")
	public Response postClear(@CookieParam("hh-auth") String val) {
		if (val.length() > 10) {
			counter.clearCount();
			return Response.status(200).build();
		}
		return Response.status(400).build();
	}
}