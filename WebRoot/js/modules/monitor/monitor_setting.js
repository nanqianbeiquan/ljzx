//倒计时计数
var count = 60;
var timer;

var userConfigController=function(){
    var event_email=function(overlaps){
    	var lap=new overlap({
            mask:{
                show:false
            },
            scroller:{
                lock:false
            },
            position:{
                type:"absolute",
                x_pos:$("#email_btn").offset().left+180,
                y_pos:$("#email_btn").offset().top-100
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
                    width:"292px",
                    height:"260px",
                    border:"16px solid rgba(0,0,0,0.15)"
                }
            }});
    	
            var email_filled=false;
            var count = 0;
            
            $("#email_btn").click(function(){
            	count = parseInt($(".email_list .checkor").length);
            	
            	if(count <= 5){
            		$("#newEmail").val("");
            		$("#emailVerificationCode").val("");
            		
            		$("#sendEmailVerificationCode").addClass("btn_3");
                	
                	$("#sendEmailVerificationCode").text("发送验证码");
                	
                	document.getElementById("sendEmailVerificationCode").onclick = function(){
                		
                	}
            		
            		lap.reset({
                        position:{
    		                type:"absolute",
    		                x_pos:$("#email_btn").offset().left+450,
    		                y_pos:$("#email_btn").offset().top-100
                        }
                    });
                    email_filled=false;
                    lap.show(".email_confirm");
            	}else{
            		toastr.warning("最多添加五个邮箱！");
            	}
            });
            
            $(".email_confirm_btn").click(function(){
                if(count <= 5){
                	var email = $("#newEmail").val().trim();
                	var emailVerificationCode = $("#emailVerificationCode").val().trim();
                	
                	if(email == ""){
                		toastr.warning("请输入邮件地址！");
                		return;
                	}
                	
                	if(!validateEmail(email)){
                		toastr.warning("邮件地址格式有误！");
                		return;
                	}
                	
                	if("" == emailVerificationCode){
                		toastr.warning("请输入验证码！");
                		return;
                	}
                	
                	var parameter = {
                			"email" : email,
                			"emailVerificationCode" : emailVerificationCode
                	}
                	
                	$.ajax({
                		url: ctx + "/monitorConfigure/addMonitorConfigureEmail",
                		type: "POST",
                		data: parameter,
                		dataType: "json",
                		success: function(result){
                			if(result.resultCode == '0'){
                				if(email.length>0 && !email_filled){
                                    var value=$(".email_editor .value").text();
                                    var mark=email;
                                    var new_email=$(".email_list .default_email_checkor").clone().removeClass("hidden").removeClass("default_email_checkor").addClass("checkor");
                                    $(new_email).find(".check_option").attr("checked",false);
                                    $(new_email).find(".value").text(value);
                                    $(new_email).find(".check_mark").text(mark);
                                    $(".email_editor").before(new_email);
                                    $(".email_editor .value").text("邮箱"+count);
                                    $(".email_editor input").val("");
                                    email_filled=true;
                                }
                            	
                                lap.close();
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
                }else{
                	toastr.warning("最多添加五个邮箱！");
                }
            })
        
        $(".mode").click(function(){
            if($(this).hasClass("selected")){
                return;
            }
            // $(".gray img").each(function(){
            // 	$(this).attr("src",$(this).attr("src_path"));
            // })
            $(".model_list .selected").removeClass('selected').addClass("gray");
            $(this).addClass('selected').removeClass("gray");
            $(this).find(".mode_img").removeClass("hidden");
            $(this).find(".gray_img").addClass("hidden");
            load_gray_pic();
            $(".config_content .config_content_mode").addClass("hidden");
            $(".config_content .config_mode_"+$(this).attr("index")).removeClass("hidden");
            lap.close();
        });
        
        $(".row_value .selector").click(function(){
            var parent = $(this).parent().parent(".row_value");
            
            $(parent).find(".selected").removeClass("selected");
            
            $(this).addClass("selected");
            
            var industry = $(this).parent().children(".value").text();
            
            if(industry == "金融"){
            	$("#industry").val("0");
            }else if(industry == "投资"){
            	$("#industry").val("1");
            }else if(industry == "法律"){
            	$("#industry").val("2");
            }else if(industry == "其他"){
            	$("#industry").val("3");
            }
        });
        
        $(".monitorCycle .selector").click(function(){
        	
        	if($(this).hasClass("selected")){
                return;
            }
        	
            var parent=$(this).parents("td");
            
            $(parent).find(".selected").removeClass("selected");
            
            $(this).addClass("selected");
            
            var monitorCycle = $(this).parent().children(".value").text();
            
            if(monitorCycle == "每日"){
            	$("#monitorCycle").val("1");
            }else if(monitorCycle == "每周"){
            	$("#monitorCycle").val("2");
            }else if(monitorCycle == "每月"){
            	$("#monitorCycle").val("3");
            }
        });
        
        $(".attentionEventClass .selector").click(function(){
        	
        	if($(this).hasClass("selected")){
                return;
            }
        	
            var parent = $(this).parents("td");
            
            $(parent).find(".selected").removeClass("selected");
            
            $(this).addClass("selected");
            
            var flag = $(this).parent().children(".value").text();
            
            if(flag == "关注"){
            	$(parent).find("input").val("1");
            }else if(flag == "不关注"){
            	$(parent).find("input").val("0");
            }
        	
        });
        
        $("#deleteGroupWindow #cancel_message").click(function(){
        	overlaps.message_lap.close();
        })
        
        $("#deleteGroupWindow #ok_message").click(function(){
        	var parameter = {
					"email" : $("#delete_email_value").val()
			}
        	$.ajax({
				url: ctx + "/monitorConfigure/deleteMonitorConfigureEmail",
				type: "POST",
				data: parameter,
				dataType: "json",
				success: function(result){
					if(result.resultCode == "0"){
						$(".email_list .delete").remove();
					}else{
						toastr.error(result.resultMsg);
						$(".email_list .delete").removeClass("delete");
					}
				},
				error: function(){
					toastr.error('网络异常！');
				},
				complete: function(XMLHttpRequest, textStatus){
					overlaps.message_lap.close();
				}
        	});
        })
    }

    var event_password=function(){
        $("#cellPhone").bind("input propertychange",function(){
            var value=$(this).val();
            if(value.length==11){
                $("#new_phone_code").removeClass("btn_3").addClass("btn");
                
                document.getElementById("new_phone_code").onclick = function(){
                	sendVerificationCode();
            	}
            }else{
                $("#new_phone_code").removeClass("btn").addClass("btn_3");
                
                document.getElementById("new_phone_code").onclick = function(){
                	
            	}
            }
        });
        
        $("#newEmail").bind("input propertychange",function(){
            var value=$(this).val();
            if(value.length > 0){
                $("#sendEmailVerificationCode").removeClass("btn_3").addClass("btn");
                
                document.getElementById("sendEmailVerificationCode").onclick = function(){
                	sendEmailVerificationCode();
            	}
            }else{
                $("#sendEmailVerificationCode").removeClass("btn").addClass("btn_3");
                
                document.getElementById("sendEmailVerificationCode").onclick = function(){
            		
            	}
            }
        });
    }
    var load_gray_pic=function(){
    	
    	$(".gray .mode_img").each(function(){
    		// $(this).attr("src_path",$(this).attr("src"));
			// $(this).attr("src",_PAGE_MODEL.gray(this));
			$(this).addClass("hidden");
		})
		$(".gray .gray_img").each(function(){
    		// $(this).attr("src_path",$(this).attr("src"));
			// $(this).attr("src",_PAGE_MODEL.gray(this));
			$(this).removeClass("hidden");
    	})
    }
    var userConfigController=function(){
    	this.overlap_list={}
        this.load_page();
    	this.init_overlap();
        this.event_bind();
    }
    userConfigController.prototype={
        load_page:function(){
        	load_gray_pic();
        },
        event_bind:function(){
            event_email(this.overlap_list);
            event_password();
        },
        init_overlap:function(){
        	this.overlap_list.message_lap=new overlap({
                mask:{
                 show:true
             },
             scroller:{
                 lock:false
             },
             position:{
                 type:"center"
             },
             close:{
                 show:false,
                 type:"close_2",
                 style:{

                 },
                 callBack:function(){
                     
                 }
             },
             content:{
                 style:{
                     width:"516px",
                     height:"200px",
                     border:"16px solid rgba(0,0,0,0.15)"
                 }
             }});
        }
    };
    userConfigController.prototype.constructor = userConfigController;
	return userConfigController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

//修改动态监控配置信息
function updateMonitorUserConfigure(){
	
	var attentionEventClassArray = $("input[name='attentionEventClass']");
	
	var attentionEventClasses = "";
	
	for(var i=0;i<attentionEventClassArray.length;i++){
		attentionEventClasses += attentionEventClassArray[i].value;
	}
	
	var monitorCycle = $("#monitorCycle").val();
	
	var notifyType = "";
	
	$('input[name="notifyType"]').each(function(){
		if($(this).is(':checked')){
			notifyType += "1";
		}else{
			notifyType += "0";
		}
	});
	
	var emailList = $(".email_list .checkor .check_mark");
	
	var emails = "";
	
	for(var i=0;i<emailList.length;i++){
		emails += emailList[i].textContent + ",";
	}
	
	var parameter = {
			"monitorCycle" : monitorCycle,
			"attentionEventClasses" : attentionEventClasses,
			"notifyType" : notifyType,
			"emails" : emails
	}
	
	$.ajax({
		url: ctx + "/monitorConfigure/updateMonitorUserConfigure",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			if(result.resultCode == '0'){
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

//发送邮箱验证码
function sendEmailVerificationCode(){
	var email = $("#newEmail").val().trim();
	
	if(email == ""){
		toastr.warning("请输入邮件地址！");
		return;
	}
	
	if(!validateEmail(email)){
		toastr.warning("邮件地址格式有误！");
		return;
	}
	
	//去掉点击事件
	$("#sendEmailVerificationCode").removeClass("btn").addClass("btn_3");
	
	document.getElementById("sendEmailVerificationCode").onclick = function(){
		
	}
	
	var parameter = {
			"email" : email
	}
	
	$.ajax({
		url: ctx + "/auth/sendVerificationCodeByEmail",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			if(result.resultCode == '0'){
            	count = 60;
            	
            	timer = window.setInterval("emailVerificationCodeCountdown()",1000);
            	
            	$("#sendEmailVerificationCode").text("重新发送(60)");
				
				toastr.success("验证码已发送至你的邮箱，请查收！");
			}else{
				$("#sendEmailVerificationCode").removeClass("btn_3").addClass("btn");
				
				document.getElementById("sendEmailVerificationCode").onclick = function(){
		    		sendEmailVerificationCode();
		    	}
				
				toastr.warning(result.resultMsg);
			}
		},
		error: function(){
			toastr.error('网络异常！');
			
			//添加点击事件
			$("#sendEmailVerificationCode").text("重新发送");
			
			$("#sendEmailVerificationCode").removeClass("btn_3").addClass("btn");
			
	    	document.getElementById("sendEmailVerificationCode").onclick = function(){
	    		sendEmailVerificationCode();
	    	}
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

function emailVerificationCodeCountdown(){
	--count;
	
	if(count < 1){
		window.clearInterval(timer);
		
		//添加点击事件
		$("#sendEmailVerificationCode").text("重新发送");
		
		$("#sendEmailVerificationCode").removeClass("btn_3").addClass("btn");
		
    	document.getElementById("sendEmailVerificationCode").onclick = function(){
    		sendEmailVerificationCode();
    	}
	}else{
		$("#sendEmailVerificationCode").text("重新发送(" + count + ")");
	}
}

//删除邮箱
function deleteEmail(email){
	controller.overlap_list.message_lap.show("#deleteGroupWindow");
	$("#delete_email_value").val(email);
	/*alertify.set({
		labels : {
			ok   : "确认",
			cancel : "取消"
		},
		delay : 5000,
		buttonReverse : false,
		buttonFocus  : "cancel"
	});
	
	alertify.confirm("确定要邮箱吗?", function(e){
		if(e){
			var parameter = {
					"email" : email
			}
			
			$.ajax({
				url: ctx + "/monitorConfigure/deleteMonitorConfigureEmail",
				type: "POST",
				data: parameter,
				dataType: "json",
				success: function(result){
					if(result.resultCode == "0"){
						$(".email_list .delete").remove();
					}else{
						toastr.error(result.resultMsg);
						$(".email_list .delete").removeClass("delete");
					}
				},
				error: function(){
					toastr.error('网络异常！');
				},
				complete: function(XMLHttpRequest, textStatus){
					
				}
			});
		}else{
			
		}
	});*/
	
}


