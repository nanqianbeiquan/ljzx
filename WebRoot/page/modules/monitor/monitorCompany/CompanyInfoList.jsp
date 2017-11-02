<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>

<div class="table4">
	<input type="hidden" id="totalNum4Search" value="${page.recordSum}" />
	<c:if test="${empty page  or empty page.results}">无记录！</c:if>
	<c:if test="${not empty page and not empty page.results}">
        <div class="search_result" style="margin-left:30px;margin-bottom:15px;">找到&nbsp;${total }&nbsp;条相关结果,为您展示匹配度高的&nbsp;${page.recordSum}&nbsp;条</div>
		<c:forEach items="${page.results}" var="searchResult" varStatus="status">
		  
		  <div class="company" style="">
                            <div class="inline_div blank"></div>
                            <div class="inline_div company_info">
                                <div class="company_name">${searchResult.registerInfo.enterpriseName}</div> 
                                <div class="">
                                    <div class="inline_div info_block">
                                        <div class="info">${searchResult.registerInfo.legalRepresentative}</div>
                                        <div class="span">法定代表人</div>
                                    
                                    </div><div class="inline_div info_block">
                                        <div class="info">${searchResult.registerInfo.registeredCapital}</div>
                                        <div class="span">注册资本</div>
                                    
                                    </div><div class="inline_div info_block">
                                        <div class="info">${searchResult.registerInfo.establishmentDate}</div>
                                        <div class="span">成立日期</div>
                                    
                                    </div><div class="inline_div info_block">
                                        <div class="info" style="overflow: hidden;text-overflow: ellipsis;height:15px;white-space: nowrap;">${searchResult.registerInfo.operateStatus}</div>
                                        <div class="span">经营状态</div>
                                    </div>
                                </div>
                            
                            </div>
                            <div class="inline_div add_btn" enterpriseName=${searchResult.registerInfo.enterpriseName}>
                                <div class="small_btn inline_div">添加</div>
                            </div><div class="inline_div blank"></div>
                        </div>    
		</c:forEach>   
		
	</c:if>
</div>


