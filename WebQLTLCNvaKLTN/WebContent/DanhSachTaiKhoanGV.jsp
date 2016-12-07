<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<sql:setDataSource driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/projectweb" user="root" password="root" />
<sql:query var="items"
	sql="SELECT id, username,password,myname,accessright,masv FROM users WHERE accessright='1'" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script>
	function get_rowID(row_id) {
		console.log(row_id);
		document.getElementById('row_id').value = row_id;
	}
</script>

<script>
	function get_ID(id_user) {
		console.log(id_user);
		document.getElementById('id_user').value = id_user;
	}
</script>
<!-- <script>
	function foo()
	{
	   alert("Submit button clicked!");
	   return true;
	}
</script> -->
</head>
<body>
	<div class="container">
	<form action="${pageContext.request.contextPath}/QuanLyTaiKhoanServlet" method="post" novalidate>
		<div class="row">
				<img src="header.jpg" class="img-rounded" alt="Cinque Terre"
					width="100%">
				<hr />
			</div>
			<div class="row">
				<nav class="navbar navbar-default">
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<li><a href="TrangChu.jsp"> Trang chủ</a></li>
						<li><a href="Quanlytaikhoan.jsp">Quản lý tài khoản</a></li>
						<li><a href="QuanLyDeTai.jsp" class="active">Quản lý đề
								tài</a></li>
						<li><a href="KiemTraSaoChep.jsp">Kiểm tra sao chép</a></li>
						<li><a href="SoSanh.jsp">So sánh</a></li>
						<li><a href="Loc.jsp">Lọc</a></li>
					</ul>
					<div style="padding-top: 8px;">
						<!--<label class="col-md-offset-3" style="padding-top:8px;">Admin</label>  -->
						<a href="DangNhapChung.jsp" class="btn btn-primary pull-right">Đăng
							xuất</a>
					</div>
				</div>
				</nav>
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
										<th>Tên đăng nhập</th>
										<th>Họ Tên</th>
										<th>Hiệu Chỉnh</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${items.rowsByIndex}" var="row">
										<tr>
											<%-- <c:forEach items="${row}" var="col">
												<td>${col}</td>
											</c:forEach> --%>
											<td>${row[5]}</td>
											<td>${row[1]}</td>
											<td>${row[3]}</td>
											<td class="text-center">
												<button type="button" class="btn btn-warning btn-xs">
													<span class="fa fa-unlock" aria-hidden="true"></span> Khóa
												</button>
												<button type="button" class="btn btn-info btn-xs"
													data-toggle="modal" data-target="#modalSua"
													data-username="${row[1]}"
													data-myname="${row[3]}"
													data-masv="${row[5]}"
													data-password="${row[2]}"
													onclick="get_rowID('${row[0]}')" name="btnsua">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Sửa
												</button>
												
												<button type="button" class="btn btn-danger btn-xs"
													data-toggle="modal" data-target="#modalXoa"
													data-masv="${row[5]}"
													onclick="get_ID('${row[0]}')" name="btnXoa">
													<span class="glyphicon glyphicon-minus" aria-hidden="true"></span>Xóa
												</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="panel panel-default">
					<div class="panel-body">
						<a href="TimDanhSachTaiKhoan.jsp" class="btn btn-primary">Trở về</a>
					</div>
				</div>
			</div>
			
	<!-- Modal xoa tai khoan -->
			<div class="modal fade" id="modalXoa" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4>Xóa Tài Khoản</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input type="hidden" id="id_user" name="id_user">
								<h5 class="modal-title"></h5>
							</div>
						</div>
						<div class="modal-footer">
							<input name="btnxoa" type="submit" class="btn btn-danger"
								value="Xóa">
							<button type="submit" class="btn btn-info" data-dismiss="modal">Hủy</button>
						</div>
					</div>
				</div>
			</div>
			<!-- End Modal Xóa tài khoản  -->
	
	<!-- modal sua tai khoan version 2-->
	<div class="modal fade" id="modalSua" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Sửa thông tin Tài Khoản</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="row_id" name="row_id">
					<div class="form-group">
						<label for="id">Mã GV</label> <input type="text"
							class="form-control" id="masv" name="masvsua" required>
					</div>
					<div class="form-group">
						<label for="inputlg">Tên:</label> <input type="text"
							class="form-control" id="ten" name="tensua" required>
					</div>

					<div class="form-group">
						<label for="inputlg">Email:</label> <input type="text"
							class="form-control" id="email" name="emailsua" required>
					</div>
					
					<div class="form-group">
						<label for="inputlg">Password:</label> <input type="text"
							class="form-control" id="pass" name="passsua" required>
					</div>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" name="btnsua" value="Sửa"></input>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="container">
		<div class="row"></div>
		<div id="footer">
			<hr>
			<h5 class="text-center text-danger">Khoa Công nghệ Thông tin -
				Đại học Sư phạm Kỹ thuật TP. Hồ Chí Minh</h5>
			<h5 class="text-center text-danger">Số 1, Võ Văn Ngân, Thủ Đức,
				TP. Hồ Chí Minh</h5>
		</div>
	</div>
	</form>
	</div>
	
	
	<script>
	$('#modalSua').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  
		  var username = button.data('username')
		  
		  console.log(username)
		  
		  var myname = button.data('myname')
		  
		  var masv = button.data('masv')
		  
		  var password = button.data('password')
		  
		  var modal = $(this)
		  modal.find('.modal-body input#masv').val(masv)
		  modal.find('.modal-body input#ten').val(myname)
		  modal.find('.modal-body input#email').val(username)
		  modal.find('.modal-body input#pass').val(password)
		});
	$('#modalXoa').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var msv = button.data('masv')
		   //Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  //alert(msp)
		  modal.find('.modal-title').text('Bạn có muốn xóa tài khoản "' + msv + '" không?')
		});
	</script>
	
</body>
</html>