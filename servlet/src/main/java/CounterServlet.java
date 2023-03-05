import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter writer = response.getWriter();
    writer.print(CounterService.getCounter());
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    PrintWriter writer = response.getWriter();
    writer.print(CounterService.incrementCounter());
    writer.flush();
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
    try {
      int subtractionValue = Integer.parseInt(request.getHeader("Subtraction-Value"));
      CounterService.decrementCounter(subtractionValue);
      response.setStatus(HttpServletResponse.SC_NO_CONTENT);
    } catch (NumberFormatException e) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
