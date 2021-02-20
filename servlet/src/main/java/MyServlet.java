import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;

public class MyServlet extends HttpServlet {

    private int counter = 0;

    private String getCookie(Cookie[] cookies, String name) {
        String result = "";
        if (cookies != null) {
            for (Cookie c : cookies){
                if (c.getName().equals(name)) result =c.getValue();
            }
        }
        return result;
    }

    private void respCounter(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpStatus.OK_200);
        resp.getWriter().println(counter);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        respCounter(resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        if (req.getRequestURI().equals("/counter/clear")){
            String cookie = getCookie(req.getCookies(), "hh-auth");
            if (cookie.length() > 10) counter = 0;
        } else {
            counter += 1;
        }
        respCounter(resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int val;
        String str = req.getHeader("Subtraction-Value");
        if (str != null){
            val = Integer.parseInt(str);
            counter -= val;
        }
        respCounter(resp);
    }

}
