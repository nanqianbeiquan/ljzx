<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/info/message.css?${appVersion }">
	<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/info/message.js?${appVersion }"></script>
	
	<script type="text/javascript">
		var controller;
		var mode = "list";
		
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
	        updateNavMenu(parseInt(deep),"我的消息",window.location.search);
		
			queryMessageList("");
		
			controller = new userMessageController();
			
			$("#checkAll").click(function(){
				if($(this).is(":checked")){
					$("[name='message']").prop("checked",'true');//全选 
				}else{
					$("[name='message']").removeAttr("checked");//取消全选 
				}
			})
		});
	
	</script>
</head>
<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	<input type="hidden" id="deep" value="${deep}" />
	<input type="hidden" id="pageName" value="MESSAGE_CENTER" />
	<div class="body_content_5 ">
    	<div class="body_center">
			<div class="body_block_2 block_shadow">
                <div class="nav_bar">
                    <div class="back_block">
                        <div class="inline_div">
                            <div class="for_back_btn" onclick="window.history.back()"></div>
                        </div>
                    </div>
                    <span><a href="${ctx }/index">首页</a></span>
                    <span>></span>
                    <span class="current_nav">我的消息</span>
                </div>
                <div class="user_content">
                    <div class="content_title">
                        <div class="content_title_line">
                            <div class="title_line"></div>
                        </div>
                        <div class="title_value">
                           	我的消息
                        </div>
                    </div>
                    <div class="message_head">
                        <div class="h_mode selected inline_div" onclick="queryMessageList('')">全部</div>
                        <div class="h_mode inline_div" onclick="queryMessageList('1')">动态监控</div>
                        <div class="h_mode inline_div" onclick="queryMessageList('0')">系统消息</div>
                        <input type="hidden" id="sender" name="sender" />
                    </div>
                    <div class="c_content message_list_block ">
                        <div class="message_config">
                            <div class="r_config inline_div_block">
                                <div class="inline_div small_btn_2" onclick="deleteMessages()">删除</div>
                                <div class="inline_div small_btn_2" onclick="readAllMessage()">全部已读</div>
                            </div>
                            <div class="l_config">
                                <input type="checkbox" id="checkAll"/>全选
                            </div>
                        </div>
                        <div id="messageList" class="message_list">
                        </div>
                        <div class="message_page">
                            <div class="page_block">
                                <div id="Pagination"></div>
                            </div>
                        </div>
                    </div>
                    
                    <div class="c_content message_read_block hidden">
                        <div class="message_config">
                            <div class="l_config">
                                <div class="inline_div back_list_btn small_img_btn">
                                    <img class="inline_div" src="${ctx }/images/common/back_arr.png" />
                                    <span class="inline_div">返回</span>
                                </div>
                            </div>
                        </div>
                        <div class="message_read">
                            <div class="l_span inline_div">主题:</div>
                            <div id="messageTitle" class="r_span inline_div">棱镜征信的优势</div>
                        </div>
                        <div class="message_read">
                            <div class="l_span inline_div">时间:</div>
                            <div id="messageTime" class="r_span inline_div">2016-10-10</div>
                        </div>
                        <div class="message_read">
                            <div class="l_span inline_div">内容:</div>
                            <div id="messageConent" class="r_span inline_div">棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势棱镜征信的优势</div>
                        </div>
                    </div>
                </div>
            </div></div>
        </div>
	
</body>
</html>