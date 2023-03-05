package filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebFilter(urlPatterns = "/counter/clear")
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        Cookie[] cookies = ((HttpServletRequest) req).getCookies();
        if (getAuthCookie(cookies)
                .filter(cookie -> cookie.getValue().length() > 10)
                .isPresent()){
            chain.doFilter(req, resp);
        } else {
            ((HttpServletResponse) resp).setStatus(401);
        }
    }

    private Optional<Cookie> getAuthCookie(Cookie[] cookies){
        String cookieName = "hh-auth";
        Cookie cookie = null;
        if(cookies !=null) {
            for(Cookie c: cookies) {
                if(cookieName.equals(c.getName())) {
                    cookie = c;
                    break;
                }
            }
        }
        return Optional.ofNullable(cookie);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
