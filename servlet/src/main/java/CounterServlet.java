import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import ru.hh.Constants;
import ru.hh.CounterService;

public class CounterServlet extends HttpServlet {
    private final CounterService service = new CounterService();
    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> postHandles = new HashMap<>();

    public CounterServlet() {
        postHandles.put("/counter", this::upCounterPost);
        postHandles.put("/counter/clear", this::cleanCounterPost);
    }

    private boolean isAuthorized(HttpServletRequest request) {
        var cookies = request.getCookies();

        return cookies != null && Stream.of(cookies)
                .anyMatch(cookie -> cookie.getName().equals(Constants.COOKIE_PARAMETER) &&
                                    cookie.getValue() != null &&
                                    cookie.getValue().length() > Constants.COOKIE_PARAM_LENGTH);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        try (var writer = response.getWriter()) {
            writer.println(service.getCounterValue().value);
        }

        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        var uri = request.getRequestURI();
        var handle = postHandles.get(uri);

        if (handle == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        handle.accept(request, response);
   }

   private void upCounterPost(HttpServletRequest request, HttpServletResponse response) {
         service.upCounterValue();
         response.setStatus(HttpServletResponse.SC_OK);
   }

   private void cleanCounterPost(HttpServletRequest request, HttpServletResponse response) {
        if (!isAuthorized(request)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        service.cleanCounterValue();
        response.setStatus(HttpServletResponse.SC_OK);
   }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            var str = request.getHeader(Constants.HEADER_PARAMETER);
            var value = Long.parseLong(str);
            service.reduceCounterValueBy(value);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (NumberFormatException ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
