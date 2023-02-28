import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = {"/status", "/counter", "/counter/clear"})
public class CounterServlet extends HttpServlet {
  private AtomicInteger counter;

  public void init() {
    counter = new AtomicInteger(0);
  }

  public boolean cookieIsValid(Cookie[] cookies) {
    return Objects.nonNull(cookies) &&
      cookies[0].getValue().length() >= 10;
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/plain");
    PrintWriter writer = resp.getWriter();

    switch (req.getRequestURI()) {
      case "/status" -> writer.print(resp.getStatus());
      case "/counter" -> {
        System.out.println("Get request.");
        System.out.println("Counter value: " + counter);
        System.out.println("------------------");
        writer.print(counter);
      }
    }
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/plain");
    PrintWriter writer = resp.getWriter();

    switch (req.getRequestURI()) {
      case "/counter" -> {
        System.out.println("Post request.");
        System.out.println("Counter before increase: " + counter.get());
        writer.print(counter.incrementAndGet());
        System.out.println("Counter after increase: " + counter.get());
        System.out.println("------------------");
      }

      case "/counter/clear" -> {
        System.out.println("Clear request.");

        if (cookieIsValid(req.getCookies())) {
          System.out.println("Cookie name: " + req.getCookies()[0].getName());
          System.out.println("Cookie value: " + req.getCookies()[0].getValue());
          counter.getAndSet(0);
          System.out.println("Counter decreased!");
        } else {
          resp.setStatus(400);
          System.out.println("Counter did not decreased!");

        }
        writer.print(counter);
        writer.flush();
      }
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/plain");
    PrintWriter writer = resp.getWriter();

    int headerValue = Integer.parseInt(req.getHeader("Subtraction-Value"));
    System.out.println("Delete request.");
    System.out.println("Counter before delete: " + counter);
    counter.set(counter.get() - headerValue);
    System.out.println("Counter after delete: " + counter);
    System.out.println("--------------------");
    writer.print(counter);
  }
}
