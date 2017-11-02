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
	<script type="text/javascript" src="${ctx }/js/modules/client/client_account_info.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/index/setting_menu.js?${appVersion }"></script>
	
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
	                    <div class="mode selected" onclick="toClientAccountInfo()">
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
	                    <div class="mode gray" onclick="toSecurityCenter()">
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
	            	<div class="config_mode_1 config_content_mode config_content_padding">
	                	<div class="config_block first_col_table">
	                    	<div class="config_title">基本信息</div>
	                     	<div class="config_value">
	                        	<div class="config_row">
	                            	<div class="inline_div l_row">
	                                     <div class="row_value">账&nbsp;&nbsp;&nbsp;号:</div>
	                                 </div>
	                                 <div class="inline_div r_row">
	                                     <div class="row_value">${clientAccount.accountName }</div>
	                             	</div>
								</div>
								<div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">姓&nbsp;&nbsp;&nbsp;名:</div>
	                                </div>
	                                <div class="inline_div r_row">
	                                    <div class="row_value">
	                                        <input type="text" id="userName" name="userName" value="${clientAccount.name }" placeholder="输入姓名" maxlength="20"/>
	                                    </div>
	                                </div>
	                            </div>
	                            <div class="config_row">
	                                <div class="inline_div l_row">
	                                    <div class="row_value">手机号:</div>
	                            	</div>
	                            	<div class="inline_div r_row">
	                                	<div class="row_value">${clientAccount.mobilePhone }</div>
	                            	</div>
								</div>
	                            <div class="config_row">
	                            	<div class="inline_div l_row">
	                                	<div class="row_value">邮&nbsp;&nbsp;&nbsp;箱:</div>
	                                </div>
	                            	<div class="inline_div r_row">
	                                	<div class="row_value">${clientAccount.email }</div>
	                           		</div>
	                       		</div>
							</div>
						</div>
						<div class="config_block first_col_table">
							<div class="config_title">账户角色</div>
							<div class="config_value">
						   		<div class="config_row">
						        	<div class="inline_div l_row">
						            	<div class="row_value">
						            		<c:if test="${clientAccount.type eq '1'}">母账号</c:if>
						            		<c:if test="${clientAccount.type eq '2'}">子账号</c:if>
						            		<c:if test="${clientAccount.type eq '3'}">附属账号</c:if>
						            	</div>
						     		</div>
						        	<div class="inline_div r_row">
						                <div class="row_value"></div>
						         	</div>
					     		</div>
					 		</div>
						</div>
	                   	<div class="btn_block">
	                       <div class="inline_div btn" onclick="updateClientInfo()">保存</div>
	                	</div>
					</div>
				</div>
			</div>
   		</div>
   	</div>
</body>
</html>