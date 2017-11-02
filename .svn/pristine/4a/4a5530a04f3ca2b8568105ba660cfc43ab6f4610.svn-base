
var userFeedbackController=function(){
	var event_feedback=function(){
        $('.idea_text').bind('input propertychange', function() {
            var max=$(".max_length").text();
            var value=$(this).val();
            if(value.length>max){
                value=value.substr(0, max);
                $(this).val(value);
            }
            $('.input_length').html(value.length);
        });
    }
	
    var userFeedbackController=function(){
        this.load_page();
        this.event_bind();
    }
    userFeedbackController.prototype={
        load_page:function(){
            
        },
        event_bind:function(){
            event_feedback();
        }
    };
    userFeedbackController.prototype.constructor = userFeedbackController;
	return userFeedbackController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

//添加反馈记录
function addFeedBack(){
	
	var feedbackContent = $("#feedbackContent").val();
	
	if(feedbackContent == ""){
		toastr.info("请输入意见！");
	}else{
		var parameter = {
			"feedbackContent": feedbackContent
		}
		
		$.ajax({
			url: ctx + "/feedBack/addFeedBack",
			type: "POST",
			data: parameter,
			dataType: "json",
			success: function(result){
				if(result.resultCode == "0"){
					$("#feedbackContent").val("");
					
					//刷新历史反馈
					queryFeedBackList();
					
		            toastr.success(result.resultMsg);
				}else{
					toastr.error(result.resultMsg);
				}
			},
			error: function(){
				toastr.error('网络异常！');
			},
			complete: function(XMLHttpRequest, textStatus){
				
			}
		});
	}
	
}

//获取意见反馈列表
function queryFeedBackList(){
	
	$.ajax({
		url: ctx + "/feedBack/queryFeedBackList",
		type: "POST",
		dataType: "json",
		success: function(result){
			$("#feedBackList").html("");//清除之前的数据
        	
        	$("#feedBackList").append(fomatFeedBackList(result));//添加新的数据
        	
        	bindFeedBackDetailEvent();//绑定查看意见反馈的事件
		},
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

//格式化动态监控分组列表数据
function fomatFeedBackList(results){
	
	var content = '';
	
	for(var key in results){
		content += '<div class="idea"><div class="l_option"><div class="inline_div"><div class="inline_div option_value">详情</div>'
				 + '<div class="inline_div"><img class="option_img up_img hidden" src="' + ctx + '/images/modules/info/up.png"/>'
				 + '<img class="option_img down_img" src="' + ctx + '/images/modules/info/down.png"/></div></div></div><div class="r_content line_txt">'
				 + results[key].feedbackContent + '</div>';
		
		if(results[key].replyStatus == '1'){
			content += '<div class="lengjing_reply hidden"><div class="reply_title">风声回复：</div><div class="reply_content_arr"><div class="content_arr"></div></div><div class="reply_content">'
					 + results[key].replyContent + '</div></div>';
		}
		
		content += '</div>';
	}
	
	return content;
}

//绑定查看意见反馈的事件
function bindFeedBackDetailEvent(){
	$(".idea .l_option").click(function(){
    	var value=$(this).find(".option_value").text();
    	if(value=="详情"){
            $(this).parents(".idea").find(".r_content").removeClass("line_txt");
            if($(this).parents(".idea").find(".lengjing_reply").length>0){
                $(this).parents(".idea").find(".lengjing_reply").removeClass("hidden");
            }
            $(this).find(".option_value").text("收起");
            $(this).find(".up_img").removeClass("hidden");
            $(this).find(".down_img").addClass("hidden");
        }else{
            $(this).parents(".idea").find(".r_content").addClass("line_txt");
            if($(this).parents(".idea").find(".lengjing_reply").length>0){
                $(this).parents(".idea").find(".lengjing_reply").addClass("hidden");
            }
            $(this).find(".option_value").text("详情");
            $(this).find(".up_img"). addClass("hidden");
            $(this).find(".down_img").removeClass("hidden");
        }
    })
}
