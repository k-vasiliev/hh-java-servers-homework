package resource;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import storage.StorageCounter;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

public class CounterClearServlet extends HttpServlet {

  private final StorageCounter storageCounter = StorageCounter.getInstance();
  private final int REQUIREDLENGTH = 10;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    Cookie[] cookies = request.getCookies();
    boolean auth = Arrays.stream(cookies)
        .filter(cookie -> cookie.getName().equals("hh-auth"))
        .anyMatch(cookie -> cookie.getValue().length() > REQUIREDLENGTH);

    if (auth){
      storageCounter.reset();
      response.setStatus(204);
    } else response.setStatus(401);
  }
}
