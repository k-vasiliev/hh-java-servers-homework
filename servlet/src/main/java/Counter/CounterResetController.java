package Counter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CounterResetController extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getCookies() == null) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		boolean flag = Arrays.stream(request.getCookies())
				.anyMatch(ck -> ck.getName().equals("hh-auth") && ck.getValue().length() > 10);
		if (flag == false) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		PrintWriter pw = response.getWriter();
		pw.print(ServiceCounter.resetCounter());
		response.setStatus(HttpServletResponse.SC_OK);
	}

}
