<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
		
		
	</div>
		
		
<footer>
</footer>

</div>

</body>
</html>