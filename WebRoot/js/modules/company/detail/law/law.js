var lap
var dishonestInfo
$(function(){
	  lap=new overlap({
	        scroller:{
	        	lock:true,
	            body_hidden:true
	        },
	    
	        content:{
	            style:{
	             width:"800px",
	   	         height:"600px",
	   	         border:"16px solid rgba(0,0,0,0.15)"
	            }
	  }});
	  dishonestInfo =new overlap({
          mask:{
           show:true
       },
       scroller:{
           lock:true,
           body_hidden:true
       },
       position:{
           type:"center"
       },
       close:{
           show:true,
           type:"close_2",
           style:{

           },
           callBack:function(){
               open_new_group=false;
           }
       },
       content:{
           style:{
               width:"600px",
               height:"580px",
               border:"16px solid rgba(0,0,0,0.15)"
           }
       }});
});

function getLegalInstrumentDetails(judgmentId){
	
	var parameter = {
			"judgmentId":judgmentId,
			"companyName":$(".monitor_company_name").html().trim()
	}
	console.log(parameter);
	$.ajax({
		url: "/ljzx/law/viewLegalInstrumentDetail",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			
			var title=result.title;
			if(1<=title.length && title.length<23){
				$("#title4Sx").css("font-size","30px");
			}else if(23<=title.length && title.length<58){
				$("#title4Sx").css("font-size","24px");
			}else if(58<=title.length && title.length<114){
				$("#title4Sx").css("font-size","18px");
			}
			
			$("#judgmentTime4Sx").html(result.judgmentTime);
			$("#title4Sx").html(title);
			$("#details4Sx").html(result.details);
			$(".content_type").html(result.instrumentType);
			$(".content_case").html(result.caseNo);
			$(".content_court").html(result.courtName);
			$("#details4Sx").html(result.content);
			
		},error : function() {
		},complete : function(request, textStatus) {
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


function getCourtDetails(courtId){
	
	var parameter = {
			"courtId":courtId,
			"companyName":$(".monitor_company_name").html().trim()
	}
	console.log(parameter);
	$.ajax({
		url: "/ljzx/law/getCourtDetails",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			$("#courtTime").html(result.publishDate);
			$("#courtTitle").html(result.party);
			$(".courtName").html(result.courtName);
			$(".court_type").html(result.announcementType);
			$(".content_party").html(result.party);
			$("#courtContent").html(result.announcementContent);
			
		},error : function() {
		},complete : function(request, textStatus) {
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

function getDishonestInfoDetails(caseNo){
	
	var parameter = {
			"caseNo":caseNo,
			"companyName":$(".monitor_company_name").html().trim()
	}
	console.log(parameter);
	$.ajax({
		url: "/ljzx/law/getDishonestInfoDetails",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			
			$("#mc4Shixin").html(result.executorName);
			$("#dm4Shixin").html(result.organizationCode);
			$("#fddbr4Shixin").html(result.legalPerson);
			$("#zxfy4Shixin").html(result.executionCourt);
			$("#sf4Shixin").html(result.province);
			$("#zxyjwh4Shixin").html(result.accordingDocumentNo);
			$("#sj4Shixin").html(result.filingTime);
			$("#ah4Shixin").html(result.caseNo);
			$("#zxyjdw4Shixin").html(result.executionOffice);
			$("#wsqdyw4Shixin").html(result.duty);
			$("#lxqk4Shixin").html(result.performanceStatus);
			$("#sxjtqk4Shixin").html(result.specificCircumstance);
			$("#fbsj4Shixin").html(result.publishDate);
			
		},error : function() {
		},complete : function(request, textStatus) {
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

function gongGaoPageselectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	var parameter = {
		"companyName" : $(".monitor_company_name").html().trim(),
		"curPage" : parseInt(pageNo),
	}
	$.ajax({
		type : "post",
		url : "/ljzx/law/gongGaoNextPage",
		data : parameter,
		dataType : "html",
		success : function(data) {
			//console.log("inner call back...");
			$("#courtDiv").html(data);
			$("html, body") .animate(
							{
								scrollTop : ($("#courtDiv")
										.offset().top - 240)
										+ "px"
							}, {
								duration : 300,
								easing : "swing"
							});
			window.location.hash = "#courtDiv";
		},
		error : function() {
			console.log("error");
		},
		complete : function(request, textStatus) {
			if (request) {
				if (request.status == 0) {
					toastr.error('网络异常！');
				} else if (request.status == 500) {
					toastr.error('站点异常！');
				} else {
					if (!textStatus || textStatus != "success") {
						toastr.error('站点异常！');
					}
				}
			}
		}
	});
}

function shiXinPageselectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	var parameter = {
		"companyName" : $(".monitor_company_name").html().trim(),
		"curPage" : parseInt(pageNo),
	}
	//console.log(parameter);
	$.ajax({
		type : "post",
		url : "/ljzx/law/shiXinNextPage",
		data : parameter,
		dataType : "html",
		success : function(data) {
			//console.log("inner call back...");
			$("#dishonestDiv").html(data);
				$("html, body").animate({
					scrollTop : ($("#dishonestDiv").offset().top - 240) + "px"
				}, {
					duration : 300,
					easing : "swing"
				});
				window.location.hash = "#dishonestDiv";
		},
		error : function() {
			console.log("error");
		},
		complete : function(request, textStatus) {
			if (request) {
				if (request.status == 0) {
					toastr.error('网络异常！');
				} else if (request.status == 500) {
					toastr.error('站点异常！');
				} else {
					if (!textStatus || textStatus != "success") {
						toastr.error('站点异常！');
					}
				}
			}
		}
	});
}

function LegalInstrumentPageselectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	var parameter = {
		"companyName" : $(".monitor_company_name").html().trim(),
		"curPage" : parseInt(pageNo),
	}
	//console.log(parameter);
	$.ajax({
		type : "post",
		url : "/ljzx/law/LegalInstrumentPage",
		data : parameter,
		dataType : "html",
		success : function(data) {
			$("#legalDiv").html(data);
			$("html, body").animate({
				scrollTop : ($("#legalDiv").offset().top - 240) + "px"
			}, {
				duration : 300,
				easing : "swing"
			});
			window.location.hash = "#legalDiv";
		},
		error : function() {
			console.log("error");
		},
		complete : function(request, textStatus) {
			if (request) {
				if (request.status == 0) {
					toastr.error('网络异常！');
				} else if (request.status == 500) {
					toastr.error('站点异常！');
				} else {
					if (!textStatus || textStatus != "success") {
						toastr.error('站点异常！');
					}
				}
			}
		}
	});
}

function kaiTingPageselectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	var parameter = {
		"companyName" : $(".monitor_company_name").html().trim(),
		"curPage" : parseInt(pageNo),
	}
	//console.log(parameter);
	$.ajax({
		type : "post",
		url : "/ljzx/law/kaiTingGongGaoNextPage",
		data : parameter,
		dataType : "html",
		success : function(data) {
			//console.log("inner call back...");
			$("#noticeDiv").html(data);
			/*	$("html, body").animate({
					scrollTop : ($("#noticeDiv").offset().top - 240) + "px"
				}, {
					duration : 300,
					easing : "swing"
				});
				window.location.hash = "#noticeDiv";*/
		},
		error : function() {
			console.log("error");
		},
		complete : function(request, textStatus) {
			if (request) {
				if (request.status == 0) {
					toastr.error('网络异常！');
				} else if (request.status == 500) {
					toastr.error('站点异常！');
				} else {
					if (!textStatus || textStatus != "success") {
						toastr.error('站点异常！');
					}
				}
			}
		}
	});
}

function personPageselectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	var parameter = {
		"companyName" : $(".monitor_company_name").html().trim(),
		"curPage" : parseInt(pageNo),
	}
	//console.log(parameter);
	$.ajax({
		type : "post",
		url : "/ljzx/law/personNextPage",
		data : parameter,
		dataType : "html",
		success : function(data) {
			//console.log("inner call back...");
			$("#personDiv").html(data);
			$("html, body").animate({
				scrollTop : ($("#personDiv").offset().top - 240) + "px"
			}, {
				duration : 300,
				easing : "swing"
			});
			window.location.hash = "#personDiv";
		},
		error : function() {
			console.log("error");
		},
		complete : function(request, textStatus) {
			if (request) {
				if (request.status == 0) {
					toastr.error('网络异常！');
				} else if (request.status == 500) {
					toastr.error('站点异常！');
				} else {
					if (!textStatus || textStatus != "success") {
						toastr.error('站点异常！');
					}
				}
			}
		}
	});
}

//查看司法文书详情
function viewInstrumentDetail(judgmentId){
	getLegalInstrumentDetails(judgmentId);
	lap.show(".sfws");
}

//查看失信信息详情
function viewDishonestInfoDetails(caseNo){
	getDishonestInfoDetails(caseNo);
	dishonestInfo.show(".shixin");
}

//查看法院公告详情
function viewCourtDetails(courtId){
	getCourtDetails(courtId);
	lap.show(".fygg");
	
}

function redictLe(){
	var companyName = $(".monitor_company_name").html().trim();
	var currentDeep = $("#currentDeep").val();
	
	var param = {
		"companyName":companyName,
		"deep":currentDeep,
		"selectedModuleIndex":1
	};
	
	updateNavMenu(parseInt(currentDeep),"企业详情",param,function(){
		spinner.spin($(".body_content_5")[0]);
		window.location.href = "/ljzx/law/toLegalInstrumentList?companyName="+companyName+"&currentDeep="+(parseInt(currentDeep)+1);
	});
}

function loadLaw(index){
	var parameter = {
			"companyName":$(".monitor_company_name").html().trim()
	}
	console.log(parameter);
	$.ajax({
		url: "/ljzx/law/toLawList",
		type: "POST",
		data: parameter,
		dataType: "html",
        beforeSend:function (){
    	   spinner.spin($(".company_modules")[0]);
        },
		success: function(result){
			spinner.spin();
			 $(".modules_block_list .modules_block:eq("+index+")").html(result);
		},error : function() {
			spinner.spin();
		},complete : function(request, textStatus) {
			
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