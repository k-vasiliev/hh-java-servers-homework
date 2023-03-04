package controller;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CounterServlet extends HttpServlet {
    private static final String SUBTRACTION_HEADER_NAME = "Subtraction-Value";
    private final Counter counter = Counter.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(counter.getCounter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(counter.increment());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long delta = Long.parseLong(req.getHeader(SUBTRACTION_HEADER_NAME));
        resp.getWriter().println(counter.add(-delta));
    }
}
