$(function(){
//	console.log("init nav menu");
//	console.log(window.location);
	
	
});

function updateNavMenu(deep,name,paramObj,callback){
	var baseURL = window.location.pathname;
	var baseParamURL=window.location.search;
	var paramURL="?";
	
	if(paramObj instanceof Object){
		for(var attr in paramObj){
			paramURL += attr+"="+paramObj[attr]+"&";
		}
	}else{
		paramURL = paramObj;
	}
	
	var param = {
			"deep":deep,"name":name,"baseURL":baseURL,"baseParamURL":baseParamURL,"paramURL":paramURL
	}
	
	$.ajax({
    	url: ctx+"/navMenu/updateNavPath.do",
    	type: "POST",
    	data: param,
    	dataType: "html",
    	success: function(data){
    		$(".nav_bar").html(data);
    		if(callback && typeof callback == 'function'){
    			callback();
    		}
    	},
    	error: function(){},
    	complete: function(request, textStatus){
    		if(request){if(request.status==0){toastr.error('网络异常！');}else if(request.status==500){toastr.error('站点异常！');}else{if(!textStatus||textStatus!="success"){toastr.error('站点异常！');}}}
    	}
    });
	
}

function backTo(url){
	
	window.location.href=url;
}