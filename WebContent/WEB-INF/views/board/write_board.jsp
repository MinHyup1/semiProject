<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
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
	
		<!-- 글쓰기 모드일경우 -->
		<c:if test="${type == 'insert'}">
		<form method = "post" action = "/FileUploadServlet" enctype="multipart/form-data">
		<input type="hidden" name="BBS_TYPE" value="${BBS_TYPE }"/> 
		<input type="hidden" name="USER_CODE" value="${authentication.userCode}"/>
		

		
        <table  style="padding-top:50px" align = center width=700 border=0 cellpadding=2 >
                <tr>
                <td height=20 align= center bgcolor=#ccc><font color=white> 글쓰기</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class = "table2">
                        <tr>
                        <td>작성자</td>
                        <td>${authentication.name}</td>
                        </tr>
                        <tr>
                        <td>첨부파일</td>
                        <td><input type="file" name="files" id="files"/></td>
                        </tr>
                        <tr>
                        <td>제목</td>
                        <td><input type = text name = "TITLE" size=60></td>
                        </tr>
 
                        <tr>
                        <td>내용</td>
                        <td><textarea name ="CONTENT" cols=85 rows=15></textarea></td>
                        </tr>
                        </table>
 
                        <tr>
                        <td colspan="2"><input type = "submit" value="작성"></td>
                        </tr>

        </table>
        </form>
		</c:if>
		
		<!-- 수정모드일 경우 -->
		<c:if test="${type == 'modify'}">
		<form method = "post" action = "update_board">
		<input type="hidden" name="BBS_TYPE" value="${BBS_TYPE }"/> 
		<input type="hidden" name="BNO" value="${vo.BNO }"/> 
		<input type="hidden" name="USER_CODE" value="${authentication.userCode}"/>
		

		
        <table  style="padding-top:50px" align = center width=700 border=0 cellpadding=2 >
                <tr>
                <td height=20 align= center bgcolor=#ccc><font color=white> 글쓰기</font></td>
                </tr>
                <tr>
                <td bgcolor=white>
                <table class = "table2">
                        <tr>
                        <td>작성자</td>
                        <td>${authentication.name}</td>
                        </tr>
                        <tr>
                        <td>제목</td>
                        <td><input type = text name = "TITLE" size=60 value="${vo.TITLE }"></td>
                        </tr>
 
                        <tr>
                        <td>내용</td>
                        <td><textarea name ="CONTENT" cols=85 rows=15>${vo.CONTENT }</textarea></td>
                        </tr>
                        </table>
 
                        <tr>
                        <td colspan="2"><input type = "submit" value="작성"></td>
                        </tr>

        </table>
        </form>
		</c:if>
		
	</div>
		
		
<footer>
</footer>

</div>

</body>
</html>