package counter;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class CounterServlet extends HttpServlet {

  private static final InMemoryStorage storage = InMemoryStorage.getInstance();

  private static final Logger log = LoggerFactory.getLogger(CounterServlet.class);

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    setSuccessResponseParam(response);
    response.getWriter().println(storage.getCounter());
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    setSuccessResponseParam(response);
    response.getWriter().println(storage.increaseCounter());
  }

  @Override
  public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
    long reductionValue = Long.parseLong(request.getHeader("Subtraction-Value"));
    setSuccessResponseParam(response);
    response.getWriter().println(storage.reductionCounter(reductionValue));
  }

  private void setSuccessResponseParam(HttpServletResponse response) {
    response.setContentType("application/text");
    response.setStatus(HttpServletResponse.SC_OK);
  }

}
