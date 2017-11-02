var controller;
$(function(){
     controller = new personGroupController();
     var deep = $("#deep").val();
     
     updateNavMenu(parseInt(deep),"关系个人详情",window.location.search);
     
     moduleSelected("30");//初始化加载 开庭公告
     moduleSelected("31");//初始化加载 司法案件
     moduleSelected("35");//初始化加载 被执行人
     moduleSelected("36");//初始化加载 失信信息
})

function moduleSelected(eventSubType){
	var param = {
			"name":encodeURIComponent($("#idName").val()),
			"idNumber":$("#idNumber").val(),
			"city":encodeURIComponent($("#city").val()),
			"province":encodeURIComponent($("#province").val()),
			"eventSubType":eventSubType,
			"pageNo":1,
			"pageSize":6
	}
	
	$.ajax({
    	url: ctx + "/monitorRelationPerson/getRelationPersonEventList",
    	type: "POST",
    	data: param,
    	dataType: "html",
    	success: function(data){
    		if(eventSubType==30){
    			$("#kaiTingGongGaoDiv").html(data);
    		}
    		if(eventSubType==31){
    			$("#caipangDiv").html(data);
    		}
    		if(eventSubType==35){
    			$("#personSubjectDiv").html(data);
    		}
    		if(eventSubType==36){
    			$("#dishonestInfoDiv").html(data);
    		}
    	},
    	error: function(){},
    	complete: function(request, textStatus){
    		if(request){if(request.status==0){toastr.error('网络异常！');}else if(request.status==500){toastr.error('站点异常！');}else{if(!textStatus||textStatus!="success"){toastr.error('站点异常！');}}}
    	}
    });
}

//查看自然人司法文书详情
function viewInstrumentDetail(key){
	var parameter = {
			"key":key,
			"eventSubType":31
	}
	
	$.ajax({
		url: ctx + "/monitorRelationPerson/getInstrumentDetail",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			for (var r in result){
				var title=result[r].anjianmingcheng;
				if(1<=title.length && title.length<23){
					$("#title4Sx").css("font-size","30px");
				}else if(23<=title.length && title.length<58){
					$("#title4Sx").css("font-size","24px");
				}else if(58<=title.length && title.length<114){
					$("#title4Sx").css("font-size","18px");
				}
				
				$("#judgmentTime4Sx").html(result[r].caipanriqi);
				$("#title4Sx").html(title);
				$("#details4Sx").html(result[r].fayuan);
				$(".content_type").html(result[r].wenshuleixing);
				$(".content_case").html(result[r].anhao);
				$(".content_court").html(result[r].fayuan);
				$("#details4Sx").html(result[r].quanwen);
				
				lap.show(".sfws");
			}	
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

//查看自然人失信信息详情
function viewDishonestDetail(key){
	var parameter = {
			"key":key,
			"eventSubType":36
	}
	
	$.ajax({
		url: ctx + "/monitorRelationPerson/getDishonestInfoDetail",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			for (var r in result){
				$("#mc4Shixin").html(result[r].mc);
				$("#dm4Shixin").html(result[r].dm);
				$("#fddbr4Shixin").html(result[r].mc);
				$("#zxfy4Shixin").html(result[r].zxfy);
				$("#sf4Shixin").html(result[r].sf);
				$("#zxyjwh4Shixin").html(result[r].zxyjwh);
				$("#sj4Shixin").html(result[r].sj);
				$("#ah4Shixin").html(result[r].ah);
				$("#zxyjdw4Shixin").html(result[r].zxyjdw);
				$("#wsqdyw4Shixin").html(result[r].wsqdyw);
				$("#lxqk4Shixin").html(result[r].lxqk);
				$("#sxjtqk4Shixin").html(result[r].sxjtqk);
				$("#fbsj4Shixin").html(result[r].fbsj);
				
				dishonestInfo.show(".shixin");
			}	
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

function siFaAnJianPageselectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	console.log(pageNo);
	var parameter = {
			"name":encodeURIComponent($("#idName").val()),
			"idNumber":$("#idNumber").val(),
			"city":encodeURIComponent($("#city").val()),
			"province":encodeURIComponent($("#province").val()),
			"eventSubType":31,
			"pageNo" : parseInt(pageNo),
			"pageSize":6
	}
	
	$.ajax({
		type : "post",
		url: ctx + "/monitorRelationPerson/getRelationPersonEventList",
		data : parameter,
		dataType : "html",
		success : function(data) {
			$("#caipangDiv").html(data);
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


function dishonestInfoSelectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	var parameter = {
			"name":encodeURIComponent($("#idName").val()),
			"idNumber":$("#idNumber").val(),
			"city":encodeURIComponent($("#city").val()),
			"province":encodeURIComponent($("#province").val()),
			"eventSubType":36,
			"pageNo" : parseInt(pageNo),
			"pageSize":6
	}
	$.ajax({
		type : "post",
		url: ctx + "/monitorRelationPerson/getRelationPersonEventList",
		data : parameter,
		dataType : "html",
		success : function(data) {
			$("#dishonestInfoDiv").html(data);
			$("html, body").animate({
				scrollTop : ($("#dishonestInfoDiv").offset().top - 120) + "px"
			}, {
				duration : 300,
				easing : "swing"
			});
			window.location.hash = "#dishonestInfoDiv";
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
			"name":encodeURIComponent($("#idName").val()),
			"idNumber":$("#idNumber").val(),
			"city":encodeURIComponent($("#city").val()),
			"province":encodeURIComponent($("#province").val()),
			"eventSubType":35,
			"pageNo" : parseInt(pageNo),
			"pageSize":6
	}
	$.ajax({
		type : "post",
		url: ctx + "/monitorRelationPerson/getRelationPersonEventList",
		data : parameter,
		dataType : "html",
		success : function(data) {
			$("#personSubjectDiv").html(data);
			$("html, body").animate({
				scrollTop : ($("#personSubjectDiv").offset().top - 120) + "px"
			}, {
				duration : 300,
				easing : "swing"
			});
			window.location.hash = "#personSubjectDiv";
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


function kaiTingGongGaoPageselectCallback(new_page_index, pagination_container) {
	var pageNo = parseInt(new_page_index) + 1;
	var parameter = {
			"name":encodeURIComponent($("#idName").val()),
			"idNumber":$("#idNumber").val(),
			"city":encodeURIComponent($("#city").val()),
			"province":encodeURIComponent($("#province").val()),
			"eventSubType":30,
			"pageNo" : parseInt(pageNo),
			"pageSize":6
	}
	$.ajax({
		type : "post",
		url: ctx + "/monitorRelationPerson/getRelationPersonEventList",
		data : parameter,
		dataType : "html",
		success : function(data) {
			$("#kaiTingGongGaoDiv").html(data);
			$("html, body").animate({
				scrollTop : ($("#kaiTingGongGaoDiv").offset().top - 120) + "px"
			}, {
				duration : 300,
				easing : "swing"
			});
			window.location.hash = "#kaiTingGongGaoDiv";
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

var personGroupController=function(){
    var event_module_menu=function(){
        //ToDo:顶部菜单栏浮动
        var position=$(".company_modules").offset().top;
        var position_left=$(".company_modules").offset().left;
        var height=$(".company_modules").height();
        var moved=false;
        $(document).scroll(function(value){
            var top=$(document).scrollTop()+82;
            if(top>=position){
                if(moved){
                    return;
                }
                moved=true;
                $(".company_modules_blodk").css({"height":$(".company_modules").height()});
                $(".company_modules").stop().css({"position":"fixed","top":"80px","background":"#ffffff","left":position_left});
                $(".company_modules .modules_sub_list").addClass("head_shadow")//.find(".modules_sub_list").css({"border":"1px solid #ffffff"});
               
            }else{
                moved=false;
                $(".company_modules").removeClass("head_shadow").css({"position":"static","top":"auto","background":"auto"});
                $(".company_modules .modules_sub_list").removeClass("head_shadow");
            }
        })
        //ToDo:菜单点击效果
        $(".modules_list .modules").click(function(){
            var index=$(this).index();
            $(".modules_bottom_selector").animate({"left":index*200},200);
            $(".modules_list .selected").removeClass("selected");
            $(this).addClass("selected");
            
            $("html,body").stop().animate({scrollTop:$(".current_modules .info_block:eq("+index+")").offset().top-100-$(".company_modules").height()},1000);
        })
    }
    var event_nav=function(){
        var content_arr=[];
        var current_index=1;
        $(".person_info").each(function(){
            var model={};
            model.top=$(this).offset().top-$(".nav_head ").height();
            content_arr.push(model);
        })
        var click_nav=false;
        $(".nav_li .nav_value").click(function(){
            click_nav=true;
            var _this=this;
            var index=parseInt($(_this).attr("index"));
            $("html,body").animate({scrollTop:content_arr[index-1].top},200);
            var top=$(_this).position().top+$(".mark_selected img").height();
            $(".mark_selected").animate({top:top},200,function(){
                click_nav=false;
            });
        });
        var last_scrollTop=$(document).scrollTop();
        var move_nav=function(){
            var scrollTop=$(document).scrollTop();
            if(scrollTop==last_scrollTop){
                for(var i=0;i<content_arr.length;i++){
                    if(scrollTop<=content_arr[i].top){
                        if(current_index==i+1){
                            return;
                        }
                        current_index=i+1;
                        break;
                    }
                }
                $(".nav_li .nav_value").each(function(){
                    if($(this).attr("index")==current_index){
                        var top=$(this).position().top+$(".mark_selected img").height();
                        $(".mark_selected").animate({top:top},300);
                    }
                })
            }else{
                last_scrollTop=scrollTop;
                setTimeout(function(){
                    move_nav()
                },200);
            }
        }
        $(window).scroll(function(e,a) {
            if(click_nav){
                return;
            }
            move_nav();
        });
    }
    var load_company_span=function(){
    	var width=0;
    	$(".person_name").find("span").each(function(){
    		width+=$(this).width();
    	})
    	console.log(width);
    	if(width>160){
    		$(".company_span").css("padding-left",width+20);
    		$(".company_span .span_list").css("width",1128-width-20-60-10);
    	}
    }

	var Binder={
		bind_overlap_event_content_scroll:function(){
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
			$(".detail_content_block").bind('mousewheel', scroll_move);
			$(".detail_content_block").bind('DOMMouseScroll', scroll_move);
			$(".detail_block .table_value").bind('mousewheel', scroll_move);
			$(".detail_block .table_value").bind('DOMMouseScroll', scroll_move);
			return this;
		}
	}

    var personGroupController=function(){
        this.load_page();
        this.event_bind();
    }
    personGroupController.prototype={
        load_page:function(){
            load_company_span();
        },
        event_bind:function(){
            event_module_menu();
			Binder.bind_overlap_event_content_scroll();
        }
    };
    personGroupController.prototype.constructor = personGroupController;
	return personGroupController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

