<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>
 <div class="company_mode mode_block">
                    <div class="search_block" >
                        <input type="text" class="search_input" placeholder="请输入企业名" id="rsearchInfo"/><div id="searchBtnId" class="search_btn inline_div" onclick="search4Company()"></div>
                    </div>
               
                    <div   id="tabComDiv" class="filter_block">
                    	<div class="filter_title_block">
                    		<div class="inline_div title selected" onclick="getStockHolderCompanyList()" >股东</div>
                    		<div class="inline_div title" onclick="getInvestmentCompanyList()">投资</div>
                    		<div class="relative_left_block">
                    			<div class="triangle-down-border filter_arr">
                    				<div class="triangle-down-border-content"></div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="filter_option_block hidden"></div>
                    </div> 
                  <input type="hidden" value="${companyName}" id="hcompanyName">  
				  <input type="hidden" id="totalNumStockHolderCompany" value="${stockHolderPage.recordSum}" />
                   <div id="stockHolderCompany">
				                   	<div class="company_list">
				                 	<c:if test="${empty stockHolderPage or empty stockHolderPage.results}">无记录！</c:if>
									<c:if test="${not empty stockHolderPage and not empty stockHolderPage.results}">
				                   	<c:forEach items="${stockHolderPage.results}" var="record" varStatus="i">
				                        <div class="company">
				                            <div class="inline_div blank">
				                            </div><div class="inline_div company_info">
				                                <div class="company_name">${record.companyNameInProperties}</div>
				                             <!--    <div class="info_block_list">
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
                   <div id="PageStockHolderCompanyDiv" class="page_block hidden" style="text-align:center;"></div>
                          <!-- searchResultDiv gongs sousu 结果 -->
                   <div id="searchResultDiv" class="company_list hidden" style="height:450px"></div>
                   <div id="pageDiv"></div>
                    <div class="page_block">
                        <div class="page_plug">
                        	 <div id="Pagination4Search" class="hidden"></div>          	 
                         </div>
                    </div> 
                    <!-- fenye -->
                    <!-- 投资搜索结果 -->
                   <div id="investSearchResultDiv" class="hidden"></div>
                   <div id="pageDiv"></div>
                    <div class="page_block">
                        <div class="page_plug">
                        	 <div id="PaginationInvestmentCompany" class="hidden"></div>  
                         </div>
                    </div>
                    <!-- fenye -->
               
</div>