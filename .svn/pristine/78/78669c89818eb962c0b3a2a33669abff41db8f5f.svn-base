<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>

<div class="table4" style="">
	<input type="hidden" id="totalNum4Details" value="${page.recordSum}" />
	<input type="hidden" id="eventSubType" value="${eventSubType}" />
	<input type="hidden" id="currentPageNo4Details" value="${page.currentPageNo}" />
	<input type="hidden" id="eventIdStr" value="${eventIdList}" />
	<input type="hidden" id="updateNum" value="${updateNum}" />
<!-- 	<input type="text" id="xx1" value="${eventSubType}" /> -->
<!-- 	<c:if test="${empty page  or empty page.results}">无记录！</c:if> -->
		<c:choose> 
			<c:when test="${eventSubType=='01' or eventSubType=='02' or 
			eventSubType=='03' or eventSubType=='05'}">
				
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">变更日期</td>
						<td class="table_10">变更前</td>
						<td class="table_10">变更后</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 		<td></td>
				                        <td></td>
				                        <td></td>
				                        <td></td>
				                        <td></td>
								</tr>
						</c:forEach>   
  				    </c:if>
					<c:if test="${not empty page and not empty page.results}">
						<c:forEach items="${page.results}" var="searchResult" varStatus="status">
									<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
										<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
				                           <td>${searchResult.date}</td>

				                        <td style="padding-right:10px;">${searchResult.before}</td>
				                        <td style="padding-left:10px;">${searchResult.after}</td>
				                        <td>
				                          <c:choose>
				                            <c:when test="${searchResult.level eq '一般'}">
				                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
				                            </c:when>
				                            <c:when test="${searchResult.level eq '异常'}">
				                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
				                            </c:when>
				                            <c:when test="${searchResult.level eq '严重'}">
				                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
				                            </c:when>
				                          </c:choose>
				                        </td>
									</tr>
						</c:forEach>
							<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
										<td></td>
				                        <td></td>
				                        <td></td>
				                        <td></td>
				                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>
						
			</c:when>
			
			<c:when test="${eventSubType=='04'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_15">做出决定机关</td>
						<td class="table_15">列入经营异常原因</td>
						<td class="table_10">列入日期</td>
						<td class="table_15">移出经营异常原因</td>
						<td class="table_15">移出日期</td>
						<td class="table_15">事件等级</td>
						</tr>
					</thead>
					
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
						</c:forEach>   
  				    </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.decisioninstitution}</td>
			                        <td>${searchResult.abnormalReason}</td>
			                        <td>${searchResult.inputDate}</td>
			                        <td>${searchResult.moveoutReason}</td>
			                        <td>${searchResult.outputDate}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			
			<c:when test="${eventSubType=='06'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
					    <td class="table_5">序号</td>
						<td class="table_15">之前状态</td>
						<td class="table_15">变化后状态</td>
						<td class="table_15">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
						</c:forEach>   
  				    </c:if>
					<c:if test="${not empty page and not empty page.results}">
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
								    <td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
									<td>waiting...</td>
			                        <td>waiting...</td>
			                        <td>waiting...</td>
								</tr>
					</c:forEach> 
						<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>  
				</table>		
			</c:when>
			<c:when test="${eventSubType=='07'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
					    <td class="table_5">序号</td>
						<td class="table_10">投资企业名称</td>
						<td class="table_10">工商注册号</td>
						<td class="table_10">企业类型</td>
						<td class="table_10">企业状态</td>
						<td class="table_10">注册资本(万元)</td>
						<td class="table_10">出资比例</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
						</c:forEach>   
  				    </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" ="${searchResult.dateType}" status="${searchResult.status}" class="tr_data">
								    <td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
									<td>${searchResult.entname}</td>
			                        <td>${searchResult.regno}</td>
			                        <td>${searchResult.enttype}</td>
			                        <td>${searchResult.entstatus}</td>
			                        <td>${searchResult.regcap}</td>
			                        <td>${searchResult.fundedratio}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach>
						<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			<c:when test="${eventSubType=='08'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">登记编号</td>
						<td class="table_10">登记日期</td>
						<td class="table_10">登记机关</td>
						<td class="table_10">被担保债权数额</td>
						<td class="table_10">状态</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
						</c:forEach>   
  				    </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.registrationNo}</td>
			                        <td>${searchResult.registrationDate}</td>
			                        <td>${searchResult.reOrg}</td>
			                        <td>${searchResult.guaranteeAmount}</td>
			                        <td>${searchResult.status}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach> 
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>  
				</table>		
			</c:when>
			
			<c:when test="${eventSubType=='09'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">登记编号</td>
						<td class="table_10">出质人</td>
						<td class="table_10">证照/证件号码</td>
						<td class="table_10">出质股权数额</td>
						<td class="table_10">质权人</td>
						<td class="table_10">登记日期 </td>
						<td class="table_10">公示日期</td>
						<td class="table_10">变化情况 </td>
						<td class="table_10">状态</td>
						<td  class="table_10">事件等级</td>
						</tr>
					</thead>
						<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.registrationNo}</td>
			                        <td>${searchResult.pledgor}</td>
			                        <td>${searchResult.pledgorid}</td>
			                        <td>${searchResult.amount}</td>
			                        
			                        <td>${searchResult.pawnee}</td>
			                        <td>${searchResult.registrationDate}</td>
			                        <td>${searchResult.announcedate}</td>
			                        <td>
			                         <c:choose>
			                            <c:when test="${searchResult.change eq '无'}">
			                                ${searchResult.change}
			                            </c:when>
			                             <c:otherwise>  
 											<a href="${searchResult.change}" style="color: #618dbe;cursor:pointer;" target="_blank">变更详情</td>
  										</c:otherwise>  

			                          </c:choose>
			                      
			                        <td>${searchResult.status}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach> 
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>  
				</table>		
			</c:when>
			
			<c:when test="${eventSubType=='10'}">
				waiting...
			</c:when>
			
			<c:when test="${eventSubType=='11'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">作出行政处罚决定日期</td>
						<td class="table_10">作出行政处罚决定机关名称</td>
						<td class="table_10">行政处罚决定书文号</td>
						<td class="table_10">违法行为类型</td>
						<td class="table_10">行政处罚内容</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
                    <c:if test="${not empty page and not empty page.results}">
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.penalty_decisiondate}</td>
			                        <td>${searchResult.penalty_decisioninsititution}</td>
			                        <td>${searchResult.penalty_code}</td>
			                        <td>${searchResult.penalty_illegaltype}</td>
			                        <td>${searchResult.penalty_decisioncontent}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
			       
								</tr>
					</c:forEach>
						<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			<c:when test="${eventSubType=='12'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">企业名称</td>
						<td class="table_10">时间</td>
						<td class="table_10">详情</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			               
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
			                       <td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                       <td>${searchResult.enterprisename}</td>
			                       <td>${searchResult.publishtime}</td>
			                       <td>
			                       <a href="${searchResult.website}" style="color: #618dbe;cursor:pointer;" target="_blank">变更详情
			                       </td>			              
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			<c:when test="${eventSubType=='13'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">受检产品</td>
						<td class="table_10">商标</td>
						<td class="table_10">规格型号</td>
						<td class="table_10">批号/生产日期</td>
						<td class="table_10">生产企业</td>
						<td class="table_10">受检企业</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
			                        <td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.product}</td>
			                        <td>${searchResult.trademark}</td>
			                        <td>${searchResult.ggxh}</td>
			                        <td>${searchResult.product_time}</td>
			                        <td>${searchResult.productEnterprise}</td>
			                        <td>${searchResult.checkEnterprise}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>

					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		 <td></td>
			                        <td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			
			<c:when test="${eventSubType=='14'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">企业名称</td>
						<td class="table_10">社保缴纳情况</td>
						<td class="table_10">发布时间</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.enterprisename}</td>
			                        <td>${searchResult.items}</td>
			                        <td>${searchResult.time}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
			                        
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
				<c:when test="${eventSubType=='15'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">被处罚人名称</td>
						<td class="table_10">违法行为类别</td>
						<td class="table_10">处罚内容</td>
						<td class="table_10">处罚决定时间</td>
						<td class="table_10">处罚单位</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		 <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
			                        <td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.enterprisename}</td>
			                        <td>${searchResult.type}</td>
			                        <td>${searchResult.penalty_unit}</td>
			                        <td>${searchResult.decide_time}</td>
			                        <td>${searchResult.penalties}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			<c:when test="${eventSubType=='16'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>	
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10"> 企业名称</td>
						<td class="table_10">税务类型</td>
						<td class="table_10">欠税金额</td>
						<td class="table_10">日期</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.qymc}</td>
			                        <td>${searchResult.qssz}</td>
			                        <td>${searchResult.qsje}</td>
			                        <td>${searchResult.xjrq}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
			                      
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			
			<c:when test="${eventSubType=='17'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_10">序号</td>
						<td class="table_10">征管分局</td>
						<td class="table_10">纳税人识别码</td>
						<td class="table_10">企业名称</td>
						<td class="table_10">法定代表人(负责人)</td>
						<td class="table_10">日期</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.zgfj}</td>
			                        <td>${searchResult.nsrsbm}</td>
			                        <td>${searchResult.qymc}</td>
			                        <td>${searchResult.fddbr}</td>
			                        <td>${searchResult.rdrq}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
			                        
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			<c:when test="${eventSubType=='188'}">
				<table class="first_col_table data_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5" style="width:60px">序号</td>
						<td class="table_10" style="width:120px">判决时间</td>
						<td class="table_10" style="width:80px">身份</td>
						<td class="">标题</td>
						<td class="table_10" style="width:80px">文书类型</td>
						<td class="table_10" style="width:80px">详情</td>
						<td class="table_10" style="width:80px">事件等级</td>
						</tr>
					</thead>
						<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}</td>
			                        <td>${searchResult.decisionDate}</td>
			                        <td>${searchResult.shuxing}</td>
			                        <td>${searchResult.title}</td>
			                        <td>
			                            <span>${searchResult.instrumenttype}</span>
			                            <c:if test="${not empty searchResult.result or not empty searchResult.amount }">
				                        	<div class="absolute_block">
				                        		<div class="relative_center_block case_result_block" style="">
				                        			<style>
				                        				.case_result_block{
			                        					top:-18px;
			                        					text-align:left;
			                        					left:118px;
			                        					width:10px;
			                        				}
			                        				.case_result{
			                        					padding:0px 5px;border:1px solid #2ea7e0;font-size:12px;color:#2ea7e0;border-radius:2px;
			                        					max-width:100px;
			                        				}
			                        				.triangle_left_border{
			                        					    width: 0;
														    height: 0;
														    border-top: 4px solid transparent;
														    border-right: 6px solid #2ea7e0;
														    border-bottom: 4px solid transparent;
														    position: relative;
														    left: -5px;
														    top: -13px;
			                        				}
			                        				.triangle_left_center{
			                        					    width: 0;
														    height: 0;
														    border-top: 3px solid transparent;
														    border-right: 4px solid #ffffff;
														    border-bottom: 3px solid transparent;
														    position: relative;
														    top: -3px;
														    left: 2px;
			                        				}
				                        			</style>
				                        			<div class="inline_div case_result line_txt" title="${searchResult.result }   ${searchResult.amount }" style="">

				                        				${searchResult.result }&nbsp;&nbsp;${searchResult.amount }
				                        			</div>
				                        						                        			<div class="triangle_left_border">
			                        				<div class="triangle_left_center"></div>
			                        			</div>
				                        			
				                        		</div>
			                        		</div>
		                        		</c:if>
			                        </td>
			                        <td style="cursor:pointer;" class="sfwsDetails link_td" judgmentId=${searchResult.judgmentId} eventId=${searchResult.eventId}>详情</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
					<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data hidden">
						<td rowspan="2">11</td>
						<td>2016-06-06</td>
                        <td></td>
                        <td>重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业重庆美新企业</td>
                        <td></td>
                        <td></td>
                        <td></td>
					</tr>
					<tr class="hidden">
						<style>
							.relative_notice_title{
								margin:0px 0px 0px 200px;
								font-size:18px;
								color:#333333;
								text-align:left;
							}
							.relative_notice_block{
								padding:10px 0px;
							}
							.relative_notice_block div:nth-child(1){
								width:120px;
							}
							.relative_notice_block div:nth-child(2){
								width:80px;
							}
							.relative_notice_block div:nth-child(3){
								width:645px;text-align:left;
							}
							.relative_notice_block div:nth-child(4){
								width:80px;
							}
							.relative_notice_block div:nth-child(5){
								width:80px;
							}
							.relative_notice_block .notice_name_round{
								width:20px;
								height:20px;
								text-align:center;
								margin: 0px 0px 0px 10px;
							}
							.relative_notice_block .notice_name_round .round_line{
								height:15px;
								width:1px;
								background:#7f7f7f;
								position:relative;
								top:1px;
							}
							.relative_notice_block:last-child .notice_name_round .round_line{
								display:none;
							}
							.relative_notice_block .notice_name_round .outer_round{
								    background: #7f7f7f;
								    border-radius: 50%;
								    width: 16px;
								    height: 16px;
							}
							.relative_notice_block .notice_name_round .inner_round{
								    background: #7f7f7f;
								    border-radius: 50%;
								    border: 3px solid #ffffff;
								    width: 8px;
								    height: 8px;
								    position: relative;
								    top: -3px;
								    left: 0px;
							}
							.relative_notice_block .notice_name_block{
								width:609px;
								text-align:left;
							}
							.relative_notice_block .notice_name_block span{
								padding:0px 0px 0px 5px;
							}
							.relative_notice_block .notice_name_block span:nth-child(1){
								max-width:100px;
							}
							.relative_notice_block .notice_name_block span:nth-child(2){
								max-width:280px;
							}
							.relative_notice_block .notice_name_block span:nth-child(3){
								max-width:200px;
							}
						</style>
						<td colspan="5" style="border-right:0px;">
							<div class="relative_notice_title">【关联文书】</div>
							<div class="inline_div_block  relative_notice_block">
								<div class="inline_div">
									2012-06-06
								</div>
								<div class="inline_div">
									被告
								</div>
								<div class="inline_div">
									<span class="inline_div notice_name_round">
										<i class="outer_round inline_div">
											<i class="inner_round inline_div""></i>
										</i>
										<i class="inline_div round_line"></i>
									</span>
									<span class="inline_div inline_div_block notice_name_block">
										<span class="inline_div line_txt">二审</span>
										<span class="inline_div line_txt">辽宁省大连市中级人民银行法院</span>
										<span class="inline_div line_txt">（2016）辽02民中6388号</span>
									</span>
								</div>
								<div class="inline_div">
									裁定书
								</div>
								<div class="inline_div">
									<span style="cursor:pointer;" class="sfwsDetails link_td" judgmentId=${searchResult.judgmentId} eventId=${searchResult.eventId}>详情</span>
								</div>
							</div>
							<div class="inline_div_block  relative_notice_block">
								<div class="inline_div">
									2012-06-06
								</div>
								<div class="inline_div">
									被告
								</div>
								<div class="inline_div">
									<span class="inline_div notice_name_round">
										<i class="outer_round inline_div">
											<i class="inner_round inline_div""></i>
										</i>
										<i class="inline_div round_line"></i>
									</span>
									<span class="inline_div inline_div_block notice_name_block">
										<span class="inline_div line_txt">二审</span>
										<span class="inline_div line_txt">辽宁省大连市中级人民银行法院</span>
										<span class="inline_div line_txt">（2016）辽02民中6388号</span>
									</span>
								</div>
								<div class="inline_div">
									裁定书
								</div>
								<div class="inline_div">
									<span style="cursor:pointer;" class="sfwsDetails link_td" judgmentId=${searchResult.judgmentId} eventId=${searchResult.eventId}>详情</span>
								</div>
							</div>
							<div class="inline_div_block  relative_notice_block">
								<div class="inline_div">
									2012-06-06
								</div>
								<div class="inline_div">
									被告
								</div>
								<div class="inline_div">
									<span class="inline_div notice_name_round">
										<i class="outer_round inline_div">
											<i class="inner_round inline_div""></i>
										</i>
										<i class="inline_div round_line"></i>
									</span>
									<span class="inline_div inline_div_block notice_name_block">
										<span class="inline_div line_txt">二审</span>
										<span class="inline_div line_txt">辽宁省大连市中级人民银行法院</span>
										<span class="inline_div line_txt">（2016）辽02民中6388号</span>
									</span>
								</div>
								<div class="inline_div">
									裁定书
								</div>
								<div class="inline_div">
									<span style="cursor:pointer;" class="sfwsDetails link_td" judgmentId=${searchResult.judgmentId} eventId=${searchResult.eventId}>详情</span>
								</div>
							</div>
							<div class="inline_div_block  relative_notice_block">
								<div class="inline_div">
									2012-06-06
								</div>
								<div class="inline_div">
									被告
								</div>
								<div class="inline_div">
									<span class="inline_div notice_name_round">
										<i class="outer_round inline_div">
											<i class="inner_round inline_div""></i>
										</i>
										<i class="inline_div round_line"></i>
									</span>
									<span class="inline_div inline_div_block notice_name_block">
										<span class="inline_div line_txt">二审二审二审二审二审二审</span>
										<span class="inline_div line_txt">辽宁省大连市中级人民银行法院省大连市中级人民银行法院</span>
										<span class="inline_div line_txt">（2016）辽02民中6388号02民中6388号</span>
									</span>
								</div>
								<div class="inline_div">
									裁定书
								</div>
								<div class="inline_div">
									<span style="cursor:pointer;" class="sfwsDetails link_td" judgmentId=${searchResult.judgmentId} eventId=${searchResult.eventId}>详情</span>
								</div>
							</div>
						</td>
						<td> <div class="event_level hight_risk_back inline_div" style="margin:20px 0px 0px 0px;">严重</div></td>
					</tr>
				</table>		
			</c:when>
			<c:when test="${eventSubType=='18' or eventSubType=='19' or eventSubType=='20'}">
				<table class="first_col_table data_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">判决时间</td>
						<td class="table_10">身份</td>
						<td class="table_10">标题</td>
						<td class="table_10">文书类型</td>
						<td class="table_10">详情</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
						<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}</td>
			                        <td>${searchResult.decisionDate}</td>
			                        <td>${searchResult.shuxing}</td>
			                        <td>${searchResult.title}</td>
			                        <td>
			                            <span>${searchResult.instrumenttype}</span>
			                            <c:if test="${not empty searchResult.result or not empty searchResult.amount }">
				                        	<div class="absolute_block">
				                        		<div class="relative_center_block case_result_block" style="">
				                        			<style>
				                        				.case_result_block{
			                        					top:-18px;
			                        					text-align:left;
			                        					left:118px;
			                        					width:10px;
			                        				}
			                        				.case_result{
			                        					padding:0px 5px;border:1px solid #2ea7e0;font-size:12px;color:#2ea7e0;border-radius:2px;
			                        					max-width:100px;
			                        				}
			                        				.triangle_left_border{
			                        					    width: 0;
														    height: 0;
														    border-top: 4px solid transparent;
														    border-right: 6px solid #2ea7e0;
														    border-bottom: 4px solid transparent;
														    position: relative;
														    left: -5px;
														    top: -13px;
			                        				}
			                        				.triangle_left_center{
			                        					    width: 0;
														    height: 0;
														    border-top: 3px solid transparent;
														    border-right: 4px solid #ffffff;
														    border-bottom: 3px solid transparent;
														    position: relative;
														    top: -3px;
														    left: 2px;
			                        				}
				                        			</style>
				                        			<div class="inline_div case_result line_txt" title="${searchResult.result }   ${searchResult.amount }" style="">

				                        				${searchResult.result }&nbsp;&nbsp;${searchResult.amount }
				                        			</div>
				                        						                        			<div class="triangle_left_border">
			                        				<div class="triangle_left_center"></div>
			                        			</div>
				                        			
				                        		</div>
			                        		</div>
		                        		</c:if>
			                        </td>
			                        <td style="cursor:pointer;" class="sfwsDetails link_td" judgmentId=${searchResult.judgmentId} eventId=${searchResult.eventId}>详情</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			<c:when test="${eventSubType=='21'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">立案时间</td>
						<td class="table_10">案号</td>
						<td class="table_10">姓名/名称</td>
						<td class="table_10">执行标的</td>
						<td class="table_10">执行法院</td>
						<td class="table_5">案件状态</td>
						<td class="table_5">异常次数</td>
						<td class="table_5">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td >${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.registerTime}</td>
			                        <td>${searchResult.caseCode}</td>
			                        <td>${searchResult.name}</td>
			                        <td>${searchResult.zxbd}</td>
			                        <td>${searchResult.courtName}</td>
			                        <td>${searchResult.caseStatus}</td>
			                        <td>${searchResult.focusNum}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                       	<td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			
			<c:when test="${eventSubType=='22'}">
				<table class="first_col_table data_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">发布时间</td>
						<td class="table_10">案号</td>
						<td class="table_10">省份</td>
						<td class="table_10">执行法院</td>
						<td class="table_10">被执行人履行情况</td>
						<td class="table_10">详情</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                        <td></td>
			                      
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.fbsj}</td>
			                        <td>${searchResult.ah}</td>
			                        <td>${searchResult.sf}</td>
			                        <td>${searchResult.zxfy}</td>
			                        <td>${searchResult.lxqk}</td>
			                      	<td style="cursor:pointer;" class="sxDetails link_td" eventId=${searchResult.eventId}>详情</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>			                        
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                    
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                       	<td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			
		
			<c:when test="${eventSubType=='23'}">
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">开庭日期</td>
						<td class="table_10">案号</td>
						<td class="table_10">案由</td>
						<td class="table_10">当事人</td>
						<td class="table_10">法院</td>
						<td class="table_10">审理法庭</td>
						<td class="table_10">审理法官</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
					<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
							  	</tr>
						</c:forEach> 
					</c:if>  
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.trialDate}</td>
			                        <td>${searchResult.anHao}</td>
			                        <td>${searchResult.origin}</td>
			                        <td>${searchResult.privy}</td>
			                        <td>${searchResult.executeCourt}</td>
			                         <td>${searchResult.trialCourt}</td>
			                        <td>${searchResult.trialJudge}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
			                     
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                     	<td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                       	<td></td>
			                        <td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>
			<c:when test="${eventSubType=='24' or eventSubType=='25'}">
				<table class="first_col_table data_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">标题</td>
						<td class="table_10">时间</td>
						<td class="table_10">来源</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
									<td style="cursor:pointer;" class="newsDetails link_td" eventId=${searchResult.eventId}>${searchResult.title}</td>
			                        <td>${searchResult.pubtime}</td>
			                        <td>${searchResult.source}</td>
								</tr>
					</c:forEach>
					<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>   
				</table>		
			</c:when>

			<c:when test="${eventSubType=='27'}">
				waiting...
			</c:when>
			<c:when test="${eventSubType=='28'}">
				waiting...
			</c:when>
			<c:when test="${eventSubType=='29'}">
			
				<table class="first_col_table" border="0" cellspacing="1">
					<thead>
					<tr>
						<td class="table_5">序号</td>
						<td class="table_10">商标名称</td>
						<td class="table_10">商标图片</td>
						<td class="table_10">申请时间</td>
						<td class="table_10">状态</td>
						<td class="table_10">注册号</td>
						<td class="table_10">类别</td>
						<td class="table_10">事件等级</td>
						</tr>
					</thead>
					<c:if test="${empty page  or empty page.results}">   
   						<c:forEach begin="1" end="6">
								<tr>
								 	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                 		<td></td>
			                        <td></td>
							  	</tr>
						</c:forEach>   
  				   	 </c:if>
					<c:if test="${not empty page and not empty page.results}">					
					<c:forEach items="${page.results}" var="searchResult" varStatus="status">
								<tr id="tr_${status.index}" status="${searchResult.status}" class="tr_data">
									<td>${ (page.currentPageNo-1)*page.pageSize +status.index+1}<input type="hidden" value="${searchResult.eventId }"/></td>
			                        <td>${searchResult.brand_name}</td>
			                        <td><img src="data:image/jpg;base64,${searchResult.img_str}"></td>
			                        <td>${searchResult.apply_time}</td>
			                        <td>${searchResult.brand_status}</td>
			                        <td>${searchResult.reg_no}</td>
			                        <td>${searchResult.cat_no}</td>
			                        <td>
			                          <c:choose>
			                            <c:when test="${searchResult.level eq '一般'}">
			                               <div class="event_level low_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '异常'}">
			                               <div class="event_level middle_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                            <c:when test="${searchResult.level eq '严重'}">
			                               <div class="event_level hight_risk_back inline_div">${searchResult.level}</div>
			                            </c:when>
			                          </c:choose>
			                        </td>
								</tr>
					</c:forEach> 
						<c:if test="${page.results.size()<6}">
								<c:forEach begin="1" end="${6-page.results.size()}">
								<tr>
									<td></td>
			                        <td></td>
			                    	<td></td>
			                        <td></td>
			                        <td></td>
			                        <td></td>
			                   		<td></td>
			                        <td></td>
			                       	
								</tr>
								</c:forEach>   
							</c:if>
					</c:if>  
				</table>		
			</c:when>
		</c:choose>
<!-- 	    <c:if test="${empty page  or empty page.results}">无记录！</c:if> -->
</div>


