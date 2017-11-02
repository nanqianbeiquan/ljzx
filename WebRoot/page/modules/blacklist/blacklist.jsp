<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
	<!DOCTYPE html>
	<html>

	<head>
		<%@ include file="/page/common/base.jsp"%>
			<%@ include file="/page/common/header.jsp"%>
				<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/blacklist/blacklist.css">
				<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
				<script type="text/javascript" src="${ctx }/js/common/layout.js"></script>
				<script type="text/javascript" src="${ctx }/js/jquery/jquery.rotate.min.js"></script>
				<script type="text/javascript" src="${ctx }/js/common/loadwaiting.js"></script>
				<script type="text/javascript" src="${ctx }/js/modules/blacklist/blacklist.js"></script>
				<script>
					var controller;
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
						controller = new BlacklistController();
						controller.init();
						var deep = $("#deep").val();
						//console.log("blacklist init,deep="+deep);
						updateNavMenu(parseInt(deep), "企业失信记录", window.location.search);
					})
				</script>
	</head>
<input type="hidden" id="deep" value="${deep}" />
	<body class="ljzx_page back_gray">
		<div class="hidden">
			<img src="${ctx }/images/modules/blacklist/disclaimer_after_spread.png"/>
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
                            <a>企业失信记录</a></span>
					</div>
					<div id="blacklist_search_block" class="">
						<div class="relative_right_block" id="">
							<div class="blacklist_disclaimer" id="blacklist_disclaimer">声明</div>
						</div>
						<div class="inline_div_block" id="search_block">
							<div class="relative_left_block" id="search_round_title">
								<div class="inline_div">
									<div class="top">失信记录</div>
									<div class="bottom">查询</div>
								</div>
							</div>
							<input class="inline_div" id="search_input" type="text" placeholder="请输入企业全称" />
							<div class="inline_div" id="search_blacklist_btn">

							</div>
						</div>
						<div class="relative_left_block" id="disclaimer_content">
							<div class="disclaimer_content_text">
								<div class="title">声明</div>
								<div class="content">
									企业失信记录库系来源于RiskRaider风险雷达专有数据库及多家专业平台的合法数据 ，通过大数据技术，提示目标企业近年来的支付信用状况。因信息迟延等原因，不保证搜索结果的完整性，搜索结果仅作参考。
								</div>
								<div class="content">
									失信记录仅为机器学习后的客观结果，不存在任何诋毁某公司的情况。本系统提供失信记录库亦不意味着为客户的商业风险提供担保。在任何情况下，对于客户的商业决策所造成的损失，无论该商业决策的做出是否参考了本系统产品及服务所载信息，本系统不承担使用者的任何商业风险，也不承担由于非控因素和疏忽而引起的相应的损失和损害。
								</div>
							</div>
						</div>
					</div>
					<div id="blacklist_company_block" class="hidden">
						<div class="blacklist_company_block">
							<div class="company_name inline_div">上海风声企业信用征信有限公司</div>
							<div class="company_info inline_div_block">
							</div>
						</div>
						<div class="company_operate_block relative_right_block">
							<div class="company_operate inline_div inline_div_block " id="add_company_btn">
								<div class="relative_right_block">
									<div class="inline_div round_btn" style="position:relative;">
										<div class="inline_div" style="width:14px;height:2px;background:#ffffff;position:absolute;top:12px;left:6px"></div>
										<div class="inline_div" style="height:14px;width:2px;background:#ffffff;position:absolute;top:6px;left:12px;"></div>
									</div>
								</div>
								<div class="content inline_div">
									<div class="value">加入失信记录</div>
								</div>
							</div>
						</div>
						<div class="company_operate_block relative_right_block">
							<div class="company_operate inline_div inline_div_block hidden" id="remove_company_btn">
								<div class="relative_right_block">
									<div class="inline_div round_btn"  style="position:relative;">
										<div class="inline_div" style="width:14px;height:2px;background:#ffffff;position:absolute;top:12px;left:6px"></div>
									</div>
								</div>
								<div class="content inline_div">
									<div class="value">移除失信记录</div>
								</div>
							</div>
						</div>
					</div>
					
					<div id="blacklist_company_list_block" class="hidden">
						<div class="relative_right_block company_list_rope_block" id="">
							<div class="inline_div rope" id="">
								<div class="inline_div rope_round_top">
									<img class="up" src="${ctx }/images/modules/blacklist/rope_down.png"/>
									<img class="down hidden" src="${ctx }/images/modules/blacklist/rope_up.png"/>
								</div>
							</div>
						</div>
						<div id="company_list_content">
							<div id="company_list">

							</div>

							<div class="page_block">
								<div class="page_plug">
									<div id="Pagination4Companylist">
									</div>
								</div>
							</div>
						</div>
						<div class="relative_right_block company_list_rope_block" id="">
							<div class="inline_div rope" id="">
								<div class="inline_div rope_round_bottom"></div>
							</div>
						</div>
					</div>
					<div id="blacklist_table_block" class="">
						<div class="title">自定义失信企业</div>
						<div class="" id="blacklist_loading_wait_block">
							<table class="first_col_table event_table data_table" id="blacklist_table">
								<thead>
									<tr>
										<td>序号</td>
										<td style="width:20%">企业名称</td>
										<td style="width:10%">账号</td>
										<td style="width:40%">加入原因</td>
										<td style="width:15%">加入时间</td>
										<td style="width:10%">操作</td>
									</tr>
								</thead>
								<tbody>
									<tr>
									</tr>
									<tr>
									</tr>
									<tr>
									</tr>
									<tr>
									</tr>
									<tr>
									</tr>
									<tr>
									</tr>
								</tbody>
							</table>
							<div class="page_block">
								<div class="page_plug">
									<div id="Pagination4Blacklist">
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="reason_block_overlap hidden" id="OverlapAddBlock">
			<div class="company_name"></div>
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