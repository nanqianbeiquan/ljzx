<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>

<c:set var="ctx" value="<%=basePath %>" />

<script type="text/javascript">
	var ctx = "${ctx}";
</script>
<div class="info_title">
    <div class="inline_div title">变更记录</div>
    <div class="inline_div count">
	   <c:if test="${not empty bianGengJiLuArray}">
	    ${fn:length(bianGengJiLuArray)}
	   </c:if>
	   <c:if test="${empty bianGengJiLuArray}">
	        0
	   </c:if>
    </div>
    <div class="inline_div message high_risk_tips_img hidden">
        <div id="countRiskMsgDiv"></div>
    </div>
</div>
<c:if test="${not empty bianGengJiLuArray}">
<div class="info_filter">
    <div class="filter_list">
        <div class="inline_div value selected" name="全部">全部(${fn:length(bianGengJiLuArray)})</div>
		<c:if test="${not empty bianGengJiLuShowCategory and not empty bianGengJiLuShowCategory.sortedName }">
		    <c:forEach items="${bianGengJiLuShowCategory.sortedName}" var="name" varStatus="i">
		       <div class="inline_div value" name="${name}">${name}<span class="">(${bianGengJiLuShowCategory[name]})</span></div>
		    </c:forEach>
		</c:if>        
    </div>
    <div class="filter_days">
           <jsp:include page="bianGengJiLuTimeAxis.jsp"></jsp:include>
    </div>
</div>
</c:if>
<div id="pagingTable" class="info_table">
    <jsp:include page="bianGengJiLuPagingTable.jsp"></jsp:include>
</div>
