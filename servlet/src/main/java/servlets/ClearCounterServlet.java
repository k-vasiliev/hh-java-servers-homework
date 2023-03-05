package servlets;

import dao.Counter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.util.Arrays;

public final class ClearCounterServlet extends HttpServlet {
    private static final Counter counter = Counter.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (req.getCookies() == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        boolean isCookieValid = Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("hh-auth"))
                .anyMatch(cookie -> cookie.getValue().length() >= 10);

        if (!isCookieValid) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        counter.clear();
    }
}
