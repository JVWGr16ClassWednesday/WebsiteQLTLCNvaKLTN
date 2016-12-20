<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<sql:setDataSource driver="com.mysql.jdbc.Driver"
	url="jdbc:mysql://localhost/projectweb" user="root" password="root" />
<sql:query var="items" sql="SELECT id, tendt, motadt, loaidt,truongnhom,masvnt,thanhvien,masvtv,gvhd,magvhd,gvpb,magvpb,diem,nam,tailieu FROM detai" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/font-awesome.min.css">
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

<script>
    	function get_ID(id_detai){
     		console.log(id_detai);
    		document.getElementById('id_detai').value = id_detai;
    	}
</script>
</head>
<body>
	<div class="container">
		<form action="SuaDeTaiServlet" method="post" novalidate="novalidate" enctype="multipart/form-data" accept-charset="utf-8">
			<div class="row">
				<img src="header.jpg" class="img-rounded" alt="Cinque Terre"
					width="100%">
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
					<div class="panel-heading">Danh sách đề tài</div>
					<div class="panel-body">
						<div class="table-responsive">
							<table class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th>STT</th>
										<th>Tên Đề Tài</th>
										<th>Loại Đề Tài</th>
										<th>GVHD</th>
										<th>Tuỳ Chỉnh</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${items.rowsByIndex}" var="row">
										<tr>
											<td>${row[0]}</td>
											<td>${row[1]}</td>
											<td>${row[3]}</td>
											<td>${row[8]}</td>
											<%-- <c:forEach items="${row}" var="col">
												<td>${col}</td>
											</c:forEach> --%>
											<td class="text-center">
												<button type="button" class="btn btn-info btn-xs"
													data-toggle="modal" data-target="#ChinhSuaDeTai"
													data-tendt="${row[1]}" 
													data-motadt="${row[2]}"
													data-loaidt="${row[3]}" 
													data-truongnhom="${row[4]}"
													data-masvtn="${row[5]}"
													data-thanhvien="${row[6]}"
													data-masvtv ="${row[7]}"
													data-gvhd="${row[8]}" 
													data-magvhd="${row[9]}"
													data-gvpb="${row[10]}" 
													data-magvpb="${row[11]}"
													data-diem="${row[12]}" 
													data-nam="${row[13]}"
													data-tailieu ="${row[14]}"
													onclick="getRow_ID('${row[0]}')">
													<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>Sửa
												</button>
												<button type="button" class="btn btn-danger btn-xs"
													data-toggle="modal" 
													data-target="#modalXoa"
													data-tendetai="${row[1]}"
													onclick="get_ID('${row[0]}')">
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
	</div>
	<!-- Start Modal sửa thông tin đề tài -->
	<div class="modal fade" id="ChinhSuaDeTai" role="dialog">
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
					<button type="submit" class="btn btn-primary" name="btnaction">Sửa</button>
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
	<form action="XoaDeTaiServlet" method="post" novalidate="novalidate" accept-charset="utf-8">
		<!-- Start Modal xóa đề tài -->
		<div class="modal fade" id="modalXoa" role="dialog">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal">&times;</button>
							<h4>Xóa Đề Tài</h4>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<input type="hidden" id="id_detai" name="id_detai">
								<h5 class="modal-title"></h5>
							</div>
						</div>
						<div class="modal-footer">
							<input name="btnxoa" type="submit" class="btn btn-danger" value="Xóa">
							<button type="" class="btn btn-info" data-dismiss="modal">Hủy</button>
						</div>
					</div>
				</div>
			</div>
		<!-- End Modal Xóa Đề Tài  -->
	</form>
	</div>
	
	
	
	<script type="text/javascript">
	$('#ChinhSuaDeTai').on('show.bs.modal', function (event) {
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
	$('#modalXoa').on('show.bs.modal', function (event) {
		  var button = $(event.relatedTarget) // Button that triggered the modal
		  var tendetai = button.data('tendetai')
		 
		   //Extract info from data-* attributes
		  // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
		  // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
		  var modal = $(this)
		  //alert(msp)
		  modal.find('.modal-title').text('Bạn có muốn xóa đề tài "' + tendetai + '" không?')
		});
	</script>
</body>
</html>