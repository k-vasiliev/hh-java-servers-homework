package counter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class CounterServlet extends HttpServlet {

  private final InMemoryStorage storage;

  private static final Logger log = LoggerFactory.getLogger(CounterServlet.class);

  public CounterServlet() {;
    storage = InMemoryStorage.getInstance();
  }

  public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
    throws ServletException, IOException {
    logUrlInfo(request);
    if (request.getRequestURI().equals("/counter")) {
      setSuccessResponseParam(response);
      response.getWriter().println(storage.getCounter());
    } else {
      response.sendError(400);
    }
  }

  public void doPost(HttpServletRequest request,
                    HttpServletResponse response)
      throws ServletException, IOException {
    logUrlInfo(request);
    setSuccessResponseParam(response);
    if (request.getRequestURI().equals("/counter")) {
      response.getWriter().println(storage.increaseCounter());
    } else {
      Optional<Cookie> authCookie =  Optional.ofNullable(request.getCookies())
          .stream()
          .flatMap(Arrays::stream)
          .filter(cookie -> cookie.getName().equals("hh-auth"))
          .filter(cookie -> cookie.getValue().length() > 10)
          .findFirst();
      if (authCookie.isPresent()) {
        response.getWriter().println(storage.clearCounter());
      } else {
        response.sendError(400);
      }
    }
  }

  public void doDelete(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    logUrlInfo(request);
    if (request.getRequestURI().equals("/counter")) {
      long reductionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
      setSuccessResponseParam(response);
      response.getWriter().println(storage.reductionCounter(reductionValue));
    } else {
      response.sendError(400);
    }
  }

  private void logUrlInfo(HttpServletRequest request) {
    log.info("{} {}", request.getMethod(), request.getRequestURI());
  }

  private void setSuccessResponseParam(HttpServletResponse response) {
    response.setContentType("application/text");
    response.setStatus(HttpServletResponse.SC_OK);
  }

}
