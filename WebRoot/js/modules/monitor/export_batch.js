var exportController;
$(function(){
	exportController = new exportEventController();
})

var exportEventController=function(){

    var event_export=function(){
        
        //导出公司列表
       // $("#export_company_btn").click(exportCompanyBtnClickedFunction);
        
    }
    var exportEventController=function(){
    	this.init_extend();
        this.event_bind();
    }
    exportEventController.prototype={
        load_page:function(){
           
        },
        init_extend:function(){
        	//判断当前字符串是否以str开始 先判断是否存在function是避免和js原生方法冲突，自定义方法的效率不如原生的高  
            if (typeof String.prototype.startWith != 'function'){   
              String.prototype.startWith = function (str){  
                 return this.slice(0, str.length) == str;  
              };  
            }
            //判断当前字符串是否以str结束  
            if (typeof String.prototype.endWith != 'function') {  
              String.prototype.endWith = function (str){  
                 return this.slice(-str.length) == str;  
              };  
            }  
        },
        event_bind:function(){
         
            console.log("init export btn...");
            event_export();
        }
    };
    exportEventController.prototype.constructor = exportEventController;
	return exportEventController;
}();

function exportCompanyBtnClickedFunction(){
	
	if(!sortParam.hasOwnProperty("sortAttr")){
		sortParam.sortAttr = "createTime";
		sortParam.isDesc = true;
	}
    
	companyName = $("#searchInfo").val();
	
	var param = {
	    "currentAccountId": $("#currentAccountId").val(),
		"showGroupType":showGroupType,	
		"groupName": encodeURIComponent(groupName),
		"riskLevel": riskLevel,
		"riskStatus": riskStatus,
		"eventLevel": eventLevel,
		"beginDate": beginDate,
		"dueDate": dueDate,
		"area": encodeURIComponent(area),
		"label": encodeURIComponent(label),
		"updateStatus": updateStatus,
		"customRiskFlag": customRiskFlag,
		"todayMonitorFlag": todayMonitorFlag,
		"companyName": encodeURIComponent(companyName),
		"groupStatus": groupStatus,
		"sortAttr": sortParam.sortAttr,
		"isDesc":sortParam.isDesc
	}
	
	var url =  ctx + "/ie/exportMonitorCompanys.do?groupName="+encodeURIComponent(param.groupName)+"&riskLevel="+param.riskLevel
	+"&riskStatus="+param.riskStatus+"&eventLevel="+param.eventLevel+"&beginDate="+param.beginDate+"&dueDate="+param.dueDate+"&area="+encodeURIComponent(param.area)+"&label="+encodeURIComponent(param.label)+"&companyName="+encodeURIComponent(param.companyName)
	+ "&groupStatus="+param.groupStatus+ "&updateStatus="+param.updateStatus+ "&customRiskFlag="+param.customRiskFlag+"&currentAccountId="+param.currentAccountId
	+"&showGroupType="+param.showGroupType+"&todayMonitorFlag="+param.todayMonitorFlag+"&sortAttr="+param.sortAttr+"&isDesc="+param.isDesc;
	
	window.location.href = url;
	
}

