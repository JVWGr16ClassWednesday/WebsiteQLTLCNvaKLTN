package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import utils.ConnectionDB;

/**
 * Servlet implementation class ThemDeTaiServlet
 */
@WebServlet("/ThemDeTaiServlet")
@MultipartConfig(maxFileSize = 16177215)
public class ThemDeTaiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThemDeTaiServlet() {
        super();
        // TODO Auto-generated constructor stub
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
					if(!item.isFormField()){
						String name = new File(item.getName()).getName();
						name_file = name;
						System.out.println(name_file);
						item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
						
					}
					else {
						map.put(item.getFieldName(), item.getString());
					}
				}
				System.out.println(name_file);
				System.out.println(map.get("tendt"));
				convertFromUTF8(map.get("tendt"));
				System.out.println(map.get("motadt"));
				System.out.println(map.get("selloaidt"));
				System.out.println(map.get("truongnhom"));
				System.out.println(map.get("masvnt"));
				Statement st = ConnectionDB.getConnection().createStatement();
				int i = 0;
				i = st.executeUpdate("insert into detai(tendt, motadt, loaidt, truongnhom, masvnt, thanhvien, masvtv, gvhd, magvhd, gvpb, magvpb, diem, nam, tailieu) "
						+ "values ('" + convertFromUTF8(map.get("tendt")) + "','" + convertFromUTF8(map.get("motadt")) + "','" + convertFromUTF8(map.get("selloaidt")) + "','" +  convertFromUTF8(map.get("truongnhom"))
						+ "','" +  map.get("masvnt") + "','" +  convertFromUTF8(map.get("thanhvien")) + "','" +  map.get("masvtv") + "','" +  convertFromUTF8(map.get("gvhd")) + "','" 
						+  map.get("idgvhd") + "','" +  convertFromUTF8(map.get("gvpb")) + "','" +  map.get("idgvpb") + "','" +  map.get("score") + "','" +  map.get("year") 
						+ "','" + name_file + "')");
				
				
				if(i>0){
					response.sendRedirect("XemDanhSachDeTai.jsp");
				}
				else
					response.sendRedirect("ThemDeTai.jsp");

				

				//File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}          

		}else{
			request.setAttribute("message",
					"Sorry this Servlet only handles file upload request");
		}


	}
}
