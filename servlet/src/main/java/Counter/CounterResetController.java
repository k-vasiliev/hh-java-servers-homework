package Counter;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.http.Cookie;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Arrays;
import java.util.Objects;



public class CounterResetController extends HttpServlet {
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
		if( request.getCookies() == null)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		Cookie ck[] = request.getCookies();
		boolean flag = false;
		for(int i=0;i<ck.length;i++){
			if(ck[i].getName().equals("hh-auth") && ck[i].getValue().length() > 10)
			{
				flag = true;
			}  
		}
		if(flag==false)
		{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;			
		}
		PrintWriter pw = response.getWriter();
		pw.print(ServiceCounter.resetCounter());
		response.setStatus(HttpServletResponse.SC_OK);
	}  

}
