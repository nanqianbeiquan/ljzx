<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


</head>

<body>
    <div class="info_table" id="noticeDiv">
    	<c:if test="${empty LawInfo or empty LawInfo.kaiTingGongGaoList}">
			<span style="display:block; width:100%; margin:0 auto; line-height:20px; color:#CCC;">无信息</span>
		</c:if>
	<c:if test="${not empty LawInfo and not empty LawInfo.kaiTingGongGaoList}">
		<table class="all_col_table ">
			<thead>
				<tr>
					<td class="td_lable" style="width:4%;">序号</td>
					<td class="td_lable" style="width:8%;">开庭日期</td>
					<td class="td_lable" style="width:10%;">案由</td>
					<td class="td_lable" style="width:30%;">当事人</td>
					<td class="td_lable" style="width:10%;">法院</td>
					<td class="td_lable" style="width:10%;">审理法庭</td>
					<td class="td_lable" style="width:5%;">审理法官</td>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${LawInfo.kaiTingGongGaoList}" var="ktgg"
					begin="${kaiTingGongGaoPage.pageSize*(kaiTingGongGaoPage.curPage-1)}"
					end="${(kaiTingGongGaoPage.pageSize*kaiTingGongGaoPage.curPage-1)<=0?0:(kaiTingGongGaoPage.pageSize*kaiTingGongGaoPage.curPage-1)}"
					varStatus="i">
					<tr>
						<td style="text-align:center">${i.index+1}</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${ktgg.publishDate ne null and ktgg.publishDate !='' and ktgg.publishDate !='null'}">
						    ${ktgg.publishDate}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>   
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${ktgg.causeAction ne null and ktgg.causeAction !='' and ktgg.causeAction !='null'}">
						    ${ktgg.causeAction}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>  
	                    </td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${ktgg.party ne null and ktgg.party !='' and ktgg.party !='null'}">
						    ${ktgg.party}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>  
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${ktgg.courtName ne null and ktgg.courtName !='' and ktgg.courtName !='null'}">
						    ${ktgg.courtName}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose>  
						</td>
						<td style="text-align:center">
						  <c:choose>
						  <c:when test="${ktgg.hearCourt ne null and ktgg.hearCourt !='' and ktgg.hearCourt !='null'}">
						    ${ktgg.hearCourt}
						  </c:when> 
	                      <c:otherwise>--</c:otherwise></c:choose> 
						</td>
						<td style="text-align:center">
						 <c:choose>
						  <c:when test="${ktgg.hearJudge ne null and ktgg.hearJudge !='' and ktgg.hearJudge !='null'}">
						    ${ktgg.hearJudge}
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
			if (!$("#noticePagination")[0]) {
				return;
			}
			if ($("#noticePagination").html()==""&&'${kaiTingGongGaoPage.size}' > 0&&'${kaiTingGongGaoPage.curPage}'==1) {
				$("#noticePagination").pagination('${kaiTingGongGaoPage.size}',{
					prev_text : "上一页",
					next_text : "下一页",
					items_per_page : '${kaiTingGongGaoPage.pageSize}',
					current_page : '${kaiTingGongGaoPage.curPage-1}',
					num_edge_entries : 3,
					num_display_entries : 5,
					// 回调
					callback : kaiTingPageselectCallback
				});
				$("#noticePagination").css("display", "block");
			}
	});
</script>
</html>
