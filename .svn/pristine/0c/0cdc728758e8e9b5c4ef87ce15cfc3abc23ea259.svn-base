<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<%@ include file="/page/common/base.jsp"%>
		<%@ include file="/page/common/header.jsp"%>
		<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>

		<link type="text/css" rel="stylesheet" href="${ctx }/css/common/bootstrap.min.css?${appVersion }">
		<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/index/index.css?${appVersion }">
		<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/index/summary_index.css?${appVersion }">
		<script type="text/javascript" src="${ctx }/js/echarts/debug/echarts.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/echarts/debug/map/china.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/common/bootstrap.min.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/modules/monitor/dailyReport.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/modules/monitor/import_batch.js?${appVersion }"></script>
		<script type="text/javascript" src="${ctx }/js/modules/index/summary_index.js?${appVersion }"></script>
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

				updateNavMenu(0, "首页", window.location.search);
				controller = new OverViewController();
				
				//黑名单点击事件
		    	$("#link_blacklist").click(function(){
		    		window.location.href = ctx+"/blacklistCompany/toBlacklistView.do?deep=1";
		    	});
			})
		</script>
	</head>

	<body class="ljzx_page back_gray">
		<input type="hidden" id="deep" name="deep" value="0" />
		<input type="hidden" id="totalCompanyNum" name="totalCompanyNum" value="${totalCompanyNum }" />
		<input type="hidden" id="highRiskCompanyNum" name="highRiskCompanyNum" value="${highRiskCompanyNum }" />
		<input type="hidden" id="middleRiskCompanyNum" name="middleRiskCompanyNum" value="${middleRiskCompanyNum }" />
		<input type="hidden" id="lowRiskCompanyNum" name="lowRiskCompanyNum" value="${lowRiskCompanyNum }" />
		<input type="hidden" id="noRiskCompanyNum" name="noRiskCompanyNum" value="${noRiskCompanyNum }" />
		<input type="hidden" id="currentAccountId" name="currentAccountId" value="${currentAccount.accountId }" />

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
					<div class="body_block_title">账户分组</div>
					<div class="inline_div_block" id="AccountInfoBlock">
						<div class="inline_div" id="CurrentAccount">
							<div class="account account_back_1" onclick="toMonitorCompanyListFromSummary('${motherMonitorQuota.accountId }','ACCOUNT','','','','','','')">
								<div class="account_icon">
									<div class="inline_div absolute_block">
										<div class="triangle_icon_1"></div>
									</div>
									<span>母</span>
								</div>
								<div class="account_num account_num_1">监控企业</div>
								<div class="account_percent">${motherMonitorQuota.monitorCompanyNum }</div>
								<div class="account_name">${motherMonitorQuota.accountName }</div>
								<div class="account_index"></div>
							</div>
						</div>
						<div class="inline_div">
							<div class="inline_div" id="AccountListLeftBtn">
								
							</div>
							<div class="inline_div" id="AccountList">
								<div class="account account_back_2 inline_div" onclick="toMonitorCompanyListFromSummary('${selfMonitorQuota.accountId }','GROUP','','','','','','')">
									<div class="account_icon">
										<div class="inline_div absolute_block">
											<div class="triangle_icon_2"></div>
										</div>
										<span>子</span>
									</div>
									<div class="account_num account_num_2">监控企业</div>
									<div class="account_percent">${selfMonitorQuota.monitorCompanyNum }</div>
									<div class="account_name">${selfMonitorQuota.accountName }（子）</div>
									<div class="position_name">${selfMonitorQuota.name }</div>
									<div class="account_index"><span class="absolute_block inline_div" id="kaka"><span class="account_index_right inline_div">1</span></span></div>
								</div>
								<c:forEach var="accountMonitorQuota" items="${childAccountMonitorQuotaList }" varStatus="status">
									
									<div class="account account_back_2 inline_div" 
										<c:if test="${accountMonitorQuota.isHaveChild eq '0' }">
											onclick="toMonitorCompanyListFromSummary('${accountMonitorQuota.accountId }','GROUP','','','','','','')"
										</c:if>
										<c:if test="${accountMonitorQuota.isHaveChild eq '1' }">
											onclick="toMonitorCompanyListFromSummary('${accountMonitorQuota.accountId }','ACCOUNT','','','','','','')"
										</c:if>
									>
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_2"></div>
											</div>
											<span>子</span>
										</div>
										<div class="account_num account_num_2">监控企业</div>
										<div class="account_percent">${accountMonitorQuota.monitorCompanyNum }</div>
										<div class="account_name">${accountMonitorQuota.accountName }</div>
										<div class="position_name">${accountMonitorQuota.name }</div>
										<div class="account_index"><span class="absolute_block inline_div" id="kaka"><span class="account_index_right inline_div">${status.count+1 }</span></span></div>
									</div>
								</c:forEach>
								<c:if test="${accountNum eq 0 }">
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
								</c:if>
								<c:if test="${accountNum eq 1 }">
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
								</c:if>
								<c:if test="${accountNum eq 2 }">
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
								</c:if>
								<c:if test="${accountNum eq 3 }">
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
								</c:if>
								<c:if test="${accountNum eq 4 }">
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
								</c:if>
								<c:if test="${accountNum eq 5 }">
									<div class="account account_back_0 inline_div">
										<div class="account_icon">
											<div class="inline_div absolute_block">
												<div class="triangle_icon_0"></div>
											</div>
											<span>&nbsp;</span>
										</div>
										<div class="account_num account_num_0">额度：0/00</div>
										<div class="account_percent">--%</div>
										<div class="account_name">账户名称</div>
										<div class="account_index"></div>
									</div>
								</c:if>
							</div>
							<div class="inline_div" id="AccountListRightBtn">
							
							</div>
						</div>
					</div>
				</div>
				<div class="body_split_block inline_div_block">
					<div class="inline_div" style="width:590px;">
						<div class="body_block block_shadow inline_div" id="OverviewRiskBlock" style="height:322px;">
							<div class="body_block_title">风险预览</div>
							<div class="overview_content">
								<div class="absolute_block">
									<div class="relative_center_block overview_total">0家</div>
								</div>
								<div id="OverviewRiskDistribution"></div>
								<div class="inline_div_block risk_overview_list">
									<div class="inline_div risk_overview_info risk_overview_info_3" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','3','','','','','')">
										<div class="risk_overview_num">0家</div>
										<div class="risk_overview_percent">0%</div>
										<div class="risk_overview_tips">特别预警</div>
									</div>
									<div class="inline_div risk_overview_info risk_overview_info_2" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','2','','','','','')">
										<div class="risk_overview_num">0家</div>
										<div class="risk_overview_percent">0%</div>
										<div class="risk_overview_tips">一般预警</div>
									</div>
									<div class="inline_div risk_overview_info risk_overview_info_1" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','1','','','','','')">
										<div class="risk_overview_num">0家</div>
										<div class="risk_overview_percent">0%</div>
										<div class="risk_overview_tips">关注</div>
									</div>
									<div class="inline_div risk_overview_info risk_overview_info_0" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','0','','','','','')">
										<div class="risk_overview_num">0家</div>
										<div class="risk_overview_percent">0%</div>
										<div class="risk_overview_tips">正常</div>
									</div>
								</div>
							</div>
						</div>
						
					</div>
					<div class="inline_div" style="width:591px;">
						<div class="body_block block_shadow inline_div" id="DynamicMonitoryInfo" style="width:278px;margin:0px 6px 0px 0px;display:inline-block;height:66px;">
							<div class="body_block_title">实时动态</div>
							<div class="inline_div_block">
								<div class="inline_div monitor_dynamic_info" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','','','','','1')">
									今日新增<span>${todayMonitorCompanyNum }</span>家
								</div>
								<div class="inline_div monitor_dynamic_info" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','1','','','','')">
									有更新事件<span>${newEventCompanyNum }</span>家
								</div>
							</div>
						</div>
						<div class="body_block block_shadow inline_div" style="width:278px;padding:0px;display:inline-block;height:96px;">
							<div id="blacklist_overview_block" class="">
								<a class="t-wsy" id="link_blacklist">
									<div class="t-sec t-bgi-bg3">
										<i class="t-bgi-b3"></i>
									</div>
									<p class="font12 f-mgt">失信记录(<span>${blackListNum }</span>)</p>
									<i class="t-bgi-more">点击详情</i>
								</a>
							</div>
						</div>
						<div class="body_block block_shadow inline_div" id="MonitorOverview" style="height:214px;margin:10px 0px 0px 0px;">
							<div class="body_block_title">监控概况</div>
							<div class="inline_div_block">
								<div class="inline_div overview_line">
									<div class="overview_info inline_div total_overview">
										<div class="value_block"><span class="value line_txt inline_div">${totalCompanyNum }</span><span class="unit">家</span></div>
										<div class="title inline_div">主体企业</div>
									</div>
									<div class="overview_table_title">风险变化<span class="num">（${riskUpCompanyNum+riskDownCompanyNum+riskInvariantCompanyNum }家）</span></div>
									<div class="overview_table">
										<table class="">
											<tr>
												<td class="overview_up_icon">升高</td>
												<td class="overview_down_icon">降低</td>
												<td class="overview_line_icon">不变</td>
											</tr>
											<tr>
												<td>
													<span class="high_light" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','','','','1','')">${riskUpCompanyNum }</span>
												</td>
												<td>
													<span class="high_light" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','','','','2','')">${riskDownCompanyNum }</span>
												</td>
												<td>
													<span class="high_light" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','','','','0','')">${riskInvariantCompanyNum }</span>
												</td>
											</tr>
										</table>
									</div>
								</div>
								<div class="inline_div overview_line">
									<div class="overview_info inline_div relate_overview">
										<div class="value_block"><span class="value line_txt inline_div">${totalRelationCompanyNum }</span><span class="unit">家</span></div>
										<div class="title inline_div">关系企业</div>
									</div>
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
													<span class="high_light" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','1','','3','','')">${newWarnCompanyNum }</span>
												</td>
												<td>
													<span class="high_light" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','1','','2','','')">${newAttentionCompanyNum }</span>
												</td>
												<td>
													<span class="high_light" onclick="toMonitorCompanyListFromSummary('${currentAccount.accountId }','ACCOUNT','','1','','1','','')">${newNormalCompanyNum }</span>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="body_block block_shadow inline_div" id="AccountMonitorCompany">
					<div class="body_block_title">风险分析</div>
					<div class="inline_div_block">
						<div class="inline_div account_structure">
							<div class="inline_div account_level">
								<div class="company_num">${highRiskCompanyNum }</div>
								<div class="monitor_level monitor_level_3">特别预警</div>
							</div>
							<div class="inline_div account_list">
								<div class="inline_div account" onclick="toMonitorCompanyListFromSummary('${selfRiskSituation.accountId }','GROUP','3','','','','','')">
									<div>
										<div class="monitor_company_back inline_div"></div>
										<div class="monitor_company_percent inline_div" style="height:${selfRiskSituation.highRiskCompanyNumProportion/2}px">
											<div class="absolute_block">
												<div class="relative_center_block percent_value_block">
													<div class="percent_value">${selfRiskSituation.highRiskCompanyNumProportion }%</div>
													<div class="percent_icon">
														<div class="percent_icon_blank"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="monitor_company_num">${selfRiskSituation.highRiskCompanyNum }家</div>
									<div class="account_name">${selfRiskSituation.accountName }</div>
								</div>
								<c:forEach var="riskSituation" items="${riskSituationList }">
									<div class="inline_div account" 
										<c:if test="${riskSituation.isHaveChild eq '0' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','GROUP','3','','','','','')"
										</c:if>
										<c:if test="${riskSituation.isHaveChild eq '1' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','ACCOUNT','3','','','','','')"
										</c:if>
									>
										<div>
											<div class="monitor_company_back inline_div"></div>
											<div class="monitor_company_percent inline_div" style="height:${riskSituation.highRiskCompanyNumProportion/2}px">
												<div class="absolute_block">
													<div class="relative_center_block percent_value_block">
														<div class="percent_value">${riskSituation.highRiskCompanyNumProportion }%</div>
														<div class="percent_icon">
															<div class="percent_icon_blank"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="monitor_company_num">${riskSituation.highRiskCompanyNum }家</div>
										<div class="account_name">${riskSituation.accountName }</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="inline_div account_structure">
							<div class="inline_div account_level">
								<div class="company_num">${middleRiskCompanyNum }</div>
								<div class="monitor_level monitor_level_2">一般预警</div>
							</div>
							<div class="inline_div account_list">
								<div class="inline_div account" onclick="toMonitorCompanyListFromSummary('${selfRiskSituation.accountId }','GROUP','2','','','','','')">
									<div>
										<div class="monitor_company_back inline_div"></div>
										<div class="monitor_company_percent inline_div" style="height:${selfRiskSituation.middleRiskCompanyNumProportion/2}px">
											<div class="absolute_block">
												<div class="relative_center_block percent_value_block">
													<div class="percent_value">${selfRiskSituation.middleRiskCompanyNumProportion }%</div>
													<div class="percent_icon">
														<div class="percent_icon_blank"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="monitor_company_num">${selfRiskSituation.middleRiskCompanyNum }家</div>
									<div class="account_name">${selfRiskSituation.accountName }</div>
								</div>
								<c:forEach var="riskSituation" items="${riskSituationList }">
									<div class="inline_div account"
										<c:if test="${riskSituation.isHaveChild eq '0' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','GROUP','2','','','','','')"
										</c:if>
										<c:if test="${riskSituation.isHaveChild eq '1' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','ACCOUNT','2','','','','','')"
										</c:if>
									>
										<div>
											<div class="monitor_company_back inline_div"></div>
											<div class="monitor_company_percent inline_div" style="height:${riskSituation.middleRiskCompanyNumProportion/2}px">
												<div class="absolute_block">
													<div class="relative_center_block percent_value_block">
														<div class="percent_value">${riskSituation.middleRiskCompanyNumProportion }%</div>
														<div class="percent_icon">
															<div class="percent_icon_blank"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="monitor_company_num">${riskSituation.middleRiskCompanyNum }家</div>
										<div class="account_name">${riskSituation.accountName }</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="inline_div account_structure">
							<div class="inline_div account_level">
								<div class="company_num">${lowRiskCompanyNum }</div>
								<div class="monitor_level monitor_level_1">关注</div>
							</div>
							<div class="inline_div account_list">
								<div class="inline_div account" onclick="toMonitorCompanyListFromSummary('${selfRiskSituation.accountId }','GROUP','1','','','','','')">
									<div>
										<div class="monitor_company_back inline_div"></div>
										<div class="monitor_company_percent inline_div" style="height:${selfRiskSituation.lowRiskCompanyNumProportion/2}px">
											<div class="absolute_block">
												<div class="relative_center_block percent_value_block">
													<div class="percent_value">${selfRiskSituation.lowRiskCompanyNumProportion }%</div>
													<div class="percent_icon">
														<div class="percent_icon_blank"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="monitor_company_num">${selfRiskSituation.lowRiskCompanyNum }家</div>
									<div class="account_name">${selfRiskSituation.accountName }</div>
								</div>
								<c:forEach var="riskSituation" items="${riskSituationList }">
									<div class="inline_div account"
										<c:if test="${riskSituation.isHaveChild eq '0' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','GROUP','1','','','','','')"
										</c:if>
										<c:if test="${riskSituation.isHaveChild eq '1' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','ACCOUNT','1','','','','','')"
										</c:if>
									>
										<div>
											<div class="monitor_company_back inline_div"></div>
											<div class="monitor_company_percent inline_div" style="height:${riskSituation.lowRiskCompanyNumProportion/2}px">
												<div class="absolute_block">
													<div class="relative_center_block percent_value_block">
														<div class="percent_value">${riskSituation.lowRiskCompanyNumProportion }%</div>
														<div class="percent_icon">
															<div class="percent_icon_blank"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="monitor_company_num">${riskSituation.lowRiskCompanyNum }家</div>
										<div class="account_name">${riskSituation.accountName }</div>
									</div>
								</c:forEach>
							</div>
						</div>
						<div class="inline_div account_structure">
							<div class="inline_div account_level">
								<div class="company_num">${noRiskCompanyNum }</div>
								<div class="monitor_level monitor_level_0">正常</div>
							</div>
							<div class="inline_div account_list">
								<div class="inline_div account" onclick="toMonitorCompanyListFromSummary('${selfRiskSituation.accountId }','GROUP','0','','','','','')">
									<div>
										<div class="monitor_company_back inline_div"></div>
										<div class="monitor_company_percent inline_div"  style="height:${selfRiskSituation.noRiskCompanyNumProportion/2}px">
											<div class="absolute_block">
												<div class="relative_center_block percent_value_block">
													<div class="percent_value">${selfRiskSituation.noRiskCompanyNumProportion }%</div>
													<div class="percent_icon">
														<div class="percent_icon_blank"></div>
													</div>
												</div>
											</div>
										</div>
									</div>
									<div class="monitor_company_num">${selfRiskSituation.noRiskCompanyNum }家</div>
									<div class="account_name">${selfRiskSituation.accountName }</div>
								</div>
								<c:forEach var="riskSituation" items="${riskSituationList }">
									<div class="inline_div account"
										<c:if test="${riskSituation.isHaveChild eq '0' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','GROUP','0','','','','','')"
										</c:if>
										<c:if test="${riskSituation.isHaveChild eq '1' }">
											onclick="toMonitorCompanyListFromSummary('${riskSituation.accountId }','ACCOUNT','0','','','','','')"
										</c:if>
									>
										<div>
											<div class="monitor_company_back inline_div"></div>
											<div class="monitor_company_percent inline_div"  style="height:${riskSituation.noRiskCompanyNumProportion/2}px">
												<div class="absolute_block">
													<div class="relative_center_block percent_value_block">
														<div class="percent_value">${riskSituation.noRiskCompanyNumProportion }%</div>
														<div class="percent_icon">
															<div class="percent_icon_blank"></div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="monitor_company_num">${riskSituation.noRiskCompanyNum }家</div>
										<div class="account_name">${riskSituation.accountName }</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
				<div class="body_block block_shadow inline_div">
					<div class="body_block_title">区域风险</div>
					<c:if test="${highRiskAreaNum gt 0}">
						<div>
							<div class="monitor_range_list_block">
								<div class="monitor_range_list inline_div">
									<div class="list_title">企业分布于<span>${areaNum }</span>个省市地区</div>
									<div class="list_sub_title">特别预警集中区域</div>
									<div class="list">
										<c:forEach items="${monitorCompanyDistributionList }" var="monitorCompanyDistribution" varStatus="status">
											<c:if test="${monitorCompanyDistribution.highRiskCompanyNum gt 0}">
												<div class="li">
													<div class="inline_div  span ">${status.count}</div>
													<div class="inline_div name">${monitorCompanyDistribution.areaName }</div>
													<div class="inline_div num">
														<fmt:formatNumber type="number" value="${monitorCompanyDistribution.highRiskCompanyPercent}" maxFractionDigits="0" />%（${monitorCompanyDistribution.highRiskCompanyNum }家）</div>
												</div>
											</c:if>
										</c:forEach>
									</div>
								</div>
							</div>
						</div>
					</c:if>
					<div id="MonitorAreaMap"></div>
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
		<div class="" id="ReloadPic">
			<div id="ReloadAccountListRightHoverBtn"></div>
			<div id="ReloadAccountListLeftHoverBtn"></div>
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