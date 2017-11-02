
var OverViewRepository = function () {
    function randomvalue() {
        return Math.round(Math.random() * 5000);
    }
    var RepositoryMocks = {
        RiskOverview: {
            total: 0,
            risk_total: 0,
            list: []
        }
    }
    var RepositoryModules = {
        RiskOverviewModules: function () {
            function get_risk_overview_data(model, callback) {
                if (IsFunction(callback)) {
                    var list = [];

                    RepositoryMocks.RiskOverview.total = $("#totalCompanyNum").val();
                    push_risk_value_model("#highRiskCompanyNum", 3);
                    push_risk_value_model("#middleRiskCompanyNum", 2);
                    push_risk_value_model("#lowRiskCompanyNum", 1);
                    push_risk_value_model("#noRiskCompanyNum", 0);
                    console.log(RepositoryMocks.RiskOverview);
                    callback(RepositoryMocks.RiskOverview);
                }
                // applyAjax("{0}/monitorCustomRiskSituation/getLastCustomRisk".format(ctx), data, function (result) {

                // })
            }
            return {
                get_risk_overview_data: get_risk_overview_data
            }
            function push_risk_value_model(id, level) {
                var value = $(id).val();
                if (value && value != "" && value > 0) {
                    var model = {};
                    model.value = value;
                    model.risk = level;
                    RepositoryMocks.RiskOverview.list.push(model)
                    RepositoryMocks.RiskOverview.risk_total += value;
                }
            }
        }(),
        MonitorAreaModules: function () {
            function get_monitor_area_data(model, callback) {
                var data = {
                    accountId:model.accountId
                };
                applyAjax("{0}/monitorCompany/getDistributionOfHighRiskCompanyByParent".format(ctx), data, function (result) {
                    console.log(result);                    
                    if (IsFunction(callback)) {
                        callback(result);
                    }
                })
            }
            return {
                get_monitor_area_data: get_monitor_area_data
            }
        }()
    }

    var OverViewRepository = function () {

    }
    OverViewRepository.prototype = {
        get_risk_overview_data: function (model, callback) {
            RepositoryModules.RiskOverviewModules.get_risk_overview_data(model, callback);
        },
        get_monitor_area_data: function (model, callback) {
            RepositoryModules.MonitorAreaModules.get_monitor_area_data(model, callback);
        }
    }
    OverViewRepository.prototype.constructor = OverViewRepository;
    return OverViewRepository;
}();

var OverViewEchart = function () {

    function clone(obj) {
        var o;
        switch (typeof obj) {
            case 'undefined': break;
            case 'string': o = obj + ''; break;
            case 'number': o = obj - 0; break;
            case 'boolean': o = obj; break;
            case 'object':
                if (obj === null) {
                    o = null;
                } else {
                    if (obj instanceof Array) {
                        o = [];
                        for (var i = 0, len = obj.length; i < len; i++) {
                            o.push(clone(obj[i]));
                        }
                    } else {
                        o = {};
                        for (var k in obj) {
                            o[k] = clone(obj[k]);
                        }
                    }
                }
                break;
            default:
                o = obj; break;
        }
        return o;
    }
    var myChart;
    function echart_set(id, option, event) {
        myChart = echarts.init(document.getElementById(id));
        myChart.setOption(option);
        if ("function" == typeof (event)) {
            event(myChart);
        }
    }
    var Modules = {
        RiskOverviewModules: function () {
            var inner_risk_color = ["#11b975", "#138dd7", "#d1a215", "#d44242"];
            var outer_risk_color = ["#1bcc84", "#1aa6fa", "#fec414", "#e95353"];
            var chart_option = {
                color: ["#d44242", "#ffffff", "#d1a215", "#ffffff", "#138dd7", "#ffffff", "#11b975", "#ffffff",
                    "#e95353", "#ffffff", "#fec414", "#ffffff", "#1aa6fa", "#ffffff", "#1bcc84", "#ffffff"],
                series: [
                    {
                        type: 'pie',
                        selectedMode: 'single',
                        radius: ["30%", '36%'],
                        silent: true,
                        animation: false,
                        label: {
                            normal: {
                                position: 'inner'
                            }
                        },
                        labelLine: {
                            normal: {
                                show: false
                            }
                        },
                        data: [
                            {
                                value: 1,
                                name: '',
                                label: {
                                    normal: { show: false }
                                },
                                itemStyle: {
                                    normal: { color: "#bebebe" }
                                }
                            }
                        ],

                    },
                    {
                        type: 'pie',
                        radius: ['35%', '55%'],
                        silent: false,
                        animation: false,
                        data: [
                            {
                                value: 1,
                                name: '暂无数据',
                                label: {
                                    normal: { show: true, textStyle: { fontWeight: 700, fontSize: 14 } }
                                },
                                itemStyle: {
                                    normal: { color: "#d5dce1" }
                                }
                            }
                        ],
                    }
                ]
            }
            function get_risk_overview_option(data) {
                var option = clone(chart_option);

                var inner_ring = [];
                var outer_ring = [];

                var total_num = data.total;
                var list = data.list;

                if (list.length > 0) {
                    for (var i = 0; i < list.length; i++) {
                        if (list[i].value > 0) {
                            var value = (list[i].value * 100) / total_num;
                            value = value.toFixed(1);
                            push_inner_ring(list[i], value);
                            push_outer_ring(list[i], value);
                        }
                    }
                    option.series[0].data = inner_ring;
                    option.series[1].data = outer_ring;
                }
                else {

                }
                return option;

                function push_inner_ring(model, value) {
                    var risk_pie = {
                        value: value,
                        name: '',
                        label: {
                            normal: { show: false }
                        },
                        itemStyle: {
                            normal: { color: inner_risk_color[model.risk] }
                        }
                    }
                    var blank_pie = {
                        value: 1,
                        name: '',
                        label: {
                            normal: { show: false }
                        },
                        itemStyle: {
                            normal: { color: "#ffffff" }
                        }
                    }
                    inner_ring.push(risk_pie);
                    inner_ring.push(blank_pie);
                }
                function push_outer_ring(model, value) {
                    var risk_pie = {
                        value: value,
                        name: value + "%",
                        label: {
                            normal: { show: true, textStyle: { fontWeight: 700, fontSize: 14 } }
                        },
                        itemStyle: {
                            normal: { color: outer_risk_color[model.risk] }
                        }
                    }
                    var blank_pie = {
                        value: 1,
                        name: '',
                        label: {
                            normal: { show: false }
                        },
                        itemStyle: {
                            normal: { color: "#ffffff" }
                        }
                    }
                    outer_ring.push(risk_pie);
                    outer_ring.push(blank_pie);
                }
            }
            function risk_overview_distribution(id, data) {
                option = get_risk_overview_option(data);
                echart_set(id, option, function (chart) {
                    console.log(chart);
                    chart.on('click', function (params) {
                        console.log(params);
                        if (params.color == "#ffffff") {
                            return;
                        }
                        else {
                            var accountId = $("#currentAccountId").val();
                            switch (params.color) {
                                case outer_risk_color[0]:
                                    toMonitorCompanyListFromSummary(accountId, 'ACCOUNT', '0', '', '', '', '', '');
                                    break;
                                case outer_risk_color[1]:
                                    toMonitorCompanyListFromSummary(accountId, 'ACCOUNT', '1', '', '', '', '', '');
                                    break;
                                case outer_risk_color[2]:
                                    toMonitorCompanyListFromSummary(accountId, 'ACCOUNT', '2', '', '', '', '', '');
                                    break;
                                case outer_risk_color[3]:
                                    toMonitorCompanyListFromSummary(accountId, 'ACCOUNT', '3', '', '', '', '', '');
                                    break;
                            }
                        }
                    });
                });
            }
            return {
                risk_overview_distribution: risk_overview_distribution,
            }
        }(),
        MonitorAreaModules: function () {
            function randomData() {
                return Math.round(Math.random() * 1000);
            }
            var chart_option = {
                tooltip: {
                    show: true,
                },
                visualMap: {
                    min: 0,
                    max: 100,
                    left: 'left',
                    top: 'bottom',
                    text: ['高', '低'],           // 文本，默认为数值文本
                    calculable: false,
                    color: ['#006edd', '#e0ffff'],
                    hoverLink: false
                },
                geo: {
                    map: 'china',
                    roam: false,
                    label: {
                        normal: {
                            show: true,
                            textStyle: {
                                color: 'rgba(0,0,0,0.4)'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            borderWidth: 0,
                            borderColor: 'rgba(0, 0, 0, 0.2)'
                        },
                        emphasis: {
                            areaColor: null,
                            shadowOffsetX: 0,
                            shadowOffsetY: 0,
                            shadowBlur: 20,
                            borderWidth: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
                },
                series: [
                    {
                        name: '',
                        type: 'map',
                        mapType: 'china',
                        roam: false,
                        geoIndex: 0,
                        dataRangeHoverLink: false,
                        label: {
                            normal: {
                                show: true,
                                fontSize:12
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: [
                            { name: '北京', value: randomData() },
                            { name: '天津', value: randomData() },
                            { name: '上海', value: randomData() },
                            { name: '重庆', value: randomData() },
                            { name: '河北', value: randomData() },
                            { name: '河南', value: randomData() },
                            { name: '云南', value: randomData() },
                            { name: '辽宁', value: randomData() },
                            { name: '黑龙江', value: randomData() },
                            { name: '湖南', value: randomData() },
                            { name: '安徽', value: randomData() },
                            { name: '山东', value: randomData() },
                            { name: '新疆', value: randomData() },
                            { name: '江苏', value: randomData() },
                            { name: '浙江', value: randomData() },
                            { name: '江西', value: randomData() },
                            { name: '湖北', value: randomData() },
                            { name: '广西', value: randomData() },
                            { name: '甘肃', value: randomData() },
                            { name: '山西', value: randomData() },
                            { name: '内蒙古', value: randomData() },
                            { name: '陕西', value: randomData() },
                            { name: '吉林', value: randomData() },
                            { name: '福建', value: randomData() },
                            { name: '贵州', value: randomData() },
                            { name: '广东', value: randomData() },
                            { name: '青海', value: randomData() },
                            { name: '西藏', value: randomData() },
                            { name: '四川', value: randomData() },
                            { name: '宁夏', value: randomData() },
                            { name: '海南', value: randomData() },
                            { name: '台湾', value: randomData() },
                            { name: '香港', value: randomData() },
                            { name: '澳门', value: randomData() }
                        ]
                    }
                ]
            }
            function get_monitor_area_option(data) {
                var option = clone(chart_option);

                option.series[0].data = data;
                option.tooltip.formatter = function (params) {
                    var result = "企业数：（-）<br/>特别预警：（-）";

                    for (var i = 0; i < data.length; i++) {
                        if (data[i].name == params.name) {
                            result = "企业数：（" + data[i].total + "）<br/>" + "特别预警：（" + data[i].high + "）";
                            break;
                        }
                    }

                    return result;
                }


                return option;
            }
            function monitor_area_map(id, data) {
                option = get_monitor_area_option(data);
                echart_set(id, option, function (chart) {
                    console.log(chart);
                    chart.on('click', function (params) {
                        console.log(params);
                        var accountId = $("#currentAccountId").val();
                        toMonitorCompanyListFromSummary(accountId, 'ACCOUNT', '', '', params.name, '', '', '');
                    });
                });
            }
            return {
                monitor_area_map: monitor_area_map,
            }
        }()
    }
    var OverViewEchart = function () {

    }
    OverViewEchart.prototype = {
        risk_overview_distribution: function (id, data) {
            Modules.RiskOverviewModules.risk_overview_distribution(id, data);
        },
        monitor_area_map: function (id, data) {
            Modules.MonitorAreaModules.monitor_area_map(id, data);
        }
    }
    OverViewEchart.prototype.constructor = OverViewEchart;
    return OverViewEchart;
}();

var OverViewController = function () {
    var repository = new OverViewRepository();
    var chart = new OverViewEchart();
    var Modules = {
        AccountDetailModules: function () {
            var ModuleModel = {
                CurrentIndex: 0,
            }
            function account_move(move) {
                var index = ModuleModel.CurrentIndex + move;
                if (index >= 0 && index <= $("#AccountList .account").length - 6) {
                    $("#AccountList .account").each(function () {
                        $(this).animate({ left: "-=" + 155 * move + "px" });
                    });
                    ModuleModel.CurrentIndex = index;
                }
            }
            function click_account_move_btn() {
                $("#AccountListLeftBtn").click(function () {
                    account_move(-1);
                })
                $("#AccountListRightBtn").click(function () {
                    account_move(1);
                })
            }
            return {
                click_account_move_btn: click_account_move_btn
            }
        }(),
        RiskOverviewModules: function () {
            function init_risk_overview_data(data) {
                if (data.total > 0) {
                    var total_num = data.total;
                    var list = data.list;
                    for (var i = 0; i < list.length; i++) {
                        var info = $("#OverviewRiskBlock .risk_overview_info_" + list[i].risk);
                        $(info).find(".risk_overview_num").text(list[i].value + "家");
                        $(info).find(".risk_overview_percent").text(((list[i].value * 100) / total_num).toFixed(1) + "%");
                    }
                    $("#OverviewRiskBlock .overview_total").text(data.total + "家");
                }
            }
            function get_risk_overview_data_callback(result) {
                init_risk_overview_data(result);
                chart.risk_overview_distribution("OverviewRiskDistribution", result);
            }
            function load_risk_overview() {
                repository.get_risk_overview_data({}, get_risk_overview_data_callback);
            }

            return {
                load_risk_overview: load_risk_overview
            }
        }(),
        MonitorAreaModules: function () {
            function init_monitor_area_level() {
                $(".monitor_range_list .list .li").each(function () {
                    var value = $(this).find(".num").text();
                    var num = value.substring(0, value.indexOf("%"));
                    num = num * 0.01;
                    var r = 224 + (0 - 224) * num;
                    var g = 255 + (110 - 255) * num;
                    var b = 255 + (221 - 255) * num;
                    $(this).find(".span").css("background", "rgb({0},{1},{2})".format(r.toFixed(0), g.toFixed(0), b.toFixed(0)));
                })
            }
            function get_monitor_area_data_callback(result) {
                init_monitor_area_level();
                chart.monitor_area_map("MonitorAreaMap", result);
            }
            function load_monitor_area() {
                var model = {
                    accountId: $("#currentAccountId").val()
                }
                repository.get_monitor_area_data(model, get_monitor_area_data_callback);
            }
            return {
                load_monitor_area: load_monitor_area
            }
        }()
    }
    var Loader = {
        load_page_chart: function () {
            Modules.RiskOverviewModules.load_risk_overview();
            Modules.MonitorAreaModules.load_monitor_area();
            return this;
        }
    }
    var Binder = {
        bind_page_click: function () {
            Modules.AccountDetailModules.click_account_move_btn();
            return this;
        }
    }

    var OverViewController = function () {
        Loader.load_page_chart();
        Binder.bind_page_click();
    }
    OverViewController.prototype = {

    }
    OverViewController.prototype.constructor = OverViewController;
    return OverViewController;
}();

//跳转到动态监控公司列表页面
function toMonitorCompanyListFromSummary(accountId, showGroupType, riskLevel, updateStatus, province, eventLevel, riskStatus, todayMonitorFlag) {
    var deep = $("#deep").val();

    window.location.href = ctx + "/monitorCompany/toMonitorCompanyList?groupName=全部&currentAccountId="
        + accountId + "&showGroupType=" + showGroupType + "&riskLevel=" + riskLevel + "&updateStatus=" + updateStatus + "&province="
        + encodeURIComponent(province) + "&eventLevel=" + eventLevel + "&riskStatus=" + riskStatus + "&todayMonitorFlag=" + todayMonitorFlag + "&deep=" + (parseInt(deep) + 1);
}

