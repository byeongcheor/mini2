<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<c:if test="${result==0}">
	<script>
		history.back();
	</script> 
</c:if>

<c:if test="${result>0}">
	<c:redirect url="/course/courseList"/>
</c:if>
</body>
</html>

