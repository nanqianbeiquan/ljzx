<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<title>My JSP 'personSubjectToEnforcement.jsp' starting page</title>

</head>

<body>
	<div class="info_table" id="personDiv">
	<c:if test="${empty LawInfo or empty LawInfo.executedPersonList}">
			<span style="display:block; width:100%; margin:0 auto; line-height:20px; color:#CCC;">无信息</span>
		</c:if>
	<c:if test="${not empty LawInfo and not empty LawInfo.executedPersonList}">
		<table class="all_col_table ">
			<thead>
				<tr>
					<td class="td_lable" style="width:4%">序号</td>
					<td class="td_lable" style="width:8%">立案时间</td>
					<td class="td_lable" style="width:15%">案号</td>
					<td class="td_lable" style="width:15%">姓名/名称</td>
					<td class="td_lable" style="width:6%">执行标的</td>
					<td class="td_lable" style="width:15%">执行法院</td>
					<td class="td_lable" style="width:8%">案件状态</td>
					<td class="td_lable" style="width:6%">关注次数</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LawInfo.executedPersonList}" var="bzxr"
					begin="${personPage.pageSize*(personPage.curPage-1)}"
					end="${personPage.pageSize*personPage.curPage-1}" varStatus="i">
					<tr>
						<td style="text-align:center">${i.index+1}</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${bzxr.filingTime ne null and bzxr.filingTime !='' and bzxr.filingTime !='null'}">
						    ${bzxr.filingTime}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>   
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${bzxr.caseNo ne null and bzxr.caseNo !='' and bzxr.caseNo !='null'}">
						    ${bzxr.caseNo}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${bzxr.executorName ne null and bzxr.executorName !='' and bzxr.executorName !='null'}">
						    ${bzxr.executorName}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${bzxr.objectOfExecution ne null and bzxr.objectOfExecution !='' and bzxr.objectOfExecution !='null'}">
						    ${bzxr.objectOfExecution}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${bzxr.executionCourt ne null and bzxr.executionCourt !='' and bzxr.executionCourt !='null'}">
						    ${bzxr.executionCourt}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${bzxr.caseStatus ne null and bzxr.caseStatus !='' and bzxr.caseStatus !='null'}">
						    ${bzxr.caseStatus}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${bzxr.attentionFrequency ne null and bzxr.attentionFrequency !='' and bzxr.attentionFrequency !='null'}">
						    ${bzxr.attentionFrequency}
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
	$(document).ready(function() {
		if (!$("#personPagination")[0]) {
			return;
		}
		if ($("#personPagination").html()==""&&'${personPage.size}' > 0&&'${personPage.curPage}'==1) {
			$("#personPagination").pagination('${personPage.size}', {
				prev_text : "上一页",
				next_text : "下一页",
				items_per_page : '${personPage.pageSize}',
				current_page : '${personPage.curPage-1}',
				num_edge_entries : 3,
				num_display_entries : 5,
				// 回调
				callback : personPageselectCallback
			});
			$("#personPagination").css("display", "block");
		}
	});
</script>
</html>
