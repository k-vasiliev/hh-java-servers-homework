package servlets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.CounterService;

import java.util.Arrays;

public class CounterClearServlet extends HttpServlet {

  private static final CounterService counterService = CounterService.getInstance();

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      response.setStatus(400);
      return;
    }
    Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals("hh-auth"))
        .map(Cookie::getValue)
        .filter(cookieValue -> cookieValue.length() > 10)
        .findFirst().ifPresentOrElse(
            cookieValue -> counterService.resetCounter(),
            () -> response.setStatus(400));
  }
}
