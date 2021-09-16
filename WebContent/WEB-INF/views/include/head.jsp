<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<meta charset="UTF-8">
<title>MEDIBOOK</title>
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<script type="text/javascript" src="${contextPath}/resources/js/web-util.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="resources/js/bootstrap.js"></script>
<script src="resources/js/klorofil-common.js"></script>
<html>
<!-- <head>

</head> -->
<body>
<!-- WRAPPER -->
	<div id="wrapper">
		<!-- NAVBAR -->
		<nav class="navbar navbar-default navbar-fixed-top">
			<div class="brand">
				<a href="메인화면.jsp"><i class="fas fa-plus-square"></i> MEDIBOOK</a>
			</div>
			<div class="container-fluid">
				<div class="navbar-btn">
					<button type="button" class="btn-toggle-fullwidth"><i class="far fa-arrow-alt-circle-left"></i></button>
				</div>
				<div class="navbar-btn navbar-btn-right">
					<a class="btn btn-success update-pro" title="" target="" href="로그인페이지.jsp"> <span>login</span></a>
					<a class="btn btn-success update-pro" title="" target="" href="회원가입페이지.jsp"> <span>회원가입</span></a>
				</div><!-- 로그아웃 버튼 추가 예정 -->
				<div id="navbar-menu">
					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown">
							<a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
								<i class="far fa-bell"></i> <!--종 아이콘  -->
								<span class="badge bg-danger">5</span> <!-- 종아이콘에 나오는 숫자 -->
							</a>
							<ul class="dropdown-menu notifications"> <!-- 종버튼 알람부분 -->
								<li><a href="#" class="notification-item"><span class="dot bg-warning"></span>System space is almost full</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-danger"></span>You have 9 unfinished tasks</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-success"></span>Monthly report is available</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-warning"></span>Weekly meeting in 1 hour</a></li>
								<li><a href="#" class="notification-item"><span class="dot bg-success"></span>Your request has been approved</a></li>
								<li><a href="#" class="more">See all notifications</a></li>
							</ul>
						</li>
						<li class="dropdown"> <!-- dropdown open -->
							<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fas fa-user-circle"></i>  <span>user-name</span>  <i class="fas fa-chevron-down"></i></a>
							<ul class="dropdown-menu">
								<li><a href="개인정보수정.jsp"><i class="far fa-user"></i> <span>My Profile</span></a></li>
								<li><a href="로그아웃"><i class="fas fa-sign-out-alt"></i> <span>Logout</span></a></li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</nav>
		<!-- END NAVBAR -->
		<!-- LEFT SIDEBAR -->
		<div id="sidebar-nav" class="sidebar">
			<div class="sidebar-scroll">
				<nav>
					<ul class="nav">
						<li><a href="병원검색.jsp" class=""><i class="fas fa-hospital"></i> <span>병원 검색</span></a></li>
						<li><a href="약국검색.jsp" class=""><i class="fas fa-vial"></i> <span>약국 검색</span></a></li>
						<li><a href="의약품검색.jsp" class=""><i class="fas fa-pills"></i> <span>의약품 검색</span></a></li>
						<li><a href="스케줄.jsp" class=""><i class="far fa-calendar-check"></i> <span>진료 스케줄</span></a></li>
						<li><a href="게시판.jsp" class=""><i class="fas fa-list"></i> <span>게시판</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
		<!-- END LEFT SIDEBAR -->
		<!-- MAIN -->
		<div class="main">
			<!-- MAIN CONTENT -->
			<!-- <div class="main-content"> -->
			
			<!-- </div>
		</div>
		
		<footer>
			
		</footer>
	</div> -->
	
<!-- </body>
</html> -->
