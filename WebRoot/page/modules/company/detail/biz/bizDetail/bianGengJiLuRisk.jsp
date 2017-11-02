<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE style PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <script type="text/javascript" src="/ljzd/js/application/biz/changeRecord.js"></script> -->
<script type="text/javascript">
  $(document).ready(function(){
      //console.log("init bianGengJiLuRisk page...");
       if('${ not empty changeRecordRiskPage}'=="true"&&'${changeRecordRiskPage.recordSum}'>0){
	    	 //console.log("true");
	        $("#bianGengRiskPageNav").pagination('${changeRecordRiskPage.recordSum}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${changeRecordRiskPage.pageSize}',
	             current_page:'${changeRecordRiskPage.currentPageNo-1}',
	             num_edge_entries: 3,
	             num_display_entries: 5,
	             //回调 
	             callback: bianGengRiskPageselectCallback
	     });
	     $("#bianGengRiskPageNav").css("display","block");
	    }
   });
</script>

</head>
<body>
<c:if test="${ empty changeRecordRiskPage and  empty changeRecordRiskPage.results}">
无数据
</c:if>

<c:if test="${not empty changeRecordRiskPage and not empty changeRecordRiskPage.results}">
	<table class="gongshangtable" cellpadding="0" cellspacing="0">
	<tr>
<!-- 		<td class="td_lableshuzi" style="text-align:center; color:#9a9a9a;">序号</td> -->
		<td class="td_lable" style="text-align:center; color:#9a9a9a;">变更日期</td>
		<td class="td_lable" style="text-align:center; color:#9a9a9a;">变更事项</td>
		<td class="td_lable" style="text-align:center; color:#9a9a9a;">变更内容</td>
		<td class="td_lable" style="text-align:center; color:#9a9a9a;">风险情况</td>
		<td class="td_lable" style="text-align:center; color:#9a9a9a;">更新日期</td>
		<td class="td_lable" style="text-align:center; color:#9a9a9a;">操作</td>
	</tr>
	<c:forEach items="${changeRecordRiskPage.results }" var="record"
			varStatus="i">
		<tr>
<!-- 			<td style="text-align:center"><c:out value="${i.index+1}"></c:out></td> -->
			<td style="text-align:center">
			   <c:out value="${record.changeDate }"></c:out>
			</td>
			<td style="text-align:center">
			   <c:out value="${record.category }"></c:out>
			<td style="text-align:center">
			   <c:out value="${record.content }" escapeXml="false"></c:out>
			</td>
			<td>
			   <c:if test="${not empty showMsgMap }">
			       <c:out value="${showMsgMap[record.riskId]}"></c:out>
			   </c:if>
			</td>
			<td style="text-align:center">
			    <fmt:formatDate value="${record.updateDate }" pattern="yyyy-MM-dd"/>
			</td>
			<td style="text-align:center" class="gongshangtablelianjie">
			   <c:if test="${record.changeDate ne '近期' }">
			      <a onClick="updateChangeRecordRisk('${record.category }','${record.changeDate }','${record.content }');">更新</a>
			   </c:if>
			</td>
		</tr>
	   </c:forEach>
	</table> 
</c:if>
</body>
</html>