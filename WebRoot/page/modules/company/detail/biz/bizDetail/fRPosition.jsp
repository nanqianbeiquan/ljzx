   <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE style PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
  $(document).ready(function(){
        //console.log("init fRPosition page...");
        //console.log("curPge="+'${fRPositionPage.curPage}');
        //console.log("fRPositionPageNav init");
	    if(!$("#fRPositionPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("fRPositionPage.size="+'${fRPositionPage.size}');
	    if($("#fRPositionPageNav").html()==""&&'${fRPositionPage.size}'>0){
	    	 //console.log("true");
	        $("#fRPositionPageNav").pagination('${fRPositionPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${fRPositionPage.pageSize}',
	             current_page:parseInt('${fRPositionPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
// 	             回调 
	             callback: fRPositionPageselectCallback
	     });
	     $("#fRPositionPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div>
	<div class="gongshangyuanspdiv">
	   <span class="gongshangyuansp">企业法人代表在外任职信息</span>
	   <span class="gongshangyuan">
		  <c:if test="${not empty company and not empty company.fRPositionArray}">
		    ${fn:length(company.fRPositionArray)}
		  </c:if>
		  <c:if test="${empty company or empty company.fRPositionArray}">
		        0
		  </c:if>
	   </span>
	</div>
	<c:if test="${empty company or empty company.fRPositionArray}">
		 <span style="display:block; margin:0 auto; line-height:40px; color:#CCC;">无信息</span>     
	</c:if>
	<c:if
		test="${not empty company and not empty company.fRPositionArray and fRPositionPage.pageSize>0 and fRPositionPage.curPage>0}">
		<table class="gongshangtable" cellpadding="0" cellspacing="0">
			<tr>
			    <td class="td_lable" style="color:#9a9a9a; text-align:center;width:30px;">序号</td>
				<td class="td_lable" style="color:#9a9a9a; text-align:center;">企业名称</td>
				<td class="td_lable" style="color:#9a9a9a; text-align:center;">工商注册号</td>
				<td class="td_lable" style="color:#9a9a9a; text-align:center;">企业类型</td>
				<td class="td_lable" style="color:#9a9a9a; text-align:center;">企业状态</td>
				<td class="td_lable" style="color:#9a9a9a; text-align:center;">注册资本(万元)</td>
				<td class="td_lable" style="color:#9a9a9a; text-align:center;">注册资本币种</td>
				<td class="td_lable" style="color:#9a9a9a; text-align:center;">职务</td>
			</tr>
			<c:forEach items="${company.fRPositionArray }" var="record"
			    begin="${fRPositionPage.pageSize*(fRPositionPage.curPage-1) }"
					end="${fRPositionPage.pageSize*fRPositionPage.curPage-1 }"
				varStatus="i">
				<tr>
				    <td style="text-align:center;width:30px;"><c:out value="${i.index+1}"></c:out></td>
					<td style="text-align:center;">
					  <c:if test="${record.entname ne null and record.entname !='' and record.entname !='null'}">
					    ${record.entname }
					  </c:if>
					
					</td>
					<td style="text-align:center;">
					  <c:if test="${record.regno ne null and record.regno !='' and record.regno !='null'}">
					    ${record.regno }
					  </c:if>
				    </td>
				    <td style="text-align:center;">
					  <c:if test="${record.enttype ne null and record.enttype !='' and record.enttype !='null'}">
					    ${record.enttype }
					  </c:if>
				    </td>
				    <td style="text-align:center;">
					  <c:if test="${record.entstatus ne null and record.entstatus !='' and record.entstatus !='null'}">
					    ${record.entstatus }
					  </c:if>
				    </td>
					<td style="text-align:center;">
					   <c:if test="${record.regcap ne null and record.regcap !='' and record.regcap !='null'}"> 
					       ${record.regcap }
					   </c:if>
					</td>
					<td style="text-align:center;">
					   <c:if test="${record.regcapcur ne null and record.regcapcur !=''  and record.regcapcur !='null'}"> 
					     ${record.regcapcur }
					   </c:if>
					</td>
					<td style="text-align:center;">
					   <c:if test="${record.position ne null and record.position !=''  and record.position !='null'}"> 
					     ${record.position }
					   </c:if>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</div>

</body>
</html>