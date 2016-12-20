<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>

<sql:setDataSource driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/projectweb" user="root" password="root" />
<sql:query var="items"
	sql="SELECT id, username,password,myname,accessright,masv, khoatk FROM users WHERE accessright='1'" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<%--Định nghĩa lại style boostrap chỉnh sửa màu sắc của table --%>
<style>
	table {
	    border-collapse: collapse;
	    width: 100%;
	}
	
	th, td {
	    text-align: left;
	    padding: 8px;
	}
	
	tr:nth-child(even){background-color: #f2f2f2}
	
	th {
	    background-color: #4CAF50;
	    color: white;
}
</style>

<%--đoạn script lấy id khi thực hiện click vào button, truyền id đó vào id của một trường input có id  là row_id --%>
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

<%-- <script>
	function show_id(user_id) {
		console.log(user_id);
		
	}
</script>  --%>


<script>
	function change_icon(id) {
		// truyền nội dung id vào trường input có id là user_id
		document.getElementById('user_id').value = id;
		//lấy id của dòng hiện đang select 
		var btnKhoa = document.getElementById("id_khoa_" + id);
		//console.log('btnKhoa.value');
		//kiểm tra nếu nội dung của btn khóa, text của Span là  Khóa, thì set lại thẻ <span> với icon là ổ khóa đóng và text hiển thị lên mà Mở Khóa
		if (btnKhoa.innerText == ' Khóa') {
			btnKhoa.innerHTML = '<span class="fa fa-lock" aria-hidden="true"></span> Mở khóa'
			//gán giá trị là Khoa cho trường input có id là value_btnkhoa
			document.getElementById('value_btnkhoa').value = 'Khoa';
		}
		//ngược lại cái ở trên
		else {
			btnKhoa.innerHTML = '<span class="fa fa-unlock" aria-hidden="true"></span> Khóa'
			document.getElementById('value_btnkhoa').value = 'Mo khoa';
		}
Z	}
</script>

</head>
<body>
	<div class="container">
	<form action="${pageContext.request.contextPath}/QuanLyTaiKhoanServlet" method="post" novalidate>
		<input type="hidden" id="user_id" name="user_id">
		<input type="hidden" id="value_btnkhoa" name="value_btnkhoa">
		<div class="row">
				<img src="header.jpg" class="img-rounded" alt="Cinque Terre"
					width="100%">
				<hr />
			</div>
			<div class="row">
				<c:set var="accessright" value='<%=session.getAttribute("accessright") %>'></c:set>
        	<c:choose>
        		<c:when test="${accessright == 0}">
        			<tag:headerAD/>
        		</c:when>
        		<c:when test="${accessright == 1}">
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
												<button type="submit"
														name="btnkhoa"
														class="btn btn-warning btn-xs" 
														id="id_khoa_${row[0]}" 
														onclick="change_icon(${row[0]})">
														<c:choose>
											        		<c:when test="${row[6] == 1}">
											        			<span class="fa fa-unlock" aria-hidden="true"></span> Khóa
											        		</c:when>
											        		<c:when test="${row[6] == 2}">
											        			<span class="fa fa-lock" aria-hidden="true"></span> Mở khóa
											        		</c:when>
											        	</c:choose>
												</button>
												<button type="button" class="btn btn-info btn-xs"
													data-toggle="modal" data-target="#modalSua"
													data-username="${row[1]}"
													data-myname="${row[3]}"
													data-masv="${row[5]}"
													data-password="${row[2]}"
													data-access = "${row[4]}"
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
			
			</form>
	
	<!-- modal sua tai khoan version 2-->
	<form action="SuaTaiKhoanServlet" method="post">
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
					
					<div class="form-group">
						<label for="inputlg">Access right:</label>
						<br/>
						<label><input type="radio" value="1" name="Check_Quyen" id="accessgv">&nbsp;Quyền Giảng Viên</label>
	                        <br />
	                        <br />
	                    <label><input type="radio" value="2" name="Check_Quyen" id="accesssv">&nbsp;Quyền Sinh Viên</label>
					</div>
					
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" name="btnsua" id="btnsua" value="Sửa"></input>
					<button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>
	
	</form>
	
	
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
	
	</div>
	
	
	<script>
	$('#modalSua').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  
		  var username = button.data('username')
		  
		  console.log(username)
		  
		  var myname = button.data('myname')
		  
		  var masv = button.data('masv')
		  
		  var password = button.data('password')
		  
		 var access = button.data('access')
		 
		 console.log(access)
		
		  var modal = $(this)	
		  modal.find('.modal-body input#masv').val(masv)
		  modal.find('.modal-body input#ten').val(myname)
		  modal.find('.modal-body input#email').val(username)
		  modal.find('.modal-body input#pass').val(password)
		  if(access == 1)
			{
				 modal.find('.modal-body input#accessgv').prop('checked', true)
				 modal.find('.modal-body input#accesssv').prop('checked', false)
		  	}
			else if (access == 2)
			{
				modal.find('.modal-body input#accessgv').prop('checked', false)
				modal.find('.modal-body input#accesssv').prop('checked', true)
			}
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