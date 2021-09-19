package mypack;

import java.sql.*;
public class DBConnection {
	static Connection con;
	static Connection connect() {
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection ("jdbc:mysql://localhost:3306/employee","root","5250");
		System.out.println("Connected");
	}
	catch(ClassNotFoundException se) {
		se.printStackTrace();
	}
	catch(SQLException se) {
		se.printStackTrace();
	}
	return con;
}
public static void main (String ar[]) {
connect();	
}
}