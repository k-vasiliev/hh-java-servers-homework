package servlets;

import counter.Counter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CounterPathServlet extends HttpServlet {

    public static final String SUBTRUCTION_VALUE_HEADER = "Subtraction-Value";

    private Counter counter;

    @Override
    public void init() throws ServletException {
        counter = (Counter) getServletContext().getAttribute("counter");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(counter.getValue());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        counter.increment();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(counter.getValue());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            counter.subtractValue(Integer.parseInt(req.getHeader(SUBTRUCTION_VALUE_HEADER)));
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(counter.getValue());
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().println("Missing Subtraction-Value Header or something wrong!");
        }
    }
}
