import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
@WebServlet("/counter/clear")
public class ServletCounterClear extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var cookies = Arrays.stream(req.getCookies())
                .filter(s -> s.getName().equals("hh-auth") & s.getValue().length() >= 10)
                .map(Cookie::clone)
                .collect(Collectors.toList());
        if (cookies.size() != 0) {
            Counter.setCount(0);
        }
    }
}
