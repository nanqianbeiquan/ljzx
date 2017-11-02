var controller;
$(function () {

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

    controller = new monitorHistoryController();

    var deep = $("#deep").val();
    updateNavMenu(parseInt(deep), "工商信息", window.location.search);
})

var monitorHistoryController = function () {
    var blacklist_controller = new BlacklistController();

    var Modules = {
        BlacklistModule: function () {
            function show_blacklist_message() {
                blacklist_controller.monitor_company_init($("#MonitorCompanyName").text().trim());
            }
            return {
                show_blacklist_message: show_blacklist_message
            }
        }()
    }

    var event_module_menu = function () {
        //ToDo:顶部菜单栏浮动
        var position = $(".company_modules").offset().top;
        var position_left = $(".company_modules").offset().left;
        var height = $(".company_modules").height();
        var moved = false;
        $(document).scroll(function (value) {
            var top = $(document).scrollTop() + 82;
            if (top >= position) {
                if (moved) {
                    return;
                }
                moved = true;
                $(".company_modules_blodk").css({ "height": $(".company_modules").height() });
                $(".company_modules").stop().css({ "position": "fixed", "top": "80px", "background": "#ffffff", "left": position_left });
                $(".company_modules .modules_sub_list").addClass("head_shadow")//.find(".modules_sub_list").css({"border":"1px solid #ffffff"});

            } else {
                moved = false;
                $(".company_modules").removeClass("head_shadow").css({ "position": "static", "top": "auto", "background": "auto" });
                $(".company_modules .modules_sub_list").removeClass("head_shadow");
            }
        })
        //ToDo:菜单悬停效果
        var timeout;
        var showhover = false;
        var hover_show = function (call_back) {
            $(".modules_bottom_hover").animate({ "top": "-3px" }, 100, function () {
                if (typeof (call_back) == "function") {
                    call_back()
                }
            });
        }
        var hover_hide = function (call_back) {
            $(".modules_bottom_hover").animate({ "top": "0px" }, 100, function () {
                if (typeof (call_back) == "function") {
                    call_back()
                }
            });
        }
        var hover_move_animate = function (index) {
            $(".modules_bottom_hover").animate({ "left": index * 200 }, 200);
        }
        var hover_move = function (index) {
            $(".modules_bottom_hover").css({ "left": index * 200 });
        }
        var animate_stop = function () {
            $(".modules_bottom_hover").stop();
        }
        //ToDo:菜单点击效果
        $(".modules_list .modules").click(function () {
            var index = $(this).index();
            $(".modules_bottom_selector").animate({ "left": index * 200 }, 200);
            $(".modules_list .selected").removeClass("selected");
            $(this).addClass("selected");
            //ToDo:模块变化
            $(".modules_block_list .current_modules").removeClass("current_modules").addClass("hidden");
            $(".modules_block_list .modules_block:eq(" + index + ")").addClass("current_modules").removeClass("hidden");
            //ToDo:子菜单切换
            $(".modules_sub_list .sub_list").removeClass("current_sub_list").addClass("hidden");
            $(".modules_sub_list .sub_list:eq(" + index + ")").addClass("current_sub_list").removeClass("hidden");
            //ToDo:子菜单选择
            //$(".modules_sub_list .current_sub_list .selected").click();
            $(".company_modules_blodk").css({ "height": $(".company_modules").height() });
            //controller.loadModule(index);


        })
        //ToDo:子菜单点击效果
        $(".modules_sub_list .sub_modules").click(function () {
            var index = $(this).index();
            $(".current_sub_list .selected").removeClass("selected");
            $(this).addClass("selected");
            $("html,body").stop().animate({ scrollTop: $(".current_modules .info_block:eq(" + index + ")").offset().top - 105 - $(".company_modules").height() }, 1000);
        })
    }
    var event_switch_btn = function () {
        //ToDo:每日记录移动
        var current = 0;
        var index = $(".day_list .day").length;
        $(".filter_days .left_btn").click(function () {
            if (current > 0) {
                current--;
                $(".day_list .day").each(function () {
                    $(this).animate({ left: "+=" + 170 });
                })
            }

        })
        $(".filter_days .right_btn").click(function () {
            console.log("right btn clicked,current=" + current + ",index=" + index);
            if (current < index - 6) {
                current++;
                $(".day_list .day").each(function () {
                    $(this).animate({ left: "-=" + 170 });
                })
            }

        })
    }
    var event_days = function () {
        console.log("event days add events.");
        //时间轴点击事件
        $(".day_list .day").click(function () {
            console.log("days click..");
            if ($(this).hasClass("selected")) {
                applyTimeAxisPage(null);
            } else {
                $(".day_list .selected").removeClass("selected");
                $(this).addClass("selected");
                var dateKey = $(this).find(".title").text().trim();

                var cateArray = new Array();

                $(this).find(".value .line_txt .cate").each(function () {
                    cateArray.push($(this).text());
                });
                console.log("dateKey=" + dateKey + ",cates=" + cateArray);
                applyBianGengJiLuDateTable(dateKey, cateArray);
            }
        });

    }
    var event_category = function () {
        $(".info_filter .filter_list .value").click(function () {
            if ($(this).hasClass("selected")) {

                if ($(this).text().trim().indexOf("全部") >= 0) {
                    //return;
                    //$(".info_filter .filter_list .selected").removeClass("selected");
                } else {
                    if ($(".info_filter .filter_list .selected").length == 1) {
                        $(".info_filter .filter_list .value:eq(0)").addClass("selected");
                    }
                    $(this).removeClass("selected");
                }

            } else {//被点击之前的状态为 未选择
                console.log($(this).text().trim().indexOf("全部"));
                //点击了 全部
                if ($(this).text().trim().indexOf("全部") >= 0) {
                    $(".info_filter .filter_list .selected").removeClass("selected");
                    $(this).addClass("selected");
                } else {

                    if ($(".info_filter .filter_list .value:eq(0)").hasClass("selected")) {
                        $(".info_filter .filter_list .value:eq(0)").removeClass("selected");
                    }
                    if ($(".info_filter .filter_list .selected").length == 6) {//所有种类都选中则认为选中的全部
                        $(".info_filter .filter_list .selected").removeClass("selected");
                        $(".info_filter .filter_list .value:eq(0)").addClass("selected");
                    } else {
                        $(this).addClass("selected");
                    }
                }
            }
            //    		var selectedArray = new Array();
            //    		$(".info_filter .filter_list .selected").each(function(){
            //    			selectedArray.push($(this).attr("name"));
            //    		});
            //    		console.log(selectedArray);
            applyTimeAxisPage(null);
        });

    }

    var Loader = {
        load_blacklist_message: function () {
            Modules.BlacklistModule.show_blacklist_message();
            return this;
        }
    }

    function init() {
        Loader.load_blacklist_message();
    }

    var monitorHistoryController = function () {

        this.load_page();
        this.event_bind();
        this.loadModule(0);
        init();
    }
    monitorHistoryController.prototype = {
        load_page: function () {

        },
        event_bind: function () {
            //event_days();
            event_module_menu();
            event_switch_btn();
            event_days();
            event_category();
        },
        /* event_bind_biz:function(){
        	event_switch_btn();
        	event_days();
        	event_category();
        },*/
        event_bind_biz_days: function () {
            event_switch_btn();
            event_days();
        },
        loadModule: function (index) {
            console.log("load module,index=" + index);
            updateChannelType("QI_CHA_CHA");
            applyChgRecodCount(2);

            //index==0,加载工商数据及页面        	
        	/*if(index==0){//工商
        		loadBiz(index);
        	}else if(index==1){
        		loadLaw(index);
        	}else if(index==2){
        		loadNewsOpinion(index);
        	}else if(index==3){
        		
        	}else if(index==4){
        		loadRiskAnalysis(index);
        	}*/
        }
    };
    monitorHistoryController.prototype.constructor = monitorHistoryController;
    return monitorHistoryController;
}();

var page_resizeContent = function () {
    if ('object' == typeof controller) {
        controller.load_page();
    }
}

