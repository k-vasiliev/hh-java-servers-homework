import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class CounterClearerServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    if (req.getCookies() == null) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    Optional<Cookie> hhAuthCookie = Arrays.stream(req.getCookies())
      .filter(cookie -> Objects.equals(cookie.getName(), "hh-auth"))
      .filter(cookie -> cookie.getValue().length() > 10)
      .findFirst();

    if (hhAuthCookie.isPresent()) {
      CounterService.clearCounter();
      PrintWriter writer = resp.getWriter();
      writer.print(0);
      writer.flush();
    } else {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
