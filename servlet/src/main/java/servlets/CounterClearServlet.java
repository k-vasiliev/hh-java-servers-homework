package servlets;

import service.CounterService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CounterClearServlet extends HttpServlet {
    private final CounterService service;

    public CounterClearServlet() {
        this.service = CounterService.getInstance();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (cookieCheck(request.getCookies())) {
            service.reset();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("Counter successfully reset.");
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else {
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().println("\"hh-auth\" cookie required.");
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    public boolean cookieCheck(Cookie[] cookies) {
        for (Cookie c : cookies) {
            if (c.getName().equalsIgnoreCase("hh-auth")) {
                return c.getValue().length() >= 10;
            }
        }
        return false;
    }
}
