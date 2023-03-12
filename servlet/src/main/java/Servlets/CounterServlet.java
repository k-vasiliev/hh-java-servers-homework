package Servlets;

import Counter.CounterClass;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CounterServlet extends HttpServlet {

  CounterClass counterClass = new CounterClass();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    writer.print(counterClass.getCounter());
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    counterClass.incrementCounter();
    PrintWriter writer = resp.getWriter();
    writer.print(counterClass.getCounter());
    writer.flush();
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    Long subtractionValue = Long.parseLong(req.getHeader("Subtraction-Value"));
    counterClass.subtractCounter(subtractionValue);
    PrintWriter writer = resp.getWriter();
    writer.print(counterClass.getCounter());
    writer.flush();
  }
}
