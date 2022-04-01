package servlet;

import entity.Counter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClearCounterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        Counter.set(0);
        resp.setStatus(HttpServletResponse.SC_RESET_CONTENT);
    }

}
