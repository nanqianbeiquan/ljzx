<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

</head>

<body>
    <div class="info_table" id="dishonestDiv">
	    <c:if test="${empty LawInfo or empty LawInfo.dishonestInfoList}">
				<span style="display:block; width:100%; margin:0 auto; line-height:20px; color:#CCC;">无信息</span>
		</c:if>
		<c:if test="${not empty LawInfo and not empty LawInfo.dishonestInfoList}">
		<table class="all_col_table data_table">
			<thead>
				<tr>
					<td class="td_lable" style="width: 4%;">序号</td>
					<td class="td_lable" style="width: 8%;">发布时间</td>
					<td class="td_lable" style="width: 8%;">案号</td>
					<td class="td_lable" style="width: 4%;">省份</td>
					<td class="td_lable" style="width: 8%; ">执行法院</td>
					<td class="td_lable" style="width: 8%;">被执行人履行情况</td>
					<td class="td_lable" style="width: 4%;">其它</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LawInfo.dishonestInfoList}"
					begin="${shiXinPage.pageSize*(shiXinPage.curPage-1)}"
					end="${(shiXinPage.pageSize*shiXinPage.curPage-1)<=0?0:(shiXinPage.pageSize*shiXinPage.curPage-1)}" var="sxxx" varStatus="i">
					<tr>
						<td style="text-align:center">${i.index+1}</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${sxxx.publishDate ne null and sxxx.publishDate !='' and sxxx.publishDate !='null'}">
						    ${sxxx.publishDate}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>   
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${sxxx.caseNo ne null and sxxx.caseNo !='' and sxxx.caseNo !='null'}">
						    ${sxxx.caseNo}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${sxxx.province ne null and sxxx.province !='' and sxxx.province !='null'}">
						    ${sxxx.province}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${sxxx.executionCourt ne null and sxxx.executionCourt !='' and sxxx.executionCourt !='null'}">
						    ${sxxx.executionCourt}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${sxxx.performanceStatus ne null and sxxx.performanceStatus !='' and sxxx.performanceStatus !='null'}">
						    ${sxxx.performanceStatus}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td class="gongshangtablelianjie" style="text-align:center;cursor: pointer;">
						<span class="link_td" onclick="viewDishonestInfoDetails('${sxxx.caseNo}')">详情>></span></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</c:if>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		if (!$("#dishonestPagination")[0]) {
			return;
		}
		if ($("#dishonestPagination").html()==""&&'${shiXinPage.size}' > 0&&'${shiXinPage.curPage}'==1) {
			$("#dishonestPagination").pagination('${shiXinPage.size}',{
				prev_text : "上一页",
				next_text : "下一页",
				items_per_page : '${shiXinPage.pageSize}',
				current_page : '${shiXinPage.curPage-1}',
				num_edge_entries : 3,
				num_display_entries : 5,
				// 回调
				callback : shiXinPageselectCallback
			});
			$("#dishonestPagination").css("display", "block");
		}
	});
</script>
</html>
