<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<script type="text/javascript" src="https://dapi.kakao.com/v2/maps/sdk.js?appkey=a91d097e5c5d9764f91631e0ac40e115&libraries=services,clusterer,drawing"></script>
<style type="text/css">

.map{
      display: flex;
      flex-wrap:wrap;
      justify-content: space-between;
      width:320px;
      height:320px;
   } 
   
</style>
</head>
<body>

<div class="map">

 <a>확인</a>


</div>

<script type="text/javascript">
selectedMenu = 'searchPharm';
</script>
</body>
</html>