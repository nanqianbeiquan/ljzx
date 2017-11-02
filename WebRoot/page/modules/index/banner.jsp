<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<head>
	<%@ include file="/page/common/base.jsp"%>
	
    <script type="text/javascript" src="${ctx }/js/modules/index/banner.js?${appVersion }"></script>
    
    <script type="text/javascript">
    
    	$(document).ready(function() {
    	
	    	$('#keyword').bind('keypress',function(event){
		        if(event.keyCode == "13"){
		        	searchCompany();
		        }
		    });
		    
		});
    
    </script>
</head>

<div id="nav_head" class="nav_head white head_shadow">
    <div class="head_back">
        <div class="back_color"></div>
    </div>
    
    <div class=" head_right inline_div_block">
        <div class="inline_div">
            <div class="message_count">
            	<c:if test="${unreadMessageNum > 0 }">
            		<div class="inline_div">${unreadMessageNum }</div>
            	</c:if>
            </div>
            <div class="message_img inline_div" onclick="toMessageList()">
                
            </div>
        </div>
        <div class="inline_div head_img_hover">
            <div class="config_img inline_div">
                <div class="user_menu_block">
                    <div class="user_menu">
                    	<c:if test="${account.canCreateChild eq '1'}">
	                    	<a class="menu" href="${ctx }/clientAccount/toClientAccountManage">
	                            
	                            <div class="inline_div value">账户管理</div>
	                        </a>
                    	</c:if>
                    	<a class="menu" href="${ctx }/clientAccount/toClientAccountInfo">
                            
                            <div class="inline_div value">账户信息</div>
                        </a>
                        <c:if test="${account.type ne '3'}">
                        	<a class="menu" href="${ctx }/monitorConfigure/toMonitorSetting">
                            	
	                            <div class="inline_div value">动态监控</div>
	                        </a>
                        </c:if>
                        <a class="menu" href="${ctx }/clientAccount/toSecurityCenter">
                            
                            <div class="inline_div value">安全中心</div>
                        </a>
                        <a class="menu" href="${ctx }/log/toLogListView">
                            
                            <div class="inline_div value">操作日志</div>
                        </a>
                    </div>
            	</div>
            </div>
        </div>
        <div class="inline_div head_img_hover">
        	<div class="user_img inline_div">
                <div class="user_menu_block">
                    <div class="user_menu">
                    	<c:if test="${childAccountNum gt 0}">
                    		<a class="menu" href="${ctx }/toSummaryIndex?accountId=${masterAccount.accountId}&preDeep=${not empty deep?deep:0}" >
                            
	                            <div class="inline_div value">${masterAccount.accountName}</div>
	                        </a>
	                        <a class="menu" href="${ctx }/index?accountId=${masterAccount.accountId}&preDeep=${not empty deep?deep:0}" >
	                            
	                            <div class="inline_div value">${masterAccount.accountName}（子）</div>
	                        </a>
                    	</c:if>
                        <c:forEach var="childAccount" items="${childAccountParamList}" >
                        	<c:if test="${childAccount.isHaveChild eq '0'}">
                        		<a class="menu" href="${ctx }/index?accountId=${childAccount.accountId}&preDeep=${not empty deep?deep:0}" >
                            		
		                            <div class="inline_div value">${childAccount.accountName}</div>
		                    	</a>
                        	</c:if>
                        	<c:if test="${childAccount.isHaveChild eq '1'}">
                        		<a class="menu" href="${ctx }/toSummaryIndex?accountId=${childAccount.accountId}&preDeep=${not empty deep?deep:0}" >
                            		
		                            <div class="inline_div value">${childAccount.accountName}</div>
		                    	</a>
                        	</c:if>
                        	
                        </c:forEach>
                        <a class="menu" href="${ctx }/logout" onclick="javascript:location.replace(this.href); event.returnValue=false; ">

                            <div class="inline_div value"><img style="position:relative;top:-1px;"  src="${ctx }/images/common/logout_icon.png"/>退出</div>
                        </a>
                    </div>
            	</div>
        	</div>
        </div>
        <div class="account inline_div">
            <span>账户号:</span>
            <span class="user_account">${account.accountName}</span>
        </div>
    </div>
    
    <div class=" head_left">
    	<c:if test="${childAccountNum gt 0}">
    		<a href="${ctx }/toSummaryIndex"><img class="inline-div" src="${ctx }/images/common/logo.png"/></a>
    	</c:if>
        <c:if test="${childAccountNum eq 0}">
    		<a href="${ctx }/index"><img class="inline-div" src="${ctx }/images/common/logo.png"/></a>
    	</c:if>
        <div class="inline_div search_company_head">
            <input type="text" id="keyword" name="keyword" value="${keyword }" class="search_input" placeholder="请搜索企业并加入监控"/><div class="search_btn inline_div" onclick="searchCompany();"></div>
        </div>
    </div>
</div>
