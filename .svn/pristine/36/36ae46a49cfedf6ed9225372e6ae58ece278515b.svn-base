<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
 	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	
	<script type="text/javascript" src="${ctx }/js/echarts/echarts.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/echarts/echartController.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/filter.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/company_rename.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/monitorRelationCompanyDetail.js?${appVersion }"></script>
	
   	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/companygroup.css?${appVersion }">
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/filter.css?${appVersion }">
</head>

<body class="ljzx_page back_gray">
	<input type="hidden" id="id" value="${monitorRelationCompany.id}" />
    <input type="hidden" id="monitorId" value="${monitorRelationCompany.monitorId}" />
    <input type="hidden" id="deep" value="${deep}" />
    <input type="hidden" id="companyName" value="${companyInfo.registerInfo.enterpriseName }"/>
    <input type="hidden" id="infoShowDate" value="${monitorRelationCompany.infoShowDate }"/>
    
    <jsp:include page="/page/modules/index/banner.jsp"/>
    
    <div class="body_content_5">
    	<div class="body_center ">
        	<div class="body_block_2 block_shadow">
            	<div class="nav_bar">
                	<div class="back_block">
                    	<div class="inline_div" onclick="window.history.back()">
                        	<div class="for_back_btn"></div>
                    	</div>
               		</div>
               		
                    <span><a href="${ctx }/index">首页</a></span>
                    <span>></span>
                    <span><a href="#">监控企业列表</a></span>
                    <span>></span>
                    <span><a href="#">监控企业详情</a></span>
                    <span>></span>
                    <span class="current_nav">关系企业详情页</span>
				</div>
				
                <div class="monitor_company_title">
            		<div class="monitor_company_title_content">
                   		<div class="company_name">
                               ${companyInfo.registerInfo.enterpriseName }
                               <!--  <span class="used_name">（曾用名：安徽厦门松霖科技有限公司）</span> -->
                           </div>
                	</div>
                </div>
                
                <div class="company_block">
                	<div class="inline_div company_info_block">
                    	<div class="company_span" style="min-height:30px;">
                        	<div class="span_title inline_div">公司标签：</div>
                        	<div class="span_list inline_div" id="companyTagList"></div>
                		</div>
                		
                        <div>
                        	<table class="company_info" style="min-height:195px;">
                            	<tr>
                                	<td class="span_title">公司名称</td>
                    				<td class="span_value">${companyInfo.registerInfo.enterpriseName }</td>
                                    <td class="span_title">注册资本</td>
                                    <td class="span_value">${companyInfo.registerInfo.registeredCapital }</td>
                                    <td class="span_title">经营状态</td>
                                    <td class="span_value">${companyInfo.registerInfo.operateStatus }</td>
                                </tr>
                                <tr>
                                	<td class="span_title">统一社会信用代码/注册号</td>
                                    <td class="span_value">${companyInfo.registerInfo.unifiedSocialCreditCode }</td>
                                    <td class="span_title">企业地址</td>
                                    <td class="span_value">${companyInfo.registerInfo.businessPlace }</td>
                                    <td class="span_title">营业期限</td>
                                    <td class="span_value" style="min-width:100px;">
                                    	<c:if test="${companyInfo.registerInfo.validityTo eq '--' }">长期</c:if>
                                    	<c:if test="${companyInfo.registerInfo.validityTo ne '--' }">${companyInfo.registerInfo.validityFrom }~${companyInfo.registerInfo.validityTo}</c:if>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="span_title">法定代表人</td>
                                    <td class="span_value">${companyInfo.registerInfo.legalRepresentative }</td>
                                    <td class="span_title">公司类型</td>
                                    <td class="span_value">${companyInfo.registerInfo.enterpriseType }</td>
                                    <td class="span_title"> 成立日期</td>
                                    <td class="span_value">${companyInfo.registerInfo.establishmentDate }</td>
                                </tr>
                                <tr>
                                    <td class="span_title">经营范围</td>
                                    <td class="span_value range" colspan="5">${companyInfo.registerInfo.businessScope }</td>
                                </tr>
                            </table>
						</div>
					</div><div class="inline_div monitor_event_pic">
                        	<div id="monitor_company_event" class="line_echart_block" style="height:290px;"></div>
                    	</div>
					</div>
                        
					<div class="body_block_title">
                    	 监控事件
						<div class="inline_div title_btn" style="z-index:300;position: relative;"> 
                            <div class="inline_div img_btn_2"
                                style="margin-left:4px;width:80px;" id="finance_data_btn">
                                <img class="img"
                                    src="${ctx }/images/modules/monitor/finance_data.png" /> <img
                                    class="hidden img_hover"
                                    src="${ctx }/images/modules/monitor/finance_data_hover.png" /> 财务分析
                            </div>
							<div class="inline_div img_btn_2" style="width:92px;"
								onclick="toSiFaQuanJingtu('${companyName}')">
								<img class="img" src="${ctx }/images/modules/monitor/img.png" />
								<img class="hidden img_hover" src="${ctx }/images/modules/monitor/img_hover.png" />司法全景图
							</div>                                                                                     
                            <div class="inline_div img_btn_2" style="margin-left:4px;width:80px;" id="history_btn">
								<img class="img" src="${ctx }/images/modules/monitor/history.png" />
								<img class="hidden img_hover" src="${ctx }/images/modules/monitor/history_hover.png" />企业详情
							</div>
							<div class="inline_div img_btn_2" style="margin-left:4px;width:105px;" id="legal_person_btn">
								<img class="img" src="${ctx }/images/modules/monitor/legal_person.png" />
								<img class="hidden img_hover" src="${ctx }/images/modules/monitor/legal_person_hover.png" />法人代表信息
							</div>
						</div>
					</div>
					
                    <div class="event_block">
                    	<div class="event_titles hidden">
	                    	<div class="inline_div title selected">
	                        	<div class="selected_block">
	                                 <div class="extend_color"></div>
	                       		</div>
	                        	<div>
	                           		<span>企业背景</span>
	                           		<span class="count">(<span>2</span>)</span>
	                        	</div>
							</div>
                            <div class="inline_div title">
                                <div class="selected_block">
                                    <div class="extend_color"></div>
                            	</div>
                            	<div>
                                     <span>司法涉诉</span>
                                     <span class="count">(<span>2</span>)</span>
                                 </div>
							</div>
	                        <div class="inline_div title">
	                       		<div class="selected_block">
	                            	<div class="extend_color"></div>
	                         	</div>
	                         	<div>
	                            	<span>税务信息</span>
	                            	<span class="count">(<span>2</span>)</span>
	                          	</div>
							</div>
							<div class="inline_div title">
                             	<div class="selected_block">
                                      <div class="extend_color"></div>
                                  </div>
                                  <div>
                                      <span>行政处罚</span>
                                      <span class="count">(<span>2</span>)</span>
                                  </div>
							</div>
							<div class="inline_div title">
                            	<div class="selected_block">
                                	<div class="extend_color"></div>
                             	</div>
                             	<div>
                                	<span>发展潜力</span>
                                	<span class="count">(<span>2</span>)</span>
                            	</div>
							</div>
                                <div class="inline_div title">
                                    <div class="selected_block">
                                        <div class="extend_color"></div>
                                    </div>
                                    <div>
                                        <span>舆情</span>
                                        <span class="count">(<span>2</span>)</span>
                                    </div>
                                </div>
                            </div>
                            <div class="event_spans hidden">
                                <div class="title_1 title_spans current">
                                    <div class="span inline_div">
                                        <span>案件总数</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>金融合同案件数</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>非金融合同案件数</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div selected">
                                        <span>其他案由案件数</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>被执行人</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>失信信息</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>开庭公告</span>
                                        (<span>3</span>)
                                    </div>
                                </div>
                                <div class="title_2 title_spans hidden">
                                    <div class="span inline_div">
                                        <span>司法涉诉标签1</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>司法涉诉标签2</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>司法涉诉标签3</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div selected">
                                        <span>司法涉诉标签4</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>司法涉诉标签5</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>司法涉诉标签6</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>司法涉诉标签7</span>
                                        (<span>3</span>)
                                    </div>
                                </div>
                                <div class="title_3 title_spans hidden">
                                    <div class="span inline_div">
                                        <span>税务信息标签1</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>税务信息标签2</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>税务信息标签3</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div selected">
                                        <span>税务信息标签4</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>税务信息标签5</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>税务信息标签6</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>税务信息标签7</span>
                                        (<span>3</span>)
                                    </div>
                                </div>
                                <div class="title_4 title_spans hidden">
                                    <div class="span inline_div">
                                        <span>行政处罚标签1</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>行政处罚标签2</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>行政处罚标签3</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div selected">
                                        <span>行政处罚标签4</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>行政处罚标签5</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>行政处罚标签6</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>行政处罚标签7</span>
                                        (<span>3</span>)
                                    </div>
                                </div>
                                <div class="title_5 title_spans hidden">
                                    <div class="span inline_div">
                                        <span>发展潜力标签1</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>发展潜力标签2</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>发展潜力标签3</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div selected">
                                        <span>发展潜力标签4</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>发展潜力标签5</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>发展潜力标签6</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>发展潜力标签7</span>
                                        (<span>3</span>)
                                    </div>
                                </div>
                                <div class="title_6 title_spans hidden">
                                    <div class="span inline_div">
                                        <span>舆情标签1</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>舆情标签2</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>舆情标签3</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div selected">
                                        <span>舆情标签4</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>舆情标签5</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>舆情标签6</span>
                                        (<span>3</span>)
                                    </div>
                                    <div class="span inline_div">
                                        <span>舆情标签7</span>
                                        (<span>3</span>)
                                    </div>
                                </div>
                            </div>
                            <div class="monitor_events" style="position: relative;">
                            	<div class="event_value inline_div selected" index="01">法人代表变更</div>
                                <div class="event_value inline_div" index="02">股东变更</div>
                                <div class="event_value inline_div" index="03">高管变更</div>
                                <div class="event_value inline_div" index="04">经营异常</div>
                                <div class="event_value inline_div" index="05">企业更名</div>
                                <div class="event_value inline_div" index="06">经营状态</div>
                                <div class="event_value inline_div" index="07">对外投资</div>
                                <div class="event_value inline_div" index="08">动产抵押</div>
                                <div class="event_value inline_div" index="09">股权出质</div>
                                <div class="event_value inline_div" index="10">股权冻结</div>
                                <div class="event_value inline_div" index="11">工商行政处罚</div>
                                <div class="event_value inline_div" index="12">食药监监察</div>
                                <div class="event_value inline_div" index="13">质量监督</div>
                                <div class="event_value inline_div" index="14">社保缴纳</div>
                                <div class="event_value inline_div" index="15">环境核查</div>
                                <div class="event_value inline_div" index="16">欠税信息</div>
                                <div class="event_value inline_div" index="17">税务非正常户</div>
                                <div class="event_value inline_div" index="23">开庭公告</div>
                                <div class="event_value inline_div" index="18">裁判文书</div>
                                <div class="event_value inline_div" index="19">裁判文书（金融合同）</div>
                                <div class="event_value inline_div" index="20">裁判文书（劳务纠纷）</div>
                                <div class="event_value inline_div" index="21">被执行人</div>
                                <div class="event_value inline_div" index="22">失信被执行人</div>
                                <div class="event_value inline_div" index="24">媒体资讯</div>
                                <div class="event_value inline_div" index="25">重点关注舆情</div>
                                <div class="event_value inline_div" index="27">专利</div>
                                <div class="event_value inline_div" index="28">投招标</div>
                                <div class="event_value inline_div" index="29">商标</div>
                            </div>
                            <div class="event_table">
                            	<div class="time_range">
										<div class="relative_right_block inline_div_block" id="time_range_right">
											<div class="inline_div checkbox_selector">
                                               <input type="checkbox" name="newEve" onclick="readStatusDeal()"/>未读事件
                                            </div>
                                            <div class="inline_div button_selector">
                                               <div class="small_btn_2" id="ignoreAll" onclick="ignoreAll()">全部已读</div>
                                          </div>
											<div class="inline_div checkbox_selector">
                                          		<div class="event_selector_serious_btn" level="3" onclick="eventLevelDeal(this)">严重</div>
                                             </div>
											<div class="inline_div checkbox_selector">
												<div class="event_selector_abnormal_btn"  level="2" onclick="eventLevelDeal(this)">异常</div>
                                              </div>
                                               <div class="inline_div checkbox_selector">
                                               	<div class="event_selector_general_btn"  level="1" onclick="eventLevelDeal(this)">一般</div>
                                               </div>
										</div>
										<div class="inline_div_block" style="height:28px;">
										  <div id="filterTimeDiv">
												<div class="inline_div time_rang_title">时间筛选</div>
												<div class="option_time inline_div">
													<div class="time_png inline_div">
														<img class="inline_div" src="${ctx }/images/common/time_png.png" />
													</div><input class="Wdate" id="beginDate" placeholder="开始时间" type="text" style="width:90px;background:#ffffff;"  onfocus="WdatePicker({onpicking:	function(dp){beginDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){beginDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true})"/> 
												</div>
												<span class="time_span inline_div"></span>
		
												<div class="option_time  inline_div">
													<div class="time_png inline_div">
														<img class="inline_div" src="${ctx }/images/common/time_png.png" />
													</div><input class="Wdate" id="dueDate" placeholder="结束时间"  type="text" style="width:90px;background:#ffffff;"  onfocus="WdatePicker({onpicking:	function(dp){dueDateDeal(dp.cal.getNewDateStr()) },onclearing:function(dp){dueDateDeal('') },lang:'zh-cn',skin:'twoer',dateFmt:'yyyy-MM-dd',readOnly:true})"/> 
												</div>
										  </div>
										</div>
									</div>
                            	
                                  <div class="event_table">
                                        <div id="detailsTable"></div>
                                  </div>
                                  <div class="page_block">
                                        <div class="page_plug">
                                     	  <div id="Pagination4Details"></div>
                                        </div>
                                  </div>
                        </div>
                            </div>
                            
                    </div>
            </div>
            <div class="detail_block_1 detail_block hidden sfws">
                <div class="border_block">
                    <div class="border_line"></div>
                    <div class="left_hide inline_div"></div>
                    <div class="border_img inline_div">
                        <img src="${ctx }/images/modules/monitor/more_detail_1.png"/>
                    </div>
                    
                    <div class="right_hide inline_div"></div>
                </div>
                <div class="detail_content ">
                    <div class="detail_title_block">
                        <div class="detail_title">
                            <span class="title">法院判决详情</span>
                            <span class="time">发布日期:<span id="judgmentTime4Sx"></span></span>
                        </div>
                    </div>
                    <div class="detail_content_block">
                        <div class="content_court" id="title4Sx">
                        </div>
                        <div class="content" id="details4Sx">
                        </div>
                    </div>
                </div>
            </div>
            <div class="detail_block_2 detail_block hidden news">
                <div class="border_block">
                    <div class="border_line"></div>
                    <div class="left_hide inline_div"></div>
                    <div class="border_img inline_div">
                        <img src="${ctx }/images/modules/monitor/more_detail_2.png"/>
                    </div>
                    
                    <div class="right_hide inline_div"></div>
                </div>
                <div class="detail_content">
                    <div class="detail_title_block">
                        <div class="detail_title">
                            <span class="title">新闻舆情详情</span>
                            <span class="time">发布日期:<span id="publishDate4News"></span></span>
                        </div>
                        <div class="content_title" id="title4News">
                        </div>
                    </div>
                    <div class="detail_content_block">
                        <div class="content" id="details4News">
                        </div>
                    </div>
                </div>
            </div>
            <div class="detail_block_4 detail_block hidden shixin" >
                <div class="table_value" style="height:520px;overflow:auto;">
                	 <table class="">
                    <tr class="">
                        <td>被执行人姓名/名称：</td>
                        <td id="mc4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>身份证号码/组织机构代码：</td>
                        <td id="dm4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>法定代表人或负责人姓名：</td>
                        <td id="fddbr4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>执行法院：</td>
                        <td id="zxfy4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>省份：</td>
                        <td id="sf4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>执行依据文号：</td>
                        <td id="zxyjwh4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>立案时间：</td>
                        <td id="sj4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>案号：</td>
                        <td id="ah4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>做出执行依据单位：</td>
                        <td id="zxyjdw4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>生效法律文书确定的义务：</td>
                        <td id="wsqdyw4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>被执行人的履行情况：</td>
                        <td id="lxqk4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>失信被执行人行为具体情形：</td>
                        <td id="sxjtqk4Shixin"></td>
                    </tr>
                    <tr class="">
                        <td>发布时间：</td>
                        <td id="fbsj4Shixin"></td>
                    </tr>
                </table>
                
               </div>
               
            </div>
            
            <div class="deltips_overlap message_overlap hidden" id="noLegalPersonTip">
				<div class="message_title_block">
					<div class="title">提示</div>
				</div>
				<div class="message_content_block">
					<div class="content">
						<div class="inline_div content_img tips_img"></div>
						<div class="inline_div">当前无对外任职及投资信息。</div>
					</div>
				</div>
				<div class="message_btn_block">
					<div id="submitNoLegalPersonTip" class="inline_div small_overlap_btn">
						确定</div>
				</div>
			</div>
            
             <div class="detail_block_5  detail_block hidden" id="shiyaojian">
                <div class="border_block">
                    <div class="border_line border_line_2"></div>
                    <div class="left_hide inline_div"></div>
                    <div class="border_img border_img_2 inline_div">
                        <img src="${ctx }/images/modules/monitor/more_detail_4.png"/>
                    </div>
                     <div class="right_hide inline_div"></div> 
                 </div> 
 
                <div class="detail_content">
                    <div class="detail_title_block">
                        <div class="detail_title">
                            <span class="title">&nbsp;</span>
                            <span class="time">&nbsp;</span>
                        </div>
                        <div class="content_title">
                        		 食药监详情
                        </div>
                    </div>
                    <div class="shiyaojian" >
                    </div>
                </div>
            </div>
            </div>
    </body>
</html>