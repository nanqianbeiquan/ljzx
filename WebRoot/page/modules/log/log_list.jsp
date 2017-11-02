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
	<script type="text/javascript" src="${ctx }/js/modules/log/log_list.js?${appVersion }"></script>
	
	<script type="text/javascript">
		
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
			
			
		    var deep = $("#deep").val();
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
	<input type="hidden" id="currentDateStr" value="${currentDateStr}" />
	
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
                    <div class="mode gray" onclick="toSecurityCenter()">
                        <img class="mode_img" id="" src="${ctx }/images/modules/user/user_password.png"/>
                        <img class="gray_img hidden" id="" src="${ctx }/images/modules/user/user_password_gray.png"/>
                        <div class="mode_title">安全中心</div>
                        <div class="select_border hidden">
                            <img src="${ctx }/images/modules/user/config_arr.png"/>
                            <div class="border_line"></div>
                        </div>
                    </div>
                    <div class="mode selected" onclick="toLogList()">
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
                <div class="config_mode_4 config_content_mode" id="AccountOperateDetail">
                    <div class="company_detail_block">
                        <span class="current_account_name">${clientCompanyName }</span>
                    </div>
                    <div class="inline_div_block operate_detail_block">
                        <div class="inline_div operate_block">
                            <span class="span_name">账户名称：</span>
                            <select id="accountIdSelect">
                                <option value="全部" selected="selected">全部</option>
                                <c:forEach items="${logAccountList }" var="acc">
                                    <option value="${acc.accountId }">${acc.accountName }</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="inline_div operate_block">
                            <span class="span_name">操作类型：</span>
                            <select id="actionTypeSelect">
                                <option value="全部" selected="selected">全部</option>
<!--                                 <option value="登录" >登录</option> -->
<!--                                 <option value="搜索企业" >搜索企业</option> -->
                                <option value="新增动态监控" >新增动态监控</option>
                                <option value="取消动态监控" >取消动态监控</option>
<!--                                 <option value="查看失信记录" >查看失信记录</option> -->
                                <option value="新增失信记录" >新增失信记录</option>
                                <option value="删除失信记录" >删除失信记录</option>
<!--                                 <option value="搜索失信信息" >搜索失信信息</option> -->
<!--                                 <option value="查看监控详情" >查看监控详情</option> -->
<!--                                 <option value="查看财务分析" >查看财务分析</option> -->
<!--                                 <option value="查看司法全景图" >查看司法全景图</option> -->
<!--                                 <option value="查看企业详情" >查看企业详情</option> -->
<!--                                 <option value="查看法人代表信息" >查看法人代表信息</option> -->
                                <option value="新增风险自定义" >新增风险自定义</option>
                                <option value="修改风险自定义" >修改风险自定义</option>
                                <option value="删除风险自定义" >删除风险自定义</option>
                                <option value="新增账户" >新增账户</option>
                                <option value="修改账户" >修改账户</option>
                                <option value="删除账户" >删除账户</option>
                                <option value="禁用账户" >禁用账户</option>
                                <option value="启用账户" >启用账户</option>
<!--                                 <option value="下载报告" >下载报告</option> -->
                            </select>
                        </div>
                        <div class="inline_div operate_block">
                            <span class="span_name">操作时间：</span>
                            <input class="Wdate" id="fromDate" name="fromDate" placeholder="开始时间"
													type="text" style="width:100px;background:#ffffff;text-align:center;"
													onfocus="WdatePicker({onpicking:	function(dp){fromDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){fromDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                            <span>&nbsp;&nbsp;-&nbsp;&nbsp;</span>
                            <input class="Wdate" id="toDate" name="toDate" placeholder="结束时间"
													type="text" style="width:100px;background:#ffffff;text-align:center;"
													onfocus="WdatePicker({onpicking:	function(dp){toDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){toDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true})" />
                            
                        </div>
                        <div class="inline_div btn" onclick="logSearch()">查询</div>
                    </div>
                    <div id="logListTableDiv">
                    </div>
                    <div id="logListTablePageDiv"></div>
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