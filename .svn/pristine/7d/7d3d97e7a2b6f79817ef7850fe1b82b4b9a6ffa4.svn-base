<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/todayMonitorCompanyList.css?${appVersion }">
	
	<script type="text/javascript" src="${ctx }/js/modules/monitor/todayMonitorCompanyList.js?${appVersion }"></script>
	
	<script type="text/javascript">
		queryTodayMonitorCompanyList();
	</script>
</head>
<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	
	<input type="hidden" id="deep" value="${deep}" />
	
	<div class="body_content_5">
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
	                <span class="current_nav">最新加入监控的公司</span>
	            </div>
	            
            	<div class="today_company_content">
                    <div id="companyList" class="company_list">
                    </div>
                    <div class="page_block">
                    	<div id="Pagination"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>