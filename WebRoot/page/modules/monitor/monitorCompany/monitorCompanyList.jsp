<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<%@ include file="/page/common/base.jsp"%>
		<%@ include file="/page/common/header.jsp"%>
		<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
		
		<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/events.css?${appVersion }"/>
		<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/filter.css?${appVersion }"/>
	
       	<script type="text/javascript" src="${ctx }/js/modules/monitor/filter.js?${appVersion }"></script>
       	<script type="text/javascript" src="${ctx }/js/common/loadwaiting.js?${appVersion }"></script>
       	<script type="text/javascript" src="${ctx }/js/utils/areaUtils.js?${appVersion }"></script>
       	<script type="text/javascript" src="${ctx }/js/modules/monitor/label.js?${appVersion }"></script>
	   	<script type="text/javascript" src="${ctx }/js/jquery/jquery.city.select.min.js?${appVersion }"></script>
       	<script type="text/javascript" src="${ctx }/js/modules/monitor/company_relationship.js?${appVersion }"></script>
       	<script type="text/javascript" src="${ctx }/js/modules/monitor/monitorCompanyList.js?${appVersion }"></script>
       	<script type="text/javascript" src="${ctx }/js/modules/monitor/export_batch.js?${appVersion }"></script>
       	<script type="text/javascript" src="${ctx }/js/modules/monitor/import_batch.js?${appVersion }"></script>       
	</head>

	<body class="ljzx_page back_gray">
		<input type="hidden" id="riskLevel" value="${riskLevel}" />
		<input type="hidden" id="eventLevel" value="${eventLevel}" />
		<input type="hidden" id="groupName1" value="${groupName}" />
		<input type="hidden" id="updateStatus" value="${updateStatus}" />
		<input type="hidden" id="province" value="${province}" />
		<input type="hidden" id="riskStatus" value="${riskStatus}" />
		<input type="hidden" id="updateStatus4Back" value="${updateStatus}" />
		<input type="hidden" id="riskLevel4Back" value="${riskLevel}" />
		<input type="hidden" id="eventLevel4Back" value="${eventLevel}" />
		<input type="hidden" id="companyName4Back" value="${companyName}" />
		<input type="hidden" id="currentPageNo4Back" value="${currentPageNo}" />
		<input type="hidden" id="pageSize4Back" value="${pageSize}" />
		<input type="hidden" id="riskStatus4Back" value="${riskStatus}" />
		<input type="hidden" id="flag4Back" value="${flag}" />
		<input type="hidden" id="move_index4Back" value="${move_index}" />
		<input type="hidden" id="groupName4Back" value="${groupName}" />
		<input type="hidden" id="steps4Back" value="${steps}" />
		<input type="hidden" id="dueDate4Back" value="${dueDate}" />
		<input type="hidden" id="beginDate4Back" value="${beginDate}" />
		<input type="hidden" id="key4Back" value="${key}" />
		<input type="hidden" id="label4Back" value="${label}" />
		<input type="hidden" id="area4Back" value="${area}" />
		<input type="hidden" id="extendFlag4Back" value="${extendFlag}" />
		<input type="hidden" id="groupStatus4Back" value="${groupStatus}" />
		<input type="hidden" id="deep" value="${deep }" />
		<input type="hidden" id="todayMonitorFlag" value="${todayMonitorFlag }" />
		<input type="hidden" id="showGroupType" value="${showGroupType }" />
   		<input type="hidden" id="currentAccountId" value="${currentAccountId }" />
   		<input type="hidden" id="showActionBtnFlag" value="${showActionBtnFlag }" />
   		<!-- 引入头部 -->
   		<jsp:include page="/page/modules/index/banner.jsp"/>
    	<div class="body_content_5">
        	<div class="body_center ">
            	<div class="body_block_2 block_shadow">
	                <div class="nav_bar">
	                    <div class="back_block">
	                        <div class="inline_div">
	                            <div class="for_back_btn" onclick="window.history.back()"></div>
	                        </div>
	                    </div>
	                    <span><a href="${ctx }/index">首页</a></span>
	                    <span>></span>
	                    <span class="current_nav">监控企业列表</span>
	                </div>
                	<div class="filter_title" id="groupName"></div>
	                <div class="filter_hidden_block">
	                    <div class="filter_hidden_btn">
	                        <div class="img_btn_3 filter_open_btn inline_div">
	                        	筛选条件<img src="${ctx }/images/common/down.png" />
	                        </div>
	                        <div class="img_btn_3 filter_close_btn hidden inline_div">
	                           	 收起筛选<img src="${ctx }/images/common/up.png" />
	                        </div>
	                    </div>
	                </div>
                	<div class="monitor_event_filter">
                    	<div class="filter_block">
                    		<div class="relative_right_block clear_txt_block" id="clearBtn">
        						<div class="inline_div clear_txt">清空</div>
        					</div>
                        	<div class="filter_data">
                            	<div class="filter_name inline_div option_text">风险状况</div>
                            	<div class="filter_options inline_div">
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name selected inline_div" id="riskLevel4All"><span class="option">全部</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">特别预警</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">一般预警</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">关注</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">正常</span></div>
	                                </div>
                            	</div>
                       		</div>
	                        <div class="filter_data">
	                            <div class="filter_name inline_div option_text">风险变化</div>
	                            <div class="filter_options inline_div">
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name selected inline_div" id="riskStatus4All"><span class="option">全部</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">升高</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">降低</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">不变</span></div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="filter_data">
	                            <div class="filter_name inline_div option_text">风险事件</div>
	                            <div class="filter_options inline_div">
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name selected inline_div" id="eventLevel4All"><span class="option">全部</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">严重</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">异常</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">一般</span></div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="filter_data">
	                            <div class="filter_name inline_div option_text">分组状况</div>
	                            <div class="filter_options inline_div">
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name selected inline_div" id="groupStatus4All"><span class="option">全部</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">已分组</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">未分组</span></div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="filter_data">
	                            <div class="filter_name inline_div option_text">事件状态</div>
	                            <div class="filter_options inline_div">
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name selected inline_div" id="updateStatus4All"><span class="option">全部</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">有</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">无</span></div>
	                                </div>
	                            </div>
	                        </div>
	                        <div class="filter_data">
	                            <div class="filter_name inline_div option_text">风险自定义</div>
	                            <div class="filter_options inline_div">
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name selected inline_div" id="customRiskFlag"><span class="option">全部</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">已定义</span></div>
	                                </div>
	                                <div class="option_block inline_div">
	                                    <div class="option_text option_name inline_div"><span class="option">未定义</span></div>
	                                </div>
	                            </div>
	                        </div>
	                        <%-- <div class="filter_data">
	                            <div class="filter_name inline_div option_text">监控时间</div>
	                            <div class="filter_options inline_div">
	                                <div class="option_block inline_div">
	                                    <div class="option_time option_time inline_div">
	                                        <div class="time_png inline_div">
	                                             <img class="inline_div" src="${ctx }/images/common/time_png.png" />
	                                        </div><input class="Wdate" id="beginDate" placeholder="开始时间" type="text" style="width:90px;background:#ffffff;"  onfocus="WdatePicker({onpicking:	function(dp){beginDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){beginDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true,maxDate:'#F{$dp.$D(\'dueDate\')}'})"/> 
	                                    </div><span class="time_span inline_div">
	                                    
	                                    </span>
	                                    <div class="option_time option_time inline_div">
	                                        <div class="time_png inline_div">
	                                             <img class="inline_div" src="${ctx }/images/common/time_png.png" />
	                                        </div><input class="Wdate" id="dueDate"   placeholder="结束时间"  type="text" style="width:90px;background:#ffffff;"  onfocus="WdatePicker({onpicking:	function(dp){dueDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){dueDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'beginDate\')}'})"/> 
	                                    </div>
	                                </div>
	                            </div>
	                        </div> --%>
                        	<div class="filter_data">
						  		<div class="filter_name inline_div option_text">区域选择</div>
	                            <div class="filter_options inline_div" id="areaOption">
	                                <div class="option_block inline_div">
	                                    <div id="area_drop" class="option_time option_drop inline_div">
	                                        <input class="inline_div drop_input" placeholder="全部区域" readonly="readonly" type="text" /><div
	                                            class="inline_div img_click"></div>
	                                    </div>
	                      	       </div>
	                    	   </div>
	                    	   <div class="filter_options inline_div">
	                    	   		<div id="area_selected_block" class="option_block inline_div" style="">
	                                    
	                      	       </div>
	                    	   </div>
							</div>
			                <div class="filter_data">
								<div class="filter_name inline_div option_text">风险标签</div>
	                            	<div class="filter_options inline_div" id="tagOption">
	                                	<div class="option_block inline_div">
	                                    <div id="span_drop" class="option_time option_drop inline_div">
	                                    	<input class="inline_div drop_input" placeholder="风险标签" readonly="readonly" type="text" /><div
	                                            class="inline_div img_click"></div>
	                            	    </div>
	                      	       </div>
	                    	   	</div>
	                    	   	<div class="filter_options inline_div">
									<div id="risk_selected_block" class="option_block inline_div" style=""></div>
	                    		</div>
		                    </div>
            			</div>
        			</div>
			        <div class="event_table_block">
			        	<div class="event_left_operate"></div>
			            <div class="event_operate" style="height: 24px;">
			            	<div class="inline_div search_btn_block"  style="float:left;">
			                	<div class="search_block">
			                    	<input type="text" class="search_input" placeholder="输入企业名" id="searchInfo" /><div class="search_btn inline_div" id="searchBtn"></div>
			                    </div>
			                </div>
			                
			                <div class="inline_div filter_close_btn2 btn_2_block hidden">
			                    <div class="inline_div  img_btn_4 group_option_img" style="background:url(${ctx }/images/modules/monitor/group_option_hover.png) 10px 6px no-repeat;background-color:#2ea7e0;color:#ffffff;">
			                        <span class="">完成</span>
			                    </div>
			                </div>
			                <c:if test="${showActionBtnFlag eq true }">
				                <div class="inline_div btn_2_block" id="importBtn">
				                    <div id="import_company_btn" class="inline_div img_btn_4 export_out_img">
				                        <span class="">批量导入</span>
				                    </div>
				                </div>
				                
			                </c:if>
			                <div class="inline_div btn_2_block" id="export_company_btn" onClick="exportCompanyBtnClickedFunction()">
			                    <div class="inline_div img_btn_4 export_in_img">
			                        <span class="">导出</span>
			                    </div>
			                </div>
			            </div>
			            <div class="tab_list">
			                <div class="relative_right_block hidden">
			                	<div class="page_line_block" style="padding:5px 0px;">
			                		<select style="border:1px solid #d7d7d7;">
			                			<option value="10" selected="selected">10</option>
			                			<option value="20">20</option>
			                			<option value="50">50</option>
			                		</select>
			                	</div>
			                </div>
			            	<div class="tab_group_list inline_div">
							</div>
							<div class="tab inline_div new_group" style="">
			                    <div class="inline_div tab_value">
			                        <div class="inline_div add_group_btn"></div><div class="inline_div">新增</div>
			                    </div> 
			                    <div class="left_tab_block hidden">
			                        <div class="left_tab"></div>
			                        <div class="left_tab_border"></div>
			                    </div>
			                </div>
			                <div class="move_tab_list inline_div hidden">
			                	<div class="more_span inline_div">
			                		<div class="inline_div pot_span"></div>
			                		<div class="inline_div pot_span"></div>
			                		<div class="inline_div pot_span"></div>
			                	</div>
			                	<div class="left_move inline_div"></div>
			                	<div class="right_move inline_div"></div>
			                </div>
			            </div>
			            <div>
			            	<div class="group_table">
			                	<div id="Searchresult"></div>
						  	</div>                                   	  
			            </div>
			            <c:if test="${showActionBtnFlag eq true }">
				            <div class="table_options_list">
				            	<div class="inline_div btn_2_block cancel_monitor ${groupName eq '全部' ? '' : 'hidden'}"  id="cancelMonitor">
				                    <div class="inline_div   btn_5">
				                        <span class="inline_div">取消监控</span>
				                    </div>
				                </div>
				                <div class="inline_div btn_2_block batch_delete_company ${groupName eq '全部' ? 'hidden' : ''} " id="batchDelete" >
				                    <div class="inline_div   btn_5">
				                        <span class="inline_div">删除</span>
				                    </div>
				                </div>
				            	<div class="inline_div btn_2_block updateGroupName ">
				                    <div class="inline_div  btn_5">
				                        <span class="inline_div">更改组名</span>
				                    </div>
				                </div>
				                <div class="inline_div btn_2_block batch_modify">
				                    <div class="inline_div btn_5">
				                        <span class="inline_div">修改分组</span>
				                    </div>
				                </div>
				            </div>
			            </c:if>
			            <div class="page_block">
			               <div id="Pagination"></div>
			            </div>
			        </div>
    			</div>
    		</div>
    	</div>
	    <div class="new_group_overlap edit_block hidden">
	        <div class="edit_title">新建分组</div>
	        <div class="option_list">
	            <div class="inline_div option_title">组名:</div>
	            <div class="inline_div option_value">
	                <div class="option_text">
	                    <input type="text" placeholder="输入组名" id="name"/>
	                </div>
	            </div>
	        </div>
	        <div class="option_list">
	            <div class="inline_div option_title">描述:</div>
	            <div class="inline_div option_value">
	                <div class="option_area">
	                    <textarea id="description"></textarea>
	                </div>
	            </div>
	        </div>
	        <div class="btn_block">
	            <div class="inline_div small_btn save_new_group">保存</div>
	        </div>
	    </div>
    
	    <div class="update_group_overlap edit_block hidden">
	        <div class="edit_title">更改组名</div>
	        <div class="option_list">
	            <div class="inline_div option_title">组名:</div>
	            <div class="inline_div option_value">
	                <div class="option_text">
	                    <input type="text" placeholder="输入组名" id="newName"/>
	                </div>
	            </div>
	        </div>
	        <div class="option_list">
	            <div class="inline_div option_title">描述:</div>
	            <div class="inline_div option_value">
	                <div class="option_area">
	                    <textarea id="newDescription"></textarea>
	                </div>
	            </div>
	        </div>
	        <div class="btn_block">
	            <div class="inline_div small_btn update_group">保存</div>
	        </div>
	    </div>
	    <div class="select_group_overlap edit_block hidden">
	        <div class="edit_title">选择分组</div>
	        <div class="option_list">
	            <div class="inline_div option_title">分组:</div>
	            <div class="inline_div option_value option_check_value"></div>
	        </div>
	        <div class="btn_block">
	            <div class="inline_div small_btn_2 new_group">
	                <div>新建分组</div>
	            </div>
	            <div class="inline_div small_btn" id="addBtn">添加</div>
	        </div>
	    </div>
	    <div class="import_company_list_over_lap hidden">
			<div class="import_title"><span id="selectedGroup"></span></div>
	        <div class="import_path">
	           <div class="" style="text-align: left;margin-bottom:7px;"><span style="margin-left:110px;color:#000;">上传EXCEL文件</span></div>
	           <form id="uploadForm">
		            <div class="inline_div">
		                <input type="text" id="filePath" name="filePath" readonly="readonly" />
		            </div><div class="inline_div">
		                <div id=browseFileBtn class="inline_div btn_4">浏览</div>
		            </div>
		            <div class="inline_div">
		                 <div id="import_company_list_btn" class="inline_div btn_4_color">确定</div>
		            </div>
		            <input type="file" id="fileInput" name="fileInput" style="display: none" accept="application/vnd.ms-excel,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" />
	            </form>
	        </div>
			<div class="import_message">
	            <div>
	                <img src="${ctx }/images/modules/monitor/import_message_icon.png"/>
	            </div>
	            <div>
	                <div class="message_title message">上传文件要求</div>
	                <div class="message">1.仅支持Excel格式，请下载模板后填写;<span class="more_btn" onClick="downloadTemplate()">模板下载</span></div>
	                <div class="message">2.每次上传上限为100家，100家以外不做处理;</div>
	                <div class="message">3.仅支持企业名称精准匹配的公司导入。</div>
	            </div>
	        </div>
	    </div>

	    <div class="import_result_overlap hidden">
	        <div class="result_title">
	            <img src="${ctx }/images/modules/monitor/import_result_title.png"/>
	        </div>
	        <div class="result_title_value ">
	            <img src="${ctx }/images/modules/monitor/import_result_ok.png"/>
	            <span class="inline_div value">
	            	已成功添加<span id="successNum">50</span>家公司！
	            </span>
	        </div>
	        <div id="canMonitorMsg" class="result_table_block" style="margin-top: 0px;"></div>
	        <div class="result_table_block">
	            <div class="result_failed_message">
	                <img src="${ctx }/images/modules/monitor/import_result_message.png"/>
	                <span class="inline_div value">
	                	添加失败（<span  id="failedNum">3</span>家）
	                </span>
	            </div>
	            <div style="max-height:180px;overflow-y:auto;border-bottom:1px solid #cccccc">
		            <table class="import_result_table first_col_table" style="border-bottom:0px;">
		                <thead>
		                    <tr>
		                        <td>公司名称</td>
		                        <td>失败原因</td>
		                    </tr>
		                </thead>
		                <tbody id="failedTbody"></tbody>
		            </table>
	            </div>
	        </div>
	        <div class="export_error_company">
	            <div class="export_error_company_btn inline_div btn">导出失败公司 </div>
	        </div>
	    </div>
    
		<div class="area_overlap drop_overlap hidden">
	        <div class="mult_select">
	        	<div class="relative_right_block clear_txt_block">
	        		<div class="inline_div clear_txt"></div>
	        	</div>
	            <div class="">支持多选</div>
	        </div>
	        <div class="selector_block">
	            <div class="selector" ><div class="inline_div selector selected" id="allArea" index="0">全选</div></div>
	            <div class="selector inline_div" index="1"><span class="province">安徽省</span></div>
	            <div class="selector inline_div" index="2"><span class="province">北京市</span></div>
	            <div class="selector inline_div" index="3"><span class="province">福建省</span></div>
	            <div class="selector inline_div" index="4"><span class="province">甘肃省</span></div>
	            <div class="selector inline_div" index="5"><span class="province">广东省</span></div>
	            <div class="selector inline_div" index="6"><span class="province">广西壮族自治区</span></div>
	            <div class="selector inline_div" index="7"><span class="province">贵州省</span></div>
	            <div class="selector inline_div" index="8"><span class="province">海南省</span></div>
	            <div class="selector inline_div" index="9"><span class="province">河北省</span></div>
	            <div class="selector inline_div" index="10"><span class="province">河南省</span></div>
	            <div class="selector inline_div" index="11"><span class="province">黑龙江省</span></div>
	            <div class="selector inline_div" index="12"><span class="province">湖北省</span></div>
	            <div class="selector inline_div" index="13"><span class="province">湖南省</span></div>
	            <div class="selector inline_div" index="14"><span class="province">吉林省</span></div>
	            <div class="selector inline_div" index="15"><span class="province">江苏省</span></div>
	            <div class="selector inline_div" index="16"><span class="province">江西省</span></div>
	            <div class="selector inline_div" index="17"><span class="province">辽宁省</span></div>
	            <div class="selector inline_div" index="18"><span class="province">内蒙古自治区</span></div>
	            <div class="selector inline_div" index="19"><span class="province">宁夏回族自治区</span></div>
	            <div class="selector inline_div" index="20"><span class="province">青海省</span></div>
	            <div class="selector inline_div" index="21"><span class="province">山东省</span></div>
	            <div class="selector inline_div" index="22"><span class="province">山西省</span></div>
	            <div class="selector inline_div" index="23"><span class="province">陕西省</span></div>
	            <div class="selector inline_div" index="24"><span class="province">上海市</span></div>
	            <div class="selector inline_div" index="25"><span class="province">四川省</span></div>
	            <div class="selector inline_div" index="26"><span class="province">天津市</span></div>
	            <div class="selector inline_div" index="27"><span class="province">西藏自治区</span></div>
	            <div class="selector inline_div" index="28"><span class="province">新疆维吾尔自治区</span></div>
	            <div class="selector inline_div" index="29"><span class="province">云南省</span></div>
	            <div class="selector inline_div" index="30"><span class="province">浙江省</span></div>
	            <div class="selector inline_div" index="31"><span class="province">重庆市</span></div>
	        </div>
	        <div class="btn_block">
	            <div class="btn inline_div"  id="area_select_btn">确定</div>
	        </div>
	    </div>
	    <div class="span_overlap drop_overlap hidden">
	        <div class="mult_select">
	        	<div class="relative_right_block clear_txt_block">
	        		<div class="inline_div clear_txt"></div>
	        	</div>
	            <div class="">支持多选</div>
	        </div>
	        <div class="selector_block">
	            <div class="selector" ><div class="inline_div selector selected" id="allLabel" index="0">全选</div></div>
	            <div class="selector inline_div" index="1"><span class="label">法人代表变更</span></div>
	            <div class="selector inline_div" index="2"><span class="label">频繁变更法人代表</span></div>
	            <div class="selector inline_div" index="3"><span class="label">股东变更</span></div>
	            <div class="selector inline_div" index="4"><span class="label">频繁变更股东</span></div>
	            <div class="selector inline_div" index="5"><span class="label">高管变更</span></div>
	            <div class="selector inline_div" index="6"><span class="label">频繁变更高管</span></div>
	            <div class="selector inline_div" index="7"><span class="label">经营异常</span></div>
	            <div class="selector inline_div" index="8"><span class="label">企业更名</span></div>
	            <div class="selector inline_div" index="9"><span class="label">企业吊销注销</span></div>
	            <div class="selector inline_div" index="10"><span class="label">对外扩张</span></div>
	            <div class="selector inline_div" index="11"><span class="label">新增对外投资</span></div>
				<div class="selector inline_div" index="12"><span class="label">新增对外融资</span></div>
	            <div class="selector inline_div" index="13"><span class="label">资产冻结</span></div>
	            <div class="selector inline_div" index="14"><span class="label">行政处罚</span></div>
	            <div class="selector inline_div" index="15"><span class="label">欠税</span></div>
	            <div class="selector inline_div" index="16"><span class="label">税务信息非正常</span></div>
	            <div class="selector inline_div" index="17"><span class="label">涉诉</span></div>
	            <div class="selector inline_div" index="18"><span class="label">舆情动态</span></div>
	            <div class="selector inline_div" index="19"><span class="label">重点关注舆情</span></div>
	            <div class="selector inline_div" index="20"><span class="label">新增专利</span></div>
	           	<div class="selector inline_div" index="21"><span class="label">新增招投标</span></div>
	           	<div class="selector inline_div" index="22"><span class="label">新增商标</span></div>
	<!--        <div class="selector inline_div" index="23"><span class="label">疑似个人涉诉</span></div> -->
	<!--        <div class="selector inline_div" index="24"><span class="label">个人涉诉</span></div> -->
	            <div class="selector inline_div" index="25"><span class="label">风险传导</span></div>
	            <div class="selector inline_div" index="26"><span class="label">关系事件</span></div>
	            <div class="selector inline_div" index="27"><span class="label">经营预警</span></div>
	            <div class="selector inline_div" index="28"><span class="label">信用预警</span></div>
	            <div class="selector inline_div" index="29"><span class="label">财务预警</span></div>
	            <div class="selector inline_div" index="30"><span class="label">高管预警</span></div>
	            <div class="selector inline_div" index="31"><span class="label">失信</span></div>
	            <div class="selector inline_div" index="32"><span class="label">财务状况不佳</span></div>
	        </div>
	        <div class="btn_block">
	            <div class="btn inline_div"  id="span_select_btn">确定</div>
	        </div>
	    </div>
	    <div class="deltips_overlap message_overlap hidden" id="deleteGroupWindow">
	        <div class="message_title_block">
	            <div class="title">提示</div>
	        </div>
	        <div class="message_content_block">
	            <div class="content">
	                <div class="inline_div content_img tips_img"></div>
	                <div class="inline_div">是否确定删除该组？</div>
	            </div>
	        </div>
	        <div class="message_btn_block">
	            <div id="cancel_message" class="inline_div small_overlap_btn_1">
	                <div>取消</div>
	            </div>
	            <div id="del_message" class="inline_div small_overlap_btn">删除</div>
	        </div>
	    </div>
		<div class="deltips_overlap message_overlap hidden" id="cancelMonitorWindow">
	    	<div class="message_title_block">
	            <div class="title">提示</div>
	        </div>
	        <div class="message_content_block">
	            <div class="content">
	                <div class="inline_div content_img tips_img"></div>
	                <div class="inline_div">该企业已确认加入监控，如取消监控，将占用当月监控企业数量额度。</div>
	            </div>
	        </div>
			<div class="message_btn_block">
	            <div id="cancel_message2" class="inline_div small_overlap_btn_1">
	                <div>取消</div>
	            </div>
	            <div id="commit_cancel" class="inline_div small_overlap_btn">确定</div>
	        </div>
	    </div>
    
		<div class="deltips_overlap message_overlap hidden" id="deleteGroupComWindow">
	    	<div class="message_title_block">
	            <div class="title">提示</div>
	        </div>
	        <div class="message_content_block">
	            <div class="content">
	                <div class="inline_div content_img tips_img"></div>
	                <div class="inline_div">请确定是否取删除公司？</div>
	            </div>
	        </div>
	        <div class="message_btn_block">
	            <div id="cancel_message3" class="inline_div small_overlap_btn_1">
	                <div>取消</div>
	            </div>
	            <div id="commit_delete" class="inline_div small_overlap_btn">确定</div>
	        </div>
	    </div>
	    
    	<!-- 添加关联公司提示 -->
        <div class="deltips_overlap message_overlap hidden" id="addRelationCompanyTipsWindow">
	        <div class="message_title_block">
	            <div class="title">提示</div>
	        </div>
	        <div class="message_content_block">
	            <div class="content">
	                <div class="inline_div content_img tips_img"></div>
	                <div class="inline_div">是否确认加入关系企业？</div>
	            </div>
	        </div>
	        <div class="message_btn_block">
	            <div id="cancel_message_company" class="inline_div small_overlap_btn_1">
	                <div>取消</div>
	            </div>
	            <div id="commit_cancel_company" class="inline_div small_overlap_btn">确定</div>
	        </div>
    	</div>
    	<!--添加关联公司提示   -->
    
       	<!-- 添加关联自然人提示 -->
        <div class="deltips_overlap message_overlap hidden" id="addRelationPersonTipsWindow">
        	<div class="message_title_block">
            	<div class="title">提示</div>
        	</div>
	        <div class="message_content_block">
	            <div class="content">
	                <div class="inline_div content_img tips_img"></div>
	                <div class="inline_div">是否确认加入关系人？</div>
	            </div>
	        </div>
	        <div class="message_btn_block">
	            <div id="cancel_message_person" class="inline_div small_overlap_btn_1">
	                <div>取消</div>
	            </div>
	            <div id="commit_cancel_person" class="inline_div small_overlap_btn">确定</div>
	        </div>
    	</div>
    
	    <!--添加关联自然人提示   -->
	    <div class="hidden group_edit_block" id="relate_add_block">
	 		<input type="hidden" id="monitorId" name="monitorId" value=""/>
	 		<input type="hidden" id="rcompanyName" name="rcompanyName" value=""/>
	 		
	        <div class="mode_selector">
	            <div id="company_mode" class="selected inline_div add_mode">新增关系企业 
	            </div><div id="person_mode" class=" inline_div add_mode">新增关系自然人</div>
	            <div class="relative_left_block">
	            	<div class="mode_arr triangle-down"></div>
	            </div>
	        </div>
	        <div id="company_mode_div"></div>
	      	<div id="person_mode_div"></div>
		</div>

		<div class="message_overlap hidden" id="MessageOverlap">
			<div class="message_title_block">
				<div class="title">
					提示
				</div>
			</div>
			<div class="message_content_block">
				<div class="content">
					<div class="inline_div content_img tips_img"></div>
					<div class="inline_div content_value"></div>
				</div>
			</div>
			<div class="message_btn_block">
				<div id="cancel_message" class="inline_div small_overlap_btn_1">
					<div>取消</div>
				</div>
				<div id="ok_message" class="inline_div small_overlap_btn">
					确定
				</div>
			</div>
		</div>
		<div class="message_overlap hidden" id="MessageInfoOverlap">
			<div class="message_title_block">
				<div class="title">
					提示
				</div>
			</div>
			<div class="message_content_block">
				<div class="content">
					<div class="inline_div content_img tips_img"></div>
					<div class="inline_div content_value"></div>
				</div>
				<div class="inline_div content_info"></div>
			</div>
			<div class="message_btn_block">
				<div id="cancel_message" class="inline_div small_overlap_btn_1 hidden">
					<div>取消</div>
				</div>
				<div id="ok_message" class="inline_div small_overlap_btn">
					确定
				</div>
			</div>
		</div>
		<div class="hidden message_tips_overlap" id="MessageTipsOverlap">
			<div class="tips">
				尚未做任何修改,请填选内容后再确认！
			</div>
		</div>
	</body>
</html>