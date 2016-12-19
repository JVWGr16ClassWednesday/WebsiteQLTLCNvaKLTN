package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			String sql = "SELECT * FROM detai WHERE MATCH(motadt) AGAINST ('" + mota + "'"+" IN NATURAL LANGUAGE MODE)";
			List<DeTai> deTais = new ArrayList<DeTai>();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){
				
						DeTai detai = new DeTai();
						detai.setTendt(resultSet.getString("tendt"));
						detai.setMotadt(resultSet.getString("motadt"));
						detai.setLoaidt(resultSet.getString("loaidt"));				
						detai.setTruongnhom(resultSet.getString("truongnhom"));
						detai.setMasvtn(resultSet.getInt("masvnt"));
						detai.setThanhvien(resultSet.getString("thanhvien"));
						detai.setMasvtv(resultSet.getInt("masvtv"));
						detai.setGvhd(resultSet.getString("gvhd"));
						detai.setMagvhd(resultSet.getInt("magvhd"));
						detai.setGvpb(resultSet.getString("gvpb"));
						detai.setMagvpb(resultSet.getInt("magvpb"));
						detai.setDiem(resultSet.getFloat("diem"));
						detai.setNam(resultSet.getInt("nam"));
						detai.setTailieu(resultSet.getString("tailieu"));
						deTais.add(detai);						
					}
					
					request.setAttribute("name", deTais);
					request.getRequestDispatcher("Loc.jsp").forward(request, response);
		}
		catch (Exception e) {
		}		
		
	}
   
}

           
