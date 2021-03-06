package tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HeaderTagGiangVien extends SimpleTagSupport{
	@Override
	public void doTag() throws JspException, IOException 
	{
		JspWriter out = getJspContext().getOut();
		out.println(" <nav class=\"navbar navbar-default\">");
		out.println("                <div class=\"container-fluid\">");
		out.println("                    <ul class=\"nav navbar-nav\">");
		out.println("                        <li>");
		out.println("                            <a href=\"TrangChu.jsp\"> Trang chủ</a>");
		out.println("                        </li>");
		out.println("                        <li><a href=\"QuanLyDeTai.jsp\">Quản lý đề tài</a></li>");
		out.println("                        <li><a href=\"KiemTraSaoChep.jsp\">Kiểm tra sao chép</a></li>");
		out.println("                    </ul>");
		out.println("                    <div style=\"padding-top:8px;\">");
		out.println("                        <a href=\"DangNhap.jsp\" class=\"btn btn-primary pull-right\">Đăng xuất</a>");
		out.println("                    </div>");
		out.println("                </div>");
		out.println("            </nav>");


	}

}
