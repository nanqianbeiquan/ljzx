<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/info/message.css?${appVersion }">
	
	<script type="text/javascript" src="${ctx }/js/modules/info/feedback.js?${appVersion }"></script>
	
	<script type="text/javascript">
		var controller;
	
		$(function(){
			//获取历史反馈
			queryFeedBackList();
		
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
		
			controller = new userFeedbackController();
			
			var deep = $("#deep").val();
			
			updateNavMenu(parseInt(deep),"意见反馈",window.location.search);
		})
	
	</script>
</head>
<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	
	<input type="hidden" id="deep" value="${deep}" />
	
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
                <span class="current_nav">意见反馈</span>
	        </div>
	        <div class="user_content">
	            <div class="content_title">
	                <div class="content_title_line">
	                    <div class="title_line"></div>
	                </div>
	                <div class="title_value">意见反馈</div>
	            </div>
	            <div class="c_content">
	                <div class="idea_input">
	                    <textarea class="idea_text" id="feedbackContent" name="feedbackContent" maxlength="500" placeholder="请在此输入您的意见和建议，以帮助我们更好的改进产品。"></textarea>
	                	<div class="input_count_block">
                        	<div>
                                <span class="input_length">0</span>
                                <span>/</span>
                                <span class="max_length">500</span>
                            </div>
                        </div>
	                </div>
	                <div class="submit_block">
	                    <div class="inline_div btn" onclick="addFeedBack()">提交</div>
	                </div>
	            </div>
	        </div>
	        <div class="user_content">
	            <div class="content_title">
	                <div class="content_title_line">
	                    <div class="title_line"></div>
	                </div>
	                <div class="title_value">历史反馈</div>
	            </div>
	            <div class="c_content">
	                <div id="feedBackList" class="history_list">
	               	</div>
	            </div>
	        </div>
	    </div></div>
	</div>
</body>
</html>