import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {
  AtomicInteger counter;

  public CounterServlet() {
    this.counter = new AtomicInteger(0);
  }

  @Override
  public void init() throws ServletException {
    super.init();
    var context = this.getServletContext();
    context.setAttribute("storage", counter);
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    var writer = resp.getWriter();
    //вот здесь немного стремно кастовать объект, но для конкретно такой задачи это сработает
    counter = (AtomicInteger) getServletContext().getAttribute("storage");
    writer.println(counter.get());
    writer.flush();
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    counter = (AtomicInteger) getServletContext().getAttribute("storage");
    counter.getAndIncrement();
  }

  @Override
  protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    counter = (AtomicInteger) getServletContext().getAttribute("storage");
    //нам могут какое-то кривое value отправить же?
    var header = req.getHeader("Subtraction-Value");
    if (header.chars().allMatch(Character::isDigit)) {
      var value = Integer.parseInt(header);
      counter.getAndAdd(-1 * value);
    } else {
      resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
    }
  }
}
