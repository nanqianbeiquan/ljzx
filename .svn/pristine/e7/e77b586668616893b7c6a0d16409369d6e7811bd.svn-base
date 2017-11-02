<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/company/companyList.css?${appVersion }">
	
	<script type="text/javascript" src="${ctx }/js/modules/company/companyList.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/import_batch.js?${appVersion }"></script>
	
	<script type="text/javascript">
		var controller;
		$(document).ready(function() {
			controller=new companyListController();
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
			
			var opts = {
	            lines: 13, // 花瓣数目
	            length: 10, // 花瓣长度
	            width: 5, // 花瓣宽度
	            radius: 10, // 花瓣距中心半径
	            corners: 1, // 花瓣圆滑度 (0-1)
	            rotate: 0, // 花瓣旋转角度
	            direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
	            color: '#618dbe', // 花瓣颜色
	            speed: 1, // 花瓣旋转速度
	            trail: 60, // 花瓣旋转时的拖影(百分比)
	            shadow: false, // 花瓣是否显示阴影
	            hwaccel: false, //spinner 是否启用硬件加速及高速旋转            
	            className: 'spinner', // spinner css 样式名称
	            zIndex: 2e9, // spinner的z轴 (默认是2000000000)
	            top: '50%', // spinner 相对父容器Top定位 单位 px
	            left: '50%'// spinner 相对父容器Left定位 单位 px
	        };
	
	        window.spinner = new Spinner(opts);
		
			//搜索公司
			queryCompanyInfoList("${keyword }");
			
			document.getElementById("addMonitorCompany").onclick = function(){
				addMonitorCompany();
	    	}
			var deep = $("#deep").val();
			 updateNavMenu(parseInt(deep),"搜索",window.location.search);
		});
	
	
	</script>
	
</head>
<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	
    <input type="hidden" id="deep" value="${deep}" />
    <input type="hidden" id="pageName" value="SEARCH_RESULT" />
    
	<div class="body_content_5">
	    <div class="body_center ">
	        <div class="body_block_2 block_shadow" style="">
	        	<div class="nav_bar">
                    <div class="back_block">
                        <div class="inline_div">
                            <div class="for_back_btn" onclick="window.history.back()"></div>
                        </div>
                    </div>
                    <span><a href="${ctx }/index">首页</a></span>
                    <span>></span>
                    <span class="current_nav">搜索</span>
                </div>
	        
	            <div class="inline_div company_list_block">
	                <div class="company_list">
	                    <div class="search_tips">
	                    	<div class="relative_right_block no_search_tips hidden">
	                			<div class="inline_div">没有找到相关公司</div>
	                			<div class="no_company_icon inline_div"></div>
	                		</div>
	                		<div class="search_tips_values"></div>
	                	</div>
	                	
	                    <div id="companyList">
	                    </div>
	                </div>
	                <div class="page_block">
	                	<div id="Pagination"></div>
	                </div>
	                
	                <input type="hidden" id="currentKeyword" name="currentKeyword" value="${keyword }" >
	            </div>
	            <div class="inline_div company_hot_list">
	                <div class="hot_list_title">热门企业</div>
	                <div>
	                	<c:forEach var="populorCompany" items="${populorCompanyList }">
	                		<div class="company">
		                        <div class="company_name">${populorCompany.companyName }</div>
		                        <div class="company_info">
		                            <span>法人:<span>${populorCompany.resentative }</span></span>
		                            <span>|</span>
		                            <span>注册时间:<span><fmt:formatDate value="${populorCompany.foundedDate }" pattern="yyyy-MM-dd"/></span></span>
		                        </div>
		                    </div>
	                	</c:forEach>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
	
	<div class="new_group_overlap edit_block hidden">
        <div class="edit_title">新建分组</div>
        <div class="option_list">
            <div class="inline_div option_title">组名:
            </div><div class="inline_div option_value">
                <div class="option option_text">
                    <input type="text" id="groupName" name="groupName" maxlength="10" placeholder="输入组名"/>
                </div>
            </div>
        </div>
        <div class="option_list">
            <div class="inline_div option_title">描述:
            </div><div class="inline_div option_value">
                <div class="option option_area">
                    <textarea id="remark" name="remark" maxlength="50"></textarea>
                </div>
            </div>
        </div>
        <div class="btn_block">
            <div class="inline_div small_overlap_btn save_new_group">保存</div>
        </div>
    </div>
    
    <div class="select_group_overlap edit_block hidden">
        <div class="edit_title">选择分组</div>
        <div class="option_list">
            <div class="inline_div option_title">分组:
            </div><div id="monitorGroupList" class="inline_div option_value option_check_value">
            </div>
        </div>
        <div class="btn_block">
            <div class="inline_div small_overlap_btn_1 new_group">
                <div>新建分组</div>
            </div>
            <input type="hidden" id="companyName" name="companyName" value="" />
            <div class="inline_div small_overlap_btn in_group" id="addMonitorCompany">添加</div>
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
                <div class="inline_div content_img tips_img"></div>
                <div class="inline_div">该企业已确认加入监控，如取消监控，将占用当月监控企业数量额度?</div>
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
	<div class="message_overlap hidden" id="MessageInfoOverlap">
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
			<div class="inline_div content_info"></div>
        </div>
        <div class="message_btn_block">
            <div id="cancel_message" class="inline_div small_overlap_btn_1 hidden">
                <div>取消</div>
            </div>
            <div id="ok_message" class="inline_div small_overlap_btn">
                确定
            </div>
        </div>
    </div>
</body>
</html>