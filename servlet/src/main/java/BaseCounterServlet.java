import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public abstract class BaseCounterServlet extends HttpServlet {
    private Counter counter;

    @Override
    public void init() throws ServletException {
        super.init();
        counter = (Counter) getServletContext().getAttribute("counter");
    }

    protected Counter getCounter() {
        return counter;
    }
}