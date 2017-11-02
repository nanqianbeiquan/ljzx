   <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/page/common/base.jsp"%>
<script type="text/javascript">
  $(document).ready(function(){
        //console.log("init shareSIMPAWN page...");
        //console.log("curPge="+'${shareSIMPAWNPage.curPage}');
        //console.log("shareSIMPAWNPageNav init");
	    if(!$("#shareSIMPAWNPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("shareSIMPAWNPage.size="+'${shareSIMPAWNPage.size}');
	    if($("#shareSIMPAWNPageNav").html()==""&&'${shareSIMPAWNPage.size}'>0){
	    	 //console.log("true");
	        $("#shareSIMPAWNPageNav").pagination('${shareSIMPAWNPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${shareSIMPAWNPage.pageSize}',
	             current_page:parseInt('${shareSIMPAWNPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
// 	             回调 
	             callback: shareSIMPAWNPageselectCallback
	     });
	     $("#shareSIMPAWNPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">股权出质</div>
    <div class="inline_div count">
	   <c:if test="${not empty shareSIMPAWNArray}">
	    ${fn:length(shareSIMPAWNArray)}
	   </c:if>
	   <c:if test="${empty shareSIMPAWNArray}">
	        0
	   </c:if>
    </div>
    <div class="inline_div message hidden">
    </div>
</div>
<c:if test="${empty shareSIMPAWNArray}">无信息</c:if>
<c:if test="${not empty shareSIMPAWNArray and shareSIMPAWNPage.pageSize>0 and shareSIMPAWNPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="width:5%">序号</td>
                <td class="td_lable" style="width:20%">登记编号</td>
                <td class="td_lable" style="width:10%">出质人</td>
                <td class="td_lable" style="width:15%">证照/证件号码</td>
                <td class="td_lable" style="width:10%">出质股权数额</td>
                <td class="td_lable" style="width:10%">质权人</td>
                <td class="td_lable" style="width:10%">登记日期</td>
                <td class="td_lable" style="width:10%">公示日期</td>
                <td class="td_lable" style="width:5%">变化情况</td>
                <td class="td_lable" style="width:5%;">状态</td>
            </tr>
        </thead>
        <tbody>
			<c:forEach items="${shareSIMPAWNArray }" var="record"
			    begin="${shareSIMPAWNPage.pageSize*(shareSIMPAWNPage.curPage-1) }"
					end="${shareSIMPAWNPage.pageSize*shareSIMPAWNPage.curPage-1 }"
				varStatus="i">        
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_registrationno ne null and record.equitypledge_registrationno !='' and record.equitypledge_registrationno !='null'}">
					    ${record.equitypledge_registrationno }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_pledgor ne null and record.equitypledge_pledgor !='' and record.equitypledge_pledgor !='null'}">
					    ${record.equitypledge_pledgor }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_pledgorid ne null and record.equitypledge_pledgorid !='' and record.equitypledge_pledgorid !='null'}">
					    ${record.equitypledge_pledgorid }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_amount ne null and record.equitypledge_amount !='' and record.equitypledge_amount !='null'}">
					    ${record.equitypledge_amount }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_pawnee ne null and record.equitypledge_pawnee !='' and record.equitypledge_pawnee !='null'}">
					    ${record.equitypledge_pawnee }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_registrationdate ne null and record.equitypledge_registrationdate !='' and record.equitypledge_registrationdate !='null'}">
					    ${record.equitypledge_registrationdate }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td style="width:10%;word-break:break-all;">
                  <c:choose>
					  <c:when test="${record.equitypledge_announcedate ne null and record.equitypledge_announcedate !='' and record.equitypledge_announcedate !='null'}">
					    ${record.equitypledge_announcedate }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_change ne null and record.equitypledge_change !='' and record.equitypledge_change !='null'}">
					    ${record.equitypledge_change }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.equitypledge_status ne null and record.equitypledge_status !='' and record.equitypledge_status !='null'}">
					    ${record.equitypledge_status }
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