package Counter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.CookieParam;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/counter")
public class CounterController {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCounter() {
		JsonObj jsonResponse = new JsonObj(ServiceCounter.getCounter());
		ObjectMapper jsonMapper = JsonMapper.getObjectMapper();
		try {
			return Response.ok(jsonMapper.writeValueAsString(jsonResponse)).build();
		} catch (JsonProcessingException e) {
			return Response.ok("ExceptionError " + e.getMessage()).build();
		}

	}

	@POST
	public Response addCounter() {
		return Response.ok(ServiceCounter.addCounter()).build();
	}

	@DELETE
	public Response decreaseCounter(@HeaderParam("Subtraction-Value") Integer value) {
		if (value == null) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		return Response.ok(ServiceCounter.decreaseCounter(value)).build();
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
