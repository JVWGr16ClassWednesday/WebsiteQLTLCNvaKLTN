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
    //code tham khảo
   /* public class CompareString {

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
    }*/


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mota = request.getParameter("nhap_abstract");
		
		Statement statement = null;
		String res = "";
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			// tim mo ta de tai cua tat ca ca de tai da co trong database
			String sql = "SELECT motadt FROM detai";
			// tao mot bien resulset de luu lai ket qua cua cau truy van
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){
				
				//voi tung ket qua duoc tra ve, lay noi dung can lay dua vao bien res
				res = resultSet.getString("motadt");
				//dua hai chuoi can so sanh vao hai list co kieu dl la char.
				char[] firstString = convertFromUTF8(mota).toLowerCase().toCharArray();
			    char[] secondString = res.toLowerCase().toCharArray();
			    
			    //sap xep cac gia tri trong tung list theo gia tri tu thap den cao  
			    Arrays.sort(firstString);
			    Arrays.sort(secondString);
			    //kiem tra neu hai chuoi nay giong nhau
			    if (Arrays.equals(firstString, secondString) == true) {
			    	int res_1;
			    	//neu giong nhau thi thuc hien truy van tim id cua nhung de tai co phan mo ta giong nhu nguoi dung nhap tren text
			    	String test_sql = "SELECT id FROM detai WHERE motadt LIKE" + "'%" + res + "%'";
			    	//ket qua cua cau truy van duoc luu vao results
					ResultSet results = statement.executeQuery(test_sql);
					//trong tung ket qua duoc tra ve
					while (results.next()){
						res_1 = results.getInt("id");
						request.setAttribute("name", res_1);
						request.getRequestDispatcher("Loc.jsp").forward(request, response);
					}

			        System.out.println("Both the string contain same charecter");
			    } else {
			        System.out.println("Both the string contains different charecter");
			    }
				
			}
		}
		catch (Exception e) {
		}		
		
	}
   
}

           
