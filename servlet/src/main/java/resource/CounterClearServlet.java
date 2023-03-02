package resource;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import storage.CounterStorage;

public class CounterClearServlet extends HttpServlet {

  private final CounterStorage counterStorage = CounterStorage.getInstance();
  private final String HH_AUTH = "hh-auth";
  private final int LENGTH_HH_AUTH = 10;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
    if (hasAuth(req)) {
      counterStorage.reset();
    } else {
      res.sendError(401, "UNAUTHORIZED");
    }
  }

  private boolean hasAuth(HttpServletRequest req) {
    Cookie[] cookies = req.getCookies();
    if (Objects.isNull(cookies)) {
      return false;
    }
    return Arrays.stream(cookies)
        .filter(cookie -> HH_AUTH.equals(cookie.getName()))
        .anyMatch(cookie -> cookie.getValue().length() > LENGTH_HH_AUTH);
  }
}
