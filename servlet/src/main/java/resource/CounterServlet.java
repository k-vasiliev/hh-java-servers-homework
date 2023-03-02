package resource;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import storage.CounterStorage;

public class CounterServlet extends HttpServlet {

  private final CounterStorage counterStorage = CounterStorage.getInstance();
  private final static Long DEFAULT_SUBTRACTION_VALUE = 0L;
  private final static String HEADER_SUBTRACTION_VALUE = "Subtraction-Value";

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) {
    counterStorage.increment();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
    PrintWriter writer = res.getWriter();
    writer.print(counterStorage.getCounter());
    writer.flush();
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse res) {
    counterStorage.subtract(getSubtractionValue(req));
  }

  private Long getSubtractionValue(HttpServletRequest req) {
    return Optional
        .ofNullable(req.getHeader(HEADER_SUBTRACTION_VALUE))
        .map(Long::parseLong)
        .orElse(DEFAULT_SUBTRACTION_VALUE);
  }

}
