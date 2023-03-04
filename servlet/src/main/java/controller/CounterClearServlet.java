package controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CounterClearServlet extends HttpServlet {
    private static final String COOKIE_NAME = "hh-auth";
    private final Counter counter = Counter.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Optional<Cookie[]> cookies = Optional.ofNullable(req.getCookies());
        if (cookies.isPresent() &&
            Arrays.stream(cookies.get()).anyMatch(c -> c.getName().equals(COOKIE_NAME) && c.getValue().length() > 10)) {
            resp.getWriter().println(counter.reset());
        } else {
            resp.setStatus(HttpStatus.FORBIDDEN_403);
        }
    }
}
