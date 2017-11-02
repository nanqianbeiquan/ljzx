<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript">
var lap;
  $(document).ready(function(){
       console.log("init dongChanDiYaPage page...");
        //console.log("curPge="+'${zhuYaoRenYuanPage.curPage}');
        //console.log("bianGengPageNav init");
	    if(!$("#dongChanDiYaPageNav")[0]){
	        //console.log("false");
	        return;
	    }
	    console.log("dongChanDiYaPage.size="+'${dongChanDiYaPage.size}');
	    if($("#dongChanDiYaPageNav").html()==""&&'${dongChanDiYaPage.size}'>0){
	    	 //console.log("true");
	        $("#dongChanDiYaPageNav").pagination('${dongChanDiYaPage.size}', {
	             prev_text: "上一页",
	             next_text: "下一页",
	             items_per_page:'${dongChanDiYaPage.pageSize}',
	             current_page:parseInt('${dongChanDiYaPage.curPage-1}'),
	             num_edge_entries: 3,
	             num_display_entries: 5,
	             //回调 
	             callback: dongChanDiYaPageselectCallback
	     });
	     $("#dongChanDiYaPageNav").css("display","block");
	     console.log("init dongChanDiYaPage end");
	    }
	    
    	lap=new overlap({
        scroller:{
            lock:true
        },
        content:{
            style:{
             width:"690px",
   	         height:"620px",
   	         border:"16px solid rgba(0,0,0,0.15)"
            }
	    }});
	    
   });
function dongChanDiYaDetailClick(index){
   
  
   $.ajax({
       type: "post",
       url: ctx+"/biz/dongChanDiYaDetail.do",
       data: {"companyName":encodeURIComponent($(".monitor_company_name").html().trim()),"index":index},
       dataType: "html",
       success: function (data) {
          //console.log("dongChanDiYaDetail call back...");
          $("#modalContent").html(data);
          lap.show("#modalContent");
       },
  		error: function(){
  		   //console.log("error");
  		},
  		complete: function(request, textStatus){
  			//console.log("complete:"+textStatus+","+request);
   		//console.log(request);
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
<style>
#pgwModal .pm-body{
margin-top: 50px;
}
</style>
</head>
<body>
<div id="modalContent" class="hidden"></div>
<div class="info_title">
    <div class="inline_div title">动产质押</div>
    <div class="inline_div count">
	   <c:if test="${not empty dongChanDiYaArray}">
	    ${fn:length(dongChanDiYaArray)}
	   </c:if>
	   <c:if test="${empty dongChanDiYaArray}">
	        0
	   </c:if>
    </div>
    <div class="inline_div message hidden"></div>
</div>
<c:if test="${empty dongChanDiYaArray}">无信息</c:if>
<c:if test="${not empty dongChanDiYaArray and dongChanDiYaPage.pageSize>0 and dongChanDiYaPage.curPage>0}">
<div class="info_table">
    <table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lable td_index" style="">序号</td>			    
                <td class="td_lable" style="">登记编号</td>
                <td class="td_lable" style="">登记日期</td>
                <td class="td_lable" style="">登记机关</td>
                <td class="td_lable" style="">被担保债权数额</td>
                <td class="td_lable" style="">状态</td>
                <td class="td_lable" style="">详情</td>
            </tr>
        </thead>
        <tbody>
		 <c:forEach items="${dongChanDiYaArray }" var="record"
		    begin="${dongChanDiYaPage.pageSize*(dongChanDiYaPage.curPage-1) }"
					end="${dongChanDiYaPage.pageSize*dongChanDiYaPage.curPage-1 }"
				varStatus="i">        
            <tr>
                <td><c:out value="${i.index+1}"></c:out></td>
                <td>
                  <c:choose>
					  <c:when test="${record.chattelmortgage_registrationno ne null and record.chattelmortgage_registrationno !='' and record.chattelmortgage_registrationno !='null'}">
					    ${record.chattelmortgage_registrationno }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.chattelmortgage_registrationdate ne null and record.chattelmortgage_registrationdate !='' and record.chattelmortgage_registrationdate !='null'}">
					    ${record.chattelmortgage_registrationdate }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.chattelmortgage_registrationinstitution ne null and record.chattelmortgage_registrationinstitution !='' and record.chattelmortgage_registrationinstitution !='null'}">
					    ${record.chattelmortgage_registrationinstitution }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.chattelmortgage_guaranteedamount ne null and record.chattelmortgage_guaranteedamount !='' and record.chattelmortgage_guaranteedamount !='null'}">
					    ${record.chattelmortgage_guaranteedamount }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.chattelmortgage_status ne null and record.chattelmortgage_status !='' and record.chattelmortgage_status !='null'}">
					    ${record.chattelmortgage_status }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>                
                </td>
                <td>
                   <a  style="max-width:150px;" onclick="dongChanDiYaDetailClick('${i.index}');" href="javascript:;">详情</a>                
                </td>
            </tr>
           </c:forEach>
        </tbody>
    </table>
</div>
</c:if>
</body>
</html>