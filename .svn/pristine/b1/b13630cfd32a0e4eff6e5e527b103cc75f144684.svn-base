var controller;
var dailyReportData;
var mapData;
var eventsData;
var risksData;
$(function(){
	console.log("init daily report page...");
    controller = new summaryController();
    applyReport();
    applyReportList("applyEvents",1,7);//默认按日请求事件列表
    applyReportList("applyRisks",1,7);//默认按日请求风险列表
})

var summaryController=function(){
    var summaryController=function(){
        this.echart_core=new echartController();
        
       // this.load_page(data);
    }
    summaryController.prototype={
        load_page:function(data){
        	//1.今日风险情况
        	this.echart_core.monitor_warning("monitor_warning",data);
        	
        	//2.今日事件情况
        	 this.echart_core.monitor_events("monitor_events",data,eventsPieSelectedCallback);
            //初始化风险状况百分比
            initRiskPersent(data);
        	
            //3.今日风险情况区域分布地图
            var mapArray = buildDistMapArray(data.monitorCompanyDistributions);
            mapData = mapArray;
            this.echart_core.monitor_area("monitor_area",mapArray,distMapSelectedCallback);
            //高风险前五排名
            initDistRankings(data.monitorCompanyDistributions);
        },
        load_events:function(data){
        	//事件列表
        	data.yName="客户数量";
        	this.echart_core.monitor_events_change("monitor_events_change",data);
        },
        load_risks:function(data){
        	//风险列表
        	data.yName="客户数量";
        	this.echart_core.monitor_level_change("monitor_level_change",data);
        }
    };
    summaryController.prototype.constructor = summaryController;
	return summaryController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page(dailyReportData);
        controller.load_events(eventsData);
        controller.load_risks(risksData);
    }
}
//地图点击事件回调
var distMapSelectedCallback = function distMapSelectedCallback(param){
	console.log("distMapSelected Callback...");
	//console.log(param);
	console.log("被点击的省份="+param.target);
	var companyNum = 0;
	for(var dist in mapData){
		if(dist.name==param.target){
			companyNum = dist.value;
		}
	}
	console.log("被点击省份公司数量="+companyNum);
   //跳转到公司列表页面
	//....，可能需要根据公司数量确定是否跳转
}
//事件饼图点击事件回调
var eventsPieSelectedCallback = function eventsPieSelectedCallback(param){
	console.log("eventsPieSelected Callback...");
	//console.log(param);
	console.log("被点击事件等级="+param.target);
	//跳转到公司列表页面
	//....，
}
function applyReport(){
	 $.ajax({
	       type: "post",
	       url: "/ljzx/dailyReportShow/applyDailyReport.do",
	       data: {"accountId":""},
	       dataType: "json",
	       success: function (data) {
	           console.log("apply report callback..");
	           console.log(data);
	           if('object'==typeof controller){
	        	   dailyReportData = data;
	               controller.load_page(data);
	           }
	       },
	  	   complete: function(request, textStatus){
	  			console.log("complete:"+textStatus+","+request);
	   		    console.log(request);
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
//module取值 applyEvents or applyRisks
function applyReportList(module,step,spotNum){
	$.ajax({
	       type: "post",
	       url: "/ljzx/dailyReportShow/"+module+".do",
	       data: {"accountId":"","step":step,"spotNum":spotNum},
	       dataType: "json",
	       success: function (data) {
	           console.log("apply reportList callback..");
	           console.log(data);
	           if('object'==typeof controller){
	        	   if("applyEvents"==module){
	        		   eventsData = data;
		               controller.load_events(eventsData);
		               //改变开关按钮样式
		               $("#eventSwitchCNT .select").removeClass("selected");
		               $("#eventSwitch"+step).addClass("selected");
		               
	        	   }else if("applyRisks"==module){
	        		   risksData = data;
	        		   controller.load_risks(risksData);
		               //改变开关按钮样式
		               $("#riskSwitchCNT .select").removeClass("selected");
		               $("#riskSwitch"+step).addClass("selected");
	        	   }
	           }
	       },
	  	   complete: function(request, textStatus){
	  			console.log("complete:"+textStatus+","+request);
	   		    console.log(request);
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

function initRiskPersent(data){
	$("#highRiskPersent").html(formatPersent(data.highRiskNum/data.totalNum*100));
	$("#middleRiskPersent").html(formatPersent(data.middleRiskNum/data.totalNum*100));
	$("#lowRiskPersent").html(formatPersent(data.lowRiskNum/data.totalNum*100));
	$("#noRiskPersent").html(formatPersent(data.noRiskNum/data.totalNum*100));
}
function formatPersent(source){
	var target = ""+source;
	if(target.indexOf(".")>-1){
		target = target.substring(0,target.indexOf(".")+2)+"%";
	}else{
		target  = target+".0%";
	}
	return target;
}
function buildDistMapArray(sourceArray){
	//console.log("sourceArray="+sourceArray);
	//console.log(sourceArray);
	var targetArray = [];
	if(sourceArray!=null){
		var record;
		for(record in sourceArray){
			//console.log(sourceArray[record]);
			if(record!=null){
				targetArray.push({"name":sourceArray[record].areaName,"value":sourceArray[record].totalNum,"value2":sourceArray[record].highRiskNum});
			}
		}
	}
	//console.log("targetArray="+targetArray);
	//console.log(targetArray);
	return targetArray;
}
//初始化企业区域排名，前五名
function initDistRankings(sourceArray){
	
	if(sourceArray!=null&&sourceArray.length>0){
		var size = 5;
		if(sourceArray.length<size){
			size = sourceArray.length;
		}
		for(var i=0;i<size;i++){
			$("#rank"+(i+1)).find(".name").html(sourceArray[i].areaName);
			$("#rank"+(i+1)).find(".num").html(sourceArray[i].highRiskPercent+'%（'+sourceArray[i].highRiskNum+'家）');
		}
	}
	
}