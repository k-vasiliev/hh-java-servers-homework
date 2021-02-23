import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CounterClearServlet extends HttpServlet {

    private final Counter counter = Counter.getCounterInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (isAuth(req)) {
            counter.resetCounter();
            resp.setStatus(HttpServletResponse.SC_OK);
        } else {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private boolean isAuth(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies == null) {
            return false;
        }
        for (Cookie cookie : cookies) {
            return cookie.getName().equals("hh-auth") && cookie.getValue().length() > 10;
        }
        return false;
    }
}
