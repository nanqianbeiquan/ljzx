
//搜索公司
function queryCompanyInfoList(keyword) {

	spinner.spin($("#companyList").get(0));

	var parameter = {
		"keyword": keyword,
		"currentPageNo": parseInt(1),
		"pageSize": parseInt(10)
	}

	$.ajax({
		url: ctx + "/companyInfo/queryCompanyInfoList",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			$(".search_tips_values").append("找到<span>" + result.total + "</span>条相关结果，为您展示匹配度高的" + result.companyInfoNum + "条");

			$("#companyList").html("");//清除之前的数据

			$("#companyList").append(formatCompanyList(result.companyInfoList));//添加新的数据
			controller.rebind_company_list();
			$("#Pagination").pagination(result.companyInfoNum, {
				prev_text: "上一页",
				next_text: "下一页",
				num_edge_entries: 3,
				num_display_entries: 10,
				items_per_page: 10,
				//回调 
				callback: pageSelectCallback
			});
		},
		error: function () {
			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {
			spinner.spin();
			if (XMLHttpRequest.responseText.indexOf("login4Web.js") > -1) {
				window.location = ctx + "/login";
			}
		}
	});

}


//分页回调
function pageSelectCallback(new_page_index, pagination_container) {
	spinner.spin($("#companyList").get(0));

	var currentPageNo = parseInt(new_page_index) + 1;

	var parameter = {
		"keyword": $("#currentKeyword").val(),
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10)
	}

	$.ajax({
		url: ctx + "/companyInfo/queryCompanyInfoListFromCache",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			$("#companyList").html("");//清除之前的数据

			$("#companyList").append(formatCompanyList(result.companyInfoList));//添加新的数据
			controller.rebind_company_list();
		},
		error: function () {
			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {
			spinner.spin();
		}
	});
}

//格式化结果数据
function formatCompanyList(results) {

	var content = '';

	for (var key in results) {
		content += '<div class="company">';
		content += '<div class="inline_div right_block"><div class="monitor_btn">';
		if (results[key].monitorFlag == '1') {
			content += '<div class="btn inline_div remove_monitor">取消监控</div>';
		} else {
			content += '<div class="btn inline_div">加入监控</div>';
		}
		var maxLen = 25;
		if (results[key].registerInfo.operateStatus.length < maxLen) {
			var harfLen = parseInt((maxLen - results[key].registerInfo.operateStatus.length) / 2);
			for (var i = 0; i < harfLen; i++) {
				results[key].registerInfo.operateStatus = results[key].registerInfo.operateStatus + "&nbsp;";
			}
		}
		content += '</div></div>';

		content += '<div class="inline_div left_block"><div class="company_name" onclick="toCompanyMonitorDetail(\'' + results[key].monitorId + '\', \'' + results[key].registerInfo.enterpriseName + '\', \'' + new Date(results[key].monitorDate).pattern("yyyy-MM-dd") + '\',\'' + results[key].monitorFlag + '\')">'
			+ results[key].registerInfo.enterpriseName + '</div><div class="company_info_block"><div class="inline_div company_info"><div class="value line_txt">'
			+ results[key].registerInfo.legalRepresentative + '</div><div class="tips">法定代表人</div></div><div class="inline_div company_info"><div class="value line_txt">';

		if (results[key].registerInfo.registeredCapital == '--') {
			content += results[key].registerInfo.registeredCapital + '万</div><div class="tips">注册资本</div></div><div class="inline_div company_info"><div class="value line_txt">';
		} else {
			content += new Number(results[key].registerInfo.registeredCapital) + '万</div><div class="tips">注册资本</div></div><div class="inline_div company_info"><div class="value line_txt">';
		}

		content += results[key].registerInfo.establishmentDate + '</div><div class="tips">成立时间</div></div><div class="inline_div company_info"><div class="value line_txt" style="padding:0px;width:200px;margin:0px">'
			+ results[key].registerInfo.operateStatus + '</div><div class="tips">经营状态</div></div></div></div><div class="inline_div right_block">';

		content += '</div></div>';
	}

	return content;
}

var companyListController = function () {

	var event_add_monitor = function (rebind) {
		var lap = OverlapList.GroupListOverlap;
		var message_lap = OverlapList.MessageDeleteOverlap;
		$(".company .btn").click(function () {
			$(".current_company_btn").removeClass("current_company_btn")
			var value = $(this).text();

			if (value == "加入监控") {
				$(this).addClass("current_company_btn");

				document.getElementById("addMonitorCompany").onclick = function () {
					addMonitorCompany();
				}

				//获取动态监控分组
				getMonitorGroupList($(this).parent().parent().parent().children(".left_block").children(".company_name").text());

				PageConfig.PageModel.OpenNewGroup = true;

				lap.reset({
					position: {
						x_pos: $(this).offset().left + 100,
						y_pos: $(this).offset().top - 16
					}
				})

				lap.show(".select_group_overlap");
			} else {
				$(this).addClass("current_company_btn");
				message_lap.show("#deleteGroupWindow");
			}
		});
		if (!rebind) {
			$("#deleteGroupWindow #cancel_message").click(function () {
				message_lap.close();
			});
			$("#deleteGroupWindow #ok_message").click(function () {
				var companyName = $(".current_company_btn").parents(".company").find(".company_name").text()
				console.log(companyName);
				var parameter = {
					"companyName": encodeURIComponent(companyName)
				}

				$.ajax({
					url: ctx + "/monitorCompany/deleteMonitorCompany",
					type: "POST",
					data: parameter,
					dataType: "json",
					success: function (result) {
						if (result.resultCode == "0") {
							$(".current_company_btn").text("加入监控").removeClass("remove_monitor");

							toastr.success(result.resultMsg);
						} else if (result.resultCode == "2") {
							toastr.warning(result.resultMsg);
						} else {
							toastr.error(result.resultMsg);
						}
					},
					error: function () {
						toastr.error('网络异常！');
					},
					complete: function (XMLHttpRequest, textStatus) {

					}
				});
				message_lap.close();
			})
			$(".new_group").click(function () {
				//清空历史值
				$("#groupName").val("");
				$("#remark").val("");

				lap.show(".new_group_overlap");
			});
			$(".save_new_group").click(function () {
				if (PageConfig.PageModel.OpenNewGroup) {
					var result = addMonitorGroup();

					if (result) {
						lap.show(".select_group_overlap");
					}
				} else {
					lap.close();
				}

				$("#groupName").val("");
				$("#remark").val("");
			});
			$(".select_group_overlap .in_group").click(function () {
				lap.close();
			});
		}


	}

	var event_company_info = function () {
		var open_flag = false;
		var lap = new overlap({
			mask: {
				show: false
			},
			scroller: {
				lock: false
			},
			position: {
				type: "absolute",
				x_pos: 0,
				y_pos: 0
			},
			close: {
				show: true,
				type: "close_2",
				style: {

				},
				callBack: function () {
					open_new_group = false;
				}
			},
			content: {
				style: {
					width: "858px",
					height: "auto",
					border: "16px solid rgba(0,0,0,0.15)"
				}
			}
		});
		$(".ljzx_page").click(function () {
			if (open_flag) {
				lap.close_quick();
			}
		});
	}

	var event_no_company = function () {
		var lap = new overlap({
			mask: {
				show: true
			},
			scroller: {
				lock: true
			},
			position: {
				type: "fixed",
				x_pos: "center",
				y_pos: "center"
			},
			close: {
				show: true,
				type: "close_2",
				style: {

				},
				callBack: function () {

				}
			},
			content: {
				style: {
					width: "603px",
					height: "502px",
					border: "16px solid rgba(0,0,0,0.15)"
				}
			}
		});
		$(".no_search_tips").click(function () {
			lap.show("#company_complete_overlap");
		})
		$("#company_complete_overlap #add_monitor").click(function () {
			$("#company_complete_overlap .complete_input_block").addClass("hidden");
			$("#company_complete_overlap .result_block").removeClass("hidden");
		});
		$("#company_complete_overlap #cancel_monitor").click(function () {
			lap.close();
		});
		$("#company_complete_overlap #ok_btn").click(function () {
			lap.close();
			$("#company_complete_overlap .complete_input_block").removeClass("hidden");
			$("#company_complete_overlap .result_block").addClass("hidden");
		});
	}

	var event_hot_company = function () {

	}

	var Modules = {
		CompanyUsedName: function () {
			return {
				message_company_used_name_message: function () {

				},
				show_used_message_overlap: function (name, message) {

					OverlapList.MessageInfoOverlap.show(PageConfig.MessageConfig.CompanyUsedNameMessage.name, message, "content_info_type_2");
					$("#MessageInfoOverlap .company_message_used_name").text(name);
				}
			}
		}()
	}

	var OverlapList = {
		MessageInfoOverlap: null,
		MessageDeleteOverlap: null,
		GroupListOverlap: null,
	}

	var PageConfig = {
		PageModel: {
			OpenNewGroup:false,
		},
		MessageConfig: {
			CompanyUsedNameMessage: {
				name: "CompanyUsedNameMessage",
				message: "该企业已更名为：<span class='company_message_used_name' style='font-size:14px;color:#333333;font-weight:700;'>曾用名</span>",
				callback: Modules.CompanyUsedName.message_company_used_name_message
			}
		}
	}

	var LoadList = {
		load_overlaps: function () {
			OverlapList.MessageInfoOverlap = new MessageInfoOverlap();
			OverlapList.MessageDeleteOverlap = new overlap({
				mask: {
					show: true
				},
				scroller: {
					lock: false
				},
				position: {
					type: "center"
				},
				close: {
					show: false,
					type: "close_2",
					style: {

					},
					callBack: function () {

					}
				},
				content: {
					style: {
						width: "516px",
						height: "200px",
						border: "16px solid rgba(0,0,0,0.15)"
					}
				}
			});
			OverlapList.GroupListOverlap = new overlap({
				mask: {
					show: false
				},
				scroller: {
					lock: false
				},
				position: {
					type: "absolute",
					x_pos: 0,
					y_pos: 0
				},
				close: {
					show: true,
					type: "close_2",
					style: {

					},
					callBack: function () {
						open_new_group = false;
					}
				},
				content: {
					style: {
						width: "292px",
						height: "263px",
						border: "16px solid rgba(0,0,0,0.15)"
					}
				}
			});
			return this;
		},
		init_message_overlap: function () {
			var message = PageConfig.MessageConfig;
			var info = OverlapList.MessageInfoOverlap;
			info.push(message.CompanyUsedNameMessage.name, message.CompanyUsedNameMessage.message, message.CompanyUsedNameMessage.callback)
			return this;
		}
	}

	var companyListController = function () {
		this.load();
		this.bind();
	}
	companyListController.prototype = {
		load: function () {
			LoadList
				.load_overlaps()
				.init_message_overlap();
		},
		bind: function () {
			event_add_monitor();
			event_company_info();
			event_no_company();
			event_hot_company();
		},
		show_used_message: function (name, message) {
			Modules.CompanyUsedName.show_used_message_overlap(name, message);
		},
		rebind_company_list: function () {
			event_add_monitor(true);
			event_company_info();
			event_no_company();
			event_hot_company();
		}
	};
	companyListController.prototype.constructor = companyListController;
	return companyListController;
}();

//获取动态监控分组
function getMonitorGroupList(companyName) {

	$.ajax({
		url: ctx + "/monitorGroup/queryMonitorGroupListByAccount",
		type: "POST",
		dataType: "json",
		success: function (result) {
			$("#monitorGroupList").html("");//清除之前的数据

			$("#monitorGroupList").append(fomatMonitorGroupList(result));//添加新的数据

			$("#companyName").val(companyName);
		},
		error: function () {
			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {

		}
	});

}

//格式化动态监控分组列表数据
function fomatMonitorGroupList(results) {

	var content = '';

	for (var key in results) {
		content += '<div class="option"><input type="checkbox" id="monitorGroup" name="monitorGroup" value="' + results[key].groupId + '" ';

		if (results[key].groupName == "全部") {
			content += 'checked="checked" disabled="disabled" ';
		}

		content += '/>' + results[key].groupName + '</div>';
	}

	return content;
}

//加入动态监控
function addMonitorCompany() {
	//禁用加入按钮的点击事件，防止重复加入
	$(".current_company_btn").attr("disabled", true);

	document.getElementById("addMonitorCompany").onclick = function () {

	}

	var monitorGroupIds = "";

	$('input[name="monitorGroup"]:checked').each(function () {
		monitorGroupIds += $(this).val() + ",";
	});

	var parameter = {
		"companyName": encodeURIComponent($("#companyName").val()),
		"monitorGroup": monitorGroupIds
	}

	$.ajax({
		url: ctx + "/monitorCompany/addMonitorCompany",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			//启用加入按钮的点击事件
			$(".current_company_btn").attr("disabled", false);

			if (result.resultCode == "0") {
				$(".current_company_btn").text("取消监控");
				$(".current_company_btn").removeClass("current_company_btn").addClass("remove_monitor");
				toastr.success(result.resultMsg);
			} else if (result.resultCode == "2") {
				regMessage = /^该企业已更名为：(.+?)，(.+)$/g
				var r = regMessage.exec(result.resultMsg)

				var companyName = r[1];
				var message = r[2];
				controller.show_used_message(companyName, message);
				if (message == "更名后企业已加入成功！监控详情将于次日展示") {
					$(".current_company_btn").text("取消监控");
					$(".current_company_btn").removeClass("current_company_btn").addClass("remove_monitor");
				}
			} else {
				document.getElementById("addMonitorCompany").onclick = function () {
					addMonitorCompany();
				}
				toastr.error(result.resultMsg);
			}
		},
		error: function () {
			//启用加入按钮的点击事件
			$(".current_company_btn").attr("disabled", false);

			document.getElementById("addMonitorCompany").onclick = function () {
				addMonitorCompany();
			}

			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {

		}
	});

}

//取消监控
function deleteMonitorCompany(companyName) {

	alertify.set({
		labels: {
			ok: "确认",
			cancel: "取消"
		},
		delay: 5000,
		buttonReverse: false,
		buttonFocus: "cancel"
	});

	alertify.confirm("该企业已确认加入监控，如取消监控，将占用当月监控企业数量额度?", function (e) {
		if (e) {
			var parameter = {
				"companyName": encodeURIComponent(companyName)
			}

			$.ajax({
				url: ctx + "/monitorCompany/deleteMonitorCompany",
				type: "POST",
				data: parameter,
				dataType: "json",
				success: function (result) {
					if (result.resultCode == "0") {
						$(".current_company_btn").text("加入监控").removeClass("remove_monitor");

						toastr.success(result.resultMsg);
					} else if (result.resultCode == "2") {
						toastr.warning(result.resultMsg);
					} else {
						toastr.error(result.resultMsg);
					}
				},
				error: function () {
					toastr.error('网络异常！');
				},
				complete: function (XMLHttpRequest, textStatus) {

				}
			});
		} else {

		}
	});
}

//新增动态监控分组
function addMonitorGroup() {
	var groupName = $("#groupName").val().trim();
	var remark = $("#remark").val().trim();

	if (groupName == "") {
		toastr.warning("请输入分组名称！");

		return false;
	} else {
		var parameter = {
			"groupName": groupName,
			"remark": remark
		}

		$.ajax({
			url: ctx + "/monitorGroup/addMonitorGroup",
			type: "POST",
			data: parameter,
			dataType: "json",
			success: function (result) {
				if (result.resultCode == "0") {
					getMonitorGroupList($("#companyName").val());

					toastr.success(result.resultMsg);
				} else if (result.resultCode == "1") {
					toastr.error(result.resultMsg);
				} else {
					toastr.warning(result.resultMsg);
				}
			},
			error: function () {
				toastr.error('网络异常！');
			},
			complete: function (XMLHttpRequest, textStatus) {

			}
		});

		return true;
	}
}

//跳转到企业动态监控详情页面
function toCompanyMonitorDetail(monitorId, companyName, monitorDate, monitorFlag) {
	var deep = $("#deep").val();

	if ("1" == monitorFlag) {
		var today = new Date().pattern("yyyy-MM-dd");

		if (monitorDate == today) {
			toCompanyBrefBizView(companyName, parseInt(deep) + 1);
		} else {
			//			var params = {
			//				"monitorId": monitorId,
			//				"deep": parseInt(deep)+1
			//			};
			//			
			//			httpPost(ctx + "/monitorCompany/viewMonitorCompanyDetail", params);

			var parameter = {
				"keyword": $("#currentKeyword").val(),
				"deep": deep
			}

			// 更新导航菜单
			updateNavMenu(parseInt(deep), "搜索", parameter);

			window.location.href = ctx + "/monitorCompany/viewMonitorCompanyDetail?monitorId=" + monitorId + "&deep=" + (parseInt(deep) + 1);
		}
	} else {
		var parameter = {
			"companyName": encodeURIComponent(companyName)
		}

		$.ajax({
			url: ctx + "/monitorCompany/isMonitorOfCompany",
			type: "POST",
			data: parameter,
			dataType: "json",
			success: function (result) {
				if (result.resultData) {
					toCompanyBrefBizView(companyName, parseInt(deep) + 1);
				} else {
					toastr.warning('加入监控后方可查看工商基本信息！');
				}
			},
			error: function () {
				toastr.error('网络异常！');
			},
			complete: function (XMLHttpRequest, textStatus) {

			}
		});
	}
}
