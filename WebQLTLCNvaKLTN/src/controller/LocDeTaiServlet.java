package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeTai;
import service.QuanLyDeTai;
import utils.ConnectionDB;


@WebServlet("/LocDeTaiServlet")
public class LocDeTaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public LocDeTaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public String convertFromUTF8(String s){
	    String out = null;
	    try {
	        out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
	    } catch (java.io.UnsupportedEncodingException e) {
	        return null;
	    }
	    return out;
	}
    
    public class CompareString {

    	String str = "Result";
    	String str1 = "Struel";

    	public void compare() {
    	    char[] firstString = str.toLowerCase().toCharArray();
    	    char[] secondString = str1.toLowerCase().toCharArray();

    	    Arrays.sort(firstString);
    	    Arrays.sort(secondString);

    	    if (Arrays.equals(firstString, secondString) == true) {
    	        System.out.println("Both the string contain same charecter");
    	    } else {
    	        System.out.println("Both the string contains different charecter");
    	    }
    	}
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mota = request.getParameter("nhap_abstract");
		System.out.println(mota);
//		int  result = mota.compareTo(QuanLyDeTai.TimMoTaDeTai());
		
//		System.out.println("------>"+result);
		
		Statement statement = null;
		String res = "";
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			// tim mo ta de tai cua tat ca ca de tai da co trong database
			String sql = "SELECT motadt FROM detai";
			System.out.println(sql);
			// tao mot bien resulset de luu lai ket qua cua cau truy van
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){
				
				//voi tung ket qua duoc tra ve, lay noi dung can lay dua vao bien res
				res = resultSet.getString("motadt");
				System.out.println("res---->" + res);
				//dua hai chuoi can so sanh vao hai list co kieu dl la char.
				char[] firstString = convertFromUTF8(mota).toLowerCase().toCharArray();
			    char[] secondString = res.toLowerCase().toCharArray();
			    System.out.println("firstString" + firstString);
			    System.out.println("secondString" + secondString);
			    
			    //sap xep cac gia tri trong tung list theo gia tri tu thap den cao  
			    Arrays.sort(firstString);
			    Arrays.sort(secondString);
			    //kiem tra neu hai chuoi nay giong nhau
			    if (Arrays.equals(firstString, secondString) == true) {
			    	int res_1;
			    	//neu giong nhau thi thuc hien truy van tim id cua nhung de tai co phan mo ta giong nhu nguoi dung nhap tren text
			    	String test_sql = "SELECT id FROM detai WHERE motadt LIKE" + "'%" + res + "%'";
//			    	System.out.println("new query sql"+test_sql);
			    	//ket qua cua cau truy van duoc luu vao results
					ResultSet results = statement.executeQuery(test_sql);
					//trong tung ket qua duoc tra ve
					while (results.next()){
						res_1 = results.getInt("id");
						// thuc hien truy van lay tat ca cac thong tin cua de tai theo id.
						String sql_full = "SELECT * FROM detai WHERE id='" + res_1+ "'";
						//ket qua truy van duoc luu vao bien results_full
						ResultSet results_full = statement.executeQuery(sql_full);
						while(results_full.next()){
					        request.getRequestDispatcher("/ResultLocDeTai.jsp").forward(request, response);
						}
						
						System.out.println(res_1);
					}

			        System.out.println("Both the string contain same charecter");
			    } else {
			        System.out.println("Both the string contains different charecter");
			    }
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
		}		
		
	}
   
}

           
