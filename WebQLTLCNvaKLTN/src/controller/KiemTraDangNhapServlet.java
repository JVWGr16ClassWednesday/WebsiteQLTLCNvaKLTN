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
		//Khởi tạo một session, từ đối tượng HttpSession. bằng cách gọi phương thức getSession từ HttpServletRequest
		HttpSession session = request.getSession();
		//tạo một biến có kiểu string, lấy thông tin từ trường input username từ trang jsp.Tương tự với passwword
		String name = request.getParameter("txtTenTaiKhoanDN");
		String pass = request.getParameter("txtMatKhauDN");
		//Tạo một list có tên là pass, list này sẽ trả về hai phần tử gồm quyền đăng nhập(access) và giá trị xác định trạng thái của tài khoản(khoatk)
		//Giá trị truyền vào cho hàm KiemTraDangNhap gồm name và pass được lấy từ trường input người dùng
		int result[] = QuanLyTaiKhoan.KiemTraDangNhap(name, pass);
		//nếu list này có phần tử đầu tiên tức là access >=0, Vì database có 3 giá trị access người dùng là: 0: admin, 1: giảng viên, 2: sinh viên
		if (result[0] >= 0){
			//khởi tạo lại biến erro cho session là false
			session.removeAttribute("error");
			session.setAttribute("error", false);
			//set session các giá trị như access, và khoatk 
			session.setAttribute("accessright", result[0]);
			//kiểm tra nếu phần tử đầu tiên và phần tử thứ hai của list result la 1 hoặc 0 thì được vào Trang chức năng tương ứng với access.
			// result[1] = 1 tức là tài khoản đó không bị khóa, =1 thì bị khóa
			//result[0] = 0 tức là người đăng nhập đó là admin
			if(result[1] == 1 || result[0] == 0){
				//thỏa điều kiện thì sert giá trị cho session là 1.
				session.setAttribute("khoatk", result[1]);
				//chỉ cho client đi tới trang ChucNang.jsp
				response.sendRedirect("ChucNang.jsp");
				
			}
			else {
				
				//set lại giá trị cho biến error của session
				session.removeAttribute("error");
				session.setAttribute("error", true);
				//chỉ cho client đi tới trang DangNhap.jsp
				response.sendRedirect("DangNhap.jsp");
			}
			
		}
		else {
			session.removeAttribute("error");
			session.setAttribute("error", true);
			response.sendRedirect("DangNhap.jsp");
		}

	}

}
