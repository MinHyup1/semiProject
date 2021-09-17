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
</head>
<body>

<script type="text/javascript">
selectedMenu = 'searchHosp';

var navList = document.getElementsByClassName("asdasd");

function handleClick(event) {
  console.log(event.target);
  console.log(event.target.classList);

  if (event.target.classList[1] === "active") {
    event.target.classList.remove("active");
  } else {
    for (var i = 0; i < navList .length; i++) {
      navList [i].classList.remove("active");
    }

    event.target.classList.add("active");
  }
}

function init() {
  for (var i = 0; i < navList .length; i++) {
    navList [i].addEventListener("click", handleClick);
  }
}

init();

/* var divElement = document.getElementByClassName('asdasd');

divElement.addEventListener('click', (e) => {
  var targetElement = e.target;
  targetElement.setAttribute('class', 'active');  
}); */
</script>
<div class="main">
<c:if test="${empty active}">

 <a>확인</a>

	</c:if>
		</div>
		
		<footer>
		</footer>
	</div>

</body>
</html>