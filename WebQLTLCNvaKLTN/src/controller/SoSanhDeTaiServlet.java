package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import model.DeTai;
import utils.ConnectionDB;

/**
 * Servlet implementation class SoSanhDeTaiServlet
 */
@WebServlet("/SoSanhDeTaiServlet")
public class SoSanhDeTaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SoSanhDeTaiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    // hàm so sánh hai đoạn text ( đoạn tóm tắt đề tài)
    // sử dụng thư viện Commons Lang 3
    public double compareStrings(String stringA, String stringB) {
        return StringUtils.getJaroWinklerDistance(stringA, stringB);
    }
    
    // hàm chuẩn hóa chuỗi về UTF8 khi bắt resquest từ server về
    public String convertFromUTF8(String s){
	    String out = null;
	    try {
	        out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
	    } catch (java.io.UnsupportedEncodingException e) {
	        return null;
	    }
	    return out;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mota = request.getParameter("nhap_abstract");
		String percent = request.getParameter("percent");
		System.out.println(convertFromUTF8(mota));
		Statement statement = null;
		String res = "";
		int res_1;
		try (Connection connection = ConnectionDB.getConnection()) {
			statement = connection.createStatement();
			
			// tim mo ta de tai cua tat ca ca de tai da co trong database
			String sql = "SELECT motadt FROM detai";
			// tao mot bien resulset de luu lai ket qua cua cau truy van
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()){
				res = resultSet.getString("motadt");
				double per = Double.parseDouble(percent);
				System.out.println("so phan tram nhap vao: "+ per);
				double ketqua = (compareStrings((convertFromUTF8(mota)),res )*100);
				System.out.println("so phan tram so sanh duoc: "+ ketqua);
				if(ketqua < per){
					String message = "No Result";
					request.setAttribute("message", message);
					request.getRequestDispatcher("/SoSanh.jsp").forward(request, response);
					response.sendRedirect("SoSanh.jsp");

				}
				else {
					//search full text
					String new_sql = "SELECT * FROM detai WHERE MATCH(motadt) AGAINST ('" + (convertFromUTF8(mota)) + "'"+" IN NATURAL LANGUAGE MODE)";

			    	//ket qua cua cau truy van duoc luu vao results
//					System.out.println(new_sql);
					ResultSet results = statement.executeQuery(new_sql);
					//khoi tao mot list co ten là detais de luu ket qua truy
					List<DeTai> deTais = new ArrayList<DeTai>();
					//trong tung ket qua duoc tra ve
					while (results.next()){
						res_1 = results.getInt("id");
//						System.out.println(res_1);
						//khoi tao doi tuong de tai moi
						DeTai detai = new DeTai();
						//set ca gia tri cho doi tuong detai nay
						detai.setTendt(results.getString("tendt"));
						detai.setMotadt(results.getString("motadt"));
						detai.setLoaidt(results.getString("loaidt"));				
						detai.setTruongnhom(results.getString("truongnhom"));
						detai.setMasvtn(results.getInt("masvnt"));
						detai.setThanhvien(results.getString("thanhvien"));
						detai.setMasvtv(results.getInt("masvtv"));
						detai.setGvhd(results.getString("gvhd"));
						detai.setMagvhd(results.getInt("magvhd"));
						detai.setGvpb(results.getString("gvpb"));
						detai.setMagvpb(results.getInt("magvpb"));
						detai.setDiem(results.getFloat("diem"));
						detai.setNam(results.getInt("nam"));
						detai.setTailieu(results.getString("tailieu"));
						// add cac thuoc tinh cua doi tuong detai va list detais
						deTais.add(detai);
						
					}
					if (deTais.isEmpty())
					{
						response.sendRedirect("SoSanh.jsp");
					}
//					System.out.println(deTais);
					String message = "This Result";
					request.setAttribute("message", message);
					request.setAttribute("name", deTais);
					request.getRequestDispatcher("SoSanh.jsp").forward(request, response);
					
				}
				
				}
			}
		catch (Exception e) {
		}	

	}

}
