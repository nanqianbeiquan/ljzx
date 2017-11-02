<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	
	<script type="text/javascript" src="${ctx }/js/utils/areaUtils.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/jquery/jquery.city.select.min.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/echarts/echarts.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/echarts/echartController.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/blacklist/blacklist.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/company_analysis.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/company_customize.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/company_rename.js?${appVersion }"></script>

	<script type="text/javascript" src="${ctx }/js/modules/monitor/company.js?${appVersion }"></script>
	
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/filter.css?${appVersion }">
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/company.css?${appVersion }">
</head>
<body class="ljzx_page back_gray">
	<input type="hidden" id="monitorId" value="${monitorCompany.monitorID}" />
	<input type="hidden" id="companyName" value="${monitorCompany.companyName}" />
	<input type="hidden" id="riskResult" value="${monitorCompany.riskResult}" />
	<input type="hidden" id="infoShowDate" value="${monitorCompany.infoShowDate }"/>
	<input type="hidden" id="deep" value="${deep}" />
	<input type="hidden" id="showActionBtnFlag" value="${showActionBtnFlag}" />
	
	<jsp:include page="/page/modules/index/banner.jsp" />
	
	<div class="body_content_5">
		<div class="body_center ">
			<div class="body_block_2 block_shadow" style="min-height:auto;padding-top:127px;">
				
					<div class="nav_bar" style="">
						<div class="back_block">
							<div class="inline_div">
								<div class="for_back_btn" onclick="window.history.back()"></div>
							</div>
						</div>
						<span><a href="${ctx }/index">首页</a></span> <span>></span> <span>监控企业列表</span>
						<span>></span> <span class="current_nav">监控企业详情</span>
					</div>
					
					<div class="monitor_company_title_content" style="z-index:700;position:absolute;top:137px;width:1188px;">
					<div class="monitor_company_title" style="background:#2ea7e0;">
						<div class="">
							<div class="company_option inline_div_block ">
							    <c:if test="${showActionBtnFlag eq true}">
								    <div class="inline_div img_btn hidden " id="BlacklistPushBtn">
										<img class="inline_div "
											src="${ctx }/images/modules/monitor/blacklist_icon.png" /> <span
											class="inline_div">加入失信</span>
									</div>
									<div class="inline_div img_btn" id="cancelMonitor">
										<img class="inline_div"
											src="${ctx }/images/common/btn_wuxing.png" /> <span
											class="inline_div ">取消监控</span>
									</div>
							    </c:if>
								<div class="inline_div img_btn">
									<img class="inline_div"
										src="${ctx }/images/common/btn_download.png" /> <span
										class="inline_div" onClick="downloadBtnClicked()">下载</span>
								</div>
							</div>
							<div class="company_name" style="height:47px;padding:1px 0px 0px 0px;">
								<div class="monitor_company_name">${monitorCompany.companyName}
								   <c:if test="${not empty entStatus }">
								      <img alt="" src="${ctx }/images/common/warning.png">
								      <span>（${entStatus}）</span>
								   </c:if>
								</div>
								<div class="monitor_company_time">加入监控时间 : <fmt:formatDate value="${monitorCompany.monitorDate }" type="date"/></div>
							</div>
						</div>
					</div>
				</div>
				<div class="company_span">
					<div class="span_title inline_div">公司标签：</div>
					<div class="span_list inline_div inline_div_block" id="companyTagList"></div>
				</div>
				<div class="company_risk">
					<div class="monitor_event_pic_block">
						<div class="inline_div monitor_event_pic">
							<div id="monitor_company_event" class="line_echart_block"></div>
						</div>
					</div>
					<div class="inline_div risk_content">
						<div id="CompanyRiskAnalysis" class="label_block">
							<div class="relative_left_block">
								<div class="label_title inline_div">
									<div class="value inline_div">风险分析</div>
								</div>
							</div>
							<div class="relative_right_block">
								<div class="label_more inline_div inline_div_block">
									<div class="inline_div option_value">详情</div>
									<div class="inline_div">
										<img class="option_img up_img hidden"
											src="${ctx }/images/modules/user/up.png">
										<img class="option_img down_img "
											src="${ctx }/images/modules/user/down.png">
									</div>
								</div>
							</div>
							<div class="label_content_block">
								<div class="analysis_list" style="height:100px;">
								</div>
							</div>
						</div>
						<div class="c_content">
							<div class="monitor_level_block">
								<div class="monitor_level" id="riskLevelImg"></div>
							</div>
							<div class="content_title">
								<div class="inline_div title">
									<div class="title_value">风险建议</div>
								</div>
							</div>
							<div class="inline_div_block system_suggest" id="RiskLabelBlock">
								<div id="risk_suggest" class="content_block inline_div" >
									<div class="r_content">
										<div class="content" id="suggestion"></div>
									</div>
								</div>
								<div class="inline_div company_risk_block "  id="customize_risk">
									<div class="relative_right_block right_triangle_block">
										<div class="triangle"></div>
										<div class="risk_title">自定义</div>
									</div>
									<div class="relative_left_block left_round_blank">
										<div class="round"></div>
									</div>
									<div class="risk_content_block ">
										<div class="risk_level">
											一般预警
										</div>
										<div class="inline_div_block risk_click_btn_block">
											<div class="inline_div risk_click_btn ">
												<div class="inline_div value">风险自定义</div>
											</div>
										</div>
									</div>
								</div>
								<div class="inline_div company_risk_block " id="system_risk">
									<div class="relative_right_block right_triangle_block">
										<div class="triangle"></div>
										<div class="risk_title">系统</div>
									</div>
									<div class="relative_left_block left_round_blank">
										<div class="round"></div>
									</div>
									<div class="risk_content_block ">
										<div class="risk_level">
											<c:choose>
											  <c:when test="${monitorCompany.riskSituation == '0'}">正常</c:when>
											  <c:when test="${monitorCompany.riskSituation == '1'}">关注</c:when>
											  <c:when test="${monitorCompany.riskSituation == '2'}">一般预警</c:when>
											  <c:when test="${monitorCompany.riskSituation == '3'}">特别预警</c:when>
											</c:choose>
										</div>
										<div class="inline_div_block risk_click_btn_block ">
											<div class="inline_div risk_click_btn " >
												<div class="inline_div value">系统默认</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="company_monitor">
					<div class="inline_div monitor_event_block">
						<div class="r_block">
							<div id="show_more_table" class="inline_div show_more">
								<div class="inline_div option_value">展开</div>
								<div class="inline_div">
									<img class="option_img up_img hidden"
										src="${ctx }/images/modules/user/up.png"> <img
										class="option_img down_img "
										src="${ctx }/images/modules/user/down.png">
								</div>
							</div>
						</div>
						<div class="body_block_title">
							监控事件
							<div class="inline_div title_btn 
								style="z-index:300;position: relative;">
								<div class="inline_div img_btn_2"
									style="margin-left:4px;width:80px;" id="finance_data_btn">
									<img class="img"
										src="${ctx }/images/modules/monitor/finance_data.png" /> <img
										class="hidden img_hover"
										src="${ctx }/images/modules/monitor/finance_data_hover.png" /> 财务分析
								</div>
								<div class="inline_div img_btn_2" style="width:92px;" onclick="toSiFaQuanJingtu('${monitorCompany.companyName}')">
									<img class="img" src="${ctx }/images/modules/monitor/img.png" />
									<img class="hidden img_hover" src="${ctx }/images/modules/monitor/img_hover.png" /> 司法全景图
								</div>
								<div class="inline_div img_btn_2"
									style="margin-left:4px;width:80px;" id="history_btn">
									<img class="img"
										src="${ctx }/images/modules/monitor/history.png" /> <img
										class="hidden img_hover"
										src="${ctx }/images/modules/monitor/history_hover.png" /> 企业详情
								</div>
								<div class="inline_div img_btn_2"
									style="margin-left:4px;width:105px;" id="legal_person_btn">
									<img class="img"
										src="${ctx }/images/modules/monitor/legal_person.png" /> <img
										class="hidden img_hover"
										src="${ctx }/images/modules/monitor/legal_person_hover.png" /> 法人代表信息
								</div>
							</div>
						</div>
						<div class="monitor_event_filter hidden">
							<div class="filter_block">
								<div class="filter_data">
									<div class="filter_name inline_div option_text">事件性质</div>
									<div class="filter_options inline_div">
										<div class="option_block inline_div">
											<div class="option_text option_name selected inline_div">
												<span class="option">全部</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">风险</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">严重</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">异常</span>
											</div>
										</div>
									</div>
								</div>
								<div class="filter_data">
									<div class="filter_name inline_div option_text">事件类型</div>
									<div class="filter_options inline_div">
										<div class="option_block inline_div">
											<div class="option_text option_name selected inline_div">
												<span class="option">全部</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">工商</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">行政处罚</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">税务</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">司法</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">舆情</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">其他</span>
											</div>
										</div>
									</div>
								</div>
								<div class="filter_data">
									<div class="filter_name inline_div option_text">时间筛选</div>
									<div class="filter_options inline_div">
										<div class="option_block inline_div">
											<div class="option_text option_name selected inline_div">
												<span class="option">全部</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">最近7天</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span class="option">最近15天</span>
											</div>
										</div>
										<div class="option_block inline_div">
											<div class="option_text option_name inline_div">
												<span>自选时间</span>
											</div>
											<div class="option_time option_time inline_div">
												<div class="time_png inline_div">
													<img class="inline_div"
														src="${ctx }/images/common/time_png.png" />
												
												</div><input type="text" readonly="readonly" placeholder="开始时间" />
											</div>
											－
											<div class="option_time option_time inline_div">
												<div class="time_png inline_div">
												</div><input type="text" readonly="readonly" placeholder="结束时间" />
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>

						<div class="monitor_event_table_block">
							<div class="monitor_events" style="position: relative;">
								<div class="event_value inline_div selected" index="01">
									法人代表变更
								</div>
								<div class="event_value inline_div" index="02">
									股东变更
								</div>
								<div class="event_value inline_div" index="03">
									高管变更
								</div>
								<div class="event_value inline_div" index="04">
									经营异常
								</div>
								<div class="event_value inline_div" index="05">
									企业更名
								</div>
								<div class="event_value inline_div" index="06">
									经营状态
								</div>
								<div class="event_value inline_div" index="07">
									对外投资
								</div>
								<div class="event_value inline_div" index="08">
									动产抵押
								</div>
								<div class="event_value inline_div" index="09">
									股权出质
								</div>
								<div class="event_value inline_div" index="10">
									股权冻结
								</div>
								<div class="event_value inline_div" index="11">
									工商行政处罚
								</div>
								<div class="event_value inline_div" index="12">
									食药监监察
								</div>
								<div class="event_value inline_div" index="13">
									质量监督
								</div>
								<div class="event_value inline_div" index="14">
									社保缴纳
								</div>
								<div class="event_value inline_div" index="15">
									环境核查
								</div>
								<div class="event_value inline_div" index="16">
									欠税信息
								</div>
								<div class="event_value inline_div" index="17">
									税务非正常户
								</div>
								<div class="event_value inline_div" index="23">
									开庭公告
								</div>
								<div class="event_value inline_div" index="18">
									裁判文书
								</div>
								<div class="event_value inline_div" index="19">
									裁判文书（金融合同）
								</div>
								<div class="event_value inline_div" index="20">
									裁判文书（劳务纠纷）
								</div>
								<div class="event_value inline_div" index="21">
									被执行人
								</div>
								<div class="event_value inline_div" index="22">
									失信被执行人
								</div>
								<div class="event_value inline_div" index="24">
									媒体资讯
								</div>
								<div class="event_value inline_div" index="25">
									重点关注舆情
								</div>
								<div class="event_value inline_div" index="27">
									专利
								</div>
								<div class="event_value inline_div" index="28">
									投招标
								</div>
								<div class="event_value inline_div" index="29">
									商标
								</div>
							</div>
							<div class="event_table_block">
								<div class="event_table ">
									<div class="time_range">
										<div class="relative_right_block inline_div_block" id="time_range_right">
											<div class="inline_div checkbox_selector">
                                               <input type="checkbox" name="newEve" onclick="readStatusDeal()"/>未读事件
                                            </div>
                                            <div class="inline_div button_selector">
                                               <div class="small_btn_2 " id="ignoreAll" onclick="ignoreAll()">全部已读</div>
                                          </div>
                                          <div class="inline_div checkbox_selector">
                                          		<div class="event_selector_serious_btn"   level="3" onclick="eventLevelDeal(this)">严重</div>
                                                 <!-- <input type="checkbox" name="typeList" value="严重" onclick="eventLevelDeal()"/> -->
                                             </div>
											<div class="inline_div checkbox_selector">
												<div class="event_selector_abnormal_btn"   level="2" onclick="eventLevelDeal(this)">异常</div>
                                                  <!-- <input type="checkbox" name="typeList" value="异常" onclick="eventLevelDeal()"/> -->
                                              </div>
                                               <div class="inline_div checkbox_selector">
                                               	<div class="event_selector_general_btn"  level="1" onclick="eventLevelDeal(this)">一般</div>
                                                   <!-- <input type="checkbox" name="typeList" value="一般" onclick="eventLevelDeal()"/> -->
                                               </div>
										</div>
										<div class="inline_div_block"  style="height:28px;">
										  <div id="filterTimeDiv">
											<div class="inline_div time_rang_title">时间筛选</div>
											<div class="option_time inline_div">
												<div class="time_png inline_div">
													<img class="inline_div"
														src="${ctx }/images/common/time_png.png" />
												
												</div><input class="Wdate" id="beginDate" placeholder="开始时间"
													type="text" style="width:90px;background:#ffffff;"
													onfocus="WdatePicker({onpicking:	function(dp){beginDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){beginDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true})" />
											</div>
											<span class="time_span inline_div"></span>
	
											<div class="option_time  inline_div">
												<div class="time_png inline_div">
													<img class="inline_div"
														src="${ctx }/images/common/time_png.png" />
												
												</div><input class="Wdate" id="dueDate" placeholder="结束时间"
													type="text" style="width:90px;background:#ffffff;"
													onfocus="WdatePicker({onpicking:	function(dp){dueDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){dueDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true})" />
											</div>
										  </div>
										</div>
										
										
									</div>
									<div id="detailsTable"></div>
								</div>
								<div class="page_block">
									<div class="page_plug">
										<div id="Pagination4Details"></div>
									</div>
								</div>
							</div>
						</div>
						<div class="monitor_no_event hidden">暂无事件</div>
					</div>
				</div>
			</div>
			<div class="body_block block_shadow">
				<div class="body_block_title">关系企业（自然人）情况</div>
				<div class="group_detail">
					<div class="group_info">
						<div class="inline_div group_table_block">
							<div class="table_operate">
								<div class="right_block" onclick="relateBlockShowInDetail()">
									<div id="add_group_company" class="img_btn_2 inline_div ${showActionBtnFlag eq false ? 'hidden':'' }" style="width:50px;line-height:28px;">
										<img src="${ctx }/images/modules/monitor/group_edit.png" /><span>新增</span>
									</div>
								</div>
								<div class="tab_block">
									<div class="inline_div tab selected" id="company">企业
									</div><div class="inline_div tab ">自然人</div>
								</div>
							</div>
							<div class="group_table">
								<div class="company_table">
									<div id="Searchresult4Company"></div>
								</div>
								<div class="person_table">
									<div id="Searchresult4Person"></div>
								</div>
								<div class="delete_block ${showActionBtnFlag eq false ? 'hidden':'' }">
									<div class="inline_div img_btn_2 delete" style="width:60px;">删除</div>
								</div>
							</div>
						</div>
						<div class="page_block">
							<div class="page_plug">
								<div id="Pagination4Company"></div>
								<div id="Pagination4Person"></div>
							</div>
						</div>
					</div>
						<div class="inline_div group_selected hidden">
							<div class="select_table">
								<div class="table_title">
									<div class="right_block">
										<div class="img_btn_2 inline_div">
											<img src="${ctx }/images/modules/monitor/group_edit.png" /><span>清空</span>
										</div>
									</div>
									已选公司名
								</div>
								<div class="table_content"></div>
							</div>
							<div class="btn_block">
								<div class="inline_div btn">
									<span>查关联</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			
		<div class="body_block block_shadow " style="display:none;">
			<div class="inline_div width_50">
				<div class="body_block_title">风险事件</div>
				<div class="calender_selector_block">
					<div class="inline_div calender_selector ">
						<div class="inline_div day_select select selected">按日
						</div><div class="inline_div week_select select">按周
						</div><div class="inline_div month_select select">按月</div>
					</div>
				</div>

				<div id="monitor_events_change" class="line_echart_block"></div>
			
			</div><div class="inline_div width_50">
				<div class="body_block_title">风险状况</div>
				<div class="calender_selector_block">
					<div class="inline_div calender_selector">
						<div class="inline_div day_select select selected">按日
						</div><div class="inline_div week_select select">按周
						</div><div class="inline_div month_select select">按月</div>
					</div>
				</div>
				<div id="monitor_level_change" class="line_echart_block"></div>
			</div>
		</div>
	</div>
	</div>
	
	<!--  新版新增关联企业，自然人  relate_add_block_in_detail   -->
	<div class="hidden group_edit_block" id="relate_add_block_in_detail">
				<input type="hidden" id="rcompanyName"/>
					<div class="mode_selector">
						<div id="company_mode" class="selected inline_div add_mode">新增关系企业 
						</div><div id="person_mode" class=" inline_div add_mode">新增关系自然人</div>
						<div class="relative_left_block">
						<div class="mode_arr triangle-down"></div>
						</div>
					</div>
				<div id="company_mode_div"></div>
				<div id="person_mode_div"></div>
	</div>
            <!-- aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa -->
    <!-- 添加关联公司提示 -->
	<div class="deltips_overlap message_overlap hidden" id="addRelationCompanyTipsWindow">
        <div class="message_title_block">
            <div class="title">
                提示
            </div>
        </div>
        <div class="message_content_block">
            <div class="content">
                <div class="inline_div content_img tips_img"></div>
                <div class="inline_div">是否确认加入关系企业？</div>
            </div>
        </div>
        <div class="message_btn_block">
            <div id="cancel_message_company" class="inline_div small_overlap_btn_1">
                <div>取消</div>
            
            </div>
            <div id="commit_cancel_company" class="inline_div small_overlap_btn">
                确定
            </div>
        </div>
    </div>
    <!--添加关联公司提示   -->
    
    <!-- 添加关联自然人提示 -->
	<div class="deltips_overlap message_overlap hidden" id="addRelationPersonTipsWindow">
        <div class="message_title_block">
            <div class="title">
                提示
            </div>
        </div>
        <div class="message_content_block">
            <div class="content">
                <div class="inline_div content_img tips_img"></div>
                <div class="inline_div">是否确认加入关系人？</div>
            </div>
        </div>
        <div class="message_btn_block">
            <div id="cancel_message_person" class="inline_div small_overlap_btn_1">
                <div>取消</div>
            
            </div>
            <div id="commit_cancel_person" class="inline_div small_overlap_btn">
                确定
            </div>
        </div>
    </div>
    <!--添加关联自然人提示   -->
    
	<div class="new_group_edit hidden">
		<div class="edit_title">分组名称</div>
		<div class="edit_input">
			<input type="text" id="newGroupName" />
		</div>
		<div class="edit_btn">
			<div class="btn inline_div">确定</div>
		</div>
	</div>

	<div class="detail_block_2 detail_block hidden news">
		<div class="border_block">
			<div class="border_line"></div>
			<div class="left_hide inline_div"></div>
			<div class="border_img inline_div">
				<img src="${ctx }/images/modules/monitor/more_detail_2.png" />
			</div>

			<div class="right_hide inline_div"></div>
		</div>
		<div class="detail_content">
			<div class="detail_title_block">
				<div class="detail_title">
					<span class="title">新闻舆情详情</span> <span class="time">发布日期:<span
						id="publishDate4News"></span></span>
				</div>
				<div class="content_title" id="title4News"></div>
			</div>
			<div class="detail_content_block">
				<div class="content" id="details4News" onclick="return false">
				</div>
			</div>
		</div>
	</div>


	<div class="deltips_overlap message_overlap hidden"
		id="cancelMonitorWindow">
		<div class="message_title_block">
			<div class="title">提示</div>
		</div>
		<div class="message_content_block">
			<div class="content">
				<div class="inline_div content_img tips_img"></div>
				<div class="inline_div">该企业已确认加入监控，如取消监控，将占用当月监控企业数量额度。</div>
			</div>
		</div>
		<div class="message_btn_block">
			<div id="cancel_message" class="inline_div small_overlap_btn_1">
				<div>取消</div>

			</div>
			<div id="submit_message" class="inline_div small_overlap_btn">
				确定</div>
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
	<div class="deltips_overlap message_overlap hidden" id="deleteGroup">
		<div class="message_title_block">
			<div class="title">提示</div>
		</div>
		<div class="message_content_block">
			<div class="content">
				<div class="inline_div content_img tips_img"></div>
				<div class="inline_div">是否确定删除分组？</div>
			</div>
		</div>
		<div class="message_btn_block">
			<div id="cancel_message2" class="inline_div small_overlap_btn_1">
				<div>取消</div>

			</div>
			<div id="submit_del_group" class="inline_div small_overlap_btn">
				确定</div>
		</div>
	</div>
	<div class="deltips_overlap message_overlap hidden" id="noLegalPersonTip">
		<div class="message_title_block">
			<div class="title">提示</div>
		</div>
		<div class="message_content_block">
			<div class="content">
				<div class="inline_div content_img tips_img"></div>
				<div class="inline_div">当前无对外任职及投资信息。</div>
			</div>
		</div>
		<div class="message_btn_block">
			<div id="submitNoLegalPersonTip" class="inline_div small_overlap_btn">
				确定</div>
		</div>
	</div>
	<div class="deltips_overlap message_overlap hidden" id="deleteReal">
		<div class="message_title_block">
			<div class="title">提示</div>
		</div>
		<div class="message_content_block">
			<div class="content">
				<div class="inline_div content_img tips_img"></div>
				<div class="inline_div delete">是否确定删除？</div>
			</div>
		</div>
		<div class="message_btn_block">
			<div id="cancel_message3" class="inline_div small_overlap_btn_1">
				<div>取消</div>

			</div>
			<div id="submit_delReal" class="inline_div small_overlap_btn">
				确定</div>
		</div>
	</div>
	<div class="detail_block_5  detail_block hidden" id="shiyaojian">
		<div class="border_block">
			<div class="border_line border_line_2"></div>
			<div class="left_hide inline_div"></div>
			<div class="border_img border_img_2 inline_div">
				<img src="${ctx }/images/modules/monitor/more_detail_4.png" />
			</div>
			<div class="right_hide inline_div"></div>
		</div>

		<div class="detail_content">
			<div class="detail_title_block">
				<div class="detail_title">
					<span class="title">&nbsp;</span> <span class="time">&nbsp;</span>
				</div>
				<div class="content_title">食药监详情</div>
			</div>
			<div class="shiyaojian"></div>
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
					<span class="title">法院判决详情</span> <span class="time">发布日期:<span
						id="judgmentTime4Sx"></span></span>
				</div>
				<div class="content_title" id="title4Sx"></div>
				<div class="content_from">
					<div class="content_court"></div>
					<div class="content_type"></div>
					<div class="content_case"></div>
				</div>
			</div>
			<div class="detail_content_block" style="">
				<div class="content_court"
					style="text-align:center;font-size:14px;color:#333333;"></div>
				<div class="content_type"
					style="text-align:center;font-size:14px;color:#333333;"></div>
				<div class="content_case"
					style="text-align:right;font-size:14px;color:#999999;"></div>
				<div class="content" id="details4Sx" style="overflow: auto;"
					onclick="return false"></div>
			</div>
		</div>
	</div>
	<div class="select_group_overlap edit_block hidden">
		<div class="edit_title">选择分组</div>
		<div class="option_list">
			<div class="inline_div option_title">分组:</div>
			<div class="inline_div option_value option_check_value"></div>
		</div>
		<div class="btn_block">
			<div class="inline_div small_btn_2 new_group">
				<div>新建分组</div>
			</div>
			<div class="inline_div small_btn" id="addBtn">添加</div>
		</div>
	</div>
	<div class="new_group_overlap edit_block hidden">
		<div class="edit_title">新建分组</div>
		<div class="option_list">
			<div class="inline_div option_title">组名:</div>
			<div class="inline_div option_value">
				<div class="option_text">
					<input type="text" placeholder="输入组名" id="name" />
				</div>
			</div>
		</div>
		<div class="option_list">
			<div class="inline_div option_title">描述:</div>
			<div class="inline_div option_value">
				<div class="option_area">
					<textarea id="description"></textarea>
				</div>
			</div>
		</div>
		<div class="btn_block">
			<div class="inline_div small_btn save_new_group">保存</div>
		</div>
	</div>
	<%--    <div class="remind_overlap" id="remind_overlap">
                <div class="remind_block">
                	<div class="remind_close_block"></div>
                	<div class="remind_send_block">
                		<img class="no_hover_img" src="${ctx }/images/modules/monitor/remind_btn.png"/>
                		<img class="hidden hover_img" src="${ctx }/images/modules/monitor/remind_btn_hover.png"/>
                	</div>
                </div>
            </div> --%>
	<div class="hidden" id="CustomizeOverlap">
		<div class="customize_page">
			<div class="customize_head customize_title">
				<div>风险自定义<span class="sub_title">（以下为选填项，支持多选）</span></div>
			</div>
			<div class="customize_body customize_risk">
				<div class="relative_right_block customize_risk_history_block">
					<div class="inline_div" id="CustomizeRiskHistoryBtn">历史记录</div>
				</div>
				<div class="inline_div_block risk_level_list">
					<div class="inline_div risk_level">特别预警</div>
					<div class="inline_div risk_level">一般预警</div>
					<div class="inline_div risk_level">关注</div>
					<div class="inline_div risk_level">正常</div>
				</div>
				<div class="risk_staff">
					<img src="${ctx }/images/modules/monitor/risk_staff.png"/>
				</div>
				<div class="inline_div_block risk_item_list">
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_4.png"/>
							</div>
						</div>
						<div class="item_name" code="11">
							经营预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="51">水电费骤降或欠缴</div>
							<div class="inline_div sub_item" code="52">主营业务变动</div>
							<div class="inline_div sub_item" code="53">劳资争议</div>
							<div class="inline_div sub_item" code="54">对外投资失败</div>
							<div class="inline_div sub_item" code="55">上下游变动</div>
							<div class="inline_div sub_item" code="56">变卖主要资产</div>
							<div class="inline_div sub_item" code="57">销售额骤降</div>
							<div class="inline_div sub_item" code="58">关联交易过多</div>
						</div>
					</div>
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_1.png"/>
							</div>
						</div>
						<div class="item_name" code="12">
							信用预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="59">拖欠货款</div>
							<div class="inline_div sub_item" code="60">交付违约</div>
							<div class="inline_div sub_item" code="61">行业口碑差</div>
							<div class="inline_div sub_item" code="62">恶意欺诈</div>
						</div>
					</div>
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_2.png"/>
							</div>
						</div>
						<div class="item_name" code="13">
							财务预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="64">负债上升过快</div>
							<div class="inline_div sub_item" code="65">应收账款过多</div>
							<div class="inline_div sub_item" code="66">盈利能力下降</div>
							<div class="inline_div sub_item" code="67">现金流紧张</div>
						</div>
					</div>
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_3.png"/>
							</div>
						</div>
						<div class="item_name" code="14">
							高管预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="68">涉案</div>
							<div class="inline_div sub_item" code="69">失联</div>
							<div class="inline_div sub_item" code="70">意外事故或健康恶化</div>
							<div class="inline_div sub_item" code="71">频繁变更</div>
						</div>
					</div>
				</div>
				<div>
					<textarea class="customize_reason" code="72" id="" placeholder="请在此输入您的补充说明"></textarea>
				</div>
				<div class="customize_edit_btn_block ${showActionBtnFlag eq false ? 'hidden':'' }">
					<div class="inline_div" id="CustomizeEditNewBtn">确定</div>
				</div>
			</div>
		</div>
		<div class="customize_page hidden">
			<div class="customize_head customize_address_title">
				<div class="relative_left_block">
					<div class="inline_div customize_back_btn" id="">
						<img src="${ctx }/images/modules/monitor/customize_overlap_back.png"/>
					</div>
				</div>
				<div class="">历史记录</div>
			</div>
			<div class="customize_body customize_risk_history">
				<div class="relative_center_block">
					<div>
						<div class="inline_div history_start_round"></div>
					</div>
					<div>
						<div class="inline_div history_time_line"></div>
					</div>
				</div>
				<div class="relative_center_block">
					<div class="customize_history_list_hover"></div>
				</div>
				<div class="customize_history_list">
					<div class="customize_history">
						<div class="relative_center_block">
							<div class="round_point inline_div"></div>
						</div>
						<div class="relative_center_block">
							<div class="status_arr inline_div"></div>
						</div>
						<div class="relative_center_block">
							<div class="data_time inline_div">
								<div class="data">2016-06-18</div>
								<div class="time">23:23:23</div>
							</div>
						</div>
						<div class="history_info_block inline_div">
							<div class="history_state">
								预警修改状态：<span class="risk_before">"一般预警"</span>改为<span class="risk_after">"特别预警"</span>
							</div>
							<div class="history_info">
								<div class="info_list">
									<div class="info inline_div_block">
										<div class="risk_item inline_div">经营预警：</div>
										<div class="risk_sub_item inline_div">水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,</div>
									</div>
									<div class="info inline_div_block">
										<div class="risk_item inline_div">经营预警：</div>
										<div class="risk_sub_item inline_div">水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,</div>
									</div>
								</div>
								<div class="history_info_btn_block inline_div_block" >
									<div class="history_edit_btn inline_div">
										编辑
									</div>
									<div class="history_delete_btn inline_div">
										删除
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="customize_history">
						<div class="relative_center_block">
							<div class="round_point inline_div"></div>
						</div>
						<div class="relative_center_block">
							<div class="status_arr inline_div"></div>
						</div>
						<div class="relative_center_block">
							<div class="data_time inline_div">
								<div class="data">2016-06-18</div>
								<div class="time">23:23:23</div>
							</div>
						</div>
						<div class="history_info_block inline_div">
							<div class="history_state">
								预警修改状态：<span class="risk_before">"一般预警"</span>改为<span class="risk_after">"特别预警"</span>
							</div>
							<div class="history_info">
								<div class="info_list">
									<div class="info inline_div_block">
										<div class="risk_item inline_div">经营预警：</div>
										<div class="risk_sub_item inline_div">水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,</div>
									</div>
									<div class="info inline_div_block">
										<div class="risk_item inline_div">经营预警：</div>
										<div class="risk_sub_item inline_div">水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,水电费骤降或欠缴,</div>
									</div>
								</div>
								<div class="history_info_btn_block inline_div_block" >
									<div class="history_edit_btn inline_div">
										编辑
									</div>
									<div class="history_delete_btn inline_div">
										删除
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				
			</div>
		</div>
		<div class="customize_page hidden">
			<div class="customize_head customize_address_title">
				<div class="relative_left_block">
					<div class="inline_div customize_back_btn" id="">
						<img src="${ctx }/images/modules/monitor/customize_overlap_back.png"/>
					</div>
				</div>
				<div class="">编辑</div>
			</div>
			<div class="customize_body customize_risk">
				<div class="inline_div_block risk_level_list">
					<div class="inline_div risk_level">特别预警</div>
					<div class="inline_div risk_level">一般预警</div>
					<div class="inline_div risk_level">关注</div>
					<div class="inline_div risk_level">正常</div>
				</div>
				<div class="risk_staff">
					<img src="${ctx }/images/modules/monitor/risk_staff.png"/>
				</div>
				<div class="inline_div_block risk_item_list">
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_4.png"/>
							</div>
						</div>
						<div class="item_name" code="11">
							经营预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="51">水电费骤降或欠缴</div>
							<div class="inline_div sub_item" code="52">主营业务变动</div>
							<div class="inline_div sub_item" code="53">劳资争议</div>
							<div class="inline_div sub_item" code="54">对外投资失败</div>
							<div class="inline_div sub_item" code="55">上下游变动</div>
							<div class="inline_div sub_item" code="56">变卖主要资产</div>
							<div class="inline_div sub_item" code="57">销售额骤降</div>
							<div class="inline_div sub_item" code="58">关联交易过多</div>
						</div>
					</div>
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_1.png"/>
							</div>
						</div>
						<div class="item_name" code="12">
							信用预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="59">拖欠货款</div>
							<div class="inline_div sub_item" code="60">交付违约</div>
							<div class="inline_div sub_item" code="61">行业口碑差</div>
							<div class="inline_div sub_item" code="62">恶意欺诈</div>
						</div>
					</div>
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_2.png"/>
							</div>
						</div>
						<div class="item_name" code="13">
							财务预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="64">负债上升过快</div>
							<div class="inline_div sub_item" code="65">应收账款过多</div>
							<div class="inline_div sub_item" code="66">盈利能力下降</div>
							<div class="inline_div sub_item" code="67">现金流紧张</div>
						</div>
					</div>
					<div class="inline_div risk_item">
						<div class="relative_center_block item_icon_block">
							<div class="inline_div item_icon">
								<img src="${ctx }/images/modules/monitor/risk_item_icon_3.png"/>
							</div>
						</div>
						<div class="item_name" code="14">
							高管预警
						</div>
						<div class="sub_item_list inline_div_list">
							<div class="inline_div sub_item" code="68">涉案</div>
							<div class="inline_div sub_item" code="69">失联</div>
							<div class="inline_div sub_item" code="70">意外事故或健康恶化</div>
							<div class="inline_div sub_item" code="71">频繁变更</div>
						</div>
					</div>
				</div>
				<div>
					<textarea class="customize_reason" id="" code="72"  placeholder="请在此输入您的修改原因"></textarea>
				</div>
				<div class="customize_edit_btn_block">
					<div class="inline_div" id="CustomizeEditBtn">确定</div>
				</div>
			</div>
		</div>
	</div>
	<div class="message_overlap hidden" id="MessageOverlap">
        <div class="message_title_block">
            <div class="title">
                提示
            </div>
        </div>
        <div class="message_content_block">
            <div class="content">
                <div class="inline_div content_img tips_img"></div>
                <div class="inline_div content_value"></div>
            </div>
        </div>
        <div class="message_btn_block">
            <div id="cancel_message" class="inline_div small_overlap_btn_1">
                <div>取消</div>
            </div>
            <div id="ok_message" class="inline_div small_overlap_btn">
                确定
            </div>
        </div>
    </div>
	<div class="message_overlap hidden" id="MessageInfoOverlap">
        <div class="message_title_block">
            <div class="title">
                提示
            </div>
        </div>
        <div class="message_content_block">
            <div class="content">
                <div class="inline_div content_img tips_img"></div>
                <div class="inline_div content_value"></div>
            </div>
			<div class="inline_div content_info"></div>
        </div>
        <div class="message_btn_block">
            <div id="cancel_message" class="inline_div small_overlap_btn_1">
                <div>取消</div>
            </div>
            <div id="ok_message" class="inline_div small_overlap_btn">
                确定
            </div>
        </div>
    </div>
	<div class="hidden message_tips_overlap" id="MessageTipsOverlap">
		<div class="tips">
			尚未做任何修改,请填选内容后再确认！
		</div>
	</div>
	<div class="reason_block_overlap hidden" id="OverlapAddBlock">
		<div class="company_name" ></div>
		<div class="inline_div_block reasons">
			<div class="inline_div reason_title">加入原因：</div>
			<div class="inline_div reason_list">
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>企业近期经营不善，存高危风险</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>企业与上下游客户存在账款逾期或货物拖欠</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>企业、法人代表、股东、高管等近期涉诉或从事非法活动</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>企业因违规经营被相关管理部门处罚</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>其他</span>
					</div>
					<div class="sub_content">
						<textarea placeholder="请输入其它原因，不超过50个字" readonly="readonly"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="btn_block inline_div_block">
			<div class="inline_div cancel_btn">取消</div>
			<div class="inline_div add_btn">确认</div>
		</div>
	</div>
	<div class="reason_block_overlap hidden" id="OverlapRemoveBlock">
		<div class="company_name"></div>
		<div class="inline_div_block reasons">
			<div class="inline_div reason_title">删除原因：</div>
			<div class="inline_div reason_list">
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>企业经营状况改善，发展预期向好</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>企业获得重大融资或重大利好政策</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>排除企业或法人代表、股东、高管等涉诉负面影响</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>排除企业负面舆情影响</span>
					</div>
				</div>
				<div class="reason">
					<div class="main">
						<input type="checkbox" />
						<span>其他</span>
					</div>
					<div class="sub_content">
						<textarea placeholder="请输入其它原因，不超过50个字" readonly="readonly"></textarea>
					</div>
				</div>
			</div>
		</div>
		<div class="btn_block inline_div_block">
			<div class="inline_div cancel_btn">取消</div>
			<div class="inline_div remove_btn">确认</div>
		</div>
	</div>
</body>
</html>