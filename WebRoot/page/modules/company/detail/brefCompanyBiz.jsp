<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
  <head>
  	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/history.css?${appVersion }">
	<script type="text/javascript" src="${ctx }/js/echarts/echarts.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/echarts/echartController.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/company/detail/biz/biz.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/blacklist/blacklist.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
    <script type="text/javascript" src="${ctx }/js/modules/monitor/company_rename.js?${appVersion }"></script>
    <script type="text/javascript" src="${ctx }/js/modules/monitor/brefCompanyBiz.js?${appVersion }"></script>
  </head>
  
  <body>
    <!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	<input id="deep"  name="deep" type="hidden" value="${deep}"/>
    <div class="body_content_5">
        <div class="body_center ">

            <div class="body_block_2 block_shadow">
                <div class="nav_bar">
                    <div class="back_block">
                        <div class="inline_div">
                                    <div class="for_back_btn"></div>
                                </div>
                    </div>
                    <span><a href="#">首页</a></span>
                    <span>></span>
                    <span><a href="#">监控企业列表</a></span>
                    <span>></span>
                    <span><a href="#">监控企业详情</a></span>
                    <span>></span>
                    <span class="current_nav">企业详情</span>
                </div>
                <div class="history_content">
                    <div class="company_title">
                        <div class="monitor_company_name inline_div" id="MonitorCompanyName">
                            ${companyName}
                            
                        </div>
                        <span class="used_name hidden" style="left:0px;top:-4px;">（曾用名：安徽厦门松霖科技有限公司）</span>
                    </div>
                    <div class="company_modules_blodk">
                        <div class="company_modules">
                            <div class="modules_list">
                                <div class="modules inline_div selected">
                                    <div class="inline_div name">工商信息</div>
                                    <div class="inline_div value">
                                    ${guDongPage.size + zhuYaoRenYuanPage.size + bianGengJiLuPage.size }
									</div>
                                </div>
                                <div class="modules inline_div">
                                    <div class="inline_div name">失信信息</div>
                                </div>
                            </div>
                            <div class="relative_center_block">
                                <div class="modules_bottom_block">
                                    <div class="modules_bottom_selector"></div>
                                    <div class="modules_bottom_hover"></div>
                                    <div class="modules_bottom_line"></div>
                                </div>
                            </div>
                            <div class="modules_sub_list">
                                <div class="modules_sub_list_1 current_sub_list sub_list">
                                    <div class="sub_modules inline_div selected">
                                                                                                                           登记信息
                                    </div>
                                    <div class="sub_modules inline_div ">
                                                                                                                    股东信息&nbsp;
                                        (<span class="value">${guDongPage.size }</span>)
                                    </div>
                                    <div class="sub_modules inline_div ">
                                                                                                                     主要人员&nbsp;
                                       (<span class="value">${zhuYaoRenYuanPage.size }</span>)
                                    </div>
                                    <div class="sub_modules inline_div ">
                                                                                                                    变更记录&nbsp;
                                       (<span class="value">${bianGengJiLuPage.size }</span>)
                                    </div>
                                </div>
                                <div class="modules_sub_list_2 current_sub_list sub_list hidden">
                                    <div class="sub_modules inline_div selected">
                                                                                                                           系统信息
                                    </div>
                                    <div class="sub_modules inline_div">
                                                                                                                           自定义
                                    </div>
                                </div>
                               
                            </div>
                        </div>
                    </div>
                    <div class="modules_block_list">
                    	<div class="modules_block current_modules">
                    	    <jsp:include page="biz/brefBizDetail.jsp"></jsp:include>
                    	</div>
                        <div class="modules_block hidden" id="BlacklistModuleBlock">
                            <div class="info_block" id="SystemBlacklistMessageBlock">
                                <div class="info_title">
                                    <div class="inline_div title">系统信息</div>
                                    <div class="inline_div count hidden"></div>
                                    <div class="inline_div message hidden"></div>
                                </div>
                                <div class="info_list">
                                    <div>该企业今年无失信信息！</div>
                                </div>
                            </div>
                    	    <div class="info_block" id="UserBlacklistMessageBlock">
                                <div class="info_title">
                                    <div class="inline_div title">自定义</div>
                                    <div class="inline_div count hidden"></div>
                                    <div class="inline_div message hidden"></div>
                                </div>
                                <div class="info_list">
                                    <div>该企业今年无失信信息！</div>
                                </div>
                            </div>
                    	</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <link type="text/css" rel="stylesheet" href="${ctx }/css/common/layout.css">
    <link type="text/css" rel="stylesheet" href="${ctx }/css/fonts/font-awesome.min.css">
    <style>
        #BlacklistModuleBlock .info_list div{font-size:14px;color:#666666;margin:10px;}
    </style>
  </body>
    
</html>
