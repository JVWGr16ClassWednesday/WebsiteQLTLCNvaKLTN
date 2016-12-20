package controller;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.ConnectionDB;

/**
 * Servlet implementation class SuaDeTaiServlet
 */
@WebServlet("/SuaDeTaiServlet")
public class SuaDeTaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public SuaDeTaiServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String root = getServletContext().getRealPath("/");
		String UPLOAD_DIRECTORY = root + "SourceFile";
		System.out.println(UPLOAD_DIRECTORY);
		System.out.println(root);
		String name_file = " ";
		if(ServletFileUpload.isMultipartContent(request)){
			try {

				Map<String, String> map =new HashMap();

				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				for(FileItem item : multiparts){
					//nếu trường input có type là file
					if(!item.isFormField()){
						String name = new File(item.getName()).getName();
						name_file = name;
						item.write( new File(UPLOAD_DIRECTORY + File.separator + name));

					}
					else {
						map.put(item.getFieldName(), item.getString());
					}
				}
				
				Statement statement = null;
				Connection connection = ConnectionDB.getConnection();
				statement = connection.createStatement();
				String updateTableSQL = "UPDATE detai" + " SET "
						+ "tendt = '"+ convertFromUTF8(map.get("tendetai_them"))+"'" + ","
						+ "motadt = '" + convertFromUTF8(map.get("motadt_them"))+"'" + ","
						+ "loaidt ='" + convertFromUTF8(map.get("selloaidt_them"))+"'" + ","
						+ "truongnhom ='" + convertFromUTF8(map.get("truongnhom_them"))+"'" + ","
						+ "masvnt ='" + map.get("idnt_them")+"'" + ","
						+ "thanhvien ='" + convertFromUTF8(map.get("thanhvien_them"))+"'" + ","
						+ "masvtv='" + map.get("idtv_them")+"'" + ","
						+ "gvhd ='" + convertFromUTF8(map.get("gvhd_them"))+"'" + ","
						+ "magvhd ='" + map.get("idgvhd_them")+"'" +","
						+ "gvpb ='" + convertFromUTF8(map.get("gvpb_them"))+"'" + ","
						+ "magvpb ='" + map.get("idgvpb_them")+"'" + ","
						+ "diem ='" + map.get("score_them")+"'" +","
						+ "nam ='" + map.get("year_them")+"'" + ","
						+ "tailieu='" + name_file+"'"
						+ " WHERE id='"+ map.get("row_id")+"'"; 
				System.err.println(updateTableSQL);
				statement.execute(updateTableSQL);
				if(updateTableSQL!=null && !updateTableSQL.isEmpty()){
				    //processing here
					System.out.println("Record is updated to detai table!");
					response.sendRedirect("XemDanhSachDeTai.jsp");
				}
				else {
					System.out.println("Record don't update to detai table!");
				}
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}          

		}
		else{
			request.setAttribute("message",
					"Sorry this Servlet only handles file upload request");
		}
	}

}
