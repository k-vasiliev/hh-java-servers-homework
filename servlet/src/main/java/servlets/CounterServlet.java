package servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Counter;

import java.io.IOException;

public class CounterServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.getWriter().println(Counter.getInstance().getCount());
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        Counter.getInstance().increase();
        response.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int subtractionValue = Integer.parseInt(request.getHeader("Subtraction-Value"));
            Counter.getInstance().decreaseBy(subtractionValue);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch(NumberFormatException exception) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            exception.printStackTrace(response.getWriter());
        }
    }
}
