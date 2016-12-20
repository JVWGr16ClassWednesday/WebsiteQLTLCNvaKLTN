package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeTai;
import utils.ConnectionDB;


@WebServlet("/TimKiemDeTaiServlet")
public class TimKiemDeTaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public TimKiemDeTaiServlet() {
        super();
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
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ndtim = request.getParameter("txttimkiem");
		String ndungtimcv = convertFromUTF8(ndtim);
		Statement statement = null;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			String sql = "SELECT * FROM detai WHERE MATCH(tendt,motadt,truongnhom,thanhvien,gvhd,tailieu) AGAINST ('" + ndungtimcv + "'"+" IN NATURAL LANGUAGE MODE)";
			ResultSet resultSet = statement.executeQuery(sql);
			
			List<DeTai> deTais = new ArrayList<DeTai>();
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
			if (deTais.isEmpty())
			{
				System.out.println("khong tim duojc ket qua");
				response.sendRedirect("TimKiemDeTai.jsp");
			}
			
		    request.setAttribute("name", deTais);
			request.getRequestDispatcher("TimKiemDeTai.jsp").forward(request, response);
		}
		catch (Exception e) {
		}		
		
	}

}
