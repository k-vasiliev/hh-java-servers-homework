package resource;

import entity.Counter;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/counter/clear")
public class ClearServlet {
	
	Counter counter =  new Counter(0);
	
	@POST
	public Response post(@CookieParam("hh-auth") String val) {
		if (val.length() > 10) {
			counter.clearCount();
			return Response.status(200).build();
		}
		return Response.status(400).build();
	}
}
