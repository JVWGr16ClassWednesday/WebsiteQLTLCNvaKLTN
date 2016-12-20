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
			return true;
		}
		catch (Exception e) {
		}
		
		return false;
	}
	
	public static String TimMoTaDeTai(){
		Statement statement = null;
		String res = "";
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			String sql = "SELECT motadt FROM detai";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){

				res = resultSet.getString("motadt");
			}
		}
		catch (Exception e) {
		}
		return res;
		
	}
	
	public static boolean check_tendetai(String tendetai){
    	
    	try {
			Statement st = ConnectionDB.getConnection().createStatement();
			String sql = "select * from detai where tendt = '"+tendetai+"'";
			ResultSet i = st.executeQuery(sql);			
			while (i.next()) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return false;
}
	
	

}
