package com.displaydetails.dao;
import java.sql.*;
import com.employee.*;

public class DisplayDetailsDao {

	public Employee getEmployee(int uid) throws SQLException  {
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String uname = "root";
		String pw = "root";
		try {
		Class.forName("com.mysql.jdbc.Driver"); 
		}
		catch (ClassNotFoundException e) {
			System.out.println("Driver class cound not be loaded");
		}
		
		Connection con = DriverManager.getConnection(url,uname,pw);
		PreparedStatement st = con.prepareStatement("select * from employee where id= ?");
		st.setInt(1, uid);
		ResultSet rs = st.executeQuery();

		
		Employee e = new Employee();
		
		if(rs.next()) {
			e.setEmpid(rs.getInt(1));
			e.setEmpname(rs.getString(2));
			e.setDesignation(rs.getString(3));
			e.setSalary(rs.getLong(4));
			return e;
		}
		else {
			return null;
		}
		
			
		
		
		
		

	}

}
