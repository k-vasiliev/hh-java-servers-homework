import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {
    CounterService counterService = CounterService.getInstance();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.print(counterService.getCount());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        counterService.incrementCounter();
        response.setStatus(204);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
            int value = Integer.parseInt(request.getHeader("Subtraction-Value"));
            counterService.subtractCounter(value);
            response.setStatus(204);
    }




}