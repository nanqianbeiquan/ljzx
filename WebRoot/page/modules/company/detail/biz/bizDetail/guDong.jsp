<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>

<script type="text/javascript">
  $(document).ready(function(){
        //console.log("init guDong page...");
        //console.log("curPge="+'${guDongPage.curPage}');
        //console.log("guDongPageNav init");
	    if(!$("#guDongPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("guDongPage.size="+'${guDongPage.size}');
	    if($("#guDongPageNav").html()==""&&'${guDongPage.size}'>0){
	    	 //console.log("true");
	        $("#guDongPageNav").pagination('${guDongPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${guDongPage.pageSize}',
	             current_page:parseInt('${guDongPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
// 	             回调 
	             callback: guDongPageselectCallback
	     });
	     $("#guDongPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">股东信息</div>
    <div class="inline_div count">
		  <c:if test="${not empty guDongArray}">
		    ${fn:length(guDongArray)}
		  </c:if>
		  <c:if test="${empty guDongArray}">
		        0
		  </c:if>
    </div>
    <div class="inline_div message hidden"></div>
</div>

<c:if test="${empty guDongArray}"><span>无信息</span></c:if>
<c:if test="${not empty guDongArray and guDongPage.pageSize>0 and guDongPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>
                <td class="td_lable" style="">股东</td>
                <td class="td_lable" style="">股东类型</td>
                <td class="td_lable" style="">出资时间</td>
                <td class="td_lable" style=" ">认缴出资(万元)</td>
            </tr>
        </thead>
        <tbody>
           <c:forEach items="${guDongArray }" var="record"
			    begin="${guDongPage.pageSize*(guDongPage.curPage-1) }"
					end="${guDongPage.pageSize*guDongPage.curPage-1 }"
				varStatus="i">
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td>
                  <c:choose>
					  <c:when test="${record.shareholder_name ne null and record.shareholder_name !='' and record.shareholder_name !='null'}">
					    ${record.shareholder_name }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>					                
                </td>
				<td>
                  <c:choose>
					  <c:when test="${record.shareholder_type ne null and record.shareholder_type !='' and record.shareholder_type !='null'}">
					    ${record.shareholder_type }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				  
			    </td>
			    <td>
                  <c:choose>
					  <c:when test="${record.subscripted_time ne null and record.subscripted_time !='' and record.subscripted_time !='null'}">
					    ${record.subscripted_time }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				  
			    </td>
				<td>
                  <c:choose>
					  <c:when test="${record.resubscripted ne null and record.resubscripted !='' and record.resubscripted !='null'}">
					    ${record.resubscripted }
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