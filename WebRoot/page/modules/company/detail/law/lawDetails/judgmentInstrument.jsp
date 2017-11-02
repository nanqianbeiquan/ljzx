<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/page/common/base.jsp"%>
<html>
<head>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/pagination.css?${appVersion }"  />
   <script type="text/javascript" src="${ctx }/js/jquery/jquery.pagination.js?${appVersion }"></script>
   <script type="text/javascript" src="${ctx }/js/modules/company/detail/law/law.js?${appVersion }"></script>	
<title>法院判决</title>
</head>
<body>
<div class="info_table" id="legalDiv">
		<c:if test="${empty LawInfo or empty LawInfo.judgmentInstrumentList}">
			<span style="display:block; width:100%; margin:0 auto; line-height:20px; color:#CCC;">无信息</span>
		</c:if>
	<c:if test="${not empty LawInfo and not empty LawInfo.judgmentInstrumentList}">
	<table class="all_col_table data_table">
		<thead>
			<tr>
				<td class="td_lable" style="width: 4%;">序号</td>
				<td class="td_lable" style="width: 8%;">判决时间</td>
				<td class="td_lable" style="width: 10%;">身份</td>
				<td class="td_lable" style="width: 40%;">判决结果</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${LawInfo.judgmentInstrumentList}" var="fypj"
					begin="${LegalInstrumentPage.pageSize*(LegalInstrumentPage.curPage-1)}"
					end="${LegalInstrumentPage.pageSize*LegalInstrumentPage.curPage-1}" varStatus="i">
				<tr>
					<td style="text-align:center">${i.index+1}</td>
					<td>
					  <c:choose>
					  <c:when test="${fypj.judgmentTime ne null and fypj.judgmentTime !='' and fypj.judgmentTime !='null'}">
					    ${fypj.judgmentTime}
					  </c:when> 
                      <c:otherwise>--</c:otherwise></c:choose> 
					</td>
					<td>
					  <c:choose>
					  <c:when test="${fypj.relatedPosition ne null and fypj.relatedPosition !='' and fypj.relatedPosition !='null'}">
					    ${fypj.relatedPosition}
					  </c:when> 
                      <c:otherwise>--</c:otherwise></c:choose> 
					</td>
					<td class="lawDetails " style="text-align: left;">
					  <c:choose>
					  <c:when test="${fypj.title ne null and fypj.title !='' and fypj.title !='null'}">
					   <div class="link_td long_value" style="cursor: pointer;text-decoration:none;" onclick="viewInstrumentDetail('${fypj.judgmentID}')">${fypj.title}</div>
					  </c:when> 
                      <c:otherwise>--</c:otherwise></c:choose> 
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	</c:if>
</div>
</body>
<script type="text/javascript">
	$(document).ready(
		function() {
			if (!$("#legalPagination")[0]) {
				//console.log("false");
				return;
			}
			if ($("#legalPagination").html()==""&&'${LegalInstrumentPage.size}' > 0&&'${LegalInstrumentPage.curPage}'==1) {
				$("#legalPagination").pagination('${LegalInstrumentPage.size}',{
					prev_text : "上一页",
					next_text : "下一页",
					items_per_page : '${LegalInstrumentPage.pageSize}',
					current_page : '${LegalInstrumentPage.curPage-1}',
					num_edge_entries : 3,
					num_display_entries : 5,
					// 回调
					callback : LegalInstrumentPageselectCallback
				});
				$("#legalPagination").css("display", "block");
			}
	});
</script>
</html>