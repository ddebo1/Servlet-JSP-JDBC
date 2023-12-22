package demo.web.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;


@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	
	//Connection pooling using Resource annotation 
	@Resource(name = "jdbc/web_student_tracker") 
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/plain");
		
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		PrintWriter out = response.getWriter();
		
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			String query = "select * from student";
			rs = st.executeQuery(query);
			while(rs.next()) {
				String mail = rs.getString("email");
				out.println(mail);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}


}
