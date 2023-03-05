package resource;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.StorageCounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class CounterServlet extends HttpServlet {
  private final StorageCounter storageCounter = StorageCounter.getInstance();

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    writer(response, storageCounter.getCounter());
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    response.setStatus(201);
    writer(response, storageCounter.increment());
  }

  @Override
  protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException{
    Long decrementValue = Optional
        .ofNullable(request.getHeader("Subtraction-Value"))
        .map(Long::parseLong)
        .orElse(0l);

    writer(response, storageCounter.decrement(decrementValue));
  }

  private void writer(HttpServletResponse response, long value) throws IOException {
    PrintWriter writer = response.getWriter();
    writer.print(value);
    writer.flush();
  }
}
