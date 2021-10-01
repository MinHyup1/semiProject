<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

/* a {
    text-decoration: none;
    color: inherit;
} */

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
/* a{
	text-decoration: underline;
} */
</style>

<script>
var rowNum = 0;
function fn_add_row(index,PARENT_COMMENT_ID){
	
	
	
	const table = document.getElementById('tbl_comment');
	
	if(rowNum != 0){
		table.deleteRow(rowNum);
	}
	rowNum = index+5;
	const newRow = table.insertRow(rowNum); 
	// 새 행(Row)에 Cell 추가 
	const newCell1 = newRow.insertCell(0);
	newCell1.colSpan = "2";
	// Cell에 텍스트 추가 
	
	var val = "<input type='text' id='content2'><a href=\"javascript:fn_add_comment(1,${vo.BNO },"+PARENT_COMMENT_ID+",'${authentication.userCode }')\">저장</a>";
	newCell1.innerHTML  = val;

	
}

function fn_add_comment(TYPE,BNO,PARENT_COMMENT_ID,USER_CODE){
	var content = '';
	if(TYPE == 1){
		content = document.getElementById('content2').value;
	}else{
		content = document.getElementById('content').value;
	}
	
	if(content == ''){
		alert('답글을 입력하세요.');
		return;
	}
	
	location.href = "add_board_comment?BBS_TYPE=${BBS_TYPE}&BNO="+BNO+"&PARENT_COMMENT_ID="+PARENT_COMMENT_ID+"&USER_CODE="+USER_CODE+"&CONTENT="+content;

}
</script>
</head>
<body>

<div class="main">
	<div class="board_list_top">
		<form method = "post" action = "/FileUploadServlet" enctype="multipart/form-data">
		<input type="hidden" name="BBS_TYPE" value="${BBS_TYPE }"/> 
		<input type="hidden" name="USER_CODE" value="${authentication.userCode}"/>
		
		<c:if test="${authentication.userCode == vo.USER_CODE}">
			<input type="button" value="수정" onclick="location.href='write_board?BNO=${vo.BNO}&BBS_TYPE=${BBS_TYPE}&type=modify'" class="input_l_s1">
			<input type="button" value="삭제" onclick="location.href='delete_board?BNO=${vo.BNO}&BBS_TYPE=${BBS_TYPE}'" class="input_l_s1">
		</c:if>
		 
        <table id="tbl_comment" style="padding-top: 50px" align=center width=700 border=0 cellpadding=2>
					<tr>
						<td height=20 align=center bgcolor=#ccc><font color=white>게시글</font></td>
					</tr>
					<tr>
						<td bgcolor=white>
							<table class="table2">
								<tr>
									<td>작성자</td>
									<td>${vo.NAME}</td>
								</tr>
								<tr>
									<td>첨부파일</td>
									<td><c:forEach var="item" items="${fileList}">
											<a href="/FileDownServlet?FILE_NO=${item.FILE_NO }">${item.ORG_FILE_NAME }</a>
											<br />
										</c:forEach></td>
								</tr>
								<tr>
									<td>제목</td>
									<td>${vo.TITLE }</td>
								</tr>

								<tr>
									<td>내용</td>
									<td>${vo.CONTENT}</td>
								</tr>

							</table>
					<tr> 
						<td height=20 align=center bgcolor=#ccc><font color=white>댓글</font></td>
					</tr>
					<c:if test="${!empty authentication}">
					<tr>
						<td colspan="2">댓글 : <input type="text" id="content"> <a href="javascript:fn_add_comment(0,${vo.BNO },0,'${authentication.userCode }')">저장</a></td>
					</tr>
					</c:if>
					<c:forEach var="item" items="${commentList}" varStatus="status">
						<tr>
							<td colspan="2" bgcolor=white>
							<c:if test="${item.COMMENTLEVEL == 1 }">
								<c:if test="${item.DELETE_AT == 'Y' }">
									***삭제된 댓글입니다***
								</c:if>
								<c:if test="${item.DELETE_AT != 'Y' }">
									${item.CONTENT } [ ${item.REGDATE } ( ${item.NAME } ) ]
									<c:if test="${!empty authentication}">
										<a href="javascript:fn_add_row(${status.index },${item.COMMENT_ID })">답글</a>
									</c:if>
									
									<c:if test="${authentication.userCode == item.USER_CODE}">
										<a href="delete_board_comment?BNO=${vo.BNO }&BBS_TYPE=${BBS_TYPE }&COMMENT_ID=${item.COMMENT_ID }">삭제</a>
									</c:if>
								</c:if>
								
							</c:if>
							
							<c:if test="${item.COMMENTLEVEL != 1 }">
								<c:forEach var="cnt" begin ="1" end="${item.COMMENTLEVEL }">
								&nbsp;&nbsp;
								</c:forEach>
								<c:if test="${item.DELETE_AT == 'Y' }">
									***삭제된 댓글입니다***
								</c:if>
								<c:if test="${item.DELETE_AT != 'Y' }">
									└${item.CONTENT } [ ${item.REGDATE } ( ${item.NAME } ) ]
									<c:if test="${!empty authentication}">
										<a href="javascript:fn_add_row(${status.index },${item.COMMENT_ID })">답글</a>
									</c:if>
									
									<c:if test="${authentication.userCode == item.USER_CODE}">
										<a href="delete_board_comment?BNO=${vo.BNO }&BBS_TYPE=${BBS_TYPE }&COMMENT_ID=${item.COMMENT_ID }">삭제</a>
									</c:if>
								</c:if>
								
							</c:if>
							</td>
						</tr>
					</c:forEach>


					<!-- <tr>
                        <td colspan="2"><input type = "submit" value="작성"></td>
                        </tr> -->

				</table>
        </form>
		
	</div>
		
		
<footer>
</footer>

</div>

</body>
</html>