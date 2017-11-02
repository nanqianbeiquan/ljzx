
var controller;
// 风险状况
var riskLevel = "";

// 事件等级
var eventLevel = "";
// 组名
var groupName = "";
// 风险变化
var riskStatus = "";
//风险自定义
var customRiskFlag = "";
//今日监控标志
var todayMonitorFlag = "";
// 区域
var area = "";
// 标签
var label = "";
// 删除的分组key
var key = "";
// 分组状况
var groupStatus = "";
//更新状态 有无新事件
var updateStatus = "";
// 开始日
var beginDate = "";
// 终了日
var dueDate = "";

// 移动步数
var steps = 0;
// 移动步数
var move_index = 1;

// 开关显示checkBox[编辑按钮]
var flag = false;
// 筛选按钮
var extendFlag = false;

var pageNo = 1;
var pageSize = 10;
var tostring = function (fn) {
	return fn.toString().split('\n').slice(1, -1).join('\n') + '\n'
};
var groupList;

var companyListString = "";

var groupListString;

var companyName = "";

var sortParam={};//排序参数

var relationship;
var showGroupType="GROUP";
var currentAccountId ;
$(function () {
	controller = new monitorEventController();

	dynamicBind();

	toastr.options = {
		"closeButton": false, // 是否显示关闭按钮
		"debug": false, // 是否使用debug模式
		"positionClass": "toast-top-center",// 弹出窗的位置
		"showDuration": "300",// 显示的动画时间
		"hideDuration": "1000",// 消失的动画时间
		"timeOut": "1000", // 展现时间
		"extendedTimeOut": "1000",// 加长展示时间
		"showEasing": "swing",// 显示时的动画缓冲方式
		"hideEasing": "linear",// 消失时的动画缓冲方式
		"showMethod": "fadeIn",// 显示时的动画方式
		"hideMethod": "fadeOut" // 消失时的动画方式
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
		hwaccel: false, // spinner 是否启用硬件加速及高速旋转
		className: 'spinner', // spinner css 样式名称
		zIndex: 2e9, // spinner的z轴 (默认是2000000000)
		top: '50%', // spinner 相对父容器Top定位 单位 px
		left: '50%'// spinner 相对父容器Left定位 单位 px
	};

	window.spinner = new Spinner(opts);

	var deep = $("#deep").val();

	// 更新导航菜单
	updateNavMenu(parseInt(deep), "监控企业列表", window.location.search);

	// step1 init初始化信息
	initComInfo();
	relationship = new CompanyRelationShip({
		RelationShipMode: 0b00
	});
})

var monitorEventController = function () {

	var update_lap;

	var address = $(function () {
		$('#pprovince, #pcity').citylist({
			data: data,
			id: 'id',
			children: 'cities',
			name: 'name',
			metaTag: 'name'
		});
	});



	var tab_move = function (tab) {
		// $(tab).css("left",(1*$(tab).index()*20)+"px");
	}
	var load_tab = function () {
		$(".tab").each(function () {
			tab_move(this);
		});
	}
	var event_filter = function () {

	}
	var event_group = function () {
		var open_new_group = false;
		var lap = new overlap({
			mask: {
				show: false
			},
			scroller: {
				lock: false
			},
			position: {
				type: "absolute",
				x_pos: $(".new_group").offset().left,
				y_pos: $(".new_group").offset().top + 35
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
		update_lap = new overlap({
			mask: {
				show: false
			},
			scroller: {
				lock: false
			},
			position: {
				type: "absolute"
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
		// 开启窗口
		$(".updateGroupName").click(function () {
			update_lap.reset({
				position: {
					type: "absolute",
					x_pos: $(".tab_group_list .selected").offset().left,
					y_pos: $(".tab_group_list .selected").offset().top + 35
				},
			});

			$("#newDescription").val("");
			$("#newName").val("");
			if ($("#groupName").text() == "") {
				toastr.warning("请选择分组!");
				return;
			}
			if ($("#groupName").text() == "全部") {
				toastr.warning("不能修改全部分组!");
				return;
			}

			for (var i = 0; i < groupList.length; i++) {

				if (groupList[i].groupName == groupName) {
					$("#newDescription").val(groupList[i].remark);
					$("#newName").val(groupName);
					break;
				}

			}

			update_lap.show(".update_group_overlap");
		})
		// 关闭窗口
		$(".update_group").click(function () {
			// 关闭窗口
			// 收集变量
			var groupName = $("#newName").val();
			var description = $("#newDescription").val()

			// 更新组名
			updateGroupName(groupName, description);
			update_lap.close();
		})

		var drop_lap = new overlap({
			mask: {
				show: false
			},
			scroller: {
				lock: false
			},
			position: {
				type: "absolute",
				x_pos: $(".new_group").offset().left,
				y_pos: $(".new_group").offset().top + 35
			},
			close: {
				show: false,
				type: "close_2",
				style: {

				},
				callBack: function () {
					open_new_group = false;
				}
			},
			content: {
				style: {
					width: "390px",
					height: "340px",
					border: "1px solid #d7d7d7"
				}
			}
		});

		$("#area_drop .drop_input").click(function () {
			drop_lap.reset({
				position: {
					x_pos: $("#area_drop").offset().left,
					y_pos: $("#area_drop").offset().top + 29
				}
			});
			drop_lap.show(".area_overlap");
		})
		$("#area_drop .img_click").click(function () {
			drop_lap.reset({
				position: {
					x_pos: $("#area_drop").offset().left,
					y_pos: $("#area_drop").offset().top + 29
				}
			});
			drop_lap.show(".area_overlap");
		})
		$("#span_drop .drop_input").click(function () {
			drop_lap.reset({
				position: {
					x_pos: $("#span_drop").offset().left + 1,
					y_pos: $("#span_drop").offset().top + 29
				}
			});
			drop_lap.show(".span_overlap");
		})
		$("#span_drop .img_click").click(function () {
			drop_lap.reset({
				position: {
					x_pos: $("#span_drop").offset().left + 1,
					y_pos: $("#span_drop").offset().top + 29
				}
			});
			drop_lap.show(".span_overlap");
		})
		$(".area_overlap #area_select_btn").click(function () {
			areaSubmit();
			drop_lap.close();
		})
		$(".span_overlap #span_select_btn").click(function () {
			labelSubmit();
			drop_lap.close();
		})
		$(".new_group").click(function () {
			lap.reset({
				position: {
					x_pos: $(".new_group").offset().left - 85,
					y_pos: $(".new_group").offset().top + 35
				}
			})
			// 清空
			$("#name").val("");
			$("#description").val("");
			lap.show(".new_group_overlap");
		});
		$(".batch_modify").click(function () {
			// 事先收集先checkBox信息进行一次预判是否打开批量修改界面
			var monitorIds = getCheckedMonitorIds();

			if (monitorIds.trim() == "") {
				toastr.warning("批量修改分组前请选择相应公司!");
				return;
			}

			open_new_group = true;
			lap.reset({
				position: {
					x_pos: $(".new_group").offset().left - 85,
					y_pos: $(".new_group").offset().top + 35
				}
			})
			$("input[name='groupIdList']").prop("checked", false);
			lap.show(".select_group_overlap");
		});
		$(".save_new_group").click(function () {
			//
			if (open_new_group) {
				lap.show(".select_group_overlap");
			} else {
				lap.close();
			}
			addMonitorGroup();
		});
		$("#addBtn").click(function () {

			updateMonitorCompanyGroupBatch();

			lap.close();
		});
		var show_filter = function () {
			lap.close();

			$(".monitor_event_filter").animate({
				height: $(".filter_block").outerHeight(true)
			});
			$(".filter_open_btn").addClass("hidden");
			$(".filter_close_btn").removeClass("hidden");
		}
		var close_filter = function () {
			lap.close();

			$(".monitor_event_filter").animate({
				height: 0
			});
			$(".filter_open_btn").removeClass("hidden");
			$(".filter_close_btn").addClass("hidden");
		}

		var show_manageMenu = function () {
			// 批量修改分组 批量删除 完成 展示 隐藏批量管理
			lap.close();
			// 隐藏批量管理
			$(".filter_open_btn2").addClass("hidden");
			// 展示完成
			$(".filter_close_btn2").removeClass("hidden");
			var value = $(".tab_list .selected .value").text();
			if (value == "全部") {
				$(".table_options_list .cancel_monitor").removeClass("hidden");
				$(".table_options_list .batch_delete_company").addClass(
					"hidden");
			} else {
				$(".table_options_list .cancel_monitor").addClass("hidden");
				$(".table_options_list .batch_delete_company").removeClass(
					"hidden");
			}
			$(".table_options_list").removeClass("hidden");
			// 展示checkBox
			flag = true;
			$(".oneChoose").show();
			$(".allChoose").show();

		}
		var close_manageMenu = function () {
			lap.close();
			$(".filter_open_btn2").removeClass("hidden");
			$(".filter_close_btn2").addClass("hidden");
			$(".table_options_list").addClass("hidden");
			// 隐藏checkBox
			flag = false;
			$(".oneChoose").hide();
			$(".allChoose").hide();
			// $(".importBtn").hide();
		}

		$(".filter_open_btn").click(function () {
			extendFlag = true;
			drop_lap.close();
			update_lap.close();
			show_filter();
		});
		$(".filter_close_btn").click(function () {
			extendFlag = false;
			drop_lap.close();
			update_lap.close();
			close_filter();
		});
		$(".filter_open_btn2").click(show_manageMenu);
		$(".filter_close_btn2").click(close_manageMenu);
	}
	var event_options = function () {

	}
	var create_message_lap = function () {
		var message_lap = new overlap({
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
					open_new_group = false;
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
		return message_lap;
	}
	// 取消动态监控提示窗口
	var create_message_lap2 = function () {
		var message_lap2 = new overlap({
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
					open_new_group = false;
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
		return message_lap2;
	}

	// 取消动态监控提示窗口
	var create_message_lap3 = function () {
		var message_lap3 = new overlap({
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
					open_new_group = false;
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
		return message_lap3;
	}


	// 企业列表添加关联关系提示；
	var create_message_lap4 = function () {
		var message_lap4 = new overlap({
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
					open_new_group = false;
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
		return message_lap4;
	}


	// 企业列表添加关联关系提示；
	var create_message_lap5 = function () {
		var message_lap5 = new overlap({
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
					open_new_group = false;
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
		return message_lap5;
	}


	var event_move_tab = function () {

		$(".move_tab_list .left_move").click(function () {
			var tab_count = $(".tab_group_list .tab").length;
			if (move_index > 1) {
				$(".tab_group_list .tab").animate({
					left: "+=" + 115
				})
				move_index--;
			}
			console.log(move_index);
		});
		$(".move_tab_list .right_move").click(function () {
			var tab_count = $(".tab_group_list .tab").length - 6;
			if (move_index < tab_count) {
				$(".tab_group_list .tab").animate({
					left: "-=" + 115
				})
				move_index++;
			}
			console.log(tab_count);
		})
	}
	/**
	 * 绑定打开窗口
	 */
	var event_del_group = function (_this, message_lap) {
		$(_this).find(".del_group_btn").click(function () {
			key = "";
			key = $(this).parent().attr("key");
			message_lap.show("#deleteGroupWindow");
			return false;
		});

	}
	/**
	 * 绑定关闭按钮
	 */
	var event_del_group_close = function (message_lap) {
		$("#cancel_message").click(function () {
			message_lap.close();
		});

		$("#del_message").click(function () {
			message_lap.close();
			deleteGroup(key);
		})
	}
	/**
	 * 取消动态监控绑定
	 */
	var cancel_monitor = function (message_lap2) {

		// 展示绑定
		$("#cancelMonitor").click(function () {
			var monitorIds = getCheckedMonitorIds();

			if (monitorIds.trim() == "") {
				toastr.warning("请选择公司!");
				return;
			}

			message_lap2.show("#cancelMonitorWindow");
		});

		// 取消绑定
		$("#cancel_message2").click(function () {
			message_lap2.close();
		});

		// 提交绑定
		$("#commit_cancel").click(function () {
			message_lap2.close();
			deleteMonitorCompanyBatch();
		})
	}

	/**
	 * 批量删除公司绑定
	 */
	var delete_group_company = function (message_lap3) {

		// 展示绑定
		$("#batchDelete").click(function () {
			var monitorIds = getCheckedMonitorIds();
			if (monitorIds.trim() == "") {
				toastr.warning("请选择公司!");
				return;
			}

			message_lap3.show("#deleteGroupComWindow");
		});

		// 取消绑定
		$("#cancel_message3").click(function () {
			message_lap3.close();
		});
		// 提交绑定
		$("#commit_delete").click(function () {
			message_lap3.close();
			batchDelete();
		})
	}
	var init_lap = function (list) {
		list.RelateOverlap = new overlap({
			scroller: {
				lock: true,
				body_hidden: true
			},
			close: {
				callBack: function () {
					console.log("search company dialog closed");
					$('.company_list').html("");
					$('.search_result').html("");
					$("#Pagination4Search").html("");
					$("#searchInfo").val("");
					$("#personName").val("");
					// $("#province").val("请先择省");
					// $("#city").val("请选择市");
					$("#idNumber").val("");
					$("#company_mode").click(); // 重新点击“新增关联企业TAB”
				}
			},
			content: {
				style: {
					width: "600px",
					height: "600px",
					border: "15px solid rgba(0,0,0,0.15)"
				}
			}
		});
	}
	// 新增关联企业，个人所用绑定事件；
	var event_relate_block = function () {

		// 新增切换
		$("#relate_add_block .mode_selector .add_mode").click(
			function () {

				console.log("pick up company or person_mode...");
				var _this = this;
				if ($(this).hasClass("selected")) {
					return;
				}
				$("#relate_add_block .mode_selector .add_mode")
					.removeClass("selected");
				var move_lenth = $(this).position().left + 93;
				$("#relate_add_block .mode_selector .selected")
					.removeClass("selected");
				$(_this).addClass("selected");
				// 事件绑定
				$("#relate_add_block .mode_selector .mode_arr").animate({
					left: move_lenth
				}, 300, function () {
					$(".mode_block").addClass("hidden");
					$("." + $(_this).attr("id")).removeClass("hidden");
					console.log("id==" + $(_this).attr("id"));
					var tabId = $(_this).attr("id");
					if (tabId == 'person_mode') {
						console.log("pick up person_mode");
						navigatePersonMode1("企业法人代表");
					} else if (tabId == 'company_mode') {
						console.log("pick up company_mode");
						navigateCompanyMode();
					}

				})
			})

		/*
		 * $("#relate_add_block .company_mode .filter_title_block
		 * .title").click(function(){
		 * 
		 * console.log("pick up company_mode");
		 * 
		 * var _this=this; if($(this).hasClass("selected")){ return; }
		 * $("#relate_add_block .company_mode .filter_title_block
		 * .selected").removeClass("selected"); $(_this).addClass("selected");
		 * var move_lenth=$(this).position().left-37; $("#relate_add_block
		 * .company_mode .filter_title_block
		 * .filter_arr").animate({left:move_lenth},300,function(){ }) })
		 */

		/*
		 * $("#relate_add_block .person_mode .filter_title_block
		 * .title").click(function(){ debugger; var _this=this;
		 * if($(this).hasClass("selected")){ return; }
		 * 
		 * $("#relate_add_block .person_mode .filter_title_block
		 * .selected").removeClass("selected"); $(_this).addClass("selected");
		 * var move_lenth=$(this).position().left-37; $("#relate_add_block
		 * .person_mode .filter_title_block
		 * .filter_arr").animate({left:move_lenth},300,function(){
		 * 
		 * console.log("switch tabs('股东','投资','高管')"); }) })
		 * 
		 * $("#relate_add_block .person_mode .filter_option_block
		 * .option").click(function(){ var _this=this;
		 * if($(this).hasClass("selected")){ return; } $("#relate_add_block
		 * .person_mode .filter_option_block
		 * .selected").removeClass("selected"); $(_this).addClass("selected"); })
		 * 
		 * $("#option_up_btn").click(function(){ $("#relate_add_block
		 * .person_mode .filter_option_block
		 * .option_list").stop().animate({scrollTop:"+="+35},500) })
		 * $("#option_down_btn").click(function(){ $("#relate_add_block
		 * .person_mode .filter_option_block
		 * .option_list").stop().animate({scrollTop:"-="+35},500) })
		 */
	}

	var Binders = {
		bind_overlap_event_content_scroll: function () {
			function scroll_move(e) {
				e = e || window.event;
				var delta = e.wheelDelta || e.detail || e.originalEvent.detail || e.originalEvent.deltaY || -1 * e.originalEvent.wheelDelta;
				delta = -1 * (delta / Math.abs(delta)) * 100;
				var top = $(this).scrollTop() - delta;
				$(this).scrollTop(top);
				if ($(this).scrollTop() <= 0) {
					top = $(this).find(".content").scrollTop() - delta;
					$(this).find(".content").scrollTop(top);
				}
				//取消事件冒泡
				e = arguments.callee.caller.arguments[0] || e; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
				if (e && e.stopPropagation) {
					// this code is for Mozilla and Opera
					e.stopPropagation();
				} else if (window.event) {
					// this code is for IE
					window.event.cancelBubble = true;
				}
				return false;
			}
			$("#searchResultDiv").bind('mousewheel', scroll_move);
			$("#searchResultDiv").bind('DOMMouseScroll', scroll_move);
			$("#stockHolderCompany .company_list").bind('mousewheel', scroll_move);
			$("#stockHolderCompany .company_list").bind('DOMMouseScroll', scroll_move);
			return this;
		}
	}

	var monitorEventController = function () {
		this.lap_list = {};
		this.load_page();
		this.event_bind();
		this.message_lap = create_message_lap();
		this.message_lap2 = create_message_lap2();
		this.message_lap3 = create_message_lap3();
		this.message_lap4 = create_message_lap4();
		this.message_lap5 = create_message_lap5();
		this.bind_del_group_close();
		this.bind_cancel_monitor();
		this.bind_batch_delete();

	}

	monitorEventController.prototype = {
		load_page: function () {
			load_tab();
			init_lap(this.lap_list);
		},
		event_bind: function () {
			event_filter();
			event_group();
			event_options();
			event_move_tab();
			event_relate_block();
		},
		bind_del_group: function (_this) {
			event_del_group(_this, this.message_lap);
		},
		bind_del_group_close: function () {
			event_del_group_close(this.message_lap);
		},
		bind_cancel_monitor: function () {
			cancel_monitor(this.message_lap2);
		},
		bind_batch_delete: function () {
			delete_group_company(this.message_lap3);
		},
		close_update_overlap: function () {
			update_lap.close();
		},
		rebind_overlap_event_content_scroll: function () {
			Binders.bind_overlap_event_content_scroll();
		}
	};
	monitorEventController.prototype.constructor = monitorEventController;
	return monitorEventController;
}();

var page_resizeContent = function () {
	if ('object' == typeof controller) {
		controller.load_page();
	}
}

function initComInfo() {
	// init初始化信息
	initParams();
	// initAreaList();
	// initLabelList();
	tabShow();
	getMonitorCompanyList(pageNo, pageSize);
}

function dynamicBind() {
	// 事件绑定
	// eventsQueryOtionDeal();
	// batchAdd();
	eventsQueryOtionDeal();
	// 检索按钮绑定
	searchByCompanyName();
	areaChoose();
	labelChoose();
	allLabelChoose();
	allAreaChoose();
	//bindEnterSearch();
	//bindEnterSearchReal();
	clear();
}

function getMonitorCompanyList(pageNoParam, pageSizeParam) {

	console.log("getMonitorCompanyList,preParam=[sortAttr="+sortParam.sortAttr+",isDesc="+sortParam.isDesc+"]");
	
	if (!pageNoParam || pageNoParam == "") {
		pageNo = 1;
	} else {
		pageNo = pageNoParam;
	}

	if (!pageSizeParam || pageSizeParam == "") {
		pageSize = 10;
	} else {
		pageSize = pageSizeParam;
	}
	if(!sortParam.hasOwnProperty("sortAttr")){
		sortParam.sortAttr = "createTime";
		sortParam.isDesc = true;
	}
    
	companyName = $("#searchInfo").val();
	console.log("getMonitorCompanyList,postParam=[sortAttr="+sortParam.sortAttr+",isDesc="+sortParam.isDesc+"]");
	// 将个人加入对应分组
	var parameter = {
	    "currentAccountId": $("#currentAccountId").val(),
		"showGroupType":showGroupType,	
		"groupName": encodeURIComponent(groupName),
		"riskLevel": riskLevel,
		"riskStatus": riskStatus,
		"eventLevel": eventLevel,
		"beginDate": beginDate,
		"dueDate": dueDate,
		"area": encodeURIComponent(area),
		"label": encodeURIComponent(label),
		"updateStatus": updateStatus,
		"customRiskFlag": customRiskFlag,
		"todayMonitorFlag": todayMonitorFlag,
		"currentPageNo": pageNo,
		"pageSize": pageSize,
		"companyName": encodeURIComponent(companyName),
		"key": key,
		"steps": steps,
		"flag": flag,
		"groupStatus": groupStatus,
		"sortAttr": sortParam.sortAttr,
		"isDesc":sortParam.isDesc
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorCompany/getMonitorCompanyPagination",
		data: parameter,
		beforeSend: function () {
			// 异步请求时spinner出现
			var target = $(".tab_list").get(0);
			spinner.spin(target);
		},
		success: function (result) {
			spinner.spin();
			$("#Searchresult").html(result);// 清除之前的数据
			if (flag == true) {
				$(".oneChoose").show();
				$(".allChoose").show();
				$(".actionRow").show();
				$(".actionName").show();
			} else {
				$(".oneChoose").hide();
				$(".allChoose").hide();
				$(".actionRow").hide();
				$(".actionName").hide();
			}

			$("#Pagination").html("");
			if ($("#totalNum").val() > 0) {
				$("#Pagination").pagination($("#totalNum").val(), {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					items_per_page: pageSize,
					current_page: parseInt(pageNo) - 1,
					num_display_entries: 10,

					// 回调
					callback: pageselectCallback
				});
			}
		}
	});
}

// 分页回调
function pageselectCallback(new_page_index, pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;

	pageNo = currentPageNo;
	if(!sortParam.hasOwnProperty("sortAttr")){
		sortParam.sortAttr = "createTime";
		sortParam.isDesc = true;
	}
	var parameter = {
		"currentAccountId": $("#currentAccountId").val(),
		"showGroupType":showGroupType,
		"groupName": encodeURIComponent(groupName),
		"riskLevel": riskLevel,
		"riskStatus": riskStatus,
		"eventLevel": eventLevel,
		"companyName": encodeURIComponent(companyName),
		"beginDate": beginDate,
		"dueDate": dueDate,
		"area": encodeURIComponent(area),
		"label": encodeURIComponent(label),
		"groupStatus": groupStatus,
		"updateStatus": updateStatus,
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10),
		"updateStatus": updateStatus,
		"customRiskFlag": customRiskFlag,
		"todayMonitorFlag": todayMonitorFlag,
		"key": key,
		"steps": steps,
		"flag": flag,
		"sortAttr": sortParam.sortAttr,
		"isDesc":sortParam.isDesc
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorCompany/getMonitorCompanyPagination",
		data: parameter,
		success: function (result) {

			$("#Searchresult").html(result);// 清除之前的数据
			if (flag == true) {
				$(".oneChoose").show();
				$(".allChoose").show();
				$(".actionRow").show();
				$(".actionName").show();
			} else {
				$(".oneChoose").hide();
				$(".allChoose").hide();
				$(".actionRow").hide();
				$(".actionName").hide();
			}
		}
	});
}

/**
 * 获取监控分组
 */
function tabShow() {
	//var showGroupType="ACCOUNT";
	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorEvents/getMonitorGroups.do",
		data: {"showGroupType":showGroupType,"currentAccountId":$("#currentAccountId").val()},
		success: function (result) {
			var list = eval("(" + result + ")");// json字符串转换为json对象
			groupList = list;

			// 模板
			var tab_block = $(tostring(function () {/*
			             <div class="tab inline_div">
		                    <div class="inline_div tab_value"></div>
		                    <div class="left_tab_block hidden">
		                        <div class="left_tab"></div>
		                        <div class="left_tab_border"></div>
		                    </div>
		                </div>
				        */}));
			// 清空除了新建分组之外的所有分组
			var arr = $(".tab_list").find(".tab");
			for (var i = 0; i < arr.length; i++) {

				if ($(arr[i]).attr("class") != "tab inline_div new_group") {
					arr[i].remove();
				}
			}
			// 分组checkBox展示
			var content2 = '';
			for (var key in list) {
				// 遍历取出对象
				// 克隆
				var tap = $(tab_block).clone();
				var tab_value;
				if (list[key].groupName == "全部") {
					tab_value = $('<div class="inline_div no_group_btn"></div><div class="inline_div value line_txt" title="'+list[key].groupName+'">'
						+ list[key].groupName + '</div>');
				} else {
					// 除了全部分组之外加上删除的功能
					var showActionBtnFlag = $("#showActionBtnFlag").val();
					if(showActionBtnFlag!="false"){
						tab_value = $('<div class="inline_div del_group_btn"></div><div class="inline_div value line_txt" title="'+list[key].groupName+'">'
							+ list[key].groupName + '</div>');
					}else{
						tab_value = $('<div class="inline_div no_group_btn"></div><div class="inline_div value line_txt" title="'+list[key].groupName+'">'
								+ list[key].groupName + '</div>');
					}
				}
				// 追加key属性
				$(tap).find(".tab_value").append(tab_value).attr("key",
					key);
				// 追加点击事件分组移动
				$(tap).click(function () {
					controller.close_update_overlap();
					var key = $(this).find(".tab_value").attr("key");
					groupChange(key);
					$(".tab_list .selected").removeClass("selected");
					$(this).addClass("selected");
					if ($(".filter_open_btn2").hasClass("hidden")) {
						$(".filter_close_btn2").click();
					}
				});
				// 全部放在最前面
				if (list[key].groupName == "全部") {
					$(".tab_group_list").before(tap);
					;
				} else {
					// 绑定删除事件
					var showActionBtnFlag = $("#showActionBtnFlag").val();
					if(showActionBtnFlag!="false"){
					   controller.bind_del_group(tap);
					}
					// 其余的就放在后面
					$(".tab_group_list").append(tap);
				}
				// 判断是否显示左右移动图标
				if (list.length > 7) {
					$(".move_tab_list").removeClass("hidden");
				} else {
					$(".move_tab_list").addClass("hidden");
				}

				// checkBox不展示全部分组
				if (list[key].groupName != "全部") {
					content2 += "<div class='option'>";
					content2 += "<input type='checkbox' name='groupIdList' value='"
						+ list[key].groupId
						+ "'/>"
						+ "<span class='grouName4Option'>"
						+ list[key].groupName + "<span/>";
					content2 += "</div>";

				}
			}
			// 清空分组checkBox
			$(".option_check_value").html("");
			$(".option_check_value").append(content2);

			if (list.length > 7) {
				// 如果说分组的个数大于7那么将会进行移动
				$(".tab_group_list .tab").animate({
					left: "-=" + 115 * steps
				});
			}
			// 更新当前步数
			move_index = steps + 1;

			// 初始化加载的时候默认选择全部分组
			var arr = $(".tab_list").find(".tab");
			for (var i = 0; i < arr.length; i++) {
				if ($(arr[i]).attr("class") != "tab inline_div new_group") {
					var key = $(this).find(".tab_value").attr("key");
					var name = groupList[i].groupName;
					if (groupName == name) {
						$(arr[i]).addClass("selected");
						break;
					}
				}
			}
			var showActionBtnFlag = $("#showActionBtnFlag").val();
			console.log("showActionBtnFlag="+showActionBtnFlag);
			if(showActionBtnFlag=="false"){
				console.log("change hidde new add btn");
				$(".new_group").addClass("hidden");
			}
		}
	});

}

/**
 * 开始日期选择触发事件:周期方式设置为:3 保存对应日期 发送查询请求
 */
function beginDateDeal(date) {

	beginDate = date;
	getMonitorCompanyList();
}

/**
 * 结束日期选择触发事件:周期方式设置为:3 保存对应日期 发送查询请求
 */
function dueDateDeal(date) {
	dueDate = date;
	getMonitorCompanyList();
}

/**
 * 区域选择改变触发新的查询
 */
// function areaChange(){
// //area=$("#areaList").val();
// area=$("#areaList").find("option:selected").text();
// getMonitorComRiskList();
// }
/**
 * 区域选择改变触发新的查询
 */
// function labelChange(){
// label=$("#labelList").find("option:selected").text();
// getMonitorComRiskList();
// }
function initAreaList() {
	// 初始化区域下拉列表
	var content = '';
	for (var key in areaList) {
		// 遍历取出对象
		content += "<option value=" + key + ">" + areaList[key].name
			+ "</option>";
	}
	$("#areaList").html(content);
}

function initLabelList() {
	// 初始化区域下拉列表
	var content = '';
	for (var key in labelList) {
		// 遍历取出对象
		content += "<option value=" + key + ">" + labelList[key].name
			+ "</option>";
	}
	$("#labelList").html(content);
}

/**
 * 监控公司事件查询 选项按钮触发事件
 */
function eventsQueryOtionDeal() {

	$(".monitor_event_filter .option").click(
		function () {
			// 清空背景色
			$(this).parents(".filter_options").children().children().attr(
				"class", "option_text option_name inline_div");
			// 重新加背景色
			$(this).parent().attr("class",
				"option_text option_name inline_div selected");
			// 赋值处理
			var obj = $(this).parents(".filter_data").children(
				".filter_name");
			if (obj.text() == "风险状况") {
				// 事件等级
				var demo = $(this).text();
				switch (demo) {

					case ("全部"):
						riskLevel = "";
						break;
					case ("特别预警"):
						riskLevel = "3";
						break;
					case ("一般预警"):
						riskLevel = "2";
						break;
					case ("关注"):
						riskLevel = "1";
						break;
					case ("正常"):
						riskLevel = "0";
						break;
				}

			} else if (obj.text() == "风险变化") {

				var demo = $(this).text();
				switch (demo) {
					// 事件性质
					case ("全部"):
						riskStatus = "";
						break;
					case ("不变"):
						riskStatus = "0";
						break;
					case ("升高"):
						riskStatus = "1";
						break;
					case ("降低"):
						riskStatus = "2";
						break;
				}
			} else if (obj.text() == "风险事件") {
				// 日期筛选
				var demo = $(this).text();
				switch (demo) {
					case ("全部"):
						eventLevel = "";
						break;
					case ("严重"):
						eventLevel = "3";
						break;
					case ("异常"):
						eventLevel = "2";
						break;
					case ("一般"):
						eventLevel = "1";
						break;
				}

			} else if (obj.text() == "分组状况") {

				var demo = $(this).text();
				switch (demo) {
					case ("全部"):
						groupStatus = "";
						break;
					case ("已分组"):
						groupStatus = "1";
						break;
					case ("未分组"):
						groupStatus = "0";
						break;
				}

			} else if (obj.text() == "事件状态") {
				//日期筛选
				var demo = $(this).text();
				switch (demo) {
					case ("全部"):
						updateStatus = "";
						break;
					case ("无"):
						updateStatus = "0";
						break;
					case ("有"):
						updateStatus = "1";
				}
			} else if (obj.text() == "风险自定义") {
				//日期筛选
				var demo = $(this).text();
				switch (demo) {
					case ("全部"):
						customRiskFlag = "";
						break;
					case ("已定义"):
						customRiskFlag = "1";
						break;
					case ("未定义"):
						customRiskFlag = "0";
				}
			}

			getMonitorCompanyList();
		});
}

/**
 * 分组改变
 */
function groupChange(key) {

	groupName = groupList[key].groupName;
	// 修改组名
	$("#groupName").text(groupName);

	if(groupName=="全部"){
		$("#batchDelete").addClass("hidden")
		$("#cancelMonitor").removeClass("hidden")
	}else{
		$("#cancelMonitor").addClass("hidden")
		$("#batchDelete").removeClass("hidden")
	}
	
	/*
	 * if(groupName=="全部"){ $("#importBtn").show(); }else{
	 * $("#importBtn").hide(); }
	 */

	pageNo = 1;
	pageSize = 10;
	// 重新发送查询请求
	getMonitorCompanyList();
}

/**
 * 全选按钮
 */
function selectAllDeal() {

	if ($(".selectAll").prop("checked")) {
		$("input[name='monitorIdCheckbox']").prop("checked", true);
	} else {
		$("input[name='monitorIdCheckbox']").prop("checked", false);
	}
}

// 新增动态监控分组
function addMonitorGroup() {

	// step1 验证新的组名是否重复
	var name = $("#name").val();

	if (name.trim() == "") {
		toastr.warning("组名不能为空!");
		return;
	}
	for (var i = 0; i < groupList.length; i++) {

		if (groupList[i].groupName == name) {
			// 抛出异常
			toastr.warning("组名重复请更换!");
			return;
		}

	}
	// step2 发送给后台进行追加分组

	var parameter = {
		"groupName": encodeURIComponent($("#name").val()),
		"remark": encodeURIComponent($("#description").val())
	}
	$.ajax({
		url: ctx + "/monitorEvents/addMonitorGroup.do",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			if (result.resultCode == "0") {
				// 查询更新
				if (groupList.length <= 7) {
					// 如果小于7的话那么久没必要移动
					steps = 0;
				} else {
					steps = groupList.length - 7;
				}
				tabShow();
				// 初始化查询
				initParams();
				// 分组更新
				getMonitorCompanyList();
			} else {
			}
		},
		error: function () {
			toastr.error('网络异常！');
		},
		complete: function (XMLHttpRequest, textStatus) {
			$("#description").val("");
			$("#name").val("");

		}
	});
}
function initParams() {
	
	showGroupType = $("#showGroupType").val();
	if(showGroupType==undefined||showGroupType==""){
		showGroupType = "ACCOUNT";
	}
	currentAccountId = $("#currentAccountId").val();
	sortParam = {};//清空排序参数
	
	todayMonitorFlag = $("#todayMonitorFlag").val();

	// 判断是否是经由详情界面返回的从详情页面返回对应的标记名称是不为空的
	if ($("#flag4Back").val() != "") {
		flag = $("#flag4Back").val();

		if (flag == 'true') {
			$(".filter_open_btn2").click();
		}
		if ($("#extendFlag4Back").val() != "") {
			extendFlag = $("#extendFlag4Back").val();
			if (extendFlag == 'true') {
				$(".filter_open_btn").click();
			}
		}

		if ($("#dueDate4Back").val() != "") {
			dueDate = $("#dueDate4Back").val();
			$("#dueDate").val(dueDate);
		}
		if ($("#beginDate4Back").val() != "") {
			beginDate = $("#beginDate4Back").val();
			$("#beginDate").val(beginDate);
		}
		if ($("#companyName4Back").val() != "") {
			companyName = $("#companyName4Back").val();
			$("#searchInfo").val(companyName);
		}
		if ($("#label4Back").val() != "") {
			label = $("#label4Back").val();
			// step1 标签内部展示

			// step2标签外部展示
			var arr1 = label.split(",");
			var array = $(".label");
			var content = "";
			for (var i = 0; i < array.length; i++) {
				for (var j = 0; j < arr1.length; j++) {
					var obj = array[i];
					var text = obj.innerText;
					if (text == arr1[j]) {
						content += " <div class='inline_div selected_value tag'><div class='relative_center_block del_group_btn_block'><div class='inline_div del_group_btn'  onclick='tagChange(this)'></div></div><div class='inline_div value'>"
							+ text + "</div></div>";
						$(obj).click();
					}
				}
			}
			// 追加外面的展示标签
			$("#span_drop").append(content);

		}
		if ($("#area4Back").val() != "") {
			area = $("#area4Back").val();
			// 内部展示
			var arr1 = area.split(",");
			var array = $(".province");
			var content = "";
			for (var i = 0; i < array.length; i++) {
				for (var j = 0; j < arr1.length; j++) {
					var obj = array[i];
					var text = obj.innerText;
					if (text == arr1[j]) {
						content += " <div class='inline_div selected_value area'><div class='relative_center_block del_group_btn_block'><div class='inline_div del_group_btn'  onclick='areaChange(this)'></div></div><div class='inline_div value'>"
							+ text + "</div></div>";
						$(obj).click();
					}
				}
			}
			// 追加外面的展示标签
			$("#area_drop").append(content);

		}
		if ($("#riskLevel4Back").val() != "") {
			riskLevel = $("#riskLevel4Back").val();
			// 选中处理
			var array = $(".option");
			for (var i = 0; i < array.length; i++) {
				// 风险状况（0：正常，1：关注，2：一般预警，3：特别预警）
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "特别预警" && riskLevel == "3")
					|| (text == "一般预警" && riskLevel == "2")
					|| (text == "关注" && riskLevel == "1")
					|| (text == "正常" && riskLevel == "0")) {
					$(obj)
						.parents(".filter_options")
						.children()
						.children()
						.attr("class", "option_text option_name inline_div");
					$(obj).parent().addClass("selected");
					$(".filter_open_btn").click();
					break;
				}
			}
		}

		if ($("#eventLevel4Back").val() != "") {
			eventLevel = $("#eventLevel4Back").val();
			var array = $(".option");

			for (var i = 0; i < array.length; i++) {
				// 更新状态有无事件 默认是"" 0:无更新事件 1:有更新事件
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "严重" && eventLevel == "3")
					|| (text == "异常" && eventLevel == "2")
					|| (text == "一般" && eventLevel == "1")) {
					$(obj)
						.parents(".filter_options")
						.children()
						.children()
						.attr("class", "option_text option_name inline_div");
					$(obj).parent().addClass("selected");
					$(".filter_open_btn").click();
					break;
				}
			}
		}

		if ($("#updateStatus4Back").val() != "") {
			updateStatus = $("#updateStatus4Back").val();
			var array = $(".option");

			for (var i = 0; i < array.length; i++) {
				//更新状态有无事件 默认是"" 0:无更新事件 1:有更新事件
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "无" && updateStatus == "0") || (text == "有" && updateStatus == "1")) {
					$(obj).parents(".filter_options").children().children().attr("class", "option_text option_name inline_div");
					$(obj).parent().addClass("selected");
					$(".filter_open_btn").click();
					break;
				}
			}
		}

		if ($("#riskStatus4Back").val() != "") {
			// 风险变化（0：不变，1：升高，2：降低）
			riskStatus = $("#riskStatus4Back").val();
			var array = $(".option");
			for (var i = 0; i < array.length; i++) {
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "不变" && riskStatus == "0")
					|| (text == "升高" && riskStatus == "1")
					|| (text == "降低" && riskStatus == "2")) {
					$(obj)
						.parents(".filter_options")
						.children()
						.children()
						.attr("class", "option_text option_name inline_div");
					$(obj).parent().addClass("selected");
					$(".filter_open_btn").click();
					break;
				}
			}
		}

		if ($("#groupStatus4Back").val() != "") {

			groupStatus = $("#groupStatus4Back").val();
			var array = $(".option");
			for (var i = 0; i < array.length; i++) {
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "未分组" && groupStatus == "0")
					|| (text == "已分组" && groupStatus == "1")) {
					$(obj)
						.parents(".filter_options")
						.children()
						.children()
						.attr("class", "option_text option_name inline_div");
					$(obj).parent().addClass("selected");
					$(".filter_open_btn").click();
					break;
				}
			}
		}

		if ($("#currentPageNo4Back").val() != "") {
			pageNo = $("#currentPageNo4Back").val();
		}
		if ($("#pageSize4Back").val() != "") {
			pageSize = $("#pageSize4Back").val();
		}
		if ($("#groupName4Back").val() != "") {
			groupName = $("#groupName4Back").val();
		}
		if ($("#move_index4Back").val() != "") {
			move_index = $("#move_index4Back").val();
		}
		if ($("#steps4Back").val() != "") {
			steps = $("#steps4Back").val();
		}
		if ($("#key4Back").val() != "") {
			key = $("#key4Back").val();
		}
	} else {
		// 从首页进入的初始化
		/**
		 * 主界面跳转 选择组名跳转处理
		 */
		if ($("#groupName1").val() != "") {
			groupName = $("#groupName1").val();
		} else {
			groupName = "全部";
		}

		/**
		 * 主界面跳转一 选择风险状况跳转到当前页面需要进行处理
		 */
		if ($("#riskLevel").val() != "") {
			riskLevel = $("#riskLevel").val();
			//风险状况有 颜色加深

			var array = $(".option");

			for (var i = 0; i < array.length; i++) {

				//风险状况（0：正常，1：关注，2：一般预警，3：特别预警）
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "特别预警" && riskLevel == "3") || (text == "一般预警" && riskLevel == "2") ||
					(text == "关注" && riskLevel == "1") || (text == "正常" && riskLevel == "0")) {
					groupName == "全部";
					$(obj).click();
					queryFlag = false;
					$(".filter_open_btn").click();
					break;

				}

			}

		} else {
			riskLevel = "";
		}
		/**
		 * 主界面跳转二 选择事件有无跳转处理
		 */
		if ($("#eventLevel").val() != "") {
			eventLevel = $("#eventLevel").val();
			var array = $(".option");

			for (var i = 0; i < array.length; i++) {

				var obj = array[i];
				var text = obj.innerText;
				if ((text == "严重" && eventLevel == "3") || (text == "异常" && eventLevel == "2") || (text == "一般" && eventLevel == "1")) {
					$(obj).parent().attr("class", "option_text option_name inline_div selected");
					$("#eventLevel4All").removeClass("selected");
					$(".filter_open_btn").click();
					break;

				}

			}

		} else {
			eventLevel = "";
		}


		/**
		 * 主界面跳转三 地图跳转
		 */
		if ($("#province").val() != "") {
			//如果是通过省份过来的需要对省份进行接收
			area = $("#province").val();
			//展示性工作
			var array = $(".province");
			var flag = false;
			for (var i = 0; i < array.length; i++) {

				var obj = array[i];
				var text = obj.innerText;
				if (text == $("#province").val()) {
					$(obj).click();
					$(".filter_open_btn").click();
					flag = true;
					break;
				}

			}
			//追加外面的展示标签
			if (flag == true) {
				var content = " <div class='inline_div selected_value area' ><div class='relative_center_block del_group_btn_block'><div class='inline_div del_group_btn'  onclick='areaChange(this)'></div></div><div class='inline_div value'>" + text + "</div></div>";
				$("#area_drop").append(content);
			} else {
				area = "";
			}


		}

		/**
		 * 主界面跳转五 选择事件有无跳转处理
		 */
		if ($("#updateStatus").val() != "") {
			updateStatus = $("#updateStatus").val();
			var array = $(".option");

			for (var i = 0; i < array.length; i++) {
				//更新状态有无事件 默认是"" 0:无更新事件 1:有更新事件
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "无" && updateStatus == "0") || (text == "有" && updateStatus == "1")) {
					//重新加背景色 
					$(obj).parent().attr("class", "option_text option_name inline_div selected");
					$("#updateStatus4All").removeClass("selected");
					$(".filter_open_btn").click();
					break;

				}

			}

		} else {
			updateStatus = "";
		}

		//跳转方式6 风险状况
		if ($("#riskStatus ").val() != "") {
			riskStatus = $("#riskStatus").val();
			var array = $(".option");

			for (var i = 0; i < array.length; i++) {
				//更新状态有无事件 默认是"" 0:无更新事件 1:有更新事件
				var obj = array[i];
				var text = obj.innerText;
				if ((text == "不变" && riskStatus == "0") || (text == "升高" && riskStatus == "1") || (text == "降低" && riskStatus == "2")) {
					$(obj).click();
					queryFlag = false;
					$(".filter_open_btn").click();
					break;

				}

			}

		} else {
			riskStatus = "";
		}
	}
	// 修改组名
	$("#groupName").text(groupName);
}

//获取选中分组id
function getCheckedGroupIds() {

	$(".selectedComName").html("");

	// 判断是否选中 如果是选中那么需要往右边加企业名称 如果是没有选中那么就需要从右边移除名称
	var arrChk = $("input[name='groupIdList']:checked");

	var groupIds = "";

	// 遍历得到每个checkbox的value值
	for (var i = 0; i < arrChk.length; i++) {
		groupIds += arrChk[i].value;

		if (i < arrChk.length - 1) {
			groupIds += ",";
		}
	}

	return groupIds;
}

//获取选中的监控企业id
function getCheckedMonitorIds() {

	var arrChk = $("input[name='monitorIdCheckbox']:checked");

	var monitorIds = "";

	// 遍历得到每个checkbox的value值
	for (var i = 0; i < arrChk.length; i++) {
		monitorIds += arrChk[i].value;
		if (i < arrChk.length - 1) {
			monitorIds += ",";
		}
	}

	return monitorIds;
}

//批量修改监控企业分组
function updateMonitorCompanyGroupBatch() {

	var monitorIds = getCheckedMonitorIds();
	var groupIds = getCheckedGroupIds();

	if (monitorIds.trim() == "") {
		toastr.warning("批量修改分组前请选择相应公司!");
		return;
	}

	var parameter = {
		"monitorIds": monitorIds,
		"groupIds": groupIds
	}

	$.ajax({
		url: ctx + "/monitorGroup/updateMonitorCompanyGroupBatch",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			if (result.resultCode == "0") {
				toastr.success(result.resultMsg);
				$("input[name='monitorIdCheckbox']").prop("checked", false);
				($(".selectAll").prop("checked", false));

				//刷新企业列表
				getMonitorCompanyList();
			} else {
				toastr.error(result.resultMsg);
			}
		}
	});
}

/**
 * 批量删除公司
 */
function batchDelete() {
	var groupId;
	for (var i = 0; i < groupList.length; i++) {

		if (groupList[i].groupName == groupName) {
			groupId = groupList[i].groupId;
			break;
		}

	}
	var monitorIds = getCheckedMonitorIds();
	var parameter = {
		"companyListStr": monitorIds,
		"groupId": groupId
	}

	$.ajax({
		url: ctx + "/monitorEvents/batchDelete.do",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			// 批量删除后续处理
			if (result.resultCode == "0") {
				getMonitorCompanyList();
				toastr.success(result.resultMsg);
			} else {
				toastr.error(result.resultMsg);
			}
		}
	});

}

//查看监控企业详情
function viewMonitorCompanyDetail(monitorId) {

	var deep = $("#deep").val();

	// 将个人加入对应分组
	var parameter = {
		"currentAccountId":$("#currentAccountId").val(),
		"showGroupType":$("#showGroupType").val(),
		"groupName": encodeURIComponent(groupName),
		"riskLevel": riskLevel,
		"riskStatus": riskStatus,
		"eventLevel": eventLevel,
		"beginDate": beginDate,
		"dueDate": dueDate,
		"area": encodeURIComponent(area),
		"label": encodeURIComponent(label),
		"updateStatus": updateStatus,
		"customRiskFlag": customRiskFlag,
		"todayMonitorFlag": todayMonitorFlag,
		"currentPageNo": pageNo,
		"pageSize": pageSize,
		"companyName": encodeURIComponent($("#searchInfo").val()),
		"key": key,
		"steps": steps,
		"flag": flag,
		"groupStatus": groupStatus,
		"deep": deep
	}


	// 更新导航菜单
	updateNavMenu(parseInt(deep), "监控企业列表", parameter);

	//	var params = {
	//		"monitorId": monitorId,
	//		"deep": parseInt(deep)+1
	//	};
	//	
	//	httpPost(ctx + "/monitorCompany/viewMonitorCompanyDetail", params);

	window.location.href = ctx + "/monitorCompany/viewMonitorCompanyDetail?monitorId=" + monitorId + "&deep=" + (parseInt(deep) + 1);
}

//查看今日监控企业的详情
function viewTodayMonitorCompanyDetail(companyName, monitorId) {

	var deep = $("#deep").val();

	// 将个人加入对应分组
	var parameter = {
		"currentAccountId":$("#currentAccountId").val(),
		"showGroupType":$("#showGroupType").val(),
		"groupName": encodeURIComponent(groupName),
		"riskLevel": riskLevel,
		"riskStatus": riskStatus,
		"eventLevel": eventLevel,
		"beginDate": beginDate,
		"dueDate": dueDate,
		"area": encodeURIComponent(area),
		"label": encodeURIComponent(label),
		"updateStatus": updateStatus,
		"customRiskFlag": customRiskFlag,
		"todayMonitorFlag": todayMonitorFlag,
		"currentPageNo": pageNo,
		"pageSize": pageSize,
		"companyName": encodeURIComponent($("#searchInfo").val()),
		"key": key,
		"steps": steps,
		"flag": flag,
		"groupStatus": groupStatus,
		"deep": deep
	}

	var date = new Date();
	var year = date.getFullYear();

	var month = date.getMonth() + 1;
	if (month >= 1 && month <= 9) {
		month = "0" + month;
	}

	var strDate = date.getDate();
	if (strDate >= 1 && strDate <= 9) {
		strDate = "0" + strDate;
	}

	toCompanyBrefBizView(companyName, parseInt(deep) + 1);
	updateNavMenu(parseInt(deep), "监控企业列表", parameter);
}

/**
 * 输入公司名称进行检索
 */
function searchByCompanyName() {
	$("#searchBtn").click(function () {
		companyName = $("#searchInfo").val().trim();
		getMonitorCompanyList();
	});

}

/**
 * 区域选择标签
 */
function areaChoose() {

	$(".province").click(function () {
		$("#allArea").removeClass("selected");
		if ($(this).parent().attr("class") == "selector inline_div") {
			$(this).parent().addClass("selected")
		} else {
			$(this).parent().removeClass("selected")
		}
	});

}

/**
 * 区域选择标签
 */
function labelChoose() {

	$(".label").click(function () {
		$("#allLabel").removeClass("selected");
		if ($(this).parent().attr("class") == "selector inline_div") {
			$(this).parent().addClass("selected")
		} else {
			$(this).parent().removeClass("selected")
		}
	});
}

/**
 * 区域选择标签
 */
function allAreaChoose() {

	$("#allArea").click(function () {
		$(this).addClass("selected");
		// var obj=$(this);
		// if($(obj).attr("class")=="inline_div selector selected"){
		// $(this).removeClass("selected");
		// }else{
		// $(this).addClass("selected");
		// }
		$(this).parents(".selector_block").children().removeClass("selected");
	});

}

/**
 * 区域选择标签
 */
function allLabelChoose() {

	$("#allLabel").click(function () {
		$(this).addClass("selected");
		// var obj=$(this);
		//			
		// if($(obj).attr("class")=="inline_div selector selected"){
		// $(this).removeClass("selected");
		// }else{
		// $(this).addClass("selected");
		//				
		// }
		$(this).parents(".selector_block").children().removeClass("selected");
	});
}

/**
 * 区域按钮提交
 */
function areaSubmit() {

	// var array=$(".label");
	var content = "";
	area = "";
	$(".area").remove();
	var array1 = $(".area_overlap .selected");
	for (var i = 0; i < array1.length; i++) {
		var text = $(array1[i]).find(".province").text();
		if (text != "") {
			content += " <div class='inline_div selected_value area' ><div class='relative_center_block del_group_btn_block'><div class='inline_div del_group_btn'  onclick='areaChange(this)'></div></div><div class='inline_div value'>"
				+ text + "</div></div>";
		}
		if (area == "") {
			area = text;
		} else {
			area = area + "," + text;
		}

	}
	// 外面进行标签展示
	$("#area_drop").append(content);
	$(".monitor_event_filter").animate({
		height: $(".filter_block").outerHeight(true)
	});
	getMonitorCompanyList();
}

/**
 * 选中标签发生了改变
 */
function areaChange(_this) {
	area = "";

	$(_this).parents(".selected_value").remove();
	$(".monitor_event_filter").animate({
		height: $(".filter_block").outerHeight(true)
	});
	// step2 重新收集area
	var array1 = $(".area");
	for (var i = 0; i < array1.length; i++) {
		var text = $(array1[i]).text();
		if (area == "") {
			area = text;
		} else {
			area = area + "," + text;
		}
	}
	$("#allArea").click();
	// 内部展示
	var arr1 = area.split(",");
	var array = $(".province");
	var content = "";
	for (var i = 0; i < array.length; i++) {
		for (var j = 0; j < arr1.length; j++) {
			var obj = array[i];
			var text = obj.innerText;
			if (text == arr1[j]) {
				content += " <div class='inline_div selected_value area'><div class='relative_center_block del_group_btn_block'><div class='inline_div del_group_btn'  onclick='areaChange(this)'></div></div><div class='inline_div value'>"
					+ text + "</div></div>";
				$(obj).click();
			}
		}
	}

	// step3重新发起查询
	getMonitorCompanyList();

}

/**
 * 标签确定提交
 */
function labelSubmit() {

	// var array=$(".label");
	label = "";
	var content = "";
	$(".tag").remove();
	var array1 = $(".span_overlap .selected");
	for (var i = 0; i < array1.length; i++) {
		var text = $(array1[i]).find(".label").text();
		if (text != "") {
			content += " <div class='inline_div selected_value tag' ><div class='relative_center_block del_group_btn_block'><div class='inline_div del_group_btn' onclick='tagChange(this)'></div></div><div class='inline_div value'>"
				+ text + "</div></div>";
		}
		if (label == "") {
			label = text;
		} else {
			label = label + "," + text;
		}
	}
	// 外面进行标签展示
	$("#span_drop").append(content);
	$(".monitor_event_filter").animate({
		height: $(".filter_block").outerHeight(true)
	});
	getMonitorCompanyList();
}

/**
 * 选中标签发生了改变
 */
function tagChange(_this) {
	var value
	label = "";
	// step1 改变外面所选择的标签
	$(_this).parents(".selected_value").remove();
	$(".monitor_event_filter").animate({
		height: $(".filter_block").outerHeight(true)
	});
	// step2 重新收集label
	var array1 = $(".tag");
	for (var i = 0; i < array1.length; i++) {
		var text = $(array1[i]).text();
		if (label == "") {
			label = text;
		} else {
			label = label + "," + text;
		}
	}
	$("#allLabel").click();
	// step2标签外部展示
	var arr1 = label.split(",");
	var array = $(".label");
	var content = "";
	for (var i = 0; i < array.length; i++) {
		for (var j = 0; j < arr1.length; j++) {
			var obj = array[i];
			var text = obj.innerText;
			if (text == arr1[j]) {
				content += " <div class='inline_div selected_value tag'><div class='relative_center_block del_group_btn_block'><div class='inline_div del_group_btn'  onclick='tagChange(this)'></div></div><div class='inline_div value'>"
					+ text + "</div></div>";
				$(obj).click();
			}
		}
	}
	// step3重新发起查询
	getMonitorCompanyList();
}

function deleteGroup(key) {

	var parameter = {
		"groupId": groupList[key].groupId
	}
	$.ajax({
		url: ctx + "/monitorEvents/deleteGroup.do",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			// 批量删除后续处理
			if (result.resultCode == "0") {

				if (groupList.length - 1 <= 8) {
					// 总共数量低于8个不需要移动
					steps = 0;
				} else {
					if (move_index + 7 == groupList.length) {
						// 正好是最后一位
						steps = move_index - 2;
					} else {
						steps = move_index - 1;
					}
				}

				tabShow();
				toastr.success(result.resultMsg);
			} else {
				toastr.error(result.resultMsg);
			}
		}
	});

}

/**
 * 更新组名
 * 
 * @param groupName
 * @param description
 */
function updateGroupName(groupName4New, description) {

	// step1 校验组名
	if (groupName4New.trim() == "") {
		toastr.warning("组名不能为空!");
		return;
	}
	var name = $("#groupName").text();

	// if(name==groupName){
	// toastr.warning("新组名不能与原组名一样!");
	// return;
	// }

	for (var i = 0; i < groupList.length; i++) {

		if (groupList[i].groupName == groupName4New) {
			// 抛出异常
			toastr.warning("组名重复请更换!");
			return;
		}

	}
	// step2 更新组名
	var groupId;
	var index;
	for (var i = 0; i < groupList.length; i++) {

		if (groupList[i].groupName == name) {
			groupId = groupList[i].groupId;
			index = i;
			break;
		}

	}

	var parameter = {
		"groupId": groupId,
		"groupName": encodeURIComponent(groupName4New),
		"description": encodeURIComponent(description)
	}
	$
		.ajax({
			url: ctx + "/monitorEvents/updateGroupName.do",
			type: "POST",
			data: parameter,
			dataType: "json",
			success: function (result) {

				if (result.resultCode == "0") {
					// 更新成功之后需要更新列表 简单的更新就不需要去查询数据库了
					$("#groupName").text(groupName4New);
					// 列表也需要更新
					groupList[index].groupName = groupName4New;
					groupName = groupName4New;
					groupList[index].remark = description;

					var key = $(this).find(".tab_value").attr("key");
					var arr = $(".tab_value");
					$(arr[index]).find(".line_txt").text(groupName4New);

					var arr1 = $(".option_check_value").children();
					$(arr1[index - 1]).find(".grouName4Option").text(
						groupName4New);

					toastr.success(result.resultMsg);
				} else {
					toastr.error(result.resultMsg);
				}
			}
		});

}

//批量取消动态监控
function deleteMonitorCompanyBatch() {

	var monitorIds = getCheckedMonitorIds();

	var parameter = {
		"monitorIds": monitorIds,
	}

	$.ajax({
		url: ctx + "/monitorCompany/deleteMonitorCompanyBatch",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			// 批量取消动态监控之后重新展示
			if (result.resultCode == "0") {
				getMonitorCompanyList();

				toastr.success(result.resultMsg);
			} else {
				toastr.error(result.resultMsg);
			}
		}
	});

}

/**
 * 日期对比函数
 * 
 * @param beginDate
 * @param dueDate
 * @returns {Boolean}
 */
function dateCompare(beginDate, dueDate) {
	if (beginDate.trim() == "" || dueDate.trim() == "") {
		return true;
	}
	var arr = beginDate.split("-");
	var starttime = new Date(arr[0], arr[1], arr[2]);
	var starttimes = starttime.getTime();

	var arrs = dueDate.split("-");
	var lktime = new Date(arrs[0], arrs[1], arrs[2]);
	var lktimes = lktime.getTime();

	if (starttimes > lktimes) {
		toastr.warning("开始时间大于结束时间!");
		return false;
	} else
		return true;
}

/**
 * 颜色处理事件
 * 
 * @param _this
 */
function colorDeal4In(_this) {
	$(_this).find(".comName").css("color", "#2ea7e0");
}

/**
 * 颜色处理事件
 * 
 * @param _this
 */
function colorDeal4Out(_this) {
	$(_this).find(".comName").css("color", "#666666");
}

/**
 * 清空按钮
 */
function clear() {
	$("#clearBtn").click(function () {
		
		sortParam={};//清空排序参数
		
		$("#allArea").click();
		$("#allLabel").click();
		$(".tag").remove();
		$(".area").remove();
		$("#beginDate").val("");
		$("#dueDate").val("");
		$("#searchInfo").val("");

		// 清空背景色
		$(".option").parent().removeClass("selected");
		// 重新加背景色

		$("#eventLevel4All").addClass("selected");
		$("#riskStatus4All").addClass("selected");
		$("#riskLevel4All").addClass("selected");
		$("#groupStatus4All").addClass("selected");
		$("#updateStatus4All").addClass("selected");
		$("#customRiskFlag").addClass("selected");
		groupStatus = "";
		riskLevel = "";
		eventLevel = "";
		riskStatus = "";
		area = "";
		label = "";
		beginDate = "";
		dueDate = "";
		customRiskFlag = "";
		todayMonitorFlag = "";
		updateStatus = "";
		pageNo = 1;
		pageSize = 10;
		getMonitorCompanyList();
	})
}
function bindEnterSearch() {
	$('#rsearchInfo').bind('keypress', function (event) {
		if (event.keyCode == "13") {
			companyName = $("#searchInfo").val().trim();
			getMonitorCompanyList();
		}
	});
}

function relateBlockShow(monitorId, companyName) {
	relationship.show(companyName, monitorId);
}

var navigatePersonMode = function () {

	var companyName = $("#rcompanyName").val();
	console.log("navigatePersonMode...... companyName ===" + companyName);
	var parameter = {
		"companyName": companyName,
	}

	$.ajax({
		url: ctx + "/addRelationCtl/getRelationPerson",
		type: "POST",
		data: parameter,
		dataType: "html",
		success: function (result) {
			$("#person_mode_div").html(result);
		},
		complete: function (request, textStatus) {
			bindPersonTabSwithcEvent();
			// initDatePicker();
			// displayTabInPersonMode('3');
		}
	});
}

var navigateCompanyMode = function () {
	var companyName = $("#rcompanyName").val();
	console.log("navigateCompanyMode...... companyName ===" + companyName);

	var parameter = {
		"companyName": companyName,
		"monitorId": $("#monitorId").val()
	}
	$.ajax({
		url: ctx + "/addRelationCtl/getRelationCompany",
		type: "POST",
		data: parameter,
		dataType: "html",
		success: function (result) {
			// 隐藏搜索结果；
			$('#searchResultDiv').hide();
			$('#Pagination4Search').html("");
			// 隐藏touzi;
			$("#investSearchResultDiv").hide();
			$('#PaginationInvestmentCompany').html("");
			// 隐藏关系自然人
			$("#person_mode_div").html("");

			$("#company_mode_div").show();
			$("#company_mode_div").html(result);
			// 展示分页标签
			if ($("#totalNumStockHolderCompany").val() > 0) {
				$("#PageStockHolderCompanyDiv").pagination(
					$("#totalNumStockHolderCompany").val(), {
						prev_text: "上一页",
						next_text: "下一页",
						num_edge_entries: 3,
						num_display_entries: 10,
						// 回调
						callback: pageselectCallbackForStockHolderCompany
					});
			}

			// 重新绑定事件
			addRealCompany('company_mode_div');
			bindEnterSearchReal();
		},
		complete: function (request, textStatus) {

			bindCompanyTabSwitchEvent();

		}
	});
}
// 股东回调；
var pageselectCallbackForStockHolderCompany = function (new_page_index,
	pagination_container) {

	var currentPageNo = parseInt(new_page_index) + 1;
	var companyName = $("#hcompanyName").val();
	console.log("companyName ==" + companyName);
	var parameter = {
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10),
		"companyName": companyName,
		"monitorId": $("#monitorId").val()
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/addRelationCtl/getStockHolderCompanyList",
		data: parameter,
		beforeSend: function () {
			// 异步请求时spinner出现
			var target = $(".company_list").get(0);
			spinner.spin(target);
		},
		success: function (result) {
			spinner.spin();
			$("#stockHolderCompany").show();// 隐藏企业股东
			$("#stockHolderCompany").html(result);
			// 隐藏企业股东
			$("#investSearchResultDiv").hide();
			$('#PaginationInvestmentCompany').html("");
			// 隐藏搜索结果；
			$('#searchResultDiv').hide();
			$('#Pagination4Search').html("");

			// 隐藏关系自然人
			$("#person_mode_div").html("");

			// 重新绑定事件
			addRealCompany('stockHolderCompany');
		}
	});
}

// 绑定公司TAB注册事件；
var bindCompanyTabSwitchEvent = function () {
	$("#relate_add_block .company_mode .filter_title_block .title")
		.click(
		function () {

			console.log("pick up company_mode");

			var _this = this;
			if ($(this).hasClass("selected")) {
				return;
			}
			$(
				"#relate_add_block .company_mode .filter_title_block .selected")
				.removeClass("selected");
			$(_this).addClass("selected");
			var move_lenth = $(this).position().left - 37;
			$(
				"#relate_add_block  .company_mode .filter_title_block .filter_arr")
				.animate({
					left: move_lenth
				}, 300, function () {

				})
		})
}

var bindPersonTabSwithcEvent = function () {

	$("#relate_add_block .person_mode .filter_title_block .title")
		.click(
		function () {
			var _this = this;
			if ($(this).hasClass("selected")) {
				return;
			}

			$(
				"#relate_add_block .person_mode .filter_title_block .selected")
				.removeClass("selected");
			$(_this).addClass("selected");
			var move_lenth = $(this).position().left - 37;
			$(
				"#relate_add_block .person_mode .filter_title_block .filter_arr")
				.animate({
					left: move_lenth
				}, 300, function () {

					console.log("switch tabs('股东','投资','高管')");

				})
			var tabId = $(_this).attr("id");
			console.log("tabId====" + tabId);
		})

	// 綁定事件；
	$("#relate_add_block .person_mode .filter_title_block .title").map(
		function () {

			var _this = this;
			var Stype = $(_this).text();
			if (Stype == '企业法人代表') {
				$(_this).click(function () {
					displayTabInPersonMode("企业法人代表");
				})
			} else if (Stype == '股东') {
				$(_this).click(function () {
					displayTabInPersonMode("股东");
				})
			} else if (Stype == '高管') {
				$(_this).click(function () {
					displayTabInPersonMode("高管");
				})
			}
		})

	// 绑定日历事件；
	$('#pprovince, #pcity').citylist({
		data: data,
		id: 'id',
		children: 'cities',
		name: 'name',
		metaTag: 'name'
	});

	$("#relate_add_block .person_mode .filter_option_block .option")
		.click(
		function () {

			var _this = this;
			if ($(this).hasClass("selected")) {
				var add = $(_this).attr("add");
				console.log("_this add= ====" + add);
				console.log("this val===" + $(_this).html());
				if (add == '1') {
					return;
				} else {
					$(_this).removeClass("selected");
					return;
				}
			}

			// $("#relate_add_block .person_mode
			// .filter_option_block
			// .selected").removeClass("selected");
			// 恢复未被选中的菜单项;
			$(
				"#relate_add_block .person_mode .filter_option_block .selected")
				.map(function () {
					var s_this = this;
					var add = $(s_this).attr("add");
					if (add == '1') {
						return;
					} else {
						$(s_this).removeClass("selected");
						return;
					}
				});

			$(_this).addClass("selected");
			$("#ppersonName").val($(_this).html());
		})

	$("#option_up_btn").click(function () {
		$("#relate_add_block .person_mode .filter_option_block .option_list").stop().animate({ scrollTop: "-=" + 35 }, 500)
	})

	$("#option_down_btn").click(function () {
		$("#relate_add_block .person_mode .filter_option_block .option_list").stop().animate({ scrollTop: "+=" + 35 }, 500)
	})

}

var getInvestmentCompanyList = function () {

	var companyName = $("#rcompanyName").val();
	console.log("navigateCompanyMode...... companyName ===" + companyName);
	var parameter = {
		"companyName": companyName,
		"monitorId": $("#monitorId").val()
	}
	$.ajax({
		url: ctx + "/addRelationCtl/getInvestmentCompanyList",
		type: "POST",
		data: parameter,
		dataType: "html",
		success: function (result) {

			$("#investSearchResultDiv").show();
			$("#investSearchResultDiv").html(result);

			// 隐藏搜索结果；
			$('#searchResultDiv').hide();
			$('#Pagination4Search').html("");
			// $('#searchResultDiv').html("");

			// 隐藏企业股东
			$("#stockHolderCompany").hide();
			$('#PageStockHolderCompanyDiv').html("");

			// 隐藏关系自然人
			$("#person_mode_div").html("");

			// 展示分页标签
			if ($("#totalNumInvestmentCompany").val() > 0) {
				$("#PaginationInvestmentCompany").pagination(
					$("#totalNumInvestmentCompany").val(), {
						prev_text: "上一页",
						next_text: "下一页",
						num_edge_entries: 3,
						num_display_entries: 10,
						// 回调
						callback: pageselectCallbackForInvestmentCompany
					});
			}

			// 重新绑定事件
			addRealCompany('investSearchResultDiv');
		},
		complete: function (request, textStatus) {
			bindCompanyTabSwitchEvent();
		}
	});

}

var pageselectCallbackForInvestmentCompany = function (new_page_index,
	pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;
	var companyName = $("#rcompanyName").val();
	var parameter = {
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10),
		"companyName": companyName,
		"monitorId": $("#monitorId").val()
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/addRelationCtl/getInvestmentCompanyList",
		data: parameter,
		beforeSend: function () {
			// 异步请求时spinner出现
			var target = $(".company_list").get(0);
			spinner.spin(target);
		},
		success: function (result) {
			spinner.spin();
			$("#investSearchResultDiv").show();
			$("#investSearchResultDiv").html(result);

			// 隐藏搜索结果；
			$('#searchResultDiv').hide();
			$('#searchResultDiv').html("");
			$('#Pagination4Search').html("");

			// 隐藏企业股东
			$("#stockHolderCompany").hide();
			$("#stockHolderCompany").html();
			$('#PageStockHolderCompanyDiv').html("");

			// 隐藏关系自然人
			$("#person_mode_div").html("");

			// 重新绑定事件
			addRealCompany('investSearchResultDiv');
		}
	});
}

// getStockHolderCompanyList
var getStockHolderCompanyList = function () {
	var companyName = $("#rcompanyName").val();
	console.log("getStockHolderCompanyList...... companyName ===" + companyName);
	var parameter = {
		"companyName": companyName,
		"monitorId": $("#monitorId").val()
	}
	$.ajax({
		url: ctx + "/addRelationCtl/getStockHolderCompanyList",
		type: "POST",
		data: parameter,
		dataType: "html",
		success: function (result) {

			$("#stockHolderCompany").show();
			$("#stockHolderCompany").html(result);
			// 隐藏搜索结果；
			$('#searchResultDiv').hide();
			$('#Pagination4Search').html("");
			// yinchang touzi ;

			$("#investSearchResultDiv").hide();
			$('#PaginationInvestmentCompany').html("");

			// 隐藏关系自然人
			$("#person_mode_div").html("");

			// 展示分页标签
			if ($("#totalNumStockHolderCompany").val() > 0) {
				$("#PageStockHolderCompanyDiv").pagination(
					$("#totalNumStockHolderCompany").val(), {
						prev_text: "上一页",
						next_text: "下一页",
						num_edge_entries: 3,
						num_display_entries: 10,
						// 回调
						callback: pageselectCallbackForStockHolderCompany
					});
			}
			// 重新绑定事件
			addRealCompany('stockHolderCompany');
		},
		complete: function (request, textStatus) {
			bindCompanyTabSwitchEvent();
		}
	});

}

var displayTabInPersonMode = function (Stype) {

	$("#relate_add_block .person_mode .filter_option_block .option_list").addClass("hidden");
	if (Stype == '企业法人代表') {
		$("#leagalPersonDiv").removeClass("hidden");

	} else if (Stype == '股东') {
		$("#stockHolderDiv").removeClass("hidden");
	} else if (Stype == '高管') {
		$("#managerDiv").removeClass("hidden");
	}
}

/**
 * 检索公司信息 调用大数据接口查询公司相关信息
 */
function search4Company() {
	// addRealCompany

	// 将个人加入对应分组
	var para = $.trim($("#rsearchInfo").val());
	var companyName = $("#rcompanyName").val();
	console.log("para=====" + para);
	var parameter = {
		"keyword": para,
		"companyName": companyName,
		"monitorId": $("#monitorId").val()
	}

	if (para != "" && para != undefined) {
		$.ajax({
			type: "post",
			dataType: "html",
			url: ctx
			+ "/addRelationCtl/search4Company1.do",
			data: parameter,
			beforeSend: function () {
				// 异步请求时spinner出现
				var target = $(".company_list")
					.get(0);
				spinner.spin(target);
			},
			success: function (result) {

				console.log("search company4 callback...");
				spinner.spin();
				// 追加完成之后重新显示页面
				$('#searchResultDiv').show();
				$('#searchResultDiv').html(result);
				$("#tabComDiv").hide();


				$("#stockHolderCompany").hide();
				$("#stockHolderCompany").html("");
				$('#PageStockHolderCompanyDiv')
					.html("");
				// 隐藏投资；
				$("#investSearchResultDiv").hide();
				$("#investSearchResultDiv").html("");
				$('#PaginationInvestmentCompany').html("");

				// $("#stockHolderCompany").hide();
				// 重新绑定事件

				$("#Pagination4Search").html("");
				// 展示分页标签
				if ($("#totalNum4Search").val() > 0) {
					$("#Pagination4Search").pagination(
						$("#totalNum4Search").val(),
						{
							prev_text: "上一页",
							next_text: "下一页",
							num_edge_entries: 3,
							num_display_entries: 10,
							// 回调
							callback: pageselectCallbackForSearch
						});
				}

				addRealCompany('searchResultDiv');
			}
		});
	}


}

/**
 * @param new_page_index
 * @param pagination_container
 */
function pageselectCallbackForSearch(new_page_index, pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;
	var companyName = $("#rcompanyName").val();
	var parameter = {
		"keyword": $.trim($("#rsearchInfo").val()),
		"companyName": companyName,
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10),
		"monitorId": $("#monitorId").val()
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/addRelationCtl/search4Company1.do",
		data: parameter,
		beforeSend: function () {
			// 异步请求时spinner出现
			var target = $(".company_list").get(0);
			spinner.spin(target);
		},
		success: function (result) {
			spinner.spin();
			// 重新绘制查询结果界面

			$("#stockHolderCompany").hide();
			$("#stockHolderCompany").html("");
			$('#PageStockHolderCompanyDiv').html("");
			// 隐藏投资；
			$("#investSearchResultDiv").hide();
			$("#investSearchResultDiv").html("");
			$('#PaginationInvestmentCompany').html("");

			$('#searchResultDiv').show();
			$('#searchResultDiv').html(result);

			$("#tabComDiv").hide();

			// 重新绑定事件
			addRealCompany('searchResultDiv');
			// initSearchResult(result);
		}
	});
}

function bindEnterSearchReal() {
	$('#rsearchInfo').bind('keypress', function (event) {
		if (event.keyCode == "13") {
			$("#searchBtnId").click();

		}
	});
}


/**
 * 追加关联公司
 */
function addRealCompany(divId) {

	$("#" + divId + " .add_btn").click(
		function () {
			var name = $(this).attr("enterpriseName");

			var parameter = {
				"companyName": encodeURIComponent(name),
				"monitorId": $("#monitorId").val()
			}

			controller.message_lap4.show("#addRelationCompanyTipsWindow");
			controller.lap_list.RelateOverlap.close_quick();
			var cancelDiv = document.getElementById("cancel_message_company");
			cancelDiv.onclick = function () {
				controller.message_lap4.close();
				controller.lap_list.RelateOverlap.show_quick("#relate_add_block");
			}

			var commitDiv = document.getElementById("commit_cancel_company");
			commitDiv.onclick = function () {
				controller.message_lap4.close();
				controller.lap_list.RelateOverlap.show_quick("#relate_add_block");
				$.ajax({
					url: ctx + "/monitorRelationCompany/addMonitorRelationCompany",
					type: "POST",
					data: parameter,
					beforeSend: function () {
						// 异步请求时spinner出现
						var target = $(".company_list").get(0);
						spinner.spin(target);
					},
					dataType: "json",
					success: function (result) {
						spinner.spin();
						if (result.resultCode == "0") {
							toastr.options.timeOut="1000";
				            toastr.options.closeButton = "true";
							toastr.success(result.resultMsg);
							// relatedGroupInfo();
						} else {
							if(result.resultCode == "2"){
								toastr.options.timeOut=24*60*60*1000+"";
					            toastr.options.closeButton = "true";
							}else{
								toastr.options.timeOut="1000";
					            toastr.options.closeButton = "true";
							}
							toastr.error(result.resultMsg);
						}
					}
				});
			}
		});
	controller.rebind_overlap_event_content_scroll();
}

/**
 * @param card
 * @returns {Boolean}
 */
function isCardNo(card) {
	// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
	var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
	if (!reg.test(card)) {
		return false;
	}
}

var addRelationPerson = function () {

	var tabType = "";
	var tt = $("#relate_add_block .person_mode .filter_title_block .title")
		.map(function () {
			var div_this = this;
			console.log("div_this html innerHTML===" + this.innerHTML);
			if ($(div_this).hasClass("selected")) {
				console.log("checked ===");
				tabType = this.innerHTML;
				$(div_this).click();
			}

		});

	if ($("#ppersonName").val() == '') {
		toastr.error("请输入姓名!");
		return;
	}
	if ($("#pprovince").val() == '请选择省') {
		toastr.error("请选择常住地！");
		return;
	}

	if ($("#pcity").val() == '请选择市') {
		toastr.error("请选择常住地！");
		return;
	}

	if ($("#pidNumber").val() != "") {
		if (isCardNo($("#pidNumber").val()) === false) {
			toastr.error("请输入正确的身份证！");
			return;
		}
	}

	console.log("para===");
	var parameter = {
		"name": encodeURIComponent($("#ppersonName").val()),
		"province": encodeURIComponent($("#pprovince").val()),
		"city": encodeURIComponent($("#pcity").val()),
		"idNumber": $("#pidNumber").val(),
		"companyName": encodeURIComponent($("#hcompanyName").val()),
		"monitorId": $("#monitorId").val()
	}

	controller.message_lap5.show("#addRelationPersonTipsWindow");
	controller.lap_list.RelateOverlap.close_quick();
	var cancelDiv = document.getElementById("cancel_message_person");
	cancelDiv.onclick = function () {
		controller.message_lap5.close();
		controller.lap_list.RelateOverlap.show_quick("#relate_add_block");
	}

	var commitDiv = document.getElementById("commit_cancel_person");

	commitDiv.onclick = function () {
		controller.message_lap5.close();
		controller.lap_list.RelateOverlap.show_quick("#relate_add_block");

		$.ajax({
			url: ctx + "/monitorRelationPerson/addMonitorRelationPerson",
			type: "POST",
			data: parameter,
			beforeSend: function () {
				// 异步请求时spinner出现
				var target = $(".company_list").get(0);
				spinner.spin(target);
			},
			dataType: "json",
			success: function (result) {
				spinner.spin();
				if (result.resultCode == "0") {
					toastr.success(result.resultMsg);
					// relatedGroupInfo();
					navigatePersonMode1(tabType);// 重新刷新；

				} else {
					toastr.error(result.resultMsg);
				}
			},
			complete: function () {

			}
		});
	}
}

var navigatePersonMode1 = function (tabType) {

	var companyName = $("#rcompanyName").val();
	console.log("navigatePersonMode...... companyName ===" + companyName);
	var parameter = {
		"companyName": companyName,
	}

	$.ajax({
		url: ctx + "/addRelationCtl/getRelationPerson",
		type: "POST",
		data: parameter,
		dataType: "html",
		success: function (result) {
			$("#person_mode_div").html(result);
		},
		complete: function (request, textStatus) {
			bindPersonTabSwithcEvent();
			refleshTabDiv(tabType);
		}
	});
}

var refleshTabDiv = function (tabType) {
	if (tabType == '企业法人代表') {
		$("#relate_add_block .person_mode .filter_title_block").find(
			"div:eq(0)").click();
	} else if (tabType == '股东') {
		$("#relate_add_block .person_mode .filter_title_block").find(
			"div:eq(1)").click();
	} else if (tabType == '高管') {
		$("#relate_add_block .person_mode .filter_title_block").find(
			"div:eq(2)").click();
	} else {
		$("#relate_add_block .person_mode .filter_title_block").find(
			"div:eq(0)").click();
	}
}
function onSortEventClicked(sortAttr,isDesc){
	console.log("sortEvent clicked,sortAttr="+sortAttr+",isDesc="+isDesc);
	console.log($(this));
	$("#"+sortAttr+"Btn").attr("onClick","onSortEventClicked("+sortAttr+",false)");
	sortParam = {"sortAttr":sortAttr,"isDesc":isDesc};
	getMonitorCompanyList();
}