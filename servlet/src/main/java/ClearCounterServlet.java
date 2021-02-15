import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ClearCounterServlet extends HttpServlet {

    private Counter counter;
    private final String hhAuthCookie = "hh-auth";

    @Override
    public void init() throws ServletException {
        this.counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (isAuthCookieCorrect(req)) {
            resp.setStatus(HttpServletResponse.SC_OK);
            counter.clear();
        } else {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().print("Missing hh-auth cookie or wrong value");
        }
    }

    private boolean isAuthCookieCorrect(HttpServletRequest req) {
        Optional<Cookie> hhAuthCookie = getAuthCookie(req);
        return !hhAuthCookie.isEmpty() && hhAuthCookie.get().getValue().length() >= 10;
    }

    private Optional<Cookie> getAuthCookie(HttpServletRequest req) {
        if (req.getCookies() == null) return Optional.empty();
        return Arrays.stream(req.getCookies())
                .peek(cookie -> System.out.println(cookie.getName() + cookie.getValue()))
                .filter(cookie -> cookie.getName().equals(hhAuthCookie))
                .findFirst();
    }
}
