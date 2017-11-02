<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/login/login4Web.css?${appVersion }" />
	
	<script type="text/javascript" src="${ctx }/js/validateUtil.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/utils/cookieUtils.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/login/login4Web.js?${appVersion }"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			
			toastr.options = {
			        "closeButton": false, //是否显示关闭按钮
			        "debug": false, //是否使用debug模式
			        "positionClass": "toast-top-center",//弹出窗的位置
			        "showDuration": "300",//显示的动画时间
			        "hideDuration": "1000",//消失的动画时间
			        "timeOut": "2000", //展现时间
			        "extendedTimeOut": "1000",//加长展示时间
			        "showEasing": "swing",//显示时的动画缓冲方式
			        "hideEasing": "linear",//消失时的动画缓冲方式
			        "showMethod": "fadeIn",//显示时的动画方式
			        "hideMethod": "fadeOut" //消失时的动画方式
			};
			
			var controller=new loginController();
		
			$(document).keydown(function(e){
		    	if (!e) {
		    		e = window.event;
		    	}
		      	
		    	if ((e.keyCode || e.which) == 13) {
		      		doLogin();
		      		return false;
		    	}
			});
			
			$("#pwd").focus(function() {
				$("#pwd").attr('type','password');
			});
			
			$("#newPwd").focus(function() {
				$("#newPwd").attr('type','password');
			});
			
		});
	
	</script>
	
</head>
<body class="ljzx_page" onload="getNameAndPwdFromCookie()">
	<div class="body_content_6">
    	<div id="nav_head" class="nav_head white head_shadow">
        <div class="head_back">
            <div class="back_color"></div>
        </div>
        <div class=" head_left">
            <a href="${ctx }/login"><img class="inline-div" src="${ctx }/images/common/logo.png"/></a>
        </div>
	</div>
	
    <div class="" style="width:100%;">
        <div class="body_back_img">
        	<div class="login_block">
            	<div class="inline_div login_input_block login_block_shadow">
                	<div class="input_block">
                	<div id="member_login_block" class="input_value_block ">
                    	<div class="login_title">会员登陆</div>
                    	<div class="login_module">
                        	<div class="inline_div module selected" value="account">用户登录
                            </div><div class="inline_div module" value="phone">手机登录</div>
                            <input type="hidden" id="loginModule" name="loginModule" value="1" />
                        </div>
                        <div class="input_values">
                        	<div id="memberLoginMessage" class="relative_center_block error_message_block hidden">
                                  	<div id="message" class="error_message"></div>
                               </div>
                            <div class="account_input_values values_block ">
                                <div>
                                    <input type="text" id="accountName" name="accountName" placeholder="用户名/手机号" maxlength="20" class="user_account"/>
                                </div>
                                <div>
                                    <input type="text" id="pwd" name="pwd" placeholder="密码" maxlength="20" class="user_password" />
                                </div>
                            </div>
                            <div class=" phone_input_values values_block hidden">
                                <div>
                                    <input type="text" id="cellPhone" name="cellPhone" placeholder="手机号" maxlength="11" class="phone_account"/>
                                </div>
                                <div>
                                    <input type="text" id="dyPwd" name="dyPwd" placeholder="动态码" maxlength="4" class="inline_div phone_password"/><div id="sendDynamicCodeButton" class="inline_div phone_password_sender">发送动态码</div>
                                </div>
                            </div>
                        </div>
                        <div class="remember_password">
                            <input type="checkbox" id="rememberPasswordFlag" name="rememberPasswordFlag" />记住密码
                        </div>
                        <div class="remember_password_blank hidden" style="height:16px;">
                        </div>
                        <div>
                            <div class="btn login_btn" onclick="doLogin();">登录</div>
                        </div>
                        <div class="login_extra_block">
                            <!-- <div class="inline_div user_register">会员注册</div>
                            <div class="inline_div cut_line"></div> -->
                            <div class="inline_div account_foget" id="account_foget">忘记密码?</div>
                        </div>
                        </div>
                        <div id="foget_password_block" class="input_value_block hidden">
	                        <div class="login_title">找回密码</div>
	                        <div class="input_values">
	                        	<div id="fogetPasswordMessage" class="relative_center_block error_message_block hidden">
                                   	<div id="message1" class="error_message"></div>
                                </div>
	                        	<div>
	                            	<input type="text" id="cellPhone1" name="cellPhone1" placeholder="请输入手机号码或邮箱"  class="phone_account"/>
	                            </div>
	                            <div class="input_line">
	                                <input type="text" id="verificationCode" name="verificationCode" placeholder="请输入验证码" maxlength="4" class="inline_div phone_code"/><div id="sendVerificationCodeButton" class="inline_div phone_password_sender">获取验证码</div>
	                            </div>
	                            <div>
	                                <input type="text" id="newPwd" name="newPwd" placeholder="请设置密码" maxlength="20" autocomplete="off" class="user_password"/>
	                            </div>
								<div class="error_message" style="margin-top:5px;">
									提示：新密码长度最少为6位，且必须包含大写字母、小写字母、数字、符号中的任意三种
								</div>
	                        </div>
	                        <div>
	                        	<div class="btn login_btn" onclick="setNewPassword()">确定</div>
	                        </div>
	                        <div class="login_extra_block">
	                            <div class="inline_div account_foget" id="back_login">返回登录</div>
	                        </div>
	                    </div>
                        <div class="weixin_content">
                            <img width="91px" src="${ctx }/images/common/officialAccounts.png"/>
                            <div class="weixin_text">RiskRaider风险雷达 扫码免费试用</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="login_back_title_block">
                <img src="${ctx }/images/modules/login/login_back_title.png"/>
            </div>
        </div>
    </div>
    
    <div class="footer">
        <div class="footer_content">
            <div>上海风声企业信用征信有限公司©2016-版本V${appVersion }</div>
            <div>客服热线：021-68580928-8011&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;服务时间：周一 至 周五&nbsp;&nbsp;上午9点-下午6点</div>
        </div>
    </div>
    <%= session.getId() %>
</div>
</body>
</html>