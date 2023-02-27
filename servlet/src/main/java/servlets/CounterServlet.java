package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CounterService;

import java.io.IOException;

@WebServlet
public class CounterServlet extends HttpServlet {
    private static final CounterService counterService = CounterService.getInstance();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(counterService.getCounter());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        counterService.increment();
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        long reductionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
        counterService.substract(reductionValue);
    }
}

