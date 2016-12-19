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
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <img src="header.jpg" class="img-rounded" alt="Cinque Terre" width="100%">
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
                <div class="panel-heading">Kiểm tra file thuyết minh</div>
                <div class="panel-body">
                    <div class="input-group">
                        <input type="file" />
                        <button class="btn btn-primary">Upload</button>

                    </div>
                    <div class="form-group">
                        <label for="name">Ngưỡng giống nhau:</label>
                        <input type="text" id="nguonggiongnhau"> %
                    </div>
                    <span class="input-group-btn">
                        <button class="btn btn-primary" type="button">Tìm kiếm</button>
                    </span>
                    <br />
                    <br />
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
                                <tr>
                                    <td>Phát triển ứng dụng đa nền tảng cho điện thoại di động</td>
                                    <td class="text-center"> TLCN</td>
                                    <td>Nguyễn Trần Thi Văn</td>
                                    <td class="text-center"><a href="ChiTietDeTai.jsp">Chi tiết</a></td>
                                </tr>
                                <tr>
                                    <td>Phát triển ứng dụng đa nền tảng cho điện thoại di động</td>
                                    <td class="text-center"> TLCN</td>
                                    <td>Nguyễn Trần Thi Văn</td>
                                    <td class="text-center"><a href="ChiTietDeTai.jsp">Chi tiết</a></td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
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