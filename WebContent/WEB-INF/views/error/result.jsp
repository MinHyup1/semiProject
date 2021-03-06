<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta charset="UTF-8">
 
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
	
	<%-- 브라우저 응답 처리 --%>
	
	<%-- 안내창 출력 --%>
	<c:if test="${not empty msg}">  /* msg가 존재한다면 실행 */
		alert("${msg}");
	</c:if>
	
	<%-- 뒤로가기 --%>
	<c:if test="${not empty back}">
		history.back();
	</c:if>
	
	<%-- 페이지 이동 --%>
	<c:if test="${not empty url}">
		location.href="${url}";
	</c:if>
	
	<%-- 뒤로가기 --%>
	<c:if test="${not empty close}">
		window.self.close();
	</c:if>
	
	</script>

</body>
</html>