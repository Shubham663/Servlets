package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/firstServlet")
public class firstServlet extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String username = req.getParameter("name");
		String passwrod = req.getParameter("password");
		PrintWriter out = resp.getWriter();
		if(username.equals("Shubham") && passwrod.equals("12345")) {
			
			out.println("<h3>Correct credentials provided<h3>");
		}
		else {
			out.println("<h3>Credentials provided are not valid<h3>");
		}
		out.close();
		
//		super.doGet(req, resp);
	}

}
