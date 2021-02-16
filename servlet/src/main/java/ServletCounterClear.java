import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/counter/clear")
public class ServletCounterClear extends HttpServlet {

    private Count count = new Count();

    @Override
    public void init() throws ServletException {
        this.count = (Count) getServletContext().getAttribute("count");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        var cookies = req.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("hh-auth") && c.getValue().length() > 10) {
                resp.setStatus(HttpServletResponse.SC_OK);
                count.setValue(0);
                return;
            }
        }

        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = resp.getWriter();
        writer.print("auth cookies not found");
        writer.flush();
    }
}
