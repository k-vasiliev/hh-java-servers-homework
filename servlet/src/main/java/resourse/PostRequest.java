package resourse;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Counter;
import org.eclipse.jetty.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

public class PostRequest {
  private final HttpServletRequest req;
  private final HttpServletResponse resp;

  public PostRequest(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;
  }

  public void process(Counter counter) throws IOException {
    String path = req.getRequestURI();

    switch (path) {
      case "/counter":
        counter.increment();
        break;
      case "/counter/clear":
        if (checkAuthCookie(req.getCookies())) {
          counter.clear();
        } else {
          resp.setStatus(403);
        }
        break;
      default:
        PrintWriter writer = resp.getWriter();
        writer.print("Page not found.");
        writer.flush();
        resp.setStatus(404);
    }
  }

  private boolean checkAuthCookie(Cookie[] cookies) {
    for (var cookie : cookies) {
      if ("hh-auth".equals(cookie.getName())) {
        String hhAuth = cookie.getValue();
        if (StringUtil.isNotBlank(hhAuth) && hhAuth.length() > 10) {
          return true;
        }
      }
    }
    return false;
  }

}
