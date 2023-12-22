package demo.web.jdbc;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private StudentDbUtil studentDbUtil;

	@Resource(name = "jdbc/web_student_tracker")

	private DataSource dataSource;
	
   
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();

		try {
			studentDbUtil = new StudentDbUtil(dataSource);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
			
		String theCommand = request.getParameter("command");
		
		if(theCommand == null)
			theCommand = "LIST";
		
		
			switch(theCommand)
			{	
				case "LIST":
					listStudents(request, response);
					break;
				case "ADD":
					addStudent(request,response);
					break;
				case "LOAD":
					loadStudent(request,response);
					break;
				case "UPDATE":
					updateStudent(request,response);
					break;
				case "DELETE":
					deleteStudent(request,response);
					break;
				default:
					listStudents(request, response);
					
			}		
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}


	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<Student> students = studentDbUtil.getStudents();
		request.setAttribute("STUDENT_LIST", students);
		RequestDispatcher rd = request.getRequestDispatcher("/list-students.jsp");
		rd.forward(request, response);

	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");	
		String email= request.getParameter("email");
		Student tempStudent = new Student(firstname, lastname,email);
		studentDbUtil.addStudent(tempStudent);
		listStudents(request, response);
			
	}
	
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int studentId = Integer.parseInt(request.getParameter("studentId"));
		Student student = studentDbUtil.loadStudent(studentId);
		request.setAttribute("theStudent", student);
		RequestDispatcher rd = request.getRequestDispatcher("/update-student-form.jsp");
		rd.forward(request, response);
		
	}
	
	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int studentId = Integer.parseInt(request.getParameter("studentId"));	
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		Student student = new Student(studentId, firstName, lastName, email );
		studentDbUtil.updateStudent(student);
		listStudents(request, response);	
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int studentId = Integer.parseInt(request.getParameter("studentId"));	
		studentDbUtil.deleteStudent(studentId);
		listStudents(request, response);
		
		
	}




}
