var mySpinner;
var channelType="";
$(function(){
	var opts = {            
            lines: 13, // 花瓣数目
            length: 10, // 花瓣长度
            width: 5, // 花瓣宽度
            radius: 10, // 花瓣距中心半径
            corners: 1, // 花瓣圆滑度 (0-1)
            rotate: 0, // 花瓣旋转角度
            direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
            color: '#618dbe', // 花瓣颜色
            speed: 1, // 花瓣旋转速度
            trail: 60, // 花瓣旋转时的拖影(百分比)
            shadow: false, // 花瓣是否显示阴影
            hwaccel: false, //spinner 是否启用硬件加速及高速旋转            
            className: 'spinner', // spinner css 样式名称
            zIndex: 2e9, // spinner的z轴 (默认是2000000000)
            top: '50%', // spinner 相对父容器Top定位 单位 px
            left: '50%'// spinner 相对父容器Left定位 单位 px
        };

        window.mySpinner = new Spinner(opts);
		var rename=new CompanyRenameController();
		rename.load_company_more_rename();
});
function updateChannelType(type){
	console.log("update channel ,"+type);
	channelType = type;
}
function loadBiz(index){
	
	console.log("load biz page and data...");
	var param = {
			"companyName":encodeURIComponent($(".monitor_company_name").html().trim()),
			"isDecode":true
	}
	$.ajax({
	       type: "post",
	       url: ctx +"/biz/applyCompanyBizView.do",
	       data: param,
	       dataType: "html",
	       beforeSend:function (){
	    	   mySpinner.spin($(".company_modules")[0]);
	       },
	       success: function (data) {
	         console.log("load biz call back..");
	         $(".modules_block_list .modules_block:eq("+index+")").html(data);
	         
	         controller.event_bind_biz();
             //请求高管和法定代表人变更次数,2年内
             applyChgRecodCount(2);
	        
	       },
	  		error: function(){
	  		  
	  		},
	  		complete: function(request, textStatus){
	             console.log("applyCompanyBizView compeleted");
	             mySpinner.spin();
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

function guDongPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"guDong",1);
}
function zhuYaoRenYuanPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"zhuYaoRenYuan",2);
}
function fiLiationPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"fiLiation",3);
}
function entInvPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"entInv",5);	
}
function shareSIMPAWNPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"shareSIMPAWN",6);	
}
function dongChanDiYaPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"dongChanDiYa",7);	
}
function jingYingYiChangPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"jingYingYiChang",8);	
}

function administrativePenaltyPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"administrativePenalty",9);	
}

function brandPageselectCallback(new_page_index, pagination_container){
	appNextPage(new_page_index, pagination_container,"brand",10);	
}

function appNextPage(new_page_index, pagination_container,module,index){
	
	 var pageNo = parseInt(new_page_index) + parseInt(1);
    console.log("curPage="+pageNo+",module="+module+",url="+ ctx+"/biz/"+module+"NextPage.do");
    var parameter = {
           "companyName":encodeURIComponent($(".monitor_company_name").html().trim()),
			"curPage": parseInt(pageNo),
			"module":module,
			"channel":channelType
	 }
    console.log(new_page_index);
	 console.log(parameter);
	 $.ajax({
       type: "post",
       url: ctx+"/biz/"+module+"NextPage.do",
       data: parameter,
       dataType: "html",
       success: function (data) {
           console.log(module+"NextPage call back...");
          // console.log(data);
          $("#"+module+"Div").html(data);
          //var index = $(".current_sub_list .selected").index();
	       $("html,body").stop().animate({scrollTop:$(".current_modules .info_block:eq("+index+")").offset().top-100-$(".company_modules").height()},1);
       /*   $("html, body").animate({
		      scrollTop: ($("#"+module+"Div").offset().top - 280) + "px"
		   }, {
		      duration: 300,
		      easing: "swing"
		  });*/
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

function applyBianGengJiLuDateTable(dateKey,cateArray){
	
	 var param = {
          "companyName":encodeURIComponent($(".monitor_company_name").html().trim()),
          "selectedCategory":cateArray,
          "dateKey":dateKey,
			"channel":channelType
      };
   mySpinner.spin($(".day_list")[0]);
   $.ajax({
	       type: "post",
	       url: ctx+"/biz/getDateTable.do",
	       data: param,
	       dataType: "html",
	       success: function (data) {
	         //console.log("DateTable call back");
	         $("#pagingTable").html(data);
	         $("#bianGengJiLuPageNav").html("")
	         /*var index = 4;
		       if(channelType=="QI_CHA_CHA"){
		    	   index = 3;
		       }
		       $("html,body").stop().animate({scrollTop:$(".current_modules .info_block:eq("+index+")").offset().top-100-$(".company_modules").height()},1000);
	           */
	       },
	  		error: function(){
	  		   //console.log("error");
	  		},
	  		complete: function(request, textStatus){
	  			//console.log("test complete........");
	  			//console.log("request.status="+request.status);
	  			//console.log("complete:"+textStatus+","+request);
	  			//console.log(request);
	  			mySpinner.spin();
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


function applyTimeAxisPage(selectedList){

	 if(!selectedList){
		 selectedList = new Array();
		 $(".info_filter .filter_list .selected").each(function(){
			 selectedList.push($(this).attr("name"));
 		});
	 }
	 
  var param = {
         "companyName":encodeURIComponent($(".monitor_company_name").html().trim()),
         "selectedCategory":selectedList,
			"channel":channelType
     };
  mySpinner.spin($(".day_list")[0]);
  $.ajax({
	       type: "post",
	       url: ctx+"/biz/buildTimeAxis.do",
	       data: param,
	       dataType: "html",
	       success: function (data) {
	        // console.log("buildTimeAxis call back");
	         $(".filter_days").html(data);
	         controller.event_bind_biz_days();
	        // $(".change_day_context").click(change_day_context_click);
        	//选择类别后重新请求分页
	         bianGengJiLuPageSelectCallback(0,null);
	         
	       },
	  		error: function(){
	  		   //console.log("error");
	  		},
	  		complete: function(request, textStatus){
	  		//	console.log("complete:"+textStatus+","+request);
	  			mySpinner.spin();
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

//变更记录分页
function bianGengJiLuPageSelectCallback(new_page_index, pagination_container){
    var pageNo = parseInt(new_page_index) + 1;
    
	 var selectedList = new Array();
	 $(".info_filter .filter_list .selected").each(function(){
		 selectedList.push($(this).attr("name"));
		});
    //console.log("curPage="+pageNo);
    var parameter = {
           "companyName":encodeURIComponent($(".monitor_company_name").html().trim()),
			"pageNo": parseInt(pageNo),
			"selectedCategory":selectedList,
			"channel":channelType
	 }
    //mySpinner.spin($(".day_list")[0]);
	 //console.log(parameter);
	 $.ajax({
       type: "post",
       url: ctx+"/biz/getPagingTable.do",
       data: parameter,
       dataType: "html",
       success: function (data) {
        //  console.log("pagingTable call back");
		   
	       $("#pagingTable").html(data);
	       var index = 4;
	       if(channelType=="QI_CHA_CHA"){
	    	   index = 3;
	       }
	       $("html,body").stop().animate({scrollTop:$(".current_modules .info_block:eq("+index+")").offset().top-100-$(".company_modules").height()},1);
		   if( data.indexOf("无信息") >= 0){
			   $(".filter_days").addClass("hidden");
			   $("#pagingTable").css("margin","10px 0px 0px 0px");
		   }
		   else{
			   $(".filter_days").removeClass("hidden");
			   $("#pagingTable").css("margin","auto");
		   }
       },
  		error: function(){
  		   //console.log("error");
  		},
  		complete: function(request, textStatus){
  			//console.log("complete:"+textStatus+","+request);
   		//console.log(request);
  		///mySpinner.spin();
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
//请求高管、法定代表人 变更次数
function applyChgRecodCount(timeLimit){
	$.ajax({
        type: "post",
        url: ctx+"/biz/getChangeRecordCount.do",
        data: {"companyName":encodeURIComponent($(".monitor_company_name").html().trim()),"timeLimit":timeLimit,
			"channel":channelType},
        dataType: "json",
        success: function (data) {
           console.log("getChangeRecordCount call back");
           //console.log(data);
           if(data.msg){
        	   $("#countRiskMsgDiv").html("温馨提示："+data.msg);
        	   $("#countRiskMsgDiv").parent().removeClass("hidden");
           }else{
        	   $("#countRiskMsgDiv").html("");
        	   $("#countRiskMsgDiv").parent().addClass("hidden");
           }
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
