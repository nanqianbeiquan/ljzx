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
	    if(!$("#zhuYaoRenYuanPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("zhuYaoRenYuanPage.size="+'${zhuYaoRenYuanPage.size}');
	    if($("#zhuYaoRenYuanPageNav").html()==""&&'${zhuYaoRenYuanPage.size}'>0){
	    	 //console.log("true");
	        $("#zhuYaoRenYuanPageNav").pagination('${zhuYaoRenYuanPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${zhuYaoRenYuanPage.pageSize}',
	             current_page:parseInt('${zhuYaoRenYuanPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
	             //回调 
	             callback: zhuYaoRenYuanPageselectCallback
	     });
	     $("#zhuYaoRenYuanPageNav").css("display","block");
	    }
   });
</script>
</head>
<body>
<div class="info_title">
    <div class="inline_div title">主要人员</div>
    <div class="inline_div count">
	   <c:if test="${not empty zhuYaoRenYuanArray}">
	    ${fn:length(zhuYaoRenYuanArray)}
	   </c:if>
	   <c:if test="${empty zhuYaoRenYuanArray}">
	        0
	   </c:if>
    </div>
    <div class="inline_div message hidden"></div>
</div>
<c:if test="${empty zhuYaoRenYuanArray}">无信息</c:if>
<c:if test="${not empty zhuYaoRenYuanArray and zhuYaoRenYuanPage.pageSize>0 and zhuYaoRenYuanPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>
                <td class="td_lable" style="">姓名</td>
                <td class="td_lable" style="">职务</td>
            </tr>
        </thead>
        <tbody>
		 <c:forEach items="${zhuYaoRenYuanArray }" var="record"
		    begin="${zhuYaoRenYuanPage.pageSize*(zhuYaoRenYuanPage.curPage-1) }"
					end="${zhuYaoRenYuanPage.pageSize*zhuYaoRenYuanPage.curPage-1 }"
				varStatus="i">        
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td>
                  <c:choose>
					  <c:when test="${record.keyperson_name ne null and record.keyperson_name !='' and record.keyperson_name !='null'}">
					    ${record.keyperson_name }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.keyperson_position ne null and record.keyperson_position !='' and record.keyperson_position !='null'}">
					    ${record.keyperson_position }
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