<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  $(document).ready(function(){
       //console.log("init zhuYaoRenYuan page...");
        //console.log("curPge="+'${zhuYaoRenYuanPage.curPage}');
        //console.log("bianGengPageNav init");
	    if(!$("#jingYingYiChangPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("zhuYaoRenYuanPage.size="+'${zhuYaoRenYuanPage.size}');
	    if($("#jingYingYiChangPageNav").html()==""&&'${jingYingYiChangPage.size}'>0){
	    	 //console.log("true");
	        $("#jingYingYiChangPageNav").pagination('${jingYingYiChangPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${jingYingYiChangPage.pageSize}',
	             current_page:parseInt('${jingYingYiChangPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
	             //回调 
	             callback: jingYingYiChangPageselectCallback
	     });
	     $("#jingYingYiChangPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">经营异常</div>
    <div class="inline_div count">
	   <c:if test="${not empty jingYingYiChangArray}">
	    ${fn:length(jingYingYiChangArray)}
	   </c:if>
	   <c:if test="${empty jingYingYiChangArray}">
	        0
	   </c:if>
    </div>
    <div class="inline_div message hidden"></div>
</div>
<c:if test="${empty jingYingYiChangArray}">无信息</c:if>
<c:if test="${not empty jingYingYiChangArray and jingYingYiChangPage.pageSize>0 and jingYingYiChangPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>			    
                <td class="td_lable" style="">做出决定机关</td>
                <td class="td_lable" style="">列入经营异常原因</td>
                <td class="td_lable" style="">列入日期</td>
                <td class="td_lable" style="">移出经营异常原因</td>
                <td class="td_lable" style="">移出日期</td>
            </tr>
        </thead>
        <tbody>
		 <c:forEach items="${jingYingYiChangArray }" var="record"
		    begin="${jingYingYiChangPage.pageSize*(jingYingYiChangPage.curPage-1) }"
					end="${jingYingYiChangPage.pageSize*jingYingYiChangPage.curPage-1 }"
				varStatus="i">          
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td>
                  <c:choose>
					  <c:when test="${record.abnormal_decisioninstitution ne null and record.abnormal_decisioninstitution !='' and record.abnormal_decisioninstitution !='null'}">
					    ${record.abnormal_decisioninstitution }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.abnormal_events ne null and record.abnormal_events !='' and record.abnormal_events !='null'}">
					    ${record.abnormal_events }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.abnormal_datesin ne null and record.abnormal_datesin !='' and record.abnormal_datesin !='null'}">
					    ${record.abnormal_datesin }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.abnormal_moveoutreason ne null and record.abnormal_moveoutreason !='' and record.abnormal_moveoutreason !='null'}">
					    ${record.abnormal_moveoutreason }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.abnormal_datesout ne null and record.abnormal_datesout !='' and record.abnormal_datesout !='null'}">
					    ${record.abnormal_datesout }
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