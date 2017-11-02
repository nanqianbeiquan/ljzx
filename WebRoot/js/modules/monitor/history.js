var controller;
var echart_core;
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

	     window.spinner = new Spinner(opts);
	
    echart_core= new echartController();
    controller = new monitorHistoryController();
    var selectedModuleIndex=$("#selectedModuleIndex").val()||0;
    if(selectedModuleIndex>3){
    	selectedModuleIndex=0;
    }
    $(".modules").get(selectedModuleIndex).click();
    
    var deep = $("#currentDeep").val();
    updateNavMenu(parseInt(deep),"企业详情",window.location.search);
})

var monitorHistoryController=function(){
    var event_module_menu=function(){
        //ToDo:顶部菜单栏浮动
        var position=$(".company_modules").offset().top;
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
                $(".company_modules").stop().css({"position":"fixed","top":"80px","background":"#ffffff"});
                $(".company_modules .modules_sub_list").addClass("head_shadow")//.find(".modules_sub_list").css({"border":"1px solid #ffffff"});
               
            }else if(position>(top+57)){
                moved=false;
                $(".company_modules").removeClass("head_shadow").css({"position":"static","top":"auto","background":"auto"});
                $(".company_modules .modules_sub_list").removeClass("head_shadow");
            }
        })
        //ToDo:菜单悬停效果
        var timeout;
        var showhover=false;
        var hover_show=function(call_back){
            $(".modules_bottom_hover").animate({"top":"-3px"},100,function(){
                if(typeof(call_back)=="function"){
                    call_back()
                }
            });
        }
        var hover_hide=function(call_back){
            $(".modules_bottom_hover").animate({"top":"0px"},100,function(){
                if(typeof(call_back)=="function"){
                    call_back()
                }
            });
        }
        var hover_move_animate=function(index){
            $(".modules_bottom_hover").animate({"left":index*200},200);
        }
        var hover_move=function(index){
            $(".modules_bottom_hover").css({"left":index*200});
        }
        var animate_stop=function(){
            $(".modules_bottom_hover").stop();
        }
        /*$(".modules_list .modules").hover(function(){
            animate_stop();
            var index=$(this).index();
            clearTimeout(timeout);
            if(!showhover){
                hover_move(index);
                hover_show();
            }else{
                hover_move_animate(index);
            }
            
        },function(){
            animate_stop();
            timeout=setTimeout(function(){
                hover_hide();
            },100)
        })*/
        //ToDo:菜单点击效果
        $(".modules_list .modules").click(function(){
            var index=$(this).index();
            $(".modules_bottom_selector").animate({"left":index*200},200);
            $(".modules_list .selected").removeClass("selected");
            $(this).addClass("selected");
            //ToDo:模块变化
            $(".modules_block_list .current_modules").removeClass("current_modules").addClass("hidden");
            $(".modules_block_list .modules_block:eq("+index+")").addClass("current_modules").removeClass("hidden");
            //ToDo:子菜单切换
            $(".modules_sub_list .sub_list").removeClass("current_sub_list").addClass("hidden");
            $(".modules_sub_list .sub_list:eq("+index+")").addClass("current_sub_list").removeClass("hidden");
            //ToDo:子菜单选择
            //$(".modules_sub_list .current_sub_list .selected").click();
            $(".company_modules_blodk").css({"height":$(".company_modules").height()});
            controller.loadModule(index);
            
            
        })
        //ToDo:子菜单点击效果
        $(".modules_sub_list .sub_modules").click(function(){
            var index=$(this).index();
            $(".current_sub_list .selected").removeClass("selected");
            $(this).addClass("selected");
            $("html,body").stop().animate({scrollTop:$(".current_modules .info_block:eq("+index+")").offset().top-100-$(".company_modules").height()},1000);
        })
    }
    var event_switch_btn=function(){
    	//ToDo:每日记录移动
        var current=0;
        var index=$(".day_list .day").length;
        $(".filter_days .left_btn").click(function(){
            if(current>0){
                current--;
                $(".day_list .day").each(function(){
                    $(this).animate({left:"+="+170});
                })
            }
            
        })
        $(".filter_days .right_btn").click(function(){
        	console.log("right btn clicked,current="+current+",index="+index);
            if(current<index-6){
                current++;
                $(".day_list .day").each(function(){
                    $(this).animate({left:"-="+170});
                })
            }
            
        })
    }
    var event_days=function(){
    	console.log("event days add events.");
        //时间轴点击事件
        $(".day_list .day").click(function(){
        	
        	if($(this).hasClass("selected")){
        		applyTimeAxisPage(null);
        	}else{
        		$(".day_list .selected").removeClass("selected");
            	$(this).addClass("selected");
            	var dateKey = $(this).find(".title").text().trim();
            	
            	var cateArray = new Array();
            	
                $(this).find(".value .line_txt .cate").each(function(){
            		cateArray.push($(this).text());
            	});
            	console.log("dateKey="+dateKey+",cates="+cateArray);
            	applyBianGengJiLuDateTable(dateKey,cateArray);
        	}
        	
        	
        	
        });
        
    }
    var event_category=function(){
    	$(".info_filter .filter_list .value").click(function(){
    		if($(this).hasClass("selected")){
    			
    			if($(this).text().trim().indexOf("全部")>=0){
    			    //return;
    	    		//$(".info_filter .filter_list .selected").removeClass("selected");
    	    	}else{
    	    		if($(".info_filter .filter_list .selected").length==1){
    	    			$(".info_filter .filter_list .value:eq(0)").addClass("selected");
    				}
    	    		$(this).removeClass("selected");
    	    	}
    			
    	    }else{//被点击之前的状态为 未选择
    	    	console.log($(this).text().trim().indexOf("全部"));
    	    	//点击了 全部
    	    	if($(this).text().trim().indexOf("全部")>=0){
    	    		$(".info_filter .filter_list .selected").removeClass("selected");
    	    		$(this).addClass("selected");
    	    	}else {
    	    		
        	    	if($(".info_filter .filter_list .value:eq(0)").hasClass("selected")){
        	    		$(".info_filter .filter_list .value:eq(0)").removeClass("selected");
        	    	}
        	    	if($(".info_filter .filter_list .selected").length==6){//所有种类都选中则认为选中的全部
        	    		$(".info_filter .filter_list .selected").removeClass("selected");
        	    		$(".info_filter .filter_list .value:eq(0)").addClass("selected");
        	    	}else{
        	    		$(this).addClass("selected");
        	    	}
    	    	}
    	    }
//    		var selectedArray = new Array();
//    		$(".info_filter .filter_list .selected").each(function(){
//    			selectedArray.push($(this).attr("name"));
//    		});
//    		console.log(selectedArray);
    		applyTimeAxisPage(null);
    	});
    	
    }

    function bind_overlap_event_content_scroll(){
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
            $(".shixin .table_value").bind('mousewheel', scroll_move);
			$(".shixin .table_value").bind('DOMMouseScroll', scroll_move);
		}

    var monitorHistoryController=function(){
    	
        this.load_page();
        this.event_bind();
    }
    monitorHistoryController.prototype={
        load_page:function(){
        	updateChannelType("ZHONG_SHU");
        },
        event_bind:function(){
        	//event_days();
            event_module_menu();
            bind_overlap_event_content_scroll();
        },
        event_bind_biz:function(){
        	event_switch_btn();
        	event_days();
        	event_category();
        },
        event_bind_biz_days:function(){
        	event_switch_btn();
        	event_days();
        },
        loadModule:function(index){
  			console.log("load module,index="+index);      	
  			
        	//index==0,加载工商数据及页面        	
        	if(index==0){//工商
        		loadBiz(index);
        	}else if(index==1){
        		loadLaw(index);
        	}else if(index==2){
        		loadNewsOpinion(index);
        	}else if(index==3){
        		
        	}else if(index==4){
        		loadRiskAnalysis(index);
        	}
        }
    };
    monitorHistoryController.prototype.constructor = monitorHistoryController;
	return monitorHistoryController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

