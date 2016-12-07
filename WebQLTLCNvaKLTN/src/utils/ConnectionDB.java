package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
	public static java.sql.Connection getConnection() throws ClassNotFoundException{
		 Class.forName("com.mysql.jdbc.Driver");
		    Connection con = null;
			try {
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectweb?useUnicode=true&characterEncoding=utf8", 
				        "root", "root");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    return con;
	 }

}
