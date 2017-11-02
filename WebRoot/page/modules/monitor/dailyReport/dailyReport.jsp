<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML>
<html>
  <head>
   	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
    <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/summary.css">

    <script type="text/javascript" src="${ctx }/js/echarts/echarts.js"></script>
    <script type="text/javascript" src="${ctx }/js/echarts/echartController.js"></script>
    <script type="text/javascript" src="${ctx }/js/modules/monitor/summary.js"></script>
    <script type="text/javascript">
       $(function(){
		 
		    
		})
    </script>
  </head>
  
  <body class="ljzx_page back_gray">
     <div id="nav_head" class="nav_head white">
         <div class="float_left head_left">
             <img class="inline-div" src="${ctx}/images/modules/index/logo.png"/>
             <div class="inline_div search_company_head">
                 <input type="text" class="search_input" placeholder="请输入企业名"/>
             </div><div class="search_btn inline_div"></div>
         </div>
         <div class="float_right head_right">
             <div class="account">
                 <span>账户号:</span>
                 <span class="user_account">ABCDEFGHIJKLMNOPQRSTUVWXYZ</span>
             </div>
         </div>
         <div class="head_center">
             
         </div>
     </div>
	
	 <div class="body_content">
	     
	     <div class="body_center ">
	         <div class="body_block">
	             <div class="body_block_title">
	                 动态监控
	             </div>
	             <div class="">
	
	             </div>
	         </div>
	         <!-- report作用域开始 -->
	         <div class="body_inline">
	             <div class="body_inline_block inline_div width_50">
	                 <div class="body_block_title">
	                                          事件情况
	                 </div>
	                 <div id="monitor_events" class="line_echart_block">
	
	                 </div>
	             </div>
	             <div class="body_inline_block inline_div width_50">
	                 <div class="body_block_title">
	                                         风险预警
	                 </div>
	                 <div class="level_block">
	                     <div class="level_show">
	                         <div class="inline_div level_color hight_risk_back"></div>
	                         <div class="inline_div">
	                             高风险(&nbsp;<span id="highRiskPersent"></span>&nbsp;)
	                         </div>
	                     </div>
	                     <div class="level_show">
	                         <div class="inline_div level_color middle_risk_back"></div>
	                         <div class="inline_div">
	                             中风险(&nbsp;<span id="middleRiskPersent"></span>&nbsp;)
	                         </div>
	                     </div>
	                     <div class="level_show">
	                         <div class="inline_div level_color low_risk_back"></div>
	                         <div class="inline_div">
	                             低风险(&nbsp;<span id="lowRiskPersent"></span>&nbsp;)
	                         </div>
	                     </div>
	                     <div class="level_show">
	                         <div class="inline_div level_color no_risk_back"></div>
	                         <div class="inline_div">
	                             无风险(&nbsp;<span id="noRiskPersent"></span>&nbsp;)
	                         </div>
	                     </div>
	                 </div>
	                 <div id="monitor_warning" class="line_echart_block">
	
	                 </div>
	                 
	             </div>
	         </div>
	         <div class="body_block">
	             <div class="body_block_title">
	                 风险区域分布
	             </div>
	             <div class="monitor_range_list_block ">
	                 <div class="monitor_range_list inline_div">
	                     <div class="list_title">企业分布于<span>23</span>个省市地区</div>
	                     <div class="list_sub_title">高风险排名前五区域</div>
	                     <div class="list">
	                         <div id="rank1" class="li">
	                             <div class="inline_div span_1 span">1</div>
	                             <div class="inline_div name">--</div>
	                             <div class="inline_div num">--%（--家）</div>
	                         </div>
	                         <div id="rank2" class="li">
	                             <div class="inline_div span_2 span">2</div>
	                             <div class="inline_div name">--</div>
	                             <div class="inline_div num">--%（--家）</div>
	                         </div>
	                         <div id="rank3" class="li">
	                             <div class="inline_div span_3 span">3</div>
	                             <div class="inline_div name">--</div>
	                             <div class="inline_div num">--%（--家）</div>
	                         </div>
	                         <div id="rank4" class="li">
	                             <div class="inline_div span_4 span">4</div>
	                             <div class="inline_div name">--</div>
	                             <div class="inline_div num">--%（--家）</div>
	                         </div>
	                         <div id="rank4" class="li">
	                             <div class="inline_div span_5 span">5</div>
	                             <div class="inline_div name">--</div>
	                             <div class="inline_div num">--%（--家）</div>
	                         </div>
	                     </div>
	                 </div>
	              </div>
	             <div id="monitor_area" class="line_echart_block">
	
	             </div>
	         </div>
             <!-- report作用域结束 -->
	         <div class="body_block">
	             <div class="inline_div width_50">
	                 <div class="body_block_title">
	                     风险事件变化情况
	                 </div>
	                 <div class="calender_selector_block">
	                     <div id="eventSwitchCNT" class="inline_div calender_selector">
	                         <div id="eventSwitch1" class="inline_div day_select select selected" onclick="applyReportList('applyEvents',1,7);">按日</div>
	                         <div id="eventSwitch7" class="inline_div week_select select" onclick="applyReportList('applyEvents',7,12);">按周</div>
	                         <div id="eventSwitch30" class="inline_div month_select select selected" onclick="applyReportList('applyEvents',30,12);">按月</div>
	                     </div>
	                 </div>
	                 
	                 <div id="monitor_events_change" class="line_echart_block">
	
	                 </div>
	             </div><div class="inline_div width_50">
	                 <div class="body_block_title">
	                     风险状况变化情况
	                 </div>
	                 <div class="calender_selector_block">
	                     <div id="riskSwitchCNT" class="inline_div calender_selector">
	                         <div id="riskSwitch1" class="inline_div day_select select" onclick="applyReportList('applyRisks',1,7);">按日</div>
	                         <div id="riskSwitch7" class="inline_div week_select select" onclick="applyReportList('applyRisks',7,12);">按周</div>
	                         <div id="riskSwitch30" class="inline_div month_select select selected" onclick="applyReportList('applyRisks',30,12);">按月</div>
	                     </div>
	                 </div>
	                 <div id="monitor_level_change"  class="line_echart_block">
	
	                 </div>
	             </div>
	         </div>
	     </div>
	 </div>
	 <!-- body_content end -->
	 
	 <div class="body_right">
	     <div class="company_show_list block_shadow">
	         <div class="company_show_title">今日加入动态监控的公司</div>
	         <div class="company_list">
	              <c:if test="${not empty todayCompanys }">
	                 <c:forEach items="${todayCompanys }" var="record">
			             <div class="company_li">
			                 <div class="company_name">
			                     <div class="inline_div pointer"></div>
			                     <div class="inline_div name">${ record.monitorCompany.companyName}</div>
			                 </div>
			                 <div class="company_time">
			                     <div class="inline_div person">
			                         <span>省份:</span>
			                         <span>${ record.monitorCompany.areaName}</span>
			                     </div>
<!-- 			                     <div class="inline_div time"> -->
<!-- 			                         <span>注册:</span> -->
<!-- 			                         <span>2016-09-01</span> -->
<!-- 			                     </div> -->
			                 </div>
			             </div>
		             </c:forEach>
	             </c:if>
	         </div>
	     </div>
	 </div>
     <div id="nav_menu" class="nav_menu dark_black">
         <div class="menu_top">
             <img src="${ctx}/images/modules/index/arr-top.png"/>
         </div>
         <div class="menu_content">
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_1.png"/>
                 </div>
                 <div class="menu_title">主页</div>
                 <div class="menu_num hidden">11</div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_2.png"/>
                 </div>
                 <div class="menu_title">工商信息</div>
                 <div class="menu_num hidden">11</div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_3.png"/>
                 </div>
                 <div class="menu_title">司法信息</div>
                 <div class="menu_num hidden">11</div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_4.png"/>
                 </div>
                 <div class="menu_title">舆情信息</div>
                 <div class="menu_num hidden">11</div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_5.png"/>
                 </div>
                 <div class="menu_title">企业族谱</div>
                 <div class="menu_num hidden"></div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src=".${ctx}/images/modules/index/menu_6.png"/>
                 </div>
                 <div class="menu_title">付款指数</div>
                 <div class="menu_num hidden">11</div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_7.png"/>
                 </div>
                 <div class="menu_title">个人信息</div>
                 <div class="menu_num hidden">11</div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_8.png"/>
                 </div>
                 <div class="menu_title">我的收藏</div>
                 <div class="menu_num hidden">11</div>
             </div>
             <div class="menu_span">
                 <div class="menu_img">
                     <img src="${ctx}/images/modules/index/menu_9.png"/>
                 </div>
                 <div class="menu_title">设置</div>
                 <div class="menu_num hidden">11</div>
             </div>
         </div>
         <div class="menu_bottom dark_black">
             <img src="${ctx}/images/modules/index/arr-down.png"/>
         </div>
     </div>
  </body>
</html>
