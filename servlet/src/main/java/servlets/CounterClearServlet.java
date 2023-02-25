package servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CounterService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

@WebServlet
public class CounterClearServlet extends HttpServlet {
    private static final CounterService counterService = CounterService.getInstance();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Optional<Cookie[]> authCookie = Optional.ofNullable(request.getCookies())
                .filter(cookies ->
                        Arrays.stream(cookies)
                                .filter(cookie -> cookie.getName().equals("hh-auth"))
                                .anyMatch(cookie -> cookie.getValue().length() > 10));

        if (authCookie.isPresent()) {
            counterService.clearCounter();
        }else {
            response.sendError(400);
        }
    }
}
