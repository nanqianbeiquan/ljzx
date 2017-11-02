var TreeViewComponent = function (id, data, deep) {
    function create_treeview() {
        // var treeview = $(toString(function () {/*
        // <ul class="treeview">
        //     <li>
        //         <div class="root_account"><span>SRD</span></div>
        //         <div class="attach_account"><span>SRDSRDSRDSRD(附属账号)</span></div>
        //         <ul>
        //             <li class="opened_tree child_root_account">
        //                 <div class="child_account"><span>SRD_CP</span></div>
        //                 <ul>
        //                     <li><div class="child_account no_child"><span>SRD_CPZ1SRD_CPZ1SRD_CPZ1</span></div></li>
        //                 </ul>
        //                 <span class="tree_click"></span>
        //             </li>
        //             <li class="opened_tree child_root_account">
        //                 <div class="child_account"><span>SRD_CPZ2</span></div>
        //                 <div class="attach_account"><span>SRD_CPZ2(附属账号)</span></div>
        //                 <ul>
        //                     <li>
        //                         <div class="child_account no_child"><span>SRD_CPZ3</span></div>
        //                     </li>
        //                     <li>
        //                         <div class="child_account no_child"><span>SRD_CPZ4</span></div>
        //                     </li>
        //                 </ul>
        //                 <span class="tree_click"></span>
        //             </li>
        //             <li class="opened_tree child_root_account">

        //                 <div class="child_account"><span>SRD_CPZ5</span></div>
        //                 <div class="attach_account"><span>SRD_CPZ5(附属账号)</span></div>
        //                 <ul>
        //                     <li>
        //                         <div class="child_account no_child"><span>SRD_CPZ6</span></div>
        //                     </li>
        //                     <li>
        //                         <div class="child_account no_child"><span>SRD_CPZ7<span></div>
        //                     </li>
        //                 </ul>
        //                 <span class="tree_click"></span>
        //             </li>
        //             <li class="">
        //                 <div class="child_account no_child"><span>SRD_CPZ5</span></div>
        //             </li>
        //         </ul>
        //     </li>
        // </ul>
        // */}))
        return $("<ul class='treeview'></ul>")
    }
    function create_tree_node(account, tree, deep) {
        deep--;
        if (deep < 0) {
            return tree;
        }
        var node = $("<li></li>");
        $(node).append("<div class='child_account no_child' accountId='{1}'><span title='{0}'>{0}</span><span class='account_num'>({2}/{3})</span></div>".format(account.name, account.id, account.usedChildNum, account.childNum));
        if (account.attach.length > 0 && deep > 0) {
            $(node).find(".child_account").removeClass("no_child");
            $(node).addClass("no_child");
            for (var i = 0; i < account.attach.length; i++) {
                $(node).append("<div class='attach_account'><span title='{0}(附属账号)'>{0}(附属账号)</span></div>".format(account.attach[i].name));
            }
        }
        if (account.accounts.length > 0 && deep > 0) {
            $(node).find(".child_account").removeClass("no_child");
            $(node).addClass("child_root_account").addClass("opened_tree");
            $(node).removeClass("no_child");
            var ul_tree = $("<ul></ul>");
            for (var i = 0; i < account.accounts.length; i++) {
                create_tree_node(account.accounts[i], ul_tree, deep);
            }
            $(node).append(ul_tree);
            $(node).append("<span class='tree_click'></span>");
        }
        if (account.attach.length > 0 || account.accounts.length > 0) {

        }
        $(tree).append(node);
        return tree;
    }

    var Loader = {
        load_tree_component: function (id, data, deep) {
            var tree = create_treeview();
            tree = create_tree_node(data[0], tree, deep);
            $(tree).find("li:first").removeClass("child_root_account").removeClass("opened_tree");
            $(tree).find("li:first .child_account:first").removeClass("child_account").addClass("root_account");
            $(id).append(tree);
        }
    }
    var Binder = {
        bind_fold_tree: function (id) {
            $(id).find(".tree_click").click(click_tree_fold);
            return this;

            function click_tree_fold() {
                var _this = this;
                var parent = $(_this).parent();
                if (parent.hasClass("opened_tree")) {
                    parent.removeClass("opened_tree");
                    parent.addClass("closed_tree");
                } else {
                    parent.addClass("opened_tree");
                    parent.removeClass("closed_tree");
                }
            }
        }
    }
    var TreeViewComponent = function (id, data, deep) {
        Loader.load_tree_component(id, data, deep);
        Binder.bind_fold_tree(id);
    };
    TreeViewComponent.prototype = {
        bind_child_node_click: function (callback) {
            $(".treeview .root_account").click(callback);
            $(".treeview .child_account").click(callback);
        }
    };
    TreeViewComponent.prototype.constructor = TreeViewComponent;
    return TreeViewComponent;
}();