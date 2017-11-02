<%@page import="com.srd.ljzd.util.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE style PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script type="text/javascript">
  $(document).ready(function(){
       // console.log("init bianGengJiLu table...");
       
   });
   
  
</script>

</head>
<body>

<c:if test="${empty targetList}">
    无信息
</c:if>
<c:if test="${not empty targetList}">
 		<table class="all_col_table ">
        <thead>
            <tr>
                <td class="td_lableshuzi td_index" style="">序号</td>
                <td class="td_lable" style="width: 85px;">变更日期</td>
                <td class="td_lable" style="width: 100px;">变更事项</td>
                <td class="td_lable" style="">变更前</td>
                <td class="td_lable" style="">变更后</td>
            </tr>
        </thead>
        <tbody>
		 <c:forEach items="${targetList }" var="record" varStatus="i">        
            <tr>
                <td>${i.index+1 }</td>
                <td>
                  <c:choose>
					  <c:when test="${record.changedannouncement_date ne null and record.changedannouncement_date !='' and record.changedannouncement_date !='null'}">
					    ${record.changedannouncement_date }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.changedannouncement_events ne null and record.changedannouncement_events !='' and record.changedannouncement_events !='null'}">
					    ${record.changedannouncement_events }
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.changedannouncement_before ne null and record.changedannouncement_before !='' and record.changedannouncement_before !='null'}">
					    <div class="long_value"><c:out value="${record.changedannouncement_before }"  escapeXml="false"></c:out></div>
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
                <td>
                  <c:choose>
					  <c:when test="${record.changedannouncement_after ne null and record.changedannouncement_after !='' and record.changedannouncement_after !='null'}">
					    <div class="long_value">${record.changedannouncement_after }</div>
					  </c:when> 
                      <c:otherwise>--</c:otherwise>
                  </c:choose>				                 
                </td>
            </tr>
           </c:forEach>
        </tbody>
    </table>
  </c:if>
</body>
</html>