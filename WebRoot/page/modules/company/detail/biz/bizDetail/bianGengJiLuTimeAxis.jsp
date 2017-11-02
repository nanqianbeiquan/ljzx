<%@page import="com.srd.ljzd.util.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/page/common/base.jsp"%>
<c:if test="${not empty bianGengJiLuSortedDate and not empty bianGengJiLuTimeAxis }">
<div class="inline_div left_btn">
    <img src="${ctx }/images/modules/monitor/day_left_icon.png">
</div><div class="inline_div day_line">
</div><div class="inline_div right_btn">
    <img src="${ctx }/images/modules/monitor/day_right_icon.png">
</div>
 <div class="relative_left_block">
     <div class="day_list">
		 <c:forEach items="${bianGengJiLuSortedDate }" var="date" varStatus="i">
			<div class="inline_div day ${i.index%2==0 ? 'down':'up' }_day">
			    <div class="inline_div">
			         <fmt:formatNumber value="${(i.index%6+1)/2.0+0.49}" pattern="#0" var="num5"></fmt:formatNumber>
			        <img src="${ctx }/images/modules/monitor/day_${i.index%2==0 ? 'down':'up' }_${num5}.png">
			    </div>
			    <div class="inline_div day_content">
			        <div class="title">${date.key}</div>
			        <div class="value">
			            <div class="line_txt">
		                      <c:if test="${not empty bianGengJiLuTimeAxis[date.key]}">
		                           <c:forEach items="${bianGengJiLuTimeAxis[date.key]}" var="cate" varStatus="j">
		                              <span class="cate">${cate.key}</span><c:if test="${!j.last }">、</c:if>
							    </c:forEach>
		                       </c:if>		            
			            </div>
			        </div>
			    </div>
			</div>
		 </c:forEach>
     </div>
 </div>  
</c:if>
