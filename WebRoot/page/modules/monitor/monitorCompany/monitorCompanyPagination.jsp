<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>

<div class="table4 ">
	<input type="hidden" id="totalNum" value="${page.recordSum}" />
	<input type="hidden" id="currentPageNo2" value="${page.currentPageNo}" />
	<input type="hidden" id="pageSize2" value="${page.pageSize}" />
	<input type="hidden" id="contractId" value="${contractId}" />

	<table class="tableb event_table first_col_table data_table" cellspacing="0" cellpadding="0">
		<thead>
			<tr>
			    <c:if test="${showActionBtnFlag eq true }">
					<td width="30px">
					     <input type="checkbox" class="selectAll" onclick="selectAllDeal()">
					</td>
				</c:if>
				<td width="30px">序号</td>
				<td width="150px">目标公司</td>
				<td width="60px">账户</td>
				<td width="60px">地区</td>
				<td width="90px" style="cursor: pointer;" onClick="onSortEventClicked('eventNum',${not empty eventNumIsDesc?eventNumIsDesc:false})" title="事件(严重/异常/一般)"><span class=${not empty eventNumIsDesc?(eventNumIsDesc==false?"sort_down":"sort_up"):""} >事件(严重/异常/一般)</span></td>
				<td width="60px" style="cursor: pointer;" onClick="onSortEventClicked('customRiskSituation',${not empty customRiskSituationIsDesc?customRiskSituationIsDesc:false})"><span class=${not empty customRiskSituationIsDesc?(customRiskSituationIsDesc==false?"sort_down":"sort_up"):""}>风险状况</span></td>
				<td width="50px" style="cursor: pointer;" onClick="onSortEventClicked('riskStatus',${not empty riskStatusIsDesc?riskStatusIsDesc:false})"><span class=${not empty riskStatusIsDesc?(riskStatusIsDesc==false?"sort_down":"sort_up"):""}>风险变化</span></td>
				<td width="50px" style="cursor: pointer;" onClick="onSortEventClicked('eventLevel',${not empty eventLevelIsDesc?eventLevelIsDesc:false})"><span class=${not empty eventLevelIsDesc?(eventLevelIsDesc==false?"sort_down":"sort_up"):""}>风险事件</span></td>
				<td width="80px">企业风险标签	</td>
				<td width="80" title="关系企业/自然人">关系企业/自然人</td>
			</tr>
		</thead>

		<c:if test="${empty page or empty page.results}">   
			<c:forEach begin="1" end="10">
				<tr>
					<c:if test="${showActionBtnFlag eq true }"><td></td></c:if>
					<td style="text-align:center"></td>
                    <td style="text-align:left;padding-left: 6px;"></td>
                    <td style="text-align:center"></td>
                    <td style="text-align:center"></td>
                    <td style="text-align:center;min-width:70px;"></td>
                    <td style="text-align:center"></td>
                    <td style="text-align:center"></td>
                    <td style="text-align:center"></td>
                    <td style="text-align:center"></td>
                    <td style="text-align:center"></td>
				</tr>
			</c:forEach>
		</c:if>
   	
		<c:if test="${not empty page and not empty page.results}">
			<c:forEach items="${page.results}" var="monitorCompany" varStatus="status">
				<tr id="tr_${status.index}" >
					
					<c:if test="${showActionBtnFlag eq true }">
					  <td>
					   <input type="checkbox" name="monitorIdCheckbox" class="selectOne" value="${monitorCompany.monitorID}">
					  </td>
					</c:if>
					
                 	<td style="text-align:center">${ (page.currentPageNo-1)*page.pageSize +status.index+1}</td>
					<c:if test="${monitorCompany.monitorDate ne today}">
						<c:if test="${monitorCompany.customRiskFlag ne '1'}">
							<c:if test="${monitorCompany.renameStatus ne '1'}">
								<td style="text-align:left;cursor: pointer;" class="comName link_td" onclick="viewMonitorCompanyDetail('${monitorCompany.monitorID }')"><span class="inline_div  comName_value">${monitorCompany.companyName}</span></td>
							</c:if>
							<c:if test="${monitorCompany.renameStatus eq '1'}">
								<td style="text-align:left;cursor: pointer;" class="comName link_td change_name_company" onclick="viewMonitorCompanyDetail('${monitorCompany.monitorID }')"><span class="inline_div  comName_value">${monitorCompany.companyName}</span></td>
							</c:if>
						</c:if>
						<c:if test="${monitorCompany.customRiskFlag eq '1'}">
							<c:if test="${monitorCompany.renameStatus ne '1'}">
								<td style="text-align:left;cursor: pointer;" class="comName link_td customize_company" onclick="viewMonitorCompanyDetail('${monitorCompany.monitorID }')"><span class="inline_div  comName_value">${monitorCompany.companyName}</span></td>
							</c:if>
							<c:if test="${monitorCompany.renameStatus eq '1'}">
								<td style="text-align:left;cursor: pointer;" class="comName link_td change_customize_company" onclick="viewMonitorCompanyDetail('${monitorCompany.monitorID }')"><span class="inline_div  comName_value">${monitorCompany.companyName}</span></td>
							</c:if>
						</c:if>
					</c:if>
					<c:if test="${monitorCompany.monitorDate eq today}">
						<c:if test="${monitorCompany.renameStatus ne '1'}">
							<td style="text-align:left;cursor: pointer;" class="comName link_td new_td_icon" onclick="viewTodayMonitorCompanyDetail('${monitorCompany.companyName}','${monitorCompany.monitorID }')"><span class="inline_div  comName_value">${monitorCompany.companyName}</span></td>
						</c:if>
						<c:if test="${monitorCompany.renameStatus eq '1'}">
							<td style="text-align:left;cursor: pointer;" class="comName link_td change_new_company" onclick="viewTodayMonitorCompanyDetail('${monitorCompany.companyName}','${monitorCompany.monitorID }')"><span class="inline_div  comName_value">${monitorCompany.companyName}</span></td>
						</c:if>
					</c:if>
					<td style="text-align:center">${monitorCompany.clientAccount.accountName}</td>
                    <td style="text-align:center">${monitorCompany.province}</td>
                    <td style="text-align:center">${monitorCompany.warnEventNum}/${monitorCompany.attentionEventNum}/${monitorCompany.normalEventNum}</td>
                    <td style="text-align:center">
                    	<c:if test="${empty monitorCompany.customRiskSituation}">--</c:if>
                    	<c:if test="${monitorCompany.customRiskSituation eq '0'}">正常</c:if>
                    	<c:if test="${monitorCompany.customRiskSituation eq '1'}">关注</c:if>
                    	<c:if test="${monitorCompany.customRiskSituation eq '2'}">一般预警</c:if>
                    	<c:if test="${monitorCompany.customRiskSituation eq '3'}">特别预警</c:if>
                    </td>
                    <td style="text-align:center">
                    	<c:if test="${empty monitorCompany.riskStatus}">--</c:if>
                    	<c:if test="${monitorCompany.riskStatus eq '0'}">不变</c:if>
                    	<c:if test="${monitorCompany.riskStatus eq '1'}">升高</c:if>
                    	<c:if test="${monitorCompany.riskStatus eq '2'}">降低</c:if>
                    </td>
                    <td style="text-align:center">
                    	<c:if test="${empty monitorCompany.eventLevel}">--</c:if>
                    	<c:if test="${monitorCompany.eventLevel eq '1'}">一般</c:if>
                    	<c:if test="${monitorCompany.eventLevel eq '2'}">异常</c:if>
                    	<c:if test="${monitorCompany.eventLevel eq '3'}">严重</c:if>
                    </td>
                    <td style="text-align:center">
                    	<c:if test="${empty monitorCompany.riskLabel and empty monitorCompany.customRiskLabel}">
                    		<div class="line_txt inline_div" style="width:110px;">--</div>
                    	</c:if>
                    	<c:if test="${empty monitorCompany.riskLabel and not empty monitorCompany.customRiskLabel}">
                    		<div class="line_txt inline_div" style="width:110px;" title='${monitorCompany.customRiskLabel}'>${monitorCompany.customRiskLabel}</div>
                    	</c:if>
                    	<c:if test="${not empty monitorCompany.riskLabel and empty monitorCompany.customRiskLabel}">
                    		<div class="line_txt inline_div" style="width:110px;" title='${monitorCompany.riskLabel}'>${monitorCompany.riskLabel}</div>
                    	</c:if>
                    	<c:if test="${not empty monitorCompany.riskLabel and not empty monitorCompany.customRiskLabel}">
                    		<div class="line_txt inline_div" style="width:110px;" title='${monitorCompany.riskLabel},${monitorCompany.customRiskLabel}'>${monitorCompany.riskLabel},${monitorCompany.customRiskLabel}</div>
                    	</c:if>
                    </td>
                   	<td style="text-align:center">
                   	    <c:if test="${showActionBtnFlag eq true}">
                   	       <div class="more_btn link_td relate_add_btn"  onclick="relateBlockShow('${monitorCompany.monitorID}', '${monitorCompany.companyName}')">${monitorCompany.relationCompanyNum}/${monitorCompany.relationPersonNum}</div>
                   	    </c:if>
                   		<c:if test="${showActionBtnFlag eq false}">
                   	       <div class="more_btn link_td relate_add_btn"  >${monitorCompany.relationCompanyNum}/${monitorCompany.relationPersonNum}</div>
                   	    </c:if>
                   	</td>
				</tr>
			</c:forEach>
				  
			<c:if test="${page.results.size()<10}">
				<c:forEach begin="1" end="${10-page.results.size()}">
					<tr>
						<c:if test="${showActionBtnFlag eq true }"><td></td></c:if>
						<td style="text-align:center"></td>
	                    <td style="text-align:left;padding-left: 6px;"></td>
	                    <td style="text-align:center"></td>
	                    <td style="text-align:center"></td>
	                    <td style="text-align:center;min-width:70px;"></td>
	                    <td style="text-align:center"></td>
	                    <td style="text-align:center"></td>
                    	<td style="text-align:center"></td>
	                    <td style="text-align:center"></td>
                     	<td style="text-align:center"></td>
					</tr>
				</c:forEach>
			</c:if>
		</c:if>
	</table>
</div>


