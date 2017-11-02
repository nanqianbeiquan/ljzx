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
	<script type="text/javascript" src="${ctx }/js/modules/monitor/monitor_setting.js?${appVersion }"></script>
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
	                    	<div class="mode selected" onclick="toMonitorSetting()">
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
	                <div class="config_mode_2 config_content_mode config_content_padding">
	                	<div class="config_block first_col_table">
	                    	<div class="config_title">事件选择</div>
	                    	
	                    	<div class="config_value attentionEventClass">
	                       		<table class="config_table">
	                            	<tr>
	                                    <td>法人代表变更</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 0, 1) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 0, 1) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 0, 1)}" />
	                                    </td>
	                                    <td>股东变更</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 1, 2) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 1, 2) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 1, 2)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>高管变更</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 2, 3) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 2, 3) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 2, 3)}" />
	                                    </td>
	                                    <td>经营异常</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 3, 4) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 3, 4) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 3, 4)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>企业更名</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 4, 5) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 4, 5) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 4, 5)}" />
	                                    </td>
	                                    <td>经营状态</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 5, 6) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 5, 6) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 5, 6)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>对外投资</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 6, 7) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 6, 7) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 6, 7)}" />
	                                    </td>
	                                    <td>动产抵押</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 7, 8) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 7, 8) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 7, 8)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>股权出质</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 8, 9) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 8, 9) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 8, 9)}" />
	                                    </td>
	                                    <td>股权冻结</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 9, 10) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 9, 10) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 9, 10)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>工商行政处罚</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 10, 11) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 10, 11) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 10, 11)}" />
	                                    </td>
	                                    <td>食药监监查</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 11, 12) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 11, 12) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 11, 12)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>质量监督</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 12, 13) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 12, 13) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 12, 13)}" />
	                                    </td>
	                                    <td>社保缴纳</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 13, 14) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 13, 14) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 13, 14)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>环境核查</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 14, 15) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 14, 15) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 14, 15)}" />
	                                    </td>
	                                    <td>欠税信息</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 15, 16) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 15, 16) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 15, 16)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>税务非正常户</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 16, 17) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 16, 17) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 16, 17)}" />
	                                    </td>
	                                    <td>裁判文书</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 17, 18) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 17, 18) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 17, 18)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>裁判文书（金融合同）</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 18, 19) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 18, 19) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 18, 19)}" />
	                                    </td>
	                                    <td>裁判文书（劳务纠纷）</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 19, 20) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 19, 20) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 19, 20)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>被执行人</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 20, 21) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 20, 21) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div"> 不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 20, 21)}" />
	                                    </td>
	                                    <td>失信被执行人</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 21, 22) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 21, 22) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 21, 22)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>开庭公告</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 22, 23) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 22, 23) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 22, 23)}" />
	                                    </td>
	                                    <td>媒体资讯</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 23, 24) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 23, 24) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 23, 24)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>重点关注舆情</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 24, 25) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 24, 25) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 24, 25)}" />
	                                    </td>
	                                    <td>专利</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 25, 26) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 25, 26) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 25, 26)}" />
	                                    </td>
	                                </tr>
	                                <tr>
	                                    <td>投招标</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 26, 27) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 26, 27) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 26, 27)}" />
	                                    </td>
	                                    <td>商标</td>
	                                    <td>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 27, 28) eq '1'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">关注</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${fn:substring(monitorUserConfigure.attentionEventClass, 27, 28) eq '0'}">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">不关注</div>
	                                        </div>
	                                        <input type="hidden" name="attentionEventClass" value="${fn:substring(monitorUserConfigure.attentionEventClass, 27, 28)}" />
	                                    </td>
	                                </tr>
	                            </table>
	                        </div>
						</div>
	                    <div class="config_block first_col_table">
	                        <div class="config_title">监控周期</div>
	                        <div class="config_value monitorCycle">
	                            <table class="config_table" style="width:556px;">
	                                <tr>
	                                    <td style="width:236px">监控周期</td>
	                                    <td style="width:320px">
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${monitorUserConfigure.monitorCycle eq '1' }">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">每日</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${monitorUserConfigure.monitorCycle eq '2' }">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">每周</div>
	                                        </div>
	                                        <div class="inline_div selector_option">
	                                            <div class="selector <c:if test="${monitorUserConfigure.monitorCycle eq '3' }">selected</c:if> inline_div"></div>
	                                            <div class="value inline_div">每月</div>
	                                        </div>
	                                        <input type="hidden" id="monitorCycle" name="monitorCycle" value="${monitorUserConfigure.monitorCycle }" >
	                                    </td>
	                                </tr>
	                            </table>
	                        </div>
	                    </div>
	                    <div class="config_block first_col_table">
		                    <div class="config_title">通知方式</div>
		                    <div class="config_value">
		                        <div class="new_btn_block">
		                            <div class="inline_div config_selector ">
		                                <div id="email_btn" class="inline_div ">
		                                    <div class="add_img inline_div"></div>
		                                    <span  class="inline_div">新增</span>
		                                </div>
		                            </div>
		                        </div>
		                        <div class=" config_selector">
		                            <div class="checkor">
		                                <input type="checkbox" id="notifyType" name="notifyType" class="inline_div check_option" <c:if test="${fn:substring(monitorUserConfigure.notifyType, 0, 1) eq '1'}">checked="checked"</c:if> /><span class="value inline_div">站内信</span>
		                                <span class="check_mark"></span>
		                            </div>
		                        </div>
		                        <div class=" config_selector email_list">
		                            <div class="checkor">
		                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="value inline_div">邮件</span>
		                                <span class="check_mark line_txt inline_div"></span>
		                            </div>
		                            <div class="default_email_checkor hidden">
	                                    <input type="checkbox" id="notifyType" name="notifyType" class="inline_div check_option" /><span class="value inline_div">邮件1</span>
	                                    <span class="check_mark line_txt inline_div"></span>
	                                    <div class="inline_div delete_btn" onclick="deleteEmail($(this).parents('.checkor').addClass('delete').find('.check_mark').text())">
	                                        <div class="close_left inline_div"></div>
	                                        <div class="close_right inline_div"></div>
	                                    </div>
	                                </div>
		                            <c:forEach items="${emailList }" var="email" varStatus="status" >
		                            	<div class="checkor">
		                                	<input type="checkbox" id="notifyType" name="notifyType" <c:if test="${fn:substring(monitorUserConfigure.notifyType, status.count, status.count+1) eq '1'}">checked="checked"</c:if> class="inline_div check_option" /><span class="value inline_div">邮件${status.count }</span>
		                                	<span class="check_mark line_txt inline_div">${email }</span>
		                                	<div class="inline_div delete_btn" onclick="deleteEmail($(this).parents('.checkor').addClass('delete').find('.check_mark').text())">
		                                    	<div class="close_left inline_div"></div>
		                                    	<div class="close_right inline_div"></div>
		                                	</div>
		                            	</div>
		                            </c:forEach>
		                            
		                            <div class="check_editor email_editor hidden">
	                                	<span class="value">邮箱${emailNum }</span>
	                                    <input type="text"/>
	                                </div>
		                        </div>
		                    </div>
		                </div>
	                    <div class="btn_block"  style="padding-bottom:100px;">
	                        <div class="inline_div btn" onclick="updateMonitorUserConfigure()">保存</div>
	                    </div>
					</div>
				</div>
	            <div class="email_confirm edit_block hidden">
	                <div class="edit_title">新邮箱认证</div>
	                <div class="option_list">
	                    <div class="inline_div option_title">邮箱:
	                    </div><div class="inline_div option_value">
	                        <div class="option option_text">
	                            <input type="text" id="newEmail" name="newEmail" class="email_value" placeholder="输入邮箱" maxlength="100"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="option_list">
	                    <div class="inline_div option_title">
	                    </div><div class="inline_div option_value">
	                        <div class="option option_btn">
	                            <div class="inline_div" id="sendEmailVerificationCode">发送验证码</div>
	                        </div>
	                    </div>
	                </div>
	                <div class="option_list">
	                    <div class="inline_div option_title">
	                    </div><div class="inline_div option_value">
	                        <div class="option option_text">
	                            <input type="text" id="emailVerificationCode" name="emailVerificationCode" placeholder="输入验证码" maxlength="4"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="btn_block">
	                    <div class="inline_div btn email_confirm_btn">确定</div>
	                </div>
	        	</div>
	    	</div>
		</div>
	</div>
	<div class="deltips_overlap message_overlap hidden" id="deleteGroupWindow">
        <div class="message_title_block">
            <div class="title">
            	提示
            </div>
        </div>
        <div class="message_content_block">
            <div class="content">
            	<input type="hidden" id="delete_email_value"/>
                <div class="inline_div content_img tips_img"></div>
                <div class="inline_div">确定要删除邮箱吗?</div>
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