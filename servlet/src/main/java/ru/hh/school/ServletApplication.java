package ru.hh.school;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import ru.hh.school.utils.CounterServlet;
import ru.hh.school.utils.ServletCleaner;
import ru.hh.school.utils.StatusServlet;

public class ServletApplication {

  public static void main(String[] args) throws Exception {
    int port = 8081;
    Server server = createServer(port);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletHandler servletHandler = new ServletHandler();
    servletHandler.addServletWithMapping(StatusServlet.class, "/status");
    servletHandler.addServletWithMapping(CounterServlet.class, "/counter");
    servletHandler.addServletWithMapping(ServletCleaner.class, "/counter/clear");
    server.setHandler(servletHandler);
    return server;
  }

}
