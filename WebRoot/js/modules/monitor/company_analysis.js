var CompanyAnalysisRepository = function () {
    var AnalysisConfig = {
        AnalysisCompanyImage: {
            "主体企业": "company_icon",
            "关系企业": "relative_company_icon",
            "关系自然人": "relative_person_icon",
        },

        AnalysisEventConfig: {
            "01": { value: "法人代表变更", unit: "次", index: 1 },
            "02": { value: "股东变更", unit: "次", index: 3 },
            "03": { value: "高管变更", unit: "次", index: 2 },
            "04": { value: "经营异常", unit: "条", index: 6 },
            "05": { value: "企业更名", unit: "次", index: 4 },
            "06": { value: "经营状态", unit: "条", index: 7 },
            "07": { value: "对外投资", unit: "家", index: 5 },
            "08": { value: "动产质押", unit: "条", index: 28 },
            "09": { value: "股权出质", unit: "条", index: 29 },
            "10": { value: "股权冻结", unit: "条", index: 8 },
            "11": { value: "工商行政处罚", unit: "条", index: 18 },
            "12": { value: "食药监监查", unit: "条", index: 19 },
            "13": { value: "质量监督", unit: "条", index: 20 },
            "14": { value: "社保缴纳", unit: "条", index: 21 },
            "15": { value: "环境核查", unit: "条", index: 22 },
            "16": { value: "欠税信息", unit: "条", index: 23 },
            "17": { value: "税务非正常户", unit: "条", index: 24 },
            "18": { value: "裁判文书", unit: "条", index: 13 },
            "19": { value: "裁判文书（金融合同）", unit: "条", index: 14 },
            "20": { value: "裁判文书（劳务纠纷）", unit: "条", index: 15 },
            "21": { value: "被执行人", unit: "条", index: 16 },
            "22": { value: "失信被执行人", unit: "条", index: 17 },
            "23": { value: "开庭公告", unit: "条", index: 12 },
            "24": { value: "媒体资讯", unit: "条", index: 25 },
            "25": { value: "重点关注舆情", unit: "条", index: 26 },
            "26": { value: "非主流媒体信息", unit: "条", index: 27 },
            "27": { value: "专利", unit: "项", index: 9 },
            "28": { value: "招投标", unit: "项", index: 11 },
            "29": { value: "商标", unit: "项", index: 10 },
            "30": { value: "开庭公告", unit: "条", index: 30 },
            "31": { value: "裁判文书", unit: "条", index: 31 },
            "32": { value: "对外投资", unit: "次", index: 32 },
            "33": { value: "对外任职", unit: "次", index: 33 },
            "34": { value: "黄、赌、毒", unit: "次", index: 34 },
            "35": { value: "被执行人", unit: "条", index: 35 },
            "36": { value: "失信信息", unit: "条", index: 36 },
        }
    }

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

    function get_analysis_list(company, person) {
        function get_analysis_sub_list(module) {
            var list = [];
            if (!module) {
                return list;
            }
            if (module.eventNum && module.eventNum > 0) {
                for (var i = 0; i < module.riskAnalysisList.length; i++) {
                    var model = module.riskAnalysisList[i];
                    model.label = AnalysisConfig.AnalysisEventConfig[model.eventSubType];
                    var data = {
                        label: model.label.value,
                        index: parseInt(model.label.index),
                        times: model.eventNum,
                        level: model.eventLevel > 1 ? 2 : 1,
                        value: "{0}<span>{1}</span>{2}".format(model.label.value, model.eventNum, model.label.unit),
                        color: model.eventLevel > 1 ? 2 : 1
                    }
                    list.push(data);
                }
            }
            if (module.yearNum && module.yearNum > 0) {
                for (var i = 0; i < module.financeAnalysisList.length; i++) {
                    var model = module.financeAnalysisList[i];
                    model.label = model.label
                    var data = {
                        label: model.label,
                        index: 0,
                        times: 0,
                        level: parseInt(model.color) > 1 ? 2 : 1,
                        value: model.label,
                        color: module.color
                    }
                    list.push(data);
                }
            }

            list.sort(function (a, b) {
                return a.index > b.index;
            });
            return list;
        }
        var list;
        if (person) {
            list = [
                {
                    name: "涉诉",
                    list: get_analysis_sub_list(company.shesu)
                },
            ]
        } else {
            list = [
                {
                    name: "经营",
                    list: get_analysis_sub_list(company.jingying)
                },
                {
                    name: "涉诉",
                    list: get_analysis_sub_list(company.shesu)
                },
                {
                    name: "违规",
                    list: get_analysis_sub_list(company.weigui)
                },
                {
                    name: "税务",
                    list: get_analysis_sub_list(company.shuiwu)
                },
                {
                    name: "舆情",
                    list: get_analysis_sub_list(company.yuqing)
                },
                {
                    name: "投融资",
                    list: get_analysis_sub_list(company.tourongzi)
                },
                {
                    name: "财务",
                    list: get_analysis_sub_list(company.caiwu)
                }
            ]
        }
        return list;
    }

    function GetCompanyAnalysis(model, callback) {
        var data = {
            monitorId: $("#monitorId").val()
        }
        applyAjax("{0}/monitorCompanyRiskAnalysis/getMonitorCompanyRiskAnalysis".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (result.resultCode == "0") {
                    var resultData = []
                    if (result.resultData.monitorCompanyRiskAnalysis) {
                        var company = result.resultData.monitorCompanyRiskAnalysis;
                        var model = {
                            name: company.companyName,
                            monitorTime: company.infoShowDate,
                            type: "主体企业",
                            img: "company_icon",
                            customize: {},
                            analysisList: get_analysis_list(company),
                        }
                        resultData.push(model);
                    }
                    if (result.resultData.monitorRelationCompanyRiskAnalysis && result.resultData.monitorRelationCompanyRiskAnalysis.length > 0) {
                        for (var i = 0; i < result.resultData.monitorRelationCompanyRiskAnalysis.length; i++) {
                            var company = result.resultData.monitorRelationCompanyRiskAnalysis[i];
                            var model = {
                                name: company.companyName,
                                monitorTime: company.infoShowDate,
                                type: "关系企业",
                                img: "relative_company_icon",
                                customize: {},
                                analysisList: get_analysis_list(company),
                            }
                            resultData.push(model);
                        }
                    }
                    if (result.resultData.monitorRelationPersonRiskAnalysis && result.resultData.monitorRelationPersonRiskAnalysis.length > 0) {
                        for (var i = 0; i < result.resultData.monitorRelationPersonRiskAnalysis.length; i++) {
                            var company = result.resultData.monitorRelationPersonRiskAnalysis[i];
                            var model = {
                                name: company.personName,
                                monitorTime: company.infoShowDate,
                                type: "关系自然人",
                                img: "relative_person_icon",
                                customize: {},
                                analysisList: get_analysis_list(company, true),
                            }
                            resultData.push(model);
                        }
                    }
                    callback(resultData);
                }
                else {
                    toastr.warning(result.resultMsg);
                }
            }
        })
    }

    function PostCompanyBacklistResult(model, callback) {
        var data = {
        	"monitorId":$("#monitorId").val(),
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
                        reason: result.resultData.customBlackCompany ? regsymbol(BuildCustomizeBlacklistReason(result.resultData.customBlackCompany)) + "。" : ""
                    };
                    callback(resultData);
                }
                else {
                    //toastr.warning(result.resultMsg);
                }
            }
        })

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
            return result.join("；") + "。";
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

            return result.join("；");
        }
    }

    function GetFinanceData(model, callback) {
        var data = {
            companyName: encodeURI(model.companyName)
        }
        applyAjax("{0}/finance/getFinanceData".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (result.respCode == "200") {
                    callback(result.data);
                }
                else {
                    callback(null);
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

    function DeleteBlacklistCompany(model, callback) {
        var data = {
            companyName: encodeURI(model.companyName),
            reason: model.reasons.join(","),
            otherReason: model.otherReason
        }
        applyAjax("{0}/blacklistCompany/remove.do".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (result.resultCode == "0") {
                    var resultData = {};
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
                    var resultData = {};
                    toastr.success("加入成功!");
                    callback(resultData);
                }
                else {
                    toastr.warning(result.resultMsg);
                }
            }
        })
    }

    var CompanyAnalysisRepository = function () { }

    CompanyAnalysisRepository.prototype = {
        get_company_analysis: function (model, callback) {
            GetCompanyAnalysis(model, callback);
        },
        get_finance_data: function (model, callback) {
            GetFinanceData(model, callback);
        },
        get_blacklist_info: function (model, callback) {
            PostCompanyBacklistResult(model, callback);
        },
        get_blacklist_reason: function (model, callback) {
            PostBlacklistReason(model, callback);
        },
        delete_blacklist_company: function (model, callback) {
            DeleteBlacklistCompany(model, callback);
        },
        post_blacklist_company: function (model, callback) {
            PostBlacklistCompany(model, callback);
        }
    }
    CompanyAnalysisRepository.prototype.constructor = CompanyAnalysisRepository;
    return CompanyAnalysisRepository;
}();

var CompanyMonitorAnalysisController = function (controller) {

    var repository = new CompanyAnalysisRepository();
    var customize = null;

    var PageConfig = {
        LabelMoreOption: {
            ShowLabel: {
                value: "详情",
                img: "down_img",
                height: 100,
                auto: false,
            },
            HideLabel: {
                value: "收起",
                img: "up_img",
                height: -1,
                auto: true,
            }
        }
    }

    var OverlapList = {
        OverlapAdd: null,
        OverlapRemove: null
    }

    var Modules = {
        AnalysisModule: function () {
            var AnalysisConfig = {
                AnalysisAnimateLock: false,
            }

            function LoadCompanyAnalysis(result) {
                var block = $(tostring(function () {/*
                    <div class="analysis_block inline_div_block">
                        <div class="analysis_title_block">
                            <div class="inline_div title_icon"></div>
                            <div class="inline_div title_link">
                                <div class="relative_center_block hidden">
                                    <div class="top_radius inline_div"></div>
                                </div>
                                <div class="relative_center_block hidden">
                                    <div class="bottom_radius inline_div"></div>
                                </div>
                            </div>
                            <div class="inline_div analysis_title_name"></div>
                            <div class="inline_div company_monitor_time"></div>
                        </div>
                        <div class="analysis_label_list "></div>
                    </div>
                */}))

                var label = $(tostring(function () {/*
                    <div class="analysis_label">
                        <div class="inline_div analysis_label_name"></div>
                        <div class="analysis_event_list inline_div inline_div_block"></div>
                    </div>
                */}))

                $("#CompanyRiskAnalysis .analysis_list").empty();

                for (var i = 0; i < result.length; i++) {
                    var company = result[i];
                    var analysis_block = $(block).clone();
                    $(analysis_block).find(".title_icon").addClass(company.img);
                    $(analysis_block).find(".analysis_title_name").text("{0} ： {1}".format(company.type, company.name));
                    $(analysis_block).find(".company_monitor_time").text("{0} ~ 至今".format(company.monitorTime));
                    var analysis_empty_label = null;
                    for (var j = 0; j < company.analysisList.length; j++) {
                        var label_level = 0;
                        var analysis = company.analysisList[j];
                        if (analysis.list.length > 0) {
                            var analysis_label = $(label).clone();
                            $(analysis_label).find(".analysis_label_name").text(analysis.name);
                            for (var k = 0; k < analysis.list.length; k++) {
                                var event = analysis.list[k];
                                label_level = label_level < event.color ? event.color : label_level;
                                $(analysis_label).find(".analysis_event_list").append("<div class='inline_div analysis_event analysis_event_{0}'>{1}</div>".format(event.level, event.value));
                            }
                            $(analysis_label).find(".analysis_label_name").addClass("analysis_label_name_{0}".format(label_level));
                            $(analysis_block).find(".analysis_label_list").append(analysis_label);
                        } else {
                            if (!analysis_empty_label) {
                                analysis_empty_label = $(label).clone();
                                $(analysis_empty_label).empty().addClass("analysis_empty_label");
                            }
                            $(analysis_empty_label).append("<div class='inline_div analysis_label_name analysis_label_name_{0}'>{1}</div>".format(0, analysis.name));
                        }
                    }
                    if (analysis_empty_label) {
                        $(analysis_empty_label).append("<div class='analysis_event_list inline_div inline_div_block'><div class='inline_div analysis_event analysis_event_{0}'>{1}</div></div>".format(0, "无信息"));
                        $(analysis_block).find(".analysis_label_list").append(analysis_empty_label);
                    }
                    $("#CompanyRiskAnalysis .analysis_list").append(analysis_block);
                }
                ResetAreaHeight();
                Events.bind_analiysis_event();
            }

            function LoadCompanyCustomize(result) {
                var analysis_block = $("#CompanyRiskAnalysis .analysis_list .analysis_block:first");
                $(analysis_block).find(".analysis_label_list .analysis_customize").remove();
                if (result && result.CustomizeId) {
                    function SubItemJoin(list) {
                        var result = [];
                        for (var i = 0; i < list.length; i++) {
                            result.push(list[i].ItemName);
                        }
                        return result.join("，");
                    }

                    var customize_str = $(tostring(function () {/*
                        <div class="analysis_label analysis_customize ">
                            <div class="relative_right_block">
                                <div class="inline_div show_customize_history hidden">历史纪录</div>
                            </div>
                            <div class="inline_div analysis_label_name">风险自定义</div>
                            <div class="analysis_event_list inline_div_block"></div>
                            <div class="relative_right_block">
                                <div class="inline_div customize_time hidden"></div>
                            </div>
                        </div>
                    */}))

                    var event_str = $(tostring(function () {/*
                        <div class="inline_div analysis_event inline_div_block">
                            <div class="customize_risk_label inline_div"></div>
                            <div class="customize_risk_event inline_div"></div>
                        </div>
                    */}))

                    function show_customize_history_btn() {
                        $(analysis_customize).find(".show_customize_history").removeClass("hidden");
                    }

                    var analysis_customize = $(customize_str).clone();
                    $(analysis_customize).find(".show_customize_history").click(customize.show_history_overlap);
                    customize.has_history_customize(show_customize_history_btn);
                    //1.判断风险等级
                    if (result.RiskLevel != result.RiskLevelBefore) {
                        var analysis_event = $(event_str).clone();
                        $(analysis_event).find(".customize_risk_label").text("风险调整：");
                        $(analysis_event).find(".customize_risk_event").text("“{0}”改为“{1}”".format(result.RiskLevelBefore, result.RiskLevel));
                        $(analysis_customize).find(".analysis_event_list").append(analysis_event);
                    }
                    result.RiskItems = result.RiskItems.sort(function (a, b) {
                        return parseInt(a.ItemCode) > parseInt(b.ItemCode);
                    });
                    for (var i = 0; i < result.RiskItems.length; i++) {
                        var analysis_event = $(event_str).clone();
                        result.RiskItems[i].SubItems = result.RiskItems[i].SubItems.sort(function (a, b) {
                            return parseInt(a.ItemCode) > parseInt(b.ItemCode);
                        });
                        $(analysis_event).find(".customize_risk_label").text("{0}：".format(result.RiskItems[i].ItemName));
                        $(analysis_event).find(".customize_risk_event").text(SubItemJoin(result.RiskItems[i].SubItems));
                        $(analysis_customize).find(".analysis_event_list").append(analysis_event);
                    }
                    $(analysis_customize).find(".customize_time").text("{0} {1}".format(result.ModifyDay, result.ModifyTime));
                    //2.显示风险内容
                    if ($(analysis_block).find(".analysis_label_list .analysis_blacklist").length > 0) {
                        $(analysis_block).find(".analysis_label_list .analysis_blacklist:first").before(analysis_customize)
                    } else {
                        $(analysis_block).find(".analysis_label_list").append(analysis_customize);
                    }
                }
                ResetAreaHeight();
            }

            function LoadBlacklistMessage(result) {
                var analysis_block = $("#CompanyRiskAnalysis .analysis_list .analysis_block:first");
                $(analysis_block).find(".analysis_label_list .analysis_blacklist").remove();
                if (result && (result.user_blacklist || result.blacklist)) {

                    var blacklist_str = $(tostring(function () {/*
                        <div class="analysis_label analysis_blacklist ">
                            <div class="inline_div analysis_label_name">失&nbsp;信&nbsp;信&nbsp;息</div>
                            <div class="analysis_event_list inline_div_block"></div>
                            <div class="relative_right_block">
                                <div class="inline_div customize_time"></div>
                            </div>
                        </div>
                    */}))

                    var event_str = $(tostring(function () {/*
                        <div class="inline_div analysis_event inline_div_block">
                            <div class="customize_risk_label inline_div"></div>
                            <div class="customize_risk_event inline_div"></div>
                        </div>
                    */}))

                    var analysis_blacklist = $(blacklist_str).clone();
                    if (result.blacklist) {
                        var system_event = $(event_str).clone();
                        $(system_event).find(".customize_risk_label").html("系统记录：");
                        $(system_event).find(".customize_risk_event").html(result.message);
                        $(analysis_blacklist).find(".analysis_event_list").append(system_event);
                    }
                    if (result.user_blacklist) {
                        var customize_event = $(event_str).clone();
                        $(customize_event).find(".customize_risk_label").html("自&nbsp;&nbsp;定&nbsp;义<div class='inline_div' style='width:1px;'></div>：");
                        $(customize_event).find(".customize_risk_event").html(result.reason);
                        $(analysis_blacklist).find(".analysis_event_list").append(customize_event);
                    }
                    $(analysis_block).find(".analysis_label_list").append(analysis_blacklist);
                }
                ResetAreaHeight();
            }

            function ResetAreaHeight() {
                PageConfig.LabelMoreOption.HideLabel.height = 0;
                $("#CompanyRiskAnalysis .analysis_list .analysis_block").each(function () {
                    PageConfig.LabelMoreOption.HideLabel.height += $(this).outerHeight();
                })
            }

            function AreaToggle() {
                if (AnalysisConfig.AnalysisAnimateLock) {
                    return;
                }
                AnalysisConfig.AnalysisAnimateLock = true;
                var config = PageConfig.LabelMoreOption;
                var value = $(this).find(".option_value").text().trim();
                var current = null;
                $(this).find(".option_img").addClass("hidden");
                switch (value) {
                    case config.ShowLabel.value:
                        current = config.HideLabel;
                        break;
                    case config.HideLabel.value:
                        current = config.ShowLabel;
                        break;
                }
                if (current) {
                    $(this).find(".option_value").text(current.value);
                    $(this).find(".{0}".format(current.img)).removeClass("hidden");
                    $("#CompanyRiskAnalysis .analysis_list").stop().animate({ height: current.height }, 500, function () {
                        if (current.auto) {
                            $(this).css("height", "auto");
                        }
                        AnalysisConfig.AnalysisAnimateLock = false;
                    });
                } else {
                    AnalysisConfig.AnalysisAnimateLock = false;
                }
            }

            function AnalysisBlockToggle() {
                if (AnalysisConfig.AnalysisAnimateLock) {
                    return;
                }
                AnalysisConfig.AnalysisAnimateLock = true;
                var _this = this;
                var hidden = $(_this).hasClass("company_hidden");
                var height = 0;
                if (hidden) {
                    $(_this).parents(".analysis_block").find(".analysis_label_list .analysis_label").each(function () {
                        height += $(this).outerHeight();
                        $(this).show();
                    })
                    $(_this).removeClass("company_hidden");

                } else {
                    $(_this).addClass("company_hidden");
                }
                $(_this).parents(".analysis_block").find(".analysis_label_list").stop().animate({ height: height }, 500, function () {
                    if (!hidden) {
                        $(this).find(".analysis_label").hide();
                    }
                    ResetAreaHeight();
                    AnalysisConfig.AnalysisAnimateLock = false;
                });

            }

            var module = {
                load_company_analysis: function (result) {
                    LoadCompanyAnalysis(result);
                    LoadCompanyCustomize(customize.get_company_customize(LoadCompanyCustomize));
                    LoadBlacklistMessage(Modules.BlacklistModule.get_company_blacklist(LoadBlacklistMessage));
                },
                reset_area_height: ResetAreaHeight,
                area_toggle: AreaToggle,
                analysis_block_toggle: AnalysisBlockToggle
            }
            return module;
        }(),
        FinanceModule: function () {
            function IsEmptyData(obj) {
                var index = 0;
                if (obj) {
                    for (var year in obj) {
                        index++;
                    }
                }
                if (index > 0) {
                    return false;
                }
                return true;
            }
            function finance_data_callback(result) {
                if (result == null || IsEmptyData(result.gxzb)) {
                    toastr.warning("该企业当前无更新的财务信息！");
                } else {
                    var deep = parseInt($("#deep").val()) + 1;
                    window.location.href = "{0}/finance/toFinanceView.do?cn={1}&deep={2}".format(ctx, encodeURI($("#companyName").val()), deep);
                }
            }
            return {
                show_finance_data: function () {
                    var model = {
                        companyName: $("#companyName").val()
                    }
                    repository.get_finance_data(model, finance_data_callback);
                }
            }
        }(),
        BlacklistModule: function () {
            var blacklist = new BlacklistController();

            var ModuleData = {
                BlacklistInfo: null,
                BlacklistReason: null,
                CurrentCompany: null,
                BlacklistAnalysisCallback: null,
                BlacklistAnalysisSet: function (result) {
                    if (!ModuleData.BlacklistInfo) {
                        ModuleData.BlacklistInfo = result;
                    }
                    if (ModuleData.BlacklistAnalysisCallback) {
                        ModuleData.BlacklistAnalysisCallback(result);
                    }
                }
            }

            var ModuleConfig = {
                BlacklistAddText: "加入失信",
                BlacklistRemoveText: "移除失信",
            }

            function click_reason_input() {
                var _this = this;
                function show_subcontent() {
                    $(_this).parents(".reason").find(".sub_content textarea").attr("readonly", false).focus();
                }
                function close_subcontent() {
                    $(_this).parents(".reason").find(".sub_content textarea").attr("readonly", true).val("").blur();
                }
                $(_this).is(':checked') ? show_subcontent() : close_subcontent();
            }

            function load_add_blacklist_reason() {
                function get_subcontent() {
                    var result = $("<div class='sub_content'></div>");
                    $(result).append("<textarea placeholder='请输入其它原因，不超过50个字' readonly='readonly'></textarea>");
                    return result;
                }
                function get_reason(i, data) {
                    var result = $("<div class='reason' index='{0}'></div>".format(i));
                    var main = $("<div class='main'></div>")
                    var check_box = $("<input type='checkbox'/>").click(click_reason_input);

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
                    for (var i in ModuleData.BlacklistReason.addreason) {
                        $("#OverlapAddBlock .reason_list").append(get_reason(i, ModuleData.BlacklistReason.addreason[i]));
                    }
                    $("#OverlapAddBlock .company_name").html(ModuleData.CurrentCompany);
                }
                show_add_reasons();
                OverlapList.OverlapAdd.show("#OverlapAddBlock");
                return this;
            }

            function load_remove_blacklist_reason() {
                function get_subcontent() {
                    var result = $("<div class='sub_content'></div>");
                    $(result).append("<textarea placeholder='请输入其它原因，不超过50个字' readonly='readonly'></textarea>");
                    return result;
                }
                function get_reason(i, data) {
                    var result = $("<div class='reason' index='{0}'></div>".format(i));
                    var main = $("<div class='main'></div>")
                    var check_box = $("<input type='checkbox'/>").click(click_reason_input);

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
                    for (var i in ModuleData.BlacklistReason.removereason) {
                        $("#OverlapRemoveBlock .reason_list").append(get_reason(i, ModuleData.BlacklistReason.removereason[i]));
                    }
                    $("#OverlapRemoveBlock .company_name").html(ModuleData.CurrentCompany);
                }
                show_add_reasons();
                OverlapList.OverlapRemove.show("#OverlapRemoveBlock");
                return this;
            }

            function show_blacklist_overlap() {
                var value = $("#BlacklistPushBtn span").text();
                ModuleData.CurrentCompany = $("#companyName").val();
                if (value == ModuleConfig.BlacklistRemoveText) {
                    load_remove_blacklist_reason();
                } else {
                    load_add_blacklist_reason();
                }
            }

            function show_blacklist_tips_message() {
                $("#BlacklistTipsMessage").show();
            }

            function hide_blacklist_tips_message() {
                $("#BlacklistTipsMessage").hide();
            }

            function show_blacklist_tips(result) {
                ModuleData.BlacklistInfo = result;
                if (result.user_blacklist) {
                    $("#BlacklistPushBtn span").text(ModuleConfig.BlacklistRemoveText);
                } else {
                    $("#BlacklistPushBtn span").text(ModuleConfig.BlacklistAddText);
                }
                $("#BlacklistPushBtn").click(show_blacklist_overlap);
                $("#BlacklistPushBtn").removeClass("hidden");
                if (result.user_blacklist || result.blacklist) {
                    if ($(".company_span #companyTagList .span:contains('失信')").length <= 0) {
                        $(".company_span #companyTagList .span:contains('无')").remove();
                        var blacklist_span = $("<div class='span span_color_2 inline_div blacklist_span blacklist_span_color_3'>失信</div>")
                        if ($(".company_span #companyTagList .customize_span").length > 0) {
                            $(".company_span #companyTagList .customize_span:first").before(blacklist_span);
                        } else {
                            $(".company_span #companyTagList").append(blacklist_span);
                        }
                    }
                } else {
                    $(".company_span #companyTagList .span:contains('失信')").remove();
                    if ($(".company_span #companyTagList .span").length <= 0) {
                        $(".company_span #companyTagList").append("<div class='span span_color_1 inline_div'>无</div>")
                    }
                }
                ModuleData.BlacklistAnalysisSet(result);
            }

            function save_blacklist_reason(result) {
                ModuleData.BlacklistReason = result;
            }

            function bind_other_reason_validate() {
                $("#OverlapAddBlock .sub_content textarea").bind("blur")
                $("#OverlapRemoveBlock .sub_content textarea").bind("blur")
            }

            return {
                load_blacklist_info: function () {
                    var model = {
                        companyName: $("#companyName").val()
                    }
                    repository.get_blacklist_info(model, show_blacklist_tips);
                },
                load_blacklist_reason: function () {
                    var model = {};
                    repository.get_blacklist_reason(model, save_blacklist_reason);
                },
                get_blacklist_reason: function () {
                    return ModuleData.BlacklistReason;
                },
                get_current_company: function () {
                    return ModuleData.CurrentCompany;
                },
                get_company_blacklist: function (callback) {
                    ModuleData.BlacklistAnalysisCallback = callback;
                    return ModuleData.BlacklistInfo || null;
                }
            }
        }()
    }

    var Loaders = {
        load_overlap_list: function () {
            OverlapList.OverlapAdd = get_add_block_overlap();
            OverlapList.OverlapRemove = get_remove_block_overlap();
            return this;

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
        },
        load_company_analysis: function () {
            var module = Modules.AnalysisModule;
            repository.get_company_analysis(null, module.load_company_analysis);
            return this;
        },
        load_page_info: function () {
            var module = Modules.AnalysisModule;
            module.reset_area_height();
            return this;
        },
        load_blacklist_info: function () {
            var module = Modules.BlacklistModule;
            module.load_blacklist_info();
            return this;
        },
        load_blacklist_reason: function () {
            var module = Modules.BlacklistModule;
            module.load_blacklist_reason();
            return this;
        }
    }

    var Events = {
        bind_area_event: function () {
            var module = Modules.AnalysisModule;
            $("#CompanyRiskAnalysis .label_more").click(module.area_toggle);
            return this;
        },
        bind_analiysis_event: function () {
            var module = Modules.AnalysisModule;
            $("#CompanyRiskAnalysis .analysis_block .analysis_title_name").click(module.analysis_block_toggle);
            return this;
        },
        bind_finance_click_event: function () {
            var module = Modules.FinanceModule;
            $("#finance_data_btn").click(module.show_finance_data)
            return this;
        },
        bind_overlap_click: function () {
            var module = Modules.BlacklistModule;
            function get_reason(block, reason) {
                var result = {
                    reasons: [],
                    sub_reason: "",
                    success: true
                }
                $(block).find(".reason_list input:checked").each(function () {
                    var index = parseInt($(this).parents(".reason").attr("index"));
                    var data = reason[index];
                    if (data) {
                        result.reasons.push(data.id);
                        switch (data.type) {
                            case 1:
                                var value = $(this).parents(".reason").find(".sub_content textarea").val();
                                if (value.length > 50) {
                                    result.success = false;
                                    toastr.warning("其它原因过长，请输入不超过50个字");
                                }
                                if (value.length <= 0) {
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
                module.load_blacklist_info(result);
                click_cancel_add_overlap();
            }
            function click_cancel_add_overlap() {
                OverlapList.OverlapAdd.close();
            }
            function click_add_add_overlap() {
                var reasons = get_reason("#OverlapAddBlock", module.get_blacklist_reason().addreason);
                if (!reasons.success) {
                    return;
                }
                if (reasons.reasons.length > 0) {
                    var model = {
                        companyName: module.get_current_company(),
                        reasons: reasons.reasons,
                        otherReason: reasons.sub_reason
                    }
                    repository.post_blacklist_company(model, post_company_result)
                } else {
                    toastr.info('请选择加入原因！');
                }
            }


            function delete_company_result(result) {
                module.load_blacklist_info(result);
                click_cancel_remove_overlap();
            }
            function click_cancel_remove_overlap() {
                OverlapList.OverlapRemove.close();
            }
            function click_remove_remove_overlap() {
                var reasons = get_reason("#OverlapRemoveBlock", module.get_blacklist_reason().removereason);
                if (!reasons.success) {
                    return;
                }
                if (reasons.reasons.length > 0) {
                    var model = {
                        companyName: module.get_current_company(),
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
    }

    function ControllerInit() {
        Loaders
            .load_blacklist_reason()
            .load_overlap_list()
            .load_company_analysis()
            .load_page_info()
            .load_blacklist_info();
        Events
            .bind_area_event()
            .bind_analiysis_event()
            .bind_finance_click_event()
            .bind_overlap_click();
    }

    var CompanyMonitorAnalysisController = function (controller) {
        customize = controller;
        ControllerInit();
    }

    CompanyMonitorAnalysisController.prototype = {

    }
    CompanyMonitorAnalysisController.prototype.constructor = CompanyMonitorAnalysisController;
    return CompanyMonitorAnalysisController;
}();