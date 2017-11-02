<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<script type="text/javascript">
  $(document).ready(function(){
        //console.log("init fRInv page...");
        //console.log("curPge="+'${fRInvPage.curPage}');
        //console.log("fRInvPageNav init");
	    if(!$("#fRInvPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    //console.log("fRInvPage.size="+'${fRInvPage.size}');
	    if($("#fRInvPageNav").html()==""&&'${fRInvPage.size}'>0){
	    	 //console.log("true");
	        $("#fRInvPageNav").pagination('${fRInvPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${fRInvPage.pageSize}',
	             current_page:parseInt('${fRInvPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
// 	             回调 
	             callback: fRInvPageselectCallback
	     });
	     $("#fRInvPageNav").css("display","block");
	    }
   });
 function fRInvPageselectCallback(new_page_index, pagination_container){
 var pageNo = parseInt(new_page_index) + parseInt(1);
    var parameter = {
           "companyName":encodeURIComponent($("#companyName").val().trim()),
			"curPage": parseInt(pageNo)
	 }
	 console.log(parameter);
	 $.ajax({
       type: "post",
       url: ctx+"/monitorComRisk/tofRInvNextPage.do",
       data: parameter,
       dataType: "html",
       success: function (data) {
           console.log("NextPage call back...");
          // console.log(data);
          $("#fRInvDiv").html(data);
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
<c:if test="${empty fRInvArray}">
	 <span style="">无信息</span>     
</c:if>
<c:if
		test="${not empty fRInvArray and fRInvPage.pageSize>0 and fRInvPage.curPage>0}">
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
            <td class="td_lable" style="width:5%">出资方式</td>
            <td class="td_lable" style="width:10%">认缴出资（万元）</td>
            <td class="td_lable" style="width:8%">认缴资本币种</td>
            <td class="td_lable" style="width:10%">出资比例</td>
        </tr>
    </thead>
    <tbody>   
		<c:forEach items="${fRInvArray }" var="record"
	    begin="${fRInvPage.pageSize*(fRInvPage.curPage-1) }"
			end="${fRInvPage.pageSize*fRInvPage.curPage-1 }"
		varStatus="i">
	        <tr>
	            <td><c:out value="${i.index+1}"></c:out></td>
	            <td><div class="info_left_value">
					  <c:if test="${record.entname ne null and record.entname !='' and record.entname !='null'}">
					    ${record.entname }
					  </c:if>
                </div></td>
	            <td>
					  <c:if test="${record.regno ne null and record.regno !='' and record.regno !='null'}">
					    ${record.regno }
					  </c:if>	            
	            </td>
	            <td><div class="info_value" >
					  <c:if test="${record.enttype ne null and record.enttype !='' and record.enttype !='null'}">
					    ${record.enttype }
					  </c:if>	            
	            </div>
	            </td>
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
					   <c:if test="${record.conform ne null and record.conform !=''  and record.conform !='null'}"> 
					     ${record.conform }
					   </c:if>	            
	            </td>
	            <td>
					   <c:if test="${record.subconam ne null and record.subconam !=''  and record.subconam !='null'}"> 
					     ${record.subconam }
					   </c:if>	            
	            </td>
	            
	            <td>
					   <c:if test="${record.currency ne null and record.currency !=''  and record.currency !='null'}"> 
					     ${record.currency }
					   </c:if>	            
	            </td>
	            <td>
					   <c:if test="${record.fundedratio ne null and record.fundedratio !=''  and record.fundedratio !='null'}"> 
					     ${record.fundedratio }
					   </c:if>	            
	            </td>
	        </tr>
	     </c:forEach>
    </tbody>
</table>		
</c:if>
  </body>
</html>
