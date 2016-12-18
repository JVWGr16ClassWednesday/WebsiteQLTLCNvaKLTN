package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.DeTai;
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
	
	public static String TimMoTaDeTai(){
		Statement statement = null;
		String res = "";
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			String sql = "SELECT motadt FROM detai";
//			System.out.println(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){

				res = resultSet.getString("motadt");
//				System.out.println(res);
//				statement.close();
			}
//			System.out.println(sql);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return res;
		
	}
	
	public static boolean check_tendetai(String tendetai){
    	
    	try {
			Statement st = ConnectionDB.getConnection().createStatement();
			String sql = "select * from detai where tendt = '"+tendetai+"'";
			ResultSet i = st.executeQuery(sql);
			System.out.println("sql------------->" +sql);
			
			while (i.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
}
	
	

}
