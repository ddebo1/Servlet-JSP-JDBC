package com.login.dao;
import java.sql.*;

public class ValidateLoginDao {
	
	public boolean validate(String uname, String pw) throws Exception {
		
		String url = "jdbc:mysql://localhost:3306/demo";
		String username = "root";
		String passw = "root";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,username,passw);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from udata where uname='"+uname+"'and pass='"+pw+"'");
		if(rs.next()) {
			if(uname.equalsIgnoreCase(rs.getString(1)) && pw.equals(rs.getString(2)) )  
				return true;
		}
		return false;
		
	}

}
