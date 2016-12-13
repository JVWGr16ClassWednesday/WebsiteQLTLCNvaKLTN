<%@page import="utils.ConnectionDB"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <img src="header.jpg" class="img-rounded" alt="Cinque Terre" width="100%">
        </div>
        <div class="row">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="TrangChu.jsp"> Trang chủ</a>
                        </li>
                        <li><a href="Quanlytaikhoan.jsp">Quản lý tài khoản</a></li>
                        <li><a href="QuanLyDeTai.jsp">Quản lý đề tài</a></li>
                        <li><a href="KiemTraSaoChep.jsp">Kiểm tra sao chép</a></li>
                        <li><a href="SoSanh.jsp">So sánh</a></li>
                        <li class="active"><a href="Loc.jsp">Lọc</a></li>
                    </ul>
                    <div style="padding-top:8px;">
                        <!--<label class="col-md-offset-3" style="padding-top:8px;">Admin</label>  -->
                        <a href="DangNhapChung.jsp" class="btn btn-primary pull-right">Đăng xuất</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="row">
        	<form action="LocDeTaiServlet" method="post" accept-charset="utf-8">
        		<div class="panel panel-default">
                	<div class="panel-heading">So sánh</div>
	                <div class="panel-body">
	                    <label>Nhập thông tin: </label>
	                    <textarea class="form-control input-lg" rows="5" id="nhap_abstract" name="nhap_abstract" placeholder="Nhập vào nội dung tóm tắt, hoặc phần abstract của đề tài. Nội dung tối đa là 300 chữ..."></textarea>
	                    <br />
	                    <br />
	                    <span class="input-group-btn">
	                        <button class="btn btn-primary" type="submit">Tìm kiếm</button>
	                    </span>
	                </div>
            	</div>
        	</form>
            <div class="table-responsive">
			<input type="text" name="myhiddenvalue" value="<%=request.getAttribute("name") %>" />
                <table class="table table-striped table-bordered table-hover">
                    <thead>
                        <tr>
                            <th class="text-center">Tên đề tài</th>
                            <th class="text-center">Loại đề tài</th>
                            <th class="text-center">Giáo viên hướng dẫn</th>
                            <th class="text-center">Chi tiết</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                        	Statement statement = null;
                        
                        try
                        {
                        	Connection connection = ConnectionDB.getConnection();
                        	statement = connection.createStatement();

                                String sqlString = "SELECT * FROM detai WHERE id ='" + request.getAttribute("name") + "'" ;
                                
                                ResultSet rs= statement.executeQuery(sqlString);
                                
                                if(!rs.isBeforeFirst())
                                {
                                    %>
                                        <tr>
                                        <td colspan="3"><center><%out.print("No Files!"); %></center></td>
                                        </tr>
                                    <%
                                }    
                                
                                while(rs.next())
                                {   
                            %>
                                    <tr>
                                        <td><center><%out.print(rs.getString("tendt")); %></center></td>
                                        <td><center><%out.print(rs.getString("loaidt")); %></center></td>
                                        <td><center><%out.print(rs.getString("gvhd")); %></center></td>
                                        <td>
                                        <center>
                                        <a href="view_file.jsp?id=<%out.print(rs.getString("id"));%>">Chi Tiết</a>
                                        </center>
                                        </td>
                                    </tr>
                            <%
                                }
                            %>
                            
                </tbody> 
        </table>
                            
                            <%
                                rs.close();
                            statement.close();
                            statement.close();
                        }catch(Exception e){e.printStackTrace();}    
                        
                    %>
                        
                 <!--    </tbody>
                </table> -->
            </div>
        </div>
    </div>
    <div class="container">
     <div class="row">
     </div>
        <div id="footer">
        <hr>
            <h5 class="text-center text-danger">Khoa Công nghệ Thông tin - Đại học Sư phạm Kỹ thuật TP. Hồ Chí Minh</h5>
            <h5 class="text-center text-danger">Số 1, Võ Văn Ngân, Thủ Đức, TP. Hồ Chí Minh</h5>
        </div>
    </div>
</body>
</html>