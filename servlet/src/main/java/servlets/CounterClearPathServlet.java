package servlets;

import counter.Counter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CounterClearPathServlet extends HttpServlet {

    public static final String AUTH_COOKIE_NAME = "hh-auth";

    private Counter counter;

    @Override
    public void init() throws ServletException {
        counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthorized(req)) {
            counter.setValue(0);
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean isAuthorized(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null)
            return false;

        return Arrays.stream(cookies)
                .filter((cookie) -> AUTH_COOKIE_NAME.equals(cookie.getName()) &&
                        cookie.getValue().length() > 10)
                .findAny()
                .isPresent();
    }
}
