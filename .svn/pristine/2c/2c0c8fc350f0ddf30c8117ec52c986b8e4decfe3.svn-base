var _PAGE_MODEL;

$(function () {
    _PAGE_MODEL = new PageCore();
    var lay_out = new layout_controller();

    var num = $(".upmonitor_company").length;
    var current_rotation = num;
    if (num > 0) {
        // window.setInterval(function(){
        //         current_rotation--;
        //         if(current_rotation<0){
        //             current_rotation=num-1
        //         }
        //         else if(current_rotation>=num){
        //             current_rotation=0;
        //         }
        //         rotation(current_rotation,num);
        //     }, 10000); 
    }
    window.onresize = function () {
        lay_out.resizeContent();
    }
    if (toastr) {
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
            "hideMethod": "fadeOut" //消失时的动画方式
        };
    }
})

var layout_controller = function () {
    function mouseWheel() {

        if (document.addEventListener) {

            document.body.addEventListener('mousewheel', function (e) {
                return;
                // Detail(e);
                // e.stopPropagation();
                // e.preventDefalut();

            });

            document.body.addEventListener('DOMMouseScroll', function (e) {
                return;
                // Detail(e);
                // e.stopPropagation();
                // e.preventDefault();

            })
        } else {
            document.body.attachEvent('onmousewheel', function (event) {
                return;
                // Detail(event);
                // event.cancelBubble=true;
                // event.returnValue=false;
            })
        }

        function Detail(e) {
            ((-e.detail || e.wheelDelta) > 0) ? alert('top') : alert('down');
        }
    }

    function rotation(current, num) {
        var last_upmonitor = $(".upmonitor_list .upmonitor_company")[current];
        var height = $(last_upmonitor).addClass("current_rotation").height();

        $(last_upmonitor).css({ top: -height * (current) + "px", "display": "none" });
        $(".upmonitor_company").each(function () {
            var _this = this;
            if ($(_this).hasClass("current_rotation")) {
                $(_this).removeClass("current_rotation");
            }
            else {
                var top = $(_this).position().top;
                if ($(_this).hasClass("light_green_2")) {
                    $(_this).removeClass("light_green_2");
                }
                else {
                    $(_this).addClass("light_green_2");
                }
                $(_this).animate({ top: "+=" + height + "px" }, 1000);
            }
        });
        $(last_upmonitor).fadeIn(2000);
    }

    var gray_pic = function () {

    }

    function back_nav_scroller_show() {

        if ($(".nav_bar").length > 0) {
            var beforeScrollTop = document.body.scrollTop;
            var nav_showed = false;
            function nav_show() {
                nav_showed = true;
                $(".nav_bar").css({ "position": "fixed", "top": "0px" }).stop().animate({ "top": "80px" }, 100, function () {

                });
                $(".monitor_company_title_content").stop().animate({ "top": "136px" }, 100);
                $(".company_modules").stop().animate({ "top": "136px" }, 100);
            }
            function nav_hidden() {
                $(".nav_bar").stop().css({ "position": "absolute" });
                if (nav_showed) {
                    $(".monitor_company_title_content").stop().css({ "top": "80px" });
                    $(".company_modules").stop().animate({ "top": "80px" }, 100);
                }
                nav_showed = false;
            }
            function scroller_show(event) {
                event = event || window.event;
                var afterScrollTop = document.body.scrollTop || document.documentElement.scrollTop;
                delta = afterScrollTop - beforeScrollTop;
                beforeScrollTop = afterScrollTop;
                if (delta < 0) {
                    if (!nav_showed) {
                        nav_show();
                    }
                }
                else if (delta > 0) {
                    nav_hidden();
                }
                else {

                }
            }
            $(document).scroll(scroller_show);
        }
    }

    var layout_controller = function () {
        this.load_page();
    }
    layout_controller.prototype = {
        load_page: function () {
            this.resizeContent();
            this.menu_move();
            gray_pic();
            back_nav_scroller_show();
        },
        resizeContent: function () {
            if ($(".body_content").length > 0) {
                var width = parseInt(document.body.clientWidth);
                if (_PAGE_MODEL.browser == "ie6") {
                    width = width - $(".body_right").css("width");
                }
                var browser_width = parseInt(document.documentElement.clientWidth);
                browser_width = (document.documentElement.scrollWidth > document.documentElement.clientWidth) ? document.documentElement.scrollWidth : document.documentElement.scrollWidth;

                var menu_width = parseInt($(".nav_menu").css("width"));
                var left_width = parseInt($(".body_left").css("width")) || 0;
                var right_width = parseInt($(".body_right").css("width"));

                width = width - menu_width - left_width - right_width;
                width = width < 600 ? 600 : width;
                if (width <= 600) {
                    $(".body_right").addClass("hidden");
                    $(".body_center").css("margin-right", 0);
                    width = width + right_width;
                }
                else {
                    $(".body_right").removeClass("hidden");
                    $(".body_center").css("margin-right", right_width);
                }
                $(".body_center").css("width", width);
                $(".top_voice").css("width", width);
                var total_width = 0;

                try_catch(function () {
                    if ("undefined" != typeof page_resizeContent) {
                        page_resizeContent();
                    }
                });

                $(".body_inline_block").each(function () {
                    var _this = this;
                    if ($(_this).hasClass("width_100")) {
                        total_width += 100;
                    }
                    else if ($(_this).hasClass("width_50")) {
                        total_width += 50;
                    }
                    if (total_width > 100) {

                    }
                    else {

                    }
                    var block_width = width / 2 - 15;
                    $(_this).css("width", block_width);
                });
            }
            if ($(".body_content_2").length > 0) {
                // var height=$(".body_center").height();
                // var html_height=$("html").height();
                // if(height<html_height){
                //     $(".body_center").css("height",html_height-$(".nav_head").height());
                // }
            }
        },
        menu_move: function () {
            var num = $(".menu_span").length;
            var click = num;
            var current_last = num - 1;
            if (_PAGE_MODEL.browser == "ie6") {
                $(".menu_top").addClass("hidden");
                $(".menu_bottom").addClass("hidden");
                $(".nav_menu").css("height", "auto");
                $(".nav_menu").css("padding-bottom", "20px");
            }
            $(".menu_top").click(function () {
                if (click < num) {
                    return;
                }
                click = 0;
                current_last = current_last + 1;
                if (current_last >= num) {
                    current_last = 0;
                }
                var first_menu = $(".menu_span")[current_last];
                var height = $(first_menu).addClass("current_menu").outerHeight(true);
                height = height - 8;
                $(first_menu).css({ top: (height) * (num - current_last - 1) + "px", "display": "none" });
                $(".menu_span").each(function () {
                    var _this = this;
                    if ($(_this).hasClass("current_menu")) {
                        $(_this).removeClass("current_menu");
                    }
                    else {
                        var top = $(_this).position().top;
                        $(_this).animate({ top: "-=" + height + "px" }, 300, function () {
                            click++;
                        });
                    }
                });
                $(first_menu).fadeIn(600, function () {
                    click++;
                });
            });

            $(".menu_bottom").click(function () {
                if (click < num) {
                    return;
                }
                click = 0;
                var last_menu = $(".menu_span")[current_last];
                var height = $(last_menu).addClass("current_menu").outerHeight(true);
                height = height - 8;
                //height=height+parseInt($(last_menu).css("padding-top"))*2;
                $(last_menu).css({ top: -(height) * (current_last) + "px", "display": "none" });
                $(".menu_span").each(function () {
                    var _this = this;
                    if ($(_this).hasClass("current_menu")) {
                        $(_this).removeClass("current_menu");
                    }
                    else {
                        var top = $(_this).position().top;
                        $(_this).animate({ top: "+=" + height + "px" }, 300, function () {
                            click++;
                        });
                    }
                });
                $(last_menu).fadeIn(600, function () {
                    click++;
                });
                current_last = current_last - 1;
                if (current_last < 0) {
                    current_last = num - 1;
                }
            });
        },
        re_back_nav_show: function () {
            back_nav_scroller_show();
        }
    };
    layout_controller.prototype.constructor = layout_controller;
    return layout_controller;
}();

var PageCore = function () {
    var PageCore = function () {
        this.browser = "unknow";
        this.clintWidth = 0;
        this.clintHeight = 0;
        this.load_info();
    }
    PageCore.prototype = {
        load_info: function () {
            this.browser = this.get_browser_type();
            this.clintWidth = this.get_body_width();
            this.clintHeight = this.get_body_height();
        },
        get_browser_type: function () {
            if ((navigator.userAgent.indexOf('MSIE') >= 0)
                && (navigator.userAgent.indexOf('Opera') < 0)) {
                var browser = navigator.appName
                var b_version = navigator.appVersion
                var version = b_version.split(";");
                var trim_Version = version[1].replace(/[ ]/g, "");
                if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE6.0") {
                    return "ie6";
                }
                else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE7.0") {
                    return "ie7";
                }
                else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE8.0") {
                    return "ie8";
                }
                else if (browser == "Microsoft Internet Explorer" && trim_Version == "MSIE9.0") {
                    return "ie9";
                }
                else {
                    return "ie";
                }
            }
            return "chrome";
        },
        get_body_width: function () {
            return (document.documentElement.scrollWidth > document.documentElement.clientWidth) ? document.documentElement.scrollWidth : document.documentElement.scrollWidth;
        },
        get_body_height: function () {
            return (document.documentElement.scrollHeight > document.documentElement.clientHeight) ? document.documentElement.scrollHeight : document.documentElement.scrollHeight;
        },
        gray: function (imgObj) {
            var canvas = document.createElement('canvas');
            var canvasContext = canvas.getContext('2d');

            var imgW = imgObj.width;
            var imgH = imgObj.height;
            if (imgW > 0 && imgH > 0) {
                canvas.width = imgW;
                canvas.height = imgH;

                canvasContext.drawImage(imgObj, 0, 0);
                var imgPixels = canvasContext.getImageData(0, 0, imgW, imgH);

                for (var y = 0; y < imgPixels.height; y++) {
                    for (var x = 0; x < imgPixels.width; x++) {
                        var i = (y * 4) * imgPixels.width + x * 4;
                        var avg = (imgPixels.data[i] + imgPixels.data[i + 1] + imgPixels.data[i + 2]) / 3;
                        imgPixels.data[i] = avg;
                        imgPixels.data[i + 1] = avg;
                        imgPixels.data[i + 2] = avg;
                    }
                }
                canvasContext.putImageData(imgPixels, 0, 0, 0, 0, imgPixels.width, imgPixels.height);
                return canvas.toDataURL();
            } else {
                return false;
            }
        }
    };
    PageCore.prototype.constructor = PageCore;
    return PageCore;
}();

var PositionCore = function () {
    var PositionCore = function () {

    }
    PositionCore.prototype = {
        fixed_position: function (id, opts) {
            var _this = this;
            switch (_PAGE_MODEL.browser) {
                case "ie6":
                    _this.fixed_position_move(id, opts);
                    break;
                case "ie8":
                case "ie":
                case "chrome":
                default:
                    _this.fixed_position_move(id, opts);
                    break;
            }
        },
        fixed_position_move: function (id, opts) {
            var top = (document.documentElement.scrollTop || document.body.scrollTop);
            $(id).css("top", top);
        }
    };
    PositionCore.prototype.constructor = PositionCore;
    return PositionCore;
}();

var debug = function (message) {
    if (!window.debug) {
        window.debug = function (message) {
            try {
                if (!window.console) {
                    window.console = {};
                    window.console.log = function () {
                        return;
                    }
                }
                window.console.log(message + ' ');
            } catch (e) { }
        }
    }
    window.console.log(message);
}

var try_catch = function (call_back) {
    try {
        call_back();
    }
    catch (message) {
        alert(message.message);
    }
}

var Cookie = function () {
    var load_cookie = function () {
        var strCookie = document.cookie;
        var arrCookie = strCookie.split("; ");
        var modelCookie = {};
        for (var i = 0; i < arrCookie.length; i++) {
            var arr = arrCookie[i].split("=");
            modelCookie[arr[0]] = arr[1];
        }
        return modelCookie;
    }
    var Cookie = function () {
        this.cookies = load_cookie();
    }
    Cookie.prototype = {
        get_cookie: function (cookieName) {
            if (this.cookies[cookieName] != undefined) {
                return this.cookies[cookieName];
            }
            return null;
        },
        set_cookie: function (cookieName, value, expires) {
            this.cookies[cookieName] = value;
            document.cookie = cookieName + "=" + value + ";expires=" + expires + ";path=/";
        }
    }
    Cookie.prototype.constructor = Cookie;
    return Cookie;
}();

RemoveByValue = function (arr, val) {
    for (var i = 0; i < arr.length; i++) {
        if (arr[i] == val) {
            arr.splice(i, 1);
            break;
        }
    }
}


String.prototype.format = function () {
    if (arguments.length == 0) return this;
    for (var s = this, i = 0; i < arguments.length; i++)
        s = s.replace(new RegExp("\\{" + i + "\\}", "g"), arguments[i]);
    return s;
};
function toCompanyBrefBizView(companyName, deep) {
    window.location.href = ctx + "/biz/applyCompanyBrefBizView.do?companyName=" + encodeURIComponent(companyName) + "&deep=" + deep + "&isDecode=true";
}

String.prototype.trim = function () {
    return this.replace(/^\s\s*/, '').replace(/\s\s*$/, '');
}

var tostring = function (fn) {
    return fn.toString().split('\n').slice(1, -1).join('\n') + '\n'
}

function applyAjax(url, param, callback, requestType, dataType, complete) {
    requestType = requestType || "POST";
    dataType = dataType || "json";

    $.ajax({
        url: url,
        type: requestType,
        data: param,
        dataType: dataType,
        success: function (result) {
            if (typeof callback != 'undefined' & callback instanceof Function) {
                callback(result);
            }
        },
        error: function () { },
        complete: function (request, textStatus) {
            if (!textStatus || textStatus == "parsererror"&&dataType!="json"
                || request != undefined && request.responseText.indexOf("logout.jsp") > -1
                || request != undefined && request.responseText.indexOf("login4Web.js") > -1) {
                toastr.error('登录已过期，需要重新登录！');
                var time = setTimeout(function () {
                    clearTimeout(time);
                    window.location = ctx + "/login";
                }, 2000);

            } else {
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
            var result = {
                resultCode: "500",
                resultMsg: "",
                resultData: null
            }
            if (typeof complete != 'undefined' & complete instanceof Function) {
                complete();
            }
        }
    });

}




// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function (fmt) { //author: meizz 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}

//密码长度6位以上且须包含大写、小写、数字、特殊符号中的任意3种
function testPass(str) {
    var rC = {
        lW: '[a-z]',
        uW: '[A-Z]',
        nW: '[0-9]',
        sW: '[\\u0020-\\u002F\\u003A-\\u0040\\u005B-\\u0060\\u007B-\\u007E]'
    };
    function Reg(str, rStr) {
        var reg = new RegExp(rStr);
        if (reg.test(str)) return true;
        else return false;
    }
    if (str.length < 6) {
        return { "flag": false, "msg": "您的密码长度太短" };
    } else {
        var tR = {
            l: Reg(str, rC.lW),
            u: Reg(str, rC.uW),
            n: Reg(str, rC.nW),
            s: Reg(str, rC.sW)
        };
        if ((tR.l && tR.u && tR.n) || (tR.l && tR.u && tR.s) || (tR.s && tR.u && tR.n) || (tR.s && tR.l && tR.n)) {
            return { "flag": true, "msg": "密码符合要求" };
        } else {
            return { "flag": false, "msg": "您的密码必须含有“大写字母”、“小写字母”、“数字”、“符号”中的任意三种" };
        }
    }
}

function detectCapsLock(event) {
    var e = event || window.event;
    var keyCode = e.keyCode || e.which; // 按键的keyCode 
    var isShift = e.shiftKey || (keyCode == 16) || false; // shift键是否按住
    console.log("keycode=" + keyCode + ",isShift=" + isShift);
    if ((keyCode >= 65 && keyCode <= 90)) {
        return true;
    } else if (keyCode >= 97 && keyCode <= 122) {
        return false;
    } else {
        return false;
    }
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return r[2]; return null;
}

function IsFunction(callback) {
    return typeof callback == "function";
}

function parseBoolean(value) {
    switch (typeof (value)) {
        case "boolean":
            return value;
        case "string":
            if (value == "false") {
                return false;
            }
            return true;
        case "number":
            return value == 0;
        case "object":
            return true;
        case "function":
            return true;
        default:
            return false;
    }
}


function regsymbol(value) {
	return value.replace(/[,.，。]+$/g, '');
}