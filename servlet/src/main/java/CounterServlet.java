import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

import ru.hh.CounterCommonService;

public class CounterServlet extends HttpServlet {
    private final CounterCommonService service = new CounterCommonService();
    private final Map<String, BiConsumer<HttpServletRequest, HttpServletResponse>> postHandles = new HashMap<>();

    private CounterServlet() {
        postHandles.put("/counter",
                (request, response) -> {
                     service.upCounterValue();
                     response.setStatus(HttpServletResponse.SC_OK);
                });
        postHandles.put("/counter/clear",
                (request, response) -> {
                    if (!isAuthorized(request)) {
                        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                        return;
                    }
                    service.cleanCounterValue();
                    response.setStatus(HttpServletResponse.SC_OK);
                });
    }

    private boolean isAuthorized(HttpServletRequest request) {
        var cookies = request.getCookies();
        var thereIsCookies = cookies == null;
        var cookiesContainToken = Stream.of(cookies)
                .anyMatch(cookie -> cookie.getName().equals("hh-ru") &&
                                    cookie.getValue() != null &&
                                    cookie.getValue().length() > 10);
        return thereIsCookies && cookiesContainToken;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        try (var writer = response.getWriter()) {
            writer.print(service.getCounterValue());
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

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        try {
            var str = request.getParameter("Subtraction-Value");
            var value = Long.parseLong(str);
            service.reduceCounterValueBy(value);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        catch (Exception ex) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
