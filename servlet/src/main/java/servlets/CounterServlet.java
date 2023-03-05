package servlets;

import dao.Counter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;
import java.io.PrintWriter;

public final class CounterServlet extends HttpServlet {
    private static final Counter counter = Counter.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");

        try (final PrintWriter out = resp.getWriter()) {
            out.println(counter.get());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        counter.increment();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        final String decrement = req.getHeader("Subtraction-Value");

        if (decrement == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            return;
        }

        counter.decrement(Long.parseLong(decrement));
    }
}
