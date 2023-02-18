package resourse;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.util.StringUtil;
import service.CounterService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet
public class ResourceCounter extends HttpServlet {
  private final CounterService counterService;

  public ResourceCounter() {
    this.counterService = CounterService.getInstance();
  }

  private static void showNotFoundError(HttpServletResponse resp, PrintWriter writer) {
    writer.print("Page not found.");
    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String path = req.getRequestURI();
    PrintWriter writer = resp.getWriter();

    switch (path) {
      case "/counter":
        writer.print(counterService.getCounterValue());
        break;
      case "/status":
        writer.print("I'm a live.");
        break;
      default:
        showNotFoundError(resp, writer);
    }
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String path = req.getRequestURI();

    switch (path) {
      case "/counter":
        counterService.incrementCounter();
        break;
      case "/counter/clear":
        if (isAuthCookie(req.getCookies())) {
          counterService.clearCounter();
        } else {
          resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        break;
      default:
        PrintWriter writer = resp.getWriter();
        showNotFoundError(resp, writer);
        writer.flush();
    }
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    String path = req.getRequestURI();
    PrintWriter writer = resp.getWriter();

    switch (path) {
      case "/counter":
        String deltaValue = req.getHeader("Subtraction-Value");
        if (StringUtil.isNotBlank(deltaValue)) {
          try {
            long delta = Long.parseLong(deltaValue);
            counterService.decrementCounter(delta);
          } catch (NumberFormatException err) {
            writer.print("Bad number format.");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
          }
        }
        break;
      default:
        showNotFoundError(resp, writer);
    }
    writer.flush();
  }

  private boolean isAuthCookie(Cookie[] cookies) {
    return Arrays.stream(cookies)
        .filter(cookie ->
            "hh-auth".equals(cookie.getName())
                && StringUtil.isNotBlank(cookie.getValue())
                && cookie.getValue().length() > 10
        ).findFirst()
        .isPresent();
  }
}
