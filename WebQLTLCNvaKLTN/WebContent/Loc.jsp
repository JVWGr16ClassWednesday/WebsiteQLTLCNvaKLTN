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
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
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
<script>
    	function getRow_ID(row_id){
     		console.log(row_id);
    		document.getElementById('row_id').value = row_id;
    	}
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<img src="header.jpg" class="img-rounded" alt="Cinque Terre"
				width="100%">
		</div>
		<div class="row">
			<c:set var="accessright"
				value='<%=session.getAttribute("accessright") %>'></c:set>
			<c:choose>
				<c:when test="${accessright == 0}">
					<tag:headerAD />
				</c:when>
				<c:when test="${accessright == 1}">
					<tag:headerGV />
				</c:when>
				<c:when test="${accessright == 2}">
					<tag:headerSV />
				</c:when>
			</c:choose>
		</div>
		<div class="row">
			<form action="LocDeTaiServlet" method="post" accept-charset="utf-8">
				<div class="panel panel-default">
					<div class="panel-heading">So sánh</div>
					<div class="panel-body">
						<label>Nhập thông tin: </label>
						<textarea class="form-control input-lg" rows="5"
							id="nhap_abstract" name="nhap_abstract"
							placeholder="Nhập vào nội dung tóm tắt, hoặc phần abstract của đề tài. Nội dung tối đa là 300 chữ..."></textarea>
						<br /> <br /> <span class="input-group-btn">
							<button class="btn btn-primary" type="submit">Tìm kiếm</button>
						</span>
					</div>
				</div>
			</form>
			<div class="table-responsive">
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
						<c:forEach items="${name}" var="tmp">
							<tr>
								<td><center>${tmp.getTendt()}</center></td>
								<td><center>${tmp.getLoaidt()}</center></td>
								<td><center>${tmp.getGvhd()}</center></td>

								<td>
									<center>
										<button type="button" class="btn btn-info btn-xs"
											data-toggle="modal" data-target="#XemChiTietDeTai"
											data-tendt="${tmp.getTendt()}"
											data-motadt="${tmp.getMotadt()}"
											data-loaidt="${tmp.getLoaidt()}"
											data-truongnhom="${tmp.getTruongnhom()}"
											data-masvtn="${tmp.getMasvtn()}"
											data-thanhvien="${tmp.getThanhvien()}"
											data-masvtv="${tmp.getMasvtv()}" data-gvhd="${tmp.getGvhd()}"
											data-magvhd="${tmp.getMagvhd()}" data-gvpb="${tmp.getGvpb()}"
											data-magvpb="${tmp.getMagvpb()}" data-diem="${tmp.getDiem()}"
											data-nam="${tmp.getNam()}" data-tailieu="${tmp.getTailieu()}"
											onclick="getRow_ID('${tmp.getId()}')">
											<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Chi
											tiết
										</button>
									</center>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


			</div>
		</div>
	</div>
	
	  <!-- Start Modal xem thông tin chi tiết đề tài -->
	<div class="modal fade" id="XemChiTietDeTai" role="dialog">
		<div class="modal-dialog modal-md">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Sửa thông tin Đề Tài</h4>
				</div>
				<div class="modal-body">
					<input type="hidden" id="row_id" name="row_id" />
					<div class="form-group">
						<label for="id">Tên đề tài:</label> 
						<input type="text" class="form-control" id="tendetai" name="tendetai_them" required>
					</div>
					<div class="form-group">
						<label for="inputlg">Mô tả đề tài:</label>
						<textarea class="form-control" rows="5" id="motadt" name="motadt_them"></textarea>
					</div>
					<div class="form-group">
						<label for="selloaidt">Loại đề tài:</label>
							<select class="form-control" id="selloaidt" name="selloaidt_them">
									<option>Tiểu luận chuyên ngành</option>
									<option>Khóa luận tốt nghiệp</option>
							</select>
					</div>
					<div class="form-group">
						<label for="name">Trưởng nhóm :</label> 
						<input type="text" class="form-control" id="truongnhom" name="truongnhom_them" required>
						<label for="name">Mã SV :</label>
						<input type="text" class="form-control" id="idnt" name="idnt_them" required>
					</div>
					<div class="form-group">
						<label for="name">Thành viên :</label>
						 <input type="text" class="form-control" id="thanhvien" name ="thanhvien_them"required>
						<label for="name">Mã SV :</label> 
						<input type="text" class="form-control" id="idtv" name="idtv_them" required>
					</div>
					<a href="ThemSinhVien.jsp">Thêm sinh viên</a>
					<div class="form-group">
						<label for="name">Giáo Viên hướng dẫn :</label> 
						<input type="text" class="form-control" id="gvhd" name="gvhd_them" required>
						<label for="name">Mã GVHD :</label> 
						<input type="text" class="form-control" id="idgvhd" name="idgvhd_them" required>
					</div>
					<div class="form-group">
						<label for="name">Giáo Viên phản biện :</label> 
						<input type="text" class="form-control" id="gvpb" name="gvpb_them" required>
						<label for="name">Mã GVPB :</label> 
						<input type="text" class="form-control" id="idgvpb" name="idgvpb_them" required>
					</div>
					<div class="form-group">
						<label for="name">Điểm đề tài:</label> 
						<input type="text" class="form-control" id="score" name="score_them" required> 
						<label for="name">Năm :</label> 
						<input type="text" class="form-control" id="year" name="year_them" required>
					</div>
					<div class="form-group">
						<label for="name">Tài liệu đính kèm:</label> 
						<input type="hidden" id="file" name="file">
						<a id="file_2" href="#">Tai Lieu</a>
						<input type="file" name="file_them"/>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-danger" data-dismiss="modal">Đóng</button>
				</div>
			</div>
		</div>
	</div>
	<!-- End Modal xem thông tin chi tiết đề tài -->
	
	<script type="text/javascript">
	$('#XemChiTietDeTai').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  
		  var tendt = button.data('tendt')
		  
		  var motadt = button.data('motadt')
		  
		  var loaidt = button.data('loaidt')
		  
		  var truongnhom = button.data('truongnhom')
		  
		  var masvtn = button.data('masvtn')
		  
		  var thanhvien = button.data('thanhvien')
		  
		  var masvtv = button.data('masvtv')
		  
		  var gvhd = button.data('gvhd')
		  
		  var magvhd = button.data('magvhd')
		  
		  var gvpb = button.data('gvpb')
		  
		  var magvpb = button.data('magvpb')
		  
		  var diem = button.data('diem')
		  
		  var nam = button.data('nam')
		  
		  var tailieu = button.data('tailieu')
		  console.log(tailieu)
		  
		 /*  var tailieu = button.data('tailieu') */
		  var modal = $(this)
		  modal.find('.modal-body input#tendetai').val(tendt)
		  modal.find('.modal-body textarea#motadt').val(motadt)
		  modal.find('.modal-body option#selloaidt').text(loaidt)
		  modal.find('.modal-body input#truongnhom').val(truongnhom)
		  modal.find('.modal-body input#idnt').val(masvtn)
		  modal.find('.modal-body input#thanhvien').val(thanhvien)
		  modal.find('.modal-body input#idtv').val(masvtv)		  
		  modal.find('.modal-body input#gvhd').val(gvhd)
		  modal.find('.modal-body input#idgvhd').val(magvhd)
		  modal.find('.modal-body input#gvpb').val(gvpb)
		  modal.find('.modal-body input#idgvpb').val(magvpb)
		  modal.find('.modal-body input#score').val(diem)
		  modal.find('.modal-body input#year').val(nam)
		  modal.find('.modal-body input#file').val(tailieu)
		  console.log
		  modal.find('.modal-body #file_2').prop('href',"DownLoadTaiLieuServlet?file="+tailieu)
		});
	</script>
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
</body>
</html>