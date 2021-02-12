import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CounterServlet extends HttpServlet {

    private Counter counter = new Counter();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().print("Current counter: " + counter.getCurrentValue());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setStatus(HttpServletResponse.SC_OK);
        counter.postIncrement();
        resp.getWriter().print("Current counter: " + counter.getCurrentValue());
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String header = req.getHeader("Substraction-Value");
        if (header == null) {
            handleWrongDeleteHeader(resp);
        } else {
            String response = parseSubstractionValueHeader(header);
            if (response != null) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().print(response);
            } else {
                handleWrongDeleteHeader(resp);
            }
        }

    }

    private void handleWrongDeleteHeader(HttpServletResponse resp) throws IOException {
        resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        resp.getWriter().print("Missing Substarction-Value Header or wrong value");
    }

    private String parseSubstractionValueHeader(String header) {
        String response = null;
        try {
            Long decrementValue = Long.valueOf(header);
            counter.deleteDecrement(decrementValue);
            response = "Decrement value: " + decrementValue + ". Current counter: " + counter.getCurrentValue();
        } catch (NumberFormatException e) {
            System.out.println("Log: Wrong parameters again");
        }
        return response;
    }

}
