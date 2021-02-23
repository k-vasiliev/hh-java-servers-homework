import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {

    private int counter = 0;

    private Cookie getCookieByName(Cookie[] cookies, String cookieName) {
        if(cookies != null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    return c;
                }
            }
        }
        return null;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println("counter = " + counter);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null) {
            counter++;
        } else if (pathInfo.equals("/clear")) {
            Cookie cookie = getCookieByName(req.getCookies(), "hh-auth");
            if (cookie.getValue().length() > 10) {
                counter = 0;
            }
        } else {
            super.doPost(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = req.getHeader("Subtraction-Value");
        if (header != null) {
            counter -= Integer.parseInt(header);
        }
    }
}
