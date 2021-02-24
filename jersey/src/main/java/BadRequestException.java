import java.net.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response.*;

public class BadRequestException extends WebApplicationException
{

    public BadRequestException(String explanation)
    {
        super(Response.status(Status.BAD_REQUEST).entity(explanation).build());
    }
}
