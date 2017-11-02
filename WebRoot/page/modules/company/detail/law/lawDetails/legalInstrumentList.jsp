<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
	<head>
		<%@ include file="/page/common/base.jsp"%>
		<%@ include file="/page/common/header.jsp"%>
		<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
		
	    <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/company.css">
	    <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/events.css">
	    <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/filter.css">
    	
	    <script type="text/javascript" src="${ctx }/js/modules/monitor/filter.js"></script>
	    <script type="text/javascript" src="${ctx }/js/modules/company/detail/law/judicial.js"></script>
	    <script type="text/javascript" src="${ctx }/js/modules/company/detail/law/legalInstrumentList.js"></script>
	    <script type="text/javascript" src="${ctx }/js/modules/company/detail/law/law.js"></script>
	    
	    <script type="text/javascript">
			$(document).ready(function () {
				toastr.options = {
					"closeButton" : false, // 是否显示关闭按钮
					"debug" : false, // 是否使用debug模式
					"positionClass" : "toast-top-center",// 弹出窗的位置
					"showDuration" : "300",// 显示的动画时间
					"hideDuration" : "1000",// 消失的动画时间
					"timeOut" : "1000", // 展现时间
					"extendedTimeOut" : "1000",// 加长展示时间
					"showEasing" : "swing",// 显示时的动画缓冲方式
					"hideEasing" : "linear",// 消失时的动画缓冲方式
					"showMethod" : "fadeIn",// 显示时的动画方式
					"hideMethod" : "fadeOut" // 消失时的动画方式
				};
				
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
				controller.load_data();
				updateNavMenu(${currentDeep},"司法文书查询",window.location.search); 
				//queryLegalInstrumentList();
			});
		</script>
	</head>

<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
  	<jsp:include page="/page/modules/index/banner.jsp"/>
  	<input id="currentDeep" name="currentDeep" type="hidden" value="${currentDeep}"/>
  	<input id="deep" name="deep" type="hidden" value="${currentDeep}"/>
  	
    <div class="body_content_5">
        <div class="body_center ">
            <div class="body_block_2 block_shadow">
                <div class="nav_bar">
                    <div class="back_block">
                   		<div class="inline_div">
                        	<div class="for_back_btn" onclick="window.history.back()"></div>
                    	</div>
                    </div>
                    <span><a href="#">首页</a></span>
                    <span>></span>
                    <span><a href="#">监控企业列表</a></span>
                    <span>></span>
                    <span><a href="#">监控企业详情</a></span>
                    <span>></span>
                    <span><a href="#">企业详情</a></span>
                    <span>></span>
                    <span class="current_nav">司法文书</span>
                </div>
                <div class="name_block">
                    <div class="relative_right_block">
                        <div class="inline_div btn_2_block" id="" >
                            <div class="inline_div img_btn_4 export_in_img" onclick="exportLegalInstrumentList()">
                                <span  class="">导出数据</span>
                            </div>
                        </div>
                    </div>
                    <div class="monitor_company_name" id="comName">${companyName}</div>
                </div>
                <div class="filter_title">全部</div>
                <div class="filter_hidden_block">
                    <div class="filter_hidden_btn">
                        <div class="img_btn_3 filter_open_btn inline_div">
                            筛选条件
                            <img src="${ctx }/images/common/down.png" />
                        </div>
                        <div class="img_btn_3 filter_close_btn hidden inline_div">
                            收起筛选
                            <img src="${ctx }/images/common/up.png" />
                        </div>
                    </div>
                </div>
                <div class="monitor_event_filter">
                    <div class="filter_block">
                        <div class="relative_right_block clear_txt_block" id="clearBtn">
                            <div class="inline_div clear_txt">清空</div>
                        </div>
                        <div class="filter_data filter_text">
                            <div class="filter_name inline_div option_text"  filter_name="instrumentTypes">文&nbsp;书&nbsp;类&nbsp;型</div>
                            <div class="filter_options inline_div">
                                <div class="option_block inline_div">
                                    <div class="option_text option_name selected all_option inline_div" id=""><span class="option">全部</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name  inline_div"><span class="option" value="裁定书">裁定书</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name  inline_div"><span class="option" value="判决书">判决书</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name  inline_div"><span class="option" value="决定书">决定书</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name  inline_div"><span class="option" value="通知书">通知书</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name  inline_div"><span class="option" value="调解书">调解书</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="filter_data filter_text">
                            <div class="filter_name inline_div option_text" filter_name="trialClasses">审&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</div>
                            <div class="filter_options inline_div">
                                <div class="option_block inline_div">
                                    <div class="option_text option_name selected all_option inline_div" id=""><span class="option">全部</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="一审">一审</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="二审">二审</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="再审">再审</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="申请再审">申请再审</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="filter_data filter_text">
                            <div class="filter_name inline_div option_text"  filter_name="litigationTypes">诉&nbsp;讼&nbsp;类&nbsp;型</div>
                            <div class="filter_options inline_div">
                                <div class="option_block inline_div">
                                    <div class="option_text option_name selected all_option inline_div" id=""><span class="option">全部</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="民事案件">民事案件</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="刑事案件">刑事案件</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="执行案件">执行案件</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="行政案件">行政案件</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="filter_data filter_text">
                            <div class="filter_name inline_div option_text" filter_name="relatedPositions">当事人地位</div>
                            <div class="filter_options inline_div">
                                <div class="option_block inline_div">
                                    <div class="option_text option_name selected all_option inline_div" id=""><span class="option">全部</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="原告">原告/上诉人</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="被告">被告/被上诉人</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="第三人">第三人</span></div>
                                </div>
                                <div class="option_block inline_div">
                                    <div class="option_text option_name inline_div"><span class="option" value="其他">其他</span></div>
                                </div>
                            </div>
                        </div>
                        <div class="filter_data  filter_input">
                            <div class="filter_name inline_div option_text">判&nbsp;决&nbsp;时&nbsp;间</div>
                            <div class="filter_options inline_div">
                                <div class="option_block inline_div">
                                    <div class="option_time option_time inline_div">
                                        <div class="time_png inline_div">
                                                <img class="inline_div" src="${ctx }/images/common/time_png.png" />
                                        </div><input filter_name="beginDate"  class="Wdate" id="beginDate" readonly="readonly" name="beginDate" placeholder="开始时间" onchange="getLegalInstrumentList();"
                                        onfocus="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd'})"  type="text" style="width:90px;background:#ffffff;"  /> 
                                    </div><span class="time_span inline_div">
                                    
                                    </span>
                                    <div class="option_time option_time inline_div">
                                        <div class="time_png inline_div">
                                                <img class="inline_div" src="${ctx }/images/common/time_png.png" />
                                        </div><input filter_name="endDate" class="Wdate" id="endDate" name="endDate" readonly="readonly"   placeholder="结束时间" onchange="getLegalInstrumentList();"
                                        onfocus="WdatePicker({skin:'twoer',dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'beginDate\')}'})"  type="text" style="width:90px;background:#ffffff;"  /> 
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="filter_data  filter_input">
                            <div class="filter_name inline_div option_text">判&nbsp;决&nbsp;金&nbsp;额</div>
                            <div class="filter_options inline_div">
                                <div class="option_block inline_div">
                                    <div class="option_input  inline_div">
                                      <input filter_name="minAmountCount" class="" id="minAmountCount" name="minAmountCount" maxlength="14" placeholder="" type="text" style=""  onchange="getLegalInstrumentList();"/> 
                                    </div><span class="time_span inline_div">
                                    
                                    </span>
                                    <div class="option_input  inline_div">
                                        <input filter_name="maxAmountCount" class="" id="maxAmountCount" name="maxAmountCount" maxlength="14"   placeholder=""  type="text" style=""  onchange="getLegalInstrumentList();"/> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="event_table_block" style="height:auto;">
                    <div class="event_left_operate">
                    </div>
                    <div class="event_operate">
                    </div>
                    <div class="tab_list">
                    </div>
                    <div>
                        <table class="judicial_table first_col_table  data_table">
                            <thead>
                                <tr>
                                    <td class="table_3" style="width:55px">序号</td>
                                    <td class="table_15" style="width:105px">判决时间</td>
                                    <td class="table_10" style="width:175px">目标公司</td>
                                    <td class="table_10" style="width:150px">案号</td>
                                    <td class="table_10" style="width:115px">当事人地位</td>
                                    <td class="table_10" style="width:190px">案由</td>
                                    <td class="table_10" style="width:100px">文书类型</td>
                                    <td class="table_10" style="width:100px">诉讼类型</td>
                                    <td class="table_10" style="width:75px">审级</td>
                                    <td class="table_5" style="width:80px">判决金额</td>
                                </tr>
                            </thead>
                            <tbody id="instrumentResult">
                               <!--  <tr>
                                    <td>1</td>
                                    <td>2016-12-01</td>
                                    <td>乐视网信息技术（北京）股份有限公司</td>
                                    <td>（2016）粤0305民初14631-14660、14665-15665、15667-15555号</td>
                                    <td>原告</td>
                                    <td>民事案由->知识产权与竞争纠纷->知识产权权属、侵权纠纷->著作权权属、侵权纠纷</td>
                                    <td>裁定书</td>
                                    <td>民事案件</td>
                                    <td>一审</td>
                                    <td>
                                        0.0元
                                    </td>
                                </tr> -->
                            </tbody>
                        </table>
                    </div>
                    <div class="page_block" id="legalPagination">
                        <!-- <div class="page_plug">
                            <div class="page_info inline_div">
                                <span class="page inline_div">1</span>
                                <span class=" inline_div">/</span>
                                <span class=" inline_div">120</span>
                            </div>
                            <div class="prev_page inline_div page_btn">上一页</div>
                            <div class="page_list inline_div">
                                <span class="page inline_div current_page">1</span>
                                <span class="page inline_div">2</span>
                                <span class="page inline_div">3</span>
                                <span class="page inline_div">4</span>
                                <span class="page inline_div">120</span>
                            </div>
                            <div class="next_page inline_div page_btn">下一页</div>
                            <div class="last_page inline_div page_btn">最后一页</div>
                        </div> -->

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
                  <div class="detail_content">
                    <div class="detail_title_block">
                        <div class="detail_title">
                            <span class="title">法院判决详情</span>
                            <span class="time">发布日期:<span id="judgmentTime4Sx"></span></span>
                        </div>
                        <div class="content_title" id="title4Sx">
                        </div>
                        <div class="content_from">
                            <div class="content_court"></div>
                            <div class="content_type"></div>
                            <div class="content_case"></div>
                        </div>
                    </div>
                    <div class="detail_content_block">
                        <div class="content_court" style="text-align:center;font-size:14px;color:#333333;">
                        </div>
                        <div class="content_type" style="text-align:center;font-size:14px;color:#333333;">
                       
                        </div>
                        <div class="content_case"  style="text-align:right;font-size:14px;color:#999999;">
                          
                        </div>
                        <div class="content" id="details4Sx" style="overflow: auto;" onclick="return false">
                        </div>
                    </div>
                </div>
           </div>
  </body>
</html>
