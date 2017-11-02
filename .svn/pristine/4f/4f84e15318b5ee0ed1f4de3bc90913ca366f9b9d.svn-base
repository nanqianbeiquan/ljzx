var CompanyRenameRepository = function () {
    function GetCompanyRename(model, callback) {
        var data = {
            companyName: model.companyName
        }
        applyAjax("{0}/companyInfo/getUsedName".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (typeof (result) == "string" && result.length <= 5) {
                    result = null;
                }
                callback(result || []);
            }
        });
    }
    var CompanyRenameRepository = function () {

    }
    CompanyRenameRepository.prototype = {
        get_company_rename: function (model, callback) {
            GetCompanyRename(model, callback);
        }
    }
    CompanyRenameRepository.prototype.constructor = CompanyRenameRepository;
    return CompanyRenameRepository;
}()

var CompanyRenameController = function () {
    var repository = new CompanyRenameRepository();
    function bind_rename_hover() {

    }

    function init_company_detail_rename_block(list, page) {
        var block = $(tostring(function () {/*
            <span class="inline_div company_rename_block" id="company_rename_block">
                <style>
                    .company_rename_block{
                        position:absolute;
                        width:60px;
                    }
                    .company_rename_block .rename_title{
                        font-size: 12px;
                        height: 14px;
                        line-height: 13px;
                        width: 40px;
                        border: 1px solid #ffffff;
                        text-align: center;
                        padding: 2px 15px 1px 0px;
                        cursor: pointer;
                        font-weight: 500;
                        background: url(../images/modules/monitor/rename_icon.png) no-repeat 40px 2px;
                        position: relative;
                        top: 0px;
                        left:10px;
                        border-radius:2px;
                    }
                    .company_rename_block .rename_title:hover{
                        color:#2ea7e0;
                        background: url(../images/modules/monitor/rename_hover_icon.png) no-repeat 40px 2px;
                        background-color:#ffffff;
                    }
                    .company_rename_block:hover .rename_list_block{
                        display:block;
                    }
                    .company_rename_block:hover .rename_title{
                        color:#2ea7e0;
                        background: url(../images/modules/monitor/rename_hover_icon.png) no-repeat 40px 2px;
                        background-color:#ffffff;
                    }
                    .company_rename_block .rename_list_block{
                        cursor:default;
                        display:none;
                        border: 1px solid #d7d7d7;
                        background: #ffffff;
                        color: #666666;
                        font-weight: 700;
                        font-size: 14px;
                        padding: 0px 20px 14px 20px;
                        line-height: 24px;
                        position: relative;
                        left: 10px;
                        width: auto;
                        min-width: 220px;
                        top: 17px;
                        box-shadow: 2px 2px 4px #d7d7d7;
                        border-radius: 3px;
                    }
                    .company_rename_block .rename_list_block_arr{
                            width: 0;
                            height: 0;
                            border-left: 10px solid transparent;
                            border-right: 10px solid transparent;
                            border-bottom: 14px solid #d7d7d7;
                            position: relative;
                            top: -14px;
                    }
                    .company_rename_block .rename_list_block_arr_back{
                            width: 0;
                            height: 0;
                            border-left: 9px solid transparent;
                            border-right: 9px solid transparent;
                            border-bottom: 13px solid #ffffff;
                            position: relative;
                            left: -9px;
                            top: 1px;
                    }
                    .company_info_rename_block .rename_title{
                        color:#ffffff;
                        background: url(../images/modules/monitor/rename_icon.png) no-repeat 40px 2px;
                        background-color:#2ea7e0;
                        top:9px;
                    }
                    .company_info_rename_block:hover .rename_title{
                        color:#ffffff;
                        background: url(../images/modules/monitor/rename_icon.png) no-repeat 40px 2px;
                        background-color:#1b89bd;
                    }
                    .company_info_rename_block .rename_list_block{
                        top:25px;
                        z-index:910;
                    }
                    .relative_company_info_rename_block .rename_title{
                        color:#ffffff;
                        background: url(../images/modules/monitor/rename_icon.png) no-repeat 40px 2px;
                        background-color:#2ea7e0;
                        top:-2px;
                    }
                    .relative_company_info_rename_block:hover .rename_title{
                        color:#ffffff;
                        background: url(../images/modules/monitor/rename_icon.png) no-repeat 40px 2px;
                        background-color:#1b89bd;
                    }
                    .relative_company_info_rename_block .rename_list_block{
                        top:15px;
                        z-index:910;
                    }
                </style>
                <div class="inline_div rename_title">曾用名</div>
                <div class="inline_div rename_list_block">
                    <div class="rename_list_block_arr">
                        <div class="rename_list_block_arr_back"></div>
                    </div>
                </div>
            </span>
        */}));
        for (var index = 0; index < list.length; index++) {
            $(block).find(".rename_list_block").append("<div>{0}</div>".format(list[index]))
        }
        if (page) {
            var class_name = "";
            switch (page) {
                case "more":
                    class_name = "company_info_rename_block";
                    break;
                case "relative_more":
                    class_name = "relative_company_info_rename_block";
                    break;
                default:
                    break;
            }
            $(block).addClass(class_name);
        }
        return block;
    }

    function load_company_detail_rename_callback(result) {
        if (result.length > 0) {
            $(".monitor_company_name").append(init_company_detail_rename_block(result));
        }
    }
    function load_company_more_rename_callback(result) {
        if (result.length > 0) {
            $(".company_title").append(init_company_detail_rename_block(result, "more"));
        }
    }
    function load_relative_company_detail_rename_callback(result) {
        if (result.length > 0) {
            $(".company_name").append(init_company_detail_rename_block(result, "relative_more"));
        }
    }
    var CompanyRenameController = function () {

    }
    CompanyRenameController.prototype = {
        load_company_detail_rename: function () {
            var model = {
                companyName: $("#companyName").val()
            }
            repository.get_company_rename(model, load_company_detail_rename_callback);
        },
        load_relative_company_detail_rename: function () {
            var model = {
                companyName: $("#companyName").val()
            }
            repository.get_company_rename(model, load_relative_company_detail_rename_callback);
        },
        load_company_more_rename: function () {
            var model = {
                companyName: $("#MonitorCompanyName").text().trim()
            }
            repository.get_company_rename(model, load_company_more_rename_callback);
        },
        load_relative_company_more_rename: function () {
            var model = {
                companyName: $("#companyName").val()
            }
            repository.get_company_rename(model, load_company_detail_rename_callback);
        }
    }
    CompanyRenameController.prototype.constructor = CompanyRenameController;
    return CompanyRenameController;
}()