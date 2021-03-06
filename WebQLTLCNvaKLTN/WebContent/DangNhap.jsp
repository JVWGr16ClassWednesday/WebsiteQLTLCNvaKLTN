<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <script src="js/bootstrap.min.js"></script>
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.9/jquery.validate.min.js"></script>
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
	<%--Khi user dang nhap vao thi form se chạy tới class Controller vào servler KiemTraDangNhapServlet thực hiện kiểm tra đăng nhập --%>
	<form  action="KiemTraDangNhap" method="post" id="FrmDN" novalidate="novalidate">
	<%-- <input type="text" value="<%=session.getAttribute("error")%>" > --%>
		<div class="container">
        <div class="row">
            <img src="header.jpg" class="img-rounded" alt="Cinque Terre" width="100%">
        </div>
        <div class="modal-dialog modal-sm">
            <!-- Modal content-->
            <div class="modal-content">
                <div class="modal-header">
                    <h4 class="pannel-header">Đăng nhập</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <input type="text" name="txtTenTaiKhoanDN" id="txtTenTaiKhoanDN" class="form-control required"
                               placeholder="Tên tài khoản ..." required>
                    </div>
                    <div class="form-group">
                        <input type="password" id="txtMatKhauDN" name="txtMatKhauDN" class="form-control required " placeholder="Mật khẩu ..." required>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" >Đăng nhập </button>
                    <a class="btn btn-danger" href="TrangChu.jsp">Hủy</a>
                </div>
                <font color="red">
                <%--Khi user đăng nhập, servlet sẽ kiểm tra xem tài khoản đó có hợp lệ hay không: hợp lệ là không bị khóa,
                 access đúng theo username, nếu không hợp lệ servlet sẽ trả về session một thông báo --%>
               <c:set var="er" value='<%=session.getAttribute("error")%>'></c:set>
		        <c:if test="${er == true }">
		            <c:out value="Tên đăng nhập hoặc mật khẩu sai" />
		        </c:if>
		        </font>
            </div>
        </div>
        
    	</div>
	</form>
    
        <div class="container">
     <div class="row">
     </div>
        <div id="footer">
        <hr>
            <h5 class="text-center text-danger">Khoa Công nghệ Thông tin - Đại học Sư phạm Kỹ thuật TP. Hồ Chí Minh</h5>
            <h5 class="text-center text-danger">Số 1, Võ Văn Ngân, Thủ Đức, TP. Hồ Chí Minh</h5>
        </div>
    </div>
    
    <!-- jQuery Form Validation code -->
  <script>
  
/*   // When the browser is ready...
 */ 
 <%-- jquery này sẽ thực hiện kiểm tra validate các trường input ở form trên sau dấu #là id của form.
  Hàm này được thực hiện khi người dùng click vào button có type="submit"--%>
 $(function() {
  
    // Setup form validation on the #register-form element
    $("#FrmDN").validate({
    
        // Specify the validation rules
        rules: {
        	txtTenTaiKhoanDN: "required",
            txtMatKhauDN: {
                required: true,
                minlength: 3
            },
        },
        
        // Specify the validation error messages
        messages: {
        	txtTenTaiKhoanDN: "Bạn chưa nhập tên đăng nhập",
            txtMatKhauDN: {
                required: "Bạn chưa nhập mật khẩu",
                minlength: "Mật khẩu ít nhất là 3 ký tự"
            }
        },
        
        submitHandler: function(form) {
            form.submit();
        }
    });

  });
  
  </script>
</body>
</html>