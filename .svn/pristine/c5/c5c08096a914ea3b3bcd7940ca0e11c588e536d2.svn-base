$(function () {
    var controller = new FinanceController();
    var deep = GetQueryString("deep");
    updateNavMenu(parseInt(deep), "财务分析", window.location.search);
})
var FinanceRepository = function () {
    function GetRandomValue() {
        return Math.random() * 0;
    }
    function GetRandomRate() {
        return (100 - (Math.random() * 0)) * 0.00
    }
    var ResultModel = {
        FinanceResult: {
            FinanceStatus: {
                TotalAssets: {
                    median: GetRandomValue(),
                    evaluation: "近三年连续涨价",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomValue()
                        },
                        {
                            year: "2015",
                            value: GetRandomValue()
                        },
                        {
                            year: "2016",
                            value: GetRandomValue()
                        }
                    ]
                },
                TotalLiabilities: {
                    median: GetRandomValue(),
                    evaluation: "",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomValue()
                        },
                        {
                            year: "2015",
                            value: GetRandomValue()
                        },
                        {
                            year: "2016",
                            value: GetRandomValue()
                        }
                    ]
                },
                TotalIncom: {
                    median: GetRandomValue(),
                    evaluation: "",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomValue()
                        },
                        {
                            year: "2015",
                            value: GetRandomValue()
                        },
                        {
                            year: "2016",
                            value: GetRandomValue()
                        }
                    ]
                },
                NetProfit: {
                    median: GetRandomValue(),
                    evaluation: "",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomValue()
                        },
                        {
                            year: "2015",
                            value: GetRandomValue()
                        },
                        {
                            year: "2016",
                            value: GetRandomValue()
                        }
                    ]
                },
                TotalTax: {
                    median: GetRandomValue(),
                    evaluation: "",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomValue()
                        },
                        {
                            year: "2015",
                            value: GetRandomValue()
                        },
                        {
                            year: "2016",
                            value: GetRandomValue()
                        }
                    ]
                },
            },
            AbilityEvaluation: {
                AssetLiabilityRate: {
                    median: GetRandomRate(),
                    evaluation: "",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomRate()
                        },
                        {
                            year: "2015",
                            value: GetRandomRate()
                        },
                        {
                            year: "2016",
                            value: GetRandomRate()
                        },
                    ]
                },
                NetProfitRate: {
                    median: GetRandomRate(),
                    evaluation: "",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomRate()
                        },
                        {
                            year: "2015",
                            value: GetRandomRate()
                        },
                        {
                            year: "2016",
                            value: GetRandomRate()
                        },
                    ]
                },
                TotalAssetsProfitRate: {
                    median: GetRandomRate(),
                    evaluation: "",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomRate()
                        },
                        {
                            year: "2015",
                            value: GetRandomRate()
                        },
                        {
                            year: "2016",
                            value: GetRandomRate()
                        },
                    ]
                },
                TotalAssetsTurnoverRate: {
                    median: GetRandomRate(),
                    evaluation: "近三年连续涨价",
                    list: [
                        {
                            year: "2014",
                            value: GetRandomRate()
                        },
                        {
                            year: "2015",
                            value: GetRandomRate()
                        },
                        {
                            year: "2016",
                            value: GetRandomRate()
                        },
                    ]
                },
            },
            AnalyzeConclusion: {
                value: "分析结论..."
            }
        },
        DataResult: {
            "respCode": "200",
            "respMsg": "success",
            "data": {
                "tjz": {
                    "2015": {
                        "assgroavg": "880167.3488119479",
                        "assrate": "0.7488237530732964",
                        "liagroavg": "659090.217469936",
                        "netincavg": "-125087.20930835952",
                        "netrate": "-4.337221154176028E-4",
                        "profilerate": "-0.1421175296688778",
                        "ratgroavg": "39372.99566147256",
                        "totalrate": "327.66954835134953",
                        "vendincavg": "2.884040376588157E8"
                    },
                    "2016": {
                        "assgroavg": "80497.23582589622",
                        "assrate": "0.9099473301953763",
                        "liagroavg": "73248.24482788186",
                        "netincavg": "-33009.41098736051",
                        "netrate": "-1.176863955142266",
                        "profilerate": "-0.41006887564133326",
                        "ratgroavg": "7543.62112833093",
                        "totalrate": "0.34844203856320993",
                        "vendincavg": "28048.620949878736"
                    }
                },
                "gxzb": {
                    "2014": {
                        "nbsj": {
                            "ancheyear": "2014",
                            "assgro": "100.0",
                            "email": "--",
                            "empnum": "1",
                            "entname": "象山铭都汽车销售有限公司",
                            "liagro": "--",
                            "maibusinc": "--",
                            "netinc": "--",
                            "personnetinc": "--",
                            "progro": "--",
                            "ratgro": "--",
                            "registeredcapital": "100.00",
                            "tel": "--",
                            "totequ": "100.0",
                            "vendinc": "--"
                        }
                    },
                    "2015": {
                        "nbsj": {
                            "ancheyear": "2015",
                            "assgro": "527.080182",
                            "email": "--",
                            "empnum": "3",
                            "entname": "象山铭都汽车销售有限公司",
                            "liagro": "434.499648",
                            "maibusinc": "329.97",
                            "netinc": "-7.419466",
                            "personnetinc": "-2.473155333333333",
                            "progro": "-7.419466",
                            "ratgro": "0.406306",
                            "registeredcapital": "100.00",
                            "tel": "65892222",
                            "totequ": "92.58",
                            "vendinc": "329.965817"
                        },
                        "zcfzl": {
                            "ancheyear": "2015",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "1327259",
                            "rate": "0.8243520869088566",
                            "totalmc": "3379569",
                            "region": 0.39
                        },
                        "jlrl": {
                            "ancheyear": "2015",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "1890382",
                            "rate": "-0.022485559466300716",
                            "totalmc": "2995492",
                            "region": 0.63
                        },
                        "zcjlrl": {
                            "ancheyear": "2015",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "1900498",
                            "rate": "-0.014076541394227567",
                            "totalmc": "3379569",
                            "region": 0.56
                        },
                        "zzczzl": {
                            "ancheyear": "2015",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "1669906",
                            "rate": "0.6260258462914472",
                            "totalmc": "3379569",
                            "region": 0.49
                        }
                    },
                    "2016": {
                        "nbsj": {
                            "ancheyear": "2016",
                            "assgro": "496.23611",
                            "email": "--",
                            "empnum": "2",
                            "entname": "象山铭都汽车销售有限公司",
                            "liagro": "405.07009",
                            "maibusinc": "39.290599",
                            "netinc": "-1.414514",
                            "personnetinc": "-0.707257",
                            "progro": "-1.414514",
                            "ratgro": "0.030827",
                            "registeredcapital": "100.00",
                            "tel": "13396678706",
                            "totequ": "91.16602",
                            "vendinc": "39.290599"
                        },
                        "zcfzl": {
                            "ancheyear": "2016",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "322950",
                            "rate": "0.8162849938510117",
                            "totalmc": "838045",
                            "region": 0.38
                        },
                        "jlrl": {
                            "ancheyear": "2016",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "534405",
                            "rate": "-0.03600133456860762",
                            "totalmc": "781746",
                            "region": 0.68
                        },
                        "zcjlrl": {
                            "ancheyear": "2016",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "438551",
                            "rate": "-0.0028504858302230364",
                            "totalmc": "838045",
                            "region": 0.52
                        },
                        "zzczzl": {
                            "ancheyear": "2016",
                            "entname": "象山铭都汽车销售有限公司",
                            "rank": "659911",
                            "rate": "0.07917722674393848",
                            "totalmc": "838045",
                            "region": 0.78
                        }
                    }
                }
            }
        }
    }

    function IsFunction(callback) {
        return typeof callback == "function";
    }

    function pushModel(result, model) {
        for (var i = 0; i < model.list.length; i++) {
            var year = model.list[i].year;
            if (!result[year]) {
                result[year] = {
                    year: year,
                    data: []
                };
            }
            var value = parseFloat(model.list[i].value);
            result[year].data.push(value);
        }
        return result;
    }

    function BuildFinanceStatusEchart(data) {
        var result = {
            "中位数": {
                year: '中位数',
                data: [parseFloat(data.TotalAssets.median) || null, parseFloat(data.TotalLiabilities.median) || null, parseFloat(data.TotalIncom.median) || null, parseFloat(data.NetProfit.median) || null, parseFloat(data.TotalTax.median) || null]
            }
        };
        result = pushModel(result, data.TotalAssets);
        result = pushModel(result, data.TotalLiabilities);
        result = pushModel(result, data.TotalIncom);
        result = pushModel(result, data.NetProfit);
        result = pushModel(result, data.TotalTax);
        return result;
    }

    function BuildAbilityEvaluationEchart(data) {
        var result = {
            "中位数": {
                year: '中位数',
                data: [parseFloat(data.AssetLiabilityRate.median) || null, parseFloat(data.NetProfitRate.median) || null, parseFloat(data.TotalAssetsProfitRate.median) || null, parseFloat(data.TotalAssetsTurnoverRate.median) || null]
            }
        };
        result = pushModel(result, data.AssetLiabilityRate);
        result = pushModel(result, data.NetProfitRate);
        result = pushModel(result, data.TotalAssetsProfitRate);
        result = pushModel(result, data.TotalAssetsTurnoverRate);
        return result;
    }

    function BuildAnalyzeConclusionEchart(data) {
        return data;
    }

    function BuildFinanceResult(data) {
        var max_year = null;
        var index = 0;
        for (var year in data.tjz) {
            index++;
            if (!max_year || max_year < parseInt(year)) {
                max_year = year;
            }
        }
        if (!data.eachItemAnalysis) {
            data.eachItemAnalysis = {};
        }
        if (index <= 0) {
            data.tjz = null;
        }
        var result = {
            FinanceStatus: {
                TotalAssets: {
                    median: get_value(data.tjz ? data.tjz[year].assgroavg : null),
                    evaluation: data.eachItemAnalysis.assgro,
                    list: []
                },
                TotalLiabilities: {
                    median: get_value(data.tjz ? data.tjz[year].liagroavg : null),
                    evaluation: data.eachItemAnalysis.liagro,
                    list: []
                },
                TotalIncom: {
                    median: get_value(data.tjz ? data.tjz[year].vendincavg : null),
                    evaluation: data.eachItemAnalysis.vendinc,
                    list: []
                },
                NetProfit: {
                    median: get_value(data.tjz ? data.tjz[year].netincavg : null),
                    evaluation: data.eachItemAnalysis.netinc,
                    list: []
                },
                TotalTax: {
                    median: get_value(data.tjz ? data.tjz[year].ratgroavg : null),
                    evaluation: data.eachItemAnalysis.ratgro,
                    list: []
                },
            },
            AbilityEvaluation: {
                AssetLiabilityRate: {
                    median: get_value(data.tjz ? get_rate(data.tjz[year].assrate) : null),
                    evaluation: data.eachItemAnalysis.zcfzl,
                    list: []
                },
                NetProfitRate: {
                    median: get_value(data.tjz ? get_rate(data.tjz[year].netincavg) : null),
                    evaluation: data.eachItemAnalysis.jlrl,
                    list: []
                },
                TotalAssetsProfitRate: {
                    median: get_value(data.tjz ? get_rate(data.tjz[year].profilerate) : null),
                    evaluation: data.eachItemAnalysis.zcjlrl,
                    list: []
                },
                TotalAssetsTurnoverRate: {
                    median: get_value(data.tjz ? get_rate(data.tjz[year].totalrate) : null),
                    evaluation: data.eachItemAnalysis.zzczzl,
                    list: []
                },
            },
            AnalyzeConclusion: {
                value: data.summaryAnalysis
            },
            count: 0
        }
        if (data.gxzb) {
            for (var year in data.gxzb) {
                var model = data.gxzb[year];
                if (model.zcfzl || model.jlrl || model.zcjlrl || model.zzczzl) {
                    result.AbilityEvaluation.AssetLiabilityRate.list.push({ year: year, value: get_rate(model.zcfzl) });
                    result.AbilityEvaluation.NetProfitRate.list.push({ year: year, value: get_rate(model.jlrl) });
                    result.AbilityEvaluation.TotalAssetsProfitRate.list.push({ year: year, value: get_rate(model.zcjlrl) });
                    result.AbilityEvaluation.TotalAssetsTurnoverRate.list.push({ year: year, value: get_rate(model.zzczzl) });
                }
                if ((model.nbsj.assgro == "--" && model.nbsj.liagro == "--") || (model.nbsj.assgro == null && model.nbsj.liagro == null)) {
                    continue;
                }
                result.FinanceStatus.TotalAssets.list.push({ year: year, value: get_value(model.nbsj.assgro) });
                result.FinanceStatus.TotalLiabilities.list.push({ year: year, value: get_value(model.nbsj.liagro) });
                result.FinanceStatus.TotalIncom.list.push({ year: year, value: get_value(model.nbsj.vendinc) });
                result.FinanceStatus.NetProfit.list.push({ year: year, value: get_value(model.nbsj.netinc) });
                result.FinanceStatus.TotalTax.list.push({ year: year, value: get_value(model.nbsj.ratgro) });
                result.count++;
            }
        }

        return result;

        function get_rate(model) {
            return model ? (model.rate * 100).toFixed(2) : null;
        }

        function get_value(value) {
            return (value || value == 0) ? parseFloat(value).toFixed(2) : null;
        }
    }

    function GetFinanceResult(model, callback) {
        var data = {
            companyName: model.companyName
        }
        applyAjax("{0}/finance/getFinanceData".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (result.respCode == "200") {
                    var resultData = BuildFinanceResult(result.data)
                    //resultData = ResultModel.FinanceResult;
                    resultData.FinanceStatusEchart = BuildFinanceStatusEchart(resultData.FinanceStatus);
                    resultData.AbilityEvaluationEchart = BuildAbilityEvaluationEchart(resultData.AbilityEvaluation);
                    resultData.AnalyzeConclusion = BuildAnalyzeConclusionEchart(resultData.AnalyzeConclusion);
                    switch (resultData.count) {
                        case 0:
                            resultData.ShowAbilityEvaluation = false;
                            break;
                        case 1:
                            var AbilityEvaluation = resultData.AbilityEvaluation;
                            if(AbilityEvaluation.AssetLiabilityRate.list[0]!=undefined&&AbilityEvaluation.AssetLiabilityRate.list[0]!=null){
                            	if (AbilityEvaluation.AssetLiabilityRate.list[0].value == "0.00" || AbilityEvaluation.AssetLiabilityRate.list[0].value == null
                                        || AbilityEvaluation.NetProfitRate.list[0].value == "0.00" || AbilityEvaluation.NetProfitRate.list[0].value == null
                                        || AbilityEvaluation.TotalAssetsProfitRate.list[0].value == "0.00" || AbilityEvaluation.TotalAssetsProfitRate.list[0].value == null
                                        || AbilityEvaluation.TotalAssetsTurnoverRate.list[0].value == "0.00" || AbilityEvaluation.TotalAssetsTurnoverRate.list[0].value == null) {
                                        resultData.ShowAbilityEvaluation = false;
                                    } else {
                                        resultData.ShowAbilityEvaluation = true;
                                    }
                            }
                            
                            break;
                        default:
                            resultData.ShowAbilityEvaluation = true;
                            break;
                    }
                    callback(resultData);
                }
                else {
                    toastr.warning(result.respMsg);
                }
            }
        })
    }

    var FinanceRepository = function () {

    }
    FinanceRepository.prototype = {
        get_finance_result: function (model, callback) {
            GetFinanceResult(model, callback);
        }
    }
    FinanceRepository.prototype.constructor = FinanceRepository;
    return FinanceRepository;
}();

var FinanceEchart = function () {
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

    var Config = {
        FinanceStatusConfig: [0, 500, 1000, 5000, 10000, 1000000, 10000000, 100000000],
        AbilityEvaluationConfig: [0, 10, 50, 100]
    }

    var Options = {
        FinanceStatus: {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                show: true,
                orient: "vertical",
                left: "left",
                top: 0,
                selectedMode: false,
                data: ['2014', '2015', '2016']
            },
            grid: {
                left: '10%',
                right: 70,
                bottom: '2%',
                top: '30',
                containLabel: true
            },
            color: ["#1aa6fa", "#fec414", "#1bcc84"],
            xAxis: [
                {
                    type: 'category',
                    data: ['资产总额', '负债总额', '营业总收入', '净利润', '纳税总额']
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '万元(人民币)',
                    axisLine: { show: false },
                    axisTick: { show: false },
                    axisLabel: {
                        formatter: function (value) {
                            // var list = ["0", "500万", "1000万", "5000万", "10000万", "1000000万", "10000000万", "100000000万", ""];
                            // return (value < 0 && list[Math.abs(value)] != "" ? "-" : "") + list[Math.abs(value)];

                            var result = "";
                            var list = [0, 500, 1000, 5000, 10000, 1000000, 10000000, 100000000]
                            var negetive = "";
                            if (value < 0) {
                                negetive = "-"
                            }
                            value = Math.abs(value);
                            var index = parseInt(value);
                            if (index >= list.length) {
                                negetive = "";
                            } else {
                                if ((value - parseInt(value)) != 0) {
                                    if ((parseInt(value) + 1) >= list.length) {
                                        negetive = "";
                                    } else {
                                        result = (list[parseInt(value) + 1] - list[parseInt(value)]) * (value - parseInt(value)) + list[parseInt(value)];
                                    }
                                } else {
                                    result = list[parseInt(value)];
                                }
                            }
                            var unit = "";
                            if (result == 0) {
                                unit = "";
                            }
                            result = get_number(result);
                            return negetive + result + unit;
                        }
                    }
                }
            ],
            series: [
                {
                    name: '2014',
                    type: 'bar',
                    barWidth: 20,
                    barGap: "0%",
                    barCategoryGap: "65%",
                    data: [320, 332, 301, 334, 390]
                },
                {
                    name: '2015',
                    type: 'bar',
                    barWidth: 20,
                    barGap: "0%",
                    data: [120, 132, 101, 134, 90]
                },
                {
                    name: '2016',
                    type: 'bar',
                    barWidth: 20,
                    barGap: "0%",
                    data: [220, 182, 191, 234, 290]
                },
                {
                    name: '中位数',
                    type: 'line',
                    data: [150, 232, 201, 154, 190]
                }
            ]
        },
        AbilityEvaluation: {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'shadow'
                }
            },
            legend: {
                show: true,
                orient: "vertical",
                left: "left",
                top: 0,
                selectedMode: false,
                data: ['2014', '2015', '2016']
            },
            grid: {
                left: '10%',
                right: 70,
                bottom: '2%',
                top: '30',
                containLabel: true
            },
            color: ["#1aa6fa", "#fec414", "#1bcc84"],
            xAxis: [
                {
                    type: 'category',
                    data: ['资产负债率', '净利润率', '资产净利润率', '总资产周转率'],
                }
            ],
            yAxis: [
                {
                    type: 'value',
                    name: '百分比（%）',
                    axisLine: { show: false },
                    axisTick: { show: false },
                    axisLabel: {
                        formatter: function (value) {
                            var result = "";
                            var list = [0, 10, 50, 100]
                            var negetive = "";
                            if (value < 0) {
                                negetive = "-"
                            }
                            value = Math.abs(value);
                            var index = parseInt(value);
                            if (index >= list.length) {
                                negetive = "";
                            } else {
                                if ((value - parseInt(value)) != 0) {
                                    if ((parseInt(value) + 1) >= list.length) {
                                        negetive = "";
                                    } else {
                                        result = (list[parseInt(value) + 1] - list[parseInt(value)]) * (value - parseInt(value)) + list[parseInt(value)];
                                    }
                                } else {
                                    result = list[parseInt(value)];
                                }
                            }
                            return negetive + get_number(result);//(value < 0 && list[Math.abs(value)] != "" ? "-" : "") + list[Math.abs(value)];
                        }
                    }
                }
            ],
            series: [
                {
                    name: '2014',
                    type: 'bar',
                    barWidth: 20,
                    barGap: "0%",
                    barCategoryGap: "65%",
                    data: [320, 332, 301, 334, 390]
                },
                {
                    name: '2015',
                    type: 'bar',
                    barWidth: 20,
                    barGap: "0%",
                    data: [120, 132, 101, 134, 90]
                },
                {
                    name: '2016',
                    type: 'bar',
                    barWidth: 20,
                    barGap: "0%",
                    data: [220, 182, 191, 234, 290]
                },
                {
                    name: '中位数',
                    type: 'line',
                    data: [150, 232, 201, 154, 190]
                }
            ]
        },
    }

    var EchartLoader = {
        echart_set: function (id, option, event) {
            var myChart = echarts.init(document.getElementById(id));
            myChart.setOption(option);
            if ("function" == typeof (event)) {
                event(myChart);
            }
        }
    }

    function get_new_value(data, config) {
        var result = [];
        for (var i = 0; i < data.length; i++) {
            result.push(map_config(data[i], config));
        }
        return result;

        function map_config(value, config) {
            var symbol = value < 0 ? -1 : 1;
            value = Math.abs(value);
            if (value == 0) {
                return value;
            }
            var index = 0;
            while (value > config[index] && index < config.length) {
                index++;
            }
            if (index >= config.length) {
                value = index;
            } else {
                value = (value - config[index - 1] || 0) / (config[index] - (config[index - 1] || 0)) + index - 1;
            }
            return symbol * value;
        }
    }

    function get_data_extremum(data, max, min) {
        var result = {
            max: max,
            min: min
        }
        for (var i = 0; i < data.length; i++) {
            result.max = result.max < data[i] ? data[i] : result.max;
            result.min = result.min > data[i] ? data[i] : result.min;
        }
        return result;
    }

    function bind_finance_status_data(option, data) {
        var years = [];
        option.series = [];
        var max = 0;
        var min = 0;
        for (var year in data) {
            var model = {
                name: year,
                data: get_new_value(data[year].data, Config.FinanceStatusConfig)
            }

            if (!parseInt(year)) {
                continue;
            } else {
                var extremum = get_data_extremum(model.data, max, min);
                max = extremum.max;
                min = extremum.min;
                model.type = "bar";
                model.barWidth = 20;
                model.barGap = "0%";
                years.push({ name: year, icon: "rect" });
            }
            option.series.push(model);
        }
        option.yAxis[0].splitNumber = Math.ceil(max) - Math.ceil(min);
        option.legend.data = years;
        option.tooltip.formatter = function (params) {
            var html = "{0}".format(params[0].name);
            for (var i = 0; i < params.length; i++) {
                var value = "--"
                switch (params[i].name) {
                    case "资产总额":
                        value = get_series_value(data[params[i].seriesName].data[0]);
                        break;
                    case "负债总额":
                        value = get_series_value(data[params[i].seriesName].data[1]);
                        break;
                    case "营业总收入":
                        value = get_series_value(data[params[i].seriesName].data[2]);
                        break;
                    case "净利润":
                        value = get_series_value(data[params[i].seriesName].data[3]);
                        break;
                    case "纳税总额":
                        value = get_series_value(data[params[i].seriesName].data[4]);
                        break;
                }
                html += "</br><span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:{0}'></span>{1} : {2}".format(params[i].color, params[i].seriesName, value)
            }
            return html;

            function get_series_value(value) {
                if (value) {
                    var index = 0;
                    var config = ["万", "亿"];
                    value = "{0}".format(value.toFixed(2)) + "万";
                } else if (value == 0) {
                    value = parseInt(value) + "万";
                } else {
                    value = "--";
                }
                return get_number(value);
            }
        }
        return option;
    }

    function bind_ability_evaluation_data(option, data) {
        var years = [];
        option.series = [];
        var max = 0;
        var min = 0;
        for (var year in data) {
            if (!parseInt(year)) {
                continue;
            }
            var extremum = get_data_extremum(data[year].data, max, min);
            max = extremum.max;
            min = extremum.min;
        }
        var result = Math.abs(max) > Math.abs(min) ? Math.abs(max) : Math.abs(min);
        if (result > Config.AbilityEvaluationConfig[Config.AbilityEvaluationConfig.length - 1]) {
            Config.AbilityEvaluationConfig[Config.AbilityEvaluationConfig.length] = Math.pow(10, ("{0}".format(parseInt(result)).length));
        }
        max = 0;
        min = 0;

        for (var year in data) {
            var model = {
                name: year,
                data: get_new_value(data[year].data, Config.AbilityEvaluationConfig)
            }

            if (!parseInt(year)) {
                continue;
            } else {
                var extremum = get_data_extremum(model.data, max, min);
                max = extremum.max;
                min = extremum.min;
                model.type = "bar";
                model.barWidth = 20;
                model.barGap = "0%";
                years.push({ name: year, icon: "rect" });
            }
            option.series.push(model);
        }
        option.yAxis[0].splitNumber = Math.ceil(max) - Math.ceil(min);
        option.legend.data = years;
        option.tooltip.formatter = function (params) {
            var html = "{0}".format(params[0].name);
            for (var i = 0; i < params.length; i++) {
                var value = "--"
                switch (params[i].name) {
                    case "资产负债率":
                        value = get_series_value(data[params[i].seriesName].data[0]);
                        break;
                    case "净利润率":
                        value = get_series_value(data[params[i].seriesName].data[1]);
                        break;
                    case "资产净利润率":
                        value = get_series_value(data[params[i].seriesName].data[2]);
                        break;
                    case "总资产周转率":
                        value = get_series_value(data[params[i].seriesName].data[3]);
                        break;
                }
                html += "</br><span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:{0}'></span>{1} : {2}".format(params[i].color, params[i].seriesName, value)
            }
            return html;

            function get_series_value(value) {
                if (value && value != 0) {
                    value = value.toFixed(2) + "%";
                } else if (value == 0) {
                    value = parseInt(value);
                } else if (!value && value != 0) {
                    value = "--";
                }
                return get_number(value);
            }
        }
        return option;
    }
    var FinanceEchart = function () {
    }
    FinanceEchart.prototype = {
        load_finance_status: function (id, data) {
            var option = clone(Options.FinanceStatus);
            option = bind_finance_status_data(option, data);
            EchartLoader.echart_set(id, option);
        },
        load_ability_evaluation: function (id, data) {
            var option = clone(Options.AbilityEvaluation);

            option = bind_ability_evaluation_data(option, data);

            EchartLoader.echart_set(id, option);
        }
    }
    FinanceEchart.prototype.constructor = FinanceEchart;
    return FinanceEchart;
}();

var FinanceController = function () {
    var repository = new FinanceRepository();
    var echart = new FinanceEchart();

    var loader = new LoadWaiting();

    var Modules = {
        FinanceModule: function () {

            function table_load_finance_status(data, echartData) {
                var table = $(tostring(function () {/*
                    <table class="all_col_table table_first_category">
                        <thead>
                            <tr>
                                <td class="" style="width:181px;">
                                    <div class="inline_div">年份</div>
                                    <div class="inline_div"></div>
                                    <div class="inline_div">类目</div>
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>资产总额</td>
                            </tr>
                            <tr>
                                <td>负债总额</td>
                            </tr>
                            <tr>
                                <td>营业总收入</td>
                            </tr>
                            <tr>
                                <td>净利润</td>
                            </tr>
                            <tr>
                                <td>纳税总额</td>
                            </tr>
                        </tbody>
                    </table>
                */}))

                var finance_table = $(table).clone();
                var cnt = 0;
                var style = "";
                for (var year in echartData) {
                    if (parseInt(year)) {
                        $(finance_table).find("thead tr").append("<td class='year'>{0}年</td>".format(year));
                        $(finance_table).find("tbody tr:eq(0)").append("<td>{0}</td>".format(get_format_data(echartData[year].data[0])));
                        $(finance_table).find("tbody tr:eq(1)").append("<td>{0}</td>".format(get_format_data(echartData[year].data[1])));
                        $(finance_table).find("tbody tr:eq(2)").append("<td>{0}</td>".format(get_format_data(echartData[year].data[2])));
                        $(finance_table).find("tbody tr:eq(3)").append("<td>{0}</td>".format(get_format_data(echartData[year].data[3])));
                        $(finance_table).find("tbody tr:eq(4)").append("<td>{0}</td>".format(get_format_data(echartData[year].data[4])));
                    } else {
                        if (cnt <= 1) {
                            $("#FinanceStatusMessage").removeClass("hidden");
                            style = "text-align:center;";
                        }
                    }
                    cnt++;
                }

                if (cnt <= 1) {
                    $("#FinanceStatusMessage").removeClass("hidden");
                    style = "text-align:center;";
                }
                $(finance_table).find("thead tr").append("<td class='evaluation'>评价</td>");
                $(finance_table).find("tbody tr:eq(0)").append(get_evaluation_text(data.TotalAssets.evaluation, style));
                $(finance_table).find("tbody tr:eq(1)").append(get_evaluation_text(data.TotalLiabilities.evaluation, style));
                $(finance_table).find("tbody tr:eq(2)").append(get_evaluation_text(data.TotalIncom.evaluation, style));
                $(finance_table).find("tbody tr:eq(3)").append(get_evaluation_text(data.NetProfit.evaluation, style));
                $(finance_table).find("tbody tr:eq(4)").append(get_evaluation_text(data.TotalTax.evaluation, style));
                $("#FinanceStatusTable").empty().append(finance_table);

                function get_format_data(value) {
                    if (value) {
                        var index = 0;
                        var config = ["万", "亿"];
                        value = "{0}".format(value.toFixed(2));
                    } else if (value == 0) {
                        value = parseInt(value);
                    } else {
                        value = "--";
                    }
                    return get_number(value);
                }

                function get_evaluation_text(value, style) {
                    if (value && value != "--") {
                        value = value;
                    } else {
                        style = "text-align:center;";
                        value = "--";
                    }
                    return "<td style='{1}'>{0}</td>".format(value, style);
                }
            }

            function table_load_analyze_conclusion(data, echartData) {
                var table = $(tostring(function () {/*
                    <table class="all_col_table table_first_category">
                        <thead>
                            <tr>
                                <td class="" style="width:208px;" colspan=2>
                                    <div class="inline_div">年份</div>
                                    <div class="inline_div"></div>
                                    <div class="inline_div">类目</div>
                                </td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td style="width:65px;">偿债能力</td>
                                <td style="width:103px;">资产负债率</td>
                            </tr>
                            <tr>
                                <td rowspan=2>盈利能力</td>
                                <td>净利润率</td>
                            </tr>
                            <tr>
                                <td style="background:#ffffff;font-weight:500;font-size:14px">资产净利润率</td>
                            </tr>
                            <tr>
                                <td>运营能力</td>
                                <td>总资产周转率</td>
                            </tr>
                        </tbody>
                    </table>
                */}))

                var finance_table = $(table).clone();
                var cnt = 0;
                var style = "";
                for (var year in echartData) {

                    if (parseInt(year)) {
                        $(finance_table).find("thead tr").append("<td class='year'>{0}年</td>".format(year));
                        $(finance_table).find("tbody tr:eq(0)").append(get_format_data(echartData[year].data[0]));
                        $(finance_table).find("tbody tr:eq(1)").append(get_format_data(echartData[year].data[1]));
                        $(finance_table).find("tbody tr:eq(2)").append(get_format_data(echartData[year].data[2]));
                        $(finance_table).find("tbody tr:eq(3)").append(get_format_data(echartData[year].data[3]));
                    } else {
                        if (cnt <= 1) {
                            $("#AbilityEvaluationMessage").removeClass("hidden");
                            style = "text-align:center;";
                        }
                    }
                    cnt++;
                }
                if (cnt <= 1) {
                    $("#AbilityEvaluationMessage").removeClass("hidden");
                    style = "text-align:center;";
                }
                $(finance_table).find("thead tr").append("<td class='evaluation'>评价</td>");
                $(finance_table).find("tbody tr:eq(0)").append(get_evaluation_text(data.AssetLiabilityRate.evaluation, style));
                $(finance_table).find("tbody tr:eq(1)").append(get_evaluation_text(data.NetProfitRate.evaluation, style));
                $(finance_table).find("tbody tr:eq(2)").append(get_evaluation_text(data.TotalAssetsProfitRate.evaluation, style));
                $(finance_table).find("tbody tr:eq(3)").append(get_evaluation_text(data.TotalAssetsTurnoverRate.evaluation, style));
                $("#AbilityEvaluationTable").empty().append(finance_table);

                function get_format_data(value, style) {
                    if (value && value != 0) {
                        value = value.toFixed(2) + "%";
                    } else if (value == 0) {
                        value = parseInt(value);
                    } else if (!value && value != 0) {
                        style = "text-align:center;";
                        value = "--";
                    }
                    return "<td style='{1}'>{0}</td>".format(get_number(value), style);
                }

                function get_evaluation_text(value, style) {
                    if (value && value != "--") {
                        value = value;
                    } else {
                        style = "text-align:center;";
                        value = "--";
                    }
                    return "<td style='{1}'>{0}</td>".format(value, style);
                }
            }

            function load_finance_status(data, echartData) {
                echart.load_finance_status("FinanceStatus", echartData);
                table_load_finance_status(data, echartData);
            }

            function load_ability_evaluation(data, echartData, show) {
                if (!show) {
                    $("#block_finance_module_3").addClass("hidden");
                    return;
                }
                echart.load_ability_evaluation("AbilityEvaluation", echartData);
                table_load_analyze_conclusion(data, echartData);
            }

            function load_analyze_conclusion(data) {
                $("#FinanceSummary .block_content_conclusion_element").empty()
                var empty = true;
                if (data.value && data.value.section1 && data.value.section1 != "") {
                    empty = false;
                    $("#FinanceSummary .block_content_conclusion_element").append("<p>{0}</p>".format(data.value.section1));
                }
                if (data.value && data.value.section2 && data.value.section1 != "") {
                    empty = false;
                    $("#FinanceSummary .block_content_conclusion_element").append("<p>{0}</p>".format(data.value.section2));
                }
                if (empty) {
                    $("#FinanceSummary").parents(".block_finance_module").addClass("hidden");
                }
            }

            return {
                repository_callback_get_finance_result: function (result) {
                    load_finance_status(result.FinanceStatus, result.FinanceStatusEchart);
                    load_ability_evaluation(result.AbilityEvaluation, result.AbilityEvaluationEchart, result.ShowAbilityEvaluation);
                    load_analyze_conclusion(result.AnalyzeConclusion);
                    loader.close();
                }
            }
        }()
    }

    var Loader = {
        load_finance: function () {
            loader.show(".block_module_list");
            var module = Modules.FinanceModule;
            var name = GetQueryString("cn");
            $(".company_name").text(decodeURI(name));
            if (name) {
                var model = {
                    companyName: name
                }
                repository.get_finance_result(model, module.repository_callback_get_finance_result)
            } else {
                window.location.href = "{0}/index".format(ctx);
            }
            return this;
        }
    }

    var Binder = {
        bind_finance_disclaimer_click: function () {
            var spread = false;
            $("#finance_disclaimer").click(click_finance_disclaimer)
            return this;
            function click_finance_disclaimer() {
                var height = 0;
                if (!spread) {
                    height = 175;
                    $("#finance_disclaimer").addClass("finance_disclaimer_spread");
                    spread = true;
                } else {
                    $("#finance_disclaimer").removeClass("finance_disclaimer_spread");
                    spread = false;
                }
                $(".finance_disclaimer_content").stop().animate({ "height": height }, 500);
            }
        }
    }

    var FinanceController = function () {
        Loader.load_finance();
        Binder.bind_finance_disclaimer_click();
    }
    FinanceController.prototype = {

    }
    FinanceController.prototype.constructor = FinanceController;
    return FinanceController;
}();

function get_number(value) {
    var value = value + "";
    var list = value.split(".");
    value = list[0].replace(/,/g, '').replace(/\d+?(?=(?:\d{3})+$)/g, function (s) {
        return s + ',';
    })
    return value + (list[1] ? "." + list[1] : "");
}