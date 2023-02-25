package resource;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import service.CounterService;

import java.util.Arrays;
import java.util.Optional;

@Path("/counter/clear")
public class CounterClearResource {
    private static final CounterService counterService = CounterService.getInstance();

    @POST
    public Response clearCounter(@Context HttpServletRequest request) {
        Optional<Cookie[]> authCookie = Optional.ofNullable(request.getCookies())
                .filter(cookies ->
                        Arrays.stream(cookies)
                                .filter(cookie -> cookie.getName().equals("hh-auth"))
                                .anyMatch(cookie -> cookie.getValue().length() > 10));

        if (authCookie.isPresent()) {
            counterService.clearCounter();
           return Response.ok().build();
        } else {
            return  Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        }
    }
}
