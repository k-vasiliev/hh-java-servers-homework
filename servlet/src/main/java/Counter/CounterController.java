package Counter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CounterController extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();// get the stream to write the data
		pw.print(ServiceCounter.getCounter());
		pw.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = response.getWriter();// get the stream to write the data
		pw.print(ServiceCounter.addCounter());
		pw.close();
	}

	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			int val = Integer.parseInt(request.getHeader("Subtraction-Value"));
			PrintWriter pw = response.getWriter();
			pw.print(ServiceCounter.decreaseCounter(val));
			pw.close();
		} catch (Exception e) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		}

	}

}
