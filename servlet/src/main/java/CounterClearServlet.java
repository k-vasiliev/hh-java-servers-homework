import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CounterClearServlet extends HttpServlet {

    private Counter counter;

    @Override
    public void init() throws ServletException {
        counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthorized(req)) {
            counter.set(0);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    private boolean isAuthorized(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null)
            return false;

        Optional<Cookie> auth = Arrays.stream(cookies)
            .filter((cookie) -> cookie.getName().equals("hh-auth") && cookie.getValue().length() > 10)
            .findAny();

        return auth.isPresent();
    }
}
