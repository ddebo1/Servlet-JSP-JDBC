package com.displaydetails;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.displaydetails.dao.DisplayDetailsDao;
import com.employee.Employee;

/**
 * Servlet implementation class DisplayDetails
 */
@WebServlet("/DisplayDetails")
public class DisplayDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		int id = Integer.parseInt(request.getParameter("empid"));
		
		DisplayDetailsDao dao = new DisplayDetailsDao();
		
		try {
		Employee e = dao.getEmployee(id);
		if(e == null) {
			out.write("Provide correct employee id");
		}
		else {
			request.setAttribute("ename", e.getEmpname());
			request.setAttribute("desg", e.getDesignation());
			request.setAttribute("sal", e.getSalary());
			RequestDispatcher rd = request.getRequestDispatcher("empdetails.jsp");
			rd.forward(request, response);
		}
		}
		catch(Exception e) {
			out.write(e.getMessage());
		}
		
		
		
		
		
		
	}

}
