<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <img src="header.jpg" class="img-rounded" alt="Cinque Terre" width="100%">
            <hr />
        </div>
        <div class="row">
           <c:set var="accessright" value='<%=session.getAttribute("accessright") %>'></c:set>
        	<c:choose>
        		<c:when test="${accessright == 1}">
        			<tag:headerAD/>
        		</c:when>
        		<c:when test="${accessright == 2}">
        			<tag:headerGV/>
        		</c:when>
        		<c:when test="${accessright == 2}">
        			<tag:headerSV/>
        		</c:when>
        	</c:choose>
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-heading">Danh sách tài khoản Giảng Viên</div>
                <div class="panel-body">
                    <div class="table-responsive">
                        <table class="table table-striped table-bordered table-hover">
                            <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Mã GV</th>
                                    <th>Họ Tên</th>
                                    <th>Email</th>
                                    <th>Tên Đăng Nhập</th>
                                    <th>Mật Khẩu</th>
                                    <th>Tạo tài khoản</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>GV10112</td>
                                    <td>Nguyễn Trần Thi Văn</td>
                                    <td>abs@gmail.com</td>
                                    <td>GV10112</td>
                                    <td>GV10112</td>
                                    <td class="text-center">
                                        <input type="checkbox" />
                                    </td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>GV10122</td>
                                    <td>Trương Thị Ngọc Phượng</td>
                                    <td>abs@gmail.com</td>
                                    <td>GV10122</td>
                                    <td>GV10122</td>
                                    <td class="text-center">
                                        <input type="checkbox" />
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                        <a href="DanhSachTaiKhoanGiangVienDuocTao.jsp" class="btn btn-primary">Tạo tài khoản</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="panel panel-default">
                <div class="panel-body">
                    <a href="Quanlytaikhoan.jsp" class="btn btn-primary">Trở về</a>
                </div>
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