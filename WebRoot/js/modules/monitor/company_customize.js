
var CompanyMonitorRepository = function () {

    var CustomizeRiskConfig = {
        RiskId: {
            "0": "正常",
            "1": "关注",
            "2": "一般预警",
            "3": "特别预警",
        },
        RiskValue: {
            "正常": "0",
            "关注": "1",
            "一般预警": "2",
            "特别预警": "3",
        },
        RiskItem: {
            "11": "经营预警",
            "12": "信用预警",
            "13": "财务预警",
            "14": "高管预警",
            "15": "补充说明",
        },
        RiskSubItem: {
            "51": "水电费骤降或欠缴",
            "52": "主营业务变动",
            "53": "劳资争议",
            "54": "对外投资失败",
            "55": "上下游变动",
            "56": "变卖主要资产",
            "57": "销售额骤降",
            "58": "关联交易过多",
            "59": "拖欠货款",
            "60": "交付违约",
            "61": "行业口碑差",
            "62": "恶意欺诈",
            "64": "负债上升过快",
            "65": "应收账款过多",
            "66": "盈利能力下降",
            "67": "现金流紧张",
            "68": "涉案",
            "69": "失联",
            "70": "意外事故或健康恶化",
            "71": "频繁变更",
        },
        RiskType: {
            "0": "system",
            "1": "customize"
        }
    }

    var ajaxLock = {
        LockPutRisk: false,
        LockGitHistory: false,
        LockPutHistory: false,
        LockDeleteHistory: false
    }

    function IsFunction(callback) {
        return typeof callback == "function";
    }

    function SetTimeOutComplete(callback) {
        if (IsFunction(callback)) {
            setTimeout(callback, 1500);
        }
    }

    function BuildCustomizeResultmodel(prev, edit) {
        function GetRiskInfo(code, subCode, value) {
            return {
                riskType: code,
                riskSubType: subCode,
                riskContent: value
            }
        }
        var result = {
            oldCustomRiskId: prev.CustomizeId,
            monitorId: $("#monitorId").val(),
            riskSituation: CustomizeRiskConfig.RiskValue[edit.RiskLevel],
            riskInfoSet: []
        };

        for (var i = 0; i < edit.RiskItems.length; i++) {
            var item = edit.RiskItems[i];
            for (var j = 0; j < item.SubItems.length; j++) {
                result.riskInfoSet.push(GetRiskInfo(item.ItemCode, item.SubItems[j].ItemCode, item.SubItems[j].ItemName));
            }
        }
        result.riskInfoSet = JSON.stringify(result.riskInfoSet)
        return result;
    }

    function BuildCustomizeModel(model) {
        var ItemStack = {}
        var date = new Date(model.lastModifiedTime || model.createTime);
        var result = {
            CustomizeId: model.riskId,
            RiskType: 0,
            RiskId: model.riskSituation,
            RiskLevel: CustomizeRiskConfig.RiskId[model.riskSituation],
            RiskSuggest: "",
            RiskTypeBefore: model.RiskTypeBefore || 1,
            RiskIdBefore: model.oldRiskSituation,
            RiskLevelBefore: CustomizeRiskConfig.RiskId[model.oldRiskSituation],
            ModifyDay: date.Format("yyyy-MM-dd"),
            ModifyTime: date.Format("hh:mm:ss"),
            RiskItems: []
        }
        function GetRiskItem(id, items) {
            if (!ItemStack[id]) {
                var item = {
                    ItemCode: id,
                    ItemName: CustomizeRiskConfig.RiskItem[id],
                    ItemLabel: CustomizeRiskConfig.RiskItem[id],
                    SubItems: []
                }
                ItemStack[id] = items.push(item);
            }
            return items[ItemStack[id] - 1]
        }

        for (var i = 0; i < model.riskInfoSet.length; i++) {
            var risk_item = GetRiskItem(model.riskInfoSet[i].riskType, result.RiskItems);
            risk_item.SubItems.push({
                ItemCode: model.riskInfoSet[i].riskSubType,
                ItemName: CustomizeRiskConfig.RiskSubItem[model.riskInfoSet[i].riskSubType] || model.riskInfoSet[i].riskContent
            })
        }
        return result;
    }

    function GetCompanyRiskLabel(model, callback) {
        var data = {
            monitorId: $("#monitorId").val()
        }

        applyAjax("{0}/monitorCustomRiskSituation/getLastCustomRisk".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var system_risk = $("#system_risk .risk_level").text().trim();
                var resultModel = {
                    SystemRisk: {
                        RiskId: CustomizeRiskConfig.RiskValue[system_risk],
                        RiskLevel: system_risk,//"特别预警"||"一般预警"||"关注"||“正常”
                        RiskType: 0,
                        RiskItems: [],
                        RiskSuggest: $("#riskResult").val().trim()
                    },
                    CustomizeRisk: result.resultCode == "0" ? BuildCustomizeModel(result.resultData) : null
                }
                callback(resultModel);
            }
        })
    }

    function GetCompanyRiskHistory(model, callback) {
        var lock = ajaxLock.LockGitHistory;
        if (ajaxLock.LockGitHistory) {
            toastr.warning("正在查询中，请勿重复操作！");
            return;
        }
        ajaxLock.LockGitHistory = true;
        var data = {
            monitorId: $("#monitorId").val()
        }
        applyAjax("{0}/monitorCustomRiskSituation/getHistoryMonitorCustomRiskSituationList".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    Page: {
                        TotalCount: result.resultData.length,
                        TotalPage: 1,
                        PageCount: 1,
                        CurrentPage: 1
                    },
                    History: []
                }
                for (var i = 0; i < result.resultData.length; i++) {
                    resultModel.History.push(BuildCustomizeModel(result.resultData[i]));
                }
                callback(resultModel);
            }

        }, null, null, SetTimeOutComplete(function () { ajaxLock.LockGitHistory = false; }))

    }

    function PutCompanyCustomizeRisk(prev, edit, callback) {
        if (ajaxLock.LockPutRisk) {
            toastr.warning("正在提交中，请勿重复操作！");
            return;
        }
        ajaxLock.LockPutRisk = true;
        var data = BuildCustomizeResultmodel(prev, edit);
        applyAjax("{0}/monitorCustomRiskSituation/addCustomRisk".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (result.resultCode == "0") {
                    edit.CustomizeId = result.resultData.customRiskSituation.riskId;
                    var resultModel = { prev: prev, edit: BuildCustomizeModel(result.resultData.customRiskSituation) };
                    callback(resultModel, result.resultData.riskSuggestion);
                }
                else {
                    toastr.warning(result.resultMsg);
                }
            }

        }, null, null, SetTimeOutComplete(function () { ajaxLock.LockPutRisk = false; }));

    }

    function DeleteCompanyCustomizeRisk(model, callback) {
        var lock = ajaxLock.LockDeleteRisk;
        if (ajaxLock.LockDeleteRisk) {
            toastr.warning("正在提交中，请勿重复操作！");
            return;
        }
        ajaxLock.LockDeleteRisk = true;
        var data = {
            monitorId: $("#monitorId").val()
        }
        applyAjax("{0}/monitorCustomRiskSituation/cleanCustomRisk".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                // var result = {
                //     resultCode: "0",
                //     resultMsg: "",
                //     resultData: {}
                // }
                if (result.resultCode == "0") {
                    callback(result.resultData);
                }
                else {
                    toastr.warning(result.resultMsg);
                }
            }

        }, null, null, SetTimeOutComplete(function () { ajaxLock.LockDeleteRisk = false; }));

    }

    function PutHistoryCustomizeRisk(prev, edit, callback) {
        var lock = ajaxLock.LockPutHistory;
        if (ajaxLock.LockPutHistory) {
            toastr.warning("正在提交中，请勿重复操作！");
            return;
        }
        ajaxLock.LockPutHistory = true;
        var data = BuildCustomizeResultmodel(prev, edit);
        applyAjax("{0}/monitorCustomRiskSituation/updateCustomRisk".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (result.resultCode == "0") {
                    var resultModel = { prev: prev, edit: BuildCustomizeModel(result.resultData) }
                    callback(resultModel);
                }
                else {
                    toastr.warning(result.resultMsg);
                }
            }

        }, null, null, SetTimeOutComplete(function () { ajaxLock.LockPutHistory = false; }));
    }

    function DeleteHistoryCustomizeRisk(model, callback) {
        var lock = ajaxLock.LockPutHistory;
        if (ajaxLock.LockPutHistory) {
            toastr.warning("正在提交中，请勿重复操作！")
            return;
        }
        ajaxLock.LockPutHistory = true;
        var data = {
            customRiskId: model.customRiskId
        }
        applyAjax("{0}/monitorCustomRiskSituation/deleteCustomRisk".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                // var result = {
                //     resultCode: "0",
                //     resultMsg: "",
                //     resultData: {
                //         index:model.index
                //     }
                // }
                if (result.resultCode == "0") {
                    callback({ index: model.index });
                }
                else {
                    toastr.warning(result.resultMsg);
                }
            }
        }, null, null, SetTimeOutComplete(function () { ajaxLock.LockPutHistory = false; }))

    }

    var CompanyMonitorRepository = function () { }

    CompanyMonitorRepository.prototype = {
        get_company_risk_label: function (model, callback) {
            GetCompanyRiskLabel(model, callback);
        },
        get_company_risk_history: function (model, callback) {
            GetCompanyRiskHistory(model, callback);
        },
        put_company_customize_risk: function (prev, edit, callback) {
            PutCompanyCustomizeRisk(prev, edit, callback);
        },
        delete_company_customize_risk: function (model, callback) {
            DeleteCompanyCustomizeRisk(model, callback);
        },
        put_history_customize_risk: function (prev, edit, callback) {
            PutHistoryCustomizeRisk(prev, edit, callback);
        },
        delete_history_customize_risk: function (model, callback) {
            DeleteHistoryCustomizeRisk(model, callback);
        }
    }
    CompanyMonitorRepository.prototype.constructor = CompanyMonitorRepository;
    return CompanyMonitorRepository;
}();

var companyMonitorCustomizeController = function () {
    var repository = new CompanyMonitorRepository();

    var PageModel = {
        CompanyName: $("#companyName").val()
    }
    var ModuleList = {
        //风险自定义模块对象
        CustomizeModule: function (data) {
            var CustomizeRisk = function () {
                function get_risk_item(RiskItems, name, code) {
                    if (RiskItems.length > 0) {
                        for (var i = 0; i < RiskItems.length; i++) {
                            if (RiskItems[i].ItemName == name) {
                                return RiskItems[i];
                            }
                        }
                    }
                    var item = {
                        ItemName: name,
                        ItemCode: code,
                        SubItems: []
                    }
                    RiskItems.push(item);
                    return item;
                }

                function RemoveByValue(arr, val) {
                    for (var i = 0; i < arr.length; i++) {
                        if (arr[i].ItemCode == val) {
                            arr.splice(i, 1);
                            break;
                        }
                    }
                }

                function RemoveItem(arr, val) {
                    for (var i = 0; i < arr.length; i++) {
                        if (arr[i].ItemName == val) {
                            arr.splice(i, 1);
                            break;
                        }
                    }
                }

                var CustomizeRisk = function (data) {
                    var date = new Date();
                    this.CustomizeId = data.CustomizeId || null;
                    this.RiskType = data.RiskType;
                    this.RiskId = data.RiskId || "0";
                    this.RiskLevel = data ? data.RiskLevel : "正常";
                    this.RiskTypeBefore = data.RiskTypeBefore || null;
                    this.RiskIdBefore = data ? data.RiskIdBefore : null;
                    this.RiskLevelBefore = data ? data.RiskLevelBefore : null;
                    this.RiskItems = data ? data.RiskItems : new Array();
                    this.ModifyDay = data ? data.ModifyDay : "{0}-{1}-{2}".format(date.getFullYear(), date.getMonth() + 1, date.getDate());
                    this.ModifyTime = data ? data.ModifyTime : "{0}:{1}:{2}".format(date.getHours(), date.getMinutes(), date.getSeconds());
                    this.RiskSuggest = data ? data.RiskSuggest : null;
                }
                CustomizeRisk.prototype = {
                    push_risk_item: function (item_name, item_code, sub_item, sub_code) {
                        var item = get_risk_item(this.RiskItems, item_name, item_code);
                        RemoveByValue(item.SubItems, sub_code);
                        item.SubItems.push({ ItemName: sub_item, ItemCode: sub_code });
                    },
                    pop_risk_item: function (item_name, item_code, sub_item, sub_code) {
                        var item = get_risk_item(this.RiskItems, item_name, item_code);
                        RemoveByValue(item.SubItems, sub_code);
                        if (item.SubItems.length <= 0) {
                            RemoveItem(this.RiskItems, item_name);
                        }
                    },
                    clear_risk_item: function (item_name, item_code) {
                        var item = get_risk_item(this.RiskItems, item_name, item_code);
                        item.SubItems = new Array();
                    },
                    reset_time: function () {
                        var date = new Date();
                        this.ModifyDay = "{0}-{1}-{2}".format(date.getFullYear(), date.getMonth() + 1, date.getDate());
                        this.ModifyTime = "{0}:{1}:{2}".format(date.getHours(), date.getMinutes(), date.getSeconds());
                    }
                }
                CustomizeRisk.prototype.constructor = CustomizeRisk;
                return CustomizeRisk;
            }();

            var CustomizeModel = {
                CustomizeLabel: null,//今日最新风险标签
                CustomizeHistory: null,//历史记录
                CurrentCustomize: null,//当前编辑页面原始内容
                CurrentCustomizeEdit: null,//编辑内容
                set_customize_label_callback: null,
                set_customize_label: function (customize) {
                    if (CustomizeModel.CustomizeLabel) {
                        CustomizeModel.CustomizeLabel.CustomizeRisk = customize;
                    }
                    if (CustomizeModel.set_customize_label_callback) {
                        CustomizeModel.set_customize_label_callback(customize)
                    }
                }
            }
            var CustomizeOverlapModel = {
                CustomizeOverlapPage: -1,
                CustomizeOverlapPageMoveing: false,
                RiskLevelCenter: 373,
                RiskLevelMove: 80,
                CurrentRiskLevel: 0,
                RiskLevelRount: {
                    width: 60,
                    height: 60,
                    "font-size": "12px",
                    "line-height": "60px"
                },
                RiskLevelMoveLock: -1,
                RiskLevelDefaultColor: "#666666",
                RiskLevelDefaultBackground: "#d7d7d7",
                RiskLevelHoverBackground: null,
                RiskLevelHoverColor: null,
                MoreRiskItemName: "补充说明",
                MoreRiskItemCode: "15",
                MoreRiskLength: 255,
                CustomizeHistoryMoving: false,
                CustomizeHistoryDelIndex: -1
            }

            function get_risk_level_info(level) {
                var model = {
                    class_name: "",
                    background: null,
                    level: 0,
                    color: "#ffffff",
                    name: level
                }
                var class_name = "";
                switch (level) {
                    case "特别预警":
                        model.class_name = "high_risk_level";
                        model.background = "#ff3030";
                        model.hover = "#d44242"
                        model.level = 0;
                        break;
                    case "一般预警":
                        model.class_name = "middle_risk_level";
                        model.background = "#fec414";
                        model.hover = "#d1a215"
                        model.level = 1;
                        break;
                    case "关注":
                        model.class_name = "low_risk_level";
                        model.background = "#1aa6fa";
                        model.hover = "#138dd7"
                        model.level = 2;
                        break;
                    case "正常":
                        model.class_name = "no_risk_level";
                        model.background = "#1bcc84";
                        model.hover = "#12a86b"
                        model.level = 3;
                        break;
                }
                return model;
            }

            function risk_level_reset() {
                $(".customize_page:not(.hidden) .risk_level_list .risk_level").css({ background: CustomizeOverlapModel.RiskLevelDefaultBackground, color: CustomizeOverlapModel.RiskLevelDefaultColor, left: 0, width: 60, height: 60, "line-height": "60px" })
            }

            function risk_level_move(risk_name) {
                CustomizeOverlapModel.RiskLevelMoveLock = 0;
                var risk_level = get_risk_level_info(risk_name);
                CustomizeOverlapModel.RiskLevelHoverBackground = risk_level.background;
                CustomizeOverlapModel.RiskLevelHoverColor = risk_level.color;
                var left = $(".customize_page:not(.hidden) .risk_level_list .risk_level").css({ background: CustomizeOverlapModel.RiskLevelDefaultBackground, color: CustomizeOverlapModel.RiskLevelDefaultColor }).eq(risk_level.level).css({ background: risk_level.background, color: risk_level.color });
                var move = (CustomizeOverlapModel.CurrentRiskLevel - (risk_level.level + 1)) * CustomizeOverlapModel.RiskLevelMove;
                $(".customize_page:not(.hidden) .risk_level_list .risk_level").each(function (i) {
                    var animateModel = $.extend(true, {}, CustomizeOverlapModel.RiskLevelRount);
                    if (i == risk_level.level) {
                        animateModel.width = 86;
                        animateModel.height = 86;
                        animateModel["font-size"] = "18px";
                        animateModel["line-height"] = "86px";
                    }
                    animateModel.left = move;
                    $(this).animate(animateModel, 500, function () {
                        if (++CustomizeOverlapModel.RiskLevelMoveLock == 4) {
                            CustomizeOverlapModel.RiskLevelMoveLock = -1;
                        }
                    });
                })
            }

            function risk_dischange(original_risk, modify_risk) {
                function search(list, callback) {
                    var result = { result: false, index: 0 };
                    for (var i = 0; i < list.length; i++) {
                        if (callback(list[i])) {
                            result.result = true;
                            result.index = i;
                            break
                        }
                    }
                    return result;
                }
                //1.判断风险等级
                if (original_risk.RiskLevel != modify_risk.RiskLevel) {
                    return false;
                }
                //2.判断风险选项
                if (original_risk.RiskItems.length != modify_risk.RiskItems.length) {
                    return false;
                }
                for (var i = 0; i < original_risk.RiskItems.length; i++) {
                    var key = original_risk.RiskItems[i].ItemName;
                    var search_result = search(modify_risk.RiskItems, function (value) { return value.ItemName == key; })
                    if (search_result.result) {
                        if (original_risk.RiskItems[i].SubItems.length != modify_risk.RiskItems[search_result.index].SubItems.length) {
                            return false;
                        }
                        for (var j = 0; j < original_risk.RiskItems[i].SubItems.length; j++) {
                            var sub_key = original_risk.RiskItems[i].SubItems[j].ItemName;
                            if (!search(modify_risk.RiskItems[search_result.index].SubItems, function (value) { return value.ItemName == sub_key; }).result) {
                                return false;
                            }
                        }
                    } else {
                        return false;
                    }
                }
                return true;
            }

            function customize_overlap_customize_page_load(data) {
                //0.记录当前状态用于判断是否修改
                CustomizeModel.CurrentCustomize = $.extend(true, {}, data);
                CustomizeModel.CurrentCustomizeEdit = $.extend(true, {}, data);

                //1.移动风险等级
                risk_level_reset();
                risk_level_move(data.RiskLevel);

                $("#CustomizeOverlap .customize_page:not(.hidden) .sub_item_list .sub_item").removeClass("selected");
                $("#CustomizeOverlap .customize_page:not(.hidden) .customize_reason").val("");
                if (data.RiskItems.length > 0) {
                    for (var i = 0; i < data.RiskItems.length; i++) {
                        if (data.RiskItems[i].ItemName == CustomizeOverlapModel.MoreRiskItemName) {
                            //3.填充其他风险
                            var value = data.RiskItems[i].SubItems[0] ? data.RiskItems[i].SubItems[0].ItemName : "";
                            $("#CustomizeOverlap .customize_page:not(.hidden) .customize_reason").val(value);
                        } else {
                            //2.填充风险内容
                            for (var j = 0; j < data.RiskItems[i].SubItems.length; j++) {
                                var item = data.RiskItems[i].SubItems[j];
                                $("#CustomizeOverlap .customize_page:not(.hidden) .sub_item:contains('" + item.ItemName + "')").click();
                            }
                        }
                    }
                }
            }

            function customize_overlap_page_next() {
                var page = CustomizeOverlapModel.CustomizeOverlapPage;
                customize_overlap_show(++page);
            }

            function customize_overlap_page_back() {
                var page = CustomizeOverlapModel.CustomizeOverlapPage;
                if (page > 0) {
                    customize_overlap_show(--page);
                }
            }

            function customize_overlap_history_preload(data) {
                CustomizeModel.CustomizeHistory = data;
                if (data.History.length <= 0) {
                    OverlapList.MessageTipsOverlap.show(PageConfig.MessageConfig.CustomizeNoHistoryMessage.name);
                    return;
                }
                if (CustomizeOverlapModel.CustomizeOverlapPage < 0) {
                    CustomizeOverlapModel.CustomizeOverlapPage++;
                    $("#CustomizeOverlap .customize_page:eq(1) .customize_back_btn").addClass("hidden");
                    customize_overlap_show_block();
                }
                customize_overlap_page_next();
            }
            function customize_overlap_history_page_load(data) {
                function customize_history_btn_down() {
                    event.stopPropagation();
                }
                function customize_history_edit_up() {
                    var index = $(this).parents(".customize_history").index();
                    CustomizeOverlapModel.CustomizeHistoryDelIndex = index;
                    CustomizeModel.CurrentCustomize = new CustomizeRisk(CustomizeModel.CustomizeHistory.History[index]);
                    customize_overlap_page_next();
                    event.stopPropagation();
                }

                function customize_history_delete_up() {
                    var index = $(this).parents(".customize_history").index();
                    CustomizeOverlapModel.CustomizeHistoryDelIndex = index;
                    OverlapList.CustomizeRiskOverlap.show_change(function () {
                        if (CustomizeModel.CustomizeLabel.CustomizeRisk.CustomizeId == CustomizeModel.CustomizeHistory.History[index].CustomizeId) {
                            if (CustomizeModel.CustomizeHistory.History.length > 1) {
                                OverlapList.MessageOverlap.show(PageConfig.MessageConfig.HistoryReturnPrevCustomizeMessage.name);
                            } else {
                                OverlapList.MessageOverlap.show(PageConfig.MessageConfig.HistoryCurrentCustomizeDelMessage.name);
                            }
                        } else {
                            OverlapList.MessageOverlap.show(PageConfig.MessageConfig.HistoryCustomizeDelMessage.name);
                        }
                    })
                }

                function customize_history_block_init(history_data, index) {
                    function SubItemJoin(list) {
                        var result = [];
                        for (var i = 0; i < list.length; i++) {
                            result.push(list[i].ItemName);
                        }
                        return result.join(",");
                    }
                    var history = $(tostring(function () {/*
						<div class="customize_history">
							<div class="relative_center_block">
								<div class="round_point inline_div"></div>
							</div>
							<div class="relative_center_block">
								<div class="status_arr inline_div"></div>
							</div>
							<div class="relative_center_block">
								<div class="data_time inline_div">
									<div class="data"></div>
									<div class="time"></div>
								</div>
							</div>
							<div class="history_info_block inline_div">
								<div class="history_state">
									风险状况：<span class="risk_before">""</span>改为<span class="risk_after">""</span>
								</div>
								<div class="history_info">
									<div class="info_list">
										
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
					*/}));
                    var history_info = $(tostring(function () {/*
						<div class="info inline_div_block">
							<div class="risk_item inline_div"></div>
							<div class="risk_sub_item inline_div"></div>
						</div>
					*/}));
                    var history_message = $(tostring(function () {/*
						<div class="info info_message inline_div_block">
							
						</div>
					*/}));
                    var customize_history = $(history).clone();
                    $(customize_history).attr("index", index);
                    $(customize_history).find(".data").text(history_data.ModifyDay);
                    $(customize_history).find(".time").text(history_data.ModifyTime);
                    $(customize_history).find(".risk_before").text('"{0}"'.format(history_data.RiskLevelBefore));
                    $(customize_history).find(".risk_after").text('"{0}"'.format(history_data.RiskLevel));
                    $(customize_history).find(".history_edit_btn").mousedown(customize_history_btn_down);
                    $(customize_history).find(".history_edit_btn").mouseup(customize_history_edit_up);
                    $(customize_history).find(".history_delete_btn").mousedown(customize_history_btn_down);
                    $(customize_history).find(".history_delete_btn").mouseup(customize_history_delete_up);
                    history_data.RiskItems = history_data.RiskItems.sort(function (a, b) {
                        return parseInt(a.ItemCode) > parseInt(b.ItemCode);
                    })
                    for (var i = 0; i < history_data.RiskItems.length; i++) {
                        var info = $(history_info).clone();
                        var item = history_data.RiskItems[i];
                        $(info).find(".risk_item").text(item.ItemName + "：");
                        $(info).find(".risk_sub_item").text(SubItemJoin(item.SubItems));
                        $(customize_history).find(".info_list").append(info);
                    }
                    if (history_data.RiskTypeBefore == 0) {
                        var message = $(history_message).clone();
                        $(message).text("延用系统风险");
                        //$(customize_history).find(".info_list").append(message);
                    }
                    return customize_history;
                }
                $(".customize_risk_history .customize_history_list").empty();
                if (data.History.length > 0) {
                    for (var i = 0; i < data.History.length; i++) {
                        $(".customize_risk_history .customize_history_list").append(customize_history_block_init(data.History[i], i));
                    }
                }
            }
            function customize_overlap_edit_page_load(data) {

            }
            function customize_overlap_show_block() {
                var y_num = ($(window).height() - $(OverlapList.CustomizeRiskOverlap.lap).find(".lap_content").height()) / 2;
                y_num = y_num < 0 ? 10 : y_num;
                OverlapList.CustomizeRiskOverlap.reset({
                    position: {
                        y_pos: $(document).scrollTop() + y_num
                    },
                });
                OverlapList.CustomizeRiskOverlap.show("#CustomizeOverlap");
            }
            function customize_overlap_show(page) {
                if (CustomizeOverlapModel.CustomizeOverlapPageMoveing) {
                    return;
                }
                CustomizeOverlapModel.CustomizeOverlapPageMoveing = true;
                page = page || 0;
                $("#CustomizeOverlap .customize_page").addClass("hidden");
                $("#CustomizeOverlap .customize_page").eq(page).removeClass("hidden");
                if (CustomizeOverlapModel.CustomizeOverlapPage < 0) {
                    customize_overlap_show_block();
                }
                switch (page) {
                    case 0:
                        customize_overlap_customize_page_load(CustomizeModel.CustomizeLabel.CustomizeRisk);
                        break;
                    case 1:
                        customize_overlap_history_page_load(CustomizeModel.CustomizeHistory);
                        break;
                    case 2:
                        customize_overlap_customize_page_load(CustomizeModel.CurrentCustomize);
                        break;
                }

                CustomizeOverlapModel.CustomizeOverlapPage = page;
                CustomizeOverlapModel.CustomizeOverlapPageMoveing = false;
            }

            function customize_item_click() {
                var item = this;
                if ($(item).hasClass("selected")) {
                    customize_item_remove();
                } else {
                    customize_item_push();
                }
            }

            function customize_label_init(data) {
                CustomizeModel.CustomizeLabel = data;
                var system_risk = get_risk_level_info(data.SystemRisk.RiskLevel);
                $("#system_risk .risk_level").addClass(system_risk.class_name);
                if (data.CustomizeRisk) {
                    data.CustomizeRisk = new CustomizeRisk(data.CustomizeRisk);
                    customize_label_show();
                } else {
                    data.CustomizeRisk = new CustomizeRisk(data.SystemRisk);
                    customize_label_hidden();
                }

                CustomizeModel.set_customize_label(CustomizeModel.CustomizeLabel.CustomizeRisk);
            }

            function customize_label_hidden() {
                $(".company_span #companyTagList .customize_span").remove();
                if ($(".company_span #companyTagList .span").length <= 0) {
                    $(".company_span #companyTagList").append("<div class='span span_color_1 inline_div'>无</div>");
                }
                $("#RiskLabelBlock").removeClass("customize_suggest").addClass("system_suggest");
                $("#system_risk .risk_click_btn .value").html("风险自定义");
            }

            function customize_label_show() {
                var customize = CustomizeModel.CustomizeLabel.CustomizeRisk;
                var risk = get_risk_level_info(customize.RiskLevel);
                $("#customize_risk .risk_level").html(risk.name).removeClass().addClass("risk_level").addClass(risk.class_name);
                $("#RiskLabelBlock").removeClass("system_suggest").addClass("customize_suggest");
                $("#system_risk .risk_click_btn .value").html("系统默认");

                $(".company_span #companyTagList .customize_span").remove();
                $(".company_span #companyTagList .span:contains('无')").remove();
                for (var i = 0; i < customize.RiskItems.length; i++) {
                    if (customize.RiskItems[i].ItemName == CustomizeOverlapModel.MoreRiskItemName) {
                        continue;
                    }
                    if (customize.RiskItems[i].SubItems.length > 0) {
                        $(".company_span #companyTagList").append("<div class='span customize_span customize_span_color_{0} inline_div'>{1}</div>".format((i % 4) + 1, customize.RiskItems[i].ItemName))
                    }
                }
                if ($(".company_span #companyTagList .span").length <= 0) {
                    $(".company_span #companyTagList").append("<div class='span span_color_1 inline_div'>无</div>");
                }
            }

            function get_customize_history_model(prev, edit) {
                edit.RiskTypeBefore = prev.RiskType;
                edit.RiskIdBefore = prev.RiskId;
                edit.RiskLevelBefore = prev.RiskLevel;
                return edit;
            }

            function reset_risk_suggest(value) {
                if (value) {
                    $("#suggestion").text(value);
                }
            }

            function put_customize_edit_result(result, suggest) {
                reset_risk_suggest(suggest);
                //0.替换当前风险自定义内容
                result.edit = new CustomizeRisk(result.edit);
                CustomizeModel.CustomizeLabel.CustomizeRisk = get_customize_history_model(result.prev, result.edit);

                //2.关闭弹出框
                OverlapList.CustomizeRiskOverlap.close();
                //3.添加到历史信息
                if (CustomizeModel.CustomizeHistory) {
                    if (CustomizeModel.CustomizeHistory.History.length > 0) {
                        if (result.prev.CustomizeId) {
                            if (CustomizeModel.CustomizeHistory.History[0].CustomizeId != result.edit.CustomizeId && CustomizeModel.CustomizeHistory.History[0].CustomizeId != result.prev.CustomizeId) {
                                CustomizeModel.CustomizeHistory.History.unshift(result.prev);
                            }
                        } else {
                            //上一次操作为恢复系统，不记录历史
                        }
                    } else {
                        if (result.prev.CustomizeId) {
                            CustomizeModel.CustomizeHistory.History.unshift(result.prev);
                        } else {
                            //上一次操作为恢复系统，不记录历史
                        }
                    }
                }

                CustomizeModel.set_customize_label(CustomizeModel.CustomizeLabel.CustomizeRisk)
                //1.修改风险自定义模块
                customize_label_show();
            }

            function put_history_edit_result(result) {

                //1.替换历史风险
                var index = CustomizeOverlapModel.CustomizeHistoryDelIndex;
                result.edit = new CustomizeRisk(result.edit);
                CustomizeModel.CustomizeHistory.History[index] = get_customize_history_model(result.prev, result.edit);
                if (CustomizeModel.CustomizeLabel.CustomizeRisk.CustomizeId == CustomizeModel.CustomizeHistory.History[index].CustomizeId) {
                    CustomizeModel.set_customize_label(CustomizeModel.CustomizeHistory.History[index]);
                    customize_label_show();
                }
                CustomizeOverlapModel.CustomizeHistoryDelIndex = -1;
                //2.返回历史页
                customize_overlap_page_back();
            }

            function hide_customize_risk_label(result) {
                if (result) {
                    reset_risk_suggest(result.riskSuggestion);
                }
                customize_label_hidden();
                if (CustomizeModel.CustomizeHistory) {
                    CustomizeModel.CustomizeHistory.History.unshift(CustomizeModel.CustomizeLabel.CustomizeRisk);
                }
                CustomizeModel.CustomizeLabel.CustomizeRisk = get_customize_history_model(CustomizeModel.CustomizeLabel.CustomizeRisk, new CustomizeRisk(CustomizeModel.CustomizeLabel.SystemRisk));
                CustomizeModel.CustomizeLabel.CustomizeRisk.reset_time();
                CustomizeModel.set_customize_label(CustomizeModel.CustomizeLabel.CustomizeRisk);
                //CustomizeModel.CustomizeHistory = null;
            }

            function message_history_customize_del(result) {
                var index = result.index;
                $(".customize_risk_history .customize_history_list .customize_history:eq(" + index + ")").fadeOut(500, function () {
                    $(this).remove();
                })
                CustomizeModel.CustomizeHistory.History.splice(index, 1);
                CustomizeModel.set_customize_label(CustomizeModel.CustomizeLabel.CustomizeRisk)
            }

            function message_history_customize_del_resystem(result) {
                message_history_customize_del(result);
                customize_label_hidden();
                //CustomizeModel.CustomizeLabel.CustomizeRisk = get_customize_history_model(CustomizeModel.CustomizeLabel.CustomizeRisk, new CustomizeRisk(CustomizeModel.CustomizeLabel.SystemRisk));
                CustomizeModel.set_customize_label(get_customize_history_model(CustomizeModel.CustomizeLabel.CustomizeRisk, new CustomizeRisk(CustomizeModel.CustomizeLabel.SystemRisk)));
                CustomizeModel.CustomizeLabel.CustomizeRisk.reset_time();
            }

            function message_return_prev_customize(result) {
                message_history_customize_del(result);
                CustomizeModel.CustomizeLabel.CustomizeRisk = new CustomizeRisk(CustomizeModel.CustomizeHistory.History[0]);
                customize_label_show();
            }

            function check_more_risk_length(customize) {
                for (var i = 0; i < customize.RiskItems.length; i++) {
                    if (customize.RiskItems[i].ItemName == CustomizeOverlapModel.MoreRiskItemName) {
                        return customize.RiskItems[i].SubItems[0].ItemName.length <= CustomizeOverlapModel.MoreRiskLength;
                    }
                }
                return true;
            }

            return {
                customize_overlap_init: function () {
                    return new overlap({
                        mask: {
                            show: true
                        },
                        scroller: {
                            lock: false,
                            body_hidden: false
                        },
                        position: {
                            type: "absolute",
                            height: "center",
                            width: "center",
                        },
                        close: {
                            show: true,
                            type: "close_2",
                            callBack: function () {
                                CustomizeOverlapModel.CustomizeOverlapPage = -1;
                                $("#CustomizeOverlap .customize_page:eq(1) .customize_back_btn").removeClass("hidden");
                            }
                        },
                        content: {
                            style: {
                                width: "800px",
                                height: "800px",
                                border: "16px solid rgba(0,0,0,0.15)"
                            }
                        }
                    });
                },
                customize_history_delete_overlap_init: function () {
                    return new overlap({
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
                },
                customize_overlap_show: customize_overlap_show,
                customize_overlap_page_back: customize_overlap_page_back,
                customize_overlap_page_next: customize_overlap_page_next,
                customize_overlap_history_page: function () {
                    if (!CustomizeModel.CustomizeHistory) {
                        var model = {
                            name: PageModel.CompanyName
                        }
                        repository.get_company_risk_history(model, customize_overlap_history_preload);
                    } else {
                        customize_overlap_history_preload(CustomizeModel.CustomizeHistory);
                    }
                },
                show_outer_history_btn: function (callback) {
                    function check_history_show(data) {
                        console.log(data);
                        CustomizeModel.CustomizeHistory = data;
                        if (CustomizeModel.CustomizeHistory.History.length > 0) {
                            callback();
                        }
                    }
                    if (!CustomizeModel.CustomizeHistory) {
                        var model = {
                            name: PageModel.CompanyName
                        }
                        repository.get_company_risk_history(model, check_history_show);
                    } else {
                        check_history_show(CustomizeModel.CustomizeHistory);
                    }
                },
                customize_label_init: function () {
                    var model = {
                        name: PageModel.CompanyName
                    }
                    repository.get_company_risk_label(model, customize_label_init);
                },
                customize_label_show: customize_label_show,
                customize_label_hidden: customize_label_hidden,
                customize_items_click: function () {
                    $("#CustomizeOverlap .sub_item_list .sub_item").click(customize_item_click);
                },
                customize_risk_level_click: function () {
                    if (CustomizeOverlapModel.RiskLevelMoveLock < 0) {
                        var name = $(this).text().trim();
                        CustomizeModel.CurrentCustomizeEdit.RiskLevel = name;
                        risk_level_move(name);
                    }
                },
                customize_risk_level_hover_on: function () {
                    CustomizeOverlapModel.RiskLevelHoverBackground = $(this).css("background");
                    CustomizeOverlapModel.RiskLevelHoverColor = $(this).css("color");
                    var info = get_risk_level_info($(this).text().trim());
                    $(this).css({ background: info.hover, color: info.color });
                },
                customize_risk_level_hover_off: function () {
                    $(this).css({ background: CustomizeOverlapModel.RiskLevelHoverBackground, color: CustomizeOverlapModel.RiskLevelHoverColor });
                },
                customize_risk_item_click: function () {
                    var data = CustomizeModel.CurrentCustomizeEdit;
                    var item_name = $(this).parents(".risk_item").find(".item_name").text().trim();
                    var item_code = $(this).parents(".risk_item").find(".item_name").attr("code").trim();
                    var sub_item = $(this).text().trim();
                    var sub_code = $(this).attr("code").trim();
                    if ($(this).hasClass("selected")) {
                        $(this).removeClass("selected");
                        data.pop_risk_item(item_name, item_code, sub_item, sub_code);
                    } else {
                        $(this).addClass("selected");
                        data.push_risk_item(item_name, item_code, sub_item, sub_code);
                    }
                },
                customize_risk_items_click: function () {
                    if ($(this).parents(".risk_item").find(".sub_item_list .sub_item:not(.selected)").length <= 0) {
                        $(this).parents(".risk_item").find(".sub_item_list .selected").click();
                    } else {
                        $(this).parents(".risk_item").find(".sub_item_list .sub_item:not(.selected)").click();
                    }
                },
                customize_risk_more: function () {
                    var data = CustomizeModel.CurrentCustomizeEdit;
                    var item_name = CustomizeOverlapModel.MoreRiskItemName;
                    var item_code = CustomizeOverlapModel.MoreRiskItemCode;
                    var sub_item = $(this).val().trim();
                    var sub_code = $(this).attr("code").trim();
                    if (sub_item.length > CustomizeOverlapModel.MoreRiskLength) {
                        OverlapList.MessageTipsOverlap.show(PageConfig.MessageConfig.CustomizeMoreRiskLongMessage.name);
                    }
                    data.clear_risk_item(item_name, item_code);
                    if (sub_item.length > 0) {
                        data.push_risk_item(item_name, item_code, sub_item, sub_code);
                    } else {
                        data.pop_risk_item(item_name, item_code, sub_item, sub_code);
                    }
                },
                customize_new_risk: function () {
                	
                    if (risk_dischange(CustomizeModel.CurrentCustomize, CustomizeModel.CurrentCustomizeEdit)) {
                        OverlapList.MessageTipsOverlap.show(PageConfig.MessageConfig.CustomizeNoChangeMessage.name);
                        return;
                    }
                    if (!check_more_risk_length(CustomizeModel.CurrentCustomizeEdit)) {
                        OverlapList.MessageTipsOverlap.show(PageConfig.MessageConfig.CustomizeMoreRiskLongMessage.name);
                        return;
                    }
                    //OverlapList.CustomizeRiskOverlap.lock();
                    repository.put_company_customize_risk(CustomizeModel.CurrentCustomize, CustomizeModel.CurrentCustomizeEdit, put_customize_edit_result);
                },
                customize_edit_risk_history: function () {
                    if (risk_dischange(CustomizeModel.CurrentCustomize, CustomizeModel.CurrentCustomizeEdit)) {
                        OverlapList.MessageTipsOverlap.show(PageConfig.MessageConfig.CustomizeNoChangeMessage.name);
                        return;
                    }
                    if (!check_more_risk_length(CustomizeModel.CurrentCustomizeEdit)) {
                        OverlapList.MessageTipsOverlap.show(PageConfig.MessageConfig.CustomizeMoreRiskLongMessage.name);
                        return;
                    }
                    repository.put_history_customize_risk(CustomizeModel.CurrentCustomize, CustomizeModel.CurrentCustomizeEdit, put_history_edit_result);
                },
                bind_history_list_event: function () {
                    var last_position = null;
                    function history_list_click_down(e) {
                        e = e || window.event;
                        CustomizeOverlapModel.CustomizeHistoryMoving = true;
                        $("#CustomizeOverlap .customize_history_list_hover").focus().css({ "z-index": 6000, "cursor": "move" });
                        last_position = e.clientY;
                    }
                    function history_list_click_move(e) {
                        e = e || window.event;
                        if (CustomizeOverlapModel.CustomizeHistoryMoving) {
                            var top = $("#CustomizeOverlap .customize_history_list").scrollTop() - (e.clientY - last_position);
                            last_position = e.clientY;
                            $("#CustomizeOverlap .customize_history_list").scrollTop(top);
                        }
                    }
                    function history_list_click_up(e) {
                        e = e || window.event;
                        CustomizeOverlapModel.CustomizeHistoryMoving = false;
                        $("#CustomizeOverlap .customize_history_list_hover").focus().css({ "z-index": 0, "cursor": "default" });
                        last_position = null;
                    }
                    function history_list_scroll_move(e) {
                        e = e || window.event;
                        var delta = e.wheelDelta || e.detail || e.originalEvent.detail || e.originalEvent.deltaY || -1 * e.originalEvent.wheelDelta;
                        delta = -1 * (delta / Math.abs(delta)) * 100;
                        var top = $("#CustomizeOverlap .customize_history_list").scrollTop() - delta;
                        $("#CustomizeOverlap .customize_history_list").scrollTop(top);
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
                    if (!(isFirefox = navigator.userAgent.indexOf("Firefox") > 0)) {
                        $("#CustomizeOverlap .customize_history_list").mousedown(history_list_click_down);
                    }
                    $("#CustomizeOverlap .customize_history_list").bind('mousewheel', history_list_scroll_move);
                    $("#CustomizeOverlap .customize_history_list").bind('DOMMouseScroll', history_list_scroll_move);
                    $("#CustomizeOverlap .customize_history_list_hover").mousemove(history_list_click_move);
                    $("#CustomizeOverlap .customize_history_list_hover").mouseout(history_list_click_up);
                    $("#CustomizeOverlap .customize_history_list_hover").mouseup(history_list_click_up);
                    $("#CustomizeOverlap .customize_history_list_hover").bind('mousewheel', history_list_scroll_move);
                    $("#CustomizeOverlap .customize_history_list_hover").bind('DOMMouseScroll', history_list_scroll_move);

                },
                load_analysisi_customize: function (callback) {
                    CustomizeModel.set_customize_label_callback = callback;
                    return CustomizeModel.CustomizeLabel ? CustomizeModel.CustomizeLabel.CustomizeRisk : null;
                },
                message_system_customize_restore: function (result) {
                    if (result) {
                        repository.delete_company_customize_risk(null, hide_customize_risk_label)
                    }
                },
                message_history_customize_del: function (result) {
                    if (result) {
                        var index = CustomizeOverlapModel.CustomizeHistoryDelIndex;
                        var model = {
                            customRiskId: CustomizeModel.CustomizeHistory.History[index].CustomizeId,
                            index: index
                        }
                        repository.delete_history_customize_risk(model, message_history_customize_del);
                    }
                    OverlapList.CustomizeRiskOverlap.show("#CustomizeOverlap");
                    CustomizeOverlapModel.CustomizeHistoryDelIndex = -1;
                },
                message_history_customize_del_resystem: function (result) {
                    if (result) {
                        var index = CustomizeOverlapModel.CustomizeHistoryDelIndex;
                        var model = {
                            customRiskId: CustomizeModel.CustomizeHistory.History[index].CustomizeId,
                            index: index
                        }
                        repository.delete_history_customize_risk(model, message_history_customize_del_resystem);
                        //repository.delete_company_customize_risk(null,hide_customize_risk_label);
                    }
                    OverlapList.CustomizeRiskOverlap.show("#CustomizeOverlap");
                    CustomizeOverlapModel.CustomizeHistoryDelIndex = -1;
                },
                message_return_prev_customize: function (result) {
                    if (result) {
                        var index = CustomizeOverlapModel.CustomizeHistoryDelIndex;
                        var model = {
                            customRiskId: CustomizeModel.CustomizeHistory.History[index].CustomizeId,
                            index: index
                        }
                        repository.delete_history_customize_risk(model, message_return_prev_customize);
                        //repository.delete_company_customize_risk(null,hide_customize_risk_label);
                    }
                    OverlapList.CustomizeRiskOverlap.show("#CustomizeOverlap");
                    CustomizeOverlapModel.CustomizeHistoryDelIndex = -1;
                },
                get_system_suggest_block: function () {
                    var system_suggest_str = $(tostring(function () {/*
                        <div>
                            <div class="system_suggest_title">系统风险建议</div>
                            <div class="system_suggest_content"></div>
                        <div>
                    */}))
                    var result = $(system_suggest_str).clone();
                    console.log(CustomizeModel.CustomizeLabel.SystemRisk);
                    $(result).find(".system_suggest_content").append(CustomizeModel.CustomizeLabel.SystemRisk.RiskSuggest)
                    return result;
                }
            }
        }(),
    }

    var PageConfig = {
        MessageConfig: {
            SystemRestoreMessage: {
                name: "SystemRestoreMessage",
                message: "是否确认删除自定义风险状况，恢复系统风险状况?",
                callback: ModuleList.CustomizeModule.message_system_customize_restore
            },
            SystemRestoreSuggestMessage: {
                name: "SystemRestoreSuggestMessage",
                message: "是否确认删除自定义风险状况，恢复系统风险状况?",
                callback: ModuleList.CustomizeModule.message_system_customize_restore
            },
            HistoryCurrentCustomizeDelMessage: {
                name: "HistoryCurrentCustomizeDelMessage",
                message: "是否确定删除该条记录，将恢复为系统风险状况?",
                callback: ModuleList.CustomizeModule.message_history_customize_del_resystem
            },
            HistoryReturnPrevCustomizeMessage: {
                name: "HistoryReturnPrevCustomizeMessage",
                message: "是否确定删除该条记录?",
                callback: ModuleList.CustomizeModule.message_return_prev_customize
            },
            HistoryCustomizeDelMessage: {
                name: "HistoryCustomizeDelMessage",
                message: "是否确定删除该条记录?",
                callback: ModuleList.CustomizeModule.message_history_customize_del
            },
            CustomizeNoChangeMessage: {
                name: "CustomizeNoChangeMessage",
                message: "尚未做任何修改,请填选内容后再确认！",
                callback: null
            },
            CustomizeNoHistoryMessage: {
                name: "CustomizeNoHistoryMessage",
                message: "无历史信息！",
                callback: null
            },
            CustomizeMoreRiskLongMessage: {
                name: "CustomizeMoreRiskLongMessage",
                message: "补充说明长度不能超过255个字！",
                callback: null
            }
        }
    }

    var OverlapList = {
        CustomizeRiskOverlap: null,
        CustomizeMessageOverlap: null,
        MessageOverlap: null,
        MessageInfoOverlap: null,
        MessageTipsOverlap: null,
    }

    var LoadList = {
        load_customize_label: function () {
            var module = ModuleList.CustomizeModule;
            module.customize_label_init();
            return this;
        },
        load_overlaps: function () {
            var CustomizeModule = ModuleList.CustomizeModule;
            OverlapList.CustomizeRiskOverlap = CustomizeModule.customize_overlap_init();
            OverlapList.CustomizeMessageOverlap = CustomizeModule.customize_history_delete_overlap_init();
            OverlapList.MessageOverlap = new MessageOverlap();
            OverlapList.MessageInfoOverlap = new MessageInfoOverlap();
            OverlapList.MessageTipsOverlap = new MessageTipsOverlap();
            return this;
        },
        init_message_overlap: function () {
            var overlap = OverlapList.MessageOverlap;
            var tipsoverlap = OverlapList.MessageTipsOverlap;
            var message = PageConfig.MessageConfig;
            var info = OverlapList.MessageInfoOverlap;
            overlap.push(message.SystemRestoreMessage.name, message.SystemRestoreMessage.message, message.SystemRestoreMessage.callback);
            overlap.push(message.HistoryCustomizeDelMessage.name, message.HistoryCustomizeDelMessage.message, message.HistoryCustomizeDelMessage.callback);
            overlap.push(message.HistoryCurrentCustomizeDelMessage.name, message.HistoryCurrentCustomizeDelMessage.message, message.HistoryCurrentCustomizeDelMessage.callback);
            overlap.push(message.HistoryReturnPrevCustomizeMessage.name, message.HistoryReturnPrevCustomizeMessage.message, message.HistoryReturnPrevCustomizeMessage.callback);
            tipsoverlap.push(message.CustomizeNoChangeMessage.name, message.CustomizeNoChangeMessage.message);
            tipsoverlap.push(message.CustomizeNoHistoryMessage.name, message.CustomizeNoHistoryMessage.message);
            tipsoverlap.push(message.CustomizeMoreRiskLongMessage.name, message.CustomizeMoreRiskLongMessage.message);
            info.push(message.SystemRestoreSuggestMessage.name, message.SystemRestoreSuggestMessage.message, message.SystemRestoreSuggestMessage.callback)
        }
    }

    var EventList = {
        bind_risk_label_click: function () {
            var module = ModuleList.CustomizeModule;
            //绑定风险标签按钮事件
            function customize_risk_show() {
                module.customize_overlap_show(0);
            }
            function customize_risk_btn_click() {
                customize_risk_show();
            }
            function system_risk_btn_click() {
            	console.log("system_risk_btn_click...");
                if ($(".customize_suggest").length > 0) {
                	var showActionBtnFlag = $("#showActionBtnFlag").val();
                	console.log("customize_new_risk,flag="+showActionBtnFlag);
                	if(showActionBtnFlag==false||showActionBtnFlag=="false"){
                		toastr.warning("此评级为系统评定");
                        return;
                	}
                   // console.log(ModuleList.CustomizeModule.get_system_suggest_block());
                    OverlapList.MessageInfoOverlap.show(PageConfig.MessageConfig.SystemRestoreSuggestMessage.name, ModuleList.CustomizeModule.get_system_suggest_block());
                } else if ($(".system_suggest").length > 0) {
                    customize_risk_show();
                }
            }
            $("#customize_risk .risk_click_btn").click(customize_risk_btn_click);
            $("#system_risk .risk_click_btn").click(system_risk_btn_click);
            return this;
        },
        bind_risk_customize_overlap_click: function () {
            var module = ModuleList.CustomizeModule;
            //绑定自定义风险弹出框内一次性触发事件
            //1.修改记录->跳转历史内容
            $("#CustomizeOverlap #CustomizeRiskHistoryBtn").click(module.customize_overlap_history_page);
            //2.自定义确定按钮
            $("#CustomizeOverlap #CustomizeEditNewBtn").click(module.customize_new_risk);
            //3.返回自定义页面
            //4.返回历史信息页面    
            $("#CustomizeOverlap .customize_back_btn").click(module.customize_overlap_page_back);
            //5.编辑页面确定按钮
            $("#CustomizeOverlap #CustomizeEditBtn").click(module.customize_edit_risk_history);
            //6.历史页面拖动事件（滚动条替代）
            module.bind_history_list_event();
            //7.风险等级选择
            $(".customize_page .risk_level_list .risk_level").hover(module.customize_risk_level_hover_on, module.customize_risk_level_hover_off)
            $(".customize_page .risk_level_list .risk_level").click(module.customize_risk_level_click);
            //8.风险预警选择
            $(".customize_page .sub_item_list .sub_item").click(module.customize_risk_item_click);
            $(".customize_page .risk_item .item_icon").click(module.customize_risk_items_click);
            //9.其他风险预警填写
            $(".customize_page .customize_reason").blur(module.customize_risk_more);
            return this;
        }
    }

    function load_init() {
        LoadList
            .load_customize_label()
            .load_overlaps()
            .init_message_overlap();
    }

    function bind_events() {
        EventList
            .bind_risk_label_click()
            .bind_risk_customize_overlap_click();
    }

    var companyMonitorCustomizeController = function () {
        load_init();
        bind_events();
    }

    companyMonitorCustomizeController.prototype = {
        get_company_customize: function (callback) {
            var module = ModuleList.CustomizeModule;
            return module.load_analysisi_customize(callback);
        },
        has_history_customize: function (callback) {
            var module = ModuleList.CustomizeModule;
            module.show_outer_history_btn(callback);
        },
        show_history_overlap: function () {
            var module = ModuleList.CustomizeModule;
            module.customize_overlap_history_page();
        }
    };
    companyMonitorCustomizeController.prototype.constructor = companyMonitorCustomizeController;
    return companyMonitorCustomizeController;
}();