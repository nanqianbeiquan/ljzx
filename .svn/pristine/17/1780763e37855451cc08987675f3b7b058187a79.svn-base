<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>
<%@ include file="/page/common/header.jsp"%>
<div class="info_block no_line_info_block">
	<div class="info_title">
		<div class="inline_div title">媒体资讯</div>
		<div class="inline_div count">${mediaPage.recordSum ge 0 ? mediaPage.recordSum:0 }</div>
		<div class="inline_div message hidden"></div>
	</div>
	<div class="info_table"></div>
	<div class="info_list">
	<input type="hidden" id="mediaTotalNum" value="${mediaPage.recordSum}" />
	<input type="hidden" id="companyName" value="${companyName}"/>
	<input type="hidden" id="opinionType" value="${opinionType}"/>
		<c:if test="${empty mediaPage  or empty mediaPage.results}">无记录！</c:if>
		<c:if test="${not empty mediaPage and not empty mediaPage.results}">
		<c:forEach items="${mediaPage.results}" var="searchResult" varStatus="status">
			<a class="info_line">
				<div class="title line_txt" onclick="getOpinionDetailById('${searchResult.opinionKey}','${searchResult.newsId}','${companyName}','norisk')">
					${searchResult.title}
				</div>
				<div class="sub_title">
					<div class="relative_right_block">${searchResult.pubtime}</div>
					来源：${searchResult.source}
				</div>
			</a>
		</c:forEach>
		</c:if>  
	</div>
</div>