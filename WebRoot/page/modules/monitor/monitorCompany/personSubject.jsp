<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<%@ include file="/page/common/base.jsp"%>
<html>
<head>
<title>被执行人</title>
</head>

<body>
	<div class="info_title">
		<div class="inline_div title">被执行人</div>
		<div class="inline_div count"> <c:if test="${empty personSubjectArray}">0</c:if>
			 <c:if test="${not empty personSubjectArray}">${personSubjectPage.recordSum}</c:if>
			</div>
	</div>
	<div class="info_table">
		<c:if test="${empty personSubjectArray}">
	                   暂无数据
	    </c:if>
		<c:if test="${not empty personSubjectArray}">
			<table class="first_col_table ">
				<thead>
					<tr>
					<td class="td_lable" style="width:4%">序号</td>
					<td class="td_lable" style="width:8%">立案时间</td>
					<td class="td_lable" style="width:15%">案号</td>
					<td class="td_lable" style="width:15%">姓名/名称</td>
					<td class="td_lable" style="width:6%">执行标的</td>
					<td class="td_lable" style="width:15%">执行法院</td>
					<!-- <td class="td_lable" style="width:8%">案件状态</td>
					<td class="td_lable" style="width:6%">关注次数</td> -->
					<td class="td_lable" style="width:6%">事件等级</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${personSubjectArray}" var="record"  varStatus="i">
						<tr>
							<td style="text-align:center">${(personSubjectPage.pageSize*(personSubjectPage.currentPageNo-1))+i.index+1}</td>
							<td class="td_lable"><c:choose>
								<c:when test="${record.sj ne null and record.sj !='' and record.sj !='null'}">
					  				  ${record.sj}
								</c:when>
								<c:otherwise>--</c:otherwise>
								</c:choose></td>
								<td class="td_lable"><c:choose>
								<c:when test="${record.ah ne null and record.ah !='' and record.sj !='ah'}">
					  				  ${record.ah}
								</c:when>
								<c:otherwise>--</c:otherwise>
								</c:choose></td>
								<td class="td_lable"><c:choose>
								<c:when test="${record.mc ne null and record.mc !='' and record.mc !='null'}">
					  				  ${record.mc}
								</c:when>
								<c:otherwise>--</c:otherwise>
								</c:choose></td>
								<td class="td_lable"><c:choose>
								<c:when test="${record.zxbd ne null and record.zxbd !='' and record.zxbd !='null'}">
					  				  ${record.zxbd}
								</c:when>
								<c:otherwise>--</c:otherwise>
								</c:choose></td>
								<td class="td_lable"><c:choose>
								<c:when test="${record.zxfy ne null and record.zxfy !='' and record.zxfy !='null'}">
					  				  ${record.zxfy}
								</c:when>
								<c:otherwise>--</c:otherwise>
								</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.eventLevel ne null and record.eventLevel !='' and record.eventLevel !='null'}">
										  <c:choose>
										    <c:when test="${record.eventLevel eq '1'}">
				                               <div class="event_level low_risk_back inline_div">一般</div>
				                            </c:when>
				                            <c:when test="${record.eventLevel eq '2'}">
				                               <div class="event_level middle_risk_back inline_div">异常</div>
				                            </c:when>
				                            <c:when test="${record.eventLevel eq '3'}">
				                               <div class="event_level hight_risk_back inline_div">严重</div>
				                            </c:when>
				                          </c:choose>
									</c:when>
									<c:otherwise>--</c:otherwise>
								</c:choose></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
	</div>
	<div class="page_block" id="personPagination" style="display:none;"></div>
</body>
<c:if test="${not empty personSubjectArray}">
<script type="text/javascript">
	$(document).ready(
		function() {
			if (!$("#personPagination")[0]) {
				return;
			}
			if ('${personSubjectPage.recordSum}' > 0) {
				$("#personPagination").pagination('${personSubjectPage.recordSum}',{
							prev_text : "上一页",
							next_text : "下一页",
							items_per_page : '${personSubjectPage.pageSize}',
							current_page : '${personSubjectPage.currentPageNo-1}',
							num_edge_entries : 3,
							num_display_entries : 6,
							// 回调
							callback : personPageselectCallback
						});
				$("#personPagination").css("display", "block");
			}
	});
</script>
</c:if>
</html>
