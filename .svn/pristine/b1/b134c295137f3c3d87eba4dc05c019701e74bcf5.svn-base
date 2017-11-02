<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<%@ include file="/page/common/base.jsp"%>
		<%@ include file="/page/common/header.jsp"%>
		<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>

		<link type="text/css" rel="stylesheet" href="${ctx }/css/common/bootstrap.min.css?${appVersion }">
		<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/index/index.css?${appVersion }">

		<script type="text/javascript" src="${ctx }/js/echarts/echarts.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/echarts/echartController.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/common/bootstrap.min.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/common/editInput.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/modules/monitor/dailyReport.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/modules/monitor/import_batch.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/modules/index/index.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/modules/index/footer.js?${appVersion }"></script>

		<script type="text/javascript">
			$(function () {
				toastr.options = {
					"closeButton": false, //是否显示关闭按钮
					"debug": false, //是否使用debug模式
					"positionClass": "toast-top-center",//弹出窗的位置
					"showDuration": "300",//显示的动画时间
					"hideDuration": "1000",//消失的动画时间
					"timeOut": "2000", //展现时间
					"extendedTimeOut": "1000",//加长展示时间
					"showEasing": "swing",//显示时的动画缓冲方式
					"hideEasing": "linear",//消失时的动画缓冲方式
					"showMethod": "fadeIn",//显示时的动画方式
					"hideMethod": "fadeOut" //消失时的动画方式
				};
				
				var opts = {
					lines: 13, // 花瓣数目
					length: 10, // 花瓣长度
					width: 5, // 花瓣宽度
					radius: 10, // 花瓣距中心半径
					corners: 1, // 花瓣圆滑度 (0-1)
					rotate: 0, // 花瓣旋转角度
					direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
					color: '#618dbe', // 花瓣颜色
					speed: 1, // 花瓣旋转速度
					trail: 60, // 花瓣旋转时的拖影(百分比)
					shadow: false, // 花瓣是否显示阴影
					hwaccel: false, //spinner 是否启用硬件加速及高速旋转            
					className: 'spinner', // spinner css 样式名称
					zIndex: 2e9, // spinner的z轴 (默认是2000000000)
					top: '50%', // spinner 相对父容器Top定位 单位 px
					left: '50%'// spinner 相对父容器Left定位 单位 px
				};

				window.spinner = new Spinner(opts);
				controller = new homeController();

				updateNavMenu(0, "首页", window.location.search);

				//初始化各事件等级公司数量趋势图
				initCompanyNumTrendChartOfEventLevel("1");

				//初始化高风险公司区域分布图
				initDistributionChartOfHighRiskCompany();

				controller.show_notice();
			})
		</script>
	</head>

	<body class="ljzx_page back_gray">
		<input type="hidden" id="totalCompanyNum" name="totalCompanyNum" value="${totalCompanyNum }" />
		<input type="hidden" id="highRiskCompanyNum" name="highRiskCompanyNum" value="${highRiskCompanyNum }" />
		<input type="hidden" id="middleRiskCompanyNum" name="middleRiskCompanyNum" value="${middleRiskCompanyNum }" />
		<input type="hidden" id="lowRiskCompanyNum" name="lowRiskCompanyNum" value="${lowRiskCompanyNum }" />
		<input type="hidden" id="noRiskCompanyNum" name="noRiskCompanyNum" value="${noRiskCompanyNum }" />
		<input type="hidden" id="warnCompanyNum" name="warnCompanyNum" value="${warnCompanyNum }" />
		<input type="hidden" id="attentionCompanyNum" name="attentionCompanyNum" value="${attentionCompanyNum }" />
		<input type="hidden" id="normalCompanyNum" name="normalCompanyNum" value="${normalCompanyNum }" />
		<input type="hidden" id="currentAccountId" name="currentAccountId" value="${currentAccount.accountId }" />
		<input type="hidden" id="deep" name="deep" value="0" />

		<!-- 引入头部 -->
		<jsp:include page="/page/modules/index/banner.jsp" />

		<div id="myCarousel" class="carousel slide">
			<!-- 轮播（Carousel）指标 -->
			<ol class="carousel-indicators hidden">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
			</ol>
			<!-- 轮播（Carousel）项目 -->
			<div class="carousel-inner">
				<div class="item active">
					<div class="head_banner head_title" alt="First slide"></div>
				</div>
				<div class="item">
					<div class="head_banner head_finance" alt="Second slide"></div>
				</div>
			</div>
			<!-- 轮播（Carousel）导航 -->
			<a class="carousel-control left" href="#myCarousel" data-slide="prev"><span class="glyphicon glyphicon-menu-left" style="color:#ffffff;position:relative;left:-60px;top:130px;"></span></a>
			<a class="carousel-control right" href="#myCarousel" data-slide="next"><span class="glyphicon glyphicon-menu-right" style="color:#ffffff;position:relative;left:60px;top:130px;"></a>
		</div>

		<div class="body_content_4">
			<div class="body_center ">
				<div class="body_block block_shadow">
					<div id="CurrentAccountSpan" class="absolute_block">
						<div class="current_account_span inline_div">
							<img src="${ctx }/images/modules/tree/root_account_icon.png"/>
							<span>当前账户：${currentAccount.accountName }</span>
						</div>
						<div class="current_account_left_span inline_div"></div>
					</div>
					<div class="body_block_head relative_left_block">
						<div class="group_info_title">当前分组</div>
					</div>
					<div class="inline_div_block">
						<div class="body_block_title" style="margin:0px 10px;">分组编辑</div>
						<div class="group_block">
							<div class="default_group inline_div">
								<div class="group inline_div">
									<div class="close_block" onclick="toMonitorCompanyList('全部','','','','','','')">
										<img src="${ctx }/images/modules/monitor/default_row.png" />
									</div>
									<div>
										<div class="group_img" onclick="toMonitorCompanyList('全部','','','','','','')">
											<img src="${ctx }/images/modules/monitor/default_group.png" />
										</div>
										<div class="group_info middle_risk_back">
											<span class="group_name">全部</span>(<span class="group_value"><c:if test="${totalMonitorCompanyNum > 999}">999+</c:if><c:if test="${totalMonitorCompanyNum <= 999}">${totalMonitorCompanyNum }</c:if></span>)
										</div>
									</div>
								</div>
							</div>
							<div id="monitorGroupLeftArrow" class="prev_group_btn inline_div">
								<c:if test="${monitorGroupNum > 5 }">
									<div class="prev_group">
										<div class="prev_group_hover_block inline_div">
											<img class="prev_group_img hidden" src="${ctx }/images/modules/monitor/prev_group.png" />
											<img class="prev_group_hover_img hidden" style="display:none;" src="${ctx }/images/modules/monitor/prev_group_hover.png"
											/>
										</div>
									</div>
								</c:if>
							</div>
							<div id="monitorGroupList" class="user_group inline_div inline_div_block" style="font-size:0px;">
								<c:forEach var="monitorGroup" items="${monitorGroupList }">
									<div class="group inline_div">
										<div class="group_id hidden">${monitorGroup.groupId}</div>
										<div class="close_block">
											<c:if test="${account.accountId eq currentAccount.accountId }">
												<div class="relative_right_block">
													<div class="close_btn inline_div">&times;</div>
												</div>
											</c:if>
											<img src="${ctx }/images/modules/monitor/user_row.png" />
										</div>
										<div>
											<div class="group_img" onclick="toMonitorCompanyList('${monitorGroup.groupName }','','','','','','')">
												<img src="${ctx }/images/modules/monitor/user_group.png" />
											</div>
											<div class="group_info low_risk_back">
												<span class="group_name inline_div" style="max-width:108px;overflow:hidden;"><span class="inline_div line_txt" style="max-width:56px;text-align:center;top:-1px;positioin:relative;">${monitorGroup.groupName }</span>(
												<span class="group_value">
												<c:if test="${monitorGroup.companyNum > 999}">999+</c:if>
												<c:if test="${monitorGroup.companyNum <= 999}">${monitorGroup.companyNum }</c:if>
												</span>)</span>
												<div class="group_remark hidden">${monitorGroup.remark}</div>
											</div>
										</div>
									</div>
								</c:forEach>
								<c:if test="${monitorGroupNum eq 0 }">
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
								</c:if>
								<c:if test="${monitorGroupNum eq 1 }">
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
								</c:if>
								<c:if test="${monitorGroupNum eq 2 }">
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
								</c:if>
								<c:if test="${monitorGroupNum eq 3 }">
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
								</c:if>
								<c:if test="${monitorGroupNum eq 4 }">
									<div class="default_user_group group inline_div">
										<img src="${ctx }/images/modules/index/user_default_group_back.png" />
									</div>
								</c:if>
							</div>
							<div id="monitorGroupRightArrow" class="next_group_btn inline_div">
								<c:if test="${monitorGroupNum > 5 }">
									<div class="next_group">
										<div class="next_group_hover_block inline_div">
											<img class="next_group_img hidden" src="${ctx }/images/modules/monitor/next_group.png" />
											<img class="next_group_hover_img hidden" style="display:none" src="${ctx }/images/modules/monitor/next_group_hover.png" />
										</div>
									</div>
								</c:if>
							</div>
							<c:if test="${account.accountId eq currentAccount.accountId }">
								<div class="add_group inline_div">
							</c:if>
							<c:if test="${account.accountId ne currentAccount.accountId }">
								<div class="add_group inline_div hidden">
							</c:if>
								<div class="group inline_div">
									<div class="close_block">
										<img src="${ctx }/images/modules/monitor/user_row.png" />
									</div>
									<div>
										<div class="group_img">+</div>
										<div class="group_info">新增分组</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>

				<div class="body_block block_shadow">
					<div class="body_block_head relative_left_block">
						<div class="monitor_info_title">监控报告</div>
					</div>
					<div class="inline_div_block" id="monitor_report">
						<div class="relative_left_block" id="monitor_right_report">
							<div class="" id="">
								<div id="blacklist_overview_block" class="">
									<a class="t-wsy"  id="link_blacklist">
										<div class="t-sec t-bgi-bg3">
											<i class="t-bgi-b3"></i>
										</div>
										<p class="font12 f-mgt">失信记录(<span>${blackListNum}</span>)</p>
										<i class="t-bgi-more">点击详情</i>
									</a>
								</div>
								<div class="monitor_overview_bottom_line" class="hidden"></div>
								<div id="monitor_overview">
									<div class="body_block_title title_block">
										<div class="inline_div monitor_report_title">监控概况</div>
									</div>
									<div class="content_list" style="padding:0px;">
										<div class="overview_line">
											<div class="overview_info inline_div total_overview">
												<div class="value_block"><span class="value line_txt inline_div">${totalCompanyNum }</span><span class="unit">家</span></div>
												<div class="title inline_div">主体企业</div>
											</div>
											<div class="overview_info inline_div relate_overview">
												<div class="value_block"><span class="value line_txt inline_div">${totalRelationCompanyNum }</span><span class="unit">家</span></div>
												<div class="title inline_div">关系企业</div>
											</div>
										</div>
										<div class="overview_line">
											<div class="overview_table_title">风险变化<span class="num">（${totalCompanyNum }家）</span></div>
											<div class="overview_table">
												<table class="">
													<tr>
														<td class="overview_up_icon">升高</td>
														<td class="overview_down_icon">降低</td>
														<td class="overview_line_icon">不变</td>
													</tr>
													<tr>
														<td>
															<span class="high_light" onclick="toMonitorCompanyList('','','','','','1','')">${riskUpCompanyNum }</span>
														</td>
														<td>
															<span class="high_light" onclick="toMonitorCompanyList('','','','','','2','')">${riskDownCompanyNum }</span>
														</td>
														<td>
															<span class="high_light" onclick="toMonitorCompanyList('','','','','','0','')">${riskInvariantCompanyNum }</span>
														</td>
													</tr>
												</table>
											</div>
										</div>
										<div class="overview_line">
											<div class="overview_table_title">更新事件<span class="num">（${newEventCompanyNum }家）</span></div>
											<div class="overview_table">
												<table class="">
													<tr>
														<td>严重</td>
														<td>异常</td>
														<td>一般</td>
													</tr>
													<tr>
														<td>
															<span class="high_light" onclick="toMonitorCompanyList('','','1','','3','','')">${newWarnCompanyNum }</span>
														</td>
														<td>
															<span class="high_light" onclick="toMonitorCompanyList('','','1','','2','','')">${newAttentionCompanyNum }</span>
														</td>
														<td>
															<span class="high_light" onclick="toMonitorCompanyList('','','1','','1','','')">${newNormalCompanyNum }</span>
														</td>
													</tr>
												</table>
											</div>
										</div>
									</div>
								</div>
								<div class="monitor_overview_bottom_line"></div>
								<div>
									<div class="body_block_title title_block">
										<div class="more_list_btn_block">
											<div class="inline_div more_btn more_point_block" onclick="toMonitorCompanyList('','','1','','','','')">
												<div class="more_point inline_div"></div>
												<div class="more_point inline_div"></div>
												<div class="more_point inline_div"></div>
											</div>
										</div>
										<div class="inline_div monitor_report_title">新增事件企业</div>
									</div>
									<div class="content_list">
										<c:if test="${empty newEventCompanyList}">
											<div class="no_monitor_company">
												<img src="${ctx }/images/common/no_monitor_company.png" />
											</div>
										</c:if>
										<c:if test="${not empty newEventCompanyList}">
											<c:forEach var="company" items="${newEventCompanyList }">
												<div class="right_body_content_block">
													<div class="round_block inline_div">
														<div class="round inline_div"></div>
													</div>
													<div class="content inline_div">
														<div class="title line_txt" onclick="toCompanyDetail('${company.monitorID }')">${company.companyName }</div>
														<div class="info line_txt">
															<span>加入监控日期 : <fmt:formatDate value="${company.monitorDate }" pattern="yyyy-MM-dd"/></span>
														</div>
													</div>
												</div>
											</c:forEach>
										</c:if>
									</div>
								</div>
							</div>
						</div>
						<div class="inline_div main_content">
							<div class="inline_div content_block ">
								<div class="body_block_title">风险预览</div>
								<div class="monitor_show">
									<div class="inline_div risk_level round_1" onclick="toMonitorCompanyList('','3','','','','','')">
										<div class="risk_num_center inline_div">
											<div class="round line_txt">
												<div class="round_text"><a href="#">${highRiskCompanyNum }</a>家</div>
											</div>
										</div>
										<div id="round_1" class="inline_div round"></div>
										<div class="inline_div risk_info">
											<div class="risk_txt risk_txt_1">特别预警</div>
											<div class="risk_num">${highRiskCompanyNum }/${totalCompanyNum }</div>
										</div>
									</div>
									<div class="inline_div risk_level round_2" onclick="toMonitorCompanyList('','2','','','','','')">
										<div class="risk_num_center inline_div">
											<div class="round line_txt">
												<div class="round_text"><a href="#">${middleRiskCompanyNum }</a>家</div>
											</div>
										</div>
										<div id="round_2" class="inline_div round"></div>
										<div class="inline_div risk_info">
											<div class="risk_txt risk_txt_2">一般预警</div>
											<div class="risk_num">${middleRiskCompanyNum }/${totalCompanyNum }</div>
										</div>
									</div>
									<div class="inline_div risk_level round_3" onclick="toMonitorCompanyList('','1','','','','','')">
										<div class="risk_num_center inline_div">
											<div class="round line_txt">
												<div class="round_text"><a href="#">${lowRiskCompanyNum }</a>家</div>
											</div>
										</div>
										<div id="round_3" class="inline_div round"></div>
										<div class="inline_div risk_info">
											<div class="risk_txt risk_txt_3">&nbsp;&nbsp;&nbsp;关&nbsp;注&nbsp;&nbsp;&nbsp;</div>
											<div class="risk_num">${lowRiskCompanyNum }/${totalCompanyNum }</div>
										</div>
									</div>
									<div class="inline_div risk_level round_4" onclick="toMonitorCompanyList('','0','','','','','')">
										<div class="risk_num_center inline_div">
											<div class="round line_txt">
												<div class="round_text"><a href="#">${noRiskCompanyNum }</a>家</div>
											</div>
										</div>
										<div id="round_4" class="inline_div round"></div>
										<div class="inline_div risk_info">
											<div class="risk_txt risk_txt_4">&nbsp;&nbsp;&nbsp;正&nbsp;常&nbsp;&nbsp;&nbsp;</div>
											<div class="risk_num">${noRiskCompanyNum }/${totalCompanyNum }</div>
										</div>
									</div>
								</div>
								<div class="monitor_summary">
									<div class="inline_div summary_content" onclick="toMonitorCompanyList('','','','','','','1')">今日新增企业<span class="high_light">${todayMonitorCompanyNum }</span>家</div>
									<div class="inline_div summary_content" onclick="toMonitorCompanyList('','','1','','','','')">有更新事件<span class="high_light">${newEventCompanyNum }</span>家</div>
								</div>
							</div>
							<div class="inline_div content_block">
								<div class="inline_div" id="monitor_event_group">

									<div class="legend_block">
										<div class="legend_content">
											<div>
												<div class="inline_div legend_color_3"></div>
												<div class="inline_div">严重</div>
											</div>
											<div>
												<div class="inline_div legend_color_2"></div>
												<div class="inline_div">异常</div>
											</div>
											<div>
												<div class="inline_div legend_color_1"></div>
												<div class="inline_div">一般</div>
											</div>
										</div>
									</div>
									<div class="relative_left_block">
										<div class="body_block_title">事件分组</div>
									</div>

									<div class="event_num_block">
										<div class="event_num inline_div">
											<span class="num">${totalCompanyNum }</span><span class="unit">家</span>
										</div>
									</div>
									<div id="monitor_events" class="line_echart_block">
									</div>
								</div>
								<div class="inline_div" id="monitor_event_right_border"></div>
								<div class="inline_div" id="monitor_event_risk">
									<div class="body_block_title">风险事件</div>
									<div class="calender_selector_block">
										<div class="inline_div_block  calender_selector">
											<div class="inline_div day_select select selected" onclick="initCompanyNumTrendChartOfEventLevel('1')">按日
											</div>
											<div class="inline_div week_select select " onclick="initCompanyNumTrendChartOfEventLevel('2')">按周
											</div>
											<div class="inline_div month_select select " onclick="initCompanyNumTrendChartOfEventLevel('3')">按月</div>
										</div>
									</div>
									<div id="monitor_events_change" class="line_echart_block"></div>
								</div>
							</div>
							<div class="inline_div content_block">
								<div class="body_block_title">风险分布</div>
								<c:if test="${highRiskAreaNum gt 0}">
									<div class="monitor_range_list_block ">
										<div class="monitor_range_list inline_div">
											<div class="list_title">企业分布于<span>${areaNum }</span>个省市地区</div>
											<div class="list_sub_title">特别预警排名靠前区域</div>
											<div class="list">
												<c:forEach items="${monitorCompanyDistributionList }" var="monitorCompanyDistribution" varStatus="status">
													<c:if test="${monitorCompanyDistribution.highRiskCompanyNum gt 0}">
														<div class="li">
															<div class="inline_div  span">${status.count}</div>
															<div class="inline_div name">${monitorCompanyDistribution.areaName }</div>
															<div class="inline_div num">
																<fmt:formatNumber type="number" value="${monitorCompanyDistribution.highRiskCompanyPercent}" maxFractionDigits="0" />%（${monitorCompanyDistribution.highRiskCompanyNum }家）</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</div>
									</div>
								</c:if>
								<div id="monitor_area" class="line_echart_block"></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="footer">
			<div class="footer_content">
				<div class="inline_div left_footer">
					<div>
						<div class="inline_div foot_link link_span" onclick="toDisclaimer()">免责声明</div>
						<div class="inline_div foot_link link_span" onClick="toQuestions();">常见问题</div>
						<!--<div class="inline_div foot_link link_span" style="cursor: auto;">联系我们</div> -->
						<div class="inline_div foot_link link_span" onclick="toAboutUs()">关于我们</div>
						<div class="inline_div foot_link link_span" onclick="toFeedBack()">意见反馈</div>
						<!--<div class="inline_div foot_link link_span" style="cursor: auto;">友情链接</div> -->
					</div>
					<div class="copy_right">
						<div class="copy_right_line">© 2016 上海风声企业信用征信有限公司 All Rights Reserved</div>
						<div class="copy_right_line">沪ICP备17027023号-1</div>
					</div>
				</div>
				<div class="inline_div right_footer">
					<div>
						<div class="inline_div foot_block">
							<div class="foot_hover">
								<div class="hover_content">
									<div class="weixin_hover">
										<div class="inline_div content">
											<img src="${ctx }/images/common/weixin.png" />
											<div class="">微信公众号</div>
											<div></div>
										</div>
										<div class="inline_div content_line"></div>
										<div class="inline_div content">
											<img width="91px" src="${ctx }/images/common/officialAccounts.jpg" />
										</div>
										<div class="arr_border_block">
											<div class="triangle_down_border inline_div"></div>
										</div>
										<div class="arr_block">
											<div class="triangle_down inline_div"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="value link_span">
								<img src="${ctx }/images/common/weixin_gray.png" />
								<span class="">微信公众号</span>
							</div>
						</div>
						<div class="inline_div foot_block">
							<div class="foot_hover">
								<div class="hover_content">
									<div class="qq_hover">
										<div class="inline_div content">
											<img src="${ctx }/images/common/qq.png" />
											<div>在线QQ客服</div>
											<div><a target="_blank" href="http://wpa.qq.com/msgrd?v=3&uin=1360596806&site=qq&menu=yes" class="qq_click">点击咨询</a></div>
										</div>
										<div class="arr_border_block">
											<div class="triangle_down_border inline_div"></div>
										</div>
										<div class="arr_block">
											<div class="triangle_down inline_div"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="value link_span">
								<img src="${ctx }/images/common/qq_gray.png" />
								<span class="">在线客服</span>
							</div>
						</div>
						<div class="inline_div foot_block">
							<div class="foot_hover">
								<div class="hover_content">
									<div class="phone_hover">
										<div class="inline_div shot_content">
											<img src="${ctx }/images/common/phone.png" />
											<div>客服电话</div>
										</div>
										<div class="inline_div content_line"></div>
										<div class="inline_div">
											<div class="phone_number">021-68580928-8011</div>
											<div class="word_day">周一至周五:9:00~18:00</div>
											<div class="release_day">周六周日:休息</div>
										</div>
										<div class="arr_border_block">
											<div class="triangle_down_border inline_div"></div>
										</div>
										<div class="arr_block">
											<div class="triangle_down inline_div"></div>
										</div>
									</div>
								</div>
							</div>
							<div class="value link_span">
								<img src="${ctx }/images/common/phone_gray.png" />
								<span class=>客服电话</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="new_group_edit edit_block hidden">
			<div class="edit_title">新建分组</div>
			<div class="option_list">
				<div class="inline_div option_title">组名:</div>
				<div class="inline_div option_value">
					<div class="option_text">
						<input type="text" id="groupName" name="groupName" maxlength="10" placeholder="输入组名" />
					</div>
				</div>
			</div>
			<div class="option_list">
				<div class="inline_div option_title">描述:</div>
				<div class="inline_div option_value">
					<div class="option_area">
						<textarea id="remark" name="remark" maxlength="50"></textarea>
					</div>
				</div>
			</div>
			<div class="btn_block">
				<div class="inline_div small_btn save_new_group">保存</div>
			</div>
		</div>

		<div class="import_company_list_over_lap hidden">
			<div class="import_title"><span>上传EXCEL文件</span></div>
			<div class="import_path">
				<form id="uploadForm">
					<div class="inline_div">
						<input type="text" id="filePath" name="filePath" readonly="readonly" />
					</div>
					<div class="inline_div">
						<div id=browseFileBtn class="inline_div btn_4">浏览</div>
					</div>
					<div class="inline_div">
						<div id="import_company_list_btn" class="inline_div btn_4_color">确定</div>
					</div>
					<input type="file" id="fileInput" name="fileInput" style="display: none" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					/>
				</form>
			</div>
			<div class="import_message">
				<div>
					<img src="${ctx }/images/modules/monitor/import_message_icon.png" />
				</div>
				<div>
					<div class="message_title message">上传文件要求</div>
					<div class="message">1.仅支持Excel格式，请下载模板后填写;<span class="more_btn" onClick="downloadTemplate()">模板下载</span></div>
					<div class="message">2.每次上传上限为100家，100家以外不做处理;</div>
					<div class="message">3.仅支持企业名称精准匹配的公司导入。</div>
				</div>
			</div>
		</div>

		<div class="import_result_overlap hidden">
			<div class="result_title">
				<img src="${ctx }/images/modules/monitor/import_result_title.png" />
			</div>
			<div class="result_title_value ">
				<img src="${ctx }/images/modules/monitor/import_result_ok.png" />
				<span class="inline_div value">
			已成功添加<span id="successNum">50</span>家公司！
				</span>
			</div>
			<div id="canMonitorMsg" class="result_table_block" style="margin-top: 0px;"></div>
			<div class="result_table_block">
				<div class="result_failed_message">
					<img src="${ctx }/images/modules/monitor/import_result_message.png" />
					<span class="inline_div value">
				添加失败（<span  id="failedNum">3</span>家）
					</span>
				</div>
				<table class="import_result_table first_col_table">
					<thead>
						<tr>
							<td>公司名称</td>
							<td>失败原因</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="">
								<div class="company_name_list">
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
									<div>荷兰皇家壳牌石油公司</div>
								</div>
							</td>
							<td>
								<div class="import_err_message">
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
									<div>重复导入</div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>

			</div>
			<div class="export_error_company">
				<div class="export_error_company_btn inline_div btn">
					导出失败公司
				</div>
			</div>
		</div>
		<div id="NoticeBlockOverlap" class="hidden">

			<div class="inline_div notice_list">
				<%-- <div class="inline_div notice_info notice_history">
			<img src="${ctx }/images/modules/index/new_notice_history.png"/>
		</div> --%>
			</div>
			<div class="relative_left_block">
				<div class="inline_div prev_notice hidden"></div>
			</div>
			<div class="relative_right_block">
				<div class="inline_div next_notice hidden"></div>
			</div>
		</div>

		<div class="deltips_overlap message_overlap hidden" id="deleteGroupWindow">
			<div class="message_title_block">
				<div class="title">提示</div>
			</div>
			<div class="message_content_block">
				<div class="content">
					<div class="inline_div content_img tips_img"></div>
					<div class="inline_div">是否确定删除该组？</div>
				</div>
			</div>
			<div class="message_btn_block">
				<div id="cancel_message" class="inline_div small_overlap_btn_1">
					<div>取消</div>
				</div>
				<input type="hidden" id="groupId" name="groupId" value="" />
				<div id="del_message" class="inline_div small_overlap_btn">删除</div>
			</div>
		</div>
	</body>
</html>