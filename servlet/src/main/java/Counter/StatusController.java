package Counter;

import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import java.io.IOException;


public class StatusController extends HttpServlet {
	
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException {  
		res.setStatus(HttpServletResponse.SC_OK); 
	}  

}
