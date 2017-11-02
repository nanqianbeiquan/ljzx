
function getLegalInstrumentList(){
	
	controller.load_data();
}
//查询司法文书信息
function queryLegalInstrumentList(parameter){
	
	/*spinner.spin($("#legalInstrumentList").get(0));*/
	
	
/*	var parameter = {
			"companyName" : '北京百度网讯科技有限公司',
			"instrumentTypes" : instrumentTypes.join(","),
			"trialClasses" : trialClasses.join(","),
			"litigationTypes" : litigationTypes.join(","),
			"relatedPositions" : relatedPositions.join(","),
			"beginDate" : $("#beginDate").val(),
			"endDate" : $("#endDate").val(),
			"minAmountCount" : $("#minAmountCount").val(),
			"maxAmountCount" : $("#maxAmountCount").val(),
			"currentPageNo": parseInt(1),
			"pageSize": parseInt(10)
	}*/
	parameter.companyName=$(".monitor_company_name").html().trim();
    $.ajax({
        type: "post",
        dataType: "json",
        url: "/ljzx/law/queryLegalInstrumentList",
        data: parameter,
        success: function (result) {
        	$("#instrumentResult").html("");//清除之前的数据
        	
        	$("#instrumentResult").append(fomatResult(result.resultData));//添加新的数据
        	/*if(result.resultData.recordSum>0){*/
	        	$("#legalPagination").pagination(result.resultData.recordSum, {
	                prev_text: "上一页",
	                next_text: "下一页",
	                num_edge_entries: 3,
	                items_per_page : result.resultData.pageSize,
					current_page :parseInt(result.resultData.currentPageNo)-1,
	                num_display_entries: 5,
	                //回调 
	                callback: pageSelectCallback
	            });
        	/*}*/
        },
		error : function() {
			console.log("error");
		},
		complete : function(request, textStatus) {
			console.log("textStatus" + textStatus);
			console.log(request);
			if(request){
    			if(request.status==0){
    				toastr.error('网络异常！');
    			}else if(request.status==500){
    				toastr.error('站点异常！');
    			}else{
    				if(!textStatus||textStatus!="success"){
    					toastr.error('站点异常！');
    				}
    			}
    		}
		}
    });
}

//分页回调
function pageSelectCallback(new_page_index, pagination_container){
	
	var currentPageNo = parseInt(new_page_index) + 1;
	var pageNo= parseInt(currentPageNo);
	console.log(pageNo);
	controller.set_current_page(pageNo);
	console.log(controller.filter_model);
	controller.load_data(pageNo);
}

//格式化结果数据
function fomatResult(resultData){
	var results=resultData.results;
	var pageSize=resultData.pageSize;
	var currentPageNo=resultData.currentPageNo;
	var content = "";
	
	if(results.length<=0){
		for(var i=0;i<6;i++){
			content +='<tr><td></td><td style="text-align:center"></td><td style="text-align:left">'
			content +='</td><td style="text-align:left;"></td><td style="text-align:center"></td><td style="text-align:left">'
			content +='</td><td style="text-align:center"></td><td style="text-align:center"></td><td style="text-align:center">'
			content +='</td><td style="text-align:center"></td></tr>';
		}
	}
	for(var key in results){
		
		content +='<tr><td>' + ((pageSize*(currentPageNo-1))+parseInt(key)+1) + '</td><td style="text-align:center">' 
				+ results[key].judgmentTime + '</td><td style="text-align:left">' 
		 		+ results[key].companyName + '</td><td  onclick="viewInstrumentDetail(\'' + results[key].judgmentID + '\')" class="gongshangtablelianjie link_td" style="text-align:left;">' 
		 		+ results[key].caseNo + '</td><td style="text-align:center">' 
		 		+ results[key].relatedPosition + '</td><td style="text-align:left">' 
		 		+ results[key].causeAction + '</td><td style="text-align:center">' 
		 		+ results[key].instrumentType + '</td><td style="text-align:center">' 
		 		+ results[key].litigationType + '</td><td style="text-align:center">' 
		 		+ results[key].trialClass + '</td><td style="text-align:center">' 
		 		+ new Number(results[key].amountCount) + '元</td></tr>';
	}
	
	return content;
}

//导出司法文书列表
function exportLegalInstrumentList(){
	var companyName=$(".monitor_company_name").html().trim();
	window.location.href = "/ljzx/law/exportLegalInstrumentList?companyName=" +companyName ;
}
