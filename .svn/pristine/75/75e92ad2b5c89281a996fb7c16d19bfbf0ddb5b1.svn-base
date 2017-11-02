<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  $(document).ready(function(){
      
	    if(!$("#administrativePenaltyPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("zhuYaoRenYuanPage.size="+'${zhuYaoRenYuanPage.size}');
	    if($("#administrativePenaltyPageNav").html()==""&&'${administrativePenaltyPage.size}'>0){
	    	 //console.log("true");
	        $("#administrativePenaltyPageNav").pagination('${administrativePenaltyPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:parseInt('${administrativePenaltyPage.pageSize}'),
	             current_page:parseInt('${administrativePenaltyPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
	             //回调 
	             callback: administrativePenaltyPageselectCallback
	     });
	     $("#administrativePenaltyPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">行政处罚</div>
    <div class="inline_div count">
	   <c:if test="${not empty administrativePenaltyArray}">
	    ${fn:length(administrativePenaltyArray)}
	   </c:if>
	   <c:if test="${empty administrativePenaltyArray}">
	        0
	   </c:if>
    </div>
    <div class="inline_div message hidden"></div>
</div>
<c:if test="${empty administrativePenaltyArray}">无信息</c:if>
<c:if test="${not empty administrativePenaltyArray and administrativePenaltyPage.pageSize>0 and administrativePenaltyPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>			    
                <td class="td_lable" style="">作出行政处罚决定日期</td>
                <td class="td_lable" style="">作出行政处罚决定机关名称</td>
                <td class="td_lable" style="">行政处罚决定书文号</td>
                <td class="td_lable" style="">违法行为类型</td>
                <td class="td_lable" style="width:100px;">行政处罚内容</td>
            </tr>
        </thead>
        <tbody>
			<c:forEach items="${administrativePenaltyArray }" var="record"
			    begin="${administrativePenaltyPage.pageSize*(administrativePenaltyPage.curPage-1) }"
				end="${administrativePenaltyPage.pageSize*administrativePenaltyPage.curPage-1 }"
				varStatus="i">        
            <tr>
                <td>${i.index+1 }</td>
                <td>
                  <c:choose>
					  <c:when test="${record.penalty_decisiondate ne null and record.penalty_decisiondate !='' and record.penalty_decisiondate !='null'}">
					    ${record.penalty_decisiondate }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.penalty_decisioninsititution ne null and record.penalty_decisioninsititution !='' and record.penalty_decisioninsititution !='null'}">
					    ${record.penalty_decisioninsititution }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.penalty_code ne null and record.penalty_code !='' and record.penalty_code !='null'}">
					    ${record.penalty_code }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.penalty_illegaltype ne null and record.penalty_illegaltype !='' and record.penalty_illegaltype !='null'}">
					    <div class="long_value">${record.penalty_illegaltype }</div>
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.penalty_decisioncontent ne null and record.penalty_decisioncontent !='' and record.penalty_decisioncontent !='null'}">
					    ${record.penalty_decisioncontent }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
            </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
</c:if>
</body>
</html>