import java.io.IOException;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearServlet extends HttpServlet {
    private final CounterService service;

    private final String AUTH_COOKIE = "hh-auth";

    public ClearServlet() {
        service = CounterService.getInstance();
    }

    private boolean isAllowed(HttpServletRequest req) {

        Optional<String> cookie = Arrays.stream(Optional.ofNullable(req.getCookies()).orElse(new Cookie[0]))
                .filter(c -> AUTH_COOKIE.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
        return cookie.orElse("").length() > 10;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isAllowed(req)) {
            resp.getWriter().println("Forbidden");
            resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        service.reset();
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
