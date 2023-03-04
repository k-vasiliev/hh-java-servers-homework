package servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        PrintWriter writer = resp.getWriter();
        AtomicLong counter = ((AtomicLong) context.getAttribute("Counter"));
        writer.print(counter.get());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer out = resp.getWriter();
        ServletContext context = req.getServletContext();
        AtomicLong counter = ((AtomicLong) context.getAttribute("Counter"));
        try{
            counter.accumulateAndGet(1L, Math::addExact);
        } catch (ArithmeticException e){
            resp.setStatus(409);
            out.write("Increment not possible");
            out.flush();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer out = resp.getWriter();
        ServletContext context = req.getServletContext();
        AtomicLong counter = ((AtomicLong) context.getAttribute("Counter"));
        try {
            Long delta = Long.parseLong(req.getHeader("Subtraction-Value"));
            if (delta.equals(Long.MIN_VALUE)){
                throw new NumberFormatException();
            }
            counter.accumulateAndGet( - delta, Math::addExact);
        } catch (NumberFormatException | ArithmeticException e){
            if(e instanceof NumberFormatException) {
                resp.setStatus(400);
                out.write("Incorrect value of 'Subtraction-Value' header");
                out.flush();
            } else {
                resp.setStatus(409);
                out.write("Impossible to subtract this value");
                out.flush();
            }
        }
    }
}
