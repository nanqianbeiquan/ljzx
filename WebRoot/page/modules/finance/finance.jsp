<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>

	<head>
		<%@ include file="/page/common/base.jsp"%>
			<%@ include file="/page/common/header.jsp"%>
				<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/finance/finance.css?${appVersion }">
				<link type="text/css" rel="stylesheet" href="${ctx }/css/common/overlap.css?${appVersion }">
				<%--<script type="text/javascript" src="${ctx }/js/echarts/echarts.min.js?${appVersion }"></script>--%>
				<script type="text/javascript" src="${ctx }/js/echarts/debug/echarts.js?${appVersion }"></script>
				<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
				<script type="text/javascript" src="${ctx }/js/common/layout.js?${appVersion }"></script>
				<script type="text/javascript" src="${ctx }/js/common/loadwaiting.js?${appVersion }"></script>
				<script type="text/javascript" src="${ctx }/js/modules/finance/finance.js?${appVersion }"></script>
	</head>

	<body class="ljzx_page back_gray">
	<input type="hidden" id="deep" value="${deep}" />
		<div class="hidden">
			<img src="${ctx }/images/modules/finance/disclaimer_after_spread.png"/>
		</div>
		<jsp:include page="/page/modules/index/banner.jsp" />
		<div class="body_content_5">
			<div class="body_center ">
				<div class="body_block_2 block_shadow">
					<div class="nav_bar">
						<div class="back_block">
							<div class="inline_div">
								<div class="for_back_btn" onclick=""></div>
							</div>
						</div>
						<span>
                            <a href="${ctx }/index">首页</a></span>
						<span>&gt;</span>
						<span class="current_nav">
                            <a>财务分析</a></span>
					</div>
					<div class="block_module_list">
						<div class="block_finance_module" id="block_finance_module_1">
							<div class="company_name">
							&nbsp;
							</div>
							<div style="text-align:right;">
								<div class="finance_disclaimer inline_div" id="finance_disclaimer">声明</div>
							</div>
							<div class="finance_disclaimer_content">
								<p class="content_text">
								以下信息根据国际和行业通行准则，以合法渠道获得，尽可能保证可靠、准确和完整，但并不保证所有信息的准确性和完整性。该信息不能作为投资研究决策的依据，不能作为道义的、责任的和法律的依据或者凭证，无论是否已经明示或者暗示。本系统将随时补充、更正和修订有关信息，但不保证及时发布。对于本系统所载信息所导致的任何直接的或者间接的投资盈亏后果不承担任何责任。 
								</p>
								<p class="content_text">
								本系统所载信息未经书面许可，任何机构和个人不得以任何形式翻版、复制和发布。如引用发布，需注明出处，且不得对本系统所载信息进行有悖原意的引用、删节和修改。
								</p>
								<p>上海风声企业信用征信有限公司对该声明享有最终解释权。</p>
							</div>
						</div>
						<div class="block_finance_module"  id="block_finance_module_2" style="margin:0px">
							<div class="element_module_title">财务状况</div>
							<div class="block_content">
								<div class="block_content_element" id="FinanceStatus"></div>
								<div class=" table_message" style="top:-20px;">万元(人民币)</div>
								<div class="block_content_element" id="FinanceStatusTable">
									<table class="all_col_table table_first_category">
										<thead>
											<tr>
												<td class="" style="width:181px;">
													<div class="inline_div">年份</div>
													<div class="inline_div"></div>
													<div class="inline_div">类目</div>
												</td>
												<td class="year">2014</td>
												<td class="year">2015</td>
												<td class="year">2016</td>
												<td class="evaluation">评价</td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td>资产总额</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
											<tr>
												<td>负债总额</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
											<tr>
												<td>营业总收入</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
											<tr>
												<td>净利润</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
											<tr>
												<td>纳税总额</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
										</tbody>
									</table>
									
								</div>
								<div class="hidden table_message" id="FinanceStatusMessage">注：全量数据按降序排列</div>
							</div>
						</div>
						<div class="block_finance_module" id="block_finance_module_3">
							<div class="element_module_title">能力评价</div>
							<div class="block_content">
								<div class="block_content_element">
									<div class="hidden inline_div" id="AbilityEvaluationLabel" style="position:relative;"></div>
									<div class="inline_div_block hidden" id="AbilityEvaluationBluePoints">
										<div class="inline_div blue_point"></div>
										<div class="inline_div blue_point"></div>
										<div class="inline_div blue_point"></div>
										<div class="inline_div blue_point"></div>
									</div>
									<div class=""  id="AbilityEvaluation"></div>
								</div>
								<div class="block_content_element" id="AbilityEvaluationTable">
									<table class="all_col_table table_first_category">
										<thead>
											<tr>
												<td class="" colspan="2" style="width:208px;">
													<div class="inline_div">年份</div>
													<div class="inline_div"></div>
													<div class="inline_div">类目</div>
												</td>
												<td class="year">2014</td>
												<td class="year">2015</td>
												<td class="year">2016</td>
												<td class="evaluation">评价</td>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td style="width:65px;">偿债能力</td>
												<td style="width:103px;">资产负债率</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
											<tr>
												<td rowspan=2>营业能力</td>
												<td>净利润率</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
											<tr>
												<td style="background:#ffffff;font-weight:500;font-size:14px">资产净利润率</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
											<tr>
												<td>运营能力</td>
												<td>总资产周转率</td>
												<td>--</td>
												<td>--</td>
												<td>--</td>
												<td></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="hidden table_message" id="AbilityEvaluationMessage">注：全量数据按降序排列</div>
							</div>
						</div>
						<div class="block_finance_module" >
							<div class="element_module_title">分析结论</div>
							<div class="block_content">
								<div class="block_content_element" id="FinanceSummary">
									<div class="block_content_conclusion_element">
										
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>

	</html>