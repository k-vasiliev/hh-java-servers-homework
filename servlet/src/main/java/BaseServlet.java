import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseServlet extends HttpServlet {

    private final String DECREMENT_HEADER = "Subtraction-Value";

    private final CounterService service;

    public BaseServlet() {
        service = CounterService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(service.get());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        service.increment();
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int by;
        try {
            by = req.getIntHeader(DECREMENT_HEADER);
        } catch (java.lang.NumberFormatException e) {
            resp.getWriter().println("invalid decrement");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        if (by <= 0) {
            resp.getWriter().println("invalid decrement");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        service.decrementBy(by);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }
}
