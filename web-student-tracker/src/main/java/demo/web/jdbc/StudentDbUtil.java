package demo.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {

	private DataSource dataSource;

	public StudentDbUtil(DataSource theDataSource) {
		this.dataSource = theDataSource;
	}
	
	
// Getting a list of all Students from Db
	public List<Student> getStudents() throws Exception {

		List<Student> students = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			st = con.createStatement();
			String query = "SELECT * FROM STUDENT ORDER BY LAST_NAME";
			rs = st.executeQuery(query);
			while (rs.next()) {
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				String email = rs.getString(4);
				Student tempStudent = new Student(id, firstName, lastName, email);
				students.add(tempStudent);
			}
			return students;

		} finally {
			close(con, st, rs);
		}

	}
	
	
//Check if a student already exists
	
	public boolean studentExists(Student student) throws Exception {
				
		Connection con = dataSource.getConnection();
		Statement statement = con.createStatement();
		String query = String.format("select * from student where first_name = '%s' and last_name = '%s' and email = '%s'", student.getFirstName(), student.getLastName(), student.getEmail());
		ResultSet rs = statement.executeQuery(query);
		System.out.println(query);
		if(!rs.next()) {
			return false;
		}
		return true;
	}
	

//Adding a Student to DB
	public void addStudent(Student student) throws Exception {
		
		Connection con = null;
		PreparedStatement pst = null;
		System.out.println(studentExists(student));
		if(!studentExists(student)) {
		try {
			con = dataSource.getConnection();			
			String query = "insert into student" + "(first_name, last_name,email)" + "values(?,?,?)" ;
			pst = con.prepareStatement(query);
			pst.setString(1, student.getFirstName());
			pst.setString(2, student.getLastName());
			pst.setString(3, student.getEmail());
			pst.execute();
		} finally {
			close(con, pst, null);
		}	
		}
	}
	
	public Student loadStudent(int studentId) throws Exception {
		
		Student student = null;
		Connection con = dataSource.getConnection();
		String query = "select * from student where id = ?";
		PreparedStatement pst = con.prepareStatement(query);
		pst.setInt(1, studentId);
		ResultSet rs = pst.executeQuery();
		
		if(rs.next()) {
		student = new Student(studentId, rs.getString(2), rs.getString(3), rs.getString(4) );
		}
		else {
			throw new Exception("Could not find Student Id: " + studentId);
		}
		return student;	
	}

	public void updateStudent(Student student) throws Exception {
		
		Connection con = null;
		PreparedStatement pst = null;
		try {
			
		con = dataSource.getConnection();	
		String query = "update student set first_name = ?, last_name = ?, email = ? where id = ?";
		pst = con.prepareStatement(query);
		pst.setString(1, student.getFirstName());
		pst.setString(2, student.getLastName());
		pst.setString(3, student.getEmail());
		pst.setInt(4,  student.getId());
		pst.executeUpdate();
		}
		finally {
			close(con,pst,null);
		} 		
	}
	

	public void deleteStudent(int studentId) throws Exception{
		
		Connection con = null;
		PreparedStatement pst = null;
		try {
			
		con = dataSource.getConnection();	
		String query = "delete from student where id = ?";
		pst = con.prepareStatement(query);
		pst.setInt(1, studentId );
		pst.executeUpdate();
		}
		finally {
			close(con,pst,null);
		} 		
	}
		

// Closing all Connections 
	private void close(Connection con, Statement st, ResultSet rs) {

		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (con != null)
				con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	


	
}
