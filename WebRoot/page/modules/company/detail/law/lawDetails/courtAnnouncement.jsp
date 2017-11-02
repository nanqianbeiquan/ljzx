<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
</head>

<body>
	<div class="info_table" id="courtDiv">
		<c:if test="${empty LawInfo or empty LawInfo.courtAnnouncementList}">
			<span style="display:block; width:100%; margin:0 auto; line-height:20px; color:#CCC;">无信息</span>
		</c:if>
		<c:if test="${not empty LawInfo and not empty LawInfo.courtAnnouncementList}">
		<table class="all_col_table data_table">
			<thead>
				<tr>
					<td class="td_lable" style="width: 6%;">序号</td>
					<td class="td_lable" style="width: 10%;">发布时间</td>
					<td class="td_lable" style="width: 20%;">公告类型</td>
					<td class="td_lable" style="width: 20%;">公告内容</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LawInfo.courtAnnouncementList}" var="sYGG"
					begin="${gongGaoPage.pageSize*(gongGaoPage.curPage-1)}"
					end="${(gongGaoPage.pageSize*gongGaoPage.curPage-1)<=0?0:(gongGaoPage.pageSize*gongGaoPage.curPage-1)}" varStatus="i">
					<tr>
						<td style="text-align:center">${i.index+1}</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${sYGG.publishDate ne null and sYGG.publishDate !='' and sYGG.publishDate !='null'}">
						    ${sYGG.publishDate}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>   
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${sYGG.announcementType ne null and sYGG.announcementType !='' and sYGG.announcementType !='null'}">
						    ${sYGG.announcementType}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>
						</td>
						<td class="gongshangtablelianjie" style="text-align:center">
						<c:choose>
						  <c:when test="${sYGG.courtName ne null and sYGG.courtName !='' and sYGG.courtName !='null'}">
						  <a href="javascript:;" class="link_td" style="cursor: pointer;text-decoration:none;" onclick="viewCourtDetails('${sYGG.courtID}')">${sYGG.courtName}</a>
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
		if (!$("#courtPagination")[0]) {
			return;
		}
		if($("#courtPagination").html()==""&&'${gongGaoPage.size}' > 0&&'${gongGaoPage.curPage}'==1) {
			$("#courtPagination").pagination(${gongGaoPage.size},{
				prev_text : "上一页",
				next_text : "下一页",
				items_per_page : ${gongGaoPage.pageSize},
				current_page : ${gongGaoPage.curPage-1},
				num_edge_entries : 3,
				num_display_entries : 5,
				//回调 
				callback : gongGaoPageselectCallback
			});
			$("#courtPagination").css("display", "block");
		}
	});
</script>
</html>
