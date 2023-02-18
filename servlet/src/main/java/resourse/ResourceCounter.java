package resourse;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Counter;

import java.io.IOException;

@WebServlet
public class ResourceCounter extends HttpServlet {
  private final static Counter counter = new Counter();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    GetRequest getRequest = new GetRequest(req, resp);
    getRequest.process(counter);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    PostRequest postRequest = new PostRequest(req, resp);
    postRequest.process(counter);
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    DeleteRequest deleteRequest = new DeleteRequest(req, resp);
    deleteRequest.process(counter);
  }
}
