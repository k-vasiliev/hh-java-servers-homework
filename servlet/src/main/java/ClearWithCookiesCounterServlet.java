import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static javax.servlet.http.HttpServletResponse.SC_OK;
import static javax.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;

@WebServlet(urlPatterns = "/counter/clear")
public class ClearWithCookiesCounterServlet extends HttpServlet {

    private static Counter counter;
    @Override
    public void init(){
        counter = new Counter();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws IOException{
        PrintWriter writer = response.getWriter();
        writer.println("doPostWithDrop");
        if(checkCookie(request.getCookies())){
            Counter.drop();
            response.setStatus(SC_OK);
        }else {
            response.setStatus(SC_UNAUTHORIZED);
        }
        writer.println(Counter.getValue());
        writer.println(response.getStatus());
        writer.flush();
    }

    private boolean checkCookie(Cookie[] cookies){
            for (Cookie cookie : cookies) {
                return cookie.getName().equals("hh-auth") && cookie.getValue().length() > 10;
            }
        return false;
    }

}
