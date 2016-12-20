<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/tlds/taglib.tld" prefix="tag"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="s" %>

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
        <%--tạo một biến là accessright có giá trị được lấy từ session. --%>
        <c:set var="accessright" value='<%=session.getAttribute("accessright") %>'></c:set>
        <%--thẻ c:choose là thẻ điều kiện, trong thẻ c:choose có thể đặt hai thẻ là <c:when> hoặc <c:otherwise> --%>
        	<c:choose>
        		<%--Khi mà accessright la 0 thì áp dung tag của admin. tag này được khai báo trong WEBIF file tlds/taglib.tld, nội dung của tag được thể hiện ở class tag. --%>
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