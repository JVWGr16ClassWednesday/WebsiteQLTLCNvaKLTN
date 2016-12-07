<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.validate.min.js"></script>
    <style type="text/css">
		label {
			display: inline-block;
		}
		label.error {
			display: inline-block;
			color:red;
			width: 200px;
		}
	</style>
</head>
<body>
	<form method="post" action="DangKyTaiKhoanServlet" accept-charset="utf-8" id="formTaoMoiTK">
		<div class="container">
        <div class="row">
            <img src="header.jpg" class="img-rounded" alt="Cinque Terre" width="100%">
            <hr />
        </div>
        <div class="row">
            <nav class="navbar navbar-default">
                <div class="container-fluid">
                    <ul class="nav navbar-nav">
                        <li>
                            <a href="TrangChu.jsp"> Trang chủ</a>
                        </li>
                        <li class="active"><a href="Quanlytaikhoan.jsp">Quản lý tài khoản</a></li>
                        <li><a href="QuanLyDeTai.jsp">Quản lý đề tài</a></li>
                        <li><a href="KiemTraSaoChep.jsp">Kiểm tra sao chép</a></li>
                        <li><a href="SoSanh.jsp">So sánh</a></li>
                        <li><a href="Loc.jsp">Lọc</a></li>
                    </ul>
                    <div style="padding-top:8px;">
                        <a href="DangNhapChung.jsp" class="btn btn-primary pull-right">Đăng xuất</a>
                    </div>
                </div>
            </nav>
        </div>
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="panel panel-primary">
                    <div class="panel-heading text-center">Tạo mới tài khoản</div>
	                    <div class="panel-body">
	                        <div class="form-group">
	                            <label for="id">Mã GV/ Mã SV</label>
	                            <input type="text" class="form-control" id="id" name="id" required>
	                        </div>
	                        <div class="form-group">
	                            <label for="name">Họ tên</label>
	                            <input type="text" class="form-control" id="name" name="name" required>
	                        </div>
	                        <div class="form-group">
	                            <label for="email">Email</label>
	                            <input type="email" class="form-control" id="email" name="email" required>
	                        </div>
	                         <div class="form-group">
	                            <label for="name">Mật khẩu</label>
	                            <input type="password" class="form-control" id="password" name="password" required>
	                        </div>
	                        <label>Quyền hệ thống</label>
	                        <br />
	                        <br />
	                        <label><input type="radio" value="1" name="Check_Quyen" checked>&nbsp;Quyền Giảng Viên</label>
	                        <br />
	                        <br />
	                        <label><input type="radio" value="2" name="Check_Quyen" value="">&nbsp;Quyền Sinh Viên</label>
	                    </div>
	                    <input type="hidden" name="isSuccess" value="1"/>
	                    <div class="panel-footer text-center">
	                     	<button id="TaoTK" class="btn btn-primary" type="submit">Tạo tài khoản</button>
	                        <a href="TrangChu.jsp" target="" class="btn btn-danger">Hủy</a>
	                    </div>
                </div>
            </div>
        </div>
    </div>
	</form>
	
	<script type="text/javascript">
	$(document).ready(function() {
		//Khi bàn phím được nhấn và thả ra thì sẽ chạy phương thức này

		$("#formTaoMoiTK").validate({
			rules: {
				id: "required",
				name: "required",
				email: {
					required: true,
					email: true
				},
			},
			messages: {
				id: "Vui lòng nhập mã SV hoặc mã GV",
				name: "Vui lòng nhập tên",
				email: "Vui lòng nhập Email"
			}
		});
	});
	</script>
	<script type="text/javascript">
	$("#TaoTK").on("click",function(){
	    if (($("input[name*='Check_Quyen']:checked").length)<=0) {
	        alert("Bạn phải chọn quyền");
	        return false;
	    }
	    
	});
</script>
    <div class="modal fade" id="myModal" role="dialog" >
        <div class="modal-dialog modal-sm">
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="modal-title">Thông báo</h4>
                </div>
                <div class="modal-body">
                    <p>Tài khoản đã được tạo thành công</p>
                </div>
                <div class="modal-footer">
                    <a href="DanhSachTaiKhoanSV.jsp" class="btn btn-default">Đóng</a>
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