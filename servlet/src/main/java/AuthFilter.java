import java.io.IOException;

import javax.security.sasl.AuthenticationException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter implements Filter {

    private void validateCookies(HttpServletRequest request) throws AuthenticationException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new AuthenticationException("Cookies don't exist");
        }
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("hh-auth")) {
                if (cookie.getValue().length() > 10) {
                    return;
                }
                break;
            }
        }
        throw new AuthenticationException("Auth cookie not found");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        try {
            validateCookies((HttpServletRequest)request);
            chain.doFilter(request, response);
        } catch (AuthenticationException e) {
            response.getWriter().write("Unauthorized");
            ((HttpServletResponse)response).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
