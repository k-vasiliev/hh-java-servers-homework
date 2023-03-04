import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/counter/clear")
public class ClearServlet extends HttpServlet {
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    if (req.getCookies() != null) {
      var cookies = req.getCookies();
      var value = Arrays.stream(cookies)
          .filter(cookie -> cookie.getName().equals("hh-auth"))
          .findFirst().orElse(new Cookie("hh-auth", "short")).getValue();
      if (value.length() < 10) {
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      } else {
        var counter = (AtomicInteger) getServletContext().getAttribute("storage");
        counter.getAndSet(0);
      }
    } else {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }
}
