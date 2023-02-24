import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;

public class CounterClearerServlet extends HttpServlet {
  private static final int HH_AUTH_TOKEN_MIN_LENGTH = 11;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    if (req.getCookies() == null) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }

    boolean correctAuthCredentials = Arrays.stream(req.getCookies())
      .anyMatch(cookie ->
        Objects.equals(cookie.getName(), "hh-auth") &&
        cookie.getValue().length() >= HH_AUTH_TOKEN_MIN_LENGTH);

    if (correctAuthCredentials) {
      CounterService.clearCounter();
      PrintWriter writer = resp.getWriter();
      writer.print(0);
      writer.flush();
    } else {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}
