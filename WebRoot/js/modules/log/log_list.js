
$(document).ready(function(){
	$("#toDate").val($("#currentDateStr").val());
	$("#fromDate").val($("#currentDateStr").val());
	fromDateStr = $("#currentDateStr").val();
	toDateStr = $("#currentDateStr").val();
	logSearch();
});
var logListParam = {};
var fromDateStr = "";
var toDateStr = "";
function fromDateDeal(dateStr){
	fromDateStr = dateStr;
	console.log("pick from date "+dateStr);
}
function toDateDeal(dateStr){
	toDateStr = dateStr;
	console.log("pick to date "+dateStr);
}

function logSearch(){
	
	if(fromDateStr==""){
		toastr.warning("开始时间不能为空！");
		return;
	}
	if(toDateStr==""){
		toastr.warning("结束时间不能为空！");
		return;
	}
	 var arr1 = fromDateStr.split("-");  
     var arr2 = toDateStr.split("-");  
     //字符串转换成日期对象  
     var date1 = new Date(parseInt(arr1[0]), parseInt(arr1[1]), parseInt(arr1[2]));  
     var date2 = new Date(parseInt(arr2[0]), parseInt(arr2[1]), parseInt(arr2[2]));
	
	var dura = (Number(date2) - Number(date1)) / 86400000;
	console.log("duration = "+dura);
	if(dura<0){
		toastr.warning("开始时间不能大于结束时间！");
		return;
	}
	if(dura>30){
		toastr.warning("时间间隔不能大于30天！");
		return;
	}
	var param = {
			"fromDate":fromDateStr,
			"toDate":toDateStr,
			"accountId":$("#accountIdSelect").val(),
			"actionType":$("#actionTypeSelect").val()
	}
	logListParam = param;
	console.log(param);
	logListPageselectCallback(0, null);
}

function logListPageselectCallback(new_page_index, pagination_container){
	var pageNo = parseInt(new_page_index) + parseInt(1);
	 
	 logListParam.pageNo = parseInt(pageNo);
	 logListParam.pageSize = 20;
	 $.ajax({
       type: "post",
       url: ctx+"/log/getLogList",
       data: logListParam,
       dataType: "html",
       success: function (data) {
           console.log("getLogList call back...");
          // console.log(data);
          $("#logListTableDiv").html(data);
        
       },
  		error: function(){},
  		complete: function(request, textStatus){
  			//console.log("complete:"+textStatus+","+request);
   		//console.log(request);
   		if(request){
   			if(request.status==0){
   				toastr.error('网络异常！');
   			}else if(request.status==500){
   				toastr.error('站点异常！');
   			}else{
   				if(!textStatus||textStatus!="success"){
   					toastr.error('站点异常！');
   				}
   			}
   		}
  		}
    });
	
}