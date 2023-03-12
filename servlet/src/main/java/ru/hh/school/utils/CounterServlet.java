package ru.hh.school.utils;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter writer = resp.getWriter();
    writer.print(CounterService.getCounter());
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PrintWriter writer = resp.getWriter();
    writer.print(CounterService.incrementCounter());
    writer.flush();
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
    try {
      int subtractionValue = Integer.parseInt(req.getHeader("Subtraction-Value"));
      CounterService.decrementCounter(subtractionValue);
      resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    } catch (NumberFormatException e) {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}