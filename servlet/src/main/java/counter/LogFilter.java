package counter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LogFilter implements Filter {

  private static final Logger log = LoggerFactory.getLogger(LogFilter.class);

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws ServletException, IOException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    log.info("[{}] {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURL());
    chain.doFilter(request, response);
  }

}
