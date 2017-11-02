<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
    	<%@ include file="/page/common/base.jsp"%>
		<%@ include file="/page/common/header.jsp"%>
		<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
		
        <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/panorama.css?${appVersion }">
        
        <script type="text/javascript">
           $(document).ready(function(){
               var deep = $("#deep").val();
               
               updateNavMenu(parseInt(deep),"司法全景图",window.location.search);
           });
        </script>
    </head>
    
    <body class="ljzx_page back_gray">
       <jsp:include page="/page/modules/index/banner.jsp" />
       <input id="companyName" value="${companyName }" type="hidden"/>
       
       <input id="deep" value="${deep }" type="hidden"/>
       
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
                         <span><a href="#">监控企业列表</a></span>
                        <span>></span>
                         <span><a href="#">监控企业详情</a></span>
                        <span>></span>
                        <span class="current_nav">司法全景图</span>
                    </div>

                    
                    <router-view></router-view>
            </div>
        </div>
        <div id="loadingAnimation">
            <div id="preloader5"></div>
        </div>
        <style>
            *{-webkit-box-sizing: content-box;-moz-box-sizing: content-box;box-sizing: content-box;}
            a:link { text-decoration: none;}
            .nav_bar a:hover{color:#2ea7e0;}
        </style>
        
         <script type="text/javascript" src="${ctx }/js/modules/monitor/panorama.js?${appVersion }"></script>
        
        
    </body>
</html>
