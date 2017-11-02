var controller;
$(function(){
     controller = new personGroupController();
     //更新导航菜单
     updateNavMenu(3,"关系个人详情",window.location.search);
     moduleSelected("30");//初始化加载 开庭公告
})

var personGroupController=function(){
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
            var top=$(_this).position().top+$(".mark_selected img").height();
            $(".mark_selected").animate({top:top},200,function(){
                click_nav=false;
            });
            
            var type=$(_this).attr("type");
            console.log("type="+type);
            moduleSelected(type);
            
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
        
    }
    var personGroupController=function(){
        this.load_page();
        this.event_bind();
    }
    personGroupController.prototype={
        load_page:function(){
            
        },
        event_bind:function(){
            event_nav();
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
function moduleSelected(eventSubType){
	var param = {
			"name":$("#name").html(),
			"idNumber":$("#idNumber").val(),
			"city":$("#city").val(),
			"province":$("#province").val(),
			"eventSubType":eventSubType,
			"relPersonRiskId":$("#relPersonRiskId").val()
	}
	var myUrl = ctx +"/monitorComRisk/getPersonEvents.do";
	$.ajax({
    	url: myUrl,
    	type: "POST",
    	data: param,
    	dataType: "html",
    	success: function(data){
    		console.log("getPersonEvents call back:data"+data);
            console.log(data);
            $("#selectedContent").html(data);
    	},
    	error: function(){},
    	complete: function(request, textStatus){
    		if(request){if(request.status==0){toastr.error('网络异常！');}else if(request.status==500){toastr.error('站点异常！');}else{if(!textStatus||textStatus!="success"){toastr.error('站点异常！');}}}
    	}
    });
}
