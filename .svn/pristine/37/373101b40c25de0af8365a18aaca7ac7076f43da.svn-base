<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>

<div class="table4">
	<input type="hidden" id="totalNum4Company" value="${page.recordSum}" />
	
	<table class="tableb data_table" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
			    <td class="table_2"></td>
				<td class="table_2"><input type="checkbox" class="selectAll4Company" onclick="selectAll4Company()"></td>
				<td class="table_6 text_left">全选</td>					
				<td width="180px">公司名称</td>
				<td width="180px">地区</td>
				<td width="180px">时间</td>
				<td width="180px">事件（严重/异常/一般）</td>
				<td width="180px">风险标签</td>
				<td width="180px">风险状况</td>
			</tr>
		</thead>
			
		<c:if test="${empty page or empty page.results}">
			<c:forEach begin="1" end="6">
				<tr>
				  	<td></td>			                        
                   	<td></td>
                       <td style="text-align:center"></td>
                       <td style="text-align:center;"></td>
                       <td style="text-align:center"></td>
                       <td style="text-align:center"></td>
                       <td style="text-align:center"></td>
                       <td style="text-align:center"></td>
                       <td style="text-align:center"></td>
                       <td style="text-align:center">
                       </td>
				</tr>
			</c:forEach>   
		</c:if>
   	
   		<c:if test="${not empty page and not empty page.results}">
   			<c:forEach items="${page.results}" var="monitorRelationCompany" varStatus="status">
				<tr id="tr_${status.index}">
					<td></td>			                        
	                <td><input type="checkbox" name="selectOne4Company" value="${monitorRelationCompany.id}" class="chkList4Company"></td>
                    <td style="text-align:center"></td>
                    <c:if test="${monitorRelationCompany.monitorDate eq today}">
                    	<c:if test="${monitorCompany.renameStatus ne '1'}">
                    		<td style="text-align:center;cursor: pointer;" class="name link_td" onclick="viewTodayMonitorRelationCompanyDetail('${monitorRelationCompany.companyName}')">${monitorRelationCompany.companyName}</td>
                    	</c:if>
                    	<c:if test="${monitorCompany.renameStatus eq '1'}">
                    		<td style="text-align:center;cursor: pointer;" class="name link_td change_name_company" onclick="viewTodayMonitorRelationCompanyDetail('${monitorRelationCompany.companyName}')">${monitorRelationCompany.companyName}</td>
                    	</c:if>
                    </c:if>
                    <c:if test="${monitorRelationCompany.monitorDate ne today}">
                    	<c:if test="${monitorCompany.renameStatus ne '1'}">
                    		<td style="text-align:center;cursor: pointer;" class="name link_td " onclick="viewRelationCompanyDetail('${monitorRelationCompany.id}')">${monitorRelationCompany.companyName}</td>
                    	</c:if>
                    	<c:if test="${monitorCompany.renameStatus eq '1'}">
                    		<td style="text-align:center;cursor: pointer;" class="name link_td change_name_company" onclick="viewRelationCompanyDetail('${monitorRelationCompany.id}')">${monitorRelationCompany.companyName}</td>
                    	</c:if>
                    </c:if>
                    <td style="text-align:center">${monitorRelationCompany.province}</td>
                    <td style="text-align:center"><fmt:formatDate value="${monitorRelationCompany.monitorDate}" pattern="yyyy-MM-dd" /></td>
                    <td style="text-align:center">${monitorRelationCompany.warnEventNum}/${monitorRelationCompany.attentionEventNum}/${monitorRelationCompany.normalEventNum}</td>
                    <td style="text-align:center">
                    	<c:if test="${empty monitorRelationCompany.riskLabel}">--</c:if>
                    	<c:if test="${not empty monitorRelationCompany.riskLabel}">${monitorRelationCompany.riskLabel}</c:if>
                    </td>
                    <td style="text-align:center">
                    	<c:if test="${empty monitorRelationCompany.riskSituation}">--</c:if>
                    	<c:if test="${monitorRelationCompany.riskSituation eq '0'}">正常</c:if>
                    	<c:if test="${monitorRelationCompany.riskSituation eq '1'}">关注</c:if>
                    	<c:if test="${monitorRelationCompany.riskSituation eq '2'}">一般预警</c:if>
                    	<c:if test="${monitorRelationCompany.riskSituation eq '3'}">特别预警</c:if>
                    </td>
                    <td style="text-align:center">
                    </td>
				</tr>
			</c:forEach>
   			
   			<c:if test="${page.results.size()<6}">
				<c:forEach begin="1" end="${6-page.results.size()}">
					<tr>
						<td></td>			                        
                    	<td></td>
                       	<td style="text-align:center"></td>
                       	<td style="text-align:center;"></td>
                       	<td style="text-align:center"></td>
                       	<td style="text-align:center"></td>
                       	<td style="text-align:center"></td>
                       	<td style="text-align:center"></td>
                       	<td style="text-align:center"></td>
                       	<td style="text-align:center">
                       	</td>
					</tr>
				</c:forEach>   
			</c:if>
   		</c:if>
	</table>
</div>


