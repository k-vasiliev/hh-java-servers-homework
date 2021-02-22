package resource;

import entity.Counter;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/counter")
public class CountServlet {
	
	private Counter counter;
	
	public void init(@Context ServletContext servletContext) {
		counter = (Counter) servletContext.getAttribute("counter");
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		System.out.println(counter);
		counter.setDate(new Date());
		return Response.status(200).entity(counter).build();
	}
	
	@POST
	public Response post() {
		counter.increaseCount();
		return Response.status(200).entity(counter).build();
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
}