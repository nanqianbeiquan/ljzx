var echartController = function () {
    var clone = function (obj) {
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
    var set_height = function (id, persent) {
        var width = $("#" + id).width();
        $("#" + id).css("height", width * persent);
    }
    var line_option = {
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble',
            effectOption: {
                effect: {
                    n: 0
                }
            }
        },
        legend: {
            textStyle: { fontSize: 14 },
            itemHeight: 18,
            itemWidth: 20,
            itemGap: 30,
            padding: [20, 0, 0, 0]
        },
        grid: {
            x: "8%",
            y: "25%",
            width: "83%",
            height: "60%"
        },
        tooltip: {
            trigger: 'axis',
            formatter: function (data) {
                var result = "<span>" + data[0].name + "</span>";
                for (var i = 0; i < data.length; i++) {
                    result += "</br><span>" + data[i].seriesName + " : " + (data[i].value == "" ? 0 : data[i].value) + "</span>";
                }
                return result;
            }
        },
        toolbox: {
            show: false
        },
        color: ['#e95353', '#fec414', '#1bcc84', '#1aa6fa'],
        calculable: false,
        xAxis: [{
            axisLine: {
                lineStyle: {
                    color: '#999999'
                }
            },
            axisLabel: {
                interval: 1,
                rotate: 0,
                textStyle: {
                    align: "center",
                    fontSize: 8
                }
            },
            splitLine: {
                show: true
            },
            type: 'category',
            boundaryGap: false,

        }],
        yAxis: [{
            axisLine: {
                lineStyle: {
                    color: '#999999'
                }
            },
            splitLine: {
                show: true
            },
            name: '客户数量',
            nameTextStyle: { color: '#333333' },
            type: 'value'
        }]
    };
    var line_option_2 = {
        tooltip: {
            trigger: 'axis'
        },
        grid: {
            x: "4",
            y: "-4",
            width: "512px",
            height: "100%",
            borderWidth: 0
        },
        legend: {
            show: false,
            data: ['风险变化']
        },
        toolbox: {
            show: false
        },
        calculable: false,
        xAxis: [
            {
                show: false,
                type: 'category',
                axisLabel: {
                    interval: 0,
                    rotate: 0,
                    textStyle: {
                        align: "center",
                        fontSize: 8
                    }
                },
                boundaryGap: false,
                data: []
            }
        ],
        yAxis: [
            {
                show: false,
                type: 'value'
            }
        ],
        series: [
            {
                symbol: 'emptyCircle',
                symbolSize: 3,
                name: '风险变化',
                type: 'line',
                stack: '总量',
                itemStyle: {
                    normal:
                    {
                        areaStyle: { type: 'default', color: '#edf5fb' },
                        lineStyle: { color: '#edf5fb' },
                        color: '#2ea7e0'
                    }
                },
                data: []
            }
        ]
    };
    var level_option = {
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble',
            effectOption: {
                effect: {
                    n: 0
                }
            }
        },
        title: {

        },

        tooltip: {
            trigger: 'axis',
            formatter: function (data) {
                switch (data[0].value) {
                    case "0":
                        data[0].value = "正常";
                        break;
                    case "1":
                        data[0].value = "关注";
                        break;
                    case "2":
                        data[0].value = "一般预警";
                        break;
                    case "3":
                        data[0].value = "特别预警";
                        break;
                    default:
                        data[0].value = data[0].value
                        break;
                }
                return "<span>" + data[0].name + "</span></br><span>风险状况 : " + (data[0].value == "" ? "-" : data[0].value) + "</span>";
            }
        },
        grid: {
            x: "8%",
            y: "25%",
            width: "83%",
            height: "60%"
        },
        legend: {
            data: ['预警状况'],
            textStyle: { fontSize: 14 },
            itemHeight: 18,
            itemWidth: 20,
            itemGap: 30,
            padding: [20, 0, 0, 0]
        },
        toolbox: {
            show: true,
            feature: {
                mark: { show: false },
                dataView: { show: false, readOnly: false }
            }
        },
        calculable: false,
        xAxis: [
            {
                splitLine: {
                    show: false
                },
                splitArea: {
                    show: false
                },
                type: 'category',
                boundaryGap: false,

            }
        ],
        yAxis: [
            {
                splitLine: {
                    show: false
                },
                splitArea: {
                    show: false
                },
                type: 'category',
                boundaryGap: false,
                data: ['正常', '关注', '一般', '特别']
            },
            {
                type: 'value',
                splitNumber: 3,
                axisLine: {
                    lineStyle: 0
                },
                axisLabel: {
                    show: false
                }
            }
        ],
        series: [
            {
                name: '预警状况',
                type: 'line',


                yAxisIndex: 1
            },

        ]//,
        //color: [
        //'#32CD32', '#FFD700', '#FFA500', '#FF0000'],
    };

    var bar_option = {
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble',
            effectOption: {
                effect: {
                    n: 0
                }
            }
        },
        title: {

        },
        tooltip: {
            trigger: 'item'
        },
        toolbox: {
            show: false
        },
        calculable: false,
        grid: {
            borderWidth: 0
        },
        xAxis: [
            {
                name: '风险状况',
                axisLine: {
                    lineStyle: {
                        color: '#999999'
                    }
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                type: 'category',
                show: true,
                data: ['特别预警', '一般预警', '关注', '正常']
            }
        ],
        yAxis: [
            {
                name: "企业数量",
                axisLine: {
                    lineStyle: {
                        color: '#999999'
                    }
                },
                splitLine: {
                    show: false
                },
                axisTick: {
                    show: false
                },
                axisLabel: {
                    show: false
                },
                type: 'value',
                show: true
            }
        ],
        series: [
            {
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: function (params) {
                            // build a color map as your need.
                            var colorList = [
                                '#e95353', '#fec414', '#1aa6fa', '#1bcc84'
                            ];
                            return colorList[params.dataIndex]
                        },
                        label: {
                            show: true,
                            position: 'top',
                            formatter: '{c}家'//{b}(20%)\n{c}家
                        }
                    }
                },
                barMaxWidth: 25
            }
        ]
    };
    var pie_option = {
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble',
            effectOption: {
                effect: {
                    n: 0
                }
            }
        },
        title: {
            show: false,
            x: 'center',
            y: 'center',
            itemGap: 20,
            textStyle: {
                color: '#666666',
                fontFamily: '微软雅黑',
                fontSize: 24,
                fontWeight: 'bolder'
            }
        },
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            show: false,
            orient: 'vertical',
            x: 'right',
            y: 'bottom'
        },
        toolbox: {
            show: false
        },
        calculable: false,
        series: [
            {
                type: 'pie',
                radius: ['35%', '50%'],
                x: '60%',
                width: '35%',
                funnelAlign: 'left',
                startAngle: 270
            }
        ]
    };
    var pie_option_2 = {
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble',
            effectOption: {
                effect: {
                    n: 0
                }
            }
        },
        title: {
            show: false,
            x: 'center',
            y: 'center',
            itemGap: 20,
            textStyle: {
                color: '#333333',
                fontFamily: '微软雅黑',
                fontSize: 14,
                fontWeight: 600
            }
        },
        tooltip: {
            show: false
        },
        legend: {
            show: false
        },
        toolbox: {
            show: false
        },
        calculable: false,
        series: [
            {
                type: 'pie',
                radius: ['80%', '90%'],
                x: '0%',
                itemStyle: {
                    normal: {
                        label: {
                            formatter: function (params) {
                                var value = params.value
                                // if(params.value>=100){
                                //     value=99+"+";
                                // }
                                return value + '家'
                            },
                            textStyle: {
                                baseline: 'center',
                                color: '#333333',
                                fontSize: 16,
                                fontWeight: 500
                            }
                        }
                    }
                }
            }
        ]
    };
    var map_option = {
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble',
            effectOption: {
                effect: {
                    n: 0
                }
            }
        },
        title: {
        },
        tooltip: {
            trigger: 'item',
            formatter: function (params) {
                console.log(params);
                return params.seriesName + "<br/>" + params.name + "&nbsp;" + params.value + "%";
            }
        },

        dataRange: {
            /*min: 0,
            max: 2500,
            x: '20',
            y: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: false,
            //color: ['#e95353', '#ff7200', '#fec414', '#1bcc84', '#1aa6fa'],
            color: ['#006edd', '#e0ffff'],
            hoverLink: false,
            selectedMode: false*/
            min: 0,
            max: 100,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: false,
            splitNumber:0,
            color: ['#006edd', '#e0ffff'],
            hoverLink: false
        },
        toolbox: {
            show: false
        },
        roamController: {
            show: false,
            x: 'right',
            mapTypeControl: {
                'china': true
            }
        },
        series: [
            {
                name: '特别预警',
                type: 'map',
                mapType: 'china',
                roam: false,
                dataRangeHoverLink: false,
                itemStyle: {
                    normal: { label: { show: true } },
                    emphasis: { label: { show: true } }
                }
            }
        ]
    };
    var radar_option = {
        noDataLoadingOption: {
            text: '暂无数据',
            effect: 'bubble',
            effectOption: {
                effect: {
                    n: 0
                }
            }
        },
        title: {

        },
        tooltip: {
            show: false,
            trigger: 'axis'
        },
        legend: {
            show: false,
            x: 'center',
            data: ['企业名称']
        },
        toolbox: {
            show: false
        },
        calculable: false,
        polar: [
            {
                indicator: [
                    { text: '企业背景', min: 0, max: 6 },
                    { text: '发展潜力', min: 0, max: 6 },
                    { text: '重点关注舆情', min: 0, max: 6 },
                    { text: '经营异常', min: 0, max: 6 },
                    { text: '司法诉讼', min: 0, max: 6 }
                ],
                splitNumber: 6,//分割段数，默认为5
                radius: '75%'
            }
        ],
        series: [
            {
                name: '企业信息',
                type: 'radar',
                itemStyle: {
                    normal: {
                        color: 'rgba(0,199,194,0.25)',
                        areaStyle: {
                            type: 'default'
                        }
                    }
                },
                data: [
                    {
                        value: [97, 42, 88, 94, 90],
                        name: '企业名字'
                    }
                ]
            }
        ]
    };

    var echart_load = function () {
        require.config({
            paths: {
                echarts: ctx + '/js/echarts'
            }
        });
    };
    var echart_map = function (id, option, event) {
        require(
            [
                'echarts',
                'echarts/chart/map'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById(id));
                myChart.setOption(option);
                if ("function" == typeof (event)) {
                    event(myChart);
                }
            }
        );
    };
    var echart_pie = function (id, option, event) {
        require(
            [
                'echarts',
                'echarts/chart/pie'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById(id));
                myChart.setOption(option);
                if ("function" == typeof (event)) {
                    event(myChart);
                }
            }
        );
    };
    var echart_line = function (id, option, event) {
        require(
            [
                'echarts',
                'echarts/chart/line'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById(id));
                myChart.setOption(option);
                if ("function" == typeof (event)) {
                    event(myChart);
                }
            }
        );
    };
    var echart_bar = function (id, option, event) {
        require(
            [
                'echarts',
                'echarts/chart/bar'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById(id));
                myChart.setOption(option);
                if ("function" == typeof (event)) {
                    event(myChart);
                }
            }
        );
    };
    var echart_radar = function (id, option, event) {
        require(
            [
                'echarts',
                'echarts/chart/radar'
            ],
            function (ec) {
                var myChart = ec.init(document.getElementById(id));
                myChart.setOption(option);
                if ("function" == typeof (event)) {
                    event(myChart);
                }
            }
        );
    }
    var echartController = function () {
        echart_load();
    }
    echartController.prototype = {
        home_round: function (id, data) {
            var option = clone(pie_option_2);
            option.title.text = (data.value) + '家';
            option.legend.data = [data.name, '其他'];
            option.series[0].data = [
                {
                    value: data.value,
                    name: data.name,
                    selected: false,
                    itemStyle: {
                        normal: {
                            color: data.color,
                            label: {
                                show: false,
                                position: 'center'
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    }
                },
                {
                    value: data.total - data.value,
                    name: '其他',
                    selected: false,
                    itemStyle: {
                        normal: {
                            color: '#f1f1f1',
                            label: {
                                show: false,
                                position: 'center'
                            },
                            labelLine: {
                                show: false
                            }
                        }
                    }
                }
            ];
            echart_pie(id, option)
        },
        monitor_events: function (id, data) {
            set_height(id, 0.543);
            var option = clone(pie_option);
            option.title.text = (data.normalNum + data.attentionNum + data.warnNum) + '家';
            option.legend.data = ['一般', '异常', '严重'];
            option.series[0].name = '事件情况';

            /* option.series[0].data=[
                     {
                         value:data.normalNum, 
                         name:'一般',
                         selected:false,
                         itemStyle:{
                             normal: {
                                 color:'#1bcc84',
                                 label: {
                                     show: true,
                                     position:'right',
                                     textStyle:{
                                         align:'center',
                                         fontSize:12,
                                         fontWeight:700,
                                         color:'#12a86b'
                                     },
                                     formatter: '{d}%\n{b}{c}家\n\n\n'//{b}(20%)\n{c}家
                                 },
                                 labelLine:{
                                     length:30
                                 }
                             },
                             emphasis: {
                                 color:'#1bcc84',
                             }
                         }
                     },
                     {
                         value:data.attentionNum, 
                         name:'异常',
                         selected:false,
                         itemStyle:{
                             normal: {
                                 color:'#fec414',
                                 label: {
                                     show: true,
                                     position:'right',
                                     textStyle:{
                                         align:'center',
                                         fontSize:12,
                                         fontWeight:700,
                                         color:'#d1a215'
                                     },
                                     formatter:'{d}%\n{b}{c}家\n\n\n'//{b}(20%)\n{c}家
                                 },
                                 labelLine:{
                                     length:30
                                 }
                             },
                             emphasis: {
                                 color:'#fec414',
                             }
                         }
                     },
                     {
                         value:data.warnNum, 
                         name:'严重',
                         selected:false,
                         itemStyle:{
                             normal: {
                                 color:'#e95353',
                                 label: {
                                     show: true,
                                     position:'right',
                                     textStyle:{
                                         align:'center',
                                         fontSize:12,
                                         fontWeight:700,
                                         color:'#d44242'
                                     },
                                     formatter: '{d}%\n{b}{c}家\n\n\n'//{b}(20%)\n{c}家
                                 },
                                 labelLine:{
                                     length:30
                                 }
                             },
                             emphasis: {
                                 color:'#e95353',
                             }
                         }
                     }
                 ];*/
            if ((data.normalNum + data.attentionNum + data.warnNum) == 0) {
                data.normalNum = 1;
                option.series[0].data = [
                    {
                        value: data.normalNum,
                        name: '全部',
                        selected: false,
                        itemStyle: {
                            normal: {
                                //color:'#5bb8f3',
                                color: '#ebebeb',
                                label: {
                                    show: false,
                                    position: 'right',
                                    textStyle: {
                                        align: 'center',
                                        fontSize: 12,
                                        fontWeight: 700,
                                        //color:'#3181b3'
                                        color: '#d1a215'
                                    },
                                    formatter: '{d}%\n{b}{c}家\n\n\n'//{b}(20%)\n{c}家
                                },
                                labelLine: {
                                    length: 0
                                }
                            },
                            emphasis: {
                                color: '#ebebeb'
                            }
                        }
                    }
                ];
                option.tooltip = {
                    show: false
                }
            } else {

                function NumData(name, num, color, fontColor, total) {
                    this.name = name;
                    this.num = parseInt(num);
                    this.color = color;
                    this.fontColor = fontColor;
                    this.length = 20;
                    this.persent = num / parseInt(total) * 100;
                    if (this.persent < 7) {
                        this.length = 60;
                    } else if (this.persent >= 7 && this.persent < 15) {
                        this.length = 40;
                    } else if (this.persent >= 15 && this.persent < 25) {
                        this.length = 30;
                    }
                }
                function NumCompare(propertyName) {
                    return function (object1, object2) {
                        var value1 = object1[propertyName];
                        var value2 = object2[propertyName];
                        if (value2 < value1) {
                            return -1;
                        }
                        else if (value2 > value1) {
                            return 1;
                        }
                        else {
                            return 0;
                        }
                    }
                }
                var dataArr = [];
                dataArr.push(new NumData("一般", data.normalNum, "#1bcc84", "#12aB6b", data.totalNum));
                dataArr.push(new NumData("异常", data.attentionNum, "#fec414", "#d1a215", data.totalNum));
                dataArr.push(new NumData("严重", data.warnNum, "#e95353", "#d44242", data.totalNum));
                dataArr.sort(NumCompare("num"));

                if (dataArr[2].persent < 7) {
                    dataArr[2].length = 30;
                    if (dataArr[1].persent < 15) {
                        dataArr[1].length = 60;
                    }
                }
                if (dataArr[0].persent == 100) {
                    dataArr.length = 1;
                }
                option.series[0].data = [];
                for (var i = 0; i < dataArr.length; i++) {
                    var value = dataArr[i];
                    option.series[0].data.push({
                        value: value.num,
                        name: value.name,
                        selected: false,
                        itemStyle: {
                            normal: {
                                color: value.color,
                                label: {
                                    show: true,
                                    position: 'right',
                                    textStyle: {
                                        align: 'center',
                                        baseline: "middle",
                                        fontSize: 12,
                                        fontWeight: 700,
                                        color: value.fontColor
                                    },
                                    formatter: '{d}%\n{b}{c}家\n\n\n'//{b}(20%)\n{c}家
                                },
                                labelLine: {
                                    length: value.length
                                }
                            },

                            emphasis: {
                                color: value.color
                            }
                        }
                    })
                }
                if (dataArr.length == 1) {
                    console.log(option.series[0].data[0]);
                    option.series[0].data[0].itemStyle.normal.label.show = false;
                    option.series[0].data[0].itemStyle.normal.labelLine.show = false;
                }
            }
            echart_pie(id, option, function (myChart) {
                myChart.on('click', function (param) {
                    if ("一般" == param.name) {
                        toMonitorCompanyList('', '', '', '', '1', '', '');
                    } else if ("异常" == param.name) {
                        toMonitorCompanyList('', '', '', '', '2', '', '');
                    } else if ("严重" == param.name) {
                        toMonitorCompanyList('', '', '', '', '3', '', '');
                    }
                });
            })
        },
        monitor_warning: function (id, data) {
            set_height(id, 0.5);
            var option = clone(bar_option);

            option.series[0].data = [data.highRiskNum, data.middleRiskNum, data.lowRiskNum, data.noRiskNum];

            echart_bar(id, option);
        },
        monitor_area: function (id, data) {
            set_height(id, 0.5);
            var option = clone(map_option);

            option.dataRange.max = 100;
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
            echart_map(id, option, function (myChart) {
                myChart.on('click', function (param) {
                    toMonitorCompanyList('', '', '', param.name, '', '', '');
                });
            });
        },
        monitor_events_change4Company: function (id, data) {
            set_height(id, 0.45);
            var option = clone(line_option);
            option.legend.data = ['严重', '异常', '一般'];
            option.yAxis[0].name = data.yName;
            option.series = [
                {
                    name: '严重',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: data.warnNumList
                },
                {
                    name: '异常',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: data.attentionNumList
                },
                {
                    name: '一般',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: data.normalNumList
                }
            ];
            option.xAxis[0].data = data.spotList;

            echart_line(id, option);

        },
        monitor_events_change: function (id, data) {
            set_height(id, 0.45);
            var option = clone(line_option);
            option.legend.data = ['严重', '异常', '一般'];
            option.yAxis[0].name = '客户数量';

            option.series = [
                {
                    name: '严重',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: data.warnNumArray.split(",")
                },
                {
                    name: '异常',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: data.attentionNumArray.split(",")
                },
                {
                    name: '一般',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: data.normalNumArray.split(",")
                }
            ];
            option.xAxis[0].data = data.dateArray.split(",");

            echart_line(id, option);
        },
        monitor_level_change2: function (id, data) {
            set_height(id, 0.45);
            var option = clone(level_option);
            yAxis: [
                {
                    type: 'value',
                    splitArea: { show: true },
                    name: '预警状况',
                    data: ['正常', '关注', '一般', '特别']
                }
            ];

            option.series = [
                {
                    name: '预警状况',
                    type: 'line',
                    data: data.gradeList,
                    symbol: 'emptyCircle',
                    yAxisIndex: 1
                }
            ];
            option.xAxis[0].data = data.spotList;
            echart_line(id, option);
        },
        monitor_level_change: function (id, data) {
            set_height(id, 0.45);
            var option = clone(line_option);
            option.legend.data = ['特别预警', '一般预警', '关注', '正常'];
            option.yAxis.name = '客户数量';

            option.series = [
                {
                    name: '特别预警',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: [6, 6, 7, 7, 7, 7, 7]
                },
                {
                    name: '一般预警',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: [6, 6, 6, 6, 6, 6, 6]
                },
                {
                    name: '关注',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: [9, 9, 8, 8, 9, 9, 9]
                },
                {
                    name: '正常',
                    type: 'line',
                    symbol: 'emptyCircle',
                    data: [0, 0, 0, 0, 0, 0, 0]
                }
            ];
            option.xAxis[0].data = ['2016-09-05', '2016-09-05', '2016-09-05', '2016-09-05', '2016-09-05', '2016-09-05', '2016-09-05'];

            echart_line(id, option);
        },
        monitor_company_event: function (id, data, monitor_level) {
            set_height(id, 0.8);
            var option = clone(radar_option);

            option.series = [
                {
                    name: '企业信息',
                    type: 'radar',
                    itemStyle: {
                        normal: {

                            areaStyle: {
                                type: 'default'
                            }
                        }
                    },
                    data: [
                        {
                            value: [data.companyBackgroundIndex, data.developmentPotentialIndex, data.negativeNewsIndex, data.operateExceptionIndex, data.judicialLitigationIndex],
                            name: data.companyName
                        }
                    ]
                }
            ];
            switch (monitor_level) {
                case 3:
                    option.color = ["#e95353"];
                    break;
                case 2:
                    option.color = ["#ff7200"];
                    break;
                case 1:
                    option.color = ["#fec414"];
                    break;
                case 0:
                    option.color = ["#1aa6fa"];
                    break;
                default:
                    option.color = ["#1aa6fa"];
                    break;
            }

            echart_radar(id, option);
        },
        history_risk_change: function (id, data) {
            var option = clone(line_option_2);
            option.xAxis[0].data = new Array();
            option.series[0].data = new Array();
            for (var i = 0; i < data.data.length; i++) {
                var value = data.data[i];
                option.xAxis[0].data.push(value.time);
                option.series[0].data.push(value.value);
            }
            echart_line(id, option);
        }
    };
    echartController.prototype.constructor = echartController;
    return echartController;
}();