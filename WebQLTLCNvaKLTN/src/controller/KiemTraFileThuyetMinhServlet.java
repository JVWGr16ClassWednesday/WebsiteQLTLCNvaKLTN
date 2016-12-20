package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.spi.DirStateFactory.Result;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jdt.internal.compiler.env.IDependent;

import model.DeTai;
import service.QuanLyDeTai;
import utils.ConnectionDB;


@WebServlet("/KiemTraFileThuyetMinhServlet")
@MultipartConfig(maxFileSize = 16177215)
public class KiemTraFileThuyetMinhServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    
    public KiemTraFileThuyetMinhServlet() {
        super();
    }
    
  public double compareStrings(String stringA, String stringB) {
        return StringUtils.getJaroWinklerDistance(stringA, stringB);
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Statement statement = null;
		HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String root = getServletContext().getRealPath("/");
		String UPLOAD_DIRECTORY = root + "SourceFile";
		String name_file = " ";
		double kq;
		
		if(ServletFileUpload.isMultipartContent(request)){
			try {
				Map<String, String> map =new HashMap();
				
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

				for(FileItem item : multiparts){
					//khi mà input có type = file
					if(!item.isFormField()){
						String name = new File(item.getName()).getName();
						name_file = name;
						item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
						
					}
					else {
						map.put(item.getFieldName(), item.getString());
					}
				}
				
				String percents = map.get("txt_percent");
				System.out.println("số phần trăm người dùng nhập: "+percents);
				String cut_file_name = name_file.substring(0, name_file.lastIndexOf('.'));
				System.out.println(cut_file_name);
				String res = "";
				String cut_name_file_res;
				
				try (Connection connection = ConnectionDB.getConnection()) {
					statement = connection.createStatement();
					
					String sql = "SELECT * FROM detai";
					List<DeTai> deTais = new ArrayList<DeTai>();
					List<Double> result = new ArrayList<Double>();
					ResultSet resultSet = statement.executeQuery(sql);
					while (resultSet.next()){

						res = resultSet.getString("tailieu");
						cut_name_file_res = res.substring(0,res.lastIndexOf('.'));
						System.out.println(cut_name_file_res);
						kq = (compareStrings(cut_name_file_res, cut_file_name)*100);
						int id = resultSet.getInt("id");
						String tendt = resultSet.getString("tendt");
						String motadt = resultSet.getString("motadt");
						String loaidt = resultSet.getString("loaidt");
						String truongnhom = resultSet.getString("truongnhom");
						int masvnt = resultSet.getInt("masvnt");
						String thanhvien = resultSet.getString("thanhvien");
						int masvtv = resultSet.getInt("masvtv");
						String gvhd = resultSet.getString("gvhd");
						int magvhd = resultSet.getInt("magvhd");
						String gvpb = resultSet.getString("gvpb");
						int magvpb = resultSet.getInt("magvpb");
						float diem = resultSet.getFloat("diem");
						int nam = resultSet.getInt("nam");
						if(percents.equals("")){
							DeTai detai_1 = new DeTai();
							detai_1.setTendt(tendt);
							detai_1.setMotadt(motadt);
							detai_1.setLoaidt(loaidt);
							detai_1.setTruongnhom(truongnhom);
							detai_1.setMasvtn(masvnt);
							detai_1.setThanhvien(thanhvien);
							detai_1.setMasvtv(masvtv);
							detai_1.setGvhd(gvhd);
							detai_1.setMagvhd(magvhd);
							detai_1.setGvpb(gvpb);
							detai_1.setMagvpb(magvpb);
							detai_1.setDiem(diem);
							detai_1.setNam(nam);
							detai_1.setTailieu(res);
							deTais.add(detai_1);
							result.add(kq);
						}
						else {
							if(kq >= Double.parseDouble(percents))
							{
								DeTai detai_1 = new DeTai();
								detai_1.setTendt(tendt);
								detai_1.setMotadt(motadt);
								detai_1.setLoaidt(loaidt);
								detai_1.setTruongnhom(truongnhom);
								detai_1.setMasvtn(masvnt);
								detai_1.setThanhvien(thanhvien);
								detai_1.setMasvtv(masvtv);
								detai_1.setGvhd(gvhd);
								detai_1.setMagvhd(magvhd);
								detai_1.setGvpb(gvpb);
								detai_1.setMagvpb(magvpb);
								detai_1.setDiem(diem);
								detai_1.setNam(nam);
								detai_1.setTailieu(res);
								deTais.add(detai_1);
								result.add(kq);
								
							}
						}
					}
					if (deTais.isEmpty())
					{
						response.sendRedirect("KiemTraFileThuyetMinh.jsp");
					}
//					System.out.println(result);
					request.setAttribute("value_percen", deTais);
					request.setAttribute("value_kq", result);
					request.getRequestDispatcher("KiemTraFileThuyetMinh.jsp").forward(request, response);
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
				//File uploaded successfully
				request.setAttribute("message", "File Uploaded Successfully");
				System.out.println("File Uploaded Successfully");
			} catch (Exception ex) {
				request.setAttribute("message", "File Upload Failed due to " + ex);
			}          

		}else{
			request.setAttribute("message",
					"Sorry this Servlet only handles file upload request");
		}


	}
}
