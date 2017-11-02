var LoadWaiting = function (name) {
    var Conf = {
        mask: $(tostring(function () {/*
                <div class="relative_center_block">
                    <div class="load_waiting_mask"></div>
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
                <div>
            */}))
    }
    function init() {

    }

    var LoadWaiting = function (name) {
        this.name = name;
        this.mask = $(Conf.mask).clone();
        this.list = [];
        this.id = null;
    }

    LoadWaiting.prototype = {
        show: function (id) {
            if (id) {
                this.id = id;
                var height = $(id).height();
                var width = $(id).width();
                $(this.mask).css({
                    "position": "relative",
                    "z-index": "90000",
                    "width": $(id).outerWidth(true),
                    "left": (-1 * parseInt($(id).css("padding-left"))) + (-1 * parseInt($(id).css("margin-left"))),
                    "opacity": 0
                })
                $(this.mask).find(".load_waiting_mask").css({
                    "opacity": "0.74",
                    "width": $(id).outerWidth(true),
                    "height": $(id).outerHeight(true),
                    "background": "#ffffff",
                });
                $(this.mask).find(".spinner").css({
                    "top": -1 * $(id).outerHeight(true) * 0.6
                });
                $(id).prepend(this.mask);
                $(this.mask).animate({ "opacity": 1 }, 1000);
            }
        },
        close: function () {
            $(this.mask).remove();
        }
    }
    LoadWaiting.prototype.constructor = LoadWaiting;
    return LoadWaiting;
}();