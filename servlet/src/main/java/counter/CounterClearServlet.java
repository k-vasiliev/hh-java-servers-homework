package counter;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CounterClearServlet extends HttpServlet {

  private static final InMemoryStorage storage = InMemoryStorage.getInstance();

  private static final Logger log = LoggerFactory.getLogger(CounterClearServlet.class);

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Optional<Cookie[]> authCookie =  Optional.ofNullable(request.getCookies())
        .filter(s -> Arrays.stream(s).filter(cookie -> cookie.getName().equals("hh-auth"))
            .anyMatch(cookie -> cookie.getValue().length() > 10));
    if (authCookie.isPresent()) {
      response.setContentType("application/text");
      response.setStatus(HttpServletResponse.SC_OK);
      response.getWriter().println(storage.clearCounter());
    } else {
      response.sendError(400);
    }
  }

}
