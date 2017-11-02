   <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  $(document).ready(function(){
        //console.log("init entInv page...");
        //console.log("curPge="+'${entInvPage.curPage}');
        //console.log("entInvPageNav init");
	    if(!$("#entInvPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("entInvPage.size="+'${entInvPage.size}');
	    if($("#entInvPageNav").html()==""&&'${entInvPage.size}'>0){
	    	 //console.log("true");
	        $("#entInvPageNav").pagination('${entInvPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${entInvPage.pageSize}',
	             current_page:parseInt('${entInvPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
// 	             回调 
	             callback: entInvPageselectCallback
	     });
	     $("#entInvPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">对外投资</div>
    <div class="inline_div count">
	   <c:if test="${not empty entInvArray}">
	    ${fn:length(entInvArray)}
	   </c:if>
	   <c:if test="${empty entInvArray}">
	        0
	   </c:if> 
    </div>
    <div class="inline_div message hidden">
       
    </div>
</div>
<c:if test="${empty entInvArray}">无信息</c:if>
<c:if test="${not empty entInvArray and not empty entInvPage and entInvPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>
                <td class="td_lable" style="">企业名称</td>
                <td class="td_lable" style="">工商注册号</td>
                <td class="td_lable" style="">企业类型</td>
                <td class="td_lable" style="">企业状态</td>
                <td class="td_lable" style="">注册资本(万元)</td>
                <td class="td_lable" style="">注册资本币种</td>
                <td class="td_lable" style="">出资方式</td>
                <td class="td_lable" style="">认缴出资(万元)</td>
                <td class="td_lable" style="">认缴资本币种</td>
                <td class="td_lable" style="">出资比例</td>
            </tr>
        </thead>
        <tbody>
			<c:forEach items="${entInvArray }" var="record"
			    begin="${entInvPage.pageSize*(entInvPage.curPage-1) }"
					end="${entInvPage.pageSize*entInvPage.curPage-1 }"
				varStatus="i">        
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td>
                  <c:choose>
					  <c:when test="${record.entname ne null and record.entname !='' and record.entname !='null'}">
					    ${record.entname }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.regno ne null and record.regno !='' and record.regno !='null'}">
					    ${record.regno }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.enttype ne null and record.enttype !='' and record.enttype !='null'}">
					    ${record.enttype }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.entstatus ne null and record.entstatus !='' and record.entstatus !='null'}">
					    ${record.entstatus }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.regcap ne null and record.regcap !='' and record.regcap !='null'}">
					    ${record.regcap }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.regcapcur ne null and record.regcapcur !='' and record.regcapcur !='null'}">
					    ${record.regcapcur }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.conform ne null and record.conform !='' and record.conform !='null'}">
					    ${record.conform }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.subconam ne null and record.subconam !='' and record.subconam !='null'}">
					    ${record.subconam }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.congrocur ne null and record.congrocur !='' and record.congrocur !='null'}">
					    ${record.congrocur }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.fundedratio ne null and record.fundedratio !='' and record.fundedratio !='null'}">
					    ${record.fundedratio }
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