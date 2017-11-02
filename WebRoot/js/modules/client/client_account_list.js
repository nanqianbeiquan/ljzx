var UserAccountRepository = function () {
    var RepositoryMocks = {
        UserAccountMock: {
            UserAccountsModel: {
                total: 200,
                remain: 20,
                accounts: [
                    {
                        name: "SRD",
                        type: 0,
                        attach: [
                            {
                                name: "SRDSRDSRDSRD",
                                type: 2,
                                attach: [],
                                accounts: []
                            }
                        ],
                        accounts: [
                            {
                                name: "SRD_CP",
                                type: 0,
                                attach: [],
                                accounts: [
                                    {
                                        name: "SRD_CPZ1SRD_CPZCP_SRD",
                                        type: 1,
                                        attach: [],
                                        accounts: []
                                    }
                                ]
                            },
                            {
                                name: "SRD_CPZ2",
                                type: 0,
                                attach: [
                                    {
                                        name: "SRDSRDSRDSRD",
                                        type: 2,
                                        attach: [],
                                        accounts: []
                                    }
                                ],
                                accounts: [
                                    {
                                        name: "SRD_CPZ1SRD_CPZCP_SRD",
                                        type: 1,
                                        attach: [],
                                        accounts: []
                                    },
                                    {
                                        name: "SRD_CPZ1SRD_CPZCP_SRD",
                                        type: 1,
                                        attach: [
                                            {
                                                name: "SRD_CPZ1SRD_CPZCP_SRD",
                                                type: 1,
                                                attach: [],
                                                accounts: []
                                            },
                                            {
                                                name: "SRD_CPZ1SRD_CPZCP_SRD",
                                                type: 1,
                                                attach: [],
                                                accounts: []
                                            }
                                        ],
                                        accounts: []
                                    }
                                ]
                            },
                            {
                                name: "SRD_CP",
                                type: 1,
                                attach: [
                                    {
                                        name: "SRD_CPZ1SRD_CPZCP_SRD",
                                        type: 1,
                                        attach: [],
                                        accounts: []
                                    }
                                ],
                                accounts: []
                            },
                            {
                                name: "SRD_CP",
                                type: 1,
                                attach: [],
                                accounts: []
                            },
                        ]
                    }
                ]
            },
            UserMonitorNum: {
                all_total: 0,
                total: 190,
                remain: 100,
                accounts: [
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                    {
                        name: 'SRD',
                        num: 200,
                        per: "10%"
                    },
                ]
            },
            UserAccountList: {
                page: {
                    pageIndex: 1,
                    pageSize: 6,
                    totalPage: 10,
                    totalCount: 60
                },
                list: [
                    {
                        index: 1,
                        accountId: "",
                        targetAccount: "SRD",
                        name: "SRD",
                        type: 0,
                        amount: 200,
                        phone: "13761550997",
                        email: "yangxufeng2007@126.com"
                    },
                    {
                        index: 2,
                        accountId: "",
                        targetAccount: "SRD",
                        name: "SRD",
                        type: 0,
                        amount: 200,
                        phone: "13761550997",
                        email: "yangxufeng2007@126.com"
                    },
                    {
                        index: 3,
                        accountId: "",
                        targetAccount: "SRD",
                        name: "SRD",
                        type: 0,
                        amount: 200,
                        phone: "13761550997",
                        email: "yangxufeng2007@126.com"
                    },
                    {
                        index: 4,
                        accountId: "",
                        targetAccount: "SRD",
                        name: "SRD",
                        type: 0,
                        amount: 200,
                        phone: "13761550997",
                        email: "yangxufeng2007@126.com"
                    }, {
                        index: 5,
                        accountId: "",
                        targetAccount: "SRD",
                        name: "SRD",
                        type: 0,
                        amount: 200,
                        phone: "13761550997",
                        email: "yangxufeng2007@126.com"
                    },
                    {
                        index: 6,
                        accountId: "",
                        targetAccount: "SRD",
                        name: "SRD",
                        type: 0,
                        amount: 200,
                        phone: "13761550997",
                        email: "yangxufeng2007@126.com"
                    }
                ]
            }
        }
    }
    var RepositoryModel = {
        Locker: {
            lock_get_user_account_data: false,
            lock_get_user_monitor_data: false,
            lock_get_account_list_data: false,
            lock_post_create_account: false,
            lock_post_edit_account: false,
            lock_post_delete_account: false,
            lock_post_disable_account: false,
        }
    }

    var Modules = {
        UserAccountModule: function () {
            function get_user_account_data(model, callback) {
                var data = {}
                applyAjax("{0}/clientAccount/getClientAccountTree".format(ctx), data, function (result) {                	
                    if (IsFunction(callback)) {
                        if (result.resultCode == "0") {
                            var resultData = {
                                total: 10,
                                remain: 10,
                                accounts: result.resultData.accounts
                            };
                            callback(resultData);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            function get_user_monitor_data(model, callback) {
                var color_range = [
                    { r: 12, g: 158, b: 248 },
                    { r: 114, g: 203, b: 64 },
                    { r: 255, g: 182, b: 56 },
                    { r: 224, g: 255, b: 255 }
                ]

                var data = {}
                applyAjax("{0}/clientAccount/getAccountQuotasByParent".format(ctx), data, function (result) {
                    if (IsFunction(callback)) {
                        if (result.resultCode == "0") {
                            var list = [];
                            var total = 0;
                            if (result.resultData.accountQuotaList.length > 0) {
                                for (var i = 0; i < result.resultData.accountQuotaList.length; i++) {
                                    var model = result.resultData.accountQuotaList[i];
                                    total += model.monitorNum;
                                    list.push({
                                        name: model.accountName,
                                        num: model.monitorNum,
                                        per: "",
                                        color: "",
                                    })
                                }
                            }
                            list = list.sort(compare_num);
                            for (var i = 0; i < list.length; i++) {
                                var color = get_range_color(i, list.length, parseInt(list.length / 3) || list.length)
                                list[i].color = "rgb({0},{1},{2}".format(color.r, color.g, color.b);
                                list[i].per = parseInt((list[i].num * 100) / total) + "%";
                            }
                            var resultData = {
                                total_num: result.resultData.childNum,
                                remain_num: result.resultData.remainingChildNum,
                                used_num: result.resultData.usedChildNum,
                                total_monitor: result.resultData.monitorNum,
                                remain_monitor: result.resultData.remainingMonitorNum,
                                used_monitor: total,
                                accounts: list
                            }
                            callback(resultData);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })

                function get_color(color_1, color_2, per) {
                    var r = color_1.r + (color_2.r - color_1.r) * per;
                    var g = color_1.g + (color_2.g - color_1.g) * per;
                    var b = color_1.b + (color_2.b - color_1.b) * per;
                    return { r: r.toFixed(0), g: g.toFixed(0), b: b.toFixed(0) }
                }

                function get_range_color(i, max, range) {
                    var level = parseInt(i / range) + 1;

                    if (level >= color_range.length) {
                        level = color_range.length - 1;
                        i = i - range * (level - 1);
                        range = range + max - range * level;
                    }
                    var index = i % range;
                    var per = index / range;
                    return get_color(color_range[level - 1], color_range[level], per);
                }

                function compare_num(x, y) {
                    if (x.num < y.num) {
                        return 1;
                    } else if (x.num > y.num) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            }
            function get_account_list_data(model, callback) {
                var data = {
                    accountId: model.accountId
                }
                applyAjax("{0}/clientAccount/getClientAccountList".format(ctx), data, function (result) {
                    if (IsFunction(callback)) {
                        if (result.resultCode == "0") {
                            var resultData = {
                                list: result.resultData
                            }
                            callback(resultData);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            function get_account_total_data(model, callback) {
                var data = {}
                applyAjax("{0}/clientAccount/getAccountQuotasByParent".format(ctx), data, function (result) {
                    if (IsFunction(callback)) {
                        if (result.resultCode == "0") {
                            var resultData = {

                            }
                            callback(resultData);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            function get_account_detail_data(model, callback) {
                var data = {
                    accountId: model.accountId
                }
                applyAjax("{0}/clientAccount/getClientAccount ".format(ctx), data, function (result) {
                    if (IsFunction(callback)) {
                        callback(result);
                    }
                })
            }
            function post_create_account(model, callback) {
                if (RepositoryModel.Locker.lock_post_create_account) {
                    toastr.error("正在提交数据！");
                    return;
                }
                RepositoryModel.Locker.lock_post_create_account = true;
                var data = {
                    parentId: model.parentId,
                    name: model.name,
                    accountName: model.accountName,
                    password: model.password,
                    type: model.type,
                    mobilePhone: model.mobilePhone,
                    email: model.email,
                    monitorNum: model.monitorNum,
                    childNum: model.childNum,
                    canCreateChild: model.canCreateChild,
                    remark: ""
                }
                applyAjax("{0}/clientAccount/addClientAccount?companyId=123456".format(ctx), data, function (result) {
                    RepositoryModel.Locker.lock_post_create_account = false;
                    if (IsFunction(callback)) {
                        console.log(result);
                        if (result.resultCode == "0") {
                            var resultData = {

                            }
                            callback(resultData);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            function post_edit_account(model, callback) {
                if (RepositoryModel.Locker.lock_post_edit_account) {
                    toastr.error("正在提交数据！");
                    return;
                }
                RepositoryModel.Locker.lock_post_edit_account = true;
                var data = {
                    accountId: model.accountId,
                    parentId: model.parentId,
                    name: model.name,
                    accountName: model.accountName,
                    password: model.password,
                    type: model.type,
                    mobilePhone: model.mobilePhone,
                    email: model.email,
                    monitorNum: model.monitorNum,
                    childNum: model.childNum,
                    canCreateChild: model.canCreateChild,
                    remark: ""
                }
                applyAjax("{0}/clientAccount/editClientAccount?companyId=123456".format(ctx), data, function (result) {
                    RepositoryModel.Locker.lock_post_edit_account = false;
                    if (IsFunction(callback)) {
                        if (result.resultCode == "0") {
                            toastr.success(result.resultMsg);
                            callback(model);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            function post_delete_account(model, callback) {
                var data = {
                    accountId: model.accountId
                }
                applyAjax("{0}/clientAccount/deleteClientAccount".format(ctx), data, function (result) {
                    if (IsFunction(callback)) {
                        console.log(result);
                        if (result.resultCode == "0") {
                            callback(model);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            function post_disable_account(model, callback) {
                var data = {
                    accountId: model.accountId
                }
                applyAjax("{0}/clientAccount/disableClientAccount".format(ctx), data, function (result) {
                    if (IsFunction(callback)) {
                        console.log(result);
                        if (result.resultCode == "0") {
                            callback(model);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            function post_enable_account(model, callback) {
                var data = {
                    accountId: model.accountId
                }
                applyAjax("{0}/clientAccount/enableClientAccount ".format(ctx), data, function (result) {
                    if (IsFunction(callback)) {
                        console.log(result);
                        if (result.resultCode == "0") {
                            callback(model);
                        }
                        else {
                            toastr.warning(result.resultMsg);
                        }
                    }
                })
            }
            return {
                get_user_account_data: get_user_account_data,
                get_user_monitor_data: get_user_monitor_data,
                get_account_total_data: get_account_total_data,
                get_account_list_data: get_account_list_data,
                get_account_detail_data: get_account_detail_data,
                post_create_account: post_create_account,
                post_edit_account: post_edit_account,
                post_delete_account: post_delete_account,
                post_disable_account: post_disable_account,
                post_enable_account: post_enable_account

            }
        }()
    }
    var UserAccountRepository = function () {

    }
    UserAccountRepository.prototype = {
        get_user_account_data: function (model, callback) {
            Modules.UserAccountModule.get_user_account_data(model, callback);
        },
        get_user_montior_data: function (model, callback) {
            Modules.UserAccountModule.get_user_monitor_data(model, callback);
        },
        get_account_total_data: function (model, callback) {
            Modules.UserAccountModule.get_account_total_data(model, callback);
        },
        get_account_list_data: function (model, callback) {
            Modules.UserAccountModule.get_account_list_data(model, callback);
        },
        get_account_detail_data: function (model, callback) {
            Modules.UserAccountModule.get_account_detail_data(model, callback);
        },
        post_create_account: function (model, callback) {
            Modules.UserAccountModule.post_create_account(model, callback);
        },
        post_edit_account: function (model, callback) {
            Modules.UserAccountModule.post_edit_account(model, callback);
        },
        post_delete_account: function (model, callback) {
            Modules.UserAccountModule.post_delete_account(model, callback);
        },
        post_disable_account: function (model, callback) {
            Modules.UserAccountModule.post_disable_account(model, callback);
        },
        post_enable_account: function (model, callback) {
            Modules.UserAccountModule.post_enable_account(model, callback);
        }
    }
    UserAccountRepository.prototype.constructor = UserAccountRepository;
    return UserAccountRepository;
}();

var UserAccountChart = function () {
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
    function echart_set(id, option, event) {
        var myChart = echarts.init(document.getElementById(id));
        myChart.setOption(option);
        if ("function" == typeof (event)) {
            event(myChart);
        }
    }
    var Modules = {
        UserAccountModule: function () {
            var chart_option = {
                color: ["#72cb40",
                    "#ffb638",
                    "#0c9ef8",
                    "#f32c51",
                    "#705ced",
                    "#ff6b1a",
                    "#f3e501"],
                series: [
                    {
                        type: 'pie',
                        radius: ['70%', '75%'],
                        avoidLabelOverlap: false,
                        silent: true,
                        label: {
                            normal: {
                                show: false,
                                position: 'center'
                            },
                            emphasis: {
                                show: true,
                                textStyle: {
                                    fontSize: '30',
                                    fontWeight: 'bold'
                                }
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
                                    normal: { color: "#d5dce1" }
                                }
                            }
                        ]
                    }
                ]
            }

            function get_user_account_option(data) {
                var option = clone(chart_option);

                if (data.length > 0) {
                    var list = [];
                    var index = 1;
                    for (var i = 0; i < data.length; i++) {
                        var model = data[i];
                        list.push({
                            value: model.num,
                            name: i,
                            itemStyle: {
                                normal: { color: model.color }
                            }
                        })
                    }
                    option.series[0].data = list;
                }
                return option;
            }
            function user_account_pie(id, data) {
                option = get_user_account_option(data);
                echart_set(id, option);
            }
            return {
                user_account_pie: user_account_pie
            }
        }()
    }
    var UserAccountChart = function () {

    }
    UserAccountChart.prototype = {
        user_account_pie: function (id, data) {
            Modules.UserAccountModule.user_account_pie(id, data);
        }
    }
    UserAccountChart.prototype.constructor = UserAccountChart;
    return UserAccountChart;
}();

var UserAccountController = function () {
    var repository = new UserAccountRepository();
    var charts = new UserAccountChart();



    var Modules = {
        UserAccountModule: function () {
            var AccountModel = {
                AccountId: null,//当前页面账户ID
                CurrentTree: null,//缓存树
                CurrentList: null,//缓存列表
                CurrentSelectAccount: null,//当前选择的子账号
                CurrentOverlapAccountId: null,//当前弹出框修改账号
                CurrentTableIndex: null,//当前选择禁用/启用的table的序号
                Loader: {
                    load: new LoadWaiting(),
                    count: 0,
                }
            }
            /*======加载模块相关======*/
            function load_module_waiting() {
                if (AccountModel.Loader.count == 0) {
                    AccountModel.Loader.load.show("#AccountConfig");
                }
                AccountModel.Loader.count++;
            }
            function load_module_over() {
                AccountModel.Loader.count--;
                if (AccountModel.Loader.count == 0) {
                    AccountModel.Loader.load.close();
                }
            }
            /*======账户总览相关======*/
            function get_user_monitor_data_callback(data) {           	
                //charts.user_account_pie("UserAccountPie", data.accounts);
                init_user_monitor_list(data);
                init_user_account_info(data);               
                load_module_over();
                
                function init_user_monitor_list(data) {
                    $("#CurrentAccountSub .total_monitor_num .monitor_num").text(data.used_monitor);
                    $("#CurrentAccountSub .monitor_detail_block").empty();
                    for (var i = 0; i < data.accounts.length; i++) {
                        var model = data.accounts[i];
                        var block;
                        if (i % 5 == 0) {
                            block = $("<div class='inline_div monitor_account_percent_block'></div>");
                            $("#CurrentAccountSub .monitor_detail_block").append(block);
                        }
                        var account = $("<div class='monitor_account_percent'></div>");
                        $(account).append("<span class='monitor_color' style='background:{0}'></span>".format(model.color));
                        $(account).append("<span class='monitor_percent'>{0}</span>".format(model.per));
                        $(account).append("<span class='monitor_account' title='{0}'>{0}</span>".format(model.name));
                        $(block).append(account);
                    }
                }

                function init_user_account_info(data) {
                    $("#MonitorTotalNum").text(data.total_monitor);
                    $("#MonitorRemainingNum").text(data.remain_monitor);
                    $("#MonitorUsedNum").text(data.UsedNum);
                    $("#DepartmentBlock .total_account_num .account_num").text(data.total_num);
                    $("#DepartmentBlock .inline_div_block .remaining_account_num .account_num").text(data.remain_num);
                    $("#DepartmentBlock .used_account_num .account_num").text(data.used_num);
                    
                    
                }
            }
            function load_account_pie() {
                load_module_waiting();
                repository.get_user_montior_data({}, get_user_monitor_data_callback);
            }
            /*======账户树相关======*/
            function get_user_account_data_callback(data) {
                $("#UserAccountTree").empty();
                var tree = new TreeViewComponent("#UserAccountTree", data.accounts, 3);
                tree.bind_child_node_click(click_child_node);

                AccountModel.CurrentTree = data.accounts;
                if (!AccountModel.CurrentSelectAccount) {
                    //AccountModel.CurrentSelectAccount = data.accounts[0];
                    $("#UserAccountTree .root_account").click();
                } else {
                    load_account_list();
                }
                if (!AccountModel.AccountId) {
                    AccountModel.AccountId = data.accounts[0].id;
                }

                console.log(AccountModel.AccountId);
                //load_account_list();

                load_module_over();

                function click_child_node(_this) {               	
                    var id = $(_this).attr("accountId");
                    
                    AccountModel.CurrentSelectAccount = get_tree_node(AccountModel.CurrentTree, id);
                    //alert(AccountModel.CurrentTree[0]);
                    $("#CurrentAccountSub .current_selected_account").text("({0})".format(AccountModel.CurrentSelectAccount.name))
                    load_account_list();
                }

                function get_tree_node(accounts, id) {
                    var result = null;
                    for (var i = 0; i < accounts.length; i++) {
                        if (accounts[i].id == id) {
                            result = accounts[i];
                            return result;
                        }
                        if (accounts[i].accounts.length > 0) {
                            result = get_tree_node(accounts[i].accounts, id);
                            if (result) {
                                return result;
                            }
                        }
                    }
                    return result;
                }
            }
            function load_account_tree() {
                load_module_waiting();
                repository.get_user_account_data({}, get_user_account_data_callback);
            }
            /*======账户列表相关======*/
            function show_table_page_info(page) {
                $("#Pagination4Blacklist").pagination(page.countTotal, {
                    prev_text: "上一页",
                    next_text: "下一页",
                    num_edge_entries: 3,
                    items_per_page: page.pageCount,
                    callback: load_account_list
                });
            }
            function init_account_list(list) {
                $("#CurrentAccountSub table tbody").empty();
                if (list.length > 0) {
                    for (var i = 0; i < list.length; i++) {
                        var row = $("<tr></tr>");
                        $(row).append("<td>{0}</td>".format(i + 1));
                        $(row).append("<td>{0}</td>".format(list[i].accountName));
                        $(row).append("<td>{0}</td>".format(get_account_roll(list[i].type)));
                        $(row).append("<td>{1}/{0}</td>".format(list[i].monitorNum,list[i].usedMonitorNum));
                        $(row).append("<td>{0}</td>".format(list[i].mobilePhone));
                        $(row).append("<td>{0}</td>".format(list[i].email));
                        if(AccountModel.CurrentSelectAccount.name==list[i].accountName){
                        	$(row).append("<td class='inline_div_block'></td>")
                        }
                        else{
                            $(row).append("<td class='inline_div_block' index='{0}'><div class='inline_div change'>修改</div><div class='inline_div delete'>删除</div><div class='inline_div disabled' val='{1}'>{2}</div></td>".format(i, list[i].status == "0" ? "1" : "0", list[i].status == "0" ? "禁用" : "启用"));
                        }
                        $("#CurrentAccountSub table tbody").append(row);
                    }
                } else {
                    var row = $("<tr><td colspan='7' style='height:50px;'>请新增子账号！</td></tr>");
                    $("#CurrentAccountSub table tbody").append(row);

                }
                $("#CurrentAccountSub table tbody .change").click(change_sub_account_info_click);
                $("#CurrentAccountSub table tbody .delete").click(delete_sub_account_info_click);
                $("#CurrentAccountSub table tbody .disabled").click(disabled_sub_account_info_click);

                function get_account_roll(type) {
                    switch (parseInt(type)) {
                        case 1:
                            return "母账号";
                        case 2:
                            return "子账号";
                        case 3:
                            return "附属账号";
                        default:
                            return "";
                    }
                }
                function change_sub_account_info_click() {
                    var index = $(this).parent().attr("index");
                    var account = AccountModel.CurrentList[index];
                    change_account_overlap_mode(account);

                    AccountModel.CurrentOverlapAccountId = account.accountId;

                    var model = {
                        accountId: account.accountId
                    }
                    repository.get_account_detail_data(model, get_account_detail_data_callback);
                    
                    var max_monitor = set_account_num_max(
                            AccountModel.CurrentSelectAccount ? AccountModel.CurrentSelectAccount.remainingMonitorNum || 0 : 0,
                            model ? model.monitorNum : 0);
                    var max_num = set_account_num_max(
                            AccountModel.CurrentSelectAccount ? AccountModel.CurrentSelectAccount.remainingChildNum || 0 : 0,
                            model ? model.childNum : 0);                  
                    i_monitor=$("#UserAccountOverlap .account_amount").val()
                    i_num=$("#UserAccountOverlap .account_num").val()
                    
                    all_monitor=parseInt(max_monitor)+parseInt(i_monitor);
                    all_num=parseInt(max_num)+parseInt(i_num);                    
                    //alert(account.monitorNum)
                    //alert(max_monitor)
                    
                    //alert(typeof(all_num))
                    //$("#UserAccountOverlap .account_amount").val(111111);
                    //$("#UserAccountOverlap .account_num").val(all_num);
                    //alert(222)
                    
                    Models.OverlapList.AccountBlockOverlap.show("#UserAccountOverlap");

                    function get_account_detail_data_callback(result) {
                        change_account_overlap_mode(result); 
                        $("#UserAccountOverlap .account_amount").val(all_monitor);
                        $("#UserAccountOverlap .account_num").val(all_num);
                    }
                }
                function delete_sub_account_info_click() {
                    var index = $(this).parent().attr("index");
                    var account = AccountModel.CurrentList[index];
                    if (parseInt(account.type) == 1) {
                        //如果为母账户则不能删除
                        Models.OverlapList.MessageOverlap.show(Models.MessageConfig.DeleteAccountFailedMessage.name);
                        return;
                    }
                    AccountModel.CurrentOverlapAccountId = account.accountId;
                    Models.OverlapList.MessageOverlap.show(Models.MessageConfig.DeleteAccountMessage.name);
                }
                function disabled_sub_account_info_click() {
                    var index = $(this).parent().attr("index");

                    var account = AccountModel.CurrentList[index];
                    var disabled = $(this).attr("val");
                    AccountModel.CurrentOverlapAccountId = account.accountId;
                    AccountModel.CurrentTableIndex = index;
                    if (disabled == "1") {
                        Models.OverlapList.MessageOverlap.show(Models.MessageConfig.DisableAccountMessage.name);
                    } else {
                        Models.OverlapList.MessageOverlap.show(Models.MessageConfig.EnableAccountMessage.name);
                    }
                }

            }
            function get_account_list_data_callback(data) {
                AccountModel.CurrentList = data.list;
                init_account_list(data.list);
                load_module_over();
                console.log(data);
            }
            function load_account_list(page) {
                load_module_over();
                var model = {
                    accountId: AccountModel.CurrentSelectAccount.id
                }
                repository.get_account_list_data(model, get_account_list_data_callback);
            }
            /*===子账号新增修改弹出框相关===*/
            function clean_account_overlap() {
                $("#UserAccountOverlap .account_name").val("");
                $("#UserAccountOverlap .account_type").val("");
                $("#UserAccountOverlap .account_type").trigger("change");
                $("#UserAccountOverlap .account_phone").val("");
                $("#UserAccountOverlap .account_email").val("");
                $("#UserAccountOverlap .account_password").val("");
                $("#UserAccountOverlap .account_repassword").val("");
                $("#UserAccountOverlap .account_create_right").prop("checked", true);
                $("#UserAccountOverlap .account_amount").val("");
                $("#UserAccountOverlap .account_num").val("");
                $("#UserAccountOverlap .account_remarks").val("");
                AccountModel.CurrentOverlapAccountId = null;
            }
            function create_account_overlap() {
                return new overlap({
                    mask: {
                        show: true
                    },
                    scroller: {
                        lock: true
                    },
                    position: {
                        type: "fixed",
                        height: "center",
                        width: "center",
                    },
                    close: {
                        show: true,
                        type: "close_2",
                        callBack: function () {
                            clean_account_overlap();
                        }
                    },
                    content: {
                        style: {
                            width: "633px",
                            height: "558px",
                            border: "16px solid rgba(0,0,0,0.15)"
                        }
                    }
                });
            }
            function change_account_overlap_mode(model) {
                if (model) {
                    $("#UserAccountOverlap .account_overlap_title").text("修改账户");
                    $("#UserAccountOverlap .account_name").val(model.accountName);
                    $("#UserAccountOverlap .account_type").val(parseInt(model.type));
                    $("#UserAccountOverlap .account_type").trigger("change");
                    $("#UserAccountOverlap .account_phone").val(model.mobilePhone);
                    $("#UserAccountOverlap .account_email").val(model.email);
                    $("#UserAccountOverlap .account_password").val("11111111");
                    $("#UserAccountOverlap .account_repassword").val("11111111");
                    $("#UserAccountOverlap .account_create_right").prop("checked", model.canCreateChild == "1" ? true : false);
                    $("#UserAccountOverlap .account_amount").val(model.monitorNum);                   
                    $("#UserAccountOverlap .account_num").val(model.childNum);
                    $("#UserAccountOverlap .account_remarks").val(model.name);
                    $("#UserAccountOverlap .account_name").attr("disabled", "disabled");
                    $("#UserAccountOverlap .account_type").attr("disabled", "disabled");
                    $("#UserAccountOverlap .account_password").attr("disabled", "disabled");
                    $("#UserAccountOverlap .account_repassword").attr("disabled", "disabled");
                    //$("#UserAccountOverlap .account_create_right").attr("disabled", "disabled");
                } else {
                    $("#UserAccountOverlap .account_overlap_title").text("新增账户");

                    $("#UserAccountOverlap .account_name").removeAttr("disabled");
                    $("#UserAccountOverlap .account_type").removeAttr("disabled");
                    $("#UserAccountOverlap .account_password").removeAttr("disabled");
                    $("#UserAccountOverlap .account_repassword").removeAttr("disabled");
                    
                    if (AccountModel.CurrentSelectAccount.type === "1") {
                    	$("#UserAccountOverlap .account_type").val("2");
                    } else {
                    	$("#UserAccountOverlap .account_type").val("3");
                    }
                    $("#UserAccountOverlap .account_type").change();
                }
                $("#UserAccountOverlap .parent_name").text(AccountModel.CurrentSelectAccount.name);
                var max_monitor = set_account_num_max(
                    AccountModel.CurrentSelectAccount ? AccountModel.CurrentSelectAccount.remainingMonitorNum || 0 : 0,
                    model ? model.monitorNum : 0);
                $("#UserAccountOverlap .account_amount").attr("placeholder", "可设置的账户额度{0}个".format(max_monitor)).attr("max", max_monitor);
                var max_num = set_account_num_max(
                    AccountModel.CurrentSelectAccount ? AccountModel.CurrentSelectAccount.remainingChildNum || 0 : 0,
                    model ? model.childNum : 0)
                if(max_num==0){
                	$("#UserAccountOverlap .account_num").attr("placeholder", "可设置的账户数{0}个".format(max_num)).attr("max", max_num);
                }else{
                	$("#UserAccountOverlap .account_num").attr("placeholder", "可设置的账户数{0}个".format(max_num-1)).attr("max", max_num);
                }                
            }
            function bind_account_type_selector() {
                $("#UserAccountOverlap .account_type").change(change_account_type_selector);

                function change_account_type_selector() {
                    var mode = $(this).children('option:selected').val();                    
                    $("#UserAccountOverlap .account_mode").addClass("hidden");
                    $("#UserAccountOverlap .account_mode_{0}".format(mode)).removeClass("hidden");
                    if(mode==3){
                    	$("#UserAccountOverlap .account_amount").val('');
                    	$("#UserAccountOverlap .account_num").val('');
                    	$("#UserAccountOverlap .account_amount").attr("disabled","disabled");
                    	$("#UserAccountOverlap .account_num").attr("disabled","disabled");
                    }else{
                    	$("#UserAccountOverlap .account_amount").removeAttr("disabled");
                        $("#UserAccountOverlap .account_num").removeAttr("disabled");
                    };
                }
            }
            function bind_account_amount_input() {
                $("#UserAccountOverlap .account_amount").blur(blur_account_amount_input);

                function blur_account_amount_input() {
                    var value = 0;
                    value = $(this).attr("max");
                    var input = $("#UserAccountOverlap .account_amount").val();
                    //if (input.length > 0 && value < parseInt(input)) {
                        //$("#UserAccountOverlap .account_amount").val(value);                   	
                    //}
                }
            }
            function bind_account_num_input() {
                $("#UserAccountOverlap .account_num").blur(blur_account_num_input);

                function blur_account_num_input() {
                    var value = 0;
                    value = $(this).attr("max");
                    var input = $("#UserAccountOverlap .account_num").val();
                    //if (input.length > 0 && value < parseInt(input)) {
                        //$("#UserAccountOverlap .account_num").val(value);
                    //}
                }
            }
            function bind_account_overlap_btn() {
                $("#UserAccountOverlap .btn").click(click_account_overlap_btn);
                $("#UserAccountOverlap .cancel_btn").click(click_account_overlap_cancel);

                function post_create_account_callback(data) {
                    console.log(data);
                    load_account_module_page("create");
                    Models.OverlapList.AccountBlockOverlap.close();
                }

                function click_account_overlap_btn() {
                    var model = build_account_overlap_model();
                    console.log(model);
                    if (model) {
                        if (AccountModel.CurrentOverlapAccountId) {
                            model.accountId = AccountModel.CurrentOverlapAccountId;
                            repository.post_edit_account(model, post_create_account_callback)
                        } else {
                            repository.post_create_account(model, post_create_account_callback)
                        }
                    }
                }
                function click_account_overlap_cancel() {
                    Models.OverlapList.AccountBlockOverlap.close();
                }
            }
            function bind_account_overlap_events() {
                bind_account_type_selector();
                bind_account_amount_input();
                bind_account_num_input();
                bind_account_overlap_btn();
                clean_account_overlap();
            }
            function build_account_overlap_model() {
                if (!AccountModel.CurrentSelectAccount) {
                    toastr.error("请选择母账号！");
                    return null;
                }
                var model = {
                    parentId: AccountModel.CurrentSelectAccount.id,
                    accountName: $("#UserAccountOverlap .account_name").val(),
                    name: $("#UserAccountOverlap .account_remarks").val(),
                    password: $("#UserAccountOverlap .account_password").val(),
                    repassword: $("#UserAccountOverlap .account_repassword").val(),
                    type: $("#UserAccountOverlap .account_type").val(),
                    mobilePhone: $("#UserAccountOverlap .account_phone").val(),
                    email: $("#UserAccountOverlap .account_email").val(),
                    monitorNum: $("#UserAccountOverlap .account_amount").val() || 0,
                    childNum: $("#UserAccountOverlap .account_num").val() || 0,
                    canCreateChild: $("#UserAccountOverlap .account_create_right").is(':checked') ? 1 : 0,
                }
                return validate_account_model(model) ? model : null;

                function validate_account_model(model) {
                	if ($("#UserAccountOverlap .account_type").val()!=3){
                		if ($("#UserAccountOverlap .account_amount").val()==null || $("#UserAccountOverlap .account_amount").val()==0) {                		
                            toastr.warning('未分配监控额度！');
                            return false;
                        }
                	}               	
                    if (model.accountName.match(/^[\w\W]{2,20}$/g) == null) {                    	
                        toastr.warning('账户名格式错误！');
                        
                        return false;
                    }
                    if (model.mobilePhone.length <= 0 && model.email.length <= 0) {
                        toastr.warning('手机或邮箱缺失！');
                        return false;
                    } else {
                        if (model.mobilePhone.match(/^1[3|4|5|8][0-9]\d{4,8}$/) == null && model.mobilePhone.length > 0) {
                            toastr.warning('手机格式错误！');
                            return false;
                        }
                        if (model.email.match(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/) == null && model.email.length > 0) {
                            toastr.warning('邮箱格式错误！');
                            return false;
                        }
                    }
                    if (model.password.length < 6 || model.password.length > 20) {
                        toastr.warning('密码长度请设置在6-20位！');
                        return false;
                    }
                    if (model.password != model.repassword) {
                        toastr.warning('两次输入密码不一致！');
                        return false;
                    }
                    return true;

                }
            }
            function set_account_num_max(parent_num, current_num) {
                return parent_num || 0 + current_num || 0;
            }
            /*===账号点击触发事件相关===*/
            function bind_add_account_btn() {


                $("#AddAccountBtn").click(click_add_account_btn);

                function click_add_account_btn() {
                    change_account_overlap_mode();                   
                    if(AccountModel.CurrentSelectAccount.type==1){ 
                    	$("#account_1").css("display","inline");
                    	Models.OverlapList.AccountBlockOverlap.show("#UserAccountOverlap");
                    	//kaka=$("#UserAccountOverlap .account_type").children('option:selected').val();
                    	//alert(kaka)
                    	
                    	
                    }
                    else{
                    	$("#account_1").css("display","none");
                    	Models.OverlapList.AccountBlockOverlap.show("#UserAccountOverlap");                    	
                    };
                    
                    
                    //$("#account_1").css("display","inline");
                }
            }
            /*======提示信息回调函数======*/
            function message_delete_account_callback(result) {
                if (result) {
                    var model = {
                        accountId: AccountModel.CurrentOverlapAccountId
                    }
                    repository.post_delete_account(model, post_delete_account_callback);
                }
                AccountModel.CurrentOverlapAccountId = null;
                function post_delete_account_callback(data) {
                    console.log(data);
                    load_account_module_page("delete");
                }
            }
            function message_disable_account_callback(result) {
                if (result) {
                    var model = {
                        accountId: AccountModel.CurrentOverlapAccountId,
                        index: AccountModel.CurrentTableIndex
                    }
                    repository.post_disable_account(model, post_disable_account_callback);
                }
                AccountModel.CurrentTableIndex = null;
                AccountModel.CurrentOverlapAccountId = null;
                function post_disable_account_callback(data) {
                    console.log(data);
                    $("#CurrentAccountSub table tbody tr:eq({0}) .disabled".format(data.index)).attr("val", "0");
                    $("#CurrentAccountSub table tbody tr:eq({0}) .disabled".format(data.index)).text("启用");
                }
            }
            function message_enable_account_callback(result) {
                if (result) {
                    var model = {
                        accountId: AccountModel.CurrentOverlapAccountId,
                        index: AccountModel.CurrentTableIndex
                    }
                    repository.post_enable_account(model, post_enable_account_callback);
                }
                AccountModel.CurrentTableIndex = null;
                AccountModel.CurrentOverlapAccountId = null;
                function post_enable_account_callback(data) {
                    console.log(data);
                    $("#CurrentAccountSub table tbody tr:eq({0}) .disabled".format(data.index)).attr("val", "1");
                    $("#CurrentAccountSub table tbody tr:eq({0}) .disabled".format(data.index)).text("禁用");
                }
            }
            function message_delete_account_failed_callback(result) {
                AccountModel.CurrentOverlapAccountId = null;
            }
            /*======统计相关======*/
            function get_account_total_data_callback(result) {

                load_module_over();
            }
            function load_account_data() {
                load_module_waiting();
                repository.get_account_total_data({}, get_account_total_data_callback)
            }
            /*======初始化相关======*/
            function load_account_module_page(response) {
                load_account_pie();
                load_account_tree();
                // if (!AccountModel.AccountId || AccountModel.AccountId == AccountModel.CurrentSelectAccount.id) {
                //     load_account_pie();
                //     load_account_tree();
                // }
                // else {
                //     load_account_pie();
                //     load_account_list();
                // }
                if (response === "create" || response === "delete" ) {
                	location.reload();
                }
            }
            return {
                load_account_module_page: load_account_module_page,
                bind_add_account_btn: bind_add_account_btn,
                create_account_overlap: create_account_overlap,
                bind_account_overlap_events: bind_account_overlap_events,
                message_delete_account_callback: message_delete_account_callback,
                message_disable_account_callback: message_disable_account_callback,
                message_enable_account_callback: message_enable_account_callback,
                message_delete_account_failed_callback: message_delete_account_failed_callback
            }
        }()
    }
    var Models = {
        OverlapList: {
            MessageOverlap: null,
            AccountBlockOverlap: null
        },
        MessageConfig: {
            DeleteAccountMessage: {
                name: "DeleteAccountMessage",
                message: "是否确认删除子账户，账户内监控的企业将全部取消监控?",
                callback: Modules.UserAccountModule.message_delete_account_callback
            },
            DeleteAccountFailedMessage: {
                name: "DeleteAccountFailedMessage",
                message: "请先删除账户下的子账户或者附属账户!",
                callback: Modules.UserAccountModule.message_delete_account_failed_callback
            },
            DisableAccountMessage: {
                name: "DisableAccountMessage",
                message: "是否确认禁用当前账号，禁用后将无法登陆该账号?",
                callback: Modules.UserAccountModule.message_disable_account_callback
            },
            EnableAccountMessage: {
                name: "EnableAccountMessage",
                message: "是否确认启用当前账号?",
                callback: Modules.UserAccountModule.message_enable_account_callback
            }
        }
    }
    var Loader = {
        load_overlap_list: function () {
            Models.OverlapList.MessageOverlap = new MessageOverlap();
            Models.OverlapList.AccountBlockOverlap = Modules.UserAccountModule.create_account_overlap();
            return this;
        },
        load_account_module_page: function () {
            Modules.UserAccountModule.load_account_module_page();
            return this;
        },
        init_message_overlap: function () {
            var message = Models.MessageConfig;

            var overlap = Models.OverlapList.MessageOverlap;

            overlap.push(message.DeleteAccountMessage.name,
                message.DeleteAccountMessage.message,
                message.DeleteAccountMessage.callback);

            overlap.push(message.DeleteAccountFailedMessage.name,
                message.DeleteAccountFailedMessage.message,
                message.DeleteAccountFailedMessage.callback);

            overlap.push(message.DisableAccountMessage.name,
                message.DisableAccountMessage.message,
                message.DisableAccountMessage.callback);

            overlap.push(message.EnableAccountMessage.name,
                message.EnableAccountMessage.message,
                message.EnableAccountMessage.callback);
            return this;
        }
    }
    var Binder = {
        bind_click_events: function () {
            Modules.UserAccountModule.bind_account_overlap_events();
            Modules.UserAccountModule.bind_add_account_btn();
            return this;
        }
    }
    var UserAccountController = function () {
        Loader
            .load_overlap_list()
            .init_message_overlap()
            .load_account_module_page();
        Binder
            .bind_click_events();
    }
    UserAccountController.prototype = {

    }
    UserAccountController.prototype.constructor = UserAccountController;
    return UserAccountController;
}();