<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  $(document).ready(function(){
      
	    if(!$("#brandPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	  
	    if($("#brandPageNav").html()==""&&'${brandPage.size}'>0){
	        /* $("#brand").html("商标(${brandPage.size})");
	         var am = parseInt(
	          "${fn:length(company.shareholderArray)+fn:length(company.keyPersonArray)+fn:length(company.bianGengJiLuArray)+fn:length(company.jingYingYiChangArray)+fn:length(company.dongChanDiYaArray)+fn:length(company.fiLiationArray)+fn:length(company.fRPositionArray)+fn:length(company.fRInvArray)+fn:length(company.entInvArray)+fn:length(company.shareSIMPAWNArray)+fn:length(company.administrativePenaltyArray)}")
	          +parseInt('${brandPage.size}');
	         $("#gsAMSpan").html(am);*/
	    	 //console.log("true");
	    	// $("#brandNum").html('${brandPage.size}');
	        $("#brandPageNav").pagination('${brandPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${brandPage.pageSize}',
	             current_page:parseInt('${brandPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
	             //回调 
	             callback: brandPageselectCallback
	     });
	     $("#brandPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">商标</div>
    <div class="inline_div count">
	   <c:if test="${not empty brandArray}">
	    ${fn:length(brandArray)}
	   </c:if>
	   <c:if test="${empty brandArray}">
	        0
	   </c:if>
    </div>
    <div class="inline_div message hidden"></div>
</div>
<c:if test="${empty brandArray}">无信息</c:if>
<c:if test="${not empty brandArray and brandPage.pageSize>0 and brandPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>			    
               <td class="td_lable" style="width:300px;">商标名称</td>
               <td class="td_lable" style="width:150px;">商标图片</td>
               <td class="td_lable" style="">申请时间</td>
               <td class="td_lable" style="">状态</td>
               <td class="td_lable" style="">注册号</td>
               <td class="td_lable" style="">类别</td>
            </tr>
        </thead>
        <tbody>
			<c:forEach items="${brandArray }" var="record"
			    begin="${brandPage.pageSize*(brandPage.curPage-1) }"
				end="${brandPage.pageSize*brandPage.curPage-1 }"
				varStatus="i">        
            <tr>
                <td>${i.index+1 }</td>
                <td>
                  <c:choose>
					  <c:when test="${record.brand_name ne null and record.brand_name !='' and record.brand_name !='null'}">
					    ${record.brand_name }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.img_str ne null and record.img_str !='' and record.img_str!='null'}">
						  <img alt="商标" style="width:100px;" src="data:image/png;base64,${record.img_str }">
					  </c:when>                  
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.apply_time ne null and record.apply_time !='' and record.apply_time !='null'}">
					    ${record.apply_time }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.brand_status ne null and record.brand_status !='' and record.brand_status !='null'}">
					    ${record.brand_status }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.reg_no ne null and record.reg_no !='' and record.reg_no !='null'}">
					    ${record.reg_no }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.cat_no ne null and record.cat_no !='' and record.cat_no !='null'}">
					    ${record.cat_no }
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