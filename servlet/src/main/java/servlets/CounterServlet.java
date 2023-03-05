package servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CounterService;

import java.io.IOException;

public class CounterServlet extends HttpServlet {
  private static final CounterService counterService = CounterService.getInstance();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    response.getWriter().print(counterService.getCounter());
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    counterService.incrementCounter();
  }

  @Override
  public void doDelete(HttpServletRequest request, HttpServletResponse response) {
    Long subtractionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
    counterService.subtractCounter(subtractionValue);
  }
}
