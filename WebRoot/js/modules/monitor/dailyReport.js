//初始化各事件等级公司数量趋势图
function initCompanyNumTrendChartOfEventLevel(cycle){
	$.ajax({
       url: ctx + "/monitorDailyReport/getCompanyNumTrendOfEventLevel",
       type: "POST",
       data: {"cycle":cycle},
       dataType: "json",
       success: function (result) {
    	   var echart_core = new echartController();
    	   
    	   echart_core.monitor_events_change("monitor_events_change",result);
    	   
               //改变开关按钮样式
               //$("#eventSwitchCNT .select").removeClass("selected");
               //$("#eventSwitch"+step).addClass("selected");
       },
       error: function(){
			toastr.error('网络异常！');
       },
       complete: function(XMLHttpRequest, textStatus){
			
       }
    });
}

