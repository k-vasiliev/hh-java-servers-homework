import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ClearCounterServlet extends HttpServlet {

    private Counter counter;

    @Override
    public void init() throws ServletException {
        this.counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Arrays.stream(req.getCookies()).forEach(
                    cookie -> System.out.println(cookie.getName() + " - " + cookie.getValue() + " - " + cookie.getPath())
            );
        } catch (NullPointerException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().print("Missing hh-auth cookies or wrong value");
        }
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        counter.clear();
    }

}
