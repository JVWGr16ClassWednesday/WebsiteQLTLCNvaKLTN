package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Users;
import service.QuanLyTaiKhoan;
import utils.ConnectionDB;


@WebServlet("/DangKyTaiKhoanServlet")
public class DangKyTaiKhoanServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DangKyTaiKhoanServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try {
			String id = request.getParameter("id");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String role = request.getParameter("Check_Quyen");
			String password =request.getParameter("password");
			Users users = new Users();
			users.setId(Integer.parseInt(id));
			users.setMyname(name);
			users.setUsername(email);
			users.setAccessright(Integer.parseInt(role));
			users.setPassword(password);
			if (QuanLyTaiKhoan.check_email(email)) {
				response.sendRedirect("TaoTaiKhoan.jsp");
			}
			if(QuanLyTaiKhoan.ThemTaiKhoan(users))
			{
				if(role.equals("1")){
					response.sendRedirect("DanhSachTaiKhoanGV.jsp");
				}else{
					response.sendRedirect("DanhSachTaiKhoanSV.jsp");
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
