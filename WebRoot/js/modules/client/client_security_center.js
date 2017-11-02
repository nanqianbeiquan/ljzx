//倒计时计数
var count = 60;
var timer;

var userConfigController=function(){
    var event_email=function(overlaps){
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
            $(".gray img").each(function(){
            	$(this).attr("src",$(this).attr("src_path"));
            })
            $(".model_list .selected").removeClass('selected').addClass("gray");
            $(this).addClass('selected').removeClass("gray");
            
            load_gray_pic();
            $(".config_content .config_content_mode").addClass("hidden");
            $(".config_content .config_mode_"+$(this).attr("index")).removeClass("hidden");
            lap.close();
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
        
        $("#email").bind("input propertychange",function(){
            var value=$(this).val();
            if(value.length > 0){
                $("#new_email_code").removeClass("btn_3").addClass("btn");
                
                document.getElementById("new_email_code").onclick = function(){
                	sendEmailVerificationCode();
            	}
            }else{
                $("#sendEmailVerificationCode").removeClass("btn").addClass("btn_3");
                
                document.getElementById("new_email_code").onclick = function(){
            		
            	}
            }
        });
    }
    var load_gray_pic=function(){
    	
    	$(".gray img").each(function(){
    		$(this).attr("src_path",$(this).attr("src"));
    		$(this).attr("src",_PAGE_MODEL.gray(this));
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
        	setTimeout(function(){
        		load_gray_pic();
        	},300);
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

//修改密码
function updatePassword(){
	
	var oldPassword = $("#oldPassword").val().trim();
	var newPassword = $("#newPassword").val().trim();
	var confirmPassword = $("#confirmPassword").val().trim();
	
	if(oldPassword == ""){
		toastr.warning("请输入旧密码！");
		return;
	}
	
	if(newPassword == ""){
		toastr.warning("请输入新密码！");
		return;
	}
	
	if(confirmPassword == ""){
		toastr.warning("请输入确认密码！");
		return;
	}
	
	if(newPassword != confirmPassword){
		toastr.warning("确认密码与新密码不一致！");
		return;
	}
	
	if(oldPassword == newPassword){
		toastr.warning("新密码不能与旧密码相同！");
		return;
	}
	
   var testPassResult = testPass(newPassword);
    if(testPassResult.flag==false){
    	toastr.warning(testPassResult.msg);
    	return;
    }
	var parameter = {
			"oldPassword" : oldPassword,
			"newPassword" : newPassword
	}
	applyAjax(ctx + "/clientAccount/updateClientAccountPassword",parameter,function(result){
		if(result.resultCode == '0'){
			toastr.success(result.resultMsg+" 2秒后跳转到登录页面。");
            var time = setTimeout(function () {
                clearTimeout(time);
                window.location = ctx + "/login";
            }, 2000);
		}else{
			toastr.error(result.resultMsg);
		}
	});
}

//修改手机号
function updateCellPhone(){
	var cellPhone = $("#cellPhone").val().trim();
	var verificationCode = $("#verificationCode").val().trim();
	
	if(cellPhone == ""){
		toastr.warning("请输入新手机号！");
		return;
	}
	
	if(!validateCelephone($("#cellPhone").val())){
		toastr.warning("新手机号格式有误！");
		return;
	}
	
	if(verificationCode == ""){
		toastr.warning("请输入验证码！");
		return;
	}
	
	var parameter = {
			"cellPhone" : cellPhone,
			"verificationCode" : verificationCode
	}
	
	$.ajax({
		url: ctx + "/clientAccount/updateClientAccountMobilePhone",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			if(result.resultCode == '0'){
				toastr.success("手机号修改成功！");
				
				$("#cellPhone").val("");
				$("#verificationCode").val("");
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

//发送验证码
function sendVerificationCode(){
	var cellPhone = $("#cellPhone").val().trim();
	
	if(cellPhone == ""){
		toastr.warning("请输入新手机号！");
		return;
	}
	
	if(!validateCelephone(cellPhone)){
		toastr.warning("新手机号格式有误！");
		return;
	}
	
	//去掉点击事件
	$("#new_phone_code").removeClass("btn").addClass("btn_3");
	
	$("#new_phone_code").text("重新发送(60)");
	
	document.getElementById("new_phone_code").onclick = function(){
		
	}
	
	var parameter = {
			"cellPhone" : cellPhone
	}
	
	$.ajax({
		url: ctx + "/auth/sendVerificationCodeByMobilePhone",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			if(result.resultCode == '0'){
            	count = 60;
            	
            	timer = window.setInterval("verificationCodeCountdown()",1000);
				
				toastr.success(result.resultMsg);
			}else{
				toastr.error(result.resultMsg);
			}
		},
		error: function(){
			toastr.error('网络异常！');
			
			//添加点击事件
			$("#new_phone_code").text("重新发送");
			
			$("#new_phone_code").removeClass("btn_3").addClass("btn");
			
	    	document.getElementById("new_phone_code").onclick = function(){
	    		sendVerificationCode();
	    	}
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

//动态码倒计时
function verificationCodeCountdown(){
	--count;
	
	if(count < 1){
		window.clearInterval(timer);
		
		//添加点击事件
		$("#new_phone_code").text("重新发送");
		
		$("#new_phone_code").removeClass("btn_3").addClass("btn");
		
    	document.getElementById("new_phone_code").onclick = function(){
    		sendVerificationCode();
    	}
	}else{
		$("#new_phone_code").text("重新发送(" + count + ")");
	}
}

//修改客户账号邮箱
function updateClientAccountEmail(){
	var email = $("#email").val().trim();
	var emailVerificationCode = $("#emailVerificationCode").val().trim();
	
	if(email == ""){
		toastr.warning("请输入新邮件地址！");
		return;
	}
	
	if(!validateEmail(email)){
		toastr.warning("邮件地址格式有误！");
		return;
	}
	
	if(emailVerificationCode == ""){
		toastr.warning("请输入验证码！");
		return;
	}
	
	var parameter = {
			"email" : email,
			"emailVerificationCode" : emailVerificationCode
	}
	
	$.ajax({
		url: ctx + "/clientAccount/updateClientAccountEmail",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			if(result.resultCode == '0'){
				toastr.success("邮件地址修改成功！");
				
				$("#email").val("");
				$("#emailVerificationCode").val("");
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
	var email = $("#email").val().trim();
	
	if(email == ""){
		toastr.warning("请输入邮件地址！");
		return;
	}
	
	if(!validateEmail(email)){
		toastr.warning("邮件地址格式有误！");
		return;
	}
	
	//去掉点击事件
	$("#new_email_code").removeClass("btn").addClass("btn_3");
	
	$("#new_email_code").text("重新发送(60)");
	
	document.getElementById("new_email_code").onclick = function(){
		
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
				
				toastr.success("验证码已发送至你的邮箱，请查收！");
			}else{
				$("#new_email_code").removeClass("btn_3").addClass("btn");
				
				document.getElementById("new_email_code").onclick = function(){
		    		sendEmailVerificationCode();
		    	}
				
				toastr.warning(result.resultMsg);
			}
		},
		error: function(){
			toastr.error('网络异常！');
			
			//添加点击事件
			$("#new_email_code").text("重新发送");
			
			$("#new_email_code").removeClass("btn_3").addClass("btn");
			
	    	document.getElementById("new_email_code").onclick = function(){
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
		$("#new_email_code").text("重新发送");
		
		$("#new_email_code").removeClass("btn_3").addClass("btn");
		
    	document.getElementById("new_email_code").onclick = function(){
    		sendEmailVerificationCode();
    	}
	}else{
		$("#new_email_code").text("重新发送(" + count + ")");
	}
}
