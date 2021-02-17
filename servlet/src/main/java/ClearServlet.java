import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

@WebServlet(urlPatterns = "/counter/clear")
public class ClearServlet extends HttpServlet {
	private Counter count;
	
	@Override
	public void init() throws ServletException {
		this.count = (Counter) getServletContext().getAttribute("count");
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost clear is execute");
		Cookie[] cookies =  req.getCookies();
		if (cookies == null){
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			System.out.println("No cookies");
		} else {
			Cookie cook = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("hh-auth")).findFirst().get();
			if (cook.getValue().length() > 10) {
				count.clearCount();
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("counter reset");
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			} else {
				System.out.println(cook.getName() + " cookies " + cook.getValue().length());
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
	}
}
