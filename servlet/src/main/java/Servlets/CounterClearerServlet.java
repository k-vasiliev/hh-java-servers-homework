package Servlets;

import Counter.CounterClass;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class CounterClearerServlet extends HttpServlet {
    private final Integer HH_COOKIE_MIN_LENGTH = 10;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie hhCookie = getAuthCookie(req);
        PrintWriter writer = resp.getWriter();
        if (hhCookie != null && hhCookie.getValue().length() > HH_COOKIE_MIN_LENGTH){
            CounterClass.clearCounter();
            writer.print(CounterClass.getCounter());
        } else {
            writer.print("Counter didn't cleared");
            resp.setStatus(401);
        }
        writer.flush();
    }

    private Cookie getAuthCookie(HttpServletRequest req){
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("hh-auth")) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
