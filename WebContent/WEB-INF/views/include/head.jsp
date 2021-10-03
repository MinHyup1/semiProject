<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<meta charset="UTF-8">
<title>MEDIBOOK</title>
<link rel="stylesheet" href="${contextPath}/resources/css/bootstrap.css">
<link rel="stylesheet" href="${contextPath}/resources/css/main.css">
<link rel="stylesheet" href="${contextPath}/resources/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script src="${contextPath}/resources/js/bootstrap.js"></script>
<script src="${contextPath}/resources/js/klorofil-common.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
<script src="${contextPath}/resources/js/schedule/schedule-fetch.js"></script>
<html>
<head>
</head>
<body>
<!-- WRAPPER -->
   <div id="wrapper">
      <!-- NAVBAR -->
      <nav class="navbar navbar-default navbar-fixed-top">
         <div class="brand">
            <a href="${contextPath}/index"><i class="fas fa-plus-square"></i> MEDIBOOK</a> <!-- href 수정 -->
         </div>
         <div class="container-fluid">
            <div class="navbar-btn">
               <button type="button" class="btn-toggle-fullwidth"><i class="far fa-arrow-alt-circle-left"></i></button>
            </div>
            <div class="navbar-btn navbar-btn-right">
            <!-- 로그인하면 login, 회원가입 버튼 사라지도록 -->
            <c:if test="${empty authentication}">
               <a href="/member/loginPage" class="btn btn-success update-pro" title="" target="" > <span>login</span></a>
               <a href="/member/joinPage" class="btn btn-success update-pro" title="" target="" > <span>회원가입</span></a>
            </c:if>   
            </div><!-- 로그아웃 버튼 추가 예정 -->
            <c:if test="${not empty authentication}">
            <div id="navbar-menu">
               <ul class="nav navbar-nav navbar-right">
                  <li class="dropdown">
                     <a href="#" class="dropdown-toggle icon-menu" data-toggle="dropdown">
                        <i class="far fa-bell"></i> <!--종 아이콘  -->
                        <span class="badge bg-danger"></span> <!-- 종아이콘에 나오는 숫자 -->
                     </a>
                     <ul class="dropdown-menu notifications"> <!-- 종버튼 알람부분 -->
                        <!-- <li><a href="#" class="notification-item"><span class="dot bg-warning"></span>System space is almost full</a></li>
                        <li><a href="#" class="notification-item"><span class="dot bg-danger"></span>You have 9 unfinished tasks</a></li>
                        <li><a href="#" class="notification-item"><span class="dot bg-success"></span>Monthly report is available</a></li>
                        <li><a href="#" class="notification-item"><span class="dot bg-warning"></span>Weekly meeting in 1 hour</a></li>
                        <li><a href="#" class="notification-item"><span class="dot bg-success"></span>Your request has been approved</a></li> -->
                        <li><a href="#" class="more">진행 중이거나 곧 예정인 알림이 없습니다.</a></li>
                     </ul>
                  </li>
                  <li class="dropdown"> <!-- dropdown open -->
                     <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false"><i class="fas fa-user-circle"></i>  <span>${authentication.nick}</span>  <i class="fas fa-chevron-down"></i></a>
                     <ul class="dropdown-menu">
                     	<!-- 카카오톡 연동으로 로그인 한 회원에게 보여주는 회원정보수정 페이지  -->
                     		<li><a href="/member/mypage"><i class="far fa-user"></i> <span>My Profile</span></a></li>
                     		<li><a onclick="kakaoLogout()"><i class="fas fa-sign-out-alt"></i> <span>Logout</span></a></li>
                     </ul>
                  </li>
               </ul>
            </div>
            </c:if>
         </div>
      </nav>
      <!-- END NAVBAR -->
      <!-- LEFT SIDEBAR -->
      <div id="sidebar-nav" class="sidebar">
         <div class="sidebar-scroll">
            <nav>
               <ul class="nav asdasd">
                  <li><a href="${contextPath}/searchHospital/search-hospital-main" class="" data-loc="searchHosp"><i class="fas fa-hospital"></i> <span>병원 검색</span></a></li>
                  <li><a href="${contextPath}/pharmacy/pharmacy" class="" data-loc="searchPharm"><i class="fas fa-vial"></i> <span>약국 검색</span></a></li>
                  <li><a href="${contextPath}/Medicine/medicineMain" class="" data-loc="searchMedi"><i class="fas fa-pills"></i> <span>의약품 검색</span></a></li>
                  <li><a href="${contextPath}/schedule/schedule-main" class="" data-loc="schedule"><i class="far fa-calendar-check"></i> <span>진료 스케줄</span></a></li> <!-- href 수정 -->
                  <li><a href="${contextPath}/board?BBS_TYPE=FREEBOARD" class="" data-loc="freeboard"><i class="fas fa-list"></i> <span>자유게시판</span></a></li>
                  <li><a href="${contextPath}/board?BBS_TYPE=QNA" class="" data-loc="freeboard"><i class="fas fa-list"></i> <span>질문게시판</span></a></li>
                  <li><a href="${contextPath}/board?BBS_TYPE=FAQ" class="" data-loc="freeboard"><i class="fas fa-list"></i> <span>자주묻는 질문</span></a></li>
               </ul>
            </nav>
         </div>
      </div>
      <script type="text/javascript">
      var selectedMenu = 'main';

      window.addEventListener('DOMContentLoaded', e=>{
         document.querySelectorAll(".asdasd>li>a").forEach(e => {
            if(e.dataset.loc == selectedMenu) {
                e.classList[1] = 'active' 
               e.className = 'active';
            }
         });
      })
      
      
      </script>

<script src='${contextPath}/resources/js/member/kakaoLogin.js'></script>

  
      <!-- END LEFT SIDEBAR -->
      <!-- MAIN -->
      <!-- <div class="main"> -->
         <!-- MAIN CONTENT -->
         <!-- <div class="main-content"> -->
         
         <!-- </div>
      </div>
      
      <footer>
         
      </footer>
   </div> -->
   
<!-- </body>
</html> -->