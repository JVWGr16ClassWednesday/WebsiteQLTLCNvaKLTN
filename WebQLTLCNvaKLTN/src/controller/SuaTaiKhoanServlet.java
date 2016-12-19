package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Users;
import service.QuanLyTaiKhoan;

/**
 * Servlet implementation class SuaTaiKhoanServlet
 */
@WebServlet("/SuaTaiKhoanServlet")
public class SuaTaiKhoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SuaTaiKhoanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("tensua");
		String email = request.getParameter("emailsua");
		String password =request.getParameter("passsua");
		String masv = request.getParameter("masvsua");
		String access = request.getParameter("Check_Quyen");
		String row_id = request.getParameter("row_id");
		
		System.out.println("im here!!!");
		System.out.println("------>sửa tài khoản");
		Users user = new Users();
        user.setId(Integer.parseInt(row_id));
    	user.setMyname(name);
    	user.setUsername(email);
    	user.setPassword(password);
    	user.setMasv(masv);
    	user.setAccessright(Integer.parseInt(access));
		if(QuanLyTaiKhoan.ChinhSuaTaiKhoan(user)){
			int res = QuanLyTaiKhoan.KiemTraRole(Integer.parseInt(row_id)); 
			System.out.println(res);
			if (res == 1){
				response.sendRedirect("DanhSachTaiKhoanGV.jsp");
			}
			
			if (res == 2){
				response.sendRedirect("DanhSachTaiKhoanSV.jsp");
			}
			
		}
		
			/*if (res == 1)
			{
				if(QuanLyTaiKhoan.ChinhSuaTaiKhoan(user)){
	        		response.sendRedirect("DanhSachTaiKhoanGV.jsp");
	        		
	        	}
				
			}
			if(res == 2)
			{
				if(QuanLyTaiKhoan.ChinhSuaTaiKhoan(user)){
	        		response.sendRedirect("DanhSachTaiKhoanSV.jsp");
	        		
	        	}
			}*/
			
		}

}
