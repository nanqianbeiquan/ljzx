<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/info/articleDetail.css?${appVersion }">
</head>
<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	
	<div class="body_content_5">
        <div class="body_center ">
            <div class="body_block_2 block_shadow">
                <div class="nav_bar">
                    <div class="back_block">
		                <div class="inline_div">
		                	<div class="for_back_btn" onclick="window.history.back()"></div>
		            	</div>
		            </div>
                    <span><a href="${ctx }/index">首页</a></span>
                    <span>></span>
                    <span class="current_nav">新闻详情页</span>
                </div>
                <div class="news_content">
                    <div class="news_title">${article.title }</div>
                    <div class="news_time"><span><fmt:formatDate value="${article.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></div>
                    <div class="news_value">
                        ${article.content }
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</body>
</html>