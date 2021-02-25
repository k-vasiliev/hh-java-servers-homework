import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_OK;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {

    private static Counter counter;
    @Override
    public void init() {
       counter = new Counter();
    }

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("doGet");
        writer.println(Counter.getValue());
        response.setStatus(SC_OK);
        writer.println(response.getStatus());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("doPost");
        Counter.inc();
        writer.println(Counter.getValue());
        response.setStatus(SC_OK);
        writer.println(response.getStatus());
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();
        writer.println("doDelete");
        Counter.sub(Integer.parseInt(request.getHeader("Subtraction-Value")));
        response.setStatus(SC_OK);
        writer.println(response.getStatus());
        writer.flush();
    }

}
