import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CounterServlet extends BaseCounterServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print(getCounter().get());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.print(getCounter().increment());
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        try {
            int val = Integer.parseInt(req.getHeader("Subtraction-Value"));
            writer.print(getCounter().decrement(val));
        } catch (NumberFormatException e) {
            writer.print("Invalid header Subtraction-Value");
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        writer.flush();
    }
}
