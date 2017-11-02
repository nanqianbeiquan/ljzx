<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
  	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	
	<script type="text/javascript" src="${ctx }/js/modules/company/detail/law/law.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/monitor/monitorRelationPersonDetail.js?${appVersion }"></script>
	
    <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/company.css?${appVersion }">
   	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/monitorRelationPersonDetail.css?${appVersion }">
</head>

<body class="ljzx_page back_gray">
	<input id="idName"  name="idName" type="hidden" value="${monitorRelationPerson.name}"/>
	<input id="idNumber"  name="idNumber" type="hidden" value="${monitorRelationPerson.idNumber}"/>
	<input id="province"  name="province" type="hidden" value="${monitorRelationPerson.province}"/>
	<input id="city"  name="city" type="hidden" value="${monitorRelationPerson.city}"/>
	<input id="deep"  name="deep" type="hidden" value="${deep}"/>
	
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	
	<div class="detail_block_4 detail_block hidden shixin">
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
                 <td id=""><div id="wsqdyw4Shixin" style=""></div></td>
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

<div class="detail_block_1 detail_block hidden sfws">
		<div class="border_block">
			<div class="border_line"></div>
			<div class="left_hide inline_div"></div>
			<div class="border_img inline_div">
				<img src="${ctx }/images/modules/monitor/more_detail_1.png" />
			</div>
			<div class="right_hide inline_div"></div>
		</div>
		  <div class="detail_content">
                    <div class="detail_title_block">
                        <div class="detail_title">
                            <span class="title">裁判文书详情</span>
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

    <div class="body_content_5">
        <div class="body_center ">
            <div class="body_block_2 block_shadow">
                <div class="nav_bar">
                    <div class="back_block">
                        <div class="inline_div">
                                    <div class="for_back_btn"></div>
                                </div>
                    </div>
                    <span><a href="#">首页</a></span>
                    <span>></span>
                    <span><a href="#">监控企业列表</a></span>
                    <span>></span>
                    <span><a href="#">监控企业详情</a></span>
                    <span>></span>
                    <span class="current_nav">关系个人详情</span>
                </div>
                <div class="history_content">
                    <div class="company_title person_info_list">
                        <div class="relative_left_block">
                            <div class="person_name">
                                <span>姓名:</span>
                                <span>${monitorRelationPerson.name}</span>
                            </div>
                        </div>
                        <div class="company_span">
                            <div class="span_title inline_div" style="top:0px;">个人标签：</div>
                            <div class="span_list inline_div">
                            	<c:if test="${not empty labelList }">
	                                <c:forEach items="${labelList }" var="record" varStatus="i">
	                                   <div class="span span_color_${i.index%4+1} inline_div">${record}</div>
	                                </c:forEach>
                                </c:if>
                            </div>
                        </div>
                    </div>
                    <div class="company_modules_blodk">
                        <div class="company_modules">
                            <div class="modules_list">
                                <div class="modules inline_div selected">
                                    <div class="inline_div name">开庭公告</div>
                                    <div class="inline_div value">${subType30 }</div>
                                </div><div class="modules inline_div">
                                    <div class="inline_div name">被执行人</div>
                                    <div class="inline_div value">${subType35}</div>
                                </div><div class="modules inline_div">
                                    <div class="inline_div name">失信信息</div>
                                    <div class="inline_div value">${subType36}</div>
                                </div><div class="modules inline_div">
                                    <div class="inline_div name">裁判文书</div>
                                    <div class="inline_div value">${subType31 }</div>
                                </div>
                            </div>
                            <div class="relative_center_block">
                                <div class="modules_bottom_block">
                                    <div class="modules_bottom_selector"></div>
                                    <div class="modules_bottom_line"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modules_block_list">
                        <div class="modules_block current_modules">
                            <div class="info_block" id="kaiTingGongGaoDiv">
                           		<jsp:include page="/page/modules/monitor/monitorCompany/kaiTingGongGao.jsp"></jsp:include>
                            </div>
                            <div class="info_block" id="personSubjectDiv">
                               <jsp:include page="/page/modules/monitor/monitorCompany/personSubject.jsp"></jsp:include>
                            </div>
                            <div class="info_block" id="dishonestInfoDiv">
                                 <jsp:include page="/page/modules/monitor/monitorCompany/dishonestInfo.jsp"></jsp:include>
                            </div>
                            <div class="info_block" id="caipangDiv">
                              <jsp:include page="/page/modules/monitor/monitorCompany/siFaAnJian.jsp"></jsp:include>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
