package servlets;

import service.CounterService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CounterOperationsServlet extends HttpServlet {
    private final CounterService service;

    public CounterOperationsServlet() {
        this.service = CounterService.getInstance();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int count = service.getCount();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(count);
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.incrementByOne();
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Counter successfully incremented by 1.");
        response.setStatus(HttpServletResponse.SC_OK);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int n = Integer.parseInt(request.getParameter("subtraction"));
        service.decrementByN(n);
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println("Counter successfully decremented by " + n);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
