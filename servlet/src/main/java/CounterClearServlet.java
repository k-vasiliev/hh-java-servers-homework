import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;

public class CounterClearServlet extends HttpServlet {
  private static final int COOKIE_MIN_LENGTH = 10;
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    if (request.getCookies() == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    boolean authCookie = Arrays.stream(request.getCookies())
        .anyMatch(cookie -> Objects.equals(cookie.getName(), "hh-auth") &&
            cookie.getValue().length() > COOKIE_MIN_LENGTH);

    if (authCookie) {
      CounterService.clearCounter();
      PrintWriter writer = response.getWriter();
      writer.print(0);
      writer.flush();

    } else response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
  }
}
