<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link href='${contextPath}/resources/css/schedule/calendar/main.css' rel='stylesheet'/>
<script src='${contextPath}/resources/js/schedule/calendar/render.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/main.js'></script>
<script src='${contextPath}/resources/js/schedule/calendar/locales-all.js'></script>
<link href='${contextPath}/resources/css/schedule/schedule_main.css' rel='stylesheet'/>
<style type="text/css">
* {
    margin: 0;
    padding: 0;
}

table {
    border-collapse: collapse;
}

caption {
    display: none;
}

a {
    text-decoration: none;
    color: inherit;
}
th{
	text-align: center;
}

.board_list_top {
    padding: 50px;
}

.board_list {
    width: 100%;
    border-top: 2px solid red;
}

.board_list tr {
    border-bottom: 1px solid #ccc;
}

.board_list th,
.board_list td {
    padding: 10px;
    font-size: 14px;
}

.board_list td {
    text-align: center;
}

.board_list .tit {
    text-align: left;
}

.board_list .tit:hover {
    text-decoration: underline;
}

.board_list_top .page_bottom {
    margin-top: 20px;
    text-align: center;
    font-size: 0;
}
.board_list_top .page_bottom a {
    display: inline-block;
    margin-left: 10px;
    padding: 5px 10px;
    border-radius: 100px;
    font-size: 12px;
}
.board_list_top .page_bottom a:first-child {
    margin-left: 0;
}

.board_list_top .page_bottom a.bt {
    border: 1px solid #eee;
    background: #eee;
}

.board_list_top .page_bottom a.num {
    border: 1px solid green;
    font-weight: 600;
    color: green;
}

.board_list_top .page_bottom a.num.on {
    background: green;
    color: #fff;
}
</style>
</head>
<body>


<div class="main">
	<div class="board_list_top">
	<c:if test="${BBS_TYPE == 'FREEBOARD'}">
		<h1>자유게시판</h1>
	</c:if>
	<c:if test="${BBS_TYPE == 'QNA'}">
		<h1>질문게시판</h1>
	</c:if>
	<c:if test="${BBS_TYPE == 'FAQ'}">
		<h1>자주묻는 질문</h1>
	</c:if>
	<c:if test="${not empty authentication}">
		<input type="button" value="글쓰기" onclick="location.href='write_board?BBS_TYPE=${BBS_TYPE}'" class="input_l_s1">
	</c:if>
		<table class="board_list">
			<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${list}">
						<tr>
							<td>${item.RN }</td>
							<td>
								<a href="view_board?BBS_TYPE=${BBS_TYPE}&BNO=${item.BNO}">${item.TITLE }</a>
							</td>
							<td>${item.NAME }</td>
							<td>${item.REGDATE }</td>
						</tr>
						
					</c:forEach>


					<!-- <tr>
						<td>5</td>
						<td>
							<a href="#">블라</a>
						</td>
						<td>최민석</td>
						<td>2021-09-17</td>
						<td>132</td>
					</tr> -->
					
				</tbody>
		</table>
		<!-- <div class="page_bottom">
			<a href="#" class="bt">첫 페이지</a>
			<a href="#" class="bt">이전 페이지</a>
			<a href="#" class="num on">1</a>
			<a href="#" class="num">2</a>
			<a href="#" class="num">3</a>
			<a href="#" class="bt">다음 페이지</a>
			<a href="#" class="bt">마지막 페이지</a>
		</div> -->
		
		<div class="page_bottom">
			<c:if test="${total > 0}">

			   <c:set var="pageCount" value="${total / pageSize + ( total % pageSize == 0 ? 0 : 1)}"/>
			   <c:set var="startPage" value="${pageGroupSize*(numPageGroup-1)+1}"/>
			   <c:set var="endPage" value="${startPage + pageGroupSize-1}"/>
			   
			   <c:if test="${endPage > pageCount}" >
			     <c:set var="endPage" value="${pageCount}" />
			   </c:if>
			   
			 
				<!-- << 처음으로 < 이전으로 -->  
			   <c:if test="${numPageGroup > 1}">
			        <a class="bt" href="/test/board?BBS_TYPE=${BBS_TYPE}&CURRENT_ROW=1">첫 페이지</a>
			        <a class="bt"href="/test/board?BBS_TYPE=${BBS_TYPE}&pageNum=${(numPageGroup-2)*pageGroupSize+1 }">이전 페이지</a>
			   </c:if>
			  

			  <c:forEach var="i" begin="${startPage}" end="${endPage}">
				   <c:if test="${CURRENT_ROW == i}">
				   		<a href="/test/board?BBS_TYPE=${BBS_TYPE}&CURRENT_ROW=${i}" class="num on">${i}</a>
				   </c:if>
			 		<c:if test="${CURRENT_ROW != i}">
				   		<a href="/test/board?BBS_TYPE=${BBS_TYPE}&CURRENT_ROW=${i}" class="num">${i}</a>
				   </c:if>
			  </c:forEach>
			 
			 
			 <!-- 소수점 형태이므로 인트형태로 변경  -->  
			   <fmt:parseNumber var="maxPage"  value="${pageCount}" integerOnly="true"/> 
			 ${maxPage }  
			<!-- >> 맨뒤로 > 다음으로 -->
			   <c:if test="${numPageGroup < pageGroupCount}">
			      <a href="/test/board?BBS_TYPE=${BBS_TYPE}&CURRENT_ROW=${numPageGroup*pageGroupSize+1}">다음 페이지</a>
			      <a href="/test/board?BBS_TYPE=${BBS_TYPE}&CURRENT_ROW=${maxPage}">마지막 페이지</a>
			  
			   </c:if>
			</c:if>
			
			<!-- <a href="#" class="bt">첫 페이지</a>
			<a href="#" class="bt">이전 페이지</a>
			<a href="#" class="num on">1</a>
			<a href="#" class="num">2</a>
			<a href="#" class="num">3</a>
			<a href="#" class="bt">다음 페이지</a>
			<a href="#" class="bt">마지막 페이지</a> -->
		</div>
	</div>
		
		
<footer>
</footer>

</div>

</body>
</html>