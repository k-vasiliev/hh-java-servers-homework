package filter;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Cookie;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

@Auth
@Provider
public class AuthFilter implements jakarta.ws.rs.container.ContainerRequestFilter
{
    @Override
    public void filter(ContainerRequestContext requestContext) {
        Cookie authCookie = requestContext.getCookies().get("hh-auth");
        if(authCookie == null || authCookie.getValue().length() <= 10){
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }
}
