package resource;

import dto.CounterDTO;
import entity.Counter;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Optional;

@Path("/counter")
public class CounterResource {

    private static final String SUBTRACTION_VALUE_HEADER = "Subtraction-Value";

    @GET
    @Produces("application/json")
    public Response getCounter() {
        CounterDTO counterDTO = new CounterDTO(LocalDateTime.now(), Counter.get());
        return Response.ok(counterDTO).build();
    }

    @POST
    public Response incrementCounter() {
        return Response.ok(Counter.incrementAndGet()).build();
    }

    @POST
    @Path("/clear")
    public Response clearCounter(@CookieParam(value = "hh-auth") String authParam) {
        boolean isAuthorized = Optional.ofNullable(authParam)
                .map(String::length)
                .map(length -> length > 10)
                .orElse(false);
        if (isAuthorized) {
            Counter.set(0);
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @DELETE
    public Response decrementCounter(@HeaderParam(value = SUBTRACTION_VALUE_HEADER)Integer subtractionValue) {
        if (subtractionValue == null) {
            return Response.status(HttpServletResponse.SC_BAD_REQUEST, SUBTRACTION_VALUE_HEADER + " header is required").build();
        } else {
            //вроде в задании нет требований про валидацию, но, мне кажется, лучше это сделать
            //и отрицательные значения отпинывать, если очень хочется увеличивать счетчик больше чем на 1, то лучше параметр в post-запрос добавить
            if (subtractionValue < 0) {
                return Response.status(HttpServletResponse.SC_BAD_REQUEST, SUBTRACTION_VALUE_HEADER + " header must contain positive integer").build();
            } else if (Counter.get() < subtractionValue) {
                return Response.status(HttpServletResponse.SC_CONFLICT, SUBTRACTION_VALUE_HEADER + " header contains value greater then current counter value").build();
            } else {
                Counter.addAndGet(-subtractionValue);
                return Response.ok().build();
            }
        }
    }

}
