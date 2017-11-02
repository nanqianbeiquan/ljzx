//根据关键字搜索企业
function searchCompany(){
	
	var deep = $("#deep").val();
	var pageName = $("#pageName").val();
	var keyword = $("#keyword").val();
	
	if(keyword == ""){
		toastr.info('请输入关键字进行查询！');
	}else{
		if(pageName&&pageName=="SEARCH_RESULT"){
			
		}else{
			deep = parseInt(deep)+1;
		}
		
		window.location.href = ctx + "/companyInfo/toCompanyInfoList?keyword=" + encodeURIComponent(keyword)
		+"&deep="+deep;
	}
}

//查看消息列表
function toMessageList(){
	
	var deep = $("#deep").val();
	var pageName = $("#pageName").val();
	if(pageName&&pageName=="MESSAGE_CENTER"){
		
	}else{
		deep = parseInt(deep)+1;
	}
	
	window.location.href = ctx + "/message/toMessageList?deep="+deep;
}