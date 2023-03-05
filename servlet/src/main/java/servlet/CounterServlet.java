package servlet;

import dao.CounterDao;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet(urlPatterns = "/counter")
public class CounterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = req.getServletContext();
        PrintWriter writer = resp.getWriter();
        CounterDao counterDao = ((CounterDao) context.getAttribute("CounterDao"));
        writer.print(counterDao.get());
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Writer out = resp.getWriter();
        ServletContext context = req.getServletContext();
        CounterDao counterDao = ((CounterDao) context.getAttribute("CounterDao"));
        try{
            counterDao.increment();
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
        CounterDao counterDao = ((CounterDao) context.getAttribute("CounterDao"));
        try {
            long delta = Long.parseLong(req.getHeader("Subtraction-Value"));
            counterDao.subtract(delta);
        } catch (NumberFormatException | ArithmeticException e){
            if(e instanceof NumberFormatException) {
                resp.setStatus(400);
                out.flush();
            } else {
                resp.setStatus(409);
                out.write("Impossible to subtract this value");
                out.flush();
            }
        }
    }
}
