var controller;
var beginDate;
var dueDate;
var companyName="";
var eventSubType="01";
var monitorDate;
var monitorId="";
var realDate="";
var superCompanyName="";
var infoShowDate="";
var temp=null;
var typeListString="";
var readStatus="0";
var preReadStatus="0";
var eventIdListString="";
var eventIdArr= new Array();
var eventsList;
var eventIdStr="";
//前一个事件类型
var preEventSubType="";
var ignoreFlag=false;
//事件维度数量列表
var riskEventList=new Array();
var eventCategory={};
//没有时间的维度
var noContentDateArray = ["06","07","10","27","28"];

var message_lap_deleteGroup;

$(function(){
	
	var opts = {            
            lines: 13, // 花瓣数目
            length: 10, // 花瓣长度
            width: 5, // 花瓣宽度
            radius: 10, // 花瓣距中心半径
            corners: 1, // 花瓣圆滑度 (0-1)
            rotate: 0, // 花瓣旋转角度
            direction: 1, // 花瓣旋转方向 1: 顺时针, -1: 逆时针
            color: '#618dbe', // 花瓣颜色
            speed: 1, // 花瓣旋转速度
            trail: 60, // 花瓣旋转时的拖影(百分比)
            shadow: false, // 花瓣是否显示阴影
            hwaccel: false, //spinner 是否启用硬件加速及高速旋转            
            className: 'spinner', // spinner css 样式名称
            zIndex: 2e9, // spinner的z轴 (默认是2000000000)
            top: '50%', // spinner 相对父容器Top定位 单位 px
            left: '50%'// spinner 相对父容器Left定位 单位 px
	};

	window.spinner = new Spinner(opts);
	
	controller = new companyGroupController();
	var rename=new CompanyRenameController();
	rename.load_relative_company_detail_rename();
	message_lap_deleteGroup=new overlap({
	    mask:{
	     show:true
	 },
	 scroller:{
	     lock:false
	 },
	 position:{
	     type:"center"
	 },
	 close:{
	     show:false,
	     type:"close_2",
	     style:{

	     },
	     callBack:function(){
	         open_new_group=false;
	     }
	 },
	 content:{
	     style:{
	         width:"516px",
	         height:"200px",
	         border:"16px solid rgba(0,0,0,0.15)"
	     }
	 }});
	
	//初始化页面信息
  	initComInfo();
  	//step2 事件绑定 
  	dynamicBind();
  	
  	var deep = $("#deep").val();
  	updateNavMenu(parseInt(deep),"关联企业详情页",window.location.search);
})


var CompanyGroupRepository = function () {
    function GetFinanceData(model, callback) {
        var data = {
            companyName: encodeURI(model.companyName)
        }
        applyAjax("{0}/finance/getFinanceData".format(ctx), data, function (result) {
            if (IsFunction(callback)) {
                if (result.respCode == "200") {
                    callback(result.data);
                }
                else {
                    callback(null);
                }
            }
        })
    }

    var CompanyAnalysisRepository = function () { }

    CompanyAnalysisRepository.prototype = {
        get_finance_data: function (model, callback) {
            GetFinanceData(model, callback);
        }
    }
    CompanyAnalysisRepository.prototype.constructor = CompanyAnalysisRepository;
    return CompanyAnalysisRepository;
}();

var companyGroupController=function(){

	var repository=new CompanyGroupRepository();

    var event_event=function(){
        $(".event_titles .title").click(function(){
            if($(this).hasClass("selected")){
                return;
            }
            $(".event_titles .selected").removeClass("selected");
            $(this).addClass("selected");
            $(".event_spans .current").removeClass("current").addClass("hidden");
            $(".event_spans .title_"+($(".event_titles .title").index(this)+1)).addClass("current").removeClass("hidden");
        });
        $(".event_spans .span").click(function(){
            if($(this).hasClass("selected")){
                return;
            }
            $(this).parents(".title_spans").find(".selected").removeClass("selected");
            $(this).addClass("selected");
        });
    }

    /**
     * 司法文书lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
    var create_sfws_lap=function(){
    	var sfws_lap=new overlap({
            mask:{
             show:true
         },
         scroller:{
             lock:true,
             body_hidden:true
         },
         position:{
             type:"center"
         },
         close:{
             show:true,
             type:"close_2",
             style:{

             },
             callBack:function(){
                 open_new_group=false;
             }
         },
         content:{
             style:{
                 width:"800px",
                 height:"600px",
                 border:"16px solid rgba(0,0,0,0.15)"
             }
         }});
    	return sfws_lap;
    }
    
    //绑定每一个lap
    var sfws_details_show=function(_this,sfws_lap){
    	  $(_this).click(function(){
          	//打开司法文书的窗口
          	var judgmentId=$(this).attr("judgmentId");
        	var eventId=$(this).attr("eventId");
        	getDetails4sfws(judgmentId,eventId,_this);
          	sfws_lap.show(".sfws");
              return false;
          });
        
    }
    
    /**
     * 新闻舆情lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
    var create_news_lap=function(){
    	var news_lap=new overlap({
            mask:{
             show:true
         },
         scroller:{
             lock:true,
             body_hidden:true
         },
         position:{
             type:"center"
         },
         close:{
             show:true,
             type:"close_2",
             style:{

             },
             callBack:function(){
                 open_new_group=false;
             }
         },
         content:{
             style:{
                 width:"800px",
                 height:"600px",
                 border:"16px solid rgba(0,0,0,0.15)"
             }
         }});
    	return news_lap;
    }
    
    //绑定每一个lap
    var news_details_show=function(_this,news_lap){
    	  $(_this).click(function(){
          	//打开新闻舆情的窗口
          	var eventId=$(this).attr("eventId");
          	getDetails(eventId,_this);
          	news_lap.show(".news");
              return false;
          });
        
    }
    
    /**
     * 失信信息lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
    var create_sx_lap=function(){
    	var sx_lap=new overlap({
            mask:{
             show:true
         },
         scroller:{
             lock:false
         },
         position:{
             type:"center"
         },
         close:{
             show:true,
             type:"close_2",
             style:{

             },
             callBack:function(){
                 open_new_group=false;
             }
         },
         content:{
             style:{
                 width:"600px",
                 height:"580px",
                 border:"16px solid rgba(0,0,0,0.15)"
             }
         }});
    	return sx_lap;
    }
    
    //绑定每一个lap
    var sx_details_show=function(_this,sx_lap){
    	  $(_this).click(function(){
          	//打开新闻舆情的窗口
          	var eventId=$(this).attr("eventId");
          	getDetails(eventId,_this);
          	sx_lap.show(".shixin");
              return false;
          });
        
    }
    /**
     * 食药监lap step1 先定义一个lap窗口 step2强内容绑定到lap中
     */
    var create_syj_lap=function(){
    	var syj_lap=new overlap({
            mask:{
             show:true
         },
         scroller:{
             lock:false
         },
         position:{
             type:"center"
         },
         close:{
             show:true,
             type:"close_2",
             style:{

             },
             callBack:function(){
                 open_new_group=false;
             }
         },
         content:{
             style:{
                 width:"800px",
                 height:"750px",
                 border:"16px solid rgba(0,0,0,0.15)"
             }
         }});
    	return syj_lap;
    }
    
    //绑定每一个lap
    var syj_details_show=function(_this,sjy_lap){
    	   $(_this).click(function(){
           	//打开新闻舆情的窗口
           	var eventId=$(this).attr("eventId");
           	getDetails(eventId,_this);
           	sjy_lap.show("#shiyaojian");
               return false;
           });
        
    }
    var event_hover_btn=function(){
    	$(".body_block_title .title_btn .img_btn_2").hover(function(){
    		$(this).find(".img").addClass("hidden");
    		$(this).find(".img_hover").removeClass("hidden");
    	},function(){
    		$(this).find(".img").removeClass("hidden");
    		$(this).find(".img_hover").addClass("hidden");
    	});
    }
	
	var Modules={
		FinanceModule:function(){
			function IsEmptyData(obj) {
                var index = 0;
                if (obj) {
                    for (var year in obj) {
                        index++;
                    }
                }
                if (index > 0) {
                    return false;
                }
                return true;
            }
			function finance_data_callback(result){
				if (result == null || IsEmptyData(result.gxzb)) {
                    toastr.warning("该企业当前无更新的财务信息！");
                } else {
                    var deep = parseInt($("#deep").val()) + 1;
                    var companyName = $("#companyName").val();
                    
                    window.location.href = "{0}/finance/toFinanceView.do?cn={1}&deep={2}".format(ctx, encodeURI(companyName), deep);
                }
			}
			return {
				show_finance_data: function () {
                    var model = {
                        companyName: $("#companyName").val()
                    }
                    repository.get_finance_data(model, finance_data_callback);
                }
			}
		}()
	}

	var Binder={
		 bind_finance_click_event: function () {
            var module = Modules.FinanceModule;
            $("#finance_data_btn").click(module.show_finance_data)
            return this;
        },
		bind_overlap_event_content_scroll:function(){
			function scroll_move(e){
				e = e || window.event;
				var delta = e.wheelDelta || e.detail || e.originalEvent.detail || e.originalEvent.deltaY || -1 * e.originalEvent.wheelDelta;
				delta = -1 * (delta / Math.abs(delta)) * 100;
				var top = $(this).scrollTop() - delta;
				$(this).scrollTop(top);
				if($(this).scrollTop()<=0){
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
			$(".detail_content_block").bind('mousewheel', scroll_move);
			$(".detail_content_block").bind('DOMMouseScroll', scroll_move);
			return this;
		}
	}

    var companyGroupController=function(){
        this.echart_core=new echartController();
        this.load_page();
        this.bind_event();
        //创建司法文书lap
        this.sfws_lap=create_sfws_lap();
        //创建新闻舆情lap
        this.news_lap=create_news_lap();
        //失信信息lap
        this.sx_lap=create_sx_lap();

		Binder.bind_finance_click_event();
		Binder.bind_overlap_event_content_scroll();
    }
    companyGroupController.prototype={
        load_page:function(){
            //this.echart_core.monitor_company_event("monitor_company_event");
        },
        bind_event:function(){
            event_event();
            event_hover_btn();
        },
        sfws_details_bind:function(_this){
       	 sfws_details_show(_this,this.sfws_lap);
        },
        news_details_bind:function(_this){
      	news_details_show(_this,this.news_lap);
        },
        sx_details_bind:function(_this){
      	sx_details_show(_this,this.sx_lap);
        },
        syj_details_bind:function(_this){
        	syj_details_show(_this,this.syj_lap);
        }
    };
    companyGroupController.prototype.constructor = companyGroupController;
	return companyGroupController;
}();

var page_resizeContent=function(){
    if('object'==typeof controller){
        controller.load_page();
    }
}

//初始化页面信息
function initComInfo(){
	//initCheckBox 已读未读
	initCheckBox();
	
	//获取风险标签和雷达图信息
	getMonitorRelationCompanyRisk();

	//获取事件的数量
	getMonitorComRiskEventNum(true);
	
	getRelationCompanyEventList();
}

function dynamicBind(){
	//事件查询标签类型选择处理
	eventsQueryTypeDeal();
	
	bindHistoryInfo();
	
	//法人代表信息
	bindLegalPerson();
}

/**
 * 展示企业风险标签 风险状况图 雷达图
 */
function getMonitorRelationCompanyRisk(){
	
	var parameter = {
		"id" : $("#id").val()
	}
	
	$.ajax({
		type : "post",
		dataType : "json",
		url : ctx + "/monitorRelationCompany/getMonitorRelationCompanyRisk",
		data : parameter,
		success : function(result) {
			if(result.resultCode == "0"){
				var label="无";
				
				if(result!=null && result.resultData.riskLabel.trim()!=""){
					label = result.resultData.riskLabel;
				}
				
				//公司标签切割展示 整体展示
				var arry = label.split(",");
				
				for(var i=0;i<arry.length;i++){
					var index=(i+1)%4;
					
					if(index==0){
						index=4;
					}
					
					$("#companyTagList").append("<div class='span span_color_"+index+" inline_div'>"+arry[i]+"</div>");
				}
				$(".company_span #companyTagList .span:contains('失信')").addClass("blacklist_span").addClass("blacklist_span_color_3");
				//事件雷达图展示 				
				controller.echart_core.monitor_company_event("monitor_company_event",result.resultData);
			}else{
				//事件雷达图展示 				
				controller.echart_core.monitor_company_event("monitor_company_event",result);
			}
		}
	});
}

/**
 * 事件详情列表处理
 */
function getRelationCompanyEventList(){
	
	//初次分页需要做四件事 A 收集之前的信息  B更新已读信息 C查询 D清空列表
	//A 查询之前对已读信息的收集 
	//已读信息分为两种 一种是有详情 一种没有详情 a 区分事件类型 b 判断是否点击了忽略全部按钮
	
	if(!ignoreFlag==true){
		readEventsTotalDeal();
	}
	
	//默认开始时间必须大于加入关联的时间
	if(beginDate==""){
		//beginDate=realDate;
	}
	
    //发送异步请求查询
    var parameter = {
		"id" : $("#id").val(),
		"eventSubType" : eventSubType,
		"beginDate" : beginDate,
		"dueDate" : dueDate,
  		"typeListString" : typeListString,	
		"eventIdListStr": eventIdListString,
		"readStatus" : readStatus
    }
    
    $.ajax({
    		type : "post",
    		dataType : "html",
    		url : ctx + "/monitorRelationCompany/getRelationCompanyEventList",
    		data : parameter,
            beforeSend : function() {
    			//异步请求时spinner出现
    			var target = $(".monitor_events").get(0);
    			spinner.spin(target);
    	    },
    		success : function(result) {

    			//D 清空信息
    			eventIdArr=new Array();
    			eventIdListString="";
    			preReadStatus=readStatus;
    		
    			//还原忽略全部标志位
    	        ignoreFlag=false;
    	        spinner.spin();
    	        
    			//spinner.spin();
    			$("#detailsTable").html(result);//清除之前的数据
    			$("#Pagination4Details").html("");
    			//分页展示之后需要重新绑定事件
    			var arr=$(".sfwsDetails");
    			for(var i=0;i<arr.length;i++){
    				controller.sfws_details_bind($(arr[i]));
    			}
    			
    			//分页展示之后需要重新绑定事件
    			var arr=$(".newsDetails");
    			for(var i=0;i<arr.length;i++){
    				controller.news_details_bind($(arr[i]));
    			}
    			//分页展示之后需要重新绑定事件
    			var sxAarr=$(".sxDetails");
    			for(var i=0;i<sxAarr.length;i++){
    				controller.sx_details_bind($(sxAarr[i]));
    			}
    	
    			//分页展示之后需要重新绑定事件
    			var syjAarr=$(".syjDetails");
    			for(var i=0;i<syjAarr.length;i++){
    				controller.syj_details_bind($(syjAarr[i]));
    			}
    			
    			//记录当前页的所有eventId
    			eventIdStr=$("#eventIdStr").val();
    			//获取更新数量
    			var updateNum=$("#updateNum").val();
    			
 				getMonitorComRiskEventNum();
    			
    			//查询之后需要备份当前的事件类型 
    	        preEventSubType=eventSubType;
    			//已读未读颜色处理
    			var arr=$("#detailsTable").find(".tr_data");
    			if(arr.length!=0){
    				for(var i=0;i<arr.length;i++){
    					var obj=arr.get(i);
    					if($(obj).attr("status")=="1"){
    						//已读
    						$(obj).addClass("read_row");
    						var tempArr=$(obj).find("td");
    						$(tempArr[1]).addClass("new_event_read");
    						if(eventSubType=="12"){
    							//食药监
    							$(tempArr[3]).addClass("read_td");
    						}else if(eventSubType=="18" ||eventSubType=="19"||eventSubType=="20"){
    							//裁判文书
    							$(tempArr[5]).addClass("read_td");
    						}else if(eventSubType=="22"){
    							$(tempArr[6]).addClass("read_td");
    						}else if(eventSubType=="24"||eventSubType=="25"){
    							//新闻舆情
    							$(tempArr[1]).addClass("read_td");
    						}
    					}else{
    						if(eventSubType=="12" ||
        	    					eventSubType=="18" ||eventSubType=="19"||eventSubType=="20"||eventSubType=="22"||
        	    					eventSubType=="24"||eventSubType=="25"){
    							//未读
        						var tempArr=$(obj).find("td");
    						}else{
    							//如果是一般事件没有详情的那种类型需要将其也设置为灰色 同事讲eveId放进列表
    							//设置为已读标志
        						$(obj).addClass("read_row");
        						
    							var curIdArr= new Array(); 
    							curIdArr=eventIdStr.split(","); //字符分割 
    							eveId=curIdArr[i];
    							if(""==eventIdListString){
						    		eventIdListString=eveId;
						    	}else{
						    		eventIdListString=eventIdListString+","+eveId;
						    	}
    						}
    					}
    				}
    			}
    			
    			if($("#totalNum4Details").val()>0){
    				console.log("init page nav");
    				$("#Pagination4Details").pagination($("#totalNum4Details").val(),{
                        prev_text: "上一页",
                        next_text: "下一页",
                        num_edge_entries: 3,
                        num_display_entries: 10,
                        items_per_page:6,
                        //回调 
                        callback: pageselectCallbackForEventsDetailsList
                    });
    			}
    		}
    	});
}

/**
 * 查看事件详情动态绑定
 */
function eventsQueryTypeDeal(){
	
	$(".event_value").click(function () {  
//		if(temp!=null){
//			//说明不是第一次点击
//			var value=$(temp).find(".eventNo").text();
//			if("0"!=value){
//				temp.css("color","#2ea7e0");
//			}
//		}
        //清空背景色
		var obj=$(this).parent().children();
		obj.removeClass("selected");
		//重新加背景色
        $(this).addClass("selected");
        //字体设置为白色
//        var value=$(this).find(".eventNo").text();
//		if("0"!=value){
//			 $(this).css("color","#ffffff");
//		}
        $(this).css("color","#ffffff");
        console.log("preEventSubType="+preEventSubType);
        //赋值处理
        eventSubType=$(this).attr("index");
        if(temp&&preEventSubType!=eventSubType){
        	if(eventCategory.hasOwnProperty(preEventSubType)){
            	temp.css("color","#2ea7e0");
            }else{
            	temp.css("color","#666666");
            }
        }
        if(eventSubType=="24"||eventSubType=="25"){
        	$(".event_selector_serious_btn").addClass("hidden");
        	$(".event_selector_abnormal_btn").addClass("hidden");
        	$(".event_selector_general_btn").addClass("hidden");
        	
        }else{
        	$(".event_selector_serious_btn").removeClass("hidden");
        	$(".event_selector_abnormal_btn").removeClass("hidden");
        	$(".event_selector_general_btn").removeClass("hidden");
        }
        if(noContentDateArray.indexOf(eventSubType)>=0){
        	$("#filterTimeDiv").addClass("hidden");
        }else{
        	$("#filterTimeDiv").removeClass("hidden");
        }
        temp=$(this);
        getRelationCompanyEventList();    
	});
	
}

/**
 * 开始日期选择触发事件:周期方式设置为:3 保存对应日期  发送查询请求
 */
function beginDateDeal(date){
		beginDate= date;
		getRelationCompanyEventList();
}

/**
 * 结束日期选择触发事件:周期方式设置为:3 保存对应日期  发送查询请求
 */
function dueDateDeal(date){
		dueDate= date;
		getRelationCompanyEventList();
}



//分页回调
function pageselectCallbackForEventsDetailsList(new_page_index, pagination_container){
	
	//二次分页需要做两件事情 A 收集信息 B 查询
	//分页的时候需要做记录 统计当前已读的事件 统计方式有两种 一种没有详情的直接统计页数 第二种 有详情的不做处理
	readEventsTotalDeal();
	var currentPageNo = parseInt(new_page_index) + 1;
	var parameter = {
			"id" : $("#id").val(),
			"eventSubType" : eventSubType,
			"beginDate" : beginDate,
			"dueDate" : dueDate,
	  		"typeListString" : typeListString,	
			"eventIdListStr": eventIdListString,
			"readStatus" : readStatus,
			"currentPageNo": parseInt(currentPageNo),
			"pageSize": parseInt(6),
	}
	
    $.ajax({
        type: "post",
        dataType: "html",
        url : ctx + "/monitorRelationCompany/getRelationCompanyEventList",
        data: parameter,
        success: function (result) {
        	$("#detailsTable").html(result);//清除之前的数据
        	//分页展示之后需要重新绑定事件
			var arr=$(".sfwsDetails");
			for(var i=0;i<arr.length;i++){
				controller.sfws_details_bind($(arr[i]));
			}
			
			//分页展示之后需要重新绑定事件
			var arr=$(".newsDetails");
			for(var i=0;i<arr.length;i++){
				controller.news_details_bind($(arr[i]));
			}
			//分页展示之后需要重新绑定事件
			var sxAarr=$(".sxDetails");
			for(var i=0;i<sxAarr.length;i++){
				controller.sx_details_bind($(sxAarr[i]));
			}
			//分页展示之后需要重新绑定事件
			var syjAarr=$(".syjDetails");
			for(var i=0;i<syjAarr.length;i++){
				controller.syj_details_bind($(syjAarr[i]));
			}
			
			//记录当前页的所有eventId
			eventIdStr=$("#eventIdStr").val();
			//还原忽略全部标志位
			ignoreFlag=false;
			//$("#dataType="${searchResult.dataType}" class="tr_data"")
			//（0：未读，1：已读）
			var arr=$("#detailsTable").find(".tr_data");
			var curIdArr= new Array(); 
			curIdArr=eventIdStr.split(","); //当前页的eventId

			var arr4Id= new Array(); //保存的待更新的已经读取的事件Id
			arr4Id=eventIdListString.split(","); //字符分割 
			
			if(arr.length!=0){
				
				for(var i=0;i<arr.length;i++){
					var obj=arr.get(i);
					if($(obj).attr("status")=="1"){
						//已读
						$(obj).addClass("read_row");
						var tempArr=$(obj).find("td");
						$(tempArr[1]).addClass("new_event_read");
						if(eventSubType=="12"){
							//食药监
							$(tempArr[3]).addClass("read_td");
						}else if(eventSubType=="18" ||eventSubType=="19"||eventSubType=="20"){
							//裁判文书
							$(tempArr[5]).addClass("read_td");
						}else if(eventSubType=="22"){
							$(tempArr[6]).addClass("read_td");
						}else if(eventSubType=="24"||eventSubType=="25"){
							//新闻舆情
							$(tempArr[1]).addClass("read_td");
						}
					}else{

							
							var curIdArr= new Array(); 
							curIdArr=eventIdStr.split(","); //当前页保存的eventId
							var checkFlag=true;//判断是否设置为new的标志
							for(var j=0;j<arr4Id.length;j++){
								if(arr4Id[j]==curIdArr[i]){
									//已读
									$(obj).addClass("read_row");
									checkFlag=false;
									var tempArr=$(obj).find("td");
									$(tempArr[1]).addClass("new_event_read");
									if(eventSubType=="12"){
										//食药监
										$(tempArr[3]).addClass("read_td");
									}else if(eventSubType=="18" ||eventSubType=="19"||eventSubType=="20"){
										//裁判文书
										$(tempArr[5]).addClass("read_td");
									}else if(eventSubType=="22"){
										$(tempArr[6]).addClass("read_td");
									}else if(eventSubType=="24"||eventSubType=="25"){
										//新闻舆情
										$(tempArr[1]).addClass("read_td");
									}
								}
								
							}
							if(checkFlag==true){
								if(eventSubType=="12" ||
	        	    					eventSubType=="18" ||eventSubType=="19"||eventSubType=="20"||eventSubType=="22"||
	        	    					eventSubType=="24"||eventSubType=="25"){
	    							//未读
	        						var tempArr=$(obj).find("td");
	        						//$(tempArr[1]).addClass("new_event");
	    						}else{
	    							//如果是一般事件没有详情的那种类型需要将其也设置为灰色 同事讲eveId放进列表
	    							//设置为已读标志
	        						$(obj).addClass("read_row");
	        						
	    							var curIdArr= new Array(); 
	    							curIdArr=eventIdStr.split(","); //字符分割 
	    							eveId=curIdArr[i];
	    							if(""==eventIdListString){
							    		eventIdListString=eveId;
							    	}else{
							    		eventIdListString=eventIdListString+","+eveId;
							    	}
	    							
	    						}
							}
							
						}
					}
				}

			}
			
    });
}

function getMonitorComRiskEventNum(isInit){
		
	var parameter = {
		"id" : $("#id").val()
	}
 
	$.ajax({
		url: ctx + "/monitorRelationCompany/getMonitorRelationCompanyEventStatus",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			if(result==null){
				return;
			}
			
			if(result.resultCode == "0"){
				//初始化事件的数量
				eventCategory=result.resultData;
				var arr=$(".event_value");
				var cate ;
				for(var i=0;i<arr.length;i++){
					if(!$(arr[i]).hasClass("selected")){//选中的不需要重新更新样式
						cate = $(arr[i]).attr("index");
						if(eventCategory.hasOwnProperty(cate)){//有数据，字体变蓝
							//if(isInit){
								$(arr[i]).css("color","#2ea7e0");
							//}
							if(eventCategory[cate]>0){//有未读数据
								$(arr[i]).addClass("unread_event_value");
							}else{
								if($(arr[i]).hasClass("unread_event_value")){
									$(arr[i]).removeClass("unread_event_value");
								}
							}
						}else{//无数据，字体变黑
							//if(isInit){
							   $(arr[i]).css("color","#666666");
							//}
						}
					}
				}
				if(isInit){
					temp=$(arr[0]);
				}
			}else{
				toastr.info(result.resultMsg);
			}
		}
	});
}

/**
 * 获取详情
 */
function getDetails4sfws(judgmentId,eventId,_this){
	
	//将事件Id统计进去
    if(ignoreFlag==false){
    	//如果没有点击忽略全部 那么需要将点击详情的事件加进去 否则不加因为已经加入进去了重复添加
    
    	//step1 校验当前的事件是否是已经读取过的
    	if($(_this).hasClass("read_td")){
    		//return;
		}else{
			//step2 当前事件是未读状态 需要校验之前是否已经点击详情加入更新列表
			var arr= new Array(); //定义一数组 
			arr=eventIdListString.split(","); //字符分割 
			for (i=0;i<arr.length ;i++ ) 
			{ 
				if(arr[i]==eventId){
					//return;
				}else{
					if(""==eventIdListString){
			    		eventIdListString=eventId;
			    	}else{
			    		eventIdListString=eventIdListString+","+eventId;
			    	}
				}
			} 
			
				 //如果是全部的时候 读取一条数据需要将其颜色设置为灰色
				
				$(_this).addClass("read_td");
				$(_this).parent().addClass("read_row");
				var obj=$(_this).parent();
				var tempArr=$(obj).find("td");
				//$(tempArr[1]).removeClass("new_event");
			
			
		}
    }
    
    
	var parameter = {
			"judgmentId":judgmentId,
			"companyName":encodeURIComponent(companyName)
	}
	
	$.ajax({
		url: ctx + "/monitorRelationCompany/getMonitorDetails4Sfws.do",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			$("#judgmentTime4Sx").html(result.judgmentTime);
			$("#title4Sx").html(result.title);
			$("#details4Sx").html(result.details);
			
		}
	});
	
	
}

/**
 * 获取详情
 */
function getDetails(eventId,_this){
	
	//将事件Id统计进去
    if(ignoreFlag==false){
    	//如果没有点击忽略全部 那么需要将点击详情的事件加进去 否则不加因为已经加入进去了重复添加
    
    	//step1 校验当前的事件是否是已经读取过的
    	if($(_this).hasClass("read_td")){
    		//return;
		}else{
			//step2 当前事件是未读状态 需要校验之前是否已经点击详情加入更新列表
			var arr= new Array(); //定义一数组 
			arr=eventIdListString.split(","); //字符分割 
			for (i=0;i<arr.length ;i++ ) 
			{ 
				if(arr[i]==eventId){
					//return;
				}else{
					if(""==eventIdListString){
			    		eventIdListString=eventId;
			    	}else{
			    		eventIdListString=eventIdListString+","+eventId;
			    	}
				}
			} 
			
			
				 //如果是全部的时候 读取一条数据需要将其颜色设置为灰色
				$(_this).addClass("read_td");
				$(_this).parent().addClass("read_row");
				var obj=$(_this).parent();
				var tempArr=$(obj).find("td");
				//$(tempArr[1]).removeClass("new_event");
			
			
		}
    }
	var parameter = {
			"eventId":eventId,
			"eventSubType":$("#eventSubType").val()
	}
	
	$.ajax({
		url: ctx + "/monitorRelationCompany/getReaComEventDetails.do",
		type: "POST",
		data: parameter,
		dataType: "json",
		success: function(result){
			var obj=result.resultObj;
			var eventSubType=result.eventSubType;
			if(eventSubType=="19"||eventSubType=="20"||eventSubType=="18"){
				$("#judgmentTime4Sx").html(obj.judgmentTime);
				$("#title4Sx").html(obj.title);
				$("#details4Sx").html(obj.details);
			}else if(eventSubType=="24"||eventSubType=="25"){
				$("#publishDate4News ").html(obj.pubtime);
				$("#title4News").html(obj.title);
				$("#details4News").html(obj.content);
			}else if(eventSubType=="22"){
				$("#mc4Shixin").html(obj.mc);
				$("#dm4Shixin").html(obj.dm);
				$("#fddbr4Shixin").html(obj.fddbr);
				$("#fddbr4Shixin").html(obj.fddbr);
				$("#zxfy4Shixin").html(obj.zxfy);
				$("#sf4Shixin").html(obj.sf);
				$("#zxyjwh4Shixin").html(obj.zxyjwh);
				$("#sj4Shixin").html(obj.sj);
				$("#ah4Shixin").html(obj.ah);
				$("#zxyjdw4Shixin").html(obj.zxyjdw);
				$("#wsqdyw4Shixin").html(obj.wsqdyw);
				$("#lxqk4Shixin").html(obj.lxqk);
				$("#sxjtqk4Shixin").html(obj.sxjtqk);
				$("#fbsj4Shixin").html(obj.fbsj);
			}else if(eventSubType=="12"){
				$("#shiyaojian .shiyaojian").append(obj.description);
			}
		}
	});
	
	
}
 
/**
 * 颜色处理事件
 * @param _this
 */
function colorDeal4InNews(_this){
	$(_this).find(".newsDetails").css("color","#2ea7e0");
}


/**
 * 颜色处理事件
 * @param _this
 */
function colorDeal4OutNews(_this){
	$(_this).find(".newsDetails").css("color","#666666");
}

function bindHistoryInfo(){
	 $("#history_btn").click(function(){
		 var deep = $("#deep").val();
		 var companyName = $("#companyName").val();
		 
		 window.location.href=ctx +"/hisCompany/toHisCompanyView.do?companyName="+encodeURIComponent(companyName)+"&deep="+(parseInt(deep)+1);
	 });

}
//跳转到司法全景图
function toSiFaQuanJingtu(companyName){
	
	var deep = $("#deep").val();
	var companyName = $("#companyName").val();
	
	window.location.href = ctx +"/monitorComRisk/toSiFaQuanJingtuView.do?compName="+encodeURIComponent(companyName)+"&deep="+(parseInt(deep)+1);
}

function bindLegalPerson(){
	 $("#legal_person_btn").click(function(){
		 var companyName = $("#companyName").val();
		 
		 $.ajax({
			url: ctx +"/monitorComRisk/isHasFaDingDaiBiaoRen.do",  //
			type: "POST",
			data: {"companyName" : encodeURIComponent(companyName)},
          	beforeSend : function() {
   			//异步请求时spinner出现
   			//var target = $(".company_list").get(0);
   			//spinner.spin(target);
          	},
			dataType: "json",
			success: function(result){
				if(result.flag == true){//
					 var deep = $("#deep").val();
					 window.location.href=ctx +"/monitorComRisk/toFaDingDaiBiaoRenView.do?companyName="+encodeURIComponent(companyName)+"&deep="+(parseInt(deep)+1);
				}else{
					message_lap_deleteGroup.show("#noLegalPersonTip");
					$("#submitNoLegalPersonTip").click(function (){
						message_lap_deleteGroup.close();
						$(this).unbind("click");
					});
				}
			}
		});
	 });
}

//初始化checkBox
function initCheckBox(){
	//初始化的checkBox 新事件 严重 异常 一般
    $("input[name='newEve']").prop("checked",true);  
    $("input[name='typeList']").prop("checked",true);  
}

/**
 * 已读未读状态的处理
 */
function readStatusDeal(){
	 //更新状态
	 if($("input[name='newEve']").prop("checked")){  
		 //0表示未读
		 readStatus="0";
	 }else{
		 //1表示全部
		 readStatus="1";
	 }
	 
	 getRelationCompanyEventList();
}

/**
 * 事件类型的处理 一般 异常 严重
 */
function eventLevelDeal(_this){
	if($(_this).hasClass("event_unselector_btn")){
		$(_this).removeClass("event_unselector_btn");
	}else{
		$(_this).addClass("event_unselector_btn");
	}
	
	var arr=["3","2","1"];
	
	$(".event_unselector_btn").each(function(){
		RemoveByValue(arr,$(this).attr("level"));
	});
	
	if(arr.length<=0){
		typeListString="无";
	}else{
		typeListString=arr.join(",");
		getRelationCompanyEventList();  
	}
}

/**
 * 忽略全部
 */
function ignoreAll(){
	var parameter = {
		"monitorId": $("#id").val(),
		"companyName": encodeURIComponent($("#companyName").val()),
		"eventSubType": eventSubType,
		"infoShowDate": $("#infoShowDate").val()
	};
	
	$.ajax({
		url: ctx + "/monitorCompanyEvent/ignoreAllUnreadEventBySubType",
		type: "POST",
		data: parameter,
        beforeSend : function() {
			//异步请求时spinner出现
			var target = $(".monitor_events").get(0);
			spinner.spin(target);
	    },
		dataType: "json",
		success: function(result){
			//spinner.spin();
			if(result!=undefined&&result.count!=undefined&&parseInt(result.count)>0){
				eventIdListString="";getRelationCompanyEventList();
			}
		},
  		error: function(){
	  		  
  		},
  		complete: function(request, textStatus){
             //console.log("applyCompanyBizView compeleted");
  			spinner.spin();
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

/**
 * 已读事件的统计
 */
function readEventsTotalDeal(){
	//统计标志位
	var dealFlag=false;
	
	if(preEventSubType=="12" ||
			preEventSubType=="18" ||preEventSubType=="19"||preEventSubType=="20"||preEventSubType=="22"||
			preEventSubType=="24"||preEventSubType=="25"){
		if(ignoreFlag==true){
			dealFlag=true;
		}
	}else{
		dealFlag=true;
	}
	
	var arr4Id= new Array(); //定义一数组 
	arr4Id=eventIdListString.split(","); //字符分割 
	
	//获取当前页面所有的eventId
	var curIdArr= new Array(); 
	curIdArr=eventIdStr.split(","); //字符分割 
	
	var arr=$("#detailsTable").find(".tr_data");
	
	if(dealFlag==true){
		
		//有详情的维度处理
			for(var i=0;i<arr.length;i++){
				var obj=$(arr[i]);
				if(!obj.hasClass("read_row")){
					eveId=curIdArr[i];
					if(ignoreFlag==true){
						var addFlag=true;
						//如果是忽略全部,需要检测是否有重复的记录
						for (j=0;j<arr4Id.length ;j++ ){
							if(arr4Id[j]==eveId){
								addFlag=false;
								break;
							}
						} 
						if(addFlag==true){
							//没有重复的直接添加
							if(""==eventIdListString){
					    		eventIdListString=eveId;
					    	}else{
					    		eventIdListString=eventIdListString+","+eveId;
					    	}
						}
						
					}else{
						//没有重复的直接添加
						if(""==eventIdListString){
				    		eventIdListString=eveId;
				    	}else{
				    		eventIdListString=eventIdListString+","+eveId;
				    	}
					}
					
			}
		}
		
	}
}

