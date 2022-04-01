package resource;

import dto.CounterDTO;
import entity.Counter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.ws.rs.CookieParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

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
    public Response clearCounter(
            @CookieParam(value = "hh-auth")
            @NotBlank
            @Pattern(regexp = ".{11,}")
            String authParam
    ) {
        Counter.set(0);
        return Response.ok().build();
    }

    //вроде в задании нет требований про валидацию, но, мне кажется, лучше это сделать
    //и отрицательные значения отпинывать, если очень хочется увеличивать счетчик больше чем на 1, то лучше параметр в post-запрос добавить
    @DELETE
    public Response decrementCounter(
            @HeaderParam(value = SUBTRACTION_VALUE_HEADER)
            @NotNull(message = SUBTRACTION_VALUE_HEADER + " header is required")
            @Min(value = 0, message = SUBTRACTION_VALUE_HEADER + " header must contain non-negative integer")
            Integer subtractionValue
    ) {
        if (Counter.get() < subtractionValue) {
            return Response.status(HttpServletResponse.SC_CONFLICT, SUBTRACTION_VALUE_HEADER + " header contains value greater then current counter value").build();
        } else {
            Counter.addAndGet(-subtractionValue);
            return Response.ok().build();
        }
    }

}
