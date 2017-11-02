
var BlacklistRepository = function () {

	RepositoryConfig = {
		AddBlacklistReason: {
			"1": {
				id: 1,
				index: 1,
				reason: "企业近期经营不善，存高危风险",
				type: 0
			},
			"2": {
				id: 2,
				index: 2,
				reason: "企业与上下游客户存在账款逾期或货物拖欠",
				type: 0
			},
			"3": {
				id: 3,
				index: 3,
				reason: "企业、法人代表、股东、高管等近期涉诉或从事非法活动",
				type: 0
			},
			"4": {
				id: 4,
				index: 4,
				reason: "企业因违规经营被相关管理部门处罚",
				type: 0
			},
			"5": {
				id: 5,
				index: 5,
				reason: "其他",
				type: 1
			},
		},
		RemoveBlacklistReason: {
			"1": {
				id: 1,
				index: 1,
				reason: "企业经营状况改善，发展预期向好",
				type: 0
			},
			"2": {
				id: 2,
				index: 2,
				reason: "企业获得重大融资或重大利好政策",
				type: 0
			},
			"3": {
				id: 3,
				index: 3,
				reason: "排除企业或法人代表、股东、高管等涉诉负面影响",
				type: 0
			},
			"4": {
				id: 4,
				index: 4,
				reason: "排除企业负面舆情影响",
				type: 0
			},
			"5": {
				id: 5,
				index: 5,
				reason: "其他",
				type: 1
			},
		},
		SystemBlacklistReason: {
			"0": "该企业近几年无严重的拖欠款项记录",
			"1": "该企业近几年曾有严重的拖欠款项记录",
			"2": "该企业主要关联企业{0}近几年曾有严重拖欠记录",
			"3": "该企业与近几年有严重拖欠记录的企业（人）{0}有连带债务关系",
			"4": "该企业的主要关联企业{0}与近几年有严重拖欠记录的企业（人）{1}有连带债务关系等"
		}
	}

	function IsFunction(callback) {
		return typeof callback == "function";
	}

	function get_null_page() {
		return {
			beginIndex: 0,
			currentPageNo: 1,
			hasNextPage: false,
			hasPrePage: false,
			nextPageNo: 0,
			pageSize: 6,
			pageSum: 0,
			prePageNo: 0,
			recordSum: 0,
			results: []
		};
	}

	function PostCompanyList(model, callback) {
		var data = {
			keyword: model.value,
			fromCacheFlag: model.flag || false,
			pageNo: model.page.pageIndex,
			pageSize: model.page.pageCount
		}
		applyAjax("{0}/blacklistCompany/queryCompanyList.do".format(ctx), data, function (result) {
			if (IsFunction(callback)) {
				if (result.resultCode == "0") {
					if (result.resultData == null) {
						result.resultData = get_null_page();
					}
					var resultData = {
						page: {
							pageIndex: result.resultData.currentPageNo,
							pageTotal: result.resultData.pageSum,
							pageCount: result.resultData.pageSize,
							countTotal: result.resultData.recordSum
						},
						list: [

						]
					};
					for (var i = 0; i < result.resultData.results.length; i++) {
						var company = result.resultData.results[i];
						var model = {
							index: i,
							name: company.companyName,
							legalPerson: company.legalRepresentative || "--",
							registerCapital: company.registeredCapital || "--",
							establishDate: company.establishmentDate || "--",
							status: company.operateStatus || "--"
						}
						resultData.list.push(model);
					}
					callback(resultData);
				}
				else {
					toastr.warning(result.resultMsg);
				}

			}
		})
	}

	function BuildSystemBlacklistMessage(message) {
		var list = (message.result || "0").split(",");
		var result = [];
		for (var i = 0; i < list.length; i++) {
			switch (parseInt(list[i])) {
				case 0:
				case 1:
					result.push(RepositoryConfig.SystemBlacklistReason[list[i]]);
					break;
				case 2:
				case 3:
					var message_list = (message[list[i]] || "").split(",");
					var company_str = [];
					for (var j = 0; j < message_list.length; j++) {
						if (j >= 3) {
							company_str[company_str.length - 1] = company_str[company_str.length - 1] + "等";
							break;
						}
						company_str.push('"{0}"'.format(message_list[j]));
					}
					result.push(RepositoryConfig.SystemBlacklistReason[list[i]].format(company_str.join("、")));
					break;
				case 4:
					var index = 0;
					for (var name in message[list[i]]) {
						if (index >= 4) {
							break;
						}
						result.push(RepositoryConfig.SystemBlacklistReason[list[i]].format('"{0}"'.format(name), '"{0}"'.format(message[list[i]][name])));
						index++;
						if (index >= 3) {
							result[result.length - 1] = result[result.length - 1] + "等";
						}
					}
					break;
			}
		}
		return result;
	}

	function BuildCustomizeBlacklistReason(model) {
		var list = (model.addReason || "").split(",")
		var result = [];
		for (var i = 0; i < list.length; i++) {
			if (parseInt(list[i]) == 5) {
				result.push(model.otherAddReason || "");
			} else {
				if (RepositoryConfig.AddBlacklistReason[list[i]]) {
					result.push(RepositoryConfig.AddBlacklistReason[list[i]].reason);
				}
			}
		}

		return result;
	}

	function PostCompanyBacklistResult(model, callback) {
		var data = {
			companyName: encodeURI(model.companyName),
			"cnt": 3
		}
		applyAjax("{0}/blacklistCompany/checkBlacklist.do".format(ctx), data, function (result) {
			if (IsFunction(callback)) {
				if (result.resultCode == "0") {
					var resultData = {
						name: model.companyName,
						blacklist: parseBoolean(result.resultData.systemBlacklistFlag),
						user_blacklist: result.resultData.customBlacklistFlag,
						message: BuildSystemBlacklistMessage(result.resultData.message || {}).join("；") + "。",
						reason: result.resultData.customBlackCompany ? regsymbol(BuildCustomizeBlacklistReason(result.resultData.customBlackCompany).join("；")) + "。" : ""
					};
					callback(resultData);
				}
				else {
					toastr.warning(result.resultMsg);
				}
			}
		})
	}
	function PostBacklistList(model, callback) {
		var data = {
			pageNo: model.page.pageIndex,
			pageSize: model.page.pageCount
		}
		applyAjax("{0}/blacklistCompany/applyBlackList.do".format(ctx), data, function (result) {
			if (IsFunction(callback)) {
				if (result.resultCode == "0") {
					if (result.resultData == null) {
						result.resultData = get_null_page();
					}
					var resultData = {
						page: {
							pageIndex: result.resultData.currentPageNo,
							pageTotal: result.resultData.pageSum,
							pageCount: result.resultData.pageSize,
							countTotal: result.resultData.recordSum
						},
						list: []
					};
					for (var i = 0; i < result.resultData.results.length; i++) {
						var company = result.resultData.results[i];
						var reasonlist = [];
						if (company.addReason) {
							var reasons = company.addReason.split(",");
							for (var j = 0; j < reasons.length; j++) {
								var reason = RepositoryConfig.AddBlacklistReason[reasons[j]];
								if (reason && reason.type == 0) {
									reasonlist.push("{0}".format(reason.reason) || "");
								}
							}
						}
						if (company.otherAddReason) {
							reasonlist.push("{0}".format(regsymbol(company.otherAddReason)))
						}
						var model = {
							index: i + 1 + resultData.page.pageCount * (resultData.page.pageIndex - 1),
							name: company.companyName,
							accountName:company.accountName,
							showActionBtnFlag:company.showActionBtnFlag,
							reason: reasonlist.join("；</br>") + "。",
							date: new Date(company.addDate).Format("yyyy-MM-dd") || ""
						}
						resultData.list.push(model);
					}
					callback(resultData);
				}
				else {
					toastr.warning(result.resultMsg);
				}

			}
		})
	}
	function PostBlacklistReason(model, callback) {
		if (IsFunction(callback)) {
			var result = {
				addreason: RepositoryConfig.AddBlacklistReason,
				removereason: RepositoryConfig.RemoveBlacklistReason
			}
			callback(result);
		}
	}

	function GetBlacklistReasonList(model, callback) {
		var data = {
			companyName: encodeURI(model.companyName),
			"cnt": 3
		}
		applyAjax("{0}/blacklistCompany/checkBlacklist.do".format(ctx), data, function (result) {
			if (IsFunction(callback)) {
				if (result.resultCode == "0") {
					var resultData = {
						name: model.companyName,
						blacklist: parseBoolean(result.resultData.systemBlacklistFlag),
						user_blacklist: result.resultData.customBlacklistFlag,
						message: BuildSystemBlacklistMessage(result.resultData.message || {}),
						reason: result.resultData.customBlackCompany ? BuildCustomizeBlacklistReason(result.resultData.customBlackCompany) : []
					};
					callback(resultData);
				}
				else {
					toastr.warning(result.resultMsg);
				}
			}
		})
	}

	function DeleteBlacklistCompany(model, callback) {
		var data = {
			companyName: encodeURI(model.companyName),
			reason: model.reasons.join(","),
			otherReason: model.otherReason
		}
		applyAjax("{0}/blacklistCompany/remove.do".format(ctx), data, function (result) {
			if (IsFunction(callback)) {
				if (result.resultCode == "0") {
					var resultData = { companyName: model.companyName };
					toastr.success("删除成功!");
					callback(resultData);
				}
				else {
					toastr.warning(result.resultMsg);
				}
			}
		})
	}

	function PostBlacklistCompany(model, callback) {
		var data = {
			companyName: encodeURI(model.companyName),
			reason: model.reasons.join(","),
			otherReason: model.otherReason
		}
		applyAjax("{0}/blacklistCompany/add.do".format(ctx), data, function (result) {
			if (IsFunction(callback)) {
				if (result.resultCode == "0") {
					var resultData = {
						reason: regsymbol(BuildCustomizeBlacklistReason({
							addReason: data.reason,
							otherAddReason: data.otherReason
						}).join("；")) + "。" || ""
					};
					toastr.success("加入成功!");
					callback(resultData);
				}
				else {
					toastr.warning(result.resultMsg);
				}
			}
		})
	}

	var Repository = function () {

	}
	Repository.prototype = {
		get_company_list: function (model, callback) {
			PostCompanyList(model, callback);
		},
		get_company_backlist_result: function (model, callback) {
			PostCompanyBacklistResult(model, callback);
		},
		get_backlist_list: function (model, callback) {
			PostBacklistList(model, callback);
		},
		get_blacklist_reason: function (model, callback) {
			PostBlacklistReason(model, callback);
		},
		get_blocklist_reason_list: function (model, callback) {
			GetBlacklistReasonList(model, callback);
		},
		delete_blacklist_company: function (model, callback) {
			DeleteBlacklistCompany(model, callback);
		},
		post_blacklist_company: function (model, callback) {
			PostBlacklistCompany(model, callback);
		}
	}
	Repository.prototype.constructor = Repository;
	return Repository;
}();

var BlacklistController = function () {

	var repository = new BlacklistRepository();

	var UserBlacklist = {
		Total: -1,
		DefaultPageCount: 6,
		current_page: 0,
	}

	var CompanyList = {
		KeyWord: "",
		Total: -1,
		DefaultPageCount: 6,
		current_page: 0,
	}

	var BlacklistReason = {};

	var OverlapList = {
		OverlapAdd: null,
		OverlapRemove: null
	};

	var LoadWaitingList = {
		CompanylistWaiting: null,
	}

	var UserSelectCache = {
		blacklist_add_company: null,
		blacklist_remove_company: null
	}

	var RequestLock = {
		SearchCompanyListLock: false,
		SearchCompanyBlacklist: false,
		GetUserBlacklist: false,
	}

	var Modules = {
		Blacklist: function () {

			return {

			}
		}(),
		CompanyBlacklist: function () {
			var ModuleData = {
				CompanyName: null,
			}

			function load_analysis_blacklist_message(name) {
				ModuleData.CompanyName = name;
				var model = {
					companyName: name
				}
				repository.get_blocklist_reason_list(model, init_blacklist_message);
			}

			function init_blacklist_message(result) {
				$("#SystemBlacklistMessageBlock .info_list").empty();
				$("#SystemBlacklistMessageBlock .info_list").append("<div>{0}</div>".format(result.message.join("；</br>") + "。"));
				$("#UserBlacklistMessageBlock .info_list").empty();
				if (result.reason.length > 0) {
					$("#UserBlacklistMessageBlock .info_list").append("<div>{0}</div>".format(result.reason.join("；</br>") + "。"));
				} else {
					$("#UserBlacklistMessageBlock .info_list").append("<div>该企业未加入自定义失信列表。</div>")
				}
			}
			return {
				load_analysis_blacklist_message: load_analysis_blacklist_message
			}
		}()
	}

	var LoadList = {
		load_user_blacklist: function (page) {
			if (RequestLock.GetUserBlacklist) {
				return false;
			}
			RequestLock.GetUserBlacklist = true;
			var pageIndex = parseInt(page) || 0;
			var load = new LoadWaiting();
			UserBlacklist.current_page = pageIndex;
			var model = {
				page: {
					pageIndex: pageIndex + 1,
					pageCount: UserBlacklist.DefaultPageCount
				}
			}

			function get_row_user_blacklist(data) {
				var result = $("<tr></tr>");
				result.append("<td>{0}</td>".format(data.index || "&nbsp;"));
				result.append("<td class='name'>{0}</td>".format(data.name || "&nbsp;"));
				result.append("<td style='text-align:center;'>{0}</td>".format(data.accountName || "&nbsp;"));
				result.append("<td>{0}</td>".format(data.reason || "&nbsp;"));
				result.append("<td>{0}</td>".format(data.date || "&nbsp;"));
				if(data.showActionBtnFlag==true||data.showActionBtnFlag=="true"){
					var delete_td = $("<td class='{1}'>{0}</td>".format(data.index == undefined ? "&nbsp;" : "删除", data.index == undefined ? "" : "link_td"));
					if (data.index) {
						$(delete_td).click(EventList.click_remove_blacklist_btn);
					}
					
				}else{
					var delete_td = "<td ></td>";
				}
				result.append(delete_td);
				return result;
			}

			function show_table_page_info(page) {
				$("#Pagination4Blacklist").pagination(page.countTotal, {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					items_per_page: page.pageCount,
					callback: LoadList.load_user_blacklist
				});
			}

			function show_table_user_blacklist(result) {
				var page = result.page;
				var len = result.list.length == page.pageCount ? result.list.length : page.pageCount;

				$("#blacklist_table tbody").empty();
				for (var i = 0; i < len; i++) {
					$("#blacklist_table tbody").append(get_row_user_blacklist(result.list[i] || {}));
				}

				if (page.countTotal != UserBlacklist.Total) {
					UserBlacklist.Total = page.countTotal;
					show_table_page_info(page);
				}
				load.close();
				RequestLock.GetUserBlacklist = false;
			}
			load.show("#blacklist_loading_wait_block");
			repository.get_backlist_list(model, show_table_user_blacklist);

			return this;
		},
		load_company_list: function (page) {
			if (RequestLock.SearchCompanyListLock) {
				toastr.warning("正在查询中，请勿重复操作！");
				return false;
			}
			RequestLock.SearchCompanyListLock = true;
			if (LoadWaitingList.CompanylistWaiting) {
				LoadWaitingList.CompanylistWaiting.close();
			}
			LoadWaitingList.CompanylistWaiting = new LoadWaiting();
			LoadList.fade_in_company_list();
			var pageIndex = parseInt(page) || 0;
			CompanyList.current_page = pageIndex;
			var model = {
				page: {
					pageIndex: pageIndex + 1,
					pageCount: CompanyList.DefaultPageCount
				},
				value: CompanyList.KeyWord,
				flag: !!page
			}

			function show_list_page_info(page) {
				$("#Pagination4Companylist").pagination(page.countTotal, {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					items_per_page: page.pageCount,
					callback: LoadList.load_company_list
				});
			}

			function get_row_company(data) {
				var result = $("<div class='company'></div>");
				var name = $("<div class='name inline_div'>{0}</div>".format(data.name || "&nbsp;"));
				var info = $("<div class='info'></div>");

				$(name).click(EventList.click_company_name_btn);

				info.append("<div class='inline_div info_legal_person'><div class='value'>{0}</div><div class='sub_title'>法定代表人</div></div>".format(data.legalPerson || "--"));
				info.append("<div class='inline_div info_register_capital'><div class='value'>{0}</div><div class='sub_title'>注册资本</div></div>".format(data.registerCapital || "--"));
				info.append("<div class='inline_div info_establish_date'><div class='value'>{0}</div><div class='sub_title'>成立日期</div></div>".format(data.establishDate || "--"));
				info.append("<div class='inline_div info_status'><div class='value'>{0}</div><div class='sub_title'>经营状态</div></div>".format(data.status || "--"));

				result
					.append(name)
					.append(info);

				return result;
			}

			function show_list_company_list(result) {
				var page = result.page;
				var len = result.list.length;

				$("#company_list").empty();
				if (len > 0) {
					for (var i = 0; i < len; i++) {
						$("#company_list").append(get_row_company(result.list[i] || {}));
					}
				} else {
					$("#company_list").append("<div style='text-align:center'>未搜索到公司！</div>");
				}

				if (page.countTotal != CompanyList.Total) {
					CompanyList.Total = page.countTotal;
					show_list_page_info(page);
				}
				if (LoadWaitingList.CompanylistWaiting) {
					LoadWaitingList.CompanylistWaiting.close();
					LoadWaitingList.CompanylistWaiting = null;
				}
				EventList.bind_click_rope_btn(true);
				RequestLock.SearchCompanyListLock = false;
			}
			repository.get_company_list(model, show_list_company_list);
			return this;
		},
		load_company_blacklist_info: function (name) {
			function show_company_backlist_info(result) {
				var message = $(tostring(function () {/*
					<div class="message">
						<div class="message_title inline_div"></div>
						<div class="message_content inline_div"></div>
					</div>
				*/}))
				result.blacklist ? $("#blacklist_company_block").addClass("blacklist_company") : $("#blacklist_company_block").removeClass("blacklist_company");
				result.user_blacklist ? $("#blacklist_company_block").addClass("blacklist_customize_company") : $("#blacklist_company_block").removeClass("blacklist_customize_company");

				if (result.user_blacklist) {
					$("#add_company_btn").addClass("hidden");
					$("#remove_company_btn").removeClass("hidden");
				} else {
					$("#add_company_btn").removeClass("hidden");
					$("#remove_company_btn").addClass("hidden");
				}
				$("#blacklist_company_block .company_name").html(result.name);
				$("#blacklist_company_block .company_info").empty();
				var system_message = $(message).clone();
				$(system_message).find(".message_title").append("系统记录：");
				$(system_message).find(".message_content").append(result.message);
				$("#blacklist_company_block .company_info").append(system_message);
				if (result.user_blacklist) {
					var user_message = $(message).clone();
					$(user_message).addClass("user_message");
					$(user_message).find(".message_title").append("自定义失信：");
					$(user_message).find(".message_content").append(result.reason);
					$("#blacklist_company_block .company_info").append(user_message);
				}

				UserSelectCache.blacklist_add_company = result.name;
				LoadList.fade_out_company_list();
				load.close();
			}
			var load = new LoadWaiting();
			var model = {
				companyName: name
			}
			UserSelectCache.blacklist_add_company = name;
			load.show("#blacklist_company_list_block");
			repository.get_company_backlist_result(model, show_company_backlist_info);
			return this;
		},
		load_blacklist_reason: function () {
			function save_blacklist_reason(result) {
				BlacklistReason = result;
			}
			var model = {};
			repository.get_blacklist_reason(model, save_blacklist_reason);
			return this;
		},
		load_overlap_list: function () {
			function get_add_block_overlap() {
				return new overlap({
					mask: {
						show: true
					},
					scroller: {
						lock: true,
					},
					position: {
						type: "center"
					},
					close: {
						show: true,
						type: "close_2"
					},
					content: {
						style: {
							width: "602px",
							height: "502px",
							border: "16px solid rgba(0,0,0,0.15)"
						}
					}
				});
			}
			function get_remove_block_overlap() {
				return new overlap({
					mask: {
						show: true
					},
					scroller: {
						lock: true,
					},
					position: {
						type: "center"
					},
					close: {
						show: true,
						type: "close_2"
					},
					content: {
						style: {
							width: "602px",
							height: "502px",
							border: "16px solid rgba(0,0,0,0.15)"
						}
					}
				});
			}
			OverlapList.OverlapAdd = get_add_block_overlap();
			OverlapList.OverlapRemove = get_remove_block_overlap();
			return this;
		},
		load_waiting_list: function () {
			LoadWaitingList.CompanylistWaiting = new LoadWaiting();
		},
		load_add_blacklist_reason: function () {
			function get_subcontent() {
				var result = $("<div class='sub_content'></div>");
				$(result).append("<textarea placeholder='请输入其它原因，不超过50个字' readonly='readonly'></textarea>");
				return result;
			}
			function get_reason(i, data) {
				var result = $("<div class='reason' index='{0}'></div>".format(i));
				var main = $("<div class='main'></div>")
				var check_box = $("<input type='checkbox'/>").click(EventList.click_reason_input);

				$(main)
					.append(check_box)
					.append("<span>{0}</span>".format(data.reason == "其他" ? data.reason + "：" : data.reason + "。" || "&nbsp;"));

				$(result).append(main);

				switch (data.type) {
					case 1:
						$(result).append(get_subcontent());
						break;
					case 0:
					default:
						break;
				}
				return result;
			}
			function show_add_reasons() {
				$("#OverlapAddBlock .reason_list").empty();
				for (var i in BlacklistReason.addreason) {
					$("#OverlapAddBlock .reason_list").append(get_reason(i, BlacklistReason.addreason[i]));
				}
				$("#OverlapAddBlock .company_name").html(UserSelectCache.blacklist_add_company);
			}
			show_add_reasons();
			OverlapList.OverlapAdd.show("#OverlapAddBlock");
			return this;
		},
		load_remove_blacklist_reason: function () {
			function get_subcontent() {
				var result = $("<div class='sub_content'></div>");
				$(result).append("<textarea placeholder='请输入其它原因，不超过50个字' readonly='readonly'></textarea>");
				return result;
			}
			function get_reason(i, data) {
				var result = $("<div class='reason' index='{0}'></div>".format(i));
				var main = $("<div class='main'></div>")
				var check_box = $("<input type='checkbox'/>").click(EventList.click_reason_input);

				$(main)
					.append(check_box)
					.append("<span>{0}</span>".format(data.reason == "其他" ? data.reason + "：" : data.reason + "。" || "&nbsp;"));

				$(result).append(main);

				switch (data.type) {
					case 1:
						$(result).append(get_subcontent());
						break;
					case 0:
					default:
						break;
				}
				return result;
			}
			function show_add_reasons() {
				$("#OverlapRemoveBlock .reason_list").empty();
				for (var i in BlacklistReason.removereason) {
					$("#OverlapRemoveBlock .reason_list").append(get_reason(i, BlacklistReason.removereason[i]));
				}
				$("#OverlapRemoveBlock .company_name").html(UserSelectCache.blacklist_remove_company);
			}
			show_add_reasons();
			OverlapList.OverlapRemove.show("#OverlapRemoveBlock");
			return this;
		},
		fade_in_company_list: function () {
			$("#blacklist_company_block").fadeOut(500, function () {

				$("#blacklist_company_list_block").fadeIn(500, function () {
					$("#blacklist_company_list_block").removeClass("hidden");
					if (LoadWaitingList.CompanylistWaiting) {
						LoadWaitingList.CompanylistWaiting.show("#blacklist_company_list_block");
					}
				});
			})
		},
		fade_out_company_list: function () {
			$("#blacklist_company_list_block").fadeOut(500, function () {
				$("#blacklist_company_list_block").addClass("hidden");
				$("#blacklist_company_block").fadeIn(500);
			})
		}
	}
	var EventList = {
		EventModel: {
			disclaimer: {
				binded: false,
				click_disclaimer: true,
				height_min: "128px",
				height_max: "324px",
				animate_speed: 1000,
			},
			rope: {
				binded: false,
				click_rope: true,
				height_min: "116px",
				height_max: "756px",
				animate_speed: 1000,
			},
			round: {
				animate_speed: 1000,
				width_min: "26px",
				width_max: "138px",
				rounding: false,
				spreading: false,
				hover: false,
				start_angle: 0,
				end_angle: -180
			}
		},
		bind_click_disclaimer_btn: function () {
			var disclaimer = this.EventModel.disclaimer;

			function disclaimer_show() {
				$("#blacklist_search_block")
					.stop()
					.animate({ height: disclaimer.height_max }, disclaimer.animate_speed);
				$("#blacklist_disclaimer").addClass("blacklist_disclaimer_spread");
			}

			function disclaimer_close() {
				$("#blacklist_search_block")
					.stop()
					.animate({ height: disclaimer.height_min }, disclaimer.animate_speed);
				$("#blacklist_disclaimer").removeClass("blacklist_disclaimer_spread");
			}

			if (!disclaimer.binded) {
				disclaimer.binded = !disclaimer.binded;
				$("#blacklist_disclaimer").click(function () {
					disclaimer.click_disclaimer ? disclaimer_show() : disclaimer_close();
					disclaimer.click_disclaimer = !disclaimer.click_disclaimer;
				})
			}
			else {

			}
			return this;
		},
		bind_click_search_btn: function () {
			function click_search_btn() {
				if (RequestLock.SearchCompanyListLock) {
					toastr.warning("正在查询中，请勿重复操作！");
					return false;
				}
				$("#search_input").blur();
				var company_name = $("#search_input").val();

				if (company_name.length <= 0) {
					toastr.info('请输入关键字进行查询！');
					return;
				}
				CompanyList.KeyWord = company_name;
				CompanyList.Total = -1;
				LoadList.load_company_list();

			}

			$("#search_blacklist_btn").click(function () {
				click_search_btn();
			})

			$('#search_input').bind('keypress', function (e) {
				var key_num = window.event ? e.keyCode : e.which;
				if (key_num == 13) {
					click_search_btn();
				}
			});

			return this;
		},
		bind_click_rope_btn: function (spread) {
			var show = spread || false;
			var rope = this.EventModel.rope;

			function rope_show() {
				$(".rope_round_top img").addClass("hidden");
				$(".rope_round_top .up").removeClass("hidden");
				$("#company_list_content")
					.stop()
					.animate({ height: rope.height_max }, rope.animate_speed);
			}

			function rope_close() {
				$(".rope_round_top img").addClass("hidden");
				$(".rope_round_top .down").removeClass("hidden");
				$("#company_list_content")
					.stop()
					.animate({ height: rope.height_min }, rope.animate_speed);
			}

			if (!rope.binded) {
				rope.binded = true;
				$(".rope_round_top").click(function () {
					rope.click_rope ? rope_close() : rope_show();
					rope.click_rope = !rope.click_rope;
				})
			} else {
				show ? rope_show() : rope_close();
				rope.click_rope = show;
			}
			return this;
		},
		bind_hover_round_btn: function () {
			var round = this.EventModel.round;
			function hover_in_round_btn() {
				round.hover = true;
				var _this = this;
				function round_spread() {
					if (!round.hover) {
						return;
					}
					round.rouding = false;
					round.spreading = true;
					$(_this).find(".round_btn").stop().rotate({ angle: round.start_angle });
					$(_this).find(".content").stop().animate({ width: "138px" }, round.animate_speed, function () {
						round.spreading = false;
					});
				}
				function round_rotate() {
					if (!round.hover) {
						return;
					}
					round.rouding = true;
					$(_this).find(".round_btn").rotate({ animateTo: round.end_angle, callback: round_spread });
				}
				round.spreading ? round_spread() : round_rotate();
			}
			function hover_out_round_btn() {
				round.hover = false;
				var _this = this;
				function round_derotate() {
					round.spreading = false;
					round.rouding = true;
					$(_this).find(".round_btn").stop().rotate({ angle: round.end_angle });
					$(_this).find(".round_btn").stop().rotate({
						animateTo: round.start_angle, callback: function () {
							round.rouding = false;
						}
					});
				}
				function round_despread() {
					round.spreading = true;
					$(_this).find(".content").stop().animate({ width: "26px" }, round.animate_speed, round_derotate);
				}
				round_despread();
			}
			$("#add_company_btn").hover(hover_in_round_btn, hover_out_round_btn);
			$("#remove_company_btn").hover(hover_in_round_btn, hover_out_round_btn);
			return this;
		},
		bind_click_round_btn: function () {
			function show_overlap() {
				UserSelectCache.blacklist_remove_company = $("#blacklist_company_block .company_name").text()
				LoadList.load_remove_blacklist_reason();
			}
			$("#add_company_btn").click(LoadList.load_add_blacklist_reason);
			$("#remove_company_btn").click(show_overlap);
			return this;
		},
		bind_overlap_click: function () {
			function get_reason(block, reason) {
				var result = {
					reasons: [],
					sub_reason: "",
					success: true,
				}
				$(block).find(".reason_list input:checked").each(function () {
					var index = parseInt($(this).parents(".reason").attr("index"));
					var data = reason[index];
					if (data) {
						result.reasons.push(data.id);
						switch (data.type) {
							case 1:
								var value = $(this).parents(".reason").find(".sub_content textarea").val();
								if (value.trim().length > 50) {
									result.success = false;
									toastr.warning("其它原因过长，请输入不超过50个字");
								}
								if (value.trim().length <= 0) {
									result.sub_reason += data.reason + "。";
								} else {
									result.sub_reason += data.reason + "：" + regsymbol(value) + "。"
								}
								break;
							case 0:
							default:
								break;
						}
					}
				})
				return result;
			}

			function post_company_result(result) {
				var message = $(tostring(function () {/*
					<div class="message">
						<div class="message_title inline_div"></div>
						<div class="message_content inline_div"></div>
					</div>
				*/}))
				LoadList.load_user_blacklist();
				$("#add_company_btn").addClass("hidden");
				$("#remove_company_btn").removeClass("hidden");
				$("#blacklist_company_block .company_info .user_message").remove();
				var user_message = $(message).clone();
				$(user_message).addClass("user_message");
				$(user_message).find(".message_title").append("自定义失信：");
				$(user_message).find(".message_content").append(result.reason);
				$("#blacklist_company_block .company_info").append(user_message);
				$("#blacklist_company_block").addClass("blacklist_customize_company");
				click_cancel_add_overlap();
			}
			function click_cancel_add_overlap() {
				OverlapList.OverlapAdd.close();
			}
			function click_add_add_overlap() {
				var reasons = get_reason("#OverlapAddBlock", BlacklistReason.addreason);
				if (!reasons.success) {
					return;
				}
				if (reasons.reasons.length > 0) {
					var model = {
						companyName: UserSelectCache.blacklist_add_company,
						reasons: reasons.reasons,
						otherReason: reasons.sub_reason
					}
					repository.post_blacklist_company(model, post_company_result)
				} else {
					toastr.info('请选择加入原因！');
				}
			}


			function delete_company_result(result) {
				LoadList.load_user_blacklist(UserBlacklist.current_page);
				if (result.companyName === $("#blacklist_company_block .company_name").text()) {
					$("#remove_company_btn").addClass("hidden");
					$("#add_company_btn").removeClass("hidden");
					$("#blacklist_company_block .company_info .user_message").remove();
					$("#blacklist_company_block").removeClass("blacklist_customize_company");
				}
				click_cancel_remove_overlap();
			}
			function click_cancel_remove_overlap() {
				OverlapList.OverlapRemove.close();
			}
			function click_remove_remove_overlap() {
				var reasons = get_reason("#OverlapRemoveBlock", BlacklistReason.removereason);
				if (!reasons.success) {
					return;
				}
				if (reasons.reasons.length > 0) {
					var model = {
						companyName: UserSelectCache.blacklist_remove_company,
						reasons: reasons.reasons,
						otherReason: reasons.sub_reason
					}
					repository.delete_blacklist_company(model, delete_company_result)
				} else {
					toastr.info('请选择删除原因！');
				}
			}

			$("#OverlapAddBlock .cancel_btn").click(click_cancel_add_overlap);
			$("#OverlapAddBlock .add_btn").click(click_add_add_overlap);
			$("#OverlapRemoveBlock .cancel_btn").click(click_cancel_remove_overlap);
			$("#OverlapRemoveBlock .remove_btn").click(click_remove_remove_overlap);
			return this;
		},
		click_company_name_btn: function () {
			var name = $(this).text();
			LoadList.load_company_blacklist_info(name);
			return this;
		},
		click_remove_blacklist_btn: function () {
			UserSelectCache.blacklist_remove_company = $(this).parents("tr").find(".name").text();
			LoadList.load_remove_blacklist_reason();
			return this;
		},
		click_reason_input: function () {
			var _this = this;
			function show_subcontent() {
				$(_this).parents(".reason").find(".sub_content textarea").attr("readonly", false).focus();
			}
			function close_subcontent() {
				$(_this).parents(".reason").find(".sub_content textarea").attr("readonly", true).val("").blur();
			}
			$(_this).is(':checked') ? show_subcontent() : close_subcontent();
		}
	}

	function load_data() {
		LoadList
			.load_user_blacklist()
			.load_blacklist_reason()
			.load_overlap_list();
	}

	function bind_event() {
		EventList
			.bind_click_disclaimer_btn()
			.bind_click_search_btn()
			.bind_click_rope_btn()
			.bind_hover_round_btn()
			.bind_click_round_btn()
			.bind_overlap_click();
	}

	var BlacklistController = function () {

	}
	BlacklistController.prototype = {
		init: function () {
			load_data();
			bind_event();
		},
		monitor_company_init: function (name) {
			Modules.CompanyBlacklist.load_analysis_blacklist_message(name);
		}
	}
	BlacklistController.prototype.constructor = BlacklistController;
	return BlacklistController;
}();
