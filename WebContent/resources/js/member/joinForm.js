
//전체 체크버튼 클릭 시 전체체크 및 해제
function allCheck(e) {
    if(e.target.checked) {
      document.querySelectorAll(".checkList").forEach(function(v, i) {
        v.checked = true;
      });
    } else {
      document.querySelectorAll(".checkList").forEach(function(v, i) {
        v.checked = false;
      });
    }
  }
  
 //개별 체크버튼 클릭 시 전체 체크버튼 체크 및 해제
  function checkList(e) {
    let checkCount = 0;
    document.querySelectorAll(".checkList").forEach(function(v, i) {
      if(v.checked === false){
        checkCount++;
      }
    });

    if(checkCount>0) {
      document.getElementById("allCheckList").checked = false;
    } else if(checkCount === 0) {
      document.getElementById("allCheckList").checked = true;
    }
  }