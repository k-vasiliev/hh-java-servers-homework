import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = {"/status", "/counter"})
public class CounterServlet extends HttpServlet {

  private AtomicInteger counter;

  public void init() {
    counter = new AtomicInteger();
  }

  public boolean isCookieValid(Cookie[] cookies) {
    return Objects.nonNull(cookies) &&
      cookies[0].getValue().length() > 10;
  }

  public PrintWriter getPrintWriter(HttpServletResponse resp) throws IOException {
    resp.setContentType("text/plain");
    return resp.getWriter();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter writer = getPrintWriter(resp);

    switch (req.getRequestURI()) {
      case "/status" -> writer.print(resp.getStatus());
      case "/counter" -> writer.print(counter);
    }
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter writer = getPrintWriter(resp);

    switch (req.getRequestURI()) {
      case "/counter" -> writer.print(counter.incrementAndGet());
      case "/counter/clear" -> {
        if (isCookieValid(req.getCookies())) {
          counter.getAndSet(0);
          writer.print(counter);
        }
        else resp.setStatus(400);
      }
    }
    writer.flush();
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter writer = getPrintWriter(resp);

    int headerValue = Integer.parseInt(req.getHeader("Subtraction-Value"));
    for (int i = 0; i < headerValue; i++) {
      counter.decrementAndGet();
    }
    writer.print(counter);
    writer.flush();
  }

}
