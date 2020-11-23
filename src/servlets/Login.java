package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(
	description = "Login Servlet Testing",
	urlPatterns = {"/Login"},
	initParams = {
			@WebInitParam(name= "name",value = "Shubham"),
			@WebInitParam(name = "password",value = "Shu%12bham")
	}
)
public class Login extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		String username = req.getParameter("name");
		String passwrod = req.getParameter("password");
		String nameRegex = "^[A-Z]{1}.{2,}$";
		String PASSWORD_CHECK = "^(?=.*\\d)(?=.*[A-Z])(?=.*\\W)(?!.*\\W\\w*\\W)(?!.*\\s).{8,}$";
		Pattern pattern = Pattern.compile(nameRegex);
		Matcher matcher = pattern.matcher(username);
		PrintWriter out = resp.getWriter();
		if(!matcher.matches()) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			
			out.println("<font color=red>Name does not match the required pattern</font>");
			rd.include(req, resp);
			return;
		}
		pattern = Pattern.compile(PASSWORD_CHECK);
		matcher = pattern.matcher(passwrod);
		if(!matcher.matches()) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			
			out.println("<font color=red>Password does not match the required pattern</font>");
			rd.include(req, resp);
			return;
		}
		String nameChecker = getServletConfig().getInitParameter("name");
		String passwordChecker = getServletConfig().getInitParameter("password");
		if(username.equals(nameChecker) && passwrod.equals(passwordChecker)) {				
			req.setAttribute("name", username);
			req.getRequestDispatcher("LoginSuccess.jsp").forward(req, resp);
		}
		else {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			out.println("<font color=red>Credentials provided are not valid</font>");
			rd.include(req, resp);
		}
		out.close();
		
//		super.doGet(req, resp);
	}

}
