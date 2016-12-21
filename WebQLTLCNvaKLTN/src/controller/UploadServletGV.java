package controller;
import java.sql.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.io.File;
import java.io.FileNotFoundException;

import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.jasper.tagplugins.jstl.core.ForEach;

//import com.mysql.jdbc.PreparedStatement;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")

public class UploadServletGV extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	}
	
	//khi upload can dung phuong thuc post
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		String type=request.getParameter("file");
		String name=request.getParameter("folder");
		System.out.println(name);
		
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String filePath1;
		int maxFileSize = 5000*1024;
		int maxMemSize = 5*1024;
		
		//String root = getServletContext().getRealPath("/");
		//String UPLOAD_DIRECTORY = root + "SourceFile";
		
		 filePath1 = getServletContext().getInitParameter("file_upload");
		 System.out.println("file path 1" + filePath1);
		 String filePath = filePath1+name;
		 response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
		 if(!isMultipart){
			 out.print("file not upload");
			 return;
		 }
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(maxFileSize);
			factory.setRepository(new File("C:\\temp"));
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setFileSizeMax(maxFileSize);
			try {
				List fileItems = upload.parseRequest(request);
				Iterator i = fileItems.iterator();
				while(i.hasNext()){
					FileItem fi = (FileItem) i.next();
					if(!fi.isFormField()){
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						System.out.println("file name "+ fieldName);
						String value = fi.getString();
						System.out.println("value "+ value);
						
						String[] lines = value.split(System.getProperty("line.separator"));
						for (String string : lines) {
							String values[]= string.split(",");
							//	 String id=values[0];
								 String Name =values[0];
								 String username = values[1];
								 String masv = values[2];
								 String password =masv+(int)(((Math.random())*100)+1);
								 String acc = "1";
								
						            PrintWriter pw = response.getWriter(); 
						            Connection conn=null;
						            String url="jdbc:mysql://localhost:3306/projectweb?rewriteBatchedStatements=true&relaxAutoCommit=true";
						            String dbName="projectweb";
						            String driver="com.mysql.jdbc.Driver";
						            String query1 = "INSERT INTO users ("
						            	  //  + " id,"
						            	    + " username,"
						            	    + " password,"
						            	    + " myname,"
						            	    + " accessright,"
						            	    + "masv ) VALUES (?, ?, ?, ?, ?)";
						            Class.forName(driver).newInstance();  
						            conn = DriverManager.getConnection(url,"root", "root");
						            PreparedStatement pst =(PreparedStatement) conn.prepareStatement(query1);

						         //   pst.setString(1,id);  
						            pst.setString(1,username);        
						            pst.setString(2,password);
						            pst.setString(3,Name);
						            pst.setString(4,acc);
						            pst.setString(5,masv);
						            
						            int k = pst.executeUpdate();
						            conn.commit(); 
						            String msg=" ";
						            if(k!=0){  
						             // msg="Record has been inserted";
						              pw.println("<font size='6' color=blue>" + msg + "</font>"); }
						}
						
						String contentType = fi.getContentType();
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						
						// upload file lên server
						 File file;
						if(fileName.lastIndexOf("\\")>=0)
						 {
							 
							 file = new File(filePath1+fileName.substring(fileName.lastIndexOf("\\")));
						 }
						 else
						 {
							 file = new File(filePath1+fileName.substring(fileName.lastIndexOf("\\")+1));
						 }
						 fi.write(file);
						
						 out.println ("<html><body><script>alert('upload thanh cong!');</script></body></html>");
						 response.sendRedirect("DanhSachTaiKhoanGV.jsp");
						 
						 file = new File(fileName);
						 System.out.println("test----> "+file);					 
						}
				
				}
			} catch (Exception e) {
				System.out.println("Exception : " + e.toString());
				System.out.println("File upload failed");
			}
		
	}
}