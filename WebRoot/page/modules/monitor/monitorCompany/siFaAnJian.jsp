<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<%@ include file="/page/common/base.jsp"%>
<html>
  <head>
    
  </head>
  
  <body>
    <div class="info_title">
        <div class="inline_div title">裁判文书</div>
        <div class="inline_div count">
            <c:if test="${empty siFaAnJianPage}">0</c:if>
            <c:if test="${not empty siFaAnJianPage}"> ${siFaAnJianPage.recordSum}</c:if>
        </div>
    </div>
    <c:if test="${empty siFaAnJianArray}">
                   暂无数据
    </c:if>
      <div class="info_table">
      <c:if test="${not empty siFaAnJianArray}">
           <table class="first_col_table data_table">
               <thead>
                   <tr>
                       <td class="td_lable" style="width:4%;">序号</td>
                       <td class="td_lable" style="width:8%;">判决时间</td>
                       <td class="td_lable" style="width:20%;">当事人</td>
                       <td class="td_lable" style="width:20%;">标题</td>
                       <td class="td_lable" style="width:8%;">文书类型</td>
                       <td class="td_lable" style="width:6%;">详情</td>
                       <td class="td_lable" style="width:8%;">事件等级</td>
                   </tr>
               </thead>
               <tbody>
                 <c:forEach items="${siFaAnJianArray}" var="record"  varStatus="i">
                 <tr>
	                 <td style="text-align:center"> ${(siFaAnJianPage.pageSize*(siFaAnJianPage.currentPageNo-1))+i.index+1}</td>
					 <td class="td_lable"><c:choose>
							<c:when test="${record.caipanriqi ne null and record.caipanriqi !='' and record.caipanriqi !='null'}">
										  ${record.caipanriqi}
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
							<c:when test="${record.anjianmingcheng ne null and record.anjianmingcheng !='' and record.anjianmingcheng !='null'}">
										  ${record.anjianmingcheng}
							</c:when>
							<c:otherwise>--</c:otherwise>
							</c:choose></td>
					 <td class="td_lable"><c:choose>
							<c:when test="${record.wenshuleixing ne null and record.wenshuleixing !='' and record.wenshuleixing !='null'}">
										  ${record.wenshuleixing}
							</c:when>
							<c:otherwise>--</c:otherwise>
							</c:choose></td>
					 <td> <div class="link_td" style="cursor: pointer;text-decoration:none;" onclick="viewInstrumentDetail('${record.key}')">详情</div></td>
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
       <div class="page_block" id="siFaAnJianPage" style="display: none;"></div>
  </body>
  <c:if test="${not empty siFaAnJianArray  }">
  <script type="text/javascript">
	$(document).ready(function() {
		if (!$("#siFaAnJianPage")[0]) {
			return;
		}
		if('${siFaAnJianPage.recordSum}' > 0) {
			$("#siFaAnJianPage").pagination(${siFaAnJianPage.recordSum},{
				prev_text : "上一页",
				next_text : "下一页",
				items_per_page : ${siFaAnJianPage.pageSize},
				current_page : ${siFaAnJianPage.currentPageNo-1},
				num_edge_entries : 3,
				num_display_entries : 6,
				//回调 
				callback : siFaAnJianPageselectCallback
			});
			$("#siFaAnJianPage").css("display", "block");
		}
	});
</script>
</c:if>
</html>
