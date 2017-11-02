   <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  $(document).ready(function(){
        //console.log("init fiLiation page...");
        //console.log("curPge="+'${fiLiation.curPage}');
        //console.log("fiLiationPageNav init");
	    if(!$("#fiLiationPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("fiLiationPage.size="+'${fiLiationPage.size}');
	    if($("#fiLiationPageNav").html()==""&&'${fiLiationPage.size}'>0){
	    	 //console.log("true");
	        $("#fiLiationPageNav").pagination('${fiLiationPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${fiLiationPage.pageSize}',
	             current_page:parseInt('${fiLiationPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
// 	             回调 
	             callback: fiLiationPageselectCallback
	     });
	     $("#fiLiationPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">分支机构</div>
    <div class="inline_div count">
	  <c:if test="${not empty fiLiationArray}">
	    ${fn:length(fiLiationArray)}
	  </c:if>
	  <c:if test="${empty fiLiationArray}">
	        0
	  </c:if>
    </div>
    <div class="inline_div message hidden"></div>
</div>
<c:if test="${empty fiLiationArray}">无信息</c:if>
<c:if test="${not empty fiLiationArray and fiLiationPage.pageSize>0 and fiLiationPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>
                <td class="td_lable" style="">名称</td>
                <td class="td_lable" style="">负责人</td>
                <td class="td_lable" style="">注册号</td>
            </tr>
        </thead>
        <tbody>
		 <c:forEach items="${fiLiationArray }" var="record"
		    begin="${fiLiationPage.pageSize*(fiLiationPage.curPage-1) }"
					end="${fiLiationPage.pageSize*fiLiationPage.curPage-1 }"
				varStatus="i">        
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td>
                  <c:choose>
					  <c:when test="${record.branch_registrationname ne null and record.branch_registrationname !='' and record.branch_registrationname !='null'}">
					    ${record.branch_registrationname }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.brprincipal ne null and record.brprincipal !='' and record.brprincipal !='null'}">
					    ${record.brprincipal }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.branch_registrationno ne null and record.branch_registrationno !='' and record.branch_registrationno !='null'}">
					    ${record.branch_registrationno }
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