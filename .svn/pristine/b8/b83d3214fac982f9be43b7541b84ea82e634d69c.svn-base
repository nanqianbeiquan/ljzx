<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/page/common/base.jsp"%>
<html>
    <head>
        <meta charset="UTF-8">
		<%@ include file="/page/common/header.jsp"%>
		<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
       	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/history.css?${appVersion }">
<!--         <script type="text/javascript" src="${ctx }/js/jquery/jquery.js"></script> -->
<!--         <script type="text/javascript" src="${ctx }/js/common/layout.js"></script> -->
        <script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
<!--         <link type="text/css" rel="stylesheet" href="${ctx }/css/common/layout.css"> -->
        <script type="text/javascript">
           $(document).ready(function(){
               var deep = $("#deep").val();
               updateNavMenu(parseInt(deep),"法人代表信息",window.location.search);
           });
        </script>
    </head>
  
  <body>
    <jsp:include page="/page/modules/index/banner.jsp"/>
    <input type="hidden" id="deep" value="${deep}" />
    <input type="hidden" id="companyName" value="${companyName}" />
        <div class="body_content_5">
        <div class="body_center ">
            <div class="body_block_2 block_shadow">
                <div class="nav_bar">
                    <div class="back_block">
                        <div class="inline_div">
                                    <div class="for_back_btn"></div>
                                </div>
                    </div>
                    <span><a href="#">首页</a></span>
                    <span>></span>
                    <span><a href="#">监控企业列表</a></span>
                    <span>></span>
                    <span><a href="#">监控企业详情</a></span>
                    <span>></span>
                    <span class="current_nav">法人代表信息</span>
                </div>
                <div class="history_content">
                    <div class="modules_block_list">
                    	<div class="modules_block current_modules">
                    		   <!-- 企业法人代表对外投资信息 -->
								<div  class="info_block">   
								   <div id="">
								       <div class="info_title">
										    <div class="inline_div title">企业法人代表对外任职信息</div>
										    <div class="inline_div count">
											  <c:if test="${not empty fRPositionArray}">
											    ${fn:length(fRPositionArray)}
											  </c:if>
											  <c:if test="${empty fRPositionArray}">
											    0
											  </c:if>											  
										    </div>
										    <div class="inline_div message hidden"></div>
										</div>
										<div class="info_table" id="fRPositionDiv">
										   <jsp:include page="/page/modules/monitor/monitorCompany/fRPosition.jsp"></jsp:include>
										</div>
								   </div>
								   <div id=""  class="page_block" style="">
								   		<div id="fRPositionPageNav"  class="pagination" style="display: none;"></div>
								   </div>
								</div>
								 <!-- 企业外投资信息 -->
								<div  class="info_block">   
								   <div id="">
								       <div class="info_title">
										    <div class="inline_div title">企业法人代表对外投资信息</div>
										    <div class="inline_div count">
											  <c:if test="${not empty fRInvArray}">
											    ${fn:length(fRInvArray)}
											  </c:if>
											  <c:if test="${empty fRInvArray}">
											    0
											  </c:if>
										    </div>
										    <div class="inline_div message hidden"></div>
										</div>
										<div class="info_table" id="fRInvDiv">
										   <jsp:include page="/page/modules/monitor/monitorCompany/fRInv.jsp"></jsp:include>
										</div>
								   </div>
								   <div id=""  class="page_block" style="">
								   	  <div id="fRInvPageNav"  class="pagination" style="display: none;"></div>
								   </div>
								</div>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
  </body>
</html>
