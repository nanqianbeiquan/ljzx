var controller;
$(function(){
     controller = new userQuestioinsController();
		var deep = $("#deep").val();
		updateNavMenu(parseInt(deep),"常见问题",window.location.search);
})

var userQuestioinsController=function(){
    var event_menu=function(){
        $(".menu_1 .menu_title").click(function(){
            var _this=$(this).parent(".menu_1");
            if($(_this).hasClass("selected_menu_1")){
                $(_this).removeClass("selected_menu_1");
                $(_this).find(".menu_content").stop().animate({"height":0});
            }else{
                $(_this).addClass("selected_menu_1");
                var height=0;
                $(_this).find(".menu_content .menu_2").each(function(){
                    height+=$(_this).outerHeight(true)-6;
                });
                $(_this).find(".menu_content").stop().animate({"height":height});
            }
        })
        $(".menu_2 .menu_title").click(function(){
            var index=$(this).attr("index");
            $(".questions").addClass("hidden");
            $("#question_"+index).removeClass("hidden");
            $(".current_question").removeClass("current_question");
            $(this).addClass("current_question");
        })
    }

    var userQuestioinsController=function(){
        this.load_page();
        this.event_bind();
    }
    userQuestioinsController.prototype={
        load_page:function(){

        },
        event_bind:function(){
            event_menu();
        }
    };
    userQuestioinsController.prototype.constructor = userQuestioinsController;
	return userQuestioinsController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

