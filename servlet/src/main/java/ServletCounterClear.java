import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;

@WebServlet(urlPatterns = "/counter/clear")
public class ServletCounterClear extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!isAuthCookieValid(req)) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            PrintWriter writer = resp.getWriter();
            writer.print("auth cookies is invalid");
            writer.flush();
            return;
        }

        resp.setStatus(HttpServletResponse.SC_OK);
        Count.setValue(0);
        PrintWriter writer = resp.getWriter();
        writer.print(Count.getValue());
        writer.flush();
    }

    private boolean isAuthCookieValid(HttpServletRequest req) {
        return getAuthCookie(req)
                .map(cookie -> cookie.getValue().length() > 10)
                .orElse(false);
    }

    private Optional<Cookie> getAuthCookie(HttpServletRequest req) {
        if (req.getCookies() == null) {
            return Optional.empty();
        }

        return Arrays.stream(req.getCookies())
                .filter(cookie -> cookie.getName().equals("hh-auth"))
                .findFirst();
    }
}
