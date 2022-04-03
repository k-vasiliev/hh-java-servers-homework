import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/counter")
public class ServletCounter extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var response = resp.getWriter();
        response.print(Counter.getCount());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Counter.setCount(Counter.getCount() + 1);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        var subtractionValue = req.getParameter("Subtraction-Value");
        if (subtractionValue != null) {
            try {
                Counter.setCount(Integer.parseInt(subtractionValue));
            } catch (NumberFormatException e) {
                System.out.println(e);
            }

        }
    }
}
