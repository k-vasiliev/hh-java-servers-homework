import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CounterServlet extends HttpServlet {

    public static final String SUBTRUCTION_VALUE_HEADER_NAME = "Subtraction-Value";

    private Counter counter;

    @Override
    public void init() throws ServletException {
        counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(counter.get());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter.increment();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            counter.subtract(Integer.parseInt(req.getHeader(SUBTRUCTION_VALUE_HEADER_NAME)));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
        catch (NumberFormatException e) {
            resp.getWriter().println("Bad Subtraction Value");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
