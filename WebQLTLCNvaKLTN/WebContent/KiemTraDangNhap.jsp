<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery.validate.min.js"></script> 
</head>
<body>
	<c:if test="${ empty param.txtTenTaiKhoanDN or empty param.txtMatKhauDN}">
      <c:redirect url="DangNhap.jsp" >
              <c:param name="errMsg" value="Vui lòng nhập tên đăng nhập và mật khẩu" />
      </c:redirect>
       
    </c:if>
    <c:if test="${not empty param.txtTenTaiKhoanDN and not empty param.txtMatKhauDN}">
      <s:setDataSource var="ds" driver="com.mysql.jdbc.Driver"
                       url="jdbc:mysql://localhost:3306/projectweb"
                       user="root" password="root"/>
 
      <s:query dataSource="${ds}" var="selectQ">
        select count(*) as kount from users
        where username='${param.txtTenTaiKhoanDN}'
        and password='${param.txtMatKhauDN}'and accessright='1'
      </s:query>
 
      <c:forEach items="${selectQ.rows}" var="r">
        <c:choose>
          <c:when test="${r.kount gt 0}">
            <c:set scope="session"
                   var="loginUser"
                   value="${param.txtTenTaiKhoanDN}"/>
            <c:redirect url="ChucNangAdmin.jsp"/>
          </c:when>
          <c:otherwise>
            <c:redirect url="DangNhap.jsp">
              <c:param name="errMsg" value="Tên đăng nhập/mật khẩu không tồn tại" />
            </c:redirect>
          </c:otherwise>
        </c:choose>
      </c:forEach>
 
    </c:if>
</body>
</html>