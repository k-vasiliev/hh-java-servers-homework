import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
public class CounterServlet extends HttpServlet {

    private final Counter counter = Counter.getCounterInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        resp.getWriter().print(counter.getCounter());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        counter.incrementCounter();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String header = req.getHeader("Subtraction-Value");
        if (header == null) resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        else {
            counter.decrementCounter(Integer.parseInt(header));
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
