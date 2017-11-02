<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
  $(document).ready(function(){
       console.log("init log list page...");
        //console.log("curPge="+'${zhuYaoRenYuanPage.curPage}');
        //console.log("bianGengPageNav init");
	    if(!$("#logListTablePageDiv")[0]){
	        //console.log("false");
	        return;
	    }
	    console.log("logListPage.recordSum="+'${logListPage.recordSum}');
	    console.log("logListPage.pageSize="+'${logListPage.pageSize}');
	    console.log("logListPage.currentPageNo="+'${logListPage.currentPageNo}');
	    if('${logListPage.currentPageNo}'==1||'${logListPage.recordSum}'>0){
	    	 //console.log("true");
	        $("#logListTablePageDiv").pagination('${logListPage.recordSum}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:parseInt('${logListPage.pageSize}'),
	             current_page:parseInt('${logListPage.currentPageNo-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
	             //回调 
	             callback: logListPageselectCallback
	     });
	     $("#logListTablePageDiv").css("display","block");
	     console.log("init logListTablePageDiv end");
	    }
	    
	    
   });

 
</script>
<style>
#pgwModal .pm-body{
margin-top: 50px;
}
</style>
</head>
  <body>
    <table class="first_col_table" style="width:100%;margin:16px 0px 0px 0px;">
        <thead>
            <tr>
                <td style="width:60px;">序号</td>
                <td style="width:150px;">账户</td>
                <td style="width:150px;">类型</td>
                <td>内容</td>
                <td style="width:200px;">时间</td>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${logListPage.results }" var="record"
		    
				varStatus="i">
            <tr>
                <td><c:out value="${i.index+ 1 +logListPage.pageSize*(logListPage.currentPageNo-1)}"></c:out></td>
                <td><c:out value="${record.accountName}"></c:out></td>
                <td><c:out value="${record.actionType}"></c:out></td>
                <td><c:out value="${record.opContent}"></c:out></td>
                <td><c:out value="${record.createTime}"></c:out></td>
            </tr>
           </c:forEach> 
        </tbody>
    </table>
  </body>
</html>
