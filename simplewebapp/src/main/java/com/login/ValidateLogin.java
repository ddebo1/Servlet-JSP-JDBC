package com.login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.login.dao.ValidateLoginDao;

/**
 * Servlet implementation class ValidateLogin
 */
@WebServlet("/ValidateLogin")
public class ValidateLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

			
		String username = request.getParameter("uname");
		String password = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		
		
		ValidateLoginDao dao = new ValidateLoginDao();
		
		
		
		try {
		if(dao.validate(username, password)) {
			RequestDispatcher rd = request.getRequestDispatcher("welcome.jsp");	
			rd.forward(request, response);
			
		}
		else {
			out.write("Invalid Credentials");
		}
		}
		catch(Exception e) {
			out.write(e.getMessage());
		}
		
		
		
		
		
	}

}
