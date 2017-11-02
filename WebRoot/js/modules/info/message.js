
var userMessageController = function () {
	var event_mode = function () {
		var show_list = function () {
			$(".message_list_block").removeClass("hidden");
			$(".message_read_block").addClass("hidden");
			mode = "list"
		}
		$(".h_mode").click(function () {
			if ($(this).hasClass("selected")) {
				return;
			}
			$(".message_head .selected").removeClass("selected");
			$(this).addClass("selected");
			if (mode == "read") {
				show_list();
			}
		})
		$(".message_read_block .back_list_btn").click(function () {
			queryMessageList($("#sender").val());

			show_list();
		})
	}
	var userMessageController = function () {
		this.load_page();
		this.event_bind();
	}
	userMessageController.prototype = {
		load_page: function () {

		},
		event_bind: function () {
			event_mode();
		}
	};
	userMessageController.prototype.constructor = userMessageController;
	return userMessageController;
}();

var page_resizeContent = function () {
	if ('object' == typeof controller) {
		controller.load_page();
	}
}

//获取消息列表
function queryMessageList(sender) {

	$("#checkAll").removeAttr("checked");

	$("#sender").val(sender);

	var parameter = {
		"sender": sender,
		"currentPageNo": parseInt(1),
		"pageSize": parseInt(10)
	}

	$.ajax({
		url: ctx + "/message/queryMessageList",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			$("#messageList").html("");//清除之前的数据

			//$("#messageList").append(fomatMessageList(result.results));//添加新的数据
			fomatMessageList(result.results);
			$("#Pagination").pagination(result.recordSum, {
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

		}
	});

}


//分页回调
function pageSelectCallback(new_page_index, pagination_container) {
	$("#checkAll").removeAttr("checked");

	var currentPageNo = parseInt(new_page_index) + 1;

	var parameter = {
		"sender": $("#sender").val(),
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10)
	}

	$.ajax({
		url: ctx + "/message/queryMessageList",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			$("#messageList").html("");//清除之前的数据

			//$("#messageList").append(fomatMessageList(result.results));//添加新的数据
			fomatMessageList(result.results);
		},
		error: function () {
			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {

		}
	});
}

//格式化结果数据
function fomatMessageList(results) {

	var message_block = $(tostring(function () {/*
		<div class="message">
			<div class="message_preview" onclick="">
				<div>
					<div class="relative_right_block">
						<div class="message_right_arr up_arr"></div>
					</div>
					<input type="checkbox" id="message" name="message" value="">
					<span class="new_mewssage message_title  "></span>
				</div>
				<div class="message_type">
					<div class="t_time_block">
						<div class="t_time"></div>
					</div>
					<div class="t_value"></div>
				</div>
			</div>
			<div class="message_detail" style="height:0px;"></div>
		</div>
	*/}));

	for (var key in results) {
		var message = $(message_block).clone();
		$(message).find(".message_title").text(results[key].infoMessage.title);
		$(message).find(".t_value").text(results[key].infoMessage.sender == '0' ? '系统消息' : '动态监控');
		$(message).find(".t_time").text(new Date(results[key].infoMessage.sendTime).pattern("yyyy-MM-dd HH:mm:ss"));

		$(message).find("#message").attr("value", results[key].id);
		//未读标示
		if (results[key].readStatus == '0') {
			$(message).find(".message_title").addClass("unread_message");
		} else {
			$(message).find(".message_title").addClass("read_message");
		}
		$(message).attr("keyId", results[key].id);
		$(message).attr("keyMesssageId", results[key].infoMessage.messageId);
		$(message).attr("keyReadStatus", results[key].readStatus);
		$(message).click(viewMessageDetail);
		$("#messageList").append(message);
	}
}

//查看消息详情
function viewMessageDetail() {
	var _this = this;
	$(_this).find(".message_title").removeClass("unread_message").addClass("read_message");
	var messageAccountId = $(_this).attr("keyId");
	var messageId = $(_this).attr("keyMesssageId");
	var readStatus = $(_this).attr("keyReadStatus");
	var message_detail = $(_this).find(".message_detail");
	if ($(message_detail).height() > 0) {
		$(message_detail).animate({ height: 0 }, 300);
		$(_this).find(".message_right_arr").removeClass("down_arr").addClass("up_arr");
		return;
	}else{
		$(_this).find(".message_right_arr").removeClass("up_arr").addClass("down_arr");
	}
	
	var parameter = {
		"messageAccountId": messageAccountId,
		"messageId": messageId,
		"readStatus": readStatus
	}

	$.ajax({
		url: ctx + "/message/getMessage",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			var message_detail_block = $(tostring(function () {/*
				<div class="message_detail_block" style="padding:1px 0px 0px 0px;border-top:1px dashed #d7d7d7;min-height:127px;">
					<div style="margin:8px 0px;">
						<div class="inline_div" style="width:65px;text-align:right;">内容：</div>
						<div class="inline_div" style="color:#666666;width:1000px;">主题：<span class="message_detail_title"></span></div>
					</div>
					<div style="margin:8px 0px;">
						<div class="inline_div" style="width:65px;text-align:right;"></div>
						<div class="inline_div"  style="color:#666666;width:1000px;">时间：<span class="message_detail_time"></span></div>
					</div>
					<div style="margin:8px 0px 7px 0px;">
						<div class="inline_div" style="width:65px;text-align:right;"></div>
						<div class="inline_div"  style="color:#666666;width:1000px;">内容：<span class="message_detail_content"></span></div>
					</div>
				</div>
			*/}));
			$(message_detail_block).find(".message_detail_title").text(result.title);
			$(message_detail_block).find(".message_detail_time").text(new Date(result.sendTime).pattern("yyyy-MM-dd HH:mm:ss"));
			$(message_detail_block).find(".message_detail_content").html(result.content);
			$(message_detail).empty();

			$(message_detail).append(message_detail_block);
			$(message_detail).animate({ height: $(message_detail_block).outerHeight(true) }, 300);
		},
		error: function () {
			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {

		}
	});
}

//全部已读
function readAllMessage() {
	var sender = "";

	var parameter = {
		"sender": sender
	}

	$.ajax({
		url: ctx + "/message/readAllMessage",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			if (result.resultCode == 0) {
				queryMessageList(sender);

				$(".message_count .inline_div").addClass("hidden");

				toastr.success("已全部忽略！");
			}
		},
		error: function () {
			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {

		}
	});
}

//删除消息
function deleteMessages() {

	var sender = $("#sender").val();

	var messageIds = "";

	$('input[name="message"]:checked').each(function () {
		messageIds += $(this).val() + ",";
	});

	if (messageIds.length > 0) {
		var parameter = {
			"messageIds": messageIds
		}

		$.ajax({
			url: ctx + "/message/deleteMessages",
			type: "POST",
			data: parameter,
			dataType: "json",
			success: function (result) {
				if (result.resultCode == 0) {
					queryMessageList(sender);

					toastr.success("删除成功！");
				} else {
					toastr.error('删除失败！');
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

