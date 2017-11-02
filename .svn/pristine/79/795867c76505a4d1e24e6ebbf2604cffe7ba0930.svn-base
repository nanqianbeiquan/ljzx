var overlap = function (option) {
    var default_option = {
        position: {//弹出框位置
            type: 'fixed',//弹出框位置模式，参考样式position
            x_pos: 'middle',//弹出框left位置，middel为居中，也可以设置数值：10px
            y_pos: 'middle'//弹出框top位置，设置与left相同
        },
        mask: {//弹出框背景遮罩层
            show: true,//是否显示遮罩层
            close: true,//是否点击遮罩层后关闭
            style: {
                //遮罩层样式，通过jquery.css注入
            }
        },
        content: {//具体内容
            body: "",//弹出框内容
            style: {//弹出框样式，通过jquery.css注入
                width: "30%",
                height: "40%",
                border: "0px"
            }
        },
        close: {//关闭按钮相关
            show: true,//是否显示关闭按钮
            type: "close_1",//关闭样式,type为注入到标签内的class，具体样式可以参考overlap.css。可以自定义扩展
            style: {
                //其他样式，通过jquery.css注入
            },
            callBack: function () {
                //关闭回调事件
            },
            quick_mode: true
        },
        scroller: {//滚动条事件
            lock: false,//锁屏，如果开启锁屏，有可能导致弹出框内部滚动条事件的触发失效，需要对滚动条事件重新注册
            body_hidden: false,//隐藏窗体的滚动条
            content_lock: true,//内容锁屏
            relative: false,
            callBack_scroller: function () {

            }
        }
    }

    var tostring = function (fn) {
        return fn.toString().split('\n').slice(1, -1).join('\n') + '\n'
    }

    var load_lap = function (option) {
        var lap_block = $(tostring(function () {/*
            <div class="overlap hidden">
                <div class="lap_mask hidden"></div>
                <div class="lock_mask hidden">
                </div>
                <div class="load_mask hidden">
                    <div class="spinner">
                        <div class="spinner-container container1">
                            <div class="circle1"></div>
                            <div class="circle2"></div>
                            <div class="circle3"></div>
                            <div class="circle4"></div>
                        </div>
                        <div class="spinner-container container2">
                            <div class="circle1"></div>
                            <div class="circle2"></div>
                            <div class="circle3"></div>
                            <div class="circle4"></div>
                        </div>
                        <div class="spinner-container container3">
                            <div class="circle1"></div>
                            <div class="circle2"></div>
                            <div class="circle3"></div>
                            <div class="circle4"></div>
                        </div>
                    </div>
                </div>
                <div class="lap_block">
                    <div class="lap_content" style="display:none;">
                        <div class="lap_close_block">
                            <div class="lap_close inline_div">
                                <div class="close_left inline_div"></div>
                                <div class="close_right inline_div"></div>
                            </div>
                        </div>
                        <div class="lap_body">

                        </div>
                    </div>
                </div>
            </div>
        */}));
        set_content(lap_block, option.content);
        set_mask(lap_block, option);
        set_close(lap_block, option);
        set_position(lap_block, option);
        //set_scroller(lap_block,option.scroller);
        $("body").append(lap_block);
        $(lap_block).fadeOut();
        return lap_block;
    }
    var reload_lap = function (lap, option) {
        set_content(lap, option.content);
        set_position(lap, option);
    }
    var set_position = function (lap, option) {
        var position = option.position;
        $(lap).find(".lap_content").css("position", position.type);
        var x_num = position.x_pos.toString().match(/\d+(\.\d+)?/g);

        var window_width = $(window).width();
        var content_width = $(lap).find(".lap_content").width();
        var content_left_border = parseInt($(lap).find(".lap_content").css("border-left-width"));
        var content_right_border = parseInt($(lap).find(".lap_content").css("border-right-width"));
        if (x_num == null) {
            switch (position.x_pos) {
                case "middle":
                default:
                    x_num = (window_width - content_width - content_left_border - content_right_border) / 2;
                    break;
            }
        } else if (window_width < (content_width + content_left_border + content_right_border + parseInt(x_num))) {
            x_num = window_width - content_width - content_left_border - content_right_border;
        }

        $(lap).find(".lap_content").css("left", parseInt(x_num));

        var y_num = position.y_pos.toString().match(/\d+(\.\d+)?/g);
        if (y_num == null) {
            switch (position.y_pos) {
                case "middle":
                default:
                    y_num = ($(window).height() - $(lap).find(".lap_content").height()) / 2;
                    y_num = y_num < 0 ? 10 : y_num;
                    break;
            }
        }
        $(lap).find(".lap_content").css("top", parseInt(y_num));
    }
    var set_content = function (lap, content) {
        var height = $(window).height();
        var h_num = content.style.height.toString().match(/\d+(\.\d+)?/g);
        if (content.style.height.toString().indexOf('auto') > 0) {
            content.style.height = "auto";
        } else {
            if (content.style.height.toString().indexOf('%') > 0) {
                height = h_num * 0.01 * height;
            } else {
                height = h_num;
            }
            content.style.height = height;
        }
        var width = $(window).width();
        var w_num = content.style.width.toString().match(/\d+(\.\d+)?/g);
        if (content.style.width.toString().indexOf('auto') > 0) {
            content.style.width = "auto";
        } else {
            if (content.style.width.toString().indexOf('%') > 0) {
                width = w_num * 0.01 * width;
            } else {
                width = w_num;
            }
            content.style.width = width;
        }
        $(lap).find(".lap_content").css(content.style);
        if (content.style.width == null || content.style.width.toString().indexOf('auto') > 0) {
            content.style.width = "auto";
        }
        if (content.style.height == null || content.style.height.toString().indexOf('auto') > 0) {
            content.style.height = "auto";
        }
    }
    var set_content_body = function (lap, body) {
        switch (typeof (body)) {
            case "undefined":
                break;
            case "string":
                $("body").append($(lap).find(".lap_content").children(".children").addClass("hidden").removeClass("children"));
                $(body).addClass("children").removeClass("hidden");
                $(lap).find(".lap_content").append($(body));
                break;
            case "object":
                break;
            default:
                break;
        }
    }
    var set_mask = function (lap, options) {
        var mask = options.mask;
        if (!mask.show) {
            $(lap).find(".lap_mask").addClass("hidden");
        } else {
            $(lap).find(".lap_mask").css(mask.style).click(function () {
                if (mask.close) {
                    lap_close(lap, options);
                }
            });
        }
    }
    var set_close = function (lap, options) {
        var close = options.close;
        $(lap).find(".lap_close_block").addClass(close.type);
        if (!close.show) {
            $(lap).find(".lap_close_block").addClass("hidden");
        } else {
            $(lap).find(".lap_close").css(close.style).click(function () {
                lap_close(lap, options);
            });
        }
    }
    function scrollFunc(evt) {
        evt = evt || window.event;
        if ($(evt.target).parents(".lap_content").length > 0) {
            //window.event? window.event.cancelBubble = true : e.stopPropagation();
            return true;
        }
        if (evt.preventDefault) {
            // Firefox
            evt.preventDefault();
            evt.stopPropagation();

        } else {
            // IE
            evt.cancelBubble = true;
            evt.returnValue = false;
        }
        event.stopPropagation();
        return false;
    }
    function stop_scroll_move() {
        return false;
    }
    var set_scroller = function (lap, scroller) {
        if (scroller.lock) {
            if (document.addEventListener) {
                document.addEventListener('DOMMouseScroll', scrollFunc, false);
            }//W3C
            window.onmousewheel = document.onmousewheel = scrollFunc;//IE/Opera/Chrome

            if (scroller.body_hidden) {
                $(document.body).css({
                    "overflow-x": "hidden",
                    "overflow-y": "hidden"
                });
            }
            if (scroller.content_lock) {
                $(lap).find(".lap_content").bind('mousewheel', stop_scroll_move);
                $(lap).find(".lap_content").bind('DOMMouseScroll', stop_scroll_move);
            }

        }
    }
    var remove_scroller = function (scroller) {
        if (scroller.relative) {
            return;
        }
        if (document.removeEventListener) {
            document.removeEventListener('DOMMouseScroll', scrollFunc, false);
        }//W3C
        window.onmousewheel = document.onmousewheel = function () {
        };//IE/Opera/Chrome
        $(document.body).css({
            "overflow-x": "auto",
            "overflow-y": "auto"
        });
    }
    var lap_close = function (lap, options) {
        $(lap).find(".lap_content").fadeOut(500, function () {
            $(lap).addClass("hidden").find(".lap_mask").addClass("hidden");
            remove_scroller(options.scroller);
            options.close.callBack();
        });
    }
    var lap_close_quick = function (lap, options) {
        $(lap).addClass("hidden").find(".lap_mask").addClass("hidden");
        remove_scroller(options.scroller);
        if (!options.close.quick_mode) {
            options.close.callBack();
        }

    }
    var lap_show = function (lap, options) {
        if (options.mask.show) {
            $(lap).find(".lap_mask").removeClass("hidden");
        }
        $(lap).removeClass("hidden");
        $(lap).find(".lap_content").fadeIn(500, function () {
            set_scroller(lap, options.scroller);
        });
    }
    var lap_show_quick = function (lap, options) {
        if (options.mask.show) {
            $(lap).find(".lap_mask").removeClass("hidden");
        }
        $(lap).removeClass("hidden");
        $(lap).find(".lap_content").fadeIn(0);
        set_scroller(lap, options.scroller);
    }
    var overlap = function (option) {
        this.showed = false;
        this.opts = $.extend(true, {}, default_option, option || {});
        this.lap = load_lap(this.opts);
    };
    overlap.prototype = {
        show: function (body, call_back) {
            var _this = this;
            set_content_body(_this.lap, body);
            if ("function" == typeof (call_back)) {
                call_back(_this.lap);
            }
            lap_show(_this.lap, _this.opts);
            _this.showed = true;
        },
        show_quick: function (body, call_back) {
            var _this = this;
            set_content_body(_this.lap, body);
            if ("function" == typeof (call_back)) {
                call_back(_this.lap);
            }
            lap_show_quick(_this.lap, _this.opts);
            _this.showed = true;
        },
        show_change: function (callback) {
            var _this = this;
            $(_this.lap).find(".lap_content").fadeOut(300, callback);
        },
        close: function () {
            var _this = this;
            lap_close(_this.lap, _this.opts);
            _this.showed = false;
        },
        close_quick: function () {
            var _this = this;
            lap_close_quick(_this.lap, _this.opts);
            _this.showed = false;
        },
        reset: function (option) {
            var _this = this;
            _this.opts = $.extend(true, _this.opts, option || {});
            reload_lap(_this.lap, _this.opts);
        },
        lock: function () {
            var _this = this;
            $(_this.lap).find(".lock_mask").removeClass("hidden");
            $(_this.lap).find(".load_mask").removeClass("hidden");
        },
        unlock: function () {
            var _this = this;
            $(_this.lap).find(".lock_mask").addClass("hidden");
            $(_this.lap).find(".load_mask").addClass("hidden");
        }
    };
    overlap.prototype.constructor = overlap;
    return overlap;
}();

var MessageOverlap = function () {

    var MessageModule = {
        Overlap: null,
        List: {},
        Current: null
    }
    MessageModule.Push = function (name, message, callback) {
        var list = MessageModule.List;
        if (!list[name]) {
            list[name] = {};
        }
        list[name].Message = message;
        list[name].CallBack = callback;
    }

    function init() {
        MessageModule.Overlap = new overlap({
            mask: {
                show: true,
                close: false
            },
            scroller: {
                lock: true,
                body_hidden: false
            },
            position: {
                type: "center"
            },
            close: {
                show: false,
                type: "close_2"
            },
            content: {
                style: {
                    width: "516px",
                    height: "200px",
                    border: "16px solid rgba(0,0,0,0.15)"
                }
            }
        });
        $("#MessageOverlap #cancel_message").click(function () {
            MessageModule.Overlap.close();
            if (typeof MessageModule.Current.CallBack == "function") {
                MessageModule.Current.CallBack(false);
            }
        })
        $("#MessageOverlap #ok_message").click(function () {
            MessageModule.Overlap.close();
            if (typeof MessageModule.Current.CallBack == "function") {
                MessageModule.Current.CallBack(true);
            }
        })
    }

    var MessageOverlap = function () {
        init();
    }
    MessageOverlap.prototype = {
        show: function (name) {
            var module = MessageModule;
            if (module.List[name]) {
                var current = module.Current = module.List[name];
                $("#MessageOverlap .content_value").html(current.Message);
                module.Overlap.show("#MessageOverlap");
            }
        },
        show_quick: function (name) {
            var module = MessageModule;
            if (module.List[name]) {
                var current = module.Current = module.List[name];

                $("#MessageOverlap .content_value").html(current.Message);
                module.Overlap.show_quick("#MessageOverlap");
            }
        },
        push: function (name, message, callback) {
            MessageModule.Push(name, message, callback);
        }
    }
    MessageOverlap.prototype.constructor = MessageOverlap;
    return MessageOverlap;
}();


var MessageInfoOverlap = function () {
    var MessageClassId = "#MessageInfoOverlap"
    var MessageModule = {
        Overlap: null,
        List: {},
        Current: null
    }
    MessageModule.Push = function (name, message, callback) {
        var list = MessageModule.List;
        if (!list[name]) {
            list[name] = {};
        }
        list[name].Message = message;
        list[name].CallBack = callback;
    }

    function init() {
        MessageModule.Overlap = new overlap({
            mask: {
                show: true,
                close: false
            },
            scroller: {
                lock: true,
                body_hidden: false
            },
            position: {
                type: "center"
            },
            close: {
                show: false,
                type: "close_2",
                callBack: function () {
                    $("body").append($(MessageClassId).addClass("hidden"));
                },
            },
            content: {
                style: {
                    width: "516px",
                    height: "200px",
                    border: "16px solid rgba(0,0,0,0.15)"
                }
            }
        });
        $(MessageClassId).find("#cancel_message").click(function () {
            MessageModule.Overlap.close();
            if (typeof MessageModule.Current.CallBack == "function") {
                MessageModule.Current.CallBack(false);
            }
        })
        $(MessageClassId).find("#ok_message").click(function () {
            MessageModule.Overlap.close();
            if (typeof MessageModule.Current.CallBack == "function") {
                MessageModule.Current.CallBack(true);
            }
        })
    }

    var MessageInfoOverlap = function () {
        init();
    }
    MessageInfoOverlap.prototype = {
        show: function (name, info, type) {
            var module = MessageModule;
            if (module.List[name]) {
                var current = module.Current = module.List[name];
                $(MessageClassId).find(".content_value").html(current.Message);
                if (info) {
                    $(MessageClassId).find(".content_info").removeClass("hidden");
                    $(MessageClassId).find(".content_info").empty().append(info);
                } else {
                    $(MessageClassId).find(".content_info").addClass("hidden");
                }
                $(MessageClassId).find(".content_info").removeClass().addClass("content_info");
                $(MessageClassId).find("#cancel_message").removeClass("hidden");
                if (type) {
                    switch(type){
                        case "content_info_type_2":
                            $(MessageClassId).find("#cancel_message").addClass("hidden");
                            break;
                    }
                    $(MessageClassId).find(".content_info").addClass(type);
                }
                module.Overlap.reset({
                    content: {
                        style: {
                            height: $(MessageClassId).height(),
                        }
                    }
                })
                module.Overlap.show(MessageClassId);
            }
        },
        show_quick: function (name, info, type) {
            var module = MessageModule;
            if (module.List[name]) {
                var current = module.Current = module.List[name];
                $(MessageClassId).find(".content_value").html(current.Message);
                if (info) {
                    $(MessageClassId).find(".content_info").removeClass("hidden");
                    $(MessageClassId).find(".content_info").empty().append(info);
                } else {
                    $(MessageClassId).find(".content_info").addClass("hidden");
                }
                if (type) {
                    $(MessageClassId).find(".content_info").addClass(type);
                }
                module.Overlap.reset({
                    content: {
                        style: {
                            height: $(MessageClassId).height(),
                        }
                    }
                })
                module.Overlap.show_quick(MessageClassId);
            }
        },
        push: function (name, message, callback) {
            MessageModule.Push(name, message, callback);
        }
    }
    MessageInfoOverlap.prototype.constructor = MessageInfoOverlap;
    return MessageInfoOverlap;
}();

var MessageTipsOverlap = function () {
    var OverlapModule = {
        show: false
    }
    var MessageModule = {
        Overlap: null,
        List: {},
        Current: null
    }

    MessageModule.Push = function (name, message) {
        var list = MessageModule.List;
        if (!list[name]) {
            list[name] = {};
        }
        list[name].Message = message;
    }

    function init() {
        MessageModule.Overlap = new overlap({
            mask: {
                show: false,
                close: false
            },
            scroller: {
                lock: false,
                relative: true
            },
            position: {
                type: "center"
            },
            close: {
                show: false,
                type: "close_2",
                callBack: function () {
                    OverlapModule.show = false;
                }
            },
            content: {
                style: {
                    width: "200px",
                    height: "auto"
                }
            }
        });
    }

    var MessageTipsOverlap = function () {
        init();
    }
    MessageTipsOverlap.prototype = {
        show: function (name) {
            if (OverlapModule.show) {
                return;
            }
            var module = MessageModule;
            if (module.List[name]) {
                OverlapModule.show = true;
                var current = module.Current = module.List[name];
                $("#MessageTipsOverlap .tips").html(current.Message);
                module.Overlap.show("#MessageTipsOverlap");
                setTimeout(function () {
                    module.Overlap.close();

                }, 2000)
            }
        },
        push: function (name, message) {
            MessageModule.Push(name, message);
        }
    }
    MessageTipsOverlap.prototype.constructor = MessageTipsOverlap;
    return MessageTipsOverlap;
}();