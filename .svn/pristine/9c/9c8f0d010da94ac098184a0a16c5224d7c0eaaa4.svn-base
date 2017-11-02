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
	<script type="text/javascript" src="${ctx }/js/modules/client/client_account_list.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/index/setting_menu.js?${appVersion }"></script>
	
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
			
            var useraccount=new UserAccountController();
            
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
	                	<div class="mode selected" onclick="toClientAccountManage()">
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
                <div class="config_mode_0 config_content_mode inline_div_block" id="AccountConfig">
                    <div class="inline_div" id="DepartmentBlock">
                        <div class="department_name"><strong>账户总览</strong></div>
                    	<div class="inline_div_block">
                            <div class="inline_div account_num_block total_account_num" style="padding-left:48px">
                                <div class="account_num">0</div>
                                <div class="account_title blue_account_title_icon">账户数</div>
                            </div>
                            <div class="inline_div account_num_block used_account_num">
                                <div class="account_num">0</div>
                                <div class="account_title red_account_title_icon">已用</div>
                            </div>
                            <div class="inline_div account_num_block remaining_account_num">
                                <div class="account_num">0</div>
                                <div class="account_title green_account_title_icon">未用</div>
                            </div>
                        </div>
                        <div class="parting_line" id="">
                            
                        </div>
                        <div class="account_tree_block" id="UserAccountTree">
                            
                        </div>
                    </div>
                    <div class="inline_div" id="CurrentAccountSub">
                        <div class="current_account_name"><strong>${account.clientCompany.companyName}</strong></div>
                        <div class="inline_div_block">
                            <div class="inline_div monitor_num_block">
                                <div class="monitor_list" style="float:left">
                                    <div class="monitor_num" id="MonitorTotalNum">
                                        0
                                    </div>
                                    <div class="absolute_block">
                                        <div class="relative_center_block ">
                                            <div class="monitor_num_block_arr"></div>
                                        </div>
                                    </div>
                                    <div class="monitor_num_span">
                                    	监控额度
                                    </div>
                                </div>
                                <div class="monitor_list" style="float:left;margin: 5px 0px 0px 30px;">
                                    <div class="monitor_num" id="MonitorRemainingNum">
                                        0
                                    </div>
                                    <div class="absolute_block">
                                        <div class="relative_center_block">
                                            <div class="monitor_num_block_arr"></div>
                                        </div>
                                    </div>
                                    <div class="monitor_num_span">
                                    	剩余额度
                                    </div>
                                </div>
                            </div>
                            <div class="inline_div account_chart_block">
                                <!-- <div class="chart_block_title">账户总览</div> -->
                                <div>
                                    <div class="absolute_block">
                                        <!-- <div class="relative_left_block total_monitor_num">
                                            <div class="monitor_num">0</div>
                                            <div class="monitor_span">监控额度</div>
                                        </div> -->
                                    </div>
                                    <!-- <div class="inline_div" id="UserAccountPie"></div> -->
                                                                        
                                </div>
                            </div>
                            <!-- <div class="inline_div inline_div_block monitor_detail_block">
                                 <div class="inline_div monitor_account_percent_block">
                                    <div class="monitor_account_percent monitor_account_percent_0">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_1">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_2">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_3">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_4">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                </div> -->
                                <!-- <div class="inline_div monitor_account_percent_block">
                                    <div class="monitor_account_percent monitor_account_percent_3">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_4">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_5">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_6">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_0">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                </div> -->
                                <!-- <div class="inline_div monitor_account_percent_block">
                                    <div class="monitor_account_percent monitor_account_percent_1">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_2">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_3">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_4">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                    <div class="monitor_account_percent monitor_account_percent_5">
                                        <span class="monitor_percent">12%</span>
                                        <span class="monitor_account">SRD</span>
                                    </div>
                                </div> 
                            </div>  -->
                            
                        </div>
                        <div class="account_table_title">
                            <div class="absolute_block">
                                <div class="relative_right_block added_btn_block">
                                    <div class="added_btn" id="AddAccountBtn">新增</div>
                                </div>
                            </div>
                            <div>账户管理<span class="current_selected_account"></span></div>
                        </div>
                        <div>
                            <table class="first_col_table" style="width:100%;">
                                <thead>
                                    <tr>
                                        <td style="width:40px">序号</td>
                                        <td style="width:80px">账户名</td>
                                        <td style="width:100px">账户角色</td>
                                        <td style="width:100px">监控额度</td>
                                        <td style="width:100px">手机号</td>
                                        <td style="">邮箱</td>
                                        <td style="width:120px">操作</td>
                                    </tr>
                                </thead>
                                <tbody>
                                    <!--<tr>
                                        <td>1</td>
                                        <td>SRD</td>
                                        <td>母账户</td>
                                        <td>200</td>
                                        <td>153493204802</td>
                                        <td>153493204802@qq.com</td>
                                        <td class="inline_div_block">
                                            <div class="inline_div change">修改</div>
                                            <div class="inline_div delete">删除</div>
                                            <div class="inline_div disabled">禁用</div>
                                        </td>
                                    </tr>-->
                                </tbody>
                            </table>
                            <div id="AccountTablePageBlock">
                                    
                            </div>
                        </div>
                        
                    </div>
                </div>
			</div>
			</div>
    	</div>
	</div>
	
    <div class="hidden" id="UserAccountOverlap">
    	<div class="account_overlap_title">新增账户</div>

    	<div class="inline_div_block account_info_block">    
    		<!-- <div class="first_line">
                <span>上级账户：</span>
                <span class="parent_name">SRD</span>
            </div>           -->		
             <div class="lock_area">
                 	<div class="superior_account">
			            <span>上级账户：</span>
			            <span class="parent_name">SRD</span>
			        </div>   
	             <div class="inline_div"  style="margin-right:50px;">
	                 <span class="">
	                 <div class="absolute_block">
	                 	<div class="tip_icon"></div>
	                 </div>
	                 	监控额度：</span>
	                 <input class="account_amount" maxlength="20" onKeyUp="value=value.replace(/\D/g,'')" onchange="value=value.replace(/\D/g,'')"  type="text" />
	             </div>
	             <div class="inline_div"  style="margin-right:0px;" >
	                 <span><div class="absolute_block">
	                 	<div class="tip_icon"></div>
	                 </div>剩余户数：</span>
	                 <input class="account_num"  maxlength="20" onKeyUp="value=value.replace(/\D/g,'')" onchange="value=value.replace(/\D/g,'')"  type="text" />
	             </div>
             </div>
    		          <div class="hidden">
            </div>
            <div class="inline_div">
                <span><div class="absolute_block">
	                 	<div class="tip_icon"></div>
	                 </div>账户名称：</span>
                <input class="account_name" maxlength="20" onkeyup="value=value.replace(/[\W]/g,'') " onbeforepaste="clipboardData.setData('text',clipboardData.getData('text').replace(/[^\d]/g,''))" type="text" />
            </div>
            <div class=" inline_div">
	            <span>&nbsp;&nbsp;&nbsp;备用名：</span>
	            <input class="account_remarks" type="text" maxlength="20"/> 
        	</div>
            <div class="inline_div">
                <span><div class="absolute_block">
	                 	<div class="tip_icon"></div>
	                 </div>账户角色：</span>
                <select class="account_type" >               	
                    <option value="2" id="account_1">子账户</option>
                    <option value="3">附属账户</option>
                </select>
            </div>
 <div class="hidden">
            </div>
            <div class="inline_div">
                <span>手机号码：</span>
                <input class="account_phone"  maxlength="11" onkeypress="return event.keyCode>=48&&event.keyCode<=57" ng-pattern="/[^a-zA-Z]/"  type="text" />
            </div>
            <div class="inline_div">
                <span>邮箱地址：</span>
                <input class="account_email" type="text" maxlength="20"/>
            </div>
            <div class="inline_div">
                <span><div class="absolute_block">
	                 	<div class="tip_icon"></div>
	                 </div>密码设置：</span>
                <input class="account_password" type="password" maxlength="20"/>
            </div>
            <div class="inline_div">
                <span><div class="absolute_block">
	                 	<div class="tip_icon"></div>
	                 </div>确认密码：</span>
                <input class="account_repassword" type="password" maxlength="20"/>
            </div>
        </div>
        <div class="btn_block">
            <div class="inline_div btn">保存</div>
            <div class="inline_div cancel_btn">取消</div>
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