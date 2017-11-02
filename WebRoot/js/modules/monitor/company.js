var controller;
var beginDate;
var dueDate;
var monitorId = "";
var companyName = "";
var reportId = "";
var riskId = "";
var monitorDate = "";
var eventLevel = "0";

var dateCycle = "0";
var numCycle = "day";
var levelCycle = "day";
var groupList;
var today = "";
//分组列表开始下标
var beginIndex = 1;
//默认是第一个标签
var eventSubType = "01";
var infoShowDate = "";
//关联类型 0:企业 1:个人
var realType = "0";
//公司多选拼接字符串
var companyListString;
//个人多选拼接字符串
var personListString;
//删除分组的下表
var deleteGroupIndex = 0;
var groupId;
var groupListString;
var temp = null;

var typeListString = "";
var readStatus = "0";
var preReadStatus = "0";
var eventIdListString = "";
var eventIdArr = new Array();
var eventsList;
var eventIdStr = "";
//前一个事件类型
var preEventSubType = "";

var ignoreFlag = false;
//事件维度数量列表
var riskEventList = new Array();

var eventCategory = {};
var loading_page = true;
/**
 * 备注:1:最近七天 2:最近15天 3:自定义事件 0:全部
 */
var dateCycle;

var message_lap_deleteGroup;
//没有时间的维度
var noContentDateArray = ["06", "07", "10", "27", "28"];

/**
 * 页面加载完成后调用此函数
 */
$(document).ready(function () {

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
		"hideMethod": "fadeOut", //消失时的动画方式
		"preventDuplicates": true
	};

	message_lap_deleteGroup = new overlap({
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

	controller = new companyMonitorController();
	var customize = new companyMonitorCustomizeController();
	var analysis = new CompanyMonitorAnalysisController(customize);
	var rename=new CompanyRenameController();
	rename.load_company_detail_rename();
	//step1 init初始化信息
	initComInfo();
	//step2 事件绑定 
	dynamicBind();

	getRelationCompanyList();

	//更新导航菜单
	var deep = $("#deep").val();
	updateNavMenu(parseInt(deep), "监控企业详情", window.location.search);
});

var companyMonitorController = function () {

	var create_message_deleteReal = function () {
		var message_lap_deleteReal = new overlap({
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
					width: "516px",
					height: "200px",
					border: "16px solid rgba(0,0,0,0.15)"
				}
			}
		});
		return message_lap_deleteReal;
	}

	var delete_real_window_deal = function (message_lap_deleteReal) {

		$(".delete_block .delete").click(function () {
			if (realType == "0") {
				//公司
				//step1 收集公司信息
				var relationCompanyIds = getCheckedRelationCompanyIds();

				if (relationCompanyIds == "") {
					toastr.warning("请选择公司");
					return;
				}
			} else if (realType == "1") {
				//个人 收集个人信息
				var relationPersonIds = getCheckedRelationPersonIds();

				if (relationPersonIds == "") {
					toastr.warning("请选择个人");
					return;
				}
			}
			message_lap_deleteReal.show("#deleteReal");
		});

		$("#cancel_message3").click(function () {
			personListString = "";
			companyListString = "";
			message_lap_deleteReal.close();
		});

		$("#submit_delReal").click(function () {
			message_lap_deleteReal.close();
			deleteRelationCompanyOrPersonBatch();
		});

	}


    /**
     * 创建一个窗口
     */
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

    /**
     * 司法文书lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
	var create_sfws_lap = function () {
		var sfws_lap = new overlap({
			mask: {
				show: true
			},
			scroller: {
				lock: true,
				body_hidden: true,
				content_lock: false
			},
			position: {
				type: "center"
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
					width: "800px",
					height: "600px",
					border: "16px solid rgba(0,0,0,0.15)"
				}
			}
		});
		return sfws_lap;
	}

	//绑定每一个lap
	var sfws_details_show = function (_this, sfws_lap) {
		$(_this).click(function () {
			//打开司法文书的窗口
			var judgmentId = $(this).attr("judgmentId");
			var eventId = $(this).attr("eventId");
			getDetails4sfws(judgmentId, eventId, _this);
			sfws_lap.show(".sfws");
			return false;
		});

	}

    /**
     * 新闻舆情lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
	var create_news_lap = function () {
		var news_lap = new overlap({
			mask: {
				show: true
			},
			scroller: {
				lock: true,
				body_hidden: true,
				content_lock: false

			},
			position: {
				type: "center"
			},
			close: {
				show: true,
				type: "close_2",
				style: {

				},
				callBack: function () {
					console.log("媒体资讯详情关闭。。。");
					$("#details4News").html("");
					open_new_group = false;
				}
			},
			content: {
				style: {
					width: "800px",
					height: "600px",
					border: "16px solid rgba(0,0,0,0.15)"
				}
			}
		});
		return news_lap;
	}

	//绑定每一个lap
	var news_details_show = function (_this, news_lap) {
		$(_this).click(function () {
			//打开新闻舆情的窗口
			var eventId = $(this).attr("eventId");
			getDetails(eventId, _this);
			news_lap.show(".news");
			return false;
		});

	}

    /**
     * 失信信息lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
	var create_sx_lap = function () {
		var sx_lap = new overlap({
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
					width: "600px",
					height: "580px",
					border: "16px solid rgba(0,0,0,0.15)"
				}
			}
		});
		return sx_lap;
	}

	//绑定每一个lap
	var sx_details_show = function (_this, sx_lap) {
		$(_this).click(function () {
			//打开新闻舆情的窗口
			var eventId = $(this).attr("eventId");
			getDetails(eventId, _this);
			sx_lap.show(".shixin");
			return false;
		});

	}

    /**
     * 食药监lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
	var create_syj_lap = function () {
		var syj_lap = new overlap({
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
					width: "800px",
					height: "750px",
					border: "16px solid rgba(0,0,0,0.15)"
				}
			}
		});
		return syj_lap;
	}

	//绑定每一个lap
	var syj_details_show = function (_this, sjy_lap) {
		$(_this).click(function () {
			//打开新闻舆情的窗口
			var eventId = $(this).attr("eventId");
			getDetails(eventId, _this);
			sjy_lap.show("#shiyaojian");
			return false;
		});

	}

	//动态监控取消窗口事件
	var cancel_monitor = function (message_lap) {
		$("#cancelMonitor").click(function () {
			message_lap.show("#cancelMonitorWindow");
			return false;
		});
		$("#cancel_message").click(function () {
			message_lap.close();
		});

		$("#submit_message").click(function () {
			message_lap.close();
			//取消动态监控
			cancelMonitor();
		})
	}
	//  多余代码？
	var event_click_group_company = function () {
		var lap = new overlap({
			scroller: {
				lock: true
			},
			close: {
				callBack: function () {
					console.log("search company dialog closed");
					$('.company_list').html("");
					$('.search_result').html("");
					$("#Pagination4Search").html("");
					$("#searchInfo").val("");
					$("#personName").val("");
					$("#idNumber").val("");
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

		$(".group_edit_block #company_mode").click(function () {
			console.log("company_mode clicked");
			var _this = this;
			if (!$(_this).hasClass("selected")) {
				$(".group_edit_block .selected").removeClass("selected");
				$(_this).addClass("selected");
				$(".group_edit_block .person_mode").addClass("hidden");
				$(".group_edit_block .company_mode").removeClass("hidden");
			}
		})
		$(".group_edit_block #person_mode").click(function () {
			//        	$("#personName").val("");
			//        	$("#idNumber").val("");
			var _this = this;
			if (!$(_this).hasClass("selected")) {
				$(".group_edit_block .selected").removeClass("selected");
				$(_this).addClass("selected");
				$(".group_edit_block .company_mode").addClass("hidden");
				$(".group_edit_block .person_mode").removeClass("hidden");
			}
		})

		$(".group_edit_block .company_mode").find(".small_btn").click(function () {
			lap.close();
		})

		$(".group_edit_block .person_mode .btn").click(function () {
			lap.close();
		})
	}

	var event_click_more = function () {
		var lap = new overlap({
			scroller: {
				lock: true
			},
			close: {
				type: "close_2"
			},
			content: {
				style: {
					width: "600",
					height: "500"
				}
			}
		});
		$(".event_table .more_btn").click(function () {
			var index = $(this).attr("index");
			if (index == 4) {
				lap.reset({
					content: {
						style: {
							width: "600",
							height: "540",
							border: "16px solid rgba(0,0,0,0.15)"
						}
					}
				})
			} else {
				lap.reset({
					content: {
						style: {
							width: "800",
							height: "600",
							border: "0px"
						}
					}
				})
			}
		});
	}
	var event_click_mode = function () {

	}
	var event_click_analysis = function () {
		$("#show_more_analysis").click(function () {
			var value = $(this).find(".option_value").text();
			if (value == "详情") {
				var height = $(".risk_content .r_content").height() + $(".b_border_line").height();
				if (parseInt(height) < (100 + 42)) {
					height = height + 100;
				}
				$("#risk_analysis").animate({ "height": height });
				$(".b_border_line").addClass("hidden");
				$(this).find(".option_value").text("收起");
				$(this).find(".up_img").removeClass("hidden");
				$(this).find(".down_img").addClass("hidden");
			} else {
				var height = 100;
				$("#risk_analysis").animate({ "height": height }, 500, function () {
					$(".b_border_line").removeClass("hidden");
				});

				$(this).find(".option_value").text("详情");
				$(this).find(".up_img").addClass("hidden");
				$(this).find(".down_img").removeClass("hidden");
			}
		});
		$("#show_more_table").click(function () {
			var value = $(this).find(".option_value").text();
			if (value == "展开") {

				openEventsListPanel();
			} else {
				closeEventsListPanel();
			}
		})
	}


	var Modules = {
		CompanyUsedName: function () {
			return {
				message_company_used_name_message: function () {
					controller.lap_list.RelateOverlapInDetail.show();
				},
				show_used_message_overlap: function (name, message) {
					controller.lap_list.RelateOverlapInDetail.close_quick();
					OverlapList.MessageInfoOverlap.show(PageConfig.MessageConfig.CompanyUsedNameMessage.name, message, "content_info_type_2");
					$("#MessageInfoOverlap .company_message_used_name").text(name);
				}
			}
		}()
	}

	var OverlapList = {
		MessageInfoOverlap: null,
	}

	var PageConfig = {
		MessageConfig: {
			CompanyUsedNameMessage: {
				name: "CompanyUsedNameMessage",
				message: "该企业已更名为：<span class='company_message_used_name' style='font-size:14px;color:#333333;font-weight:700;'>曾用名</span>",
				callback: Modules.CompanyUsedName.message_company_used_name_message
			}
		}
	}

	var Loaders = {
		load_overlaps: function () {
			OverlapList.MessageInfoOverlap = new MessageInfoOverlap();
			return this;
		},
		init_message_overlap: function () {
			var message = PageConfig.MessageConfig;
			var info = OverlapList.MessageInfoOverlap;
			info.push(message.CompanyUsedNameMessage.name, message.CompanyUsedNameMessage.message, message.CompanyUsedNameMessage.callback)
			return this;
		}
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
			$(".detail_content_block").bind('mousewheel', scroll_move);
			$(".detail_content_block").bind('DOMMouseScroll', scroll_move);
			$(".detail_block .table_value").bind('mousewheel', scroll_move);
			$(".detail_block .table_value").bind('DOMMouseScroll', scroll_move);
			return this;
		}
	}


	function init() {
		Loaders
			.load_overlaps()
			.init_message_overlap();
		Binders.bind_overlap_event_content_scroll();
	}

	var companyMonitorController = function () {
		this.lap_list = {};
		this.echart_core = new echartController();
		init();
		this.message_lap = create_message_lap();
		//创建司法文书lap
		this.sfws_lap = create_sfws_lap();
		//创建新闻舆情lap
		this.news_lap = create_news_lap();
		//失信信息lap
		this.sx_lap = create_sx_lap();
		//食药监lap
		this.syj_lap = create_syj_lap();
		//删除关联lap
		this.deleteReal_lap = create_message_deleteReal();
		// 提示关联公司；
		this.message_lap4 = create_message_lap4();
		// 提示关联关系人；
		this.message_lap5 = create_message_lap5();

		this.bind_event();
		this.load_page();
	}
	var remind_history_btn = function (call_back) {
		var lap = new overlap({
			mask: {
				close: false
			},
			close: { show: false },
			scroller: {
				lock: true
			},
			position: {
				type: "absolute",
				x_pos: $("#history_btn").offset().left - 89,
				y_pos: $("#history_btn").offset().top - 165
			},
			content: {
				style: {
					width: "0px",
					height: "0px"
				}
			}
		});
		$("html,body").animate({ scrollTop: 0 }, 1000);//1000是ms,也可以用slow代替
		$(".remind_overlap .remind_send_block").hover(function () {
			$(this).find(".no_hover_img").addClass("hidden");
			$(this).find(".hover_img").removeClass("hidden");
		}, function () {
			$(this).find(".hover_img").addClass("hidden");
			$(this).find(".no_hover_img").removeClass("hidden");
		})
		$(".remind_overlap .remind_close_block").click(function () {
			lap.close();
		})
		$(".remind_overlap .remind_send_block").click(function () {
			if (typeof (call_back) == "function") {
				call_back(lap);
			} else {
				lap.close();
			}
		})
		lap.show("#remind_overlap");
		console.log(lap.opts);
	}
	var event_hover_btn = function () {
		$(".body_block_title .title_btn .img_btn_2").hover(function () {
			$(this).find(".img").addClass("hidden");
			$(this).find(".img_hover").removeClass("hidden");
		}, function () {
			$(this).find(".img").removeClass("hidden");
			$(this).find(".img_hover").addClass("hidden");
		})
	}

	var event_company_name = function () {
		var moved = false;
		var position = $(".monitor_company_title").offset().top;
		var position_left = $(".monitor_company_title").offset().left;
		var width = $(".monitor_company_title").width();
		var height = $(".monitor_company_title").height();
		$(document).scroll(function (value) {
			var top = $(document).scrollTop() + 82 + 57;
			if (top >= position) {
				if (moved) {
					return;
				}
				moved = true;
				//$(".company_modules_blodk").css({"height":$(".company_modules").height()});
				$(".monitor_company_title_content").stop().css({ "position": "fixed", "top": "80px", "width": width, "border-top": "1px solid #d7d7d7" });
				$(".monitor_company_title_content").addClass("head_shadow")//.find(".modules_sub_list").css({"border":"1px solid #ffffff"});

			} else {
				moved = false;
				$(".monitor_company_title_content").css({ "position": "absolute", "top": "137px", "border-top": "0px" });
				//$(".company_modules .modules_sub_list").removeClass("head_shadow");
			}
		})
	}

	companyMonitorController.prototype = {
		load_page: function () {
			//            this.echart_core.monitor_events_change("monitor_events_change");
			//            this.echart_core.monitor_level_change("monitor_level_change");
			//            this.echart_core.monitor_company_event("monitor_company_event");
			//初始化新增绑定页面；
			init_lap_in_detail(this.lap_list);
		},
		bind_event: function () {
			event_hover_btn();
			event_click_mode();
			//event_click_group_company();
			event_click_more();
			event_click_analysis();
			delete_real_window_deal(this.deleteReal_lap);
			cancel_monitor(this.message_lap);
			event_company_name();
			// 公司详情页面显示关系人列表；
			event_relate_block_inCompanyDetail();
		},
		sfws_details_bind: function (_this) {
			sfws_details_show(_this, this.sfws_lap);
		},
		news_details_bind: function (_this) {
			news_details_show(_this, this.news_lap);
		},
		sx_details_bind: function (_this) {
			sx_details_show(_this, this.sx_lap);
		},
		syj_details_bind: function (_this) {
			syj_details_show(_this, this.syj_lap);
		},
		new_features_remind: function (call_back) {
			remind_history_btn(call_back);
		},
		show_used_message: function (name, message) {
			Modules.CompanyUsedName.show_used_message_overlap(name, message);
		}
	};
	companyMonitorController.prototype.constructor = companyMonitorController;
	return companyMonitorController;
}();

var page_resizeContent = function () {
	if ('object' == typeof controller) {
		controller.load_page();
	}
}

function closeEventsListPanel() {
	/*	 $(".monitor_event_table_block .event_table").addClass("hidden");
		 $(".monitor_event_table_block .page_block").addClass("hidden");*/
	var page_height = $(".monitor_event_table_block .page_block").outerHeight(true) + 6;
	if (page_height >= 60) {
		page_height = 60;
		$(".company_monitor").css("padding-bottom", "20px");
		$(".company_monitor .monitor_event_block").css("margin-bottom", "20px");
		$(".company_monitor .monitor_event_block .monitor_event_table_block").css("padding-bottom", "20px");
	} else {
		page_height = 0;

	}
	$(".event_table_block").stop().animate({ height: 0 }, 500, function () {

	})
	$("#show_more_table").find(".option_value").text("展开");
	$("#show_more_table").find(".up_img").addClass("hidden");
	$("#show_more_table").find(".down_img").removeClass("hidden");
}
function openEventsListPanel() {
	/*	$(".monitor_event_table_block .event_table").removeClass("hidden");
		$(".monitor_event_table_block .page_block").removeClass("hidden");*/
	var table_height = $(".monitor_event_table_block .event_table").height();
	var page_height = $(".monitor_event_table_block .page_block").outerHeight(true) + 6;

	if (page_height >= 60) {
		page_height = 60;
		$(".company_monitor").css("padding-bottom", "0px");
		$(".company_monitor .monitor_event_block").css("margin-bottom", "0px");
		$(".company_monitor .monitor_event_block .monitor_event_table_block").css("padding-bottom", "0px");
	} else {
		page_height = 0;
		$(".company_monitor").css("padding-bottom", "20px");
		$(".company_monitor .monitor_event_block").css("margin-bottom", "20px");
		$(".company_monitor .monitor_event_block .monitor_event_table_block").css("padding-bottom", "20px");
	}
	$(".event_table_block").stop().animate({ height: table_height + page_height }, 500, function () {

	})
	$("#show_more_table").find(".option_value").text("收起");
	$("#show_more_table").find(".up_img").removeClass("hidden");
	$("#show_more_table").find(".down_img").addClass("hidden");
}
/**
 * 初始化页面信息
 */
function initComInfo() {

	//初始化界面的属性参数
	initParams();
	//step1  展示企业风险标签
	monitorComRiskInfo();
	//step3查询默认时间信息
	eventsDetailsListDeal();
	//initCheckBox 已读未读
	initCheckBox();
	//step4 事件数量统计曲线图
	//eventNumChangeShow();
	//step5 事件等级统计曲线图
	//eventLevelChangeShow();
}

//事件的动态绑定
function dynamicBind() {
	//事件查询类型处理
	eventsQueryTypeDeal();
	//统计图年月日绑定事件
	//cycleDeal();
	//关联个人关联
	getRelationCompanyAndPersonList();
	//取消删除分组
	cancelTips();
	// 绑定搜索回车事件
	//bindEnterSearch();
	//绑定搜索回车事件  在添加关系的时候绑定事件；
	//bindEnterSearchInDetail();
	//历史记录
	bindHistoryInfo();
	//法人代表信息
	bindLegalPerson();

}

/**
 * 展示企业风险标签 风险状况图 雷达图
 */
function monitorComRiskInfo() {

	var parameter = {
		"monitorId": monitorId
	}

	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx + "/monitorCompany/getMonitorCompany",
		data: parameter,
		success: function (result) {
			//公司标签切割展示 整体展示 建议展示 风险状况展示
			var label = result.riskLabel;

			//建议
			var suggestion = result.riskResult;

			//风险状况
			var riskGrade = result.riskGrade;

			if ((label == "无" || label.trim() == "") && $("#companyTagList .span").length > 0) {

			} else {
				$(".company_span #companyTagList .span:contains('无')").remove();
				var arry = label.split(",");
				for (var i = arry.length - 1; i >= 0; i--) {
					var index = (i + 1) % 4;
					if (index == 0) {
						index = 4;
					}
					if ((arry[i] == "失信") && $(".company_span #companyTagList .span:contains('失信')").length > 0) {
						continue;
					}
					$("#companyTagList").prepend("<div class='span span_color_" + index + " inline_div'>" + arry[i] + "</div>");
				}
				$(".company_span #companyTagList .span:contains('失信')").addClass("blacklist_span").addClass("blacklist_span_color_3");
			}

			$("#companyRiskTags").html(result.analysis);

			//建议
			if ($("#suggestion").text().trim().length <= 0) {
				$("#suggestion").html(suggestion);
			}

			var re_width_risk_point_block = function () {
				$(".risk_points").each(function () {
					var width_total = $(this).parents(".risk_point_block").width();
					var width_company = $(this).parents(".risk_point_block").find(".company_name").width();
					var width_index = $(this).parents(".risk_point_block").find(".index").width() | 0;
					$(this).css({ "width": width_total - width_company - width_index - 20 - 10 });
				})
			}

			controller.echart_core.monitor_company_event("monitor_company_event", result);
		}
	});
}


/**
 * 公司风险事件数量统计展示图
 */
function eventNumChangeShow() {

	var parameter = {
		"reportId": $("#reportId").val(),
		"companyName": encodeURIComponent(companyName),
		"numCycle": numCycle
	}

	//根据分组Id找到对应的关联公司的信息
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx + "/monitorComRisk/eventNumChangeShow.do",
		data: parameter,
		success: function (result) {
			//风险事件统计图
			result.yName = "事件数量";
			controller.echart_core.monitor_events_change4Company("monitor_events_change", result);

		}
	});

}

/**
 * 公司风险事件等级统计展示图
 */
function eventLevelChangeShow() {

	var parameter = {
		"companyName": encodeURIComponent(companyName),
		"levelCycle": levelCycle
	}

	//根据分组Id找到对应的关联公司的信息
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx + "/monitorComRisk/eventLevelChangeShow.do",
		data: parameter,
		success: function (result) {
			//风险事件统计图
			controller.echart_core.monitor_level_change2("monitor_level_change", result);
		}
	});

}

/**
 * 查看事件详情动态绑定
 */
function eventsQueryTypeDeal() {

	$(".event_value").click(function () {



		//		if(temp!=null){
		//说明不是第一次点击
		//			var value=$(temp).find(".eventNo").text();
		//			if("0"!=value){
		//				temp.css("color","#2ea7e0");
		//			}
		//		}
		//清空背景色
		var obj = $(this).parent().children();
		//obj.css("color","#666666");
		obj.removeClass("selected");
		//重新加背景色
		$(this).addClass("selected");
		//字体设置为白色
		//        var value=$(this).find(".eventNo").text();
		//		if("0"!=value){
		//			 $(this).css("color","#ffffff");
		//		}
		$(this).css("color", "#ffffff");
		console.log("preEventSubType=" + preEventSubType);
		//赋值处理
		eventSubType = $(this).attr("index");
		if (temp && preEventSubType != eventSubType) {
			if (eventCategory.hasOwnProperty(preEventSubType)) {
				temp.css("color", "#2ea7e0");
			} else {
				temp.css("color", "#666666");
			}
		}
		if (eventSubType == "24" || eventSubType == "25") {
			$(".event_selector_serious_btn").addClass("hidden");
			$(".event_selector_abnormal_btn").addClass("hidden");
			$(".event_selector_general_btn").addClass("hidden");

		} else {
			$(".event_selector_serious_btn").removeClass("hidden");
			$(".event_selector_abnormal_btn").removeClass("hidden");
			$(".event_selector_general_btn").removeClass("hidden");
		}

		if (noContentDateArray.indexOf(eventSubType) >= 0) {
			$("#filterTimeDiv").addClass("hidden");
		} else {
			$("#filterTimeDiv").removeClass("hidden");
		}

		temp = $(this);
		eventsDetailsListDeal();
		openEventsListPanel();
	});

}

/**
 * 开始日期选择触发事件:周期方式设置为:3 保存对应日期  发送查询请求
 */
function beginDateDeal(date) {

	beginDate = date;

	eventsDetailsListDeal();
}

/**
 * 结束日期选择触发事件:周期方式设置为:3 保存对应日期  发送查询请求
 */
function dueDateDeal(date) {

	dueDate = date;
	eventsDetailsListDeal();
}

/**
 * 查看动态监控公司事件信息 共性代码封装
 */
function eventsDetailsListDeal() {


	//初次分页需要做四件事 A 收集之前的信息  B更新已读信息 C查询 D清空列表
	//A 查询之前对已读信息的收集 
	//已读信息分为两种 一种是有详情 一种没有详情 a 区分事件类型 b 判断是否点击了忽略全部按钮

	if (!ignoreFlag == true) {
		readEventsTotalDeal();
	}

	//发送异步请求查询
	var parameter = {
		"eventSubType": eventSubType,
		"companyName": encodeURIComponent(companyName),
		"beginDate": beginDate,
		"dueDate": dueDate,
		"infoShowDate": $("#infoShowDate").val(),
		"monitorDate": monitorDate,
		"typeListString": typeListString,
		"eventIdListStr": eventIdListString,
		"monitorId": monitorId,
		"riskId": riskId,
		"preEventSubType": preEventSubType,
		"readStatus": readStatus
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorComRisk/monitorComEventsDetails.do",
		data: parameter,
		beforeSend: function () {
			//异步请求时spinner出现
			var target = $(".monitor_events").get(0);
			spinner.spin(target);
		},
		success: function (result) {


			//D 清空信息
			eventIdArr = new Array();
			eventIdListString = "";
			preReadStatus = readStatus;

			//还原忽略全部标志位
			ignoreFlag = false;

			spinner.spin();
			$("#detailsTable").html(result);//清除之前的数据

			$("#Pagination4Details").html("");
			console.log("recordNum=" + $("#totalNum4Details").val());
			//分页展示之后需要重新绑定事件
			var arr = $(".sfwsDetails");
			for (var i = 0; i < arr.length; i++) {
				controller.sfws_details_bind($(arr[i]));
			}

			//分页展示之后需要重新绑定事件
			var arr = $(".newsDetails");
			for (var i = 0; i < arr.length; i++) {
				controller.news_details_bind($(arr[i]));
			}
			//分页展示之后需要重新绑定事件
			var sxAarr = $(".sxDetails");
			for (var i = 0; i < sxAarr.length; i++) {
				controller.sx_details_bind($(sxAarr[i]));
			}

			//分页展示之后需要重新绑定事件
			var syjAarr = $(".syjDetails");
			for (var i = 0; i < syjAarr.length; i++) {
				controller.syj_details_bind($(syjAarr[i]));
			}
			//记录当前页的所有eventId
			eventIdStr = $("#eventIdStr").val();
			//获取更新数量
			var updateNum = $("#updateNum").val();

			getMonitorComRiskEventNum();
			/*if(updateNum!=0){
					//重新获取各分类未读事件的数量，并判断是否显示红点
					
					
				var arr=$(".event_value");
				for(var i=0;i<riskEventList.length;i++){
					//获取子类型
					if(preEventSubType==riskEventList[i].eventSubType){
						if(riskEventList[i].eventNum-updateNum<=0){
							for(var j=0;j<arr.length;j++){
								if(preEventSubType==$(arr[j]).attr("index")){
									$(arr[j]).removeClass("unread_event_value");
						
									break;
								}
							}
						}
						//同时需要更新数量
						riskEventList[i].eventNum=riskEventList[i].eventNum-updateNum;
					}
				}	
			}*/

			//查询之后需要备份当前的事件类型 
			preEventSubType = eventSubType;
			//已读未读颜色处理
			var arr = $("#detailsTable").find(".tr_data");
			if (arr.length != 0) {
				for (var i = 0; i < arr.length; i++) {
					var obj = arr.get(i);
					if ($(obj).attr("status") == "1") {
						//已读
						$(obj).addClass("read_row");
						var tempArr = $(obj).find("td");
						$(tempArr[1]).addClass("new_event_read");
						if (eventSubType == "12") {
							//食药监
							$(tempArr[3]).addClass("read_td");
						} else if (eventSubType == "18" || eventSubType == "19" || eventSubType == "20") {
							//裁判文书
							$(tempArr[5]).addClass("read_td");
						} else if (eventSubType == "22") {
							$(tempArr[6]).addClass("read_td");
						} else if (eventSubType == "24" || eventSubType == "25") {
							//新闻舆情
							$(tempArr[1]).addClass("read_td");
						}
					} else {

						if (eventSubType == "12" ||
							eventSubType == "18" || eventSubType == "19" || eventSubType == "20" || eventSubType == "22" ||
							eventSubType == "24" || eventSubType == "25") {
							//未读
							var tempArr = $(obj).find("td");
							//$(tempArr[1]).addClass("new_event");
						} else {
							//如果是一般事件没有详情的那种类型需要将其也设置为灰色 同事讲eveId放进列表
							//设置为已读标志
							$(obj).addClass("read_row");

							var curIdArr = new Array();
							curIdArr = eventIdStr.split(","); //字符分割 
							eveId = curIdArr[i];
							if ("" == eventIdListString) {
								eventIdListString = eveId;
							} else {
								eventIdListString = eventIdListString + "," + eveId;
							}

						}
					}
				}

			}

			/*if(eventSubType=="12" ||
					eventSubType=="18" ||eventSubType=="19"||eventSubType=="20"||eventSubType=="22"||
					eventSubType=="24"||eventSubType=="25"){
				//事件类型是这些的时候才会显示忽略全部按钮
				$("#ignoreAll").removeClass("hidden");
			}else{
				$("#ignoreAll").addClass("hidden");
			}*/


			if ($("#totalNum4Details").val() > 0) {
				console.log("init page nav");
				$("#Pagination4Details").pagination($("#totalNum4Details").val(), {
					prev_text: "上一页",
					next_text: "下一页",
					items_per_page: 6,
					num_edge_entries: 3,
					num_display_entries: 10,
					//回调 
					callback: pageselectCallbackForEventsDetailsList
				});
			}
			if (loading_page) {
				loading_page = false;
			} else {
				openEventsListPanel();
			}

		}
	});

}

//分页回调
function pageselectCallbackForEventsDetailsList(new_page_index, pagination_container) {


	//二次分页需要做两件事情 A 收集信息 B 查询
	//分页的时候需要做记录 统计当前已读的事件 统计方式有两种 一种没有详情的直接统计页数 第二种 有详情的不做处理
	if (!ignoreFlag == true) {
		readEventsTotalDeal();
	}

	var currentPageNo = parseInt(new_page_index) + 1;
	var parameter = {
		"eventSubType": eventSubType,
		"companyName": encodeURIComponent(companyName),
		"beginDate": beginDate,
		"infoShowDate": $("#infoShowDate").val(),
		"dueDate": dueDate,
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(6),
		"monitorDate": monitorDate,
		"typeListString": typeListString,
		"monitorId": monitorId,
		"readStatus": readStatus
	}
	console.log("param=");
	console.log(parameter);
	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorComRisk/monitorComEventsDetails.do",
		data: parameter,
		beforeSend: function () {
			//异步请求时spinner出现
			var target = $(".monitor_events").get(0);
			spinner.spin(target);
		},
		success: function (result) {


			console.log(result);
			spinner.spin();
			$("#detailsTable").html(result);//清除之前的数据
			//分页展示之后需要重新绑定事件
			var arr = $(".sfwsDetails");
			for (var i = 0; i < arr.length; i++) {
				controller.sfws_details_bind($(arr[i]));
			}
			//分页展示之后需要重新绑定事件
			var newsArr = $(".newsDetails");
			for (var i = 0; i < newsArr.length; i++) {
				controller.news_details_bind($(newsArr[i]));
			}
			//分页展示之后需要重新绑定事件
			var sxAarr = $(".sxDetails");
			for (var i = 0; i < sxAarr.length; i++) {
				controller.sx_details_bind($(sxAarr[i]));
			}
			//分页展示之后需要重新绑定事件
			var syjAarr = $(".syjDetails");
			for (var i = 0; i < syjAarr.length; i++) {
				controller.syj_details_bind($(syjAarr[i]));
			}
			//记录当前页的所有eventId
			eventIdStr = $("#eventIdStr").val();
			//还原忽略全部标志位
			ignoreFlag = false;
			//$("#dataType="${searchResult.dataType}" class="tr_data"")
			//（0：未读，1：已读）
			var arr = $("#detailsTable").find(".tr_data");

			var curIdArr = new Array();
			curIdArr = eventIdStr.split(","); //当前页的eventId

			var arr4Id = new Array(); //保存的待更新的已经读取的事件Id
			arr4Id = eventIdListString.split(","); //字符分割 

			if (arr.length != 0) {

				for (var i = 0; i < arr.length; i++) {
					var obj = arr.get(i);
					//已经读取
					if ($(obj).attr("status") == "1") {

						$(obj).addClass("read_row");
						var tempArr = $(obj).find("td");
						$(tempArr[1]).addClass("new_event_read");
						if (eventSubType == "12") {
							//食药监
							$(tempArr[3]).addClass("read_td");
						} else if (eventSubType == "18" || eventSubType == "19" || eventSubType == "20") {
							//裁判文书
							$(tempArr[5]).addClass("read_td");
						} else if (eventSubType == "22") {
							$(tempArr[6]).addClass("read_td");
						} else if (eventSubType == "24" || eventSubType == "25") {
							//新闻舆情
							$(tempArr[1]).addClass("read_td");
						}
					} else {
						//最新事件 需要去判断在分页的途中是否已经加入了统计Id字符串中 如果加入了颜色照样要设置为已经读取

						var curIdArr = new Array();
						curIdArr = eventIdStr.split(","); //当前页保存的eventId
						var checkFlag = true;//判断是否设置为new的标志
						for (var j = 0; j < arr4Id.length; j++) {
							if (arr4Id[j] == curIdArr[i]) {
								//已读
								$(obj).addClass("read_row");
								checkFlag = false;
								var tempArr = $(obj).find("td");
								$(tempArr[1]).addClass("new_event_read");
								if (eventSubType == "12") {
									//食药监
									$(tempArr[3]).addClass("read_td");
								} else if (eventSubType == "18" || eventSubType == "19" || eventSubType == "20") {
									//裁判文书
									$(tempArr[5]).addClass("read_td");
								} else if (eventSubType == "22") {
									$(tempArr[6]).addClass("read_td");
								} else if (eventSubType == "24" || eventSubType == "25") {
									//新闻舆情
									$(tempArr[1]).addClass("read_td");
								}
							}

						}
						if (checkFlag == true) {
							//如果是
							if (eventSubType == "12" ||
								eventSubType == "18" || eventSubType == "19" || eventSubType == "20" || eventSubType == "22" ||
								eventSubType == "24" || eventSubType == "25") {
								//未读
								var tempArr = $(obj).find("td");
								//$(tempArr[1]).addClass("new_event");
							} else {
								//如果是一般事件没有详情的那种类型需要将其也设置为灰色 同事讲eveId放进列表
								//设置为已读标志
								$(obj).addClass("read_row");

								var curIdArr = new Array();
								curIdArr = eventIdStr.split(","); //字符分割 
								eveId = curIdArr[i];
								if ("" == eventIdListString) {
									eventIdListString = eveId;
								} else {
									eventIdListString = eventIdListString + "," + eveId;
								}

							}
						}
					}
				}

			}


			openEventsListPanel();
		}
	});
}

/**
 * 已读事件的统计
 */
function readEventsTotalDeal() {
	//统计标志位
	var dealFlag = false;

	if (preEventSubType == "12" ||
		preEventSubType == "18" || preEventSubType == "19" || preEventSubType == "20" || preEventSubType == "22" ||
		preEventSubType == "24" || preEventSubType == "25") {
		if (ignoreFlag == true) {
			dealFlag = true;
		}
	} else {
		dealFlag = true;
	}

	var arr4Id = new Array(); //定义一数组 
	arr4Id = eventIdListString.split(","); //字符分割 

	//获取当前页面所有的eventId
	var curIdArr = new Array();
	curIdArr = eventIdStr.split(","); //字符分割 

	var arr = $("#detailsTable").find(".tr_data");

	if (dealFlag == true) {

		//有详情的维度处理
		for (var i = 0; i < arr.length; i++) {
			var obj = $(arr[i]);
			if (!obj.hasClass("read_row")) {
				eveId = curIdArr[i];
				if (ignoreFlag == true) {
					var addFlag = true;
					//如果是忽略全部,需要检测是否有重复的记录
					for (j = 0; j < arr4Id.length; j++) {
						if (arr4Id[j] == eveId) {
							addFlag = false;
							break;
						}
					}
					if (addFlag == true) {
						//没有重复的直接添加
						if ("" == eventIdListString) {
							eventIdListString = eveId;
						} else {
							eventIdListString = eventIdListString + "," + eveId;
						}
					}

				} else {
					//没有重复的直接添加
					if ("" == eventIdListString) {
						eventIdListString = eveId;
					} else {
						eventIdListString = eventIdListString + "," + eveId;
					}
				}

			}
		}

	}
}



/**
 * 动态绑定事件展示图周期
 */
function cycleDeal() {

	$(".select").click(function () {

		var text = $(this).parents(".width_50").children(".body_block_title").text().trim();

		if (text == "风险事件") {

			if ("按日" == $(this).text().trim()) {
				$(this).attr("class", "inline_div day_select select selected");
				$(this).next().attr("class", "inline_div week_select select")
				$(this).next().next().attr("class", "inline_div month_select select");
				numCycle = "day";

			} else if ("按周" == $(this).text().trim()) {
				$(this).prev().attr("class", "inline_div day_select select");
				$(this).attr("class", "inline_div week_select select selected");
				$(this).next().attr("class", "inline_div month_select select")
				numCycle = "week";

			} else if ("按月" == $(this).text().trim()) {
				$(this).prev().prev().attr("class", "inline_div day_select select");
				$(this).prev().attr("class", "inline_div week_select select");
				$(this).attr("class", "inline_div month_select select selected");
				numCycle = "month";
			}
			eventNumChangeShow();

		} else {

			if ("按日" == $(this).text().trim()) {
				$(this).attr("class", "inline_div day_select select selected");
				$(this).next().attr("class", "inline_div week_select select")
				$(this).next().next().attr("class", "inline_div month_select select");
				levelCycle = "day";

			} else if ("按周" == $(this).text().trim()) {
				$(this).prev().attr("class", "inline_div day_select select");
				$(this).attr("class", "inline_div week_select select selected");
				$(this).next().attr("class", "inline_div month_select select")
				levelCycle = "week";

			} else if ("按月" == $(this).text().trim()) {
				$(this).prev().prev().attr("class", "inline_div day_select select");
				$(this).prev().attr("class", "inline_div week_select select");
				$(this).attr("class", "inline_div month_select select selected");
				levelCycle = "month";
			}
			eventLevelChangeShow();

		}

	});
}

function openDeleteGroupWindows(index) {
	message_lap_deleteGroup.show("#deleteGroup");
	deleteGroupIndex = index;
}

function cancelTips() {
	$("#cancel_message2").click(function () {
		message_lap_deleteGroup.close();
	});

}

//关系企业和关系自然人列表
function getRelationCompanyAndPersonList() {

	$(".inline_div .tab").click(function () {
		var value = $(this).text();
		$(this).parents(".tab_block").children().attr("class", "inline_div tab");
		$(this).addClass("selected");
		//选中按钮都去除
		if (value.trim() == "企业") {
			realType = "0";
			$(".person_table").hide();
			$("#Pagination4Person").hide();
			$(".company_table").show();
			$("#Pagination4Company").show();

			//获取关系企业分页列表
			getRelationCompanyList();
		} else {
			realType = "1";
			$(".company_table").hide();
			$("#Pagination4Company").hide();
			$(".person_table").show();
			$("#Pagination4Person").show();

			//获取关系自然人信息列表
			getRelationPersonList();
		}

	});

}

//获取关系企业分页列表
function getRelationCompanyList() {

	var parameter = {
		"monitorId": monitorId
	}

	$("#Searchresult4Company").html("");//清除之前的数据

	//根据分组Id找到对应的关联公司风险评估信息
	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorRelationCompany/getMonitorRelationCompanyPage",
		data: parameter,
		success: function (result) {
			//获取结果进行分页展示 
			$("#Searchresult4Company").html(result);//清除之前的数据
			$("#Pagination4Company").html("");
			if ($("#totalNum4Company").val() > 0) {
				$("#Pagination4Company").pagination($("#totalNum4Company").val(), {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					num_display_entries: 10,
					items_per_page: 6,
					//回调 
					callback: pageselectCallbackForRealComRiskList
				});
			}
		}
	});
}

//关系企业分页回调
function pageselectCallbackForRealComRiskList(new_page_index, pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;

	var parameter = {
		"monitorId": monitorId,
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(6)
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorRelationCompany/getMonitorRelationCompanyPage",
		data: parameter,
		success: function (result) {
			$("#Searchresult4Company").html(result);//清除之前的数据
		}
	});
}

//获取关系自然人信息列表
function getRelationPersonList() {

	//发送异步请求查询
	var parameter = {
		"monitorId": monitorId
	}

	$("#Searchresult4Person").html("");

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorRelationPerson/getMonitorRelationPersonPage",
		data: parameter,
		success: function (result) {
			$("#Searchresult4Person").html(result);//清除之前的数据
			$("#Pagination4Person").html("");

			if ($("#totalNum4Person").val() > 0) {
				$("#Pagination4Person").pagination($("#totalNum4Person").val(), {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					num_display_entries: 10,
					items_per_page: 6,
					//回调 
					callback: pageselectCallbackForRealPersonRiskList
				});
			}
		}
	});
}

//关系自然人列表分页回调
function pageselectCallbackForRealPersonRiskList(new_page_index, pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;

	var parameter = {
		"monitorId": monitorId,
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(6)
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorRelationPerson/getMonitorRelationPersonPage",
		data: parameter,
		success: function (result) {
			$("#Searchresult4Person").html(result);//清除之前的数据
		}
	});
}


//查看关系企业详情
function viewRelationCompanyDetail(id) {
	var deep = $("#deep").val();

	//	var params = {
	//        "id" : id,
	//        "deep" : (parseInt(deep)+1)
	//    };
	//
	//    httpPost(ctx + "/monitorRelationCompany/viewMonitorRelationCompanyDetail", params);

	var parameter = {
		"monitorId": $("#monitorId").val(),
		"deep": deep
	}

	// 更新导航菜单
	updateNavMenu(parseInt(deep), "监控企业详情", parameter);

	window.location.href = ctx + "/monitorRelationCompany/viewMonitorRelationCompanyDetail?id=" + id + "&deep=" + (parseInt(deep) + 1);
}

//查看今日加入的关系企业详情
function viewTodayMonitorRelationCompanyDetail(companyName) {
	var deep = $("#deep").val();

	//	var params = {
	//        "companyName" : companyName
	//    };
	//
	//    httpPost(ctx + "/monitorRelationCompany/viewMonitorRelationCompanySimpleDetail", params);

	var parameter = {
		"monitorId": $("#monitorId").val(),
		"deep": deep
	}

	// 更新导航菜单
	updateNavMenu(parseInt(deep), "监控企业详情", parameter);

	window.location.href = ctx + "/monitorRelationCompany/viewMonitorRelationCompanySimpleDetail?companyName=" + companyName + "&deep=" + (parseInt(deep) + 1);
}

//跳转关联个人详情
function viewRelationPersonDetail(id) {
	var deep = $("#deep").val();

	//	var params = {
	//        "id" : id,
	//        "deep" : (parseInt(deep)+1)
	//    };
	//
	//    httpPost(ctx + "/monitorRelationPerson/viewMonitorRelationPersonDetail", params);

	var parameter = {
		"monitorId": $("#monitorId").val(),
		"deep": deep
	}

	// 更新导航菜单
	updateNavMenu(parseInt(deep), "监控企业详情", parameter);

	window.location.href = ctx + "/monitorRelationPerson/viewMonitorRelationPersonDetail?id=" + id + "&deep=" + (parseInt(deep) + 1);
}

//查看今日加入的关系自然人详情
function viewTodayMonitorRelationPersonDetail() {
	toastr.warning("关系自然人风险信息将于次日展示！");
	return;
}

/**
 * 公司checkBox全选按钮
 */
function selectAll4Company() {

	if ($(".selectAll4Company").prop("checked")) {
		$("input[name='selectOne4Company']").prop("checked", true);
	} else {
		$("input[name='selectOne4Company']").prop("checked", false);
	}


}
/**
 * 个人checkBox全选按钮
 */
function selectAll4Person() {

	var arrChk = $(".chkList4Person");

	if ($(".selectAll4Person").prop("checked")) {
		$("input[name='selectOne4Person']").prop("checked", true);
	} else {
		$("input[name='selectOne4Person']").prop("checked", false);
	}

}

/**
 * 删除关联 公司以及个人关联
 */
function deleteReal() {

	//公司区分
	//delete_block

}
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
/**
 * 检索公司信息 调用大数据接口查询公司相关信息
 */
function search4Company() {
	//将个人加入对应分组
	var para = $.trim($("#rsearchInfo").val());
	var companyName = $("#rcompanyName").val();
	console.log("para=====" + para);
	var parameter = {
		"keyword": para,
		"companyName": companyName,
		"monitorId": monitorId
	}

	if (para != "" && para != undefined) {
		$.ajax({
			type: "post",
			dataType: "html",
			url: ctx + "/addRelationCtl/search4Company1.do",
			data: parameter,
			beforeSend: function () {
				//异步请求时spinner出现
				var target = $(".company_list").get(0);
				spinner.spin(target);
			},
			success: function (result) {

				console.log("search company4 callback...");
				spinner.spin();
				//追加完成之后重新显示页面
				$('#searchResultDiv').show();
				$('#searchResultDiv').html(result);
				$("#tabComDiv").hide();

				// 隐藏企业股东
				/*$("#company_mode_div").hide();
				$("#company_mode_div").html("");*/

				$("#stockHolderCompany").hide();
				$("#stockHolderCompany").html("");
				$('#PageStockHolderCompanyDiv').html("");
				// 隐藏投资；
				$("#investSearchResultDiv").hide();
				$("#investSearchResultDiv").html("");
				$('#PaginationInvestmentCompany').html("");

				//$("#stockHolderCompany").hide();
				//重新绑定事件

				$("#Pagination4Search").html("");
				//展示分页标签
				if ($("#totalNum4Search").val() > 0) {
					$("#Pagination4Search").pagination($("#totalNum4Search").val(), {
						prev_text: "上一页",
						next_text: "下一页",
						num_edge_entries: 3,
						num_display_entries: 10,
						//回调 
						callback: pageselectCallbackForSearchInDetail
					});
					$("#Pagination4Search").show();
				}

				addRelationCompany('searchResultDiv');
				bind_search_company_list();
				$("#searchResultDiv").scroll(0);
				
				function bind_search_company_list() {
					$("#searchResultDiv").bind('mousewheel', scroll_move);
					$("#searchResultDiv").bind('DOMMouseScroll', scroll_move);
				}
			}
		});
	} else {
		toastr.warning("请输入关键词！");
	}
	//});

}


/**
 * @param new_page_index
 * @param pagination_container
 */
function pageselectCallbackForSearchInDetail(new_page_index, pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;
	var companyName = $("#rcompanyName").val();
	var parameter = {
		"keyword": $.trim($("#rsearchInfo").val()),
		"companyName": companyName,
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10),
		"monitorId": monitorId
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/addRelationCtl/search4Company1.do",
		data: parameter,
		beforeSend: function () {
			//异步请求时spinner出现
			var target = $(".company_list").get(0);
			spinner.spin(target);
		},
		success: function (result) {
			spinner.spin();
			//重新绘制查询结果界面
			//        	$('.company_list').html(result);
			// 隐藏企业股东

			/*	$("#company_mode_div").hide();
				 $("#company_mode_div").html("");*/

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


			//重新绑定事件
			addRelationCompany('searchResultDiv');
			//initSearchResult(result);
		}
	});
}

//分页回调
function pageselectCallbackForSearch(new_page_index, pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;

	var parameter = {
		"keyword": encodeURIComponent($.trim($("#searchInfo").val())),
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10)
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorComRisk/search4Company.do",
		data: parameter,
		beforeSend: function () {
			//异步请求时spinner出现
			var target = $(".company_list").get(0);
			spinner.spin(target);
		},
		success: function (result) {
			spinner.spin();
			//重新绘制查询结果界面
			$('.company_list').html(result);
		}
	});
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

//添加关系自然人
function addRelationPerson() {

	var tabType = "";

	var tt = $("#relate_add_block_in_detail .person_mode .filter_title_block .title").map(function () {
		var div_this = this;

		if ($(div_this).hasClass("selected")) {
			tabType = this.innerHTML;
			$(div_this).click();
		}
	});

	if ($("#ppersonName").val() == '') {
		toastr.warning("请输入姓名!");
		return;
	}

	if ($("#pprovince").val() == '请选择省') {
		toastr.warning("请选择常住地！");
		return;
	}

	if ($("#pcity").val() == '请选择市') {
		toastr.warning("请选择常住地！");
		return;
	}

	if ($("#pidNumber").val() != "") {
		if (isCardNo($("#pidNumber").val()) === false) {
			toastr.warning("请输入正确的身份证！");
			return;
		}
	}

	var parameter = {
		"name": encodeURIComponent($("#ppersonName").val()),
		"province": encodeURIComponent($("#pprovince").val()),
		"city": encodeURIComponent($("#pcity").val()),
		"idNumber": $("#pidNumber").val(),
		"companyName": encodeURIComponent($("#hcompanyName").val()),
		"monitorId": monitorId
	}

	controller.message_lap5.show("#addRelationPersonTipsWindow");
	controller.lap_list.RelateOverlapInDetail.close_quick();

	var cancelDiv = document.getElementById("cancel_message_person");

	cancelDiv.onclick = function () {
		controller.message_lap5.close();
		controller.lap_list.RelateOverlapInDetail.show_quick("#relate_add_block_in_detail");
	}

	var commitDiv = document.getElementById("commit_cancel_person");

	commitDiv.onclick = function () {
		controller.message_lap5.close();
		controller.lap_list.RelateOverlapInDetail.show_quick("#relate_add_block_in_detail");

		$.ajax({
			url: ctx + "/monitorRelationPerson/addMonitorRelationPerson", // 添加分组；
			type: "POST",
			data: parameter,
			beforeSend: function () {
				//异步请求时spinner出现
				var target = $(".company_list").get(0);
				spinner.spin(target);
			},
			dataType: "json",
			success: function (result) {
				spinner.spin();
				if (result.resultCode == "0") {
					toastr.success(result.resultMsg);

					navigatePersonMode1InDetail(tabType);// 重新刷新；
					getRelationPersonList();
				} else {
					toastr.error(result.resultMsg);
				}
			},
			complete: function () {

			}
		});
	}

}

/**
 * 获取事件
 * @returns
 */
function getEvent() {
	if (document.all) {
		return window.event; //如果是ie
	}
	func = getEvent.caller;
	while (func != null) {
		var arg0 = func.arguments[0];
		if (arg0) {
			if ((arg0.constructor == Event || arg0.constructor == MouseEvent) || (typeof (arg0) == "object" && arg0.preventDefault && arg0.stopPropagation)) {
				return arg0;
			}
		}
		func = func.caller;
	}
	return null;
}

/**
 * 停止其余事件
 */
function stopevt() {
	var ev = getEvent();
	if (ev.stopPropagation) {
		ev.stopPropagation();
	} else if (window.ev) {
		window.ev.cancelBubble = true;
	}
}

/**
 * 取消动态监控事件绑定
 */
function cancelMonitor() {


	var parameter = {
		"companyName": encodeURIComponent(companyName),
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/monitorComRisk/cancelMonitor.do",
		data: parameter,
		success: function (result) {
			toastr.success("取消成功");
			//点击返回按钮
			$(".for_back_btn").click();
			//		        	window.history.back();
		}
	});


}

//批量删除关系企业或关系自然人
function deleteRelationCompanyOrPersonBatch() {

	if (realType == "0") {
		//公司
		//step1 收集公司信息
		var relationCompanyIds = getCheckedRelationCompanyIds();

		if (relationCompanyIds == "") {
			toastr.warning("请选择公司");
			return;
		}

		var parameter = {
			"relationCompanyIds": relationCompanyIds,
		}

		$.ajax({
			url: ctx + "/monitorRelationCompany/deleteMonitorRelationCompanys",
			type: "POST",
			data: parameter,
			dataType: "json",
			success: function (result) {
				if (result.resultCode == "0") {
					getRelationCompanyList();

					toastr.success(result.resultMsg);
				} else {
					toastr.error(result.resultMsg);
				}

			}
		});

	} else if (realType == "1") {
		//个人 收集个人信息
		var relationPersonIds = getCheckedRelationPersonIds();

		if (relationPersonIds == "") {
			toastr.warning("请选择个人");
			return;
		}

		var parameter = {
			"relationPersonIds": relationPersonIds,
		}

		$.ajax({
			url: ctx + "/monitorRelationPerson/deleteMonitorRelationPersons",
			type: "POST",
			data: parameter,
			dataType: "json",
			success: function (result) {
				if (result.resultCode == "0") {
					getRelationPersonList();

					toastr.success(result.resultMsg);
				} else {
					toastr.error(result.resultMsg);
				}

			}
		});
	}
}

//获取选中个关系企业的id
function getCheckedRelationCompanyIds() {

	var arrChk = $("input[name='selectOne4Company']:checked");
	var ids = "";

	//遍历得到每个checkbox的value值
	for (var i = 0; i < arrChk.length; i++) {
		ids += arrChk[i].value;
		if (i < arrChk.length - 1) {
			ids += ",";
		}
	}

	return ids;
}

//获取选中个关系自然人的id
function getCheckedRelationPersonIds() {

	var arrChk = $("input[name='selectOne4Person']:checked");
	var ids = "";

	//遍历得到每个checkbox的value值
	for (var i = 0; i < arrChk.length; i++) {
		ids += arrChk[i].value;
		if (i < arrChk.length - 1) {
			ids += ",";
		}
	}

	return ids;
}

//下载按钮点击事件
function downloadBtnClicked() {

	window.location.href = ctx + "/ie/exportCompanyMonitorReport.do?monitorId=" + monitorId;
}

//跳转到司法全景图
function toSiFaQuanJingtu(companyName) {
	var deep = $("#deep").val();

	window.location.href = ctx + "/monitorComRisk/toSiFaQuanJingtuView.do?compName=" + encodeURIComponent(companyName) + "&deep=" + (parseInt(deep) + 1);
}

function hideComponent(id) {
	document.getElementById(id).style.visibility = "hidden";//隐藏 
	document.getElementById(id).style.display = "";//显示 
}
function showComponent(id) {
	document.getElementById(id).style.visibility = "visible";//隐藏 
	document.getElementById(id).style.display = "";//显示 
}

function hideComponent2(id) {
	document.getElementById(id).style.visibility = "hidden";//隐藏 
	document.getElementById(id).style.display = "none";//显示 
}
function showComponent2(id) {
	document.getElementById(id).style.visibility = "visible";//隐藏 
	document.getElementById(id).style.display = "none";//显示 
}

/**
 * 初始化页面属性
 */
function initParams() {
	monitorId = $("#monitorId").val();
	companyName = $("#companyName").val();
}

//重新获取各分类未读事件的数量，并判断是否显示红点
function getMonitorComRiskEventNum(isInit) {

	var parameter = {
		"monitorId": monitorId
	}

	$.ajax({
		url: ctx + "/monitorCompany/getMonitorComRiskEventNum",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			if (result == null) {
				return;
			}

			//初始化事件的数量
			eventCategory = result;

			var arr = $(".event_value");
			var cate;
			for (var i = 0; i < arr.length; i++) {
				if (!$(arr[i]).hasClass("selected")) {//选中的不需要重新更新样式
					cate = $(arr[i]).attr("index");
					if (eventCategory.hasOwnProperty(cate)) {//有数据，字体变蓝
						//if(isInit){
						$(arr[i]).css("color", "#2ea7e0");
						///}
						if (eventCategory[cate] > 0) {//有未读数据
							$(arr[i]).addClass("unread_event_value");
						} else {
							if ($(arr[i]).hasClass("unread_event_value")) {
								$(arr[i]).removeClass("unread_event_value");
							}
						}
					} else {//无数据，字体变黑
						$(arr[i]).css("color", "#666666");
					}
				}
			}
			if (isInit) {
				temp = $(arr[0]);
			}
		}
	});
}


/**
 * 获取详情
 */
function getDetails4sfws(judgmentId, eventId, _this) {

	//将事件Id统计进去
	if (ignoreFlag == false) {
		//如果没有点击忽略全部 那么需要将点击详情的事件加进去 否则不加因为已经加入进去了重复添加

		//step1 校验当前的事件是否是已经读取过的
		if ($(_this).hasClass("read_td")) {
			//return;
		} else {
			//step2 当前事件是未读状态 需要校验之前是否已经点击详情加入更新列表
			var arr = new Array(); //定义一数组 
			arr = eventIdListString.split(","); //字符分割 
			for (i = 0; i < arr.length; i++) {
				if (arr[i] == eventId) {
					//return;
				} else {
					if ("" == eventIdListString) {
						eventIdListString = eventId;
					} else {
						eventIdListString = eventIdListString + "," + eventId;
					}
				}
			}

			//如果是全部的时候 读取一条数据需要将其颜色设置为灰色
			$(_this).addClass("read_td");
			$(_this).parent().addClass("read_row");
			var obj = $(_this).parent();
			var tempArr = $(obj).find("td");
		}
	}

	var parameter = {
		"judgmentId": judgmentId,
		"companyName": encodeURIComponent(companyName)
	}
	console.log("sfws detail clicked...,param=" + judgmentId + "," + eventId);
	$.ajax({
		url: ctx + "/monitorComRisk/getMonitorDetails4Sfws.do",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {
			$("#judgmentTime4Sx").html(result.judgmentTime);
			$("#title4Sx").html(result.title);
			var title = result.title;
			if (1 <= title.length && title.length < 23) {
				$("#title4Sx").css("font-size", "30px");
			} else if (23 <= title.length && title.length < 58) {
				$("#title4Sx").css("font-size", "24px");
			} else if (58 <= title.length && title.length < 114) {
				$("#title4Sx").css("font-size", "18px");
			}
			$(".content_type").html(result.instrumenttype);
			$(".content_case").html(result.docket);
			$(".content_court").html(result.courtname);
			$("#details4Sx").html(result.details);

		}
	});


}

/**
 * 获取详情
 */
function getDetails(eventId, _this) {

	//将事件Id统计进去
	if (ignoreFlag == false) {
		//如果没有点击忽略全部 那么需要将点击详情的事件加进去 否则不加因为已经加入进去了重复添加

		//step1 校验当前的事件是否是已经读取过的
		if ($(_this).hasClass("read_td")) {
			//return;
		} else {
			//step2 当前事件是未读状态 需要校验之前是否已经点击详情加入更新列表
			var arr = new Array(); //定义一数组 
			arr = eventIdListString.split(","); //字符分割 
			for (i = 0; i < arr.length; i++) {
				if (arr[i] == eventId) {
					//return;
				} else {
					if ("" == eventIdListString) {
						eventIdListString = eventId;
					} else {
						eventIdListString = eventIdListString + "," + eventId;
					}
				}
			}
			//如果是全部的时候 读取一条数据需要将其颜色设置为灰色
			$(_this).addClass("read_td");
			$(_this).parent().addClass("read_row");
			var obj = $(_this).parent();
			var tempArr = $(obj).find("td");
		}
	}

	var parameter = {
		"eventId": eventId,
		"eventSubType": $("#eventSubType").val()
	}
	applyAjax(ctx + "/monitorComRisk/getMonitorComEventDetails.do", parameter, getDetailsCallback);
}

function getDetailsCallback(result) {
	var obj = result.resultObj;
	var eventSubType = result.eventSubType;
	if (eventSubType == "19" || eventSubType == "20" || eventSubType == "18") {
		$("#judgmentTime4Sx").html(obj.judgmentTime);
		$("#title4Sx").html(obj.title);

		$(".content_type").html(obj.instrumenttype);
		$(".content_case").html(obj.docket);
		$(".content_court").html(obj.courtname);
		$("#details4Sx").html(obj.details);
	} else if (eventSubType == "24" || eventSubType == "25") {
		$("#publishDate4News ").html(obj.pubtime);
		$("#title4News").html(obj.title);
		$("#details4News").html(obj.content);
	} else if (eventSubType == "22") {
		$("#mc4Shixin").html(obj.mc);
		$("#dm4Shixin").html(obj.dm);
		$("#fddbr4Shixin").html(obj.fddbr);
		$("#fddbr4Shixin").html(obj.fddbr);
		$("#zxfy4Shixin").html(obj.zxfy);
		$("#sf4Shixin").html(obj.sf);
		$("#zxyjwh4Shixin").html(obj.zxyjwh);
		$("#sj4Shixin").html(obj.sj);
		$("#ah4Shixin").html(obj.ah);
		$("#zxyjdw4Shixin").html(obj.zxyjdw);
		$("#wsqdyw4Shixin").html(obj.wsqdyw);
		$("#lxqk4Shixin").html(obj.lxqk);
		$("#sxjtqk4Shixin").html(obj.sxjtqk);
		$("#fbsj4Shixin").html(obj.fbsj);
	} else if (eventSubType == "12") {
		$("#shiyaojian .shiyaojian").append(obj.description);
	}
}

/**
* 分组Box处理
*/
function groupBoxDeal() {

	//	 	$(".selectedComName").html("");
	//判断是否选中 如果是选中那么需要往右边加企业名称 如果是没有选中那么就需要从右边移除名称
	var arrChk = $("input[name='groupNameList']:checked");
	var content = "";
	//遍历得到每个checkbox的value值
	for (var i = 0; i < arrChk.length; i++) {
		content += arrChk[i].value;
		if (i < arrChk.length - 1) {
			content += ",";
		}
	}

	groupListString = content;

}

/**
 * 批量追加公司分组
 */
function batchAdd4Person() {

	getCheckedRelationPersonIds();
	getCheckedGroupIds();

	var parameter = {
		"personListStr": encodeURIComponent(personListString),
		"groupListStr": groupListString,
		"allGroupId": groupList[0].groupID
	}

	$.ajax({
		url: ctx + "/monitorComRisk/batchAdd4Person.do",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function (result) {

			if (result.resultCode == "0") {
				toastr.success(result.resultMsg);
				$("input[name='selectOne4Person']").prop("checked", false);
				($(".selectAll4Person").prop("checked", false));
				updateGroupNum();
			} else {
				toastr.error(result.resultMsg);
			}

		}
	});

}

/**
 * 更新关联分组中的数量信息
 */
function updateGroupNum() {

	var parameter = {
		"monitorId": monitorId
	};

	//首先发送异步请求获取关联分组信息
	$.ajax({
		type: "post",
		dataType: "json",
		url: ctx + "/monitorComRisk/getMonitorReaGroups.do",
		data: parameter,
		success: function (result) {
			//step1 更新分组列表
			groupList = result;
			//step2 局部更新当前页面展示分组的数量
			var arr = $(".group_list").children();
			for (var i = 0; i < arr.length; i++) {
				var index = $(arr[i]).attr("index");
				var str = "(" + groupList[index].companyNum + ")"
				$(arr[i]).find(".group_count").html(str);
			}
		}
	});


}


/**
 * 颜色处理事件
 * @param _this
 */
function colorDeal4In(_this) {
	$(_this).find(".name").css("color", "#2ea7e0");
}


/**
 * 颜色处理事件
 * @param _this
 */
function colorDeal4Out(_this) {
	$(_this).find(".name").css("color", "#666666");
}
/**
 * 颜色处理事件
 * @param _this
 */
function colorDeal4InNews(_this) {
	$(_this).find(".newsDetails").css("color", "#2ea7e0");
}


/**
 * 颜色处理事件
 * @param _this
 */
function colorDeal4OutNews(_this) {
	$(_this).find(".newsDetails").css("color", "#666666");
}


function bindEnterSearch() {
	console.log("add lister to input");
	$('#rsearchInfo').bind('keypress', function (event) {
		console.log("searchInfo input keypress");
		if (event.keyCode == "13") {

			//$(".company_mode .search_btn").click();
			search4Company();
		}
	});
}

function bindHistoryInfo() {
	$("#history_btn").click(function () {
		var deep = $("#deep").val();

		window.location.href = ctx + "/hisCompany/toHisCompanyView.do?companyName=" + encodeURIComponent(companyName) + "&deep=" + (parseInt(deep) + 1);
	});
}

function bindLegalPerson() {
	$("#legal_person_btn").click(function () {

		$.ajax({
			url: ctx + "/monitorComRisk/isHasFaDingDaiBiaoRen.do",  //
			type: "POST",
			data: { "companyName": encodeURIComponent(companyName) },
			beforeSend: function () {
				//异步请求时spinner出现
				//var target = $(".company_list").get(0);
				//spinner.spin(target);
			},
			dataType: "json",
			success: function (result) {
				console.log("isHasFaDingDaiBiaoRen call back..");
				console.log(result);
				//spinner.spin();
				if (result.flag == true) {//
					console.log("has FaDingDaiBiaoRen");
					var deep = $("#deep").val();
					window.location.href = ctx + "/monitorComRisk/toFaDingDaiBiaoRenView.do?companyName=" + encodeURIComponent(companyName) + "&deep=" + (parseInt(deep) + 1);
				} else {
					console.log("not has FaDingDaiBiaoRen");
					//toastr.warning("当前无对外任职及投资信息！");
					message_lap_deleteGroup.show("#noLegalPersonTip");
					$("#submitNoLegalPersonTip").click(function () {
						message_lap_deleteGroup.close();
						$(this).unbind("click");
					});
				}

			}
		});
	});
}

var init_lap_in_detail = function (list) {
	list.RelateOverlapInDetail = new overlap({
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
				//				$("#province").val("请先择省");
				//				$("#city").val("请选择市");
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

// 详情页面添加关联关系；
function relateBlockShowInDetail(companyName) {
	//console.log("add btn click...");
	//	$("#rcompanyName").val(companyName);
	var hcompanyName = $("#companyName").val();
	console.log("hcompanyName===" + hcompanyName);
	$("#rcompanyName").val(hcompanyName);
	controller.lap_list.RelateOverlapInDetail.show("#relate_add_block_in_detail");

	var isCompanySelected = $("#company").hasClass("selected");
	if (isCompanySelected) {
		//console.log("company selected");
		navigateCompanyModeInDetail();
	} else {
		//console.log("person selected");
		$("#relate_add_block_in_detail .mode_selector .add_mode").click();
	}

	//
}

// 新增关联企业，个人所用绑定事件；
var event_relate_block_inCompanyDetail = function () {
	//新增切换
	$("#relate_add_block_in_detail .mode_selector .add_mode").click(function () {
		console.log("pick up company or person_mode...");
		var _this = this;
		if ($(this).hasClass("selected")) {
			return;
		}
		$("#relate_add_block_in_detail .mode_selector .add_mode").removeClass("selected");
		var move_lenth = $(this).position().left + 93;
		$("#relate_add_block_in_detail .mode_selector .selected").removeClass("selected");
		$(_this).addClass("selected");
		//事件绑定
		$("#relate_add_block_in_detail .mode_selector .mode_arr").animate({ left: move_lenth }, 300, function () {
			$(".mode_block").addClass("hidden");
			$("." + $(_this).attr("id")).removeClass("hidden");
			console.log("id==" + $(_this).attr("id"));
			var tabId = $(_this).attr("id");
			if (tabId == 'person_mode') {
				console.log("pick up person_mode");
				navigatePersonMode1InDetail("企业法人代表");
			} else if (tabId == 'company_mode') {
				console.log("pick up company_mode");
				navigateCompanyModeInDetail();
			}

		})
	})
}

// 跳转到关联自然人界面；
function navigatePersonMode1InDetail(tabType) {
	var companyName = $("#rcompanyName").val();

	var parameter = {
		"monitorId": monitorId,
		"companyName": companyName
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
			bindPersonTabSwithcEventInDetail();
			refleshTabDivInDetail(tabType);
		}
	})
}

// 关联自然人 三TAB，添加后重新刷新回去；
var refleshTabDivInDetail = function (tabType) {
	if (tabType == '企业法人代表') {
		$("#relate_add_block_in_detail .person_mode .filter_title_block").find("div:eq(0)").click();
	} else if (tabType == '股东') {
		$("#relate_add_block_in_detail .person_mode .filter_title_block").find("div:eq(1)").click();
	} else if (tabType == '高管') {
		$("#relate_add_block_in_detail .person_mode .filter_title_block").find("div:eq(2)").click();
	} else {
		$("#relate_add_block_in_detail .person_mode .filter_title_block").find("div:eq(0)").click();
	}
}

// 导航到关联企业页面；
var navigateCompanyModeInDetail = function () {
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
			//展示分页标签
			if ($("#totalNumStockHolderCompany").val() > 0) {
				$("#PageStockHolderCompanyDiv").pagination($("#totalNumStockHolderCompany").val(), {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					num_display_entries: 10,
					//回调 
					callback: pageselectCallbackForStockHolderCompanyInDetail
				});
			}

			//重新绑定事件
			addRelationCompany('company_mode_div');
			bindEnterSearch();
			$("#stockHolderCompany .company_list").scroll(0);
			bind_shareholder_list();
			

			function bind_shareholder_list() {
                $("#stockHolderCompany .company_list").bind('mousewheel', scroll_move);
                $("#stockHolderCompany .company_list").bind('DOMMouseScroll', scroll_move);
			}
			
		},
		complete: function (request, textStatus) {
			bindCompanyTabSwitchEventInDetail();
			//bindEnterSearchInDetail();
		}
	});
}

// pageselectCallbackForStockHolderCompanyInDetail

//股东回调；
var pageselectCallbackForStockHolderCompanyInDetail = function (new_page_index, pagination_container) {

	var currentPageNo = parseInt(new_page_index) + 1;
	var companyName = $("#hcompanyName").val();
	console.log("companyName ==" + companyName);
	var parameter = {
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10),
		"companyName": companyName,
		"monitorId": monitorId
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/addRelationCtl/getStockHolderCompanyList",
		data: parameter,
		beforeSend: function () {
			//异步请求时spinner出现
			var target = $(".company_list").get(0);
			spinner.spin(target);
		},
		success: function (result) {
			spinner.spin();
			$("#stockHolderCompany").show();//隐藏企业股东
			$("#stockHolderCompany").html(result);
			//隐藏企业股东
			$("#investSearchResultDiv").hide();
			$('#PaginationInvestmentCompany').html("");
			// 隐藏搜索结果；
			$('#searchResultDiv').hide();
			$('#Pagination4Search').html("");
			// 隐藏关系自然人
			$("#person_mode_div").html("");
			//重新绑定事件
			addRelationCompany('stockHolderCompany');
			$("#stockHolderCompany .company_list").scroll(0);
			bind_shareholder_list();
			

			function bind_shareholder_list() {
                $("#stockHolderCompany .company_list").bind('mousewheel', scroll_move);
                $("#stockHolderCompany .company_list").bind('DOMMouseScroll', scroll_move);
			}
			
		}
	});
}


//绑定公司TAB注册事件；
var bindCompanyTabSwitchEventInDetail = function () {
	$("#relate_add_block_in_detail .company_mode .filter_title_block .title").click(function () {

		console.log("pick up company_mode");

		var _this = this;
		if ($(this).hasClass("selected")) {
			return;
		}
		$("#relate_add_block_in_detail .company_mode .filter_title_block .selected").removeClass("selected");
		$(_this).addClass("selected");
		var move_lenth = $(this).position().left - 37;
		$("#relate_add_block_in_detail  .company_mode .filter_title_block .filter_arr").animate({ left: move_lenth }, 300, function () {

		})
	})
}



function bindEnterSearchInDetail() {
	console.log("add lister to input");
	$('#rsearchInfo').bind('keypress', function (event) {
		console.log("searchInfo input keypress");
		if (event.keyCode == "13") {
			search4Company();
		}
	});
}



var bindPersonTabSwithcEventInDetail = function () {
	// 移動箭頭；
	$("#relate_add_block_in_detail .person_mode .filter_title_block .title").click(function () {
		var _this = this;
		if ($(this).hasClass("selected")) {
			return;
		}

		$("#relate_add_block_in_detail .person_mode .filter_title_block .selected").removeClass("selected");
		$(_this).addClass("selected");
		console.log("val====" + $(_this).text());
		var move_lenth = $(this).position().left - 37;
		$("#relate_add_block_in_detail .person_mode .filter_title_block .filter_arr").animate({ left: move_lenth }, 300, function () {
			console.log("switch tabs('股东','投资','高管')");
		})

		var tabId = $(_this).attr("id");
		console.log("tabId====" + tabId);
	})

	//綁定事件；
	$("#relate_add_block_in_detail .person_mode .filter_title_block .title").map(function () {
		var _this = this;
		var Stype = $(_this).text();
		if (Stype == '企业法人代表') {
			$(_this).click(function () { displayTabInPersonModeInDetail("企业法人代表"); })
		} else if (Stype == '股东') {
			$(_this).click(function () { displayTabInPersonModeInDetail("股东"); })
		}
		else if (Stype == '高管') {
			$(_this).click(function () { displayTabInPersonModeInDetail("高管"); })
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

	$("#relate_add_block_in_detail .person_mode .filter_option_block .option").click(function () {
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

		//恢复未被选中的菜单项;
		$("#relate_add_block_in_detail .person_mode .filter_option_block .selected").map(function () {
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
		$("#relate_add_block_in_detail .person_mode .filter_option_block .option_list").stop().animate({ scrollTop: "-=" + 35 }, 500);
	})

	$("#option_down_btn").click(function () {
		$("#relate_add_block_in_detail .person_mode .filter_option_block .option_list").stop().animate({ scrollTop: "+=" + 35 }, 500);
	})
}

var getInvestmentCompanyList = function () {

	var companyName = $("#rcompanyName").val();
	console.log("navigateCompanyMode...... companyName ===" + companyName);
	var parameter = {
		"companyName": companyName,
		"monitorId": monitorId
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
			//        	$('#searchResultDiv').html("");

			// 隐藏企业股东
			$("#stockHolderCompany").hide();
			$("#PageStockHolderCompanyDiv").hide();
			$('#PageStockHolderCompanyDiv').html("");

			// 隐藏关系自然人
			$("#person_mode_div").html("");

			//展示分页标签
			if ($("#totalNumInvestmentCompany").val() > 0) {
				$("#PaginationInvestmentCompany").pagination($("#totalNumInvestmentCompany").val(), {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					num_display_entries: 10,
					//回调 
					callback: pageselectCallbackForInvestmentCompany
				});
				$("#PaginationInvestmentCompany").show();
			}

			//重新绑定事件
			addRelationCompany('investSearchResultDiv');
			$("#investSearchResultDiv .company_list").scroll(0);
			bind_investment_list();
			function bind_investment_list() {
                $("#investSearchResultDiv .company_list").bind('mousewheel', scroll_move);
                $("#investSearchResultDiv .company_list").bind('DOMMouseScroll', scroll_move);
            }
		},
		complete: function (request, textStatus) {
			bindCompanyTabSwitchEventInDetail();
		}
	});

}

var pageselectCallbackForInvestmentCompany = function (new_page_index, pagination_container) {
	var currentPageNo = parseInt(new_page_index) + 1;
	var companyName = $("#rcompanyName").val();
	var parameter = {
		"currentPageNo": parseInt(currentPageNo),
		"pageSize": parseInt(10),
		"companyName": companyName,
		"monitorId": monitorId
	}

	$.ajax({
		type: "post",
		dataType: "html",
		url: ctx + "/addRelationCtl/getInvestmentCompanyList",
		data: parameter,
		beforeSend: function () {
			//异步请求时spinner出现
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
			//重新绑定事件
			addRelationCompany('investSearchResultDiv');
			$("#investSearchResultDiv .company_list").scroll(0);
			bind_investment_list();
			function bind_investment_list() {
                $("#investSearchResultDiv .company_list").bind('mousewheel', scroll_move);
                $("#investSearchResultDiv .company_list").bind('DOMMouseScroll', scroll_move);
            }
		}
	});
}

//getStockHolderCompanyList
var getStockHolderCompanyList = function () {
	var companyName = $("#rcompanyName").val();
	console.log("getStockHolderCompanyList...... companyName ===" + companyName);
	var parameter = {
		"companyName": companyName,
		"monitorId": monitorId
	}
	$.ajax({
		url: ctx + "/addRelationCtl/getStockHolderCompanyList",
		type: "POST",
		data: parameter,
		dataType: "html",
		success: function (result) {

			$("#stockHolderCompany").show();
			$("#PageStockHolderCompanyDiv").show();
			$("#stockHolderCompany").html(result);
			// 隐藏搜索结果；
			$('#searchResultDiv').hide();
			$('#Pagination4Search').html("");
			// yinchang touzi ;	

			$("#investSearchResultDiv").hide();
			$('#PaginationInvestmentCompany').html("");

			// 隐藏关系自然人
			$("#person_mode_div").html("");


			//展示分页标签
			if ($("#totalNumStockHolderCompany").val() > 0) {
				$("#PageStockHolderCompanyDiv").pagination($("#totalNumStockHolderCompany").val(), {
					prev_text: "上一页",
					next_text: "下一页",
					num_edge_entries: 3,
					num_display_entries: 10,
					//回调 
					callback: pageselectCallbackForStockHolderCompanyInDetail
				});
				$("#PageStockHolderCompanyDiv").show();
			}
			//重新绑定事件
			addRelationCompany('stockHolderCompany');
			$("#stockHolderCompany .company_list").scroll(0);
			bind_shareholder_list();
			

			function bind_shareholder_list() {
                $("#stockHolderCompany .company_list").bind('mousewheel', scroll_move);
                $("#stockHolderCompany .company_list").bind('DOMMouseScroll', scroll_move);
			}
		},
		complete: function (request, textStatus) {
			bindCompanyTabSwitchEventInDetail();
		}
	});

}

// relate_add_block_in_detail     relate_add_block
var displayTabInPersonModeInDetail = function (Stype) {

	$("#relate_add_block_in_detail .person_mode .filter_option_block .option_list").addClass("hidden");
	if (Stype == '企业法人代表') {
		$("#leagalPersonDiv").removeClass("hidden");

	} else if (Stype == '股东') {
		$("#stockHolderDiv").removeClass("hidden");
	}
	else if (Stype == '高管') {
		$("#managerDiv").removeClass("hidden");
	}
}

//添加关系企业
function addRelationCompany(divId) {

	$("#" + divId + " .add_btn").click(function () {

		console.log("addBtn clicked...,id=" + "#" + divId + " .add_btn");
		var existFlag = $(this).attr("existFlag");
		if (existFlag) {
			return;
		}
		var name = $(this).attr("enterpriseName");

		var parameter = {
			"companyName": encodeURIComponent(name),
			"monitorId": monitorId
		}

		controller.message_lap4.show("#addRelationCompanyTipsWindow");
		controller.lap_list.RelateOverlapInDetail.close_quick();

		var cancelDiv = document.getElementById("cancel_message_company");

		cancelDiv.onclick = function () {
			controller.message_lap4.close();
			controller.lap_list.RelateOverlapInDetail.show_quick("#relate_add_block_in_detail");
		}

		var thisthis = $(this);

		var commitDiv = document.getElementById("commit_cancel_company");
		commitDiv.onclick = function () {
			controller.message_lap4.close();
			controller.lap_list.RelateOverlapInDetail.show_quick("#relate_add_block_in_detail");

			$.ajax({
				url: ctx + "/monitorRelationCompany/addMonitorRelationCompany",  // 添加分组信息；
				type: "POST",
				data: parameter,
				beforeSend: function () {
					//异步请求时spinner出现
					var target = $(".company_list").get(0);
					spinner.spin(target);
				},
				dataType: "json",
				success: function (result) {
					spinner.spin();
					if (result.resultCode == "0") {
						toastr.success(result.resultMsg);
						getRelationCompanyList();
						thisthis.empty().append("<div class='inline_div' style='width:46px;height:21px;'>已添加</div>");
						thisthis.attr("existFlag", true);
					} else if (result.resultCode == "2") {
						regMessage = /^该企业已更名为：(.+?)，(.+)$/g
						var r = regMessage.exec(result.resultMsg)
						var companyName = r[1];
						var message = r[2];
						if (message == "更名后企业加入成功！监控详情将于次日展示") {
							getRelationCompanyList();
							//thisthis.empty().append("<div class='inline_div' style='width:46px;height:21px;'>已添加</div>");
							//thisthis.attr("existFlag", true);
						}
						controller.show_used_message(companyName, message);
					}
					else {
						toastr.error(result.resultMsg);
					}
				}
			});
		}
	});
}



//企业列表添加关联关系提示；
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

//初始化checkBox
function initCheckBox() {
	//初始化的checkBox 新事件 严重 异常 一般
	$("input[name='newEve']").prop("checked", true);
	$("input[name='typeList']").prop("checked", true);
}

/**
 * 已读未读状态的处理
 */
function readStatusDeal() {
	//更新状态
	if ($("input[name='newEve']").prop("checked")) {
		//0表示未读
		readStatus = "0";
	} else {
		//1表示全部
		readStatus = "1";
	}
	eventsDetailsListDeal();
}

/**
 * 事件类型的处理 一般 异常 严重
 */
function eventLevelDeal(_this) {
	if ($(_this).hasClass("event_unselector_btn")) {
		$(_this).removeClass("event_unselector_btn");
	} else {
		$(_this).addClass("event_unselector_btn");
	}
	var arr = ["3", "2", "1"];
	$(".event_unselector_btn").each(function () {
		RemoveByValue(arr, $(this).attr("level"));
	});
	if (arr.length <= 0) {
		typeListString = "无";
	} else {
		typeListString = arr.join(",");
	}
	eventsDetailsListDeal();
}

/**
 * 忽略全部
 */
function ignoreAll() {
	var parameter = {
		"monitorId": monitorId,
		"companyName": encodeURIComponent(companyName),
		"eventSubType": eventSubType,
		"infoShowDate": $("#infoShowDate").val()
	};

	$.ajax({
		url: ctx + "/monitorCompanyEvent/ignoreAllUnreadEventBySubType",
		type: "POST",
		data: parameter,
		beforeSend: function () {
			//异步请求时spinner出现
			var target = $(".monitor_events").get(0);
			spinner.spin(target);
		},
		dataType: "json",
		success: function (result) {
			//spinner.spin();
			if (result != undefined && result.count != undefined && parseInt(result.count) > 0) {
				eventIdListString = "";
				eventsDetailsListDeal();
			}
		},
		error: function () {

		},
		complete: function (request, textStatus) {
			//console.log("applyCompanyBizView compeleted");
			spinner.spin();
			if (request) {
				if (request.status == 0) {
					toastr.error('网络异常！');
				} else if (request.status == 500) {
					toastr.error('站点异常！');
				} else {
					if (!textStatus || textStatus != "success") {
						toastr.error('站点异常！');
					}
				}
			}
		}
	});

}

