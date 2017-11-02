var CompanyRelationShipRepository = function () {
    function GetRelationshipCompanyList(model, callback) {
        var data = {
            monitorId: model.monitorId,
            currentPageNo: model.page.pageIndex,
            pageSize: model.page.pageSize
        }

        applyAjax("{0}/monitorRelationCompany/getMonitorRelationCompanyPageWithJSON".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    page: {
                        pageSize: result.pageSize,
                        pageIndex: result.currentPageNo,
                        totalPage: result.pageSum,
                        totalSum: result.recordSum
                    },
                    result: {
                        list: result.results || []
                    }
                }
                callback(resultModel);
            }
        })
    }

    function GetRelationshipCompanyAdd(model, callback) {
        var data = {
            currentPageNo: model.page.pageIndex,
            pageSize: model.page.pageSize,
            companyName: model.companyName,
            monitorId: model.monitorId
        }

        applyAjax("{0}/addRelationCtl/getRelationCompany".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    view: result
                }
                callback(resultModel);
            }
        }, "post", "html")
    }

    function GetRelationshipPersonList(model, callback) {
        var data = {
            monitorId: model.monitorId,
            currentPageNo: model.page.pageIndex,
            pageSize: model.page.pageSize
        }

        applyAjax("{0}/monitorRelationPerson/getMonitorRelationPersonPageWithJOSN".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    page: {
                        pageSize: result.pageSize,
                        pageIndex: result.currentPageNo,
                        totalPage: result.pageSum,
                        totalSum: result.recordSum
                    },
                    result: {
                        list: result.results || []
                    }
                }
                callback(resultModel);
            }
        })
    }

    function GetRelationshipPersonAdd(model, callback) {
        var data = {
            companyName: model.companyName,
            monitorId: model.monitorId
        }

        applyAjax("{0}/addRelationCtl/getRelationPerson".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    view: result
                }
                callback(resultModel);
            }
        }, 'post', 'html');
    }

    function GetShareholderList(model, callback) {
        var data = {
            companyName: model.companyName,
            monitorId: model.monitorId,
            currentPageNo: model.page.pageIndex,
            pageSize: model.page.pageSize
        }

        applyAjax("{0}/addRelationCtl/getStockHolderCompanyList".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    view: result,
                    page: model.page,
                }
                callback(resultModel);
            }
        }, 'post', 'html')
    }

    function GetInvestmentList(model, callback) {
        var data = {
            companyName: model.companyName,
            monitorId: model.monitorId,
            currentPageNo: model.page.pageIndex,
            pageSize: model.page.pageSize
        }

        applyAjax("{0}/addRelationCtl/getInvestmentCompanyList".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    view: result,
                    page: model.page
                }
                callback(resultModel);
            }
        }, 'post', 'html')
    }

    function GetSearchCompanyList(model, callback) {
        var data = {
            keyword: model.model.keyword,
            companyName: model.companyName,
            monitorId: model.monitorId,
            currentPageNo: model.page.pageIndex,
            pageSize: model.page.pageSize
        }

        applyAjax("{0}/addRelationCtl/search4Company1.do".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    view: result,
                    page: model.page
                }
                callback(resultModel);
            }
        }, 'post', 'html')
    }

    function PostRelationshipCompany(model, callback) {
        var data = {
            companyName: model.companyName,
            monitorId: model.monitorId
        }

        applyAjax("{0}/monitorRelationCompany/addMonitorRelationCompany".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    resultCode: result.resultCode,
                    resultData: result.resultData,
                    resultMsg: result.resultMsg
                }
                callback(resultModel);
            }
        })
    }

    function PostRelationshipPerson(model, callback) {
        var data = {
            name: model.name,
            province: model.province,
            city: model.city,
            idNumber: model.idNumber,
            companyName: model.companyName,
            monitorId: model.monitorId
        }

        applyAjax("{0}/monitorRelationPerson/addMonitorRelationPerson".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                var resultModel = {
                    resultCode: result.resultCode,
                    resultData: result.resultData,
                    resultMsg: result.resultMsg
                }
                callback(resultModel);
            }
        })
    }

    var CompanyRelationShipRepository = function () {

    }
    CompanyRelationShipRepository.prototype = {
        get_relationship_company_list: function (model, callback) {
            GetRelationshipCompanyList(model, callback);
        },
        get_relationship_company_add: function (model, callback) {
            GetRelationshipCompanyAdd(model, callback);
        },
        get_relationship_person_list: function (model, callback) {
            GetRelationshipPersonList(model, callback);
        },
        get_relationship_person_add: function (model, callback) {
            GetRelationshipPersonAdd(model, callback);
        },
        get_shareholder_list: function (model, callback) {
            GetShareholderList(model, callback);
        },
        get_investment_list: function (model, callback) {
            GetInvestmentList(model, callback);
        },
        get_search_company_list: function (model, callback) {
            GetSearchCompanyList(model, callback);
        },
        post_relationship_company: function (model, callback) {
            PostRelationshipCompany(model, callback);
        },
        post_relationship_person: function (model, callback) {
            PostRelationshipPerson(model, callback);
        }
    }
    CompanyRelationShipRepository.prototype.constructor = CompanyRelationShipRepository;
    return CompanyRelationShipRepository;
}();

var CompanyRelationShip = function (config) {
    var repository = new CompanyRelationShipRepository();



    var Models = {
        Overlaps: {
            RelationShipOverlap: null,
            MessageOverlap: null,
            MessageInfoOverlap: null,
        },
        LoadWaitingList: {
            WaitingModel: null
        }
    }

    var Modules = {
        RelationShip: function () {
            var ModuleConfig = null;
            var ModuleData = {
                CurrentCompany: null,
                CurrentPerson: null,
            }


            function scroll_move(e) {
                e = e || window.event;
                var delta = e.wheelDelta || e.detail || e.originalEvent.detail || e.originalEvent.deltaY || -1 * e.originalEvent.wheelDelta;
                delta = -1 * (delta / Math.abs(delta)) * 100;
                var top = $(this).scrollTop() - delta;
                $(this).scrollTop(top);
                if ($(this).scrollTop() <= 0) {
                    top = $(this).find(".content").scrollTop() - delta;
                    $(this).find(".content").scrollTop(top);
                }
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

            function load_waiting_show(id) {
                if (Models.LoadWaitingList.WaitingModel) {
                    Models.LoadWaitingList.WaitingModel.close();
                }
                Models.LoadWaitingList.WaitingModel = new LoadWaiting();
                Models.LoadWaitingList.WaitingModel.show(id);
            }

            function load_waiting_close() {
                if (Models.LoadWaitingList.WaitingModel) {
                    Models.LoadWaitingList.WaitingModel.close();
                    Models.LoadWaitingList.WaitingModel = null;
                }
            }

            function init_module_config(name, id) {
                ModuleData.CurrentCompany = null;
                ModuleData.CurrentPerson = null;
                if (ModuleConfig && ModuleConfig.MonitorId == id) {
                    return;
                }
                ModuleConfig = {
                    MonitorId: id,
                    CompanyName: name,
                    RelationCompanyModel: {
                        CurrentListPage: 0,
                        CurrentAddPage: 0,
                        PageSize: 10,
                        RelationshipListShow: false,
                        RelationshipAddShow: false,
                        CallbackList: load_relationship_company_list,
                        RepositoryList: repository.get_relationship_company_list,
                        CallbackAdd: load_relationship_company_add,
                        RepositoryAdd: repository.get_relationship_company_add,
                        LoadWaitingBlock: "#RelationshipCompanyList",
                        LoadWaitingAddBlock: "#RelationshipCompanyAdd"
                    },
                    ShareholderModel: {
                        CurrentListPage: 0,
                        PageSize: 10,
                        RelationshipListShow: false,
                        CallbackList: load_shareholder_list,
                        RepositoryList: repository.get_shareholder_list,
                        LoadWaitingBlock: "#RelationshipCompanyAdd"
                    },
                    InvestmentModel: {
                        CurrentListPage: 0,
                        PageSize: 10,
                        RelationshipListShow: false,
                        CallbackList: load_investment_list,
                        RepositoryList: repository.get_investment_list,
                        LoadWaitingBlock: "#RelationshipCompanyAdd"
                    },
                    SearchCompanyModel: {
                        CurrentListPage: 0,
                        PageSize: 10,
                        RelationshipListShow: false,
                        CallbackList: load_search_company_list,
                        RepositoryList: repository.get_search_company_list,
                        LoadWaitingBlock: "#RelationshipCompanyAdd"
                    },
                    RelationPersonModel: {
                        CurrentListPage: 0,
                        CurrentAddPage: 0,
                        PageSize: 10,
                        RelationshipListShow: false,
                        RelationshipAddShow: false,
                        CallbackList: load_relationship_person_list,
                        RepositoryList: repository.get_relationship_person_list,
                        CallbackAdd: load_relationship_person_add,
                        RepositoryAdd: repository.get_relationship_person_add,
                        LoadWaitingBlock: "#RelationshipPersonList",
                        LoadWaitingAddBlock: "#RelationshipPersonAdd"
                    }
                }
            }

            function init_relationship_overlap() {
                return new overlap({
                    scroller: {
                        lock: true,
                        body_hidden: false
                    },
                    close: {
                        callBack: function () {
                            getMonitorCompanyList(pageNo, pageSize);
                        }
                    },
                    content: {
                        style: {
                            width: "600px",
                            height: "600px",
                            border: "15px solid rgba(0,0,0,0.15)"
                        }
                    }
                });
            }

            function init_relationship_block() {
                var block = tostring(function () {/*
                    <div class="hidden group_edit_block" id="RelationshipBlock">
                        <style>
                            #RelationshipBlock #RelationshipCompanyList{height:550px;width:600px;}
                            #RelationshipBlock #RelationshipCompanyAdd{height:550px;width:600px;}
                            #RelationshipBlock #RelationshipPersonList{height:550px;width:600px;}
                            #RelationshipBlock #RelationshipPersonAdd{height:550px;width:600px;}
                            #RelationshipBlock .label_list{width:35px;text-align:center;margin:0px 0px 0px 0px;}
                            #RelationshipBlock .label_list .label{cursor:pointer;color:#666666;border:1px solid #d7d7d7;line-height:20px;padding:5px 0px;width:20px;text-align:center;margin:5px 0px 0px 0px;border-radius:10px 2px 10px 2px;min-height:60px;}
                            #RelationshipBlock .label_list .selected{background:#2ea7e0;color:#ffffff;border:1px solid #2ea7e0;}
                        </style>
                        <div class="mode_selector">
                            <div id="company_mode" class="selected inline_div add_mode">关系企业 
                            </div><div id="person_mode" class=" inline_div add_mode">关系自然人</div>
                            <div class="relative_left_block">
                                <div class="mode_arr triangle-down"></div>
                            </div>
                        </div>
                        <div class="mode_div company_mode_div ">
                            <div class="relative_left_block">
                                <div class="label_list">
                                    <div class="label inline_div selected" mode='0'>企业</div>
                                    <div class="label add_label inline_div" mode='1'>新增</div>
                                </div>
                            </div>
                            <div id="RelationshipCompanyList"></div>
                            <div id="RelationshipCompanyAdd"></div>
                        </div>
                        <div class="mode_div person_mode_div hidden">
                            <div class="relative_left_block">
                                <div class="label_list">
                                    <div class="label inline_div selected" mode='2'>自然人</div>
                                    <div class="label add_label inline_div" mode='3'>新增</div>
                                </div>
                            </div>
                            <div id="RelationshipPersonList"></div>
                            <div id="RelationshipPersonAdd"></div>
                        </div>
                    </div>
                */})
                $("body").append(block);

                if (ControllerConfig.RelationShipMode & 0b1 === 1) {
                    $("#RelationshipBlock .label_list .label").addClass("hidden");
                    $("#RelationshipBlock .label_list .label").removeClass("selected");
                    $("#RelationshipBlock .label_list .add_label").removeClass("hidden");
                    $("#RelationshipBlock .label_list .add_label").addClass("selected");
                }
                if (ControllerConfig.RelationShipMode & 0b10 === 2) {
                    $("#RelationshipBlock .label_list").addClass("hidden");
                }
            }

            function init_relationship_list(page, containers, callbackModel) {

                page = page + 1 || relationModel.CurrentListPage + 1;
                if (!callbackModel) {
                    return;
                }
                var relationModel = callbackModel.relationModel || null;
                var name = callbackModel.name;
                var monitorId = callbackModel.monitorId;
                if (!relationModel || (relationModel.RelationshipListShow && page == relationModel.CurrentListPage)) {
                    return;
                }
                load_waiting_show(relationModel.LoadWaitingBlock);
                var model = {
                    page: {
                        pageSize: relationModel.PageSize,
                        pageIndex: page
                    },
                    monitorId: monitorId,
                    companyName: name,
                    model: callbackModel.model || null
                }
                relationModel.RepositoryList(model, relationModel.CallbackList);
            }

            function init_relationship_add(page, containers, callbackModel) {
                if (!callbackModel) {
                    return;
                }
                var relationModel = callbackModel.relationModel || null;
                var name = callbackModel.name;
                var monitorId = callbackModel.monitorId;
                if (!relationModel || relationModel.RelationshipAddShow) {
                    return;
                }
                load_waiting_show(relationModel.LoadWaitingAddBlock);
                var model = {
                    page: {
                        pageSize: relationModel.PageSize,
                        pageIndex: page + 1
                    },
                    companyName: name,
                    monitorId: monitorId
                }
                relationModel.RepositoryAdd(model, relationModel.CallbackAdd);
            }

            function bind_relationship_block_events() {
                $("#RelationshipBlock .add_mode").click(click_relationship_mode);
                $("#RelationshipBlock .label_list .label").click(click_relationship_mode_label);
                function click_relationship_mode() {
                    var _this = this;
                    if ($(_this).hasClass("selected")) {
                        return;
                    }
                    $("#RelationshipBlock .add_mode").removeClass("selected");
                    $(_this).addClass("selected");
                    $("#RelationshipBlock .mode_arr").animate({ left: 128 + 265 * parseInt($(_this).index()) }, 500, function () {
                        $("#RelationshipBlock .mode_div").addClass("hidden");
                        $("#RelationshipBlock .mode_div:eq({0})".format($(_this).index())).removeClass("hidden");
                    })
                }
            }

            function bind_relationship_company_list() {
                $("#RelationshipCompanyList .company_list").bind('mousewheel', scroll_move);
                $("#RelationshipCompanyList .company_list").bind('DOMMouseScroll', scroll_move);
            }

            function bind_relationship_person_list() {
                $("#RelationshipPersonList .company_list").bind('mousewheel', scroll_move);
                $("#RelationshipPersonList .company_list").bind('DOMMouseScroll', scroll_move);
            }

            function bind_shareholder_list() {
                $("#stockHolderCompany .company_list").bind('mousewheel', scroll_move);
                $("#stockHolderCompany .company_list").bind('DOMMouseScroll', scroll_move);
            }

            function bind_investment_list() {
                $("#investSearchResultDiv .company_list").bind('mousewheel', scroll_move);
                $("#investSearchResultDiv .company_list").bind('DOMMouseScroll', scroll_move);
            }

            function bind_search_company_list() {
                $("#searchResultDiv").bind('mousewheel', scroll_move);
                $("#searchResultDiv").bind('DOMMouseScroll', scroll_move);
            }

            function load_relationship_company_list(result) {
                load_waiting_close();
                ModuleConfig.RelationCompanyModel.RelationshipListShow = true;
                ModuleConfig.RelationCompanyModel.CurrentListPage = result.pageIndex;
                if (result.result.list.length <= 0) {
                    $("#RelationshipBlock #RelationshipCompanyList").empty();
                    $("#RelationshipBlock #RelationshipCompanyList").append("<div style='text-align:center;margin:20px;'>未添加关系企业</div>")
                } else {
                    var list_block = tostring(function () {/*
                        <div id="RelationshipCompanyListBlock">
                            <style>
                                #RelationshipBlock #RelationshipCompanyList #RelationCompanyListPagination{text-align:center;margin:10px 0px;}
                                #RelationshipBlock #RelationshipCompanyList .company_list{
                                    margin: 0px 10px 0px 35px;
                                    overflow: auto;
                                    padding: 0px 0px;
                                    height: 470px;}
                                #RelationshipBlock #RelationshipCompanyList .company{
                                    padding-bottom: 4px;
                                    margin-right: 20px;
                                    border-bottom: 1px dashed #d7d7d7;
                                    padding-right: 60px;}
                                #RelationshipBlock #RelationshipCompanyList .company .blank{width:25px;}
                                #RelationshipBlock #RelationshipCompanyList .company .company_info{
                                    padding:0px 0px;}
                                #RelationshipBlock #RelationshipCompanyList .company .company_name{
                                    text-align: left;
                                    font-size: 16px;
                                    font-weight: 700;
                                    line-height:60px;
                                    color: #333333;
                                    overflow: hidden;
                                    text-overflow: ellipsis;
                                    white-space: nowrap;}
                            </style>
                            <div class="company_list" style="">

                            </div>
                            <div id="RelationCompanyListPagination"></div>
                        </div>
                    */})

                    var block = tostring(function () {/*
                        <div class="company" style="">
                            <div class="inline_div blank">
                            </div><div class="inline_div company_info">
                                <div class="company_name" style=""></div>
                            </div>
                        </div>
                    */})

                    var company_list_block = $("#RelationshipBlock #RelationshipCompanyListBlock");
                    if ($(company_list_block).length <= 0 || result.page.pageIndex <= 1) {
                        $("#RelationshipBlock #RelationshipCompanyList").empty();
                        company_list_block = $(list_block).clone();
                        $("#RelationshipBlock #RelationshipCompanyList").append(company_list_block);
                        load_relation_company_pagination(result.page);
                    }
                    $(company_list_block).find(".company_list").empty();
                    for (var i = 0; i < result.result.list.length; i++) {
                        var company = result.result.list[i];
                        var company_block = $(block).clone();
                        $(company_block).find(".company_name").text(company.companyName);
                        $(company_list_block).find(".company_list").append(company_block);
                    }
                }
                bind_relationship_company_list();
            }

            function load_relation_company_pagination(page) {
                if (page.totalSum > 0) {
                    $("#RelationshipBlock #RelationCompanyListPagination").pagination(
                        page.totalSum,
                        {
                            prev_text: "上一页",
                            next_text: "下一页",
                            num_edge_entries: 3,
                            num_display_entries: 10,
                            callback: init_relationship_list,
                            callback_model: {
                                name: ModuleConfig.CompanyName,
                                monitorId: ModuleConfig.MonitorId,
                                relationModel: ModuleConfig.RelationCompanyModel
                            }
                        }
                    )
                }
            }

            function load_relationship_company_add(result) {
                load_waiting_close();
                $("#RelationshipBlock #RelationshipCompanyAdd").empty();
                $("#RelationshipBlock #RelationshipCompanyAdd").append(result.view);
                $("#RelationshipBlock #RelationshipCompanyAdd").find("div[onclick]").attr("onclick", "");
                $("#RelationshipBlock #RelationshipCompanyAdd").find(".filter_title_block .title").click(click_filter_title);
                $("#RelationshipBlock #RelationshipCompanyAdd").find("#searchBtnId").click(click_search_input);
                ModuleConfig.ShareholderModel.RelationshipListShow = false;
                ModuleConfig.InvestmentModel.RelationshipListShow = false;
                $("#RelationshipBlock #RelationshipCompanyAdd").find('#rsearchInfo').bind('keypress', function (event) {
                    if (event.keyCode == "13") {
                        $("#searchBtnId").click();
                    }
                });
                $("#RelationshipBlock #RelationshipCompanyAdd").find("#stockHolderCompany .company_list .company .small_btn").click(click_add_relation_company);
                load_shareholder_pagination();
                bind_shareholder_list();
            }

            function load_shareholder_list(result) {
                load_waiting_close();
                ModuleConfig.ShareholderModel.RelationshipListShow = true;
                ModuleConfig.ShareholderModel.CurrentListPage = result.page.pageIndex;
                $("#RelationshipBlock #stockHolderCompany").empty();
                $("#RelationshipBlock #stockHolderCompany").append(result.view);
                $("#RelationshipBlock #stockHolderCompany").find(".company_list .company .small_btn").click(click_add_relation_company);
                bind_shareholder_list();
            }

            function load_shareholder_pagination() {
                $("#PageStockHolderCompanyDiv").removeClass("hidden");
                if ($("#totalNumStockHolderCompany").val() > 0) {
                    $("#PageStockHolderCompanyDiv").pagination(
                        $("#totalNumStockHolderCompany").val(), {
                            prev_text: "上一页",
                            next_text: "下一页",
                            num_edge_entries: 3,
                            num_display_entries: 10,
                            callback: init_relationship_list,
                            callback_model: {
                                name: ModuleConfig.CompanyName,
                                monitorId: ModuleConfig.MonitorId,
                                relationModel: ModuleConfig.ShareholderModel
                            }
                        });
                }
            }

            function load_investment_list(result) {
                load_waiting_close();
                $("#RelationshipBlock #investSearchResultDiv").empty();
                $("#RelationshipBlock #investSearchResultDiv").append(result.view);
                $("#RelationshipBlock #investSearchResultDiv").find(".company_list .company .small_btn").click(click_add_relation_company);
                bind_investment_list();
                if (!ModuleConfig.InvestmentModel.RelationshipListShow) {
                    load_investment_pagination();
                }
                ModuleConfig.InvestmentModel.RelationshipListShow = true;
                ModuleConfig.InvestmentModel.CurrentListPage = result.page.pageIndex;
            }

            function load_investment_pagination() {
                $("#PaginationInvestmentCompany").removeClass("hidden");
                if ($("#totalNumInvestmentCompany").val() > 0) {
                    $("#PaginationInvestmentCompany").pagination(
                        $("#totalNumInvestmentCompany").val(), {
                            prev_text: "上一页",
                            next_text: "下一页",
                            num_edge_entries: 3,
                            num_display_entries: 10,
                            callback: init_relationship_list,
                            callback_model: {
                                name: ModuleConfig.CompanyName,
                                monitorId: ModuleConfig.MonitorId,
                                relationModel: ModuleConfig.InvestmentModel
                            }
                        });
                }
            }

            function load_search_company_list(result) {
                load_waiting_close();
                $("#RelationshipBlock #searchResultDiv").empty();
                $("#RelationshipBlock #searchResultDiv").append(result.view);
                $("#RelationshipBlock #searchResultDiv").find(".table4 .company .small_btn").click(click_add_relation_company);
                bind_search_company_list();
                if (!ModuleConfig.SearchCompanyModel.RelationshipListShow) {
                    load_search_company_pagination();
                }
                ModuleConfig.SearchCompanyModel.RelationshipListShow = true;
                ModuleConfig.SearchCompanyModel.CurrentListPage = result.page.pageIndex;
            }

            function load_search_company_pagination() {
                if ($("#totalNum4Search").val() > 0) {
                    $("#Pagination4Search").pagination(
                        $("#totalNum4Search").val(), {
                            prev_text: "上一页",
                            next_text: "下一页",
                            num_edge_entries: 3,
                            num_display_entries: 10,
                            callback: init_relationship_list,
                            callback_model: {
                                name: ModuleConfig.CompanyName,
                                monitorId: ModuleConfig.MonitorId,
                                relationModel: ModuleConfig.SearchCompanyModel,
                                model: {
                                    keyword: $("#rsearchInfo").val().trim()
                                }
                            }
                        });
                }
            }

            function load_relationship_person_list(result) {
                load_waiting_close();
                ModuleConfig.RelationPersonModel.RelationshipListShow = true;
                ModuleConfig.RelationPersonModel.CurrentListPage = result.page.pageIndex;

                if (result.result.list.length <= 0) {
                    $("#RelationshipBlock #RelationshipPersonList").empty();
                    $("#RelationshipBlock #RelationshipPersonList").append("<div style='text-align:center;margin:20px;'>未添加关系自然人</div>")
                } else {
                    var list_block = tostring(function () {/*
                        <div id="RelationshipPersonListBlock">
                            <style>
                                #RelationshipBlock #RelationshipPersonList #RelationPersonListPagination{text-align:center;margin:10px 0px;}
                                #RelationshipBlock #RelationshipPersonList .company_list{
                                    margin: 0px 10px 0px 35px;
                                    overflow: auto;
                                    padding: 0px 0px;
                                    height: 470px;}
                                #RelationshipBlock #RelationshipPersonList .company{
                                    padding-bottom: 4px;
                                    margin-right: 20px;
                                    border-bottom: 1px dashed #d7d7d7;}
                                #RelationshipBlock #RelationshipPersonList .company .blank{width:25px;}
                                #RelationshipBlock #RelationshipPersonList .company .company_info{
                                    padding:0px 0px;}
                                #RelationshipBlock #RelationshipPersonList .company .company_name{
                                    text-align: left;
                                    width:250px;
                                    padding:0px 0px 0px 72px;
                                    font-size: 16px;
                                    font-weight: 700;
                                    line-height:60px;
                                    color: #333333;
                                    overflow: hidden;
                                    text-overflow: ellipsis;
                                    white-space: nowrap;}
                                #RelationshipBlock #RelationshipPersonList .company .province{
                                    color:#666666;}
                                #RelationshipBlock #RelationshipPersonList .company .city{
                                    color:#666666;}
                            </style>
                            <div class="company_list" style="">

                            </div>
                            <div id="RelationPersonListPagination"></div>
                        </div>
                    */})

                    var block = tostring(function () {/*
                        <div class="company" style="">
                            <div class="inline_div blank">
                            </div><div class="inline_div company_info">
                                <div class="inline_div company_name" style=""></div>
                                <div class="inline_div province" style=""></div>
                                <div class="inline_div city" style=""></div>
                            </div>
                        </div>
                    */})

                    var company_list_block = $("#RelationshipBlock #RelationshipPersonListBlock");
                    if ($(company_list_block).length <= 0 || result.page.pageIndex <= 1) {
                        $("#RelationshipBlock #RelationshipPersonList").empty();
                        company_list_block = $(list_block).clone();
                        $("#RelationshipBlock #RelationshipPersonList").append(company_list_block);
                        load_relation_person_pagination(result.page);
                    }
                    $(company_list_block).find(".company_list").empty();
                    for (var i = 0; i < result.result.list.length; i++) {
                        var company = result.result.list[i];
                        var company_block = $(block).clone();
                        $(company_block).find(".company_name").text(company.name);
                        $(company_block).find(".province").text(company.province);
                        $(company_block).find(".city").text(company.city);
                        $(company_list_block).find(".company_list").append(company_block);
                    }
                }
                bind_relationship_person_list();
            }

            function load_relation_person_pagination(page) {
                if (page.totalSum > 0) {
                    $("#RelationshipBlock #RelationPersonListPagination").pagination(
                        page.totalSum,
                        {
                            prev_text: "上一页",
                            next_text: "下一页",
                            num_edge_entries: 3,
                            num_display_entries: 10,
                            callback: init_relationship_list,
                            callback_model: {
                                name: ModuleConfig.CompanyName,
                                monitorId: ModuleConfig.MonitorId,
                                relationModel: ModuleConfig.RelationPersonModel
                            }
                        }
                    )
                }
            }

            function load_relationship_person_add(result) {
                load_waiting_close();
                $("#RelationshipBlock #RelationshipPersonAdd").empty();
                $("#RelationshipBlock #RelationshipPersonAdd").append(result.view);

                $("#RelationshipBlock #RelationshipPersonAdd").find("div[onclick]").attr("onclick", "");
                $("#RelationshipBlock #RelationshipPersonAdd").find(".filter_title_block .title").click(click_filter_person_title);
                $("#RelationshipBlock #RelationshipPersonAdd").find(".filter_option_block .option").click(click_filter_person_option);
                $("#RelationshipBlock #RelationshipPersonAdd").find("#addRealPerson").click(click_add_relation_person);
                $('#pprovince, #pcity').citylist({
                    data: data,
                    id: 'id',
                    children: 'cities',
                    name: 'name',
                    metaTag: 'name'
                });
                $("#option_up_btn").click(function () {
                    $("#RelationshipBlock #RelationshipPersonAdd").find(".filter_option_block .option_list").stop().animate({ scrollTop: "-=" + 35 }, 300)
                })

                $("#option_down_btn").click(function () {
                    $("#RelationshipBlock #RelationshipPersonAdd").find(".filter_option_block .option_list").stop().animate({ scrollTop: "+=" + 35 }, 300)
                })

            }

            function click_relationship_mode_label() {
                var mode = $(this).attr("mode");
                switch (parseInt(mode)) {
                    case 0:
                        $("#RelationshipBlock #RelationshipCompanyList").removeClass("hidden");
                        $("#RelationshipBlock #RelationshipCompanyAdd").addClass("hidden");
                        init_relationship_list(0, null, {
                            name: ModuleConfig.CompanyName,
                            monitorId: ModuleConfig.MonitorId,
                            relationModel: ModuleConfig.RelationCompanyModel
                        });
                        break;
                    case 1:
                        $("#RelationshipBlock #RelationshipCompanyList").addClass("hidden");
                        $("#RelationshipBlock #RelationshipCompanyAdd").removeClass("hidden");
                        init_relationship_add(0, null, {
                            name: ModuleConfig.CompanyName,
                            monitorId: ModuleConfig.MonitorId,
                            relationModel: ModuleConfig.RelationCompanyModel
                        });
                        break;
                    case 2:
                        $("#RelationshipBlock #RelationshipPersonList").removeClass("hidden");
                        $("#RelationshipBlock #RelationshipPersonAdd").addClass("hidden");
                        init_relationship_list(0, null, {
                            name: ModuleConfig.CompanyName,
                            monitorId: ModuleConfig.MonitorId,
                            relationModel: ModuleConfig.RelationPersonModel
                        });
                        break;
                    case 3:
                        $("#RelationshipBlock #RelationshipPersonAdd").removeClass("hidden");
                        $("#RelationshipBlock #RelationshipPersonList").addClass("hidden");
                        init_relationship_add(0, null, {
                            name: ModuleConfig.CompanyName,
                            monitorId: ModuleConfig.MonitorId,
                            relationModel: ModuleConfig.RelationPersonModel
                        });
                        break;
                }
                $(this).parents(".label_list").find(".label").removeClass("selected");
                $(this).addClass("selected");
            }

            function click_filter_title() {
                var _this = this;
                if ($(_this).hasClass("selected")) {
                    return;
                }
                $(_this).parents(".filter_title_block").find(".filter_arr").animate({ left: 18 + 62 * parseInt($(_this).index()) }, 300, function () {
                    switch ($(_this).text().trim()) {
                        case "股东":
                            $("#RelationshipBlock #stockHolderCompany").removeClass("hidden");
                            $("#RelationshipBlock #PageStockHolderCompanyDiv").removeClass("hidden");
                            $("#RelationshipBlock #PaginationInvestmentCompany").addClass("hidden");
                            $("#RelationshipBlock #investSearchResultDiv").addClass('hidden');
                            $("#RelationshipBlock #searchResultDiv").addClass('hidden');
                            init_relationship_list(0, null, {
                                name: ModuleConfig.CompanyName,
                                monitorId: ModuleConfig.MonitorId,
                                relationModel: ModuleConfig.ShareholderModel
                            });
                            break;
                        case "投资":
                            $("#RelationshipBlock #investSearchResultDiv").removeClass('hidden');
                            $("#RelationshipBlock #PaginationInvestmentCompany").removeClass("hidden");
                            $("#RelationshipBlock #PageStockHolderCompanyDiv").addClass("hidden");
                            $("#RelationshipBlock #stockHolderCompany").addClass("hidden");
                            $("#RelationshipBlock #searchResultDiv").addClass('hidden');
                            init_relationship_list(0, null, {
                                name: ModuleConfig.CompanyName,
                                monitorId: ModuleConfig.MonitorId,
                                relationModel: ModuleConfig.InvestmentModel
                            });
                            break;
                    }
                })
                $(_this).parents(".filter_title_block").find(".title").removeClass("selected");
                $(_this).addClass("selected");
            }

            function click_search_input() {
                $("#RelationshipBlock #investSearchResultDiv").addClass('hidden');
                $("#RelationshipBlock #PaginationInvestmentCompany").addClass("hidden");
                $("#RelationshipBlock #PageStockHolderCompanyDiv").addClass("hidden");
                $("#RelationshipBlock #stockHolderCompany").addClass("hidden");
                $("#RelationshipBlock #searchResultDiv").removeClass('hidden');
                $("#RelationshipBlock #tabComDiv").addClass('hidden');
                $("#RelationshipBlock #Pagination4Search").removeClass('hidden');
                ModuleConfig.SearchCompanyModel.RelationshipListShow = false;
                init_relationship_list(0, null, {
                    name: ModuleConfig.CompanyName,
                    monitorId: ModuleConfig.MonitorId,
                    relationModel: ModuleConfig.SearchCompanyModel,
                    model: {
                        keyword: $("#rsearchInfo").val().trim()
                    }
                });
            }

            function click_add_relation_company() {
                if (ModuleData.CurrentCompany != null) {
                    toastr.error("正在添加企业,请耐心等待!");
                    return;
                }
                ModuleData.CurrentCompany = {
                    //name: $(this).parents(".add_btn").attr("enterprisename").trim(),
                    block: this
                }
                Models.Overlaps.RelationShipOverlap.show_change(function () {
                    Models.Overlaps.MessageOverlap.show(ControllerConfig.MessageConfig.RelationCompanyAddMessage.name);
                })
            }

            function click_add_relation_person() {
                if (ModuleData.CurrentCompany != null) {
                    toastr.error("正在添加自然人,请耐心等待!");
                    return;
                }
                Models.Overlaps.RelationShipOverlap.show_change(function () {
                    Models.Overlaps.MessageOverlap.show(ControllerConfig.MessageConfig.RelationPersonAddMessage.name);
                })
            }

            function click_show_relationship_block_event(name, id) {
                if (!id) {
                    //toastr.error("页面错误！");
                    return;
                }
                init_module_config(name, id);
                $("#RelationshipBlock .label_list .selected").click();
                Models.Overlaps.RelationShipOverlap.show("#RelationshipBlock");
            }

            function click_filter_person_title() {
                var _this = this;
                if ($(_this).hasClass("selected")) {
                    return;
                }
                $(_this).parents(".filter_title_block").find(".filter_arr").animate({ left: 48 + (88 - 13 * (parseInt($(_this).index()) - 1)) * parseInt($(_this).index()) }, 300, function () {
                    $(_this).parents(".filter_block").find(".filter_option_block .option_list").addClass("hidden");
                    $(_this).parents(".filter_block").find(".filter_option_block .option_list:eq({0})".format($(_this).index())).removeClass("hidden");
                })
                $(_this).parents(".filter_title_block").find(".title").removeClass("selected");
                $(_this).addClass("selected");
            }

            function click_filter_person_option() {
                if ($(this).hasClass("added")) {
                    return;
                }
                var value = $(this).text().trim();
                $("#RelationshipBlock #RelationshipPersonAdd").find(".filter_option_block .option").removeClass("selected");
                $(this).addClass("selected");
                $("#ppersonName").val(value);
                ModuleData.CurrentPerson = {
                    block: this
                };
            }

            function click_add_relation_person() {
                if ($("#ppersonName").val() == '') {
                    toastr.error("请输入姓名!");
                    return;
                }
                if ($("#pprovince").val() == '请选择省') {
                    toastr.error("请选择常住地！");
                    return;
                }

                if ($("#pcity").val() == '请选择市') {
                    toastr.error("请选择常住地！");
                    return;
                }

                if ($("#pidNumber").val().trim() != "") {
                    if (isCardNo($("#pidNumber").val().trim()) === false) {
                        toastr.error("请输入正确的身份证！");
                        return;
                    }
                }
                Models.Overlaps.RelationShipOverlap.show_change(function () {
                    Models.Overlaps.MessageOverlap.show(ControllerConfig.MessageConfig.RelationPersonAddMessage.name);
                })
            }

            function message_relation_company_add(result) {
                if (result) {
                    load_waiting_show("#RelationshipBlock #RelationshipCompanyAdd");
                    var model = {
                        monitorId: ModuleConfig.MonitorId,
                        companyName: $(ModuleData.CurrentCompany.block).parents(".add_btn").attr("enterprisename").trim(),
                    }
                    repository.post_relationship_company(model, post_relation_company_add);
                }
                Models.Overlaps.RelationShipOverlap.show("#RelationshipBlock");
            }

            function message_relation_person_add(result) {
                if (result) {
                    load_waiting_show("#RelationshipBlock #RelationshipPersonAdd");
                    var model = {
                        name: encodeURIComponent($("#ppersonName").val()),
                        province: encodeURIComponent($("#pprovince").val()),
                        city: encodeURIComponent($("#pcity").val()),
                        idNumber: $("#pidNumber").val().trim(),
                        companyName: encodeURIComponent($("#hcompanyName").val()),
                        monitorId: ModuleConfig.MonitorId,
                        companyName: ModuleConfig.CompanyName,
                    }
                    repository.post_relationship_person(model, post_relation_person_add);
                }
                Models.Overlaps.RelationShipOverlap.show("#RelationshipBlock");
            }

            function message_company_used_name_message() {
                Models.Overlaps.RelationShipOverlap.show("#RelationshipBlock");
            }

            function post_relation_company_add(result) {
                load_waiting_close();
                if (result.resultCode == "0") {
                    $(ModuleData.CurrentCompany.block).parents(".add_btn").empty().append("<div class='inline_div' style='width:46px;height:21px;'>已添加</div>")
                    $(ModuleData.CurrentCompany.block).parents(".add_btn").attr("existFlag", true);
                    toastr.success(result.resultMsg);
                } else if (result.resultCode == "2") {
                    regMessage = /^该企业已更名为：(.+?)，(.+)$/g
                    var r = regMessage.exec(result.resultMsg)
                    var companyName = r[1];
                    var message = r[2];
                    show_used_message(companyName, message);
                    if (message == "更名后企业加入成功！监控详情将于次日展示") {
                       // $(ModuleData.CurrentCompany.block).parents(".add_btn").empty().append("<div class='inline_div' style='width:46px;height:21px;'>已添加</div>")
                        //$(ModuleData.CurrentCompany.block).parents(".add_btn").attr("existFlag", true);
                    }
                }
                else {
                    toastr.error(result.resultMsg);
                }
                ModuleData.CurrentCompany = null;
            }

            function post_relation_person_add(result) {
                load_waiting_close();
                if (result.resultCode == "0") {
                    //$(ModuleData.CurrentPerson.block).addClass("added").removeClass("selected");
                    toastr.success(result.resultMsg);
                    ModuleConfig.RelationPersonModel.RelationshipListShow = false;
                    ModuleData.CurrentPerson = null;
                } else {
                    toastr.error(result.resultMsg);
                }

            }

            function show_used_message(name, message) {
                Models.Overlaps.RelationShipOverlap.show_change(function () {
                    Models.Overlaps.MessageInfoOverlap.show(ControllerConfig.MessageConfig.CompanyUsedNameMessage.name, message, "content_info_type_2");
                    $("#MessageInfoOverlap .company_message_used_name").text(name);
                })
            }

            return {
                init_relationship_overlap: init_relationship_overlap,
                init_relationship_block: init_relationship_block,
                bind_relationship_block_events: bind_relationship_block_events,
                click_show_relationship_block_event: click_show_relationship_block_event,
                message_relation_company_add: message_relation_company_add,
                message_relation_person_add: message_relation_person_add,
                message_company_used_name_message: message_company_used_name_message
            }
        }()
    }

    var ControllerConfig = {
        RelationShipMode: 0b00,
        MessageConfig: {
            RelationPersonAddMessage: {
                name: "RelationPersonAddMessage",
                message: "是否确认加入关系人？",
                callback: Modules.RelationShip.message_relation_person_add
            },
            RelationCompanyAddMessage: {
                name: "RelationCompanyAddMessage",
                message: "是否确认加入关系企业？",
                callback: Modules.RelationShip.message_relation_company_add
            },
            CompanyUsedNameMessage: {
                name: "CompanyUsedNameMessage",
                message: "该企业已更名为：<span class='company_message_used_name' style='font-size:14px;color:#333333;font-weight:700;'>曾用名</span>",
                callback: Modules.RelationShip.message_company_used_name_message
            }
        }
    }

    var Loader = {
        load_models: function () {
            init_overlap();
            return this;

            function init_overlap() {
                var message = ControllerConfig.MessageConfig;
                Models.Overlaps.RelationShipOverlap = Modules.RelationShip.init_relationship_overlap();
                Models.Overlaps.MessageOverlap = new MessageOverlap();
                Models.Overlaps.MessageInfoOverlap = new MessageInfoOverlap();
                Models.Overlaps.MessageOverlap.push(message.RelationCompanyAddMessage.name, message.RelationCompanyAddMessage.message, message.RelationCompanyAddMessage.callback);
                Models.Overlaps.MessageOverlap.push(message.RelationPersonAddMessage.name, message.RelationPersonAddMessage.message, message.RelationPersonAddMessage.callback);
                Models.Overlaps.MessageInfoOverlap.push(message.CompanyUsedNameMessage.name, message.CompanyUsedNameMessage.message, message.CompanyUsedNameMessage.callback)
            }
        },
        load_page: function () {
            Modules.RelationShip.init_relationship_block();
            return this;
        },
    }

    var Binder = {
        bind_overlap_events: function () {
            Modules.RelationShip.bind_relationship_block_events();

            return this;
        },
    }

    var CompanyRelationShip = function (config) {
        $.extend(ControllerConfig, config || {});
        Loader
            .load_models()
            .load_page();

        Binder
            .bind_overlap_events();
    }
    CompanyRelationShip.prototype = {
        show: function (companyName, monitorId) {
            Modules.RelationShip.click_show_relationship_block_event(companyName, monitorId);
        }
    }
    CompanyRelationShip.prototype.constructor = CompanyRelationShip;
    return CompanyRelationShip;
}();