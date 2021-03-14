package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import java.io.IOException;

public class ServletCounter extends HttpServlet {

  private final Counter counter = new Counter();

  private String getCookie(Cookie[] cookies, String name) {
    String result = null;
    if (cookies != null) {
      for (Cookie c : cookies) {
        if (c.getName().equals(name)) result = c.getValue();
      }
    }
    return result;
  }

  private Boolean isCorrectAuth(HttpServletRequest req) {
    String cookie = getCookie(req.getCookies(), "hh-auth");
    Boolean result = cookie.length() > 10;
    return result;
  }

  private void respCounter(HttpServletResponse resp) throws IOException {
    resp.setStatus(HttpStatus.OK_200);
    resp.getWriter().println(counter.getInstance().getValue());
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    respCounter(resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    if (req.getRequestURI().equals("/counter/clear")) {
      if (isCorrectAuth(req)) counter.getInstance().resetValue();
    } else {
      counter.getInstance().increment();
    }
    respCounter(resp);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    int val;
    String str = req.getHeader("Subtraction-Value");
    if (str != null) {
      try {
        val = Integer.parseInt(str);
        counter.getInstance().addValue(-val);
      } catch (NumberFormatException e) {
        System.out.println("Передано неверное значение");
      }
    }
    respCounter(resp);
  }

}
