//打开客户账号管理页面
function toClientAccountManage(){
	var deep = $("#deep").val();
	
	window.location.href = ctx + "/clientAccount/toClientAccountManage";
}

//打开客户账号信息页面
function toClientAccountInfo(){
	var deep = $("#deep").val();
	
	window.location.href = ctx + "/clientAccount/toClientAccountInfo";
}

//打开账号安全中心
function toSecurityCenter(){
	
	window.location.href = ctx + "/clientAccount/toSecurityCenter";
}

//打开操作日志查询页面
function toLogList(){
	
	window.location.href = ctx + "/log/toLogListView";
}

//跳转到动态监控设置页面
function toMonitorSetting(){
	
	window.location.href = ctx + "/monitorConfigure/toMonitorSetting";
}




