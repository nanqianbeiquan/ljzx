//倒计时计数
var count = 60;
var timer;

var loginController=function(){
    var event_module=function(){
        $(".login_module .module").click(function(){
            if($(this).hasClass("selected")){
                return;
            }
            $(".login_module .selected").removeClass("selected");
            $(".input_values .values_block").removeClass("hidden").addClass("hidden");
            $(this).addClass("selected");
            $("."+$(this).attr("value")+"_input_values").removeClass("hidden");
            
            var loginModule = $("#loginModule").val();
            
            if("1" == loginModule){
            	$("#loginModule").val("2");
            	$(".remember_password").addClass("hidden");
            	$(".remember_password_blank").removeClass("hidden");
            }else if("2" == loginModule){
            	$("#loginModule").val("1");
            	$(".remember_password").removeClass("hidden");
            	$(".remember_password_blank").addClass("hidden");
            }
            
            //清除错误提示
            $("#message").text('');
        	$("#memberLoginMessage").addClass("hidden");
            
        });
        
        $("#cellPhone").bind("input propertychange",function(){
            var value=$(this).val();
            
            if(value.length==11){
                $("#sendDynamicCodeButton").addClass("send_allow");
                
                document.getElementById("sendDynamicCodeButton").onclick = function(){
                	sendDynamicCode();
            	}
            }else{
                $("#sendDynamicCodeButton").removeClass("send_allow");
                
                document.getElementById("sendDynamicCodeButton").onclick = function(){
            		
            	}
            }
        });
        
        $("#cellPhone1").bind("input propertychange",function(){
            var value=$(this).val();
            
            if(value.length>0){
            	$("#sendVerificationCodeButton").text("重新发送");
                $("#sendVerificationCodeButton").addClass("send_allow");
                
                document.getElementById("sendVerificationCodeButton").onclick = function(){
                	sendVerificationCode();
            	}
            }else{
                $("#sendVerificationCodeButton").removeClass("send_allow");
                
                document.getElementById("sendVerificationCodeButton").onclick = function(){
                	
            	}
            }
        });
        
        $("#account_foget").click(function(){
            $("#member_login_block").addClass("hidden");
            $("#foget_password_block").removeClass("hidden");
            
            //清除错误提示
            $("#message").text('');
        	$("#memberLoginMessage").addClass("hidden");
        })
        $("#back_login").click(function(){
            $("#member_login_block").removeClass("hidden");
            $("#foget_password_block").addClass("hidden");
            
            //清除错误提示
            $("#message1").text('');
        	$("#fogetPasswordMessage").addClass("hidden");
        })
        $("#accountName").focus(function(){
        	$("#message").text('');
        	$("#memberLoginMessage").addClass("hidden");
        });
        $("#pwd").focus(function(){
        	console.log("pwd focus...");
        	$("#message").empty();
        	$("#memberLoginMessage").addClass("hidden");
        });
       /* $("#pwd").bind("keypress",function(event){
        	$("#message").empty();
        	$("#memberLoginMessage").addClass("hidden");
        	console.log("pwd key press");
        	var flag = detectCapsLock(event);
        	console.log("flag="+flag);
        	if(flag){
        		$("#message").text('大写锁定键被按下，请注意大小写！');
    	    	$("#memberLoginMessage").removeClass("hidden");
        	}
        	
        });
        $("#newPwd").bind("keypress",function(event){
        	$("#message1").empty();
        	$("#fogetPasswordMessage").addClass("hidden");
        	console.log("pwd key press");
        	var flag = detectCapsLock(event);
        	console.log("flag="+flag);
        	if(flag){
        		$("#message1").text('大写锁定键被按下，请注意大小写！');
    	    	$("#fogetPasswordMessage").removeClass("hidden");
        	}
        	
        });*/
    }
    var loginController=function(){
        this.load();
        this.bind();
    }
    loginController.prototype={
        load:function(){
        
        },
        bind:function(){
            event_module();
        }
    };
    loginController.prototype.constructor = loginController;
	return loginController;
}();

//登录
function doLogin(){
	$("#message").text('');
	$("#memberLoginMessage").addClass("hidden");
	var url ;
	var parameter = "";
	var loginModule = $("#loginModule").val();
	
	if("1" == loginModule){//账户名/手机号登录
		var accountName = $("#accountName").val().trim();
		var pwd = $("#pwd").val().trim();
		
	    if(accountName == ""){
	    	$("#message").text('请输入用户名/手机号！');
	    	$("#memberLoginMessage").removeClass("hidden");
	    	return;
	    }
	    
	    if(pwd == ""){
	    	$("#message").text('请输入密码！');
	    	$("#memberLoginMessage").removeClass("hidden");
        	return;
	    }
	    
	    parameter = {
			"accountName" : accountName,
			"pwd" : pwd
    	}
	    
	    //保存用户名密码
	    var rememberPasswordFlag = $("input[name='rememberPasswordFlag']").is(':checked');
	    
	    if(rememberPasswordFlag){
	    	setCookie("ljzx_accountName",accountName,1000);
	    	setCookie("ljzx_pwd",pwd,1000);
	    }else{
	    	cleanCookie("ljzx_accountName");
	    	cleanCookie("ljzx_pwd");
	    }
	    
	    url = ctx + "/doLogin";
	
	}else{//动态密码登录
		var cellPhone = $("#cellPhone").val().trim();
		
		if(cellPhone == ""){
			$("#message").text('请输入手机号！');
			$("#memberLoginMessage").removeClass("hidden");
        	return;
        }
		
        if(!validateCelephone(cellPhone)){
        	$("#message").text('手机号格式有误！');
        	$("#memberLoginMessage").removeClass("hidden");
        	return;
        }
	
        //验证码
        var dyPwd = $("#dyPwd").val().trim();
        
        if(dyPwd == ""){
        	$("#message").text('动态密码不能为空！');
        	$("#memberLoginMessage").removeClass("hidden");
        	return;
        }
        
        var pattern = /^\d{4}$/; 
        if(!pattern.test(dyPwd)){
        	$("#message").text('动态密码输入错误，必须是4位数字！');
        	$("#memberLoginMessage").removeClass("hidden");
        	return;
        }
        
        parameter = {
			"cellPhone" : cellPhone,
			"dyPwd" : dyPwd
    	}
        
        url = ctx + "/doLoginByDynamicPassword";
	}

    $.ajax({
    	url: url,
    	type: "POST",
    	data: parameter,
    	dataType: "json",
    	success: function(result){
    		window.clearInterval(timer);
    		
    		//添加点击事件
    		$("#sendDynamicCodeButton").text("重新发送");
    		
    		$("#sendDynamicCodeButton").addClass("send_allow");
    		
        	document.getElementById("sendDynamicCodeButton").onclick = function(){
            	sendDynamicCode();
        	}
            
    		if(result.resultCode == "0"){
    			location.href = ctx + result.resultData.url;
    		}else{
    			$("#message").text(result.resultMsg);
    			$("#memberLoginMessage").removeClass("hidden");
    		}
    	},
    	error: function(){
			
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
    });
	
}

//发送动态码
function sendDynamicCode(){
	var cellPhone = $("#cellPhone").val().trim();
	
    if(cellPhone == ""){
    	$("#message").text('请输入手机号！');
    	$("#memberLoginMessage").removeClass("hidden");
    	return;
    }
    
    if(!validateCelephone(cellPhone)){
    	$("#message").text('手机号格式有误！');
    	$("#memberLoginMessage").removeClass("hidden");
        return;
    }
    
    //去掉点击事件
	$("#sendDynamicCodeButton").removeClass("send_allow");
	
	$("#sendDynamicCodeButton").text("重新发送(60)");
	
	document.getElementById("sendDynamicCodeButton").onclick = function(){
		
	}
    
	$.ajax({
		url: ctx + "/auth/sendDynamicCode",
		type: "POST",
		data: {"cellPhone":cellPhone},
		dataType: "json",
		success: function(result){
            if(result.resultCode == "0"){
            	$("#memberLoginMessage").addClass("hidden");
            	
            	count = 60;
            	
            	timer = window.setInterval("dynamicCodeCountdown()",1000);
            }else{
            	$("#message").text(result.resultMsg);
            	$("#memberLoginMessage").removeClass("hidden");
            }
		},
		error: function(){
			//添加点击事件
			$("#sendDynamicCodeButton").text("重新发送");
			
			$("#sendDynamicCodeButton").addClass("send_allow");
			
	    	document.getElementById("sendDynamicCodeButton").onclick = function(){
	        	sendDynamicCode();
	    	}
		},
		complete: function(request, textStatus){
			
		}
	});
}

//动态码倒计时
function dynamicCodeCountdown(){
	--count;
	
	if(count < 1){
		window.clearInterval(timer);
		
		//添加点击事件
		$("#sendDynamicCodeButton").text("重新发送");
		
		$("#sendDynamicCodeButton").addClass("send_allow");
		
    	document.getElementById("sendDynamicCodeButton").onclick = function(){
        	sendDynamicCode();
    	}
	}else{
		$("#sendDynamicCodeButton").text("重新发送(" + count + ")");
	}
}

//设置新密码
function setNewPassword(){
	var cellPhone = $("#cellPhone1").val().trim();
	var verificationCode = $("#verificationCode").val().trim();
	var newPwd = $("#newPwd").val().trim();
	
	if(cellPhone == ""){
		$("#message1").text('请输入手机号！');
		$("#fogetPasswordMessage").removeClass("hidden");
    	return;
    }
	
//    if(!validateCelephone(cellPhone)){
//    	$("#message1").text('手机号格式有误！');
//    	$("#fogetPasswordMessage").removeClass("hidden");
//    	return;
//    }
    
    if(verificationCode == ""){
    	$("#message1").text('验证码不能为空！');
    	$("#fogetPasswordMessage").removeClass("hidden");
    	return;
    }
    
    var pattern = /^\d{4}$/; 
    if(!pattern.test(verificationCode)){
    	$("#message1").text('验证码错误！');
    	$("#fogetPasswordMessage").removeClass("hidden");
    	return;
    }
    
    if(newPwd == ""){
    	$("#message1").text('请输入新密码！');
    	$("#fogetPasswordMessage").removeClass("hidden");
    	return;
    }
    var testPassResult = testPass(newPwd);
    if(testPassResult.flag==false){
    	$("#message1").text("密码不符合规范！");
    	$("#fogetPasswordMessage").removeClass("hidden");
    	return;
    }
    
    var parameter = {
			"receiver" : cellPhone,
			"verificationCode" : verificationCode,
			"newPwd" : newPwd
	}
    
    $.ajax({
    	url: ctx + "/login/setNewPassword",
    	type: "POST",
    	data: parameter,
    	dataType: "json",
    	success: function(result){
    		window.clearInterval(timer);
    		
    		//添加点击事件
    		$("#sendVerificationCodeButton").text("重新发送");
    		
    		$("#sendVerificationCodeButton").addClass("send_allow");
    		
        	document.getElementById("sendVerificationCodeButton").onclick = function(){
        		sendVerificationCode();
        	}
    		
    		if(result.resultCode == "0"){
    			location.href = ctx + "/page/modules/login/setPasswordSuccess.jsp?action='找回密码'";
    		}else{
    			$("#message1").text(result.resultMsg);
    			$("#fogetPasswordMessage").removeClass("hidden");
    		}
    	},
    	error: function(){
			
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
    });
}

//发送验证码
function sendVerificationCode(){
	var cellPhone = $("#cellPhone1").val().trim();
	
    if(cellPhone == ""){
    	$("#message1").text('请输入手机号！');
    	$("#fogetPasswordMessage").removeClass("hidden");
    	return;
    }
    
//    if(!validateCelephone(cellPhone)){
//    	$("#message1").text('手机号格式有误！');
//    	$("#fogetPasswordMessage").removeClass("hidden");
//        return;
//    }
    
    //去掉点击事件
	$("#sendVerificationCodeButton").removeClass("send_allow");
	
	$("#sendVerificationCodeButton").text("重新发送(60)");
	
	document.getElementById("sendVerificationCodeButton").onclick = function(){
		
	}
    
	$.ajax({
		url: ctx + "/login/sendAuthCode",
		type: "POST",
		data: {"receiver":cellPhone},
		dataType: "json",
		success: function(result){
            if(result.resultCode == "0"){
            	toastr.success(result.resultMsg);
            	$("#fogetPasswordMessage").addClass("hidden");
            	count = 60;
            	timer = window.setInterval("verificationCodeCountdown()",1000);
            }else{
            	$("#message1").text(result.resultMsg);
            	
            	$("#fogetPasswordMessage").removeClass("hidden");
            	
            	$("#sendVerificationCodeButton").text("重新发送");
            }
		},
		error: function(){
			//添加点击事件
			$("#sendVerificationCodeButton").text("重新发送");
			
			$("#sendVerificationCodeButton").addClass("send_allow");
			
	    	document.getElementById("sendVerificationCodeButton").onclick = function(){
	    		sendVerificationCode();
	    	}
		},
		complete: function(request, textStatus){
			
		}
	});
}

//动态码倒计时
function verificationCodeCountdown(){
	--count;
	
	if(count < 1){
		window.clearInterval(timer);
		
		//添加点击事件
		$("#sendVerificationCodeButton").text("重新发送");
		
		$("#sendVerificationCodeButton").addClass("send_allow");
		
    	document.getElementById("sendVerificationCodeButton").onclick = function(){
    		sendVerificationCode();
    	}
	}else{
		$("#sendVerificationCodeButton").text("重新发送(" + count + ")");
	}
}

//从
function getNameAndPwdFromCookie(){
	var accountName = getCookie("ljzx_accountName");
	var pwd = getCookie("ljzx_pwd");
	
	if(accountName!=null && accountName!="" && pwd!=null && pwd!=""){
		$("#pwd").attr('type','password');
		
		$("#accountName").val(accountName);
		$("#pwd").val(pwd);
		
		$("[name='rememberPasswordFlag']").attr("checked",'true');
	}
}
//密码长度6位以上且须包含大写、小写、数字、特殊符号中的任意3种
function testPass(str){  
    var rC = {  
        lW:'[a-z]',  
        uW:'[A-Z]',  
        nW:'[0-9]',  
        sW:'[\\u0020-\\u002F\\u003A-\\u0040\\u005B-\\u0060\\u007B-\\u007E]'  
    };  
    function Reg(str, rStr){  
        var reg = new RegExp(rStr);  
        if(reg.test(str)) return true;  
        else return false;  
    }  
    if(str.length < 6){  
        return {"flag":false,"msg":"您的密码长度太短"};  
    }else{  
        var tR = {  
            l:Reg(str, rC.lW),  
            u:Reg(str, rC.uW),  
            n:Reg(str, rC.nW),  
            s:Reg(str, rC.sW)  
        };  
        if((tR.l && tR.u && tR.n) || (tR.l && tR.u && tR.s) || (tR.s && tR.u && tR.n) || (tR.s && tR.l && tR.n)){  
            return {"flag":true,"msg":"密码符合要求"};  
        }else{  
            return {"flag":false,"msg":"您的密码必须含有“大写字母”、“小写字母”、“数字”、“符号”中的任意三种"};  
        }  
    }  
}
