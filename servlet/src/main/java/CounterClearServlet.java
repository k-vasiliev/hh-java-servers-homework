import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
public class CounterClearServlet extends HttpServlet {
    CounterService counterService = CounterService.getInstance();


    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (request.getCookies() == null) {
            response.sendError(400);
            return;
        }

        boolean correctRequest = Arrays.stream(request.getCookies())
                .anyMatch(cookie ->
                        Objects.equals(cookie.getName(), "hh-auth") &&
                                cookie.getValue().length() >= 10);

        if (correctRequest) {
            counterService.clear();
            response.setStatus(204);
        } else {
            response.setStatus(400);
        }
    }
}
