package counter.resource;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@Provider
public class LogFilter implements ContainerRequestFilter {

  private final Logger log = LoggerFactory.getLogger(LogFilter.class);

  @Override
  public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    log.info("[{}] {}", containerRequestContext.getMethod(),
        containerRequestContext.getUriInfo().getRequestUri());
  }

}
