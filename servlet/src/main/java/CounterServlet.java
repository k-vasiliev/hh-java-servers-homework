
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Arrays;

public class CounterServlet extends HttpServlet {
    private CounterService serviceCounter;
    private static final Logger LOGGER = LoggerFactory.getLogger(CounterServlet.class);
    private static final String PATH_CLEAR = "/clear";
    private static final String COOKIE_HH = "hh-auth";
    private static final int COOKIE_SIZE = 10;
    private static final String HEADER_SUBTRACTION = "Subtraction-Value";

    @Override
    public void init() {
        serviceCounter = new CounterService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        long count = serviceCounter.getCounter().longValue();
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().println(count);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        if (PATH_CLEAR.equals(req.getPathInfo()) && validate(req)) {
            serviceCounter.reset();
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
            return;
        }
        if (req.getPathInfo() != null) {
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        serviceCounter.increment();
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    private boolean validate(HttpServletRequest req) {
        return Arrays.stream(req.getCookies())
                .filter(cookie -> COOKIE_HH.equals(cookie.getName()))
                .anyMatch(cookie -> cookie.getValue().length() > COOKIE_SIZE);
    }


    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        String countAsString = req.getHeader(HEADER_SUBTRACTION);
        long value = parseString(countAsString);
        serviceCounter.decrementByValue(value);
        resp.setStatus(HttpServletResponse.SC_NO_CONTENT);
    }

    private long parseString(String countAsString) {
        if (countAsString == null) {
            return 0L;
        }
        try {
            return Long.parseLong(countAsString);
        } catch (NumberFormatException e) {
            LOGGER.warn("can't parse header with value {}", countAsString, e);
            return 0L;
        }
    }
}
