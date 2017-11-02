
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="back_block">
    <div class="inline_div">
         <c:if test="${empty preMenu }"> 
             <div class="for_back_btn" onclick="window.history.back()"></div>
         </c:if> 
         <c:if test="${not empty preMenu }"> 
            <div class="for_back_btn" onClick="backTo('${preMenu.baseURL }${preMenu.paramURL}')"></div> 
           
         </c:if> 
    </div>
</div>
<c:if test="${not empty navData }">
   <c:forEach items="${navData}" var="record" varStatus="status">
       <c:if test="${!status.last }">
	       <span><a href="${record.baseURL }${record.paramURL}">${record.name }</a></span>
	       <span>></span>
       </c:if>
       <c:if test="${status.last }">
	       <span class="current_nav"><a href="${record.baseURL }${record.paramURL}">${record.name }</a></span>
       </c:if>

   </c:forEach>
</c:if>

