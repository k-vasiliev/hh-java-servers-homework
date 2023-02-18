package resourse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.Counter;

import java.io.IOException;
import java.io.PrintWriter;

public class GetRequest {
  private final HttpServletRequest req;
  private final HttpServletResponse resp;

  public GetRequest(HttpServletRequest req, HttpServletResponse resp) {
    this.req = req;
    this.resp = resp;
  }

  public void process(Counter counter) throws IOException {
    String path = req.getRequestURI();
    PrintWriter writer = resp.getWriter();

    switch (path) {
      case "/status":
        writer.print("I'm a live.");
        break;
      case "/counter":
        writer.print(counter.get());
        break;
      default:
        writer.print("Page not found.");
        resp.setStatus(404);
    }
    writer.flush();
  }

}
