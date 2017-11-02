$(function(){
	 document.onkeydown=function(event){
         var e = event || window.event || arguments.callee.caller.arguments[0];         
         if(e && e.keyCode==13){ // enter 键
             var span=$(".tips_block .selected").attr("span");
             $(".search_btn_"+span).click();
         }
     }; 

	$(".tips_block .select_tip_span").click(function(){
		var _this=this;
		if($(_this).hasClass("selected"))
		{
			return;
		}
		var _selected=$(".tips_block .selected");
		
		var current_index=$(_this).attr("span");
		var last_index=$(_selected).attr("span");
		
		$(_selected).removeClass("selected");
		$(_this).addClass("selected");
		
		$(".select_tip_"+current_index).removeClass("hidden");
		$(".select_tip_"+last_index).addClass("hidden");
		
	});
});

function idCardList(){
	if(!idCardValidate()){//验证表单
		return false;
	}else{
		$("#idCardForm").submit();
	}
}

function blackMessagePageCallback(new_page_index, pagination_container){
    var pageNo = parseInt(new_page_index) + 1;
   // console.log("curPage="+pageNo);
    var parameter = {
			"curPage": parseInt(pageNo)
	 }
	// console.log(parameter);
	 $.ajax({
       type: "post",
       url: "/ljzx/newPerson/BlackMessageNextPage",
       data: parameter,
       dataType: "html",
       success: function (data) {
    	   $("#PersonBlackDiv").html(data);
    	   $("html, body").animate({
    			      scrollTop: ($("#PersonBlackDiv").offset().top - 200) + "px"
    			   }, {
    			      duration: 300,
    			      easing: "swing"
    			  });
       },
  		error: function(){
  		  // console.log("error");
  		},
  		complete: function(request, textStatus){
  			//console.log("complete:"+textStatus+","+request);
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

function badMessagePageCallback(new_page_index, pagination_container){
    var pageNo = parseInt(new_page_index) + 1;
    //console.log("curPage="+pageNo);
    var parameter = {
			"curPage": parseInt(pageNo)
	 }
	 //console.log(parameter);
	 $.ajax({
       type: "post",
       url: "/ljzx/newPerson/BadMessageNextPage",
       data: parameter,
       dataType: "html",
       success: function (data) {
    	   $("#personBadDiv").html(data);
    	   $("html, body").animate({
    			      scrollTop: ($("#personBadDiv").offset().top - 200) + "px"
    			   }, {
    			      duration: 300,
    			      easing: "swing"
    			  });
       },
  		error: function(){
  		  // console.log("error");
  		},
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

function idCardValidate(){
	if(isEmpty($("#idCardNameNew").val().trim())){
		toastr.warning('姓名不能为空！');
		return false;
	}
	if(isEmpty($("#identityCardNew").val().trim())){
		toastr.warning('身份证号码不能为空！');
		return false;
	}else{
		var reg =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
		if(reg.test($("#identityCardNew").val().trim())==false){  
			toastr.warning("身份证号码输入不合法");  
			return  false;  
		}  
	}
	return true;
}

function idCardBlackValidate(){
	if(isEmpty($("#identityCardBlack").val().trim())){
		toastr.warning('身份证号码不能为空！');
		return false;
	}else{
		var reg =/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
		if(reg.test($("#identityCardBlack").val().trim())==false){  
			toastr.warning("身份证号码输入不合法");  
			return  false;  
		}  
	}
	return true;
}
