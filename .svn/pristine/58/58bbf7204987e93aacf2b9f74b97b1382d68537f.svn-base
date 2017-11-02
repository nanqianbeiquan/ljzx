<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>

<div class="table4">
	<input type="hidden" id="totalNum4Person" value="${page.recordSum}" />
	
	<table class="tableb data_table" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
			    <td class="table_2"></td>
				<td class="table_2"><input type="checkbox" class="selectAll4Person" onclick="selectAll4Person()"></td>
				<td class="table_6 text_left">全选</td>
				<td width="50px">姓名</td>
				<td width="50px">地区</td>
				<td width="50px">时间</td>
				<td width="50px">事件（严重/异常/一般）</td>
				<td width="50px">风险标签</td>
			</tr>
		</thead>
		
		<c:choose>
  				<c:when test="${empty page  or empty page.results}">   
  					<c:forEach begin="1" end="6">
					<tr>
						<td></td>
						<td></td>			                        
		                <td></td>
		                <td style="text-align:center;"></td>
		                <td style="text-align:center"></td>
		                <td style="text-align:center"></td>
		                <td style="text-align:center"></td>
		                <td style="text-align:center"></td>
					</tr>
				</c:forEach>   
			</c:when>
  				
			<c:otherwise>  
				<c:forEach items="${page.results}" var="monitorRelationPerson" varStatus="status">
					<tr id="tr_${status.index}">
						<td></td>
						<td><input type="checkbox" name="selectOne4Person" value="${monitorRelationPerson.id}" class="chkList4Person"></td>			                        
                    	<td></td>
                    	<c:if test="${monitorRelationPerson.monitorDate eq today}">
                    		<td style="text-align:center;cursor: pointer;" class="name link_td" onclick="viewTodayMonitorRelationPersonDetail()">${monitorRelationPerson.name}</td>
                    	</c:if>
                    	<c:if test="${monitorRelationPerson.monitorDate ne today}">
                    		<td style="text-align:center;cursor: pointer;" class="name link_td" onclick="viewRelationPersonDetail('${monitorRelationPerson.id}')">${monitorRelationPerson.name}</td>
                    	</c:if>
                    	<td style="text-align:center">${monitorRelationPerson.province}/${monitorRelationPerson.city}</td>
                    	<td style="text-align:center"><fmt:formatDate value="${monitorRelationPerson.monitorDate}" pattern="yyyy-MM-dd" /></td>
                    	<td style="text-align:center">${monitorRelationPerson.warnEventNum}/${monitorRelationPerson.attentionEventNum}/${monitorRelationPerson.normalEventNum}</td>
                        <td style="text-align:center">
                        	<c:if test="${empty monitorRelationPerson.riskLabel}">--</c:if>
                        	<c:if test="${not empty monitorRelationPerson.riskLabel}">${monitorRelationPerson.riskLabel}</c:if>
                        </td>
					</tr>
				</c:forEach>
					
				<c:if test="${page.results.size()<6}">
					<c:forEach begin="1" end="${6-page.results.size()}">
						<tr>
							<td></td>
							<td></td>			                        
		                    <td></td>
		                    <td style="text-align:center;"></td>
		                    <td style="text-align:center"></td>
		                    <td style="text-align:center"></td>
		                    <td style="text-align:center"></td>
		                    <td style="text-align:center"></td>
						</tr>
					</c:forEach>   
				</c:if>
			</c:otherwise>
		</c:choose>
	</table>

</div>


