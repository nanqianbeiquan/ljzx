<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	
    <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/user/userInfo.css?${appVersion }">
    <link type="text/css" rel="stylesheet" href="${ctx }/css/common/treeview.css?${appVersion }">
    
    <script type="text/javascript" src="${ctx }/js/echarts/debug/echarts.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/validateUtil.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/common/treeview.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/common/loadwaiting.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/common/backspace_disabled.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/index/setting_menu.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/client/client_security_center.js?${appVersion }"></script>
	
	<script type="text/javascript">
		var controller;
		
		$(function(){
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
			
			controller = new userConfigController();
			
			updateNavMenu(1,"设置",window.location.search);
		})
		
	</script>
	
	<style type="text/css">
		.btn{cursor:pointer;font-size:14px;color:#ffffff;background:#2ea7e0;width:92px;height:30px;line-height:30px;text-align: center;border-radius:2px;}
		.btn:hover{background:#1b89bd;}

		.btn_3{cursor:auto;font-size:14px;width:92px;height:30px;line-height:30px;text-align: center;color:#666666;background:#D7D7D7;}
	</style>
	
</head>
<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	
	<input type="hidden" id="deep" value="1" />
	
	<div class="body_content_5 " style="min-height: 100%;">
    	<div class="body_center ">
	    	<div class="body_block_2 block_shadow" style="min-height: 100%;">
	        	<div class="nav_bar">
	            	<div class="back_block">
	                    <div class="inline_div">
	                        <div class="for_back_btn" onclick="window.history.back()"></div>
	                    </div>
	            	</div>
	            	<span><a href="${ctx }/index">首页</a></span>
	                <span>></span>
	                <span class="current_nav">设置</span>
				</div>
	            <div class="config_mode ">
	                <div class="model_list">
	                	<c:if test="${account.canCreateChild eq '1'}">
		                    <div class="mode gray" onclick="toClientAccountManage()">
		                        <img class="mode_img" id="" src="${ctx }/images/modules/user/user_account.png"/>
		                        <img class="gray_img hidden" id="" src="${ctx }/images/modules/user/user_ccount_gray.png"/>
		                        <div class="mode_title">账户管理</div>
		                        <div class="select_border hidden">
		                            <img src="${ctx }/images/modules/user/config_arr.png"/>
		                            <div class="border_line"></div>
		                        </div>
		                    </div>
	                    </c:if>
	                    <div class="mode gray" onclick="toClientAccountInfo()">
	                        <img class="mode_img" id="" src="${ctx }/images/modules/user/user_info.png"/>
	                        <img class="gray_img hidden" id="" src="${ctx }/images/modules/user/user_info_gray.png"/>
	                        <div class="mode_title">账户信息</div>
	                        <div class="select_border hidden">
	                            <img src="${ctx }/images/modules/user/config_arr.png"/>
	                            <div class="border_line"></div>
	                        </div>
	                    </div>
	                    <c:if test="${account.type ne '3'}">
		                    <div class="mode gray" onclick="toMonitorSetting()">
		                        <img class="mode_img" id="" src="${ctx }/images/modules/user/user_monitor.png"/>
		                        <img class="gray_img hidden" id="" src="${ctx }/images/modules/user/user_monitor_gray.png"/>
		                        <div class="mode_title">动态监控设置</div>
		                        <div class="select_border hidden">
		                            <img src="${ctx }/images/modules/user/config_arr.png"/>
		                            <div class="border_line"></div>
		                        </div>
		                    </div>
	                    </c:if>
	                    <div class="mode selected" onclick="toSecurityCenter()">
	                        <img class="mode_img" id="" src="${ctx }/images/modules/user/user_password.png"/>
	                        <img class="gray_img hidden" id="" src="${ctx }/images/modules/user/user_password_gray.png"/>
	                        <div class="mode_title">安全中心</div>
	                        <div class="select_border hidden">
	                            <img src="${ctx }/images/modules/user/config_arr.png"/>
	                            <div class="border_line"></div>
	                        </div>
	                    </div>
	                    <div class="mode gray" onclick="toLogList()">
	                        <img class="mode_img" id="" src="${ctx }/images/modules/user/user_operate.png"/>
	                        <img class="gray_img hidden" id="" src="${ctx }/images/modules/user/user_operate_gray.png"/>
	                        <div class="mode_title">操作日志</div>
	                        <div class="select_border hidden">
	                            <img src="${ctx }/images/modules/user/config_arr.png"/>
	                            <div class="border_line"></div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="config_content">
	                <div class="config_mode_3 config_content_mode config_content_padding ">
	                    <div class="config_block first_col_table">
	                        <div class="config_title">修改密码</div>
	                        <div class="config_value">
	                        	<div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">旧&nbsp;&nbsp;密&nbsp;&nbsp;码:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                    <div class="row_value"><input type="password" id="oldPassword" name="oldPassword" placeholder="" maxlength="20" /></div>
	                                </div>
	                            </div>
	                            <div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">新&nbsp;&nbsp;密&nbsp;&nbsp;码:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                    <div class="row_value">
	                                      <input type="password" id="newPassword" name="newPassword" placeholder="" maxlength="20" />
	                                      
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">确认密码:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                    <div class="row_value"><input type="password" id="confirmPassword" name="confirmPassword" placeholder="" maxlength="20" /></div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
		                <span class="error_message">提示：新密码长度最少为6位，且必须包含大写字母、小写字母、数字、符号中的任意三种</span>
	                    <div class="btn_block" style="margin-top:20px;">
	                        <div class="inline_div btn" onclick="updatePassword();">保存</div>
	                    </div>
	                    <div class="config_block first_col_table">
	                        <div class="config_title">修改手机号</div>
	                        <div class="config_value">
	                            <div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">手机号:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                	<div class="row_value">
		                                	<input type="text" id="cellPhone" name="cellPhone" class="inline_div" placeholder="请输入新手机号" maxlength="11" />
		                                	<div id="new_phone_code" class="inline_div btn_3" >发送验证码</div>
		                            	</div>
	                                </div>
	                            </div>
	                            <div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">验证码:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                    <div class="row_value">
	                                        <input type="text" id="verificationCode" name="verificationCode" placeholder="请输入短信验证码" maxlength="4"/>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="btn_block"><div class="inline_div btn" onclick="updateCellPhone();">保存</div></div>
						<div class="config_block first_col_table">
	                        <div class="config_title">修改邮箱</div>
	                        <div class="config_value">
	                            <div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">邮箱:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                	<div class="row_value">
		                                	<input type="text" id="email" name="email" class="inline_div" placeholder="请输入邮箱" maxlength="50" />
		                                	<div id="new_email_code" class="inline_div btn_3" >发送验证码</div>
		                            	</div>
	                                </div>
	                            </div>
	                            <div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">验证码:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                    <div class="row_value">
	                                        <input type="text" id="emailVerificationCode" name="emailVerificationCode" placeholder="输入验证码" maxlength="4"/>
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                    <div class="btn_block"><div class="inline_div btn" onclick="updateClientAccountEmail();">保存</div></div>
					</div>
				</div>
	   		</div>
   		</div>
   	</div>
    <div class="message_overlap hidden" id="MessageOverlap">
        <div class="message_title_block">
            <div class="title">
            	提示
            </div>
        </div>
        <div class="message_content_block">
            <div class="content">
                <div class="inline_div content_img tips_img"></div>
                <div class="inline_div content_value"></div>
            </div>
        </div>
        <div class="message_btn_block">
            <div id="cancel_message" class="inline_div small_overlap_btn_1">
            	<div>取消</div>
            </div>
            <div id="ok_message" class="inline_div small_overlap_btn">
               	 确定
            </div>
        </div>
    </div>
</body>
</html>