$(function(){
    var controller=new aboutUsController();
  //  console.log("init about us...");
	var deep = $("#deep").val();
	 updateNavMenu(parseInt(deep),"关于我们",window.location.search);
})

var aboutUsController=function(){
    var event_more=function(){
        $("#more_paragraph").click(function(){
            var value=$(this).text();
            if(value=="查看更多"){
                $(this).text("收起更多");
                $(".content_content").animate({height:504});
            }else{
                $(this).text("查看更多");
                $(".content_content").animate({height:220});
            }
        })
        $("#more_history").click(function(){
            var value=$(this).text();
            if(value=="查看更多"){
                $(this).text("收起更多");
                $(".history_content").animate({height:1216});
            }else{
                $(this).text("查看更多");
                $(".history_content").animate({height:324});
            }
        })
    }
    var aboutUsController=function(){
        this.load();
        this.bind();
    }
    aboutUsController.prototype={
        load:function(){
            
        },
        bind:function(){
           event_more();
        }
    };
    aboutUsController.prototype.constructor = aboutUsController;
	return aboutUsController;
}();