package resourse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.Counter;
import org.eclipse.jetty.util.StringUtil;

import java.io.IOException;
import java.io.PrintWriter;

public class DeleteRequest {
  private final HttpServletRequest req;
  private final HttpServletResponse resp;

  public DeleteRequest(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;
  }

  public void process(Counter counter) throws IOException {
    String path = req.getRequestURI();
    PrintWriter writer = resp.getWriter();

    switch (path) {
      case "/counter":
        String deltaValue = req.getHeader("Subtraction-Value");
        if (StringUtil.isNotBlank(deltaValue)) {
          try {
            long delta = Long.parseLong(deltaValue);
            counter.decrement(delta);
          } catch (NumberFormatException err) {
            writer.print("Bad number format.");
            resp.setStatus(400);
          }
        }
        break;
      default:
        writer.print("Page not found.");
        resp.setStatus(404);
    }
    writer.flush();
  }
}
