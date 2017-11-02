<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
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
function fRPositionPageselectCallback(new_page_index, pagination_container){
 var pageNo = parseInt(new_page_index) + parseInt(1);
    var parameter = {
           "companyName":encodeURIComponent($("#companyName").val().trim()),
			"curPage": parseInt(pageNo)
	 }
	 console.log(parameter);
	 $.ajax({
       type: "post",
       url: ctx+"/monitorComRisk/tofRPositionNextPage.do",
       data: parameter,
       dataType: "html",
       success: function (data) {
           console.log("NextPage call back...");
          // console.log(data);
          $("#fRPositionDiv").html(data);
       },
  		error: function(){},
  		complete: function(request, textStatus){
   		if(request){
   			if(request.status==0){
   				toastr.error('网络异常！');
   			}else if(request.status==500){
   				toastr.error('站点异常！');
   			}else{
   				if(!textStatus||textStatus!="success"){
   					toastr.error('站点异常！');
   				}
   			}
   		}
  		}
    });
}
</script>
</head>
  
<body>
<c:if test="${empty fRPositionArray}">
	 <span style="">无信息</span>     
</c:if>
<c:if
		test="${not empty fRPositionArray and fRPositionPage.pageSize>0 and fRPositionPage.curPage>0}">
 <table class="all_col_table ">
     <thead>
         <tr>
             <td class="td_lable td_index" style="width:5%">序号</td>
             <td class="td_lable" style="width:15%">企业名称</td>
             <td class="td_lable" style="width:10%">工商注册号</td>
             <td class="td_lable" style="width:12%">企业类型</td>
             <td class="td_lable" style="width:10%">企业状态</td>
             <td class="td_lable" style="width:10%">注册资本（万元）</td>
             <td class="td_lable" style="width:8%">注册资本币种</td>
             <td class="td_lable" style="width:5%">职务</td>
         </tr>
     </thead>
     <tbody>   
		<c:forEach items="${fRPositionArray }" var="record"
	    begin="${fRPositionPage.pageSize*(fRPositionPage.curPage-1) }"
			end="${fRPositionPage.pageSize*fRPositionPage.curPage-1 }"
		varStatus="i">    
	         <tr>
	             <td><c:out value="${i.index+1}"></c:out></td>
	             <td>
	               <div class="info_left_value">
	                  <c:if test="${record.entname ne null and record.entname !='' and record.entname !='null'}">
					    ${record.entname }
					  </c:if>
	               </div>
	             </td>
	             <td>
					  <c:if test="${record.regno ne null and record.regno !='' and record.regno !='null'}">
					    ${record.regno }
					  </c:if>
                 </td>
	             <td><div class="info_value" >
					  <c:if test="${record.enttype ne null and record.enttype !='' and record.enttype !='null'}">
					    ${record.enttype }
					  </c:if>	             
	             </div></td>
	             <td>
					  <c:if test="${record.entstatus ne null and record.entstatus !='' and record.entstatus !='null'}">
					    ${record.entstatus }
					  </c:if>	             
	             </td>
	             <td>
					   <c:if test="${record.regcap ne null and record.regcap !='' and record.regcap !='null'}"> 
					       ${record.regcap }
					   </c:if>	             
	             </td>
	             <td>
					   <c:if test="${record.regcapcur ne null and record.regcapcur !=''  and record.regcapcur !='null'}"> 
					     ${record.regcapcur }
					   </c:if>	             
	             </td>
	             <td>
					   <c:if test="${record.position ne null and record.position !=''  and record.position !='null'}"> 
					     ${record.position }
					   </c:if>
                 </td>
	         </tr>
        </c:forEach>
     </tbody>
</table>
</c:if>
  </body>
</html>
