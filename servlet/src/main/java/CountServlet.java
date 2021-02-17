import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/counter")
public class CountServlet extends HttpServlet {
	private Counter count;
	
	public void init() throws ServletException {
		this.count = (Counter) getServletContext().getAttribute("count");
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGet is executed");
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("count = " + count.getCount());
		printWriter.close();
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPost is executed");
		count.increaseCount();
		PrintWriter printWriter = resp.getWriter();
		printWriter.write("count = " + count.getCount());
		printWriter.close();
		resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	}
	
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doDelete is executed");
		String num = req.getHeader("Subtraction-Value");
		if (num == null){
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		} else {
			try {
				count.decreaseCount(Integer.parseInt(num));
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			} catch (NumberFormatException ex) {
				resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				PrintWriter printWriter = resp.getWriter();
				printWriter.write("Input correct number");
				printWriter.close();
			}
		}
	}
}
