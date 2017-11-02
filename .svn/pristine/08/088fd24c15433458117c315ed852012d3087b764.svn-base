var controller;
var homeController=function(){
	var OverlapList={
		group_delete_overlap:null
	}

	var PageModel={
		CurrentGroup:null,
		GroupMoved:false,
		CurrentGroupIndex:1
	}

	var EventList={
			bind_overlap_cancel:function(){
				$("#deleteGroupWindow #cancel_message").click(function(){
					OverlapList.group_delete_overlap.close();
				})
				$("#deleteGroupWindow #del_message").click(function(){
					EventList.deleteMonitorGroup();
					OverlapList.group_delete_overlap.close();
				})
			},
			deleteMonitorGroupCallback:function(){
				var group=PageModel.CurrentGroup;
				// if(PageModel.GroupMoved){
				// 	var c_width=$(".user_group .group").outerWidth(true);
				// 	$(group).nextAll(".group").each(function(){
				// 		console.log(c_width);
				// 		$(this).animate({left:"-="+(c_width)+"px"});
				// 	})
				// }
				var group_len=$(".user_group .group").length;
				var group_index=$(".user_group .group").index(group)+1;
				$(group).remove();
				if(((group_len-group_index)<=4)&&(group_len>5)&&(group_index>5)){
					//右移
					var c_width=$(".user_group .group").outerWidth(true);
					PageModel.CurrentGroupIndex=EventList.group_prev(PageModel.CurrentGroupIndex,c_width);
				}
				if(group_len<=5){

					
					//补图片
					$(".user_group").append('<div class="default_user_group group inline_div"><img src="{0}/images/modules/index/user_default_group_back.png"></div>'.format(ctx));
				}
				
				if(group_len==6){
					$("#monitorGroupLeftArrow").empty();
					
					$("#monitorGroupRightArrow").empty();
				}
				

			},
			deleteMonitorGroup:function(){
				var groupId = $("#groupId").val();
				
				var parameter = {
					"groupId" : groupId
				}
				
				$.ajax({
					url: ctx + "/monitorGroup/deleteMonitorGroup",
					type: "POST",
					data: parameter,
					dataType: "json",
					success: function(result){
						// 批量删除后续处理
						if (result.resultCode == "0") {
							EventList.deleteMonitorGroupCallback();
							toastr.success(result.resultMsg);
						} else {
							toastr.error(result.resultMsg);
						}
					},
					error: function(){
						toastr.error('网络异常！');
					},
					complete: function(XMLHttpRequest, textStatus){
						
					}
				});
				
			},
			group_name_edit:function(_this){
				
				var group=$(_this).parents(".group");
			
				var groupId=$(group).find(".group_id").text();
				
				$(group).find(".group_name").editInput(function(value){
					//判断输入是否正确
					return updateMonitorGroup(groupId,value);
				},function(value){
					//用户输入结束触发事件
					$(group).find(".group_name .line_txt").text(value)
				},$(group).find(".group_name .line_txt").text());
			},
			group_next:function(current_group,c_width){
				var total=$(".user_group .group").length;
	            if(current_group<=total&&$(".user_group").width()<(c_width*(total-current_group+1)))
	            {
	            	EventList.group_next_move(c_width)
	                current_group++;
	            }
	            return current_group;
			},
			group_next_move:function(c_width){
				PageModel.GroupMoved=true;
				$(".user_group .group").each(function(){
                    $(this).animate({left:"-="+(c_width)+"px"});
                });
			},
			group_next_css:function(c_width){
				PageModel.GroupMoved=true;
				$(".user_group .group").each(function(){
                    $(this).css({left:"-="+(c_width)+"px"});
                });
			},
			group_prev:function(current_group,c_width){
				var total=$(".user_group .group").length;
	            if(current_group>1)
	            {
	            	EventList.group_prev_move(c_width)
	                current_group--;
	            }
	            return current_group;
			},
			group_prev_move:function(c_width){
				PageModel.GroupMoved=true;
				$(".user_group .group").each(function(){
                    $(this).animate({left:"+="+(c_width)+"px"});
                });
			},
			group_prev_css:function(c_width){
				PageModel.GroupMoved=true;
				$(".user_group .group").each(function(){
                    $(this).css({left:"+="+(c_width)+"px"});
                });
			},
			group_delete:function(_this){
				//1.弹出提示框
				var group=$(_this).parents(".group");
					PageModel.CurrentGroup=group;
				if(!OverlapList.group_delete_overlap){}
				var groupId=$(group).find(".group_id").text();
				$("#groupId").val(groupId);
				OverlapList.group_delete_overlap.show("#deleteGroupWindow");
			}
	}
	
    function risk_round(class_name,num,max){
        var radius=(num/max)*360;
        var round=0;
        var timer=window.setInterval(function(){
            if(round>radius)
            {
                clearInterval(timer)
            }
            else
            {
                round=round+5;
                round=round>360?360:round;
                radius_round("."+class_name,round);
            }
        }, 50);
    }
    function news_hidden(){
        var p_height=$(window).height() -$(".news_list_title").height()-$(".current_day").height()-$(".nav_head").height();
        var new_height=$(".news_line").outerHeight(true);
        var news_count=parseInt(p_height/new_height);
        
        if((p_height-news_count*new_height)>(0.6*new_height)){
            news_count++;
        }
        var index=0;
        $(".news_line").each(function(){
            index++;
            if(index>news_count)
            {
                $(this).addClass("hidden");
            }
        });
    }
    function radius_round(id,round){
        var rotatenum = round;
        $(id).find('.yuan_bl1').empty();
        $(id).find('.yuan_bl2').remove();
        if(round > 180){
            var blhtml = '';
            rotatenum = round - 180;
            blhtml += '<div class="yuan_bl2">';
            blhtml += '<div class="yuan_bl4" style="-webkit-transform:rotate(' + rotatenum + 'deg);transform:rotate(' + rotatenum + 'deg);"></div>';
            blhtml += '</div>';
            //$('.yuan_bl1').remove($('.yuan_bl3'));
            $(id).find('.yuan_bl1').after(blhtml);
        }else{
            var blhtml = '';
            blhtml += '<div class="yuan_bl3" style="-webkit-transform:rotate(' + rotatenum + 'deg);transform:rotate(' + rotatenum + 'deg);"></div>';
            $(id).find('.yuan_bl1').append(blhtml);
        }
    }
    var event_newApp_click=function(){
        $(".apply_result img").click(function(){
            if($(this).hasClass("gray")){
                $(this).removeClass("gray");
            }else{
                $(this).addClass("gray");
            }
        })
    }

    var event_group=function(){
       
        var c_width=$(".user_group .group").outerWidth(true);

        
        $(".prev_group").click(function(){
        	PageModel.CurrentGroupIndex=EventList.group_prev(PageModel.CurrentGroupIndex,c_width);
        });
        $(".next_group").click(function(){
        	PageModel.CurrentGroupIndex=EventList.group_next(PageModel.CurrentGroupIndex,c_width);
        });
        
        var remarkArr=[];
        $(".user_group .group").each(function(){
        	var _this=this;
        	remarkArr.push({
        		group_remark:$(_this).find(".group_remark").html(),
        		group_value:$(_this).find(".group_name").html(),
        		timeout:null,
        		interval:null
        	})
			$(this).find(".close_btn").click(function(){
				EventList.group_delete(this);
			});
			$(this).find(".group_name").click(function(){
				EventList.group_name_edit(this);
			});
        })
    }
    var event_new_group=function(){

        var edit_length=function(value){
            return !(value.length>4)
        }
        var lap=new overlap({
        scroller:{
            lock:false,
        },
        position:{
            type:"absolute",
            x_pos:$(".add_group .group").offset().left-80,
            y_pos:$(".add_group .group").offset().top+80
        },
        content:{
            style:{
                width:"292px",
                height:"263px",
                border:"16px solid rgba(0,0,0,0.15)"
            }
        }});
        $(".add_group .group .group_img").click(function(){
        	 $("#groupName").val("");
        	 $("#remark").val("");
        	
            lap.show(".new_group_edit");
        });
        $(".save_new_group").click(function(){
        	
        	var groupName = $("#groupName").val().trim();
        	var remark = $("#remark").val().trim();
        	
        	if(groupName == ""){
        		toastr.warning("请输入分组名称！");
        		
        		return false;
        	}else{
        		var parameter = {
        				"groupName" : groupName,
        				"remark": remark
        		}
        		
        		$.ajax({
        			url: ctx + "/monitorGroup/addMonitorGroup",
        			type: "POST",
        			data: parameter,
        			dataType: "json",
        			success: function(result){
        				if(result.resultCode == "0"){
        					//关闭新增窗口
        	        		lap.close();
        	        		
        		            toastr.success(result.resultMsg);
        	        		
        	        		//刷新动态监控列表	
        	             	refreshMonitorGroupList();
        				}else if(result.resultCode == "1"){
        					toastr.error(result.resultMsg);
        				}else{
        					toastr.warning(result.resultMsg);
        				}
        			},
        			error: function(){
        				toastr.error('网络异常！');
        			},
        			complete: function(XMLHttpRequest, textStatus){
        				
        			}
        		});
        	}
        });
        
        $(".new_group_edit .edit_btn .btn").click(function(){
            var value=$(".new_group_edit input").val();
            if(value.length>1&&edit_length(value)){
                var new_group=$(".default_group .group").clone();
                $(new_group).find(".group_name").text(value);
                $(new_group).find(".group_value").text(0);
                $(".user_group").append(new_group);
                if($(".user_group .group").length>1){
                    $(new_group).css("left",$(new_group).prev().css("left"));
                }
            }
            lap.close();
        });
        
    }
    var change_area_color=function(){
    	$(".monitor_range_list .list .li").each(function(){
    		var value=$(this).find(".num").text();
    		var num=value.substring(0,value.indexOf("%"));
    		var class_name="risk_back_color_6";
    		if(num>80){
    			class_name="risk_back_color_1";
    		}else if(num>60){
    			class_name="risk_back_color_2";
    		}else if(num>40){
    			class_name="risk_back_color_3";
    		}else if(num>20){
    			class_name="risk_back_color_4";
    		}else if(num>0){
    			class_name="risk_back_color_5";
    		}else{
    			class_name="risk_back_color_6";
    		}
    		$(this).find(".span").addClass(class_name);
    	})
    }
    
    var load_overlap=function(list,_this){
    	list.notice_lap=new overlap({
    		scroller:{
                lock:true
            },
            close:{
                show:true,
                type:"close_4",
                style:{
                	
                },
                callBack:function(){
                	var exp = new Date();
                    exp.setTime(exp.getTime() + (24*60*60*1000*365));
                	_this.page_cookie.set_cookie("NOTICE_INFO_COOKIE",_this.notice_info.version,exp.toGMTString());
                }
            },
            mask:{
                show:true,
                close:false,
                style:{
                    
                }
            },
            content:{
                style:{
                    width:"600px",
                    height:"352px",
                    border:"0px"
                }
            }
    	});
		OverlapList.group_delete_overlap=new overlap({
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
                 
             }
         },
         content:{
             style:{
                 width:"516px",
                 height:"200px",
                 border:"16px solid rgba(0,0,0,0.15)"
             }
        }});
    }
    
    var event_notice=function(overlap){
    	var notice_len=$("#NoticeBlockOverlap .notice_info").length;
    	var current_notice=1;
    	if(notice_len>1){
    		$("#NoticeBlockOverlap .next_notice").removeClass("hidden");
    		
    		$("#NoticeBlockOverlap .prev_notice").click(function(){
    			$("#NoticeBlockOverlap .next_notice").removeClass("hidden");
    			$("#NoticeBlockOverlap .notice_info").animate({"left":"+=600"});
    			if(--current_notice<=1){
        			current_notice=1;
        			$(this).addClass("hidden");
        		}
        	});
        	
        	$("#NoticeBlockOverlap .next_notice").click(function(){
        		$("#NoticeBlockOverlap .prev_notice").removeClass("hidden");
        		$("#NoticeBlockOverlap .notice_info").animate({"left":"-=600"});
        		if(notice_len<=++current_notice){
        			current_notice=notice_len;
        			$(this).addClass("hidden");
        		}
        	});
    	}
    }
    
    var event_blacklist=function(){
    	//黑名单点击事件
    	$("#link_blacklist").click(function(){
    		window.location.href = ctx+"/blacklistCompany/toBlacklistView.do?deep=1";
    	});
    }
    
    var show_notice_overlap=function(overlap){
    	overlap.show("#NoticeBlockOverlap");
    }
    
    var homeController=function(){
    	this.notice_info={
    			version:5,
    			images:[
    			        	ctx+"/images/modules/index/new_notice_v_1_5_1.png"
    			        ]
    	}
        this.echart_core=new echartController();
        this.page_cookie=new Cookie();
        this.overlap_list={};
        this.load();
        this.bind();
    }
    
    homeController.prototype={
        load:function(){
        	load_overlap(this.overlap_list,this);
            var data = {
                "totalNum":$("#totalCompanyNum").val(),
                "highRiskNum":$("#highRiskCompanyNum").val(),
                "middleRiskNum":$("#middleRiskCompanyNum").val(),
                "lowRiskNum":$("#lowRiskCompanyNum").val(),
                "noRiskNum":$("#noRiskCompanyNum").val(),
                "normalNum":$("#normalCompanyNum").val(),
                "attentionNum":$("#attentionCompanyNum").val(),
                "warnNum":$("#warnCompanyNum").val()
            };
            this.echart_core.monitor_events("monitor_events",data);
            
            this.echart_core.home_round("round_1",{name:'高风险',value:$("#highRiskCompanyNum").val(),total:$("#totalCompanyNum").val()==0?1:$("#totalCompanyNum").val(),color:'#e95353'});
            this.echart_core.home_round("round_2",{name:'中风险',value:$("#middleRiskCompanyNum").val(),total:$("#totalCompanyNum").val()==0?1:$("#totalCompanyNum").val(),color:'#fec414'});
            this.echart_core.home_round("round_3",{name:'高风险',value:$("#lowRiskCompanyNum").val(),total:$("#totalCompanyNum").val()==0?1:$("#totalCompanyNum").val(),color:'#1aa6fa'});
            this.echart_core.home_round("round_4",{name:'高风险',value:$("#noRiskCompanyNum").val(),total:$("#totalCompanyNum").val()==0?1:$("#totalCompanyNum").val(),color:'#1bcc84'});
            news_hidden();
            $(".calender_selector .select").click(function(){
     		   if($(this).hasClass(".selected")){
     			   return;
     		   }
     		   $(".calender_selector .selected").removeClass("selected");
     		   $(this).addClass("selected");
     	   })
     	   change_area_color();
            
        	for(var i=0;i<this.notice_info.images.length;i++){
    			var notice=$("<div class='inline_div notice_info notice_history'></div>");
    			notice.append("<img src="+this.notice_info.images[i]+"/>");
    			$("#NoticeBlockOverlap .notice_list").append(notice);
    		}
        },
        bind:function(){
            event_group();
            event_new_group();
            event_notice(this.overlap_list.notice_lap);
            event_blacklist();
			EventList.bind_overlap_cancel();
        },
        show_notice:function(){
			var _this=this;
        	var cookie_info=this.page_cookie.get_cookie("NOTICE_INFO_COOKIE")||0;
        	if(cookie_info=="NOTICED"){
        		cookie_info=0;
        	}
        	
        	if(parseInt(cookie_info)<this.notice_info.version){
				function getImageWidth(url,callback){
					var img = new Image();
					img.src = url;
					if(img.complete){
						callback(img.width, img.height);
					}else{
						img.onload = function(){
							callback(img.width, img.height);
						}
					}
				}
				var imgSrc=this.notice_info.images[0]
				getImageWidth(imgSrc,function(w,h){
					_this.overlap_list.notice_lap.reset({
						content:{
							style:{
								width:w,
								height:h
							}
						}
					})
				});
        		show_notice_overlap(this.overlap_list.notice_lap);
        	}
        },
        group_next:function(width){
        	PageModel.CurrentGroupIndex=EventList.group_next(PageModel.CurrentGroupIndex,width);
        },
        group_prev:function(width){
        	PageModel.CurrentGroupIndex=EventList.group_prev(PageModel.CurrentGroupIndex,width);
        },
        group_next_move:function(width,length){
        	EventList.group_next_move(width*length);
        	PageModel.CurrentGroupIndex += length;
        },
        group_prev_move:function(width,length){
        	EventList.group_prev_move(width*length);
        	PageModel.CurrentGroupIndex -= length;
        },
		group_delete:function(_this){
			EventList.group_delete(_this);
		},
		group_name_edit:function(_this){
			EventList.group_name_edit(_this);
		},
		group_move_reset:function(){
			PageModel.CurrentGroupIndex=1;
		}
    };
    homeController.prototype.constructor = homeController;
	return homeController;
}();

//查看棱镜动态
function viewArticleDetail(articleId){
	window.location.href = ctx + "/article/viewArticleDetail?articleId=" + articleId;
}

//跳转到动态监控公司列表页面
function toMonitorCompanyList(groupName,riskLevel,updateStatus,province,eventLevel,riskStatus,todayMonitorFlag){
	var deep = $("#deep").val();
	var accountId = $("#currentAccountId").val();
	
//	var params = {
//		"groupName": encodeURIComponent(groupName),
//		"riskLevel": riskLevel,
//		"updateStatus": updateStatus,
//		"province": encodeURIComponent(province),
//		"eventLevel": eventLevel,
//		"riskStatus":riskStatus
//	};
//	
//	httpPost(ctx + "/monitorCompany/toMonitorCompanyList", params);
	
	window.location.href = ctx + "/monitorCompany/toMonitorCompanyList?currentAccountId="+accountId+"&showGroupType=GROUP&groupName="
	+encodeURIComponent(groupName)+"&riskLevel="+riskLevel+"&updateStatus="+updateStatus+"&province="
	+encodeURIComponent(province)+"&eventLevel="+eventLevel+"&riskStatus="+riskStatus+"&todayMonitorFlag="+todayMonitorFlag+"&deep="+(parseInt(deep)+1);
}

//跳转到企业动态监控详情页面
function toCompanyDetail(monitorId){
	if(!monitorId == ""){
		var deep = $("#deep").val();
		window.location.href = ctx +"/monitorCompany/viewMonitorCompanyDetail?monitorId="+monitorId+"&deep="+(parseInt(deep)+1);
	}
}

//刷新动态监控分组
function refreshMonitorGroupList(){
	var blankGroup = '<div class="default_user_group group inline_div"><img src="' + ctx + '/images/modules/index/user_default_group_back.png"/></div>';
	
	var blankGroups = '';
	
	var monitorGroup = '';
	
	var monitorGroupList = '';
	
	$.ajax({
		url: ctx + "/monitorGroup/queryMonitorGroupListByAccountExceptAll",
		type: "POST",
		dataType: "json",
		success: function(result){
			$("#monitorGroupList").html("");
			
			for(var i=0;i<result.length;i++){
				monitorGroup = '<div class="group inline_div">'
							   +'<div class="group_id hidden">' + result[i].groupID + '</div>'
					           +'<div class="close_block">'
					           +'<div class="relative_right_block">'
					           +'<div class="close_btn inline_div">&times;</div>'
					           +'</div>'
					           +'<img src="' + ctx + '/images/modules/monitor/user_row.png" />'
					           +'</div>'
					           +'<div>'
					           +'<div class="group_img" onclick="toMonitorCompanyList(\'' + result[i].groupName+'\',\'\',\'\',\'\',\'\',\'\',\'\')">'
					           +'<img src="' + ctx + '/images/modules/monitor/user_group.png" />'
					           +'</div>'
					           +'<div class="group_info low_risk_back">'
					           +'<span class="group_name inline_div" style="max-width:108px;overflow:hidden;"><span class="inline_div line_txt" style="max-width:56px;text-align:center;top:-1px;positioin:relative;">' + result[i].groupName + '</span>('
					           +'<span class="group_value">';
				
				if(result[i].companyNum > 999){
					monitorGroup = monitorGroup + '999+';
				}else{
					monitorGroup = monitorGroup + result[i].companyNum;
				}
				
				monitorGroup = monitorGroup
					           +'</span>)</span>'
					           +'<div class="group_remark hidden">' + result[i].remark + '</div>'
					           +'</div>'
					           +'</div>'
					           +'</div>';
				
				monitorGroupList += monitorGroup;
			}
			
			if(result.length == 0){
				$("#monitorGroupLeftArrow").html("");
				
				$("#monitorGroupRightArrow").html("");
				
				blankGroups = blankGroup + blankGroup + blankGroup + blankGroup + blankGroup;
				
				$("#monitorGroupList").html(blankGroups);
			}else if(result.length == 1){
				$("#monitorGroupLeftArrow").html("");
				
				$("#monitorGroupRightArrow").html("");
				
				blankGroups = blankGroup + blankGroup + blankGroup + blankGroup;
				
				$("#monitorGroupList").html(monitorGroupList + blankGroups);
			}else if(result.length == 2){
				$("#monitorGroupLeftArrow").html("");
				
				$("#monitorGroupRightArrow").html("");
				
				blankGroups = blankGroup + blankGroup + blankGroup;
				
				$("#monitorGroupList").html(monitorGroupList + blankGroups);
			}else if(result.length == 3){
				$("#monitorGroupLeftArrow").html("");
				
				$("#monitorGroupRightArrow").html("");
				
				blankGroups = blankGroup + blankGroup;
				
				$("#monitorGroupList").html(monitorGroupList + blankGroups);
			}else if(result.length == 4){
				$("#monitorGroupLeftArrow").html("");
				
				$("#monitorGroupRightArrow").html("");
				
				blankGroups = blankGroup;
				
				$("#monitorGroupList").html(monitorGroupList + blankGroups);
			}else if(result.length == 5){
				$("#monitorGroupLeftArrow").html("");
				
				$("#monitorGroupRightArrow").html("");
				
				$("#monitorGroupList").html(monitorGroupList + blankGroups);
			}else{
				$("#monitorGroupLeftArrow").html(
					'<div class="prev_group">'
					+'<div class="prev_group_hover_block inline_div">'
					+'<img class="prev_group_img hidden" src="' + ctx + '/images/modules/monitor/prev_group.png" />'
					+'<img class="prev_group_hover_img hidden" style="display:none;" src="' + ctx + '/images/modules/monitor/prev_group_hover.png" />'
					+'</div></div>'
				);
				
				$("#monitorGroupRightArrow").html(
					'<div class="next_group">'
					+'<div class="next_group_hover_block inline_div">'
					+'<img class="next_group_img hidden" src="' + ctx + '/images/modules/monitor/next_group.png" />'
					+'<img class="next_group_hover_img hidden" style="display:none" src="' + ctx + '/images/modules/monitor/next_group_hover.png" />'
					+'</div></div>'
				);
				
				$("#monitorGroupList").html(monitorGroupList);
				
				var c_width=$(".user_group .group").outerWidth(true);
		        controller.group_move_reset();
		        
		        $(".prev_group").click(function(){
		             controller.group_prev(c_width);
		        });
		        $(".next_group").click(function(){
		            controller.group_next(c_width);
		        });
		        
		        //向右移动显示新加的分组
		        if(result.length > 5){
		        	controller.group_next_move(c_width,result.length-5);
		            
		        }
			}
			
			var remarkArr=[];
			
	        $(".user_group .group").each(function(){
	        	var _this=this;
	        	remarkArr.push({
	        		group_remark:$(_this).find(".group_remark").html(),
	        		group_value:$(_this).find(".group_name").html(),
	        		timeout:null,
	        		interval:null
	        	})
				console.log($(_this).find(".close_btn").length);
				$(_this).find(".close_btn").click(function(){
					controller.group_delete(this);
				});
				$(_this).find(".group_name").click(function(){
					controller.group_name_edit(this);
				});
	        });
		},
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
}

//修改动态监控分组名称
function updateMonitorGroup(groupId,groupName){
	var resultFlag = false;
	
	if("" == groupName.trim()){
		toastr.warning("分组名称不能为空！");
	}else{
		var parameter = {
			"groupId" : groupId,
			"groupName" : groupName
		}
		
		$.ajax({
			url: ctx + "/monitorGroup/updateMonitorGroup",
			type: "POST",
			async: false,
			data: parameter,
			dataType: "json",
			success: function(result){
				if ("0" == result.resultCode) {
					resultFlag = true;
				}else{
					toastr.error(result.resultMsg);
				}
			},
			error: function(){
				toastr.error('网络异常！');
			},
			complete: function(XMLHttpRequest, textStatus){
				
			}
		});
	}
	
	return resultFlag;
}

//初始化高风险公司区域分布图
function initDistributionChartOfHighRiskCompany(){
	
	$.ajax({
		url: ctx + "/monitorCompany/getDistributionOfHighRiskCompany",
		type: "POST",
		data: {},
		dataType: "json",
		success: function(result){
			var echart_core = new echartController();
			
			echart_core.monitor_area("monitor_area",result);
			init_monitor_area_level();
		},
		error: function(){
			toastr.error('网络异常！');
		},
		complete: function(XMLHttpRequest, textStatus){
			
		}
	});
	
}

function init_monitor_area_level() {
    $(".monitor_range_list .list .li").each(function () {
        var value = $(this).find(".num").text();
        var num = value.substring(0, value.indexOf("%"));
        num = num * 0.01;
        var r = 224 + (0 - 224) * num;
        var g = 255 + (110 - 255) * num;
        var b = 255 + (221 - 255) * num;
        $(this).find(".span").css("background", "rgb({0},{1},{2})".format(r.toFixed(0), g.toFixed(0), b.toFixed(0)));
    })
}