package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.jws.soap.SOAPBinding.Use;
import javax.naming.InitialContext;

import org.apache.catalina.User;

import model.Users;
import utils.ConnectionDB;

public class QuanLyTaiKhoan {
	public static boolean XoaTaiKhoan(int maGV) {
		Statement statement = null;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			String sql = "DELETE FROM users WHERE id='" + maGV + "'";
//			System.out.println(sql);
			statement.execute(sql);
			statement.close();
			
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		return false;
	}
	public static int KiemTraRole(int maGV){
		Statement statement = null;
		int res = -1;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			String sql = "SELECT accessright FROM users WHERE id='" + maGV + "'";
//			System.out.println(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){

				res = resultSet.getInt("accessright");
				statement.close();
			}
//			System.out.println(sql);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return res;
	}
	
	public static boolean ChinhSuaTaiKhoan(Users user){
		Statement statement = null;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
//			System.out.println("----->"+user.getMyname());
			String sql = "UPDATE users" + " SET "
					+ " username = '"+ user.getUsername()+"'" + ","
					+ " password = '" + user.getPassword()+"'" + ","
					+ " myname ='" +user.getMyname()+"'" + ","
					+ " masv ='" +user.getMasv()+ "'" + ","
					+ "accessright='" + user.getAccessright() + "'"
					+ " WHERE id='"+ user.getId()+"'"; 
			System.out.println(sql);
			statement.executeUpdate(sql);
			System.out.println("update thanh cong");
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}
	
	public static boolean check_email(String email){
	    	
	    	try {
				Statement st = ConnectionDB.getConnection().createStatement();
				ResultSet i = st.executeQuery("select * from users where username = '"+email+"'");
				while (i.next()) {
					return true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	return false;
	}
	
	public static boolean ThemTaiKhoan(Users user){
		System.out.println("------");
		Statement statement = null;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();	
			String sql = "insert into users(username, password, myname, accessright,masv) values ('" + user.getUsername() + "','" + user.getPassword() + "','" + user.getMyname() + "','" + user.getAccessright() + "','" +user.getId() + "')";
//			System.out.println(sql);
			
			statement.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return false;
		
	}
	
	public static int[] KiemTraDangNhap(String username, String password){
		Statement statement = null;
		int res = -1;
		int khoa_id = -1;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			String sql = "SELECT accessright,khoatk FROM users WHERE username ='" + username + "' and password= '" + password + "'" ;
//			System.out.println(sql);
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){

				res = resultSet.getInt("accessright");
				khoa_id = resultSet.getInt("khoatk");
				statement.close();
			}
//			System.out.println(sql);
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		int list_res[] = {res, khoa_id};
		
		return list_res;
	}
	 // ham khoa tai khoan
	public static boolean Khoataikhoan(int id_user, int khoaTK){
		System.out.println("-----------------------");
		Statement statement = null;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
//			System.out.println("----->"+user.getMyname());
			String sql = "UPDATE users" + " SET "
					+ " khoatk ='" + khoaTK + "'"
					+ " WHERE id='"+ id_user+ "'"; 
			System.out.println(sql);
			statement.executeUpdate(sql);
			return true;
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}


}
