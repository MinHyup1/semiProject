<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 목록</title>
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
	<div class="board_list_top">
		<table class="board_list">
			<caption>게시판 목록</caption>
				<thead>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>작성일</th>
						<th>조회</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>5</td>
						<td>
							<a href="#">블라</a>
						</td>
						<td>최민석</td>
						<td>2021-09-17</td>
						<td>132</td>
					</tr>
					<tr>
						<td>4</td>
						<td>
							<a href="#">블라블라</a>
						</td>
						<td>최민석</td>
						<td>2021-09-12</td>
						<td>332</td>
					</tr>
					<tr>
						<td>3</td>
						<td>
							<a href="#">블라블라블라</a>
						</td>
						<td>최민석</td>
						<td>2021-09-06</td>
						<td>432</td>
					</tr>
					<tr>
						<td>2</td>
						<td>
							<a href="#">블라블라블라블라</a>
						</td>
						<td>최민석</td>
						<td>2021-09-01</td>
						<td>532</td>
					</tr>
					<tr>
						<td>1</td>
						<td>
							<a href="#">블라블라블라블라블라</a>
						</td>
						<td>최민석</td>
						<td>2021-09-17</td>
						<td>1132</td>
					</tr>
				</tbody>
		</table>
		<div class="page_bottom">
			<a href="#" class="bt">첫 페이지</a>
			<a href="#" class="bt">이전 페이지</a>
			<a href="#" class="num on">1</a>
			<a href="#" class="num">2</a>
			<a href="#" class="num">3</a>
			<a href="#" class="bt">다음 페이지</a>
			<a href="#" class="bt">마지막 페이지</a>
		</div>
	</div>
<script type="text/javascript">
	selectedMenu = 'board';
</script>
</body>
</html>