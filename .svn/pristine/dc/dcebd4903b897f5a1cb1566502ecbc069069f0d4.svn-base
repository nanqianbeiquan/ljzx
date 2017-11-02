var controller;
$(function(){
     controller = new monitorEventController();
     $(".filter_open_btn").click();
})

var monitorEventController=function(){
    var event_filter=function(){
        
    }
    var event_group=function(){
        var show_filter=function(){
            $(".monitor_event_filter").animate({height:$(".filter_block").outerHeight(true)})
            $(".filter_open_btn").addClass("hidden");
            $(".filter_close_btn").removeClass("hidden");
        }
        var close_filter=function(){
            $(".monitor_event_filter").animate({height:0});
            $(".filter_open_btn").removeClass("hidden");
            $(".filter_close_btn").addClass("hidden");
        }
        $(".filter_open_btn").click(show_filter);
        $(".filter_close_btn").click(close_filter);
    }
    
    var event_filter=function(model){
    	$(".filter_text .option_text").click(function(){
    		if($(this).hasClass("all_option")){
    			$(this).parents(".filter_options").find(".selected").removeClass("selected");
        		$(this).addClass("selected");
    		}else{
    			$(this).parents(".filter_options").find(".all_option").removeClass("selected");
    			if($(this).hasClass("selected")){
    				$(this).removeClass("selected");
    				if($(this).parents(".filter_options").find(".selected").length==0){
    					$(this).parents(".filter_options").find(".all_option").addClass("selected");
    				}
    			}else{
    	    		$(this).addClass("selected");
    			}
    		}
    		bind_model(model);
    	})
    	$(".clear_txt").click(function(){
    		$(".filter_text .selected").removeClass("selected");
    		$(".filter_text .all_option").addClass("selected");
    		$(".filter_input input").val("");
    		bind_model(model);
    	})
    }
    
    var bind_model=function(model,page){
    	$(".filter_text").each(function(){
    		var arr=[];
    		$(this).find(".selected").each(function(){
    			if($(this).hasClass("all_option")){
    				$(this).parents(".filter_options").find(".option_text:gt(0)").each(function(){
    					arr.push($(this).find(".option").attr("value"));
    				})
    			}else{
    				arr.push($(this).find(".option").attr("value"));
    			}
    		})
    		model[$(this).find(".filter_name").attr("filter_name")]=arr.join(",");
    	});
    	$(".filter_input").each(function(){
    		$(this).find("input").each(function(){
    			model[$(this).attr("filter_name")]=$(this).val();
    		})
    	});
    	console.log(model);
    	
    	model.currentPageNo=page||1;
    	queryLegalInstrumentList(model);
    }
	
	var Binders={
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
			return this;
		}
	} 

    var monitorEventController=function(){
    	this.filter_model={};
        this.load_page();
        this.event_bind();
    }
    monitorEventController.prototype={
        load_page:function(){
        	this.filter_model= {
        			"companyName" : "",
        			"instrumentTypes" : "",
        			"trialClasses" : "",
        			"litigationTypes" :"",
        			"relatedPositions" : "",
        			"beginDate" : "",
        			"endDate" : "",
        			"minAmountCount" : "",
        			"maxAmountCount" : "",
        			"currentPageNo": parseInt(1),
        			"pageSize": parseInt(6)
        	}
			Binders.bind_overlap_event_content_scroll();
        },
        event_bind:function(){
            event_group();
            event_filter(this.filter_model);
        },
        load_data:function(page){
        	bind_model(this.filter_model,page);
        },
        set_current_page:function(pageNo){
        	this.filter_model.currentPageNo=pageNo;
        }
        
    };
    monitorEventController.prototype.constructor = monitorEventController;
	return monitorEventController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

