package service;

import java.sql.Connection;
import java.sql.Statement;

import utils.ConnectionDB;

public class QuanLyDeTai {
	public static boolean XoaDeTai(int madt) {
		Statement statement = null;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			String sql = "DELETE FROM detai WHERE id='" + madt + "'";
			System.out.println(sql);
			statement.execute(sql);
//			statement.close();
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}

}
