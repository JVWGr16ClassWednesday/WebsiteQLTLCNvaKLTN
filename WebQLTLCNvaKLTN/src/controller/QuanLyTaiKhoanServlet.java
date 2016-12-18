package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jws.soap.SOAPBinding.Use;
import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Users;
import service.QuanLyTaiKhoan;

/**
 * Servlet implementation class QuanLyTaiKhoanServlet
 */
@WebServlet("/QuanLyTaiKhoanServlet")
public class QuanLyTaiKhoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuanLyTaiKhoanServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String row_id = request.getParameter("row_id");
		String user_id_khoa = request.getParameter("user_id");
		String btnkhoa = request.getParameter("value_btnkhoa");
		System.out.println("value btn khoa: "+ btnkhoa);
		System.out.println("user id khoa: "+ user_id_khoa);
		
		String user_id = request.getParameter("id_user");
		String name = request.getParameter("tensua");
		String email = request.getParameter("emailsua");
		String password =request.getParameter("passsua");
		String masv = request.getParameter("masvsua");
	
		
		String btnxoa = request.getParameter("btnxoa");
//		System.out.println(btnxoa);
		
		String btnsua = request.getParameter("btnsua");
//		System.out.println(btnsua);
		
		
//		//neu value_btnkhoa ==1 thì tai khoan bị khoa, set cho no bị khoa
		System.out.println("value_btnKhoa " + request.getParameter("value_btnkhoa"));
		if(request.getParameter("value_btnkhoa").equals("Khoa")){
			
			if(QuanLyTaiKhoan.Khoataikhoan(Integer.parseInt(user_id_khoa), 2))
			{
				System.out.println("khoa tai khoản");
				response.sendRedirect("DanhSachTaiKhoanGV.jsp");
			}
		}
		//neu value_btnkhoa ==2 thì thuc hien mo khoa tai khoan
		else{
			System.out.println("==============cái quần đùi 2");
			//QuanLyTaiKhoan.Khoataikhoan(Integer.parseInt(user_id_khoa), 2);
			if(QuanLyTaiKhoan.Khoataikhoan(Integer.parseInt(user_id_khoa), 1))
			{
				System.out.println("mở khoa tai khoản");
				response.sendRedirect("DanhSachTaiKhoanGV.jsp");
			}
			
		}
	
		if (request.getParameter("btnxoa") != null) {
			
			System.out.println("xóa tài khoản");
			int res = QuanLyTaiKhoan.KiemTraRole(Integer.parseInt(user_id)); 
			System.out.println(res);
			if (res == 1)
			{
				if(QuanLyTaiKhoan.XoaTaiKhoan(Integer.parseInt(user_id)))
				{
					response.sendRedirect("DanhSachTaiKhoanGV.jsp");
				}
				
			}
			if(res == 2)
			{
				if(QuanLyTaiKhoan.XoaTaiKhoan(Integer.parseInt(user_id))){
					response.sendRedirect("DanhSachTaiKhoanSV.jsp");
				}
			}
          
        }
		// neu field khoakt ==1 , thi hong cho dang nhap, khoatk==2 thi cho dang nhap
		
		if(request.getParameter("btnsua") != null) {
			System.out.println("sửa tài khoản");
        	Users user = new Users();
        	user.setId(Integer.parseInt(row_id));
    		user.setMyname(name);
    		user.setUsername(email);
    		user.setPassword(password);
    		user.setMasv(masv);
			int res = QuanLyTaiKhoan.KiemTraRole(Integer.parseInt(row_id)); 
			System.out.println(res);
			if (res == 1)
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
			}
        }

	}

}
