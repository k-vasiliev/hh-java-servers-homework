package ru.hh.school.utils;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Objects;

public class ServletCleaner extends HttpServlet {
  private static final int MIN_LENGTH_TOKEN = 10;

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    if (req.getCookies() == null) {
      resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return;
    }
    boolean correctAuthCredentials = Arrays.stream(req.getCookies())
        .anyMatch(cookie ->
            Objects.equals(cookie.getName(), "hh-auth") &&
                cookie.getValue().length() > MIN_LENGTH_TOKEN);

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
