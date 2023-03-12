package servlets;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Counter;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ClearCounterServlet extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        Stream<Cookie> cookieStream = Optional.ofNullable(request.getCookies()).map(Arrays::stream).orElse(null);
        if (cookieStream == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        Cookie keyCookie = cookieStream.filter(cookie -> Objects.equals(cookie.getName(), "hh-auth")).findFirst().orElse(null);
        if (keyCookie == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int keyCookieDescriptionLength = keyCookie.getValue().length();
        if (keyCookieDescriptionLength < 10) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else {
            Counter.getInstance().clear();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }
}
