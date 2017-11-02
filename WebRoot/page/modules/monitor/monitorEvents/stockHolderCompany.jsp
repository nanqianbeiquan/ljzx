<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>
<input type="hidden" id="totalNumStockHolderCompany" value="${stockHolderPage.recordSum}" />
<input type="hidden" value="${companyName}" id="hcompanyName">
 <div class="company_mode mode_block">
				                   	<div class="company_list">
									<c:if test="${empty stockHolderPage or empty stockHolderPage.results}">无记录！</c:if>
									<c:if test="${not empty stockHolderPage and not empty stockHolderPage.results}">
				                   	<c:forEach items="${stockHolderPage.results}" var="record" varStatus="i">
				                        <div class="company">
				                            <div class="inline_div blank">
				                            </div><div class="inline_div company_info">
				                                <div class="company_name">${record.companyNameInProperties}</div>
				                                <!-- <div class="info_block_list">
				                                    <div class="inline_div info_block">
				                                        <div class="info">王健林</div>
				                                        <div class="span">法定代表人</div>
				                                    
				                                    </div><div class="inline_div info_block">
				                                        <div class="info">100万</div>
				                                        <div class="span">注册资本</div>
				                                    
				                                    </div><div class="inline_div info_block">
				                                        <div class="info">2010-02-12</div>
				                                        <div class="span">成立日期</div>
				                                    
				                                    </div><div class="inline_div info_block">
				                                        <div class="info">不祥</div>
				                                        <div class="span">经营状态</div>
				                                    </div>
				                                </div> -->
				                            
				                            </div>
				                            <c:if test="${record.existFlag==true }">
					                           <div class="inline_div add_btn" existFlag=${record.existFlag} enterpriseName=${record.companyNameInProperties} >
					                                <div class="inline_div" style="width:46px;height:21px;">已添加</div>
					                            </div>
						                    </c:if>
				                            <c:if test="${record.existFlag==false }">
					                            <div class="inline_div add_btn" enterpriseName=${record.companyNameInProperties} >
					                                <div class="small_btn inline_div">添加</div>
					                            </div>
				                            </c:if>
				                        </div>
				                       </c:forEach>
				                       </c:if>
				                    </div>
</div>