<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.css" integrity="sha512-GQSxWe9Cj4o4EduO7zO9HjULmD4olIjiQqZ7VJuwBxZlkWaUFGCxRkn39jYnD2xZBtEilm0m4WBG7YEmQuMs5Q==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/d3/4.12.0/d3.js" integrity="sha512-SuXpPdajLF/GkLBHndpO/A05M1yY4UXJjeeYSbuXRat6E2AUmnG5CVQ0xPtI7IxfXjRmAHoOuOsCqd8yoPup+g==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.7.20/c3.js" integrity="sha512-11Z4MD9csmC3vH8Vd0eIPJBQu3uEHEqeznWEt3sLBCdQx3zm9mJbBcJH8WTcyGY9EXDE81BNpjE2vLosPK8cFQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

<style>
html{
	height: 60%;
}

body{
	height: 100%;
}

#covidTitle{
	display: flex;
	justify-content: center;
	align-items: center;
	height: 160px;
	font-size: 25px;
	font-weight: bolder;
	color: black;
}

#chart{
	
}

.chartWrapper{
	display: flex;
	justify-content: center;
}

</style>


</head>
<body>
<form>
<div class="main">
	
	<a href="/covid/covidInfo" id="covidTitle">코로나19 일일 확진자 현황</a>
	<div class="chartWrapper">
	
	<div id="chart"></div>
	</div>
	
</div>
<script type="text/javascript">

//Bar chart
var chart = c3.generate({
    data: {
        json: ${covidJson},
        keys: {
        	x:'days',
        		
        	value: ['decideCnt']
        },
        names:{
        	decideCnt: '일일 확진자수'        	
        },
        colors:{
     	   decideCnt : '#58ACFA'
        },
        type: 'bar',
        labels: true
    
    },
    axis : {
    	x: {
    		show: true,
    		type : 'timeseries', 
    		tick : {
    			format: '%m-%d',
    			culling: {
    		        max: 31
    		      }
    		}
    	},
    	y: {
    		show: false
    	}
    } 

});

setTimeout(function () {
    chart.resize({height:350, width:1100});
}, 0);

</script>

</form>
</body>
</html>