<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<%@ include file="/page/common/base.jsp"%>
</head>

<body>
	<div class="info_title">
		<div class="inline_div title">失信信息</div>
		<div class="inline_div count">
			<c:if test="${empty dishonestInfoArray}">0</c:if>
			<c:if test="${not empty dishonestInfoArray}">${dishonestInfoPage.recordSum}</c:if>
		</div>
	</div>
	<div class="info_table">
		<c:if test="${empty dishonestInfoArray}">
	    	 暂无数据
	    </c:if>
		<c:if test="${not empty dishonestInfoArray}">
			<table class="first_col_table data_table">
				<thead>
					<tr>
						<td class="td_lable" style="width: 4%;">序号</td>
						<td class="td_lable" style="width: 8%;">发布时间</td>
						<td class="td_lable" style="width: 8%;">案号</td>
						<td class="td_lable" style="width: 4%;">省份</td>
						<td class="td_lable" style="width: 8%; ">执行法院</td>
						<td class="td_lable" style="width: 10%;">被执行人履行情况</td>
						<td class="td_lable" style="width: 6%;">详情</td>
						<td class="td_lable" style="width: 6%;">事件等级</td>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${dishonestInfoArray}" var="record"  varStatus="i">
							<tr>
								<td style="text-align:center">${(dishonestInfoPage.pageSize*(dishonestInfoPage.currentPageNo-1))+i.index+1}</td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.sj ne null and record.sj !='' and record.sj !='null'}">
						  				  ${record.sj}
									</c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.ah ne null and record.ah !='' and record.ah !='null'}">
								  	  ${record.ah}
								    </c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.sf ne null and record.sf !='' and record.sf !='null'}">
								  	  ${record.sf}
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
									<c:when test="${record.lxqk ne null and record.lxqk !='' and record.lxqk !='null'}">
								  	  ${record.lxqk}
								    </c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td><div class="link_td" style="cursor: pointer;text-decoration:none;" onclick="viewDishonestDetail('${record.key}')">详情</div></td>
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
	<div class="page_block" id="dishonestPagination" style="display:none;"></div>
</body>
<c:if test="${not empty dishonestInfoArray}">
	<script type="text/javascript">
		$(document).ready(
			function() {
				if (!$("#dishonestPagination")[0]) {
					return;
				}
				if ('${dishonestInfoPage.recordSum}'> 0) {
					$("#dishonestPagination").pagination('${dishonestInfoPage.recordSum}',
							{
								prev_text : "上一页",
								next_text : "下一页",
								items_per_page : ${dishonestInfoPage.pageSize},
								current_page : ${dishonestInfoPage.currentPageNo-1},
								num_edge_entries : 3,
								num_display_entries : 6,
								// 回调
								callback : dishonestInfoSelectCallback
							});
					$("#dishonestPagination").css("display", "block");
				}
		});
	</script>
</c:if>
</html>
