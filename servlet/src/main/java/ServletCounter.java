import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/counter")
public class ServletCounter extends HttpServlet {

    private Count count;

    @Override
    public void init() throws ServletException {
        this.count = (Count) getServletContext().getAttribute("count");
    }

    final String SUBTRACTION_HEADER = "Subtraction-Value";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter writer = resp.getWriter();
        writer.print(count.getValue());
        writer.flush();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        count.inc();
        PrintWriter writer = resp.getWriter();
        writer.print(count.getValue());
        writer.flush();
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int reducer = 0;
        try {
            reducer = Integer.parseInt(req.getHeader(SUBTRACTION_HEADER));
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        count.setValue(count.getValue() - reducer);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
