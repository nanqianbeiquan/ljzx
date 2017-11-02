
$(function(){
	  toastr.options = {
	 	        "closeButton": false, //是否显示关闭按钮
	 	        "debug": false, //是否使用debug模式
	 	        "positionClass": "toast-top-center",//弹出窗的位置
	 	        "showDuration": "300",//显示的动画时间
	 	        "hideDuration": "1000",//消失的动画时间
	 	        "timeOut": "1000", //展现时间
	 	        "extendedTimeOut": "1000",//加长展示时间
	 	        "showEasing": "swing",//显示时的动画缓冲方式
	 	        "hideEasing": "linear",//消失时的动画缓冲方式
	 	        "showMethod": "fadeIn",//显示时的动画方式
	 	        "hideMethod": "fadeOut" //消失时的动画方式
	 };

});
function loadNewsOpinion(index){
	
	console.log("loadNewsOpinion....");
	var param = {
			"companyName":$(".monitor_company_name").html().trim()
	}
	
	console.log("param...."+param);
	
	$.ajax({
	       type: "post",
	       url: ctx +"/newsCtl/toNews",
	       data: param,
	       dataType: "html",
	       beforeSend:function (){
	    	   spinner.spin($(".company_modules")[0]);
	       },
	       success: function (data) {
	         $(".modules_block_list .modules_block:eq("+index+")").html(data);
	       },
	  		error: function(){
	  		  
	  		},
	  		complete: function(request, textStatus){
	 
	  			 spinner.spin();
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


var toNews = function(companyName){
	$.ajax({
		url: ctx + "/newsCtl/toNews",
		type: "POST",
		
		data: {"companyName":companyName},
		dataType: "html",
		success: function(result){
			$("#newsDiv").html(result);
		},
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

// 根据类型获取新闻舆情
var getMediaOpinion = function (companyName){
	
	var para={
			  "companyName":companyName
			};
	
	$.ajax({
		url: ctx + "/newsCtl/getMediaOpinion",
		type: "POST",
		data:para,
		dataType: "html",
		success: function(result){
			console.log("mediaTotalNum =="+$("#mediaTotalNum").val());
			$("#mediaDiv").html(result);
			if($("#mediaTotalNum").val()>0){
				$("#mediaPageDiv").pagination($("#mediaTotalNum").val(), {
	                prev_text: "上一页",
	                next_text: "下一页",
	                num_edge_entries: 3,
	                num_display_entries: 10,
	                items_per_page : 6,
	                //回调 
	                callback: pageselectCallback
	            });
			}else{
				$("#mediaPageDiv").hide();
			}
			getNegativeOpinion(companyName);
		},
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
		}
	});
	
}


//分页回调
var  pageselectCallback = function(new_page_index, pagination_container){

	var currentPageNo = parseInt(new_page_index) + 1;
	var parameter = {
			"companyName":$("#companyName").val(),
			"currentPageNo": parseInt(currentPageNo),
			"pageSize": parseInt(6)
	}
	
    $.ajax({
        type: "post",
        dataType: "html",
        url: ctx + "/newsCtl/getMediaOpinion",
        data: parameter,
        success: function (result) {
        	$("#mediaDiv").html(result);
        },
        error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
		}
    });
}


//根据类型获取新闻舆情
var getNegativeOpinion = function (companyName){
	var para={
			  "companyName":companyName,
			};
	
	$.ajax({
		url: ctx + "/newsCtl/getNegativeOpinion",
		type: "POST",
		data:para,
		dataType: "html",
		success: function(result){
			$("#negativeDiv").html(result);
			console.log("negativeTotalNum =="+$("#negativeTotalNum").val());
			if($("#negativeTotalNum").val()>0){
			  	$("#negativePageDiv").pagination($("#negativeTotalNum").val(), {
	                prev_text: "上一页",
	                next_text: "下一页",
	                num_edge_entries: 3,
	                num_display_entries: 10,
	                items_per_page : 6,
	                //回调 
	                callback: negativePageselectCallback
	            });
			}else{
			  $("#negativePageDiv").hide();
			}
		},
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
		}
	});
	
}


//分页回调
var  negativePageselectCallback = function(new_page_index, pagination_container){
	var currentPageNo = parseInt(new_page_index) + 1;
	var parameter = {
			"companyName":$("#companyName").val(),
			"currentPageNo": parseInt(currentPageNo),
			"pageSize": parseInt(6)
	}
	
    $.ajax({
        type: "post",
        dataType: "html",
        url: ctx + "/newsCtl/getNegativeOpinion",
        data: parameter,
        success: function (result) {
        	$("#negativeDiv").html(result);
        },
        error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
		}
    });
}

/**
 * 新闻舆情lap step1 先定义一个lap窗口 step2强内容绑定到lap中
 */
var create_news_lap=function(){
	var news_lap=new overlap({
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
             width:"800px",
             height:"600px",
             border:"16px solid rgba(0,0,0,0.15)"
         }
     }});
	return news_lap;
}



/*//绑定每一个lap
var news_details_show=function(_this,news_lap){
    $(_this).click(function(){
    	//打开新闻舆情的窗口
    	var eventId=$(this).attr("eventId");
    	getDetails(eventId);
    	news_lap.show(".news");
        return false;
    });
    
}*/

var getOpinionDetailResult = function(newsKey,newsId,companyName,opinionType){
	
	var parameter = {
			"newsKey":newsKey,
			"newsId": newsId,
			"companyName": companyName,
			"opinionType":opinionType
	}
	
    $.ajax({
        type: "post",
        dataType: "html",
        url: ctx + "/newsCtl/getOpinionDetailResult",
        data: parameter,
        success: function (result) {
        	console.log("clear....");
        	$("#newsDetailDiv").html("");
        	$("#newsDetailDiv").html(result);
          	var news_lap = create_news_lap();
        	news_lap.show("#newsDetailDiv");
			function scroll_move(e){
				e = e || window.event;
				var delta = e.wheelDelta || e.detail || e.originalEvent.detail || e.originalEvent.deltaY || -1 * e.originalEvent.wheelDelta;
				delta = -1 * (delta / Math.abs(delta)) * 100;
				var top = $(this).scrollTop() - delta;
				$(this).scrollTop(top);
				if($(this).scrollTop()<=0){
					top = $(this).find(".content").scrollTop() - delta;
					$(this).find(".content").scrollTop(top);
				}
				//取消事件冒泡
				e = arguments.callee.caller.arguments[0] || e; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
				if (e && e.stopPropagation) {
					// this code is for Mozilla and Opera
					e.stopPropagation();
				} else if (window.event) {
					// this code is for IE
					window.event.cancelBubble = true;
				}
				return false;
			}
			$("#newsDetailDiv .detail_content_block").bind('mousewheel', scroll_move);
			$("#newsDetailDiv.detail_content_block").bind('DOMMouseScroll', scroll_move);
        },
        error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
		}
    });
	
}

var getOpinionDetailById = function(newsKey,newsId,companyName,opinionType){
	// queryNewsDetails
	getOpinionDetailResult(newsKey,newsId,companyName,opinionType);

}

/**
 * 棱镜报告；
 */
function lengjingReport(){
	   var companyName=$(".monitor_company_name").html().trim();
	   console.log("lengjingReport  companyName ==="+companyName);
	   toastr.info('下载中......');
	   window.location = ctx+"/reportController/createPdfInPage?companyName="+encodeURIComponent(companyName);
}