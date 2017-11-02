<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML>
<%@ include file="/page/common/base.jsp"%>
<html>
<head>

</head>

<body>
		<div class="info_title">
			<div class="inline_div title">开庭公告</div>  <div class="inline_div count"> 
				<c:if test="${empty kaiTingGongGaoPage}">0</c:if>
				<c:if test="${not empty kaiTingGongGaoPage}">${kaiTingGongGaoPage.recordSum}</c:if>
			</div>
		</div>
		<div class="info_table">
			<c:if test="${empty kaiTingGongGaoArray}">
                   暂无数据
    </c:if>
			<c:if test="${not empty kaiTingGongGaoArray}">
				<table class="first_col_table ">
					<thead>
						<tr>
							<td class="td_lable" style="width:4%;">序号</td>
							<td class="td_lable" style="width:8%;">开庭日期</td>
							<td class="td_lable" style="width:10%;">案由</td>
							<td class="td_lable" style="width:30%;">当事人</td>
							<td class="td_lable" style="width:10%;">执行法院</td>
							<td class="td_lable" style="width:10%;">审理法庭</td>
							<td class="td_lable" style="width:5%;">审理法官</td>
							<td class="td_lable" style="width:5%;">事件等级</td>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${kaiTingGongGaoArray}" var="record"  varStatus="i">
							<tr>
								<td style="text-align:center">${(kaiTingGongGaoPage.pageSize*(kaiTingGongGaoPage.currentPageNo-1))+i.index+1}</td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.kai_ting_ri_qi ne null and record.kai_ting_ri_qi !='' and record.kai_ting_ri_qi !='null'}">
						  				  ${record.kai_ting_ri_qi}
									</c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.an_you ne null and record.an_you !='' and record.an_you !='null'}">
								  	  ${record.an_you}
								    </c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.indentity ne null and record.indentity !='' and record.indentity !='null'}">
								   		 ${record.indentity}
								    </c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.fa_yuan_ming_cheng ne null and record.fa_yuan_ming_cheng !='' and record.fa_yuan_ming_cheng !='null'}">
									    ${record.fa_yuan_ming_cheng}
									</c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.shen_li_fa_ting ne null and record.shen_li_fa_ting !='' and record.shen_li_fa_ting !='null'}">
								  		  ${record.shen_li_fa_ting}
								    </c:when>
									<c:otherwise>--</c:otherwise>
									</c:choose></td>
								<td class="td_lable"><c:choose>
									<c:when test="${record.zhu_shen_fa_guan ne null and record.zhu_shen_fa_guan !='' and record.zhu_shen_fa_guan !='null'}">
									    ${record.zhu_shen_fa_guan}
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
		<div class="page_block" id="kaiTingGongGaoPage" style="display:none;"></div>
</body>
<c:if test="${not empty kaiTingGongGaoArray}">
	<script type="text/javascript">
		$(document).ready(function() {
			if (!$("#kaiTingGongGaoPage")[0]) {
				return;
			}
			if('${kaiTingGongGaoPage.recordSum}' > 0) {
				$("#kaiTingGongGaoPage").pagination(${kaiTingGongGaoPage.recordSum},{
					prev_text : "上一页",
					next_text : "下一页",
					items_per_page : ${kaiTingGongGaoPage.pageSize},
					current_page : ${kaiTingGongGaoPage.currentPageNo-1},
					num_edge_entries : 3,
					num_display_entries : 6,
					//回调 
					callback : kaiTingGongGaoPageselectCallback
				});
				$("#kaiTingGongGaoPage").css("display", "block");
			}
		});
	</script>
</c:if>
</html>
