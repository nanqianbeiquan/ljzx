<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/company.css?${appVersion }">
</head>

<body>
 
		<div class="info_block">
			<div class="info_title">
				<div class="inline_div title">裁判文书</div>
				<div class="inline_div count">
					<c:if test="${not empty LawInfo and not empty LawInfo.judgmentInstrumentList}">
				    ${fn:length(LawInfo.judgmentInstrumentList)}
				    </c:if> <c:if test="${empty LawInfo or empty LawInfo.judgmentInstrumentList}">
				        0
				    </c:if>
				</div>
				<div class="inline_div message hidden"></div>
			</div>
			<jsp:include page="/page/modules/company/detail/law/lawDetails/judgmentInstrument.jsp"></jsp:include>
			<div class="page_block" id="legalPagination" style="display:none;"></div>
		</div>
		<div class="info_block">
			<div class="info_title">
				<div class="inline_div title">被执行人</div>
				<div class="inline_div count">
					<c:if test="${not empty LawInfo and not empty LawInfo.executedPersonList}">
		   			 ${fn:length(LawInfo.executedPersonList)}
		 			 </c:if> <c:if test="${empty LawInfo or empty LawInfo.executedPersonList}">
		      		  0
		 			 </c:if>
				</div>
				<div class="inline_div message hidden"></div>
			</div>
			<jsp:include page="/page/modules/company/detail/law/lawDetails/personSubjectToEnforcement.jsp"></jsp:include>
			<div class="page_block" id="personPagination" style="display:none;"></div>
		</div>
		<div class="info_block">
			<div class="info_title">
				<div class="inline_div title">失信信息</div>
				<div class="inline_div count">
					<c:if test="${not empty LawInfo and not empty LawInfo.dishonestInfoList}">
		   			 ${fn:length(LawInfo.dishonestInfoList)}
		 			 </c:if> <c:if test="${empty LawInfo or empty LawInfo.dishonestInfoList}">
		      		  0
		 			 </c:if>
				</div>
				<div class="inline_div message hidden"></div>
			</div>
			<jsp:include page="/page/modules/company/detail/law/lawDetails/dishonestInfo.jsp"></jsp:include>
			<div class="page_block" id="dishonestPagination" style="display:none;"></div>
		</div>
		<div class="info_block">
			<div class="info_title">
				<div class="inline_div title">开庭公告</div>
				<div class="inline_div count">
					<c:if test="${not empty LawInfo and not empty LawInfo.kaiTingGongGaoList}">
		   			 ${fn:length(LawInfo.kaiTingGongGaoList)}
		 			 </c:if> <c:if test="${empty LawInfo or empty LawInfo.kaiTingGongGaoList}">
		      		  0
		 			 </c:if>
				</div>
				<div class="inline_div message hidden"></div>
			</div>
			<jsp:include page="/page/modules/company/detail/law/lawDetails/noticeOfTrail.jsp"></jsp:include>
			<div class="page_block" id="noticePagination" style="display:none;"></div>
		</div>
		<div class="info_block">
			<div class="info_title">
				<div class="inline_div title">法院公告</div>
				<div class="inline_div count">
					<c:if test="${not empty LawInfo and not empty LawInfo.courtAnnouncementList}">
		   			 ${fn:length(LawInfo.courtAnnouncementList)}
		 			 </c:if> <c:if test="${empty LawInfo or empty LawInfo.courtAnnouncementList}">
		      		  0
		 			 </c:if>
				</div>
				<div class="inline_div message hidden"></div>
			</div>
			<jsp:include page="/page/modules/company/detail/law/lawDetails/courtAnnouncement.jsp"></jsp:include>
			<div class="page_block" id="courtPagination" style="display:none;"></div>
		</div>
</body>
</html>
