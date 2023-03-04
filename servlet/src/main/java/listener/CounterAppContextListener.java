package listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

@WebListener
public class CounterAppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        AtomicLong counter = new AtomicLong();

        servletContext.setAttribute("Counter", counter);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
