var ieController;
var importLap;
$(function(){
	ieController = new importExportEventController();
    
	
})

var importExportEventController=function(){

    var event_import=function(){
    	importLap=new overlap({
        mask:{
            show:true,
            close:false
        },
        scroller:{
            lock:true
        },
        position:{
            type:"fixed"
        },
        close:{
            show:true,
            type:"close_1",
            style:{

            },
            callBack:function(){
                //open_new_group=false;
            	console.log("import_dialog closed");
            	var deep = $("#deep").val();
            	var parameter = {
            			"currentAccountId": $("#currentAccountId").val(),
            			"showGroupType":showGroupType,
            			"groupName":groupName,
            			"riskLevel"  : riskLevel,
            	    	"riskStatus"  : riskStatus,
            	    	"eventLevel"   :eventLevel,
            	    	"beginDate"   :beginDate,
            	    	"dueDate"     :dueDate,	
            	    	"area"     :area,	
            	    	"label"    :label,
            			"currentPageNo": pageNo,
            			"pageSize": pageSize,
            			"companyName":companyName,
            			"groupStatus":groupStatus,
            			"key":key,
            			"steps":steps,
            			"flag":flag,
            			"extendFlag":extendFlag,
            			"deep":deep
            	}
            	console.log("refresh company list,param=");
            	console.log(parameter);
            	var paramURL="?";
            	if(parameter instanceof Object){
            		for(var attr in parameter){
            			paramURL += attr+"="+parameter[attr]+"&";
            		}
            	}else{
            		paramURL = parameter;
            	}
            	var baseURL = window.location.pathname;
            	window.location.href = baseURL + paramURL;
            }
        },
        content:{
            style:{
                width:"600px",
                height:"500px",
                border:"16px solid rgba(0,0,0,0.15)"
            }
        }});
        console.log("import btn added click event..");
        //批量导入按钮
        $("#import_company_btn").click(function(){
        	console.log("import btn clicked..");
        	
        	var selectedKey = $(".tab_list .selected .tab_value").attr("key");
        	var selectedValue = $(".tab_list .selected .tab_value").find(".value").text();
        	console.log("selectedKey="+selectedKey+",selectedValue="+selectedValue);
        	
        	$("#selectedGroup").html(selectedValue);
        	//$(".import_title span").html(selectedValue);
        	//$("#selectedGroupName").html(selectedValue);
        	importLap.show(".import_company_list_over_lap");
            $("#filePath").val("");
        	$("#fileInput").val(""); 
        	testFunction();
        })
        //确定按钮
        $("#import_company_list_btn").click(comfirmBtnClickedFunction);
        
        //导出失败公司按钮
        $(".export_error_company_btn").click(exportFailedCompanyBtnClicked);
        
        $(".import_err_message").scroll(function(scr){
            $(".company_name_list").scrollTop($(".import_err_message").scrollTop());
        })
        
        //浏览按钮和路径显示文本input
        $("#browseFileBtn").click(function(){
        	
        	$("#fileInput").click();
        });
        $("#filePath").click(function(){
        	$("#fileInput").click();
        });
        $("#fileInput").change(function(){
        	console.log("fileInput selected path="+$("#fileInput").val());
        	$("#filePath").val($("#fileInput").val());
        });
        
        //导出公司列表
        //$("#export_company_btn").click(exportCompanyBtnClickedFunction);
        
    }
    var importExportEventController=function(){
    	this.init_extend();
        this.event_bind();
    }
    importExportEventController.prototype={
        load_page:function(){
           
        },
        init_extend:function(){
        	//判断当前字符串是否以str开始 先判断是否存在function是避免和js原生方法冲突，自定义方法的效率不如原生的高  
            if (typeof String.prototype.startWith != 'function'){   
              String.prototype.startWith = function (str){  
                 return this.slice(0, str.length) == str;  
              };  
            }
            //判断当前字符串是否以str结束  
            if (typeof String.prototype.endWith != 'function') {  
              String.prototype.endWith = function (str){  
                 return this.slice(-str.length) == str;  
              };  
            }  
        },
        event_bind:function(){
         
            console.log("init import btn...");
            event_import();
        }
    };
    importExportEventController.prototype.constructor = importExportEventController;
	return importExportEventController;
}();


function testFunction(){
	console.log("test function is invoked..");
}
var isImporting = false;
var comfirmBtnClickedFunction=function(){
	console.log("comfirm btn clicked...");
	
	if(isImporting){
		toastr.warning("正在导入，请耐心等待...！");
		return;
	}
	
	var filePath = $("#fileInput").val();
   //验证文件不能为空，
	if(filePath==""){
		toastr.warning("请选择上传的文件！");
		return;
	}
   //验证文件格式
	if(!(filePath.endWith(".xls")||filePath.endWith(".xlsx")||filePath.endWith(".xlsm"))){
		toastr.warning("不支持该文件后缀，目前支持以.xls，.xlsx或.xlsm结尾的Excel文件！");
		return;
	}
   //上传文件
	console.log("url="+ctx+"/ie/importBatch.do");
	var formData = new FormData($("#uploadForm")[0]);//获取文件对象
	formData.append("selectedGroupName",$("#selectedGroup").html().trim());
//	console.log("selectedGroupName="+$("#selectedGroup").html().trim());
//	console.log(formData);
//	console.log(formData.toLocaleString());
	$.ajax({
    	url: ctx+"/ie/importBatch.do",
    	type: "POST",
    	data: formData,
    	dataType: "json",
        beforeSend : function() {
			//异步请求时spinner出现
			var target = $(".import_company_list_over_lap").get(0);
			spinner.spin(target);
			isImporting = true;
	    },
    	async: true,
        cache: false,
        contentType: false,
        processData: false,
    	success: function(data){
    		console.log("import call back:data"+data);
            console.log(data);
            spinner.spin();
            isImporting = false;
            if(data.resultCode=="1"){
            	var msg = data.resultMsg;
            	toastr.warning(msg);
            	return;
            }
            
            //var nameHtml="";
          //  var reasonHtml="";
            var fialedListView = "";
            //设置结果页面数据
            if(data.resultData){
            	$("#failedNum").html(data.resultData.failureNum);
            	$("#successNum").html(data.resultData.successNum);
            	var reason = "";
            	if(data.resultData.failureCompany){
            		for(var attr in data.resultData.failureCompany){
            			//nameHtml += "<div>"+attr+"</div>";
            			//reasonHtml += "<div>"+data.resultData.failureCompany[attr]+"</div>";
            			reason = data.resultData.failureCompany[attr];
            			if(reason.indexOf("该企业已更名为")>-1){
            				fialedListView+= "<tr><td><font color='red'>"+attr+"</font></td><td>"+reason+"</td></tr>";
            			}else{
            				fialedListView+= "<tr><td>"+attr+"</td><td>"+reason+"</td></tr>";
            			}
            			
            		}
            	}
        	   if(data.resultData.canMonitorResult
               		&&data.resultData.canMonitorResult.resultCode
               		&&data.resultData.canMonitorResult.resultCode!='0'
               			&&data.resultData.canMonitorResult.resultMsg){
               	console.log("<div>"+data.resultData.canMonitorResult.resultMsg+"</div>");
               	$("#canMonitorMsg").html(data.resultData.canMonitorResult.resultMsg);
               }
            	
            }         
            
           // $(".company_name_list").html(nameHtml);
    		//$(".import_err_message").html(reasonHtml);
            $("#failedTbody").html(fialedListView);
            //显示结果
    		importLap.show(".import_result_overlap");    		
    	},
    	error: function(){
    		spinner.spin();
    		isImporting = false;
    	},
    	complete: function(request, textStatus){
    		if(request){if(request.status==0){toastr.error('站点异常！');}else if(request.status==500){toastr.error('站点异常！');}else{if(!textStatus||textStatus!="success"){toastr.error('站点异常！');}}}
    	}
    });
	
	
	//
};

function exportFailedCompanyBtnClicked(){
	var content  = $("#failedTbody").html();
	if(content==""){
		toastr.warning("没有需要导出的公司！");
		return;
	}
	window.location.href = ctx + "/ie/exportFailedCompany.do";
	//importLap.close();
}
function downloadTemplate(){
	window.location.href = ctx +"/ie/downloadTemplate.do";
}
