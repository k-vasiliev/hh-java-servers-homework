import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

public class ForbiddenException extends WebApplicationException
{

    public ForbiddenException(String explanation)
    {
        super(Response.status(Status.FORBIDDEN).entity(explanation).build());
    }
}
