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
	<script type="text/javascript" src="${ctx }/js/modules/monitor/company_rename.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/company/detail/biz/biz.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/company/detail/law/law.js?${appVersion }"></script>	
	<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/history.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/company/detail/riskAnalysis/riskAnalysis.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/company/detail/news/news.js?${appVersion }"></script>

  </head>
  
  <body>
    <!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	<input id="currentDeep"  name="currentDeep" type="hidden" value="${deep}"/>
	<input id="deep"  name="deep" type="hidden" value="${deep}"/>
	<input id="selectedModuleIndex"  name="selectedModuleIndex" type="hidden" value="${selectedModuleIndex}"/>
    <div class="body_content_5">
        <div class="body_center ">

            <div class="body_block_2 block_shadow" style="padding-top:57px;">
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
                        <div class="relative_right_block">
                            <div class="inline_div btn_2_block" id="" onclick="lengjingReport();">
                                <div class="inline_div img_btn_4 export_in_img">
                                    <span  class="">导出报告</span>
                                </div>
                            </div>
                        </div>
                        <div class="monitor_company_name inline_div" id="MonitorCompanyName">
                            ${companyName}
                        </div>
                        <span class="used_name hidden" style="left:0px;top:-4px">（曾用名：安徽厦门松霖科技有限公司）</span>
                    </div>
                    <div class="company_modules_blodk">
                        <div class="company_modules">
                            <div class="modules_list">
                                <div class="modules inline_div selected">
                                  
                                    <div class="inline_div name">工商信息</div>
                                    <div class="inline_div value">
                                     ${companyNum.Shareholder_Info
									   +companyNum.KeyPerson_Info
									   +companyNum.Branches
									   +companyNum.Changed_Announcement
									   +companyNum.ent_Investor_Info
									   +companyNum.Equity_Pledge
									   +companyNum.Chattel_Mortgage
									   +companyNum.Business_Abnormal
									   +companyNum.Administrative_Penalty
									   +companyNum.LOGO
									  }
									  </div>
                                
                                </div><div class="modules inline_div">
                                    <div class="inline_div name">司法信息</div>
                                    <div class="inline_div value">
                                    ${companyNum.pjs
									   +companyNum.qt
									   +companyNum.bltin
									   +companyNum.beizhixing
									   +companyNum.shixin
									   +companyNum.kai_ting_gong_gao
									  }</div>
                              
                                </div>
                                <div class="modules inline_div">
                               		<!-- onclick="toNews('广东世荣兆业股份有限公司')" -->
                                    <div class="inline_div name"  >新闻舆情</div>
                                    <div class="inline_div value">${companyNum.norisk+companyNum.risk}</div>
                                
                                </div>
                                <div class="modules inline_div"  id="CompanyTopology">
                                    <div class="inline_div name">企业族谱</div>
                                    <div class="inline_div value hidden"></div>
                                
                                </div>
                           <!--      <div class="modules inline_div"> 
                                	// onclick="toRiskAnalysis('广东世荣兆业股份有限公司')" 
                                    <div class="inline_div name">风险分析</div>
                                    <div class="inline_div value hidden"></div>
                                </div> -->
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
                                    </div><div class="sub_modules inline_div ">
                                        股东信息&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.Shareholder_Info ne null and companyNum.Shareholder_Info !='' and companyNum.Shareholder_Info !='null'}">
						    ${companyNum.Shareholder_Info}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        主要人员&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.KeyPerson_Info ne null and companyNum.KeyPerson_Info !='' and companyNum.KeyPerson_Info !='null'}">
						    ${companyNum.KeyPerson_Info}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        分支机构&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.Branches ne null and companyNum.Branches !='' and companyNum.Branches !='null'}">
						    ${companyNum.Branches}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        变更记录&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.Changed_Announcement ne null and companyNum.Changed_Announcement !='' and companyNum.Changed_Announcement !='null'}">
						    ${companyNum.Changed_Announcement}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        对外投资&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.ent_Investor_Info ne null and companyNum.ent_Investor_Info !='' and companyNum.ent_Investor_Info !='null'}">
						    ${companyNum.ent_Investor_Info}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        股权出质&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.Equity_Pledge ne null and companyNum.Equity_Pledge !='' and companyNum.Equity_Pledge !='null'}">
						    ${companyNum.Equity_Pledge}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div">
                                        动产质押&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.Chattel_Mortgage ne null and companyNum.Chattel_Mortgage !='' and companyNum.Chattel_Mortgage !='null'}">
						    ${companyNum.Chattel_Mortgage}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        经营异常&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.Business_Abnormal ne null and companyNum.Business_Abnormal !='' and companyNum.Business_Abnormal !='null'}">
						    ${companyNum.Business_Abnormal}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        行政处罚&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.Administrative_Penalty ne null and companyNum.Administrative_Penalty !='' and companyNum.Administrative_Penalty !='null'}">
						    ${companyNum.Administrative_Penalty}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div><div class="sub_modules inline_div ">
                                        商标&nbsp;(<span id="brandNum" class="value"><c:choose>
						  <c:when test="${companyNum.LOGO ne null and companyNum.LOGO !='' and companyNum.LOGO !='null'}">
						    ${companyNum.LOGO}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div>
                                </div>
                                <div class="modules_sub_list_2 center_list sub_list hidden">
                                    <div class="sub_modules_block inline_div">
                                        <div class="sub_modules inline_div selected">
                                            裁判文书&nbsp;(<span class="value">${companyNum.pjs
									   +companyNum.qt}</span>)
                                        </div>
                                        <div class="sub_extra_modules inline_div">
                                            <div class="judicial_btn inline_div" onclick="redictLe();">
                                                <img src="${ctx }/images/modules/monitor/judicial_icon.png"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="sub_modules inline_div ">
                                        被执行人&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.beizhixing ne null and companyNum.beizhixing !='' and companyNum.beizhixing !='null'}">
						    ${companyNum.beizhixing}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div>
                                    <div class="sub_modules inline_div ">
                                        失信信息&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.shixin ne null and companyNum.shixin !='' and companyNum.shixin !='null'}">
						    ${companyNum.shixin}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div>
                                    <div class="sub_modules inline_div ">
                                        开庭公告&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.kai_ting_gong_gao ne null and companyNum.kai_ting_gong_gao !='' and companyNum.kai_ting_gong_gao !='null'}">
						    ${companyNum.kai_ting_gong_gao}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div>
                                    <div class="sub_modules inline_div ">
                                        法院公告&nbsp;(<span class="value"><c:choose>
						  <c:when test="${companyNum.bltin ne null and companyNum.bltin !='' and companyNum.bltin !='null'}">
						    ${companyNum.bltin}
						  </c:when> 
	                      <c:otherwise>0</c:otherwise></c:choose></span>
	                      )
                                    </div>
                                </div>
                                <div class="modules_sub_list_3 sub_list center_list hidden">
                                     <div class="sub_modules selected inline_div " >
                                        媒体资讯&nbsp;(<span class="value">${companyNum.norisk gt 0 ? companyNum.norisk:0}</span>)
                                    </div>
                                    <!-- onclick="toNews('广东世荣兆业股份有限公司')" -->
                                    <div class="sub_modules inline_div ">
                                        重点关注舆情&nbsp;(<span class="value">${companyNum.risk  gt 0 ? companyNum.risk:0 }</span>)
                                    </div>
                                </div>
                                <div class="modules_sub_list_4 sub_list no_sub_list center_list hidden">

                                </div>
                                <div class="modules_sub_list_5 sub_list no_sub_list center_list hidden">

                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modules_block_list">
                    	<div class="modules_block current_modules">
                    	</div>
                    	<div class="modules_block hidden">
                    	</div>
                    	<div class="modules_block hidden">
                    	</div>
                    	<div class="modules_block hidden">
                    		<div  id="TopologyBlock" style="overflow-x:hidden;width:1126px;border:1px solid #d7d7d7;">
                                <div id="searchCompName">${companyName}</div>
                                <!-- 载入动画 -->
                                <div id="loader">
                                    <div class="spinner">
                                        <div class="rect1"></div>
                                        <div class="rect2"></div>
                                        <div class="rect3"></div>
                                        <div class="rect4"></div>
                                        <div class="rect5"></div>
                                    </div>
                                    <div class="loadDesc">
                                        数据正在载入，请稍候...
                                    </div>
                                </div>

                                <!-- 投资关系模态框 -->
                                <Modalinvest ></Modalinvest>
                                <!-- 舆情分析模态框-->
                                <Modalnews :company-news-list='companyNewsList' :news-detail='newsDetail'></Modalnews>
                                <!-- 新闻详情模态框 -->
                                <!-- <Modalnewsdetail :news-detail='newsDetail'></Modalnewsdetail> -->
                                <!-- 公司股东详细列表模态框 -->
                                <Modalshareholder :node-Info='nodeInfo'></Modalshareholder>
                                <!-- 公司变更信息详细列表模态框 -->
                                <Modalinfochange :node-Info='nodeInfo'></Modalinfochange>
                                <!-- 法院公告详细列表模态框 -->
                                <Modalcourtanno :node-Info='nodeInfo'></Modalcourtanno>
                                <!-- 被执行详细列表模态框 -->
                                <Modalexecuted :node-Info='nodeInfo'></Modalexecuted>
                                <!-- 失信被执行详细列表模态框 -->
                                <Modaldishonest :node-Info='nodeInfo'></Modaldishonest>

                                <!-- 自定义拓扑图节点与连线连线 -->
                                <Setcolor :color-Config='colorConfig'></Setcolor>

                                <!-- 模态框 -->
                                <Modalinfo :node-Info='nodeInfo'></Modalinfo>
                                <!-- 导出图片模态框 -->
                                <Modalsvg :disable-Export-Svg='disableExportSvg'></Modalsvg>
                                <!-- 导航栏 -->
                                <Navbtp :search-Form='searchForm' :is-Single-Search-Mode='isSingleSearchMode' :is-Multi-Search-Mode='isMultiSearchMode' :search-Box-Placeholder='searchBoxPlaceholder' :options-Config='optionsConfig' :selected-Status='selectedStatus' :check-Keys='getLinksTypeCheckboxesKeys' :menu-Key-Name-List='menuKeyNameList' :smart-Analyse-High-Light='smartAnalyseHighLight' :all-Btn-Disable='allBtnDisable' :is-Batch-Search-Mode='isBatchSearchMode'></Navbtp>
                                <div class="">
                                    <div class="row">
                                        <div class="col-xs-12 col-sm-4 col-md-3 col-lg-3 sidebar" style="display:none;" id="leftSideBar">
                                            <div id="sidepanel">
                                                <!-- 功能提示栏 -->
                                                <!-- <Hints></Hints> -->
                                                <!-- 数据筛选 -->
                                                <!-- <div id="filterBoard">
                                                    <Datafilter :selected-Status='selectedStatus' :check-Keys='getLinksTypeCheckboxesKeys' :menu-Key-Name-List='menuKeyNameList'></Datafilter>
                                                </div> -->
                                                <!--风险提示 -->
                                                <!-- <Risktable :risk-Info='riskInfo'></Risktable> -->
                                                <Nodeinfo :node-Info='nodeInfo' :is-Show-Content='isShowContent'></Nodeinfo>
                                                <!--风险分析 -->
                                                <Riskanalyse :node-Info='nodeInfo' :risk-Info='riskInfo'></Riskanalyse>
                                            </div>
                                        </div>
                                        <!--<div class="col-xs-12 col-sm-8 col-sm-offset-4 col-md-9 col-md-offset-3 col-lg-9 col-lg-offset-3 main">-->
                                        <div class="col-md-12 main">
                                            <div id="vizContainer"></div>
                                            <button id='showTreeContainer' type='button'> <span class="glyphicon glyphicon-random" aria-hidden="true"></span>切换树状图</button>
                                            <div id="bufferLayer">
                                                <div class="spinner">
                                                <div class="cube1 cubeC"></div>
                                                <div class="cube2 cubeC"></div>
                                                </div>
                                            </div>
                                            <div id="treeContainer">
                                                <div id="subTreeContainer">
                                                    <div id="clusterContainer2"></div>
                                                    <div class="treeContainerConfig">
                                                        <div class="btn-group btn-group-sm">
                                                            <button type="button" class="btn btn-default rotateLeftBtn"><i class="fa fa-undo fa-lg" aria-hidden="true"></i></button>
                                                            <button type="button" class="btn btn-default rotateRightBtn"><i class="fa fa-repeat fa-lg" aria-hidden="true"></i></button>
                                                            <button type="button" class="btn btn-default zoomInBtn"><i class="fa fa-search-plus fa-lg" aria-hidden="true"></i></button>
                                                            <button type="button" class="btn btn-default zoomOutBtn"><i class="fa fa-search-minus fa-lg" aria-hidden="true"></i></button>
                                                            <button type="button" class="btn btn-default fullScreenTreeContainer"><span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span></button>
                                                            <button type="button" class="btn btn-default closeTreeContainer"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                                        </div>
                                                    </div>
                                                </div>
                                                <!-- <span class="closeTreeContainer">x</span>      -->
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                    	</div>
<!--                     	<div class="modules_block hidden"> -->
<!--                     	</div> -->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="detail_block_4 detail_block hidden shixin">
     	<div class="table_value" style="height:520px;overflow:auto;">
     		<table class="">
             <tr class="">
                 <td>被执行人姓名/名称：</td>
                 <td id="mc4Shixin"></td>
             </tr>
             <tr class="">
                 <td>身份证号码/组织机构代码：</td>
                 <td id="dm4Shixin"></td>
             </tr>
             <tr class="">
                 <td>法定代表人或负责人姓名：</td>
                 <td id="fddbr4Shixin"></td>
             </tr>
             <tr class="">
                 <td>执行法院：</td>
                 <td id="zxfy4Shixin"></td>
             </tr>
             <tr class="">
                 <td>省份：</td>
                 <td id="sf4Shixin"></td>
             </tr>
             <tr class="">
                 <td>执行依据文号：</td>
                 <td id="zxyjwh4Shixin"></td>
             </tr>
             <tr class="">
                 <td>立案时间：</td>
                 <td id="sj4Shixin"></td>
             </tr>
             <tr class="">
                 <td>案号：</td>
                 <td id="ah4Shixin"></td>
             </tr>
             <tr class="">
                 <td>做出执行依据单位：</td>
                 <td id="zxyjdw4Shixin"></td>
             </tr>
             <tr class="">
                 <td>生效法律文书确定的义务：</td>
                 <td id=""><div id="wsqdyw4Shixin" style=""></div></td>
             </tr>
             <tr class="">
                 <td>被执行人的履行情况：</td>
                 <td id="lxqk4Shixin"></td>
             </tr>
             <tr class="">
                 <td>失信被执行人行为具体情形：</td>
                 <td id="sxjtqk4Shixin"></td>
             </tr>
             <tr class="">
                 <td>发布时间：</td>
                 <td id="fbsj4Shixin"></td>
             </tr>
         </table>
     </div>
 </div>
	
	<div class="detail_block_1 detail_block hidden fygg">
		<div class="border_block">
			<div class="border_line"></div>
			<div class="left_hide inline_div"></div>
			<div class="border_img inline_div">
				<img src="${ctx }/images/modules/monitor/more_detail_1.png" />
			</div>
			<div class="right_hide inline_div"></div>
		</div>
		  <div class="detail_content">
                    <div class="detail_title_block">
                        <div class="detail_title">
                            <span class="title">法院公告详情</span>
                            <span class="courtTime">发布日期:<span id="courtTime"></span></span>
                        </div>
                        <div class="courtTitle" id="courtTitle" style="font-size: 18px;color: #333333;text-align: center;margin-bottom: 10px;padding: 0px 10px;min-height: 50px;">
                        </div>
                       <div class="content_from">
                            <div class="courtName"></div>
                            <div class="court_type"></div>
                            <!-- <div class="content_case"></div> -->
                        </div> 
                    </div>
                    <div class="detail_content_block">
                   	<!-- 	<div class="content_court" style="text-align:center;font-size:14px;color:#333333;">
                        </div>
                        <div class="content_type" style="text-align:center;font-size:14px;color:#333333;">
                       
                        </div>
                        <div class="content_case"  style="text-align:right;font-size:14px;color:#999999;">
                          
                        </div>
                        <div class="content" id="details4Sx" style="overflow: auto;" onclick="return false">
                        </div> -->
                    <!-- 
                    
                   		<div class="content_party"  style="text-align:right;font-size:14px;color:#999999;">
                        </div>
                      
                        <div class="courtName" style="text-align:center;font-size:14px;color:#333333;">
                        </div> -->
                        <div class="courtContent" id="courtContent" style="overflow: auto;color: #333333;" onclick="return false">
                        </div>
                    </div>
         </div>
	</div>
	
	
	<div class="detail_block_1 detail_block hidden sfws">
		<div class="border_block">
			<div class="border_line"></div>
			<div class="left_hide inline_div"></div>
			<div class="border_img inline_div">
				<img src="${ctx }/images/modules/monitor/more_detail_1.png" />
			</div>
			<div class="right_hide inline_div"></div>
		</div>
		  <div class="detail_content">
                    <div class="detail_title_block">
                        <div class="detail_title">
                            <span class="title">裁判文书详情</span>
                            <span class="time">发布日期:<span id="judgmentTime4Sx"></span></span>
                        </div>
                        <div class="content_title" id="title4Sx">
                        </div>
                        <div class="content_from">
                            <div class="content_court"></div>
                            <div class="content_type"></div>
                            <div class="content_case"></div>
                        </div>
                    </div>
                    <div class="detail_content_block">
                        <div class="content_court" style="text-align:center;font-size:14px;color:#333333;">
                        </div>
                        <div class="content_type" style="text-align:center;font-size:14px;color:#333333;">
                       
                        </div>
                        <div class="content_case"  style="text-align:right;font-size:14px;color:#999999;">
                          
                        </div>
                        <div class="content" id="details4Sx" style="overflow: auto;" onclick="return false">
                        </div>
                    </div>
         </div>
	</div>
    <link type="text/css" rel="stylesheet" href="${ctx }/css/common/layout.css">
   	
    <link type="text/css" rel="stylesheet" href="${ctx }/css/fonts/font-awesome.min.css">
    <script type="text/javascript" src="${ctx }/js/modules/company/detail/relationMap/build.js"></script>
  </body>
    
</html>
