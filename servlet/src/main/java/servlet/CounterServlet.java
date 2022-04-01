package servlet;

import entity.Counter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CounterServlet extends HttpServlet {

    public static final String SUBTRACTION_VALUE_HEADER = "Subtraction-Value";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(Counter.get());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(Counter.incrementAndGet());
        resp.setStatus(HttpServletResponse.SC_CREATED);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String subtractionValue = req.getHeader(SUBTRACTION_VALUE_HEADER);
        if (subtractionValue == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, SUBTRACTION_VALUE_HEADER + " header is required");
        } else {
            Integer subValueInt = null;
            try {
                subValueInt = Integer.parseInt(subtractionValue);
            } catch (Throwable ignored) {
            }
            //вроде в задании нет требований про валидацию, но, мне кажется, лучше это сделать
            //и отрицательные значения отпинывать, если очень хочется увеличивать счетчик больше чем на 1, то лучше параметр в post-запрос добавить
            if (subValueInt == null || subValueInt < 0) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, SUBTRACTION_VALUE_HEADER + " header must contain positive integer");
            } else if (Counter.get() < subValueInt) {
                resp.sendError(HttpServletResponse.SC_CONFLICT, SUBTRACTION_VALUE_HEADER + " header contains value greater then current counter value");
            } else {
                Counter.addAndGet(-subValueInt);
                resp.setStatus(HttpServletResponse.SC_OK);
            }
        }
    }

}
