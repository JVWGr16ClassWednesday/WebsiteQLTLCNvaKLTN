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
		String user_id_khoa = request.getParameter("user_id");
		
		String user_id = request.getParameter("id_user");
		
		String btnxoa = request.getParameter("btnxoa");
//		System.out.println("nội dung button xóa---->" + btnxoa);
		
		String btnsua = request.getParameter("btnsua");
//		System.out.println("nội dung button sửa--->" + btnsua);
		
		
//		System.out.println("nội dung button khóa---->"+ btnkhoa);
		
		
		
//		//neu value_btnkhoa ==1 thì tai khoan bị khoa, set cho no bị khoa
		if(request.getParameter("value_btnkhoa").equals("Khoa")){
			int res = QuanLyTaiKhoan.KiemTraRole(Integer.parseInt(user_id_khoa));
			if(QuanLyTaiKhoan.Khoataikhoan(Integer.parseInt(user_id_khoa), 2))
			{
				if (res==1) {
					response.sendRedirect("DanhSachTaiKhoanGV.jsp");
				}
				if (res==2) {
					response.sendRedirect("DanhSachTaiKhoanSV.jsp");
				}
				
			}
		}
		//neu value_btnkhoa ==2 thì thuc hien mo khoa tai khoan
		else if(request.getParameter("value_btnkhoa").equals("Mo khoa")){
			int res = QuanLyTaiKhoan.KiemTraRole(Integer.parseInt(user_id_khoa));
			if(QuanLyTaiKhoan.Khoataikhoan(Integer.parseInt(user_id_khoa), 1))
			{
				if (res==1) {
					response.sendRedirect("DanhSachTaiKhoanGV.jsp");
				}
				if (res==2) {
					response.sendRedirect("DanhSachTaiKhoanSV.jsp");
				}

			}
			
		}
		else if (request.getParameter("btnxoa").equals("Xóa")) {
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
			else if(res == 2)
			{
				if(QuanLyTaiKhoan.XoaTaiKhoan(Integer.parseInt(user_id))){
					response.sendRedirect("DanhSachTaiKhoanSV.jsp");
				}
			}
		}
		
		
	}

}
