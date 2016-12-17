package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Users;
import service.QuanLyTaiKhoan;

/**
 * Servlet implementation class KiemTraDangNhap
 */
@WebServlet("/KiemTraDangNhap")
public class KiemTraDangNhapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KiemTraDangNhapServlet() {
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
		HttpSession session = request.getSession();
		String name = request.getParameter("txtTenTaiKhoanDN");
		String pass = request.getParameter("txtMatKhauDN");
		int result = QuanLyTaiKhoan.KiemTraDangNhap(name, pass);
		if (result > 0){
			session.removeAttribute("error");
			session.setAttribute("error", false);
			session.setAttribute("accessright", result);
			response.sendRedirect("ChucNang.jsp");
		}
		else {
			session.removeAttribute("error");
			session.setAttribute("error", true);
			response.sendRedirect("DangNhap.jsp");
		}

	}

}
