//查询今日加入动态监控的企业
function queryTodayMonitorCompanyList(){
	
	var parameter = {
			"currentPageNo": parseInt(1),
			"pageSize": parseInt(15)
	}
	
    $.ajax({
        type: "post",
        dataType: "json",
        url: ctx + "/monitorCompany/queryTodayMonitorCompanyList",
        data: parameter,
        success: function (page) {
        	$("#companyList").html("");//清除之前的数据
        	
        	$("#companyList").append(fomatResult(page.results));//添加新的数据
        	
        	$("#Pagination").pagination(page.recordSum, {
                prev_text: "上一页",
                next_text: "下一页",
                num_edge_entries: 3,
                num_display_entries: 10,
                items_per_page : 15,
                //回调 
                callback: pageselectCallback
            });
        },
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
    });
}

//分页回调
function pageselectCallback(new_page_index, pagination_container){
	var currentPageNo = parseInt(new_page_index) + 1;
	
	var parameter = {
			"currentPageNo": parseInt(currentPageNo),
			"pageSize": parseInt(15)
	}
	
    $.ajax({
        type: "post",
        dataType: "json",
        url: ctx + "/monitorCompany/queryTodayMonitorCompanyList",
        data: parameter,
        success: function (page) {
        	$("#companyList").html("");//清除之前的数据
        	
        	$("#companyList").append(fomatResult(page.results));//添加新的数据
        },
        error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
    });
}

//格式化结果数据
function fomatResult(results){
	var content = "";
	
	for(var key in results){
		if(parseInt(key)%3==0){
			content += '<div class="company_line"><div class="company inline_div">';
		}else{
			content += '<div class="company inline_div">';
		}
		
		content += '<div class="company_name">' + results[key].companyName + '</div>'
				 + '<div class="company_info"><span>监控日期：<span>' + new Date(results[key].createTime).pattern("yyyy-MM-dd") + '</span></span></div></div>';
		
		if((parseInt(key)+1)%3==0){
			content += '</div>';
		}
	}
	
	if(!results.length%3==0){
		content += '</div>';
	}
	
	return content;
}