<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<%@ include file="/page/common/base.jsp"%>
<html>
    <head>
        <meta charset="UTF-8">
        <link type="text/css" rel="stylesheet" href="${ctx }/css/common/layout.css?${appVersion }"">
        <link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/relPersonDetail.css?${appVersion }">

        <script type="text/javascript" src="${ctx }/js/jquery/jquery.js?${appVersion }"></script>
        <script type="text/javascript" src="${ctx }/js/common/layout.js?${appVersion }"></script>
        <script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
        <script type="text/javascript" src="${ctx }/js/modules/monitor/relPersonDetail.js?${appVersion }"></script>
        <script type="text/javascript" src="${ctx }/js/common/backspace_disabled.js?${appVersion }"></script>
    </head>
    <body class="ljzx_page back_gray">
          	<!-- 引入头部 -->
	        <jsp:include page="/page/modules/index/banner.jsp"/>
      
            <div class="body_content_5">
                <div class="body_center ">
                    
                    <div class="body_block_2 block_shadow">
                        <div class="nav_bar">
                            <div class="back_block">
                                <!--<div class="inline_div small_img_btn">
                                    <img class="inline_div" src="./img/back_arr.png" />
                                    <span class="inline_div">返回</span>
                                </div>-->
                                <div class="inline_div">
<!--                                     <div class="for_back_btn"></div> -->
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
                        <div class="person_info_list" style="min-height:700px;">
                            <div class="person_name">
                                <span>姓名:</span>
                                <span id="name" name="name">${name}</span>
                            </div>
                            <input id="idNumber" name="idNumber" type="hidden" value="${idNumber }"/>
                            <input id="city" name="city" type="hidden" value="${city }"/>
                            <input id="province" name="province" type="hidden" value="${province }"/>
                            <input id="relPersonRiskId" name="relPersonRiskId" type="hidden" value="${relPersonRiskId }"/>
                            <div class="company_span">
                                <div class="span_title inline_div" style="min-width:70px;">个人标签：</div>
                                <div class="span_list inline_div">
                                    <c:if test="${not empty labelList }">
                                        <c:forEach items="${labelList }" var="record" varStatus="i">
                                           <div class="span span_color_${i.index%4+1} inline_div">${record }</div>
                                        </c:forEach>
                                    </c:if>
                                   <!-- <div class="span span_color_1 inline_div">信誉好</div>
                                    <div class="span span_color_2 inline_div">信誉好</div>
                                    <div class="span span_color_3 inline_div">信誉好</div>
                                    <div class="span span_color_4 inline_div">信誉好</div>
                                    <div class="span span_color_1 inline_div">信誉好</div>
                                    <div class="span span_color_2 inline_div">信誉好</div>
                                    <div class="span span_color_3 inline_div">信誉好</div>
                                    <div class="span span_color_4 inline_div">信誉好</div>
                                    <div class="span span_color_1 inline_div">信誉好</div>
                                    <div class="span span_color_2 inline_div">信誉好</div>
                                    <div class="span span_color_2 inline_div">信誉好</div>
                                    <div class="span span_color_2 inline_div">信誉好</div> --> 
                                </div>
                            </div>
                            <div id="selectedContent"></div>
                       <!-- <div id="person_info_1" class="person_info">
                                <div class="title">
                                    <span>失信信息</span>
                                    (<span>1</span>)
                                </div>
                                <div>
                                    <table class="first_col_table info_table">
                                        <tr>
                                            <td>执法法院</td>
                                            <td>日照市五莲县法院</td>
                                        </tr>
                                        <tr>
                                            <td>省份</td>
                                            <td>山东</td>
                                        </tr>
                                        <tr>
                                            <td>案号</td>
                                            <td>（2014）莲执字第00954号</td>
                                        </tr>
                                        <tr>
                                            <td>被执行人的履行情况</td>
                                            <td>全部未履行</td>
                                        </tr>
                                        <tr>
                                            <td>失信被执行人行为具体情形</td>
                                            <td>其他有履行能力而拒不履行生效法律文书确定义务</td>
                                        </tr>
                                        <tr>
                                            <td>发布时间</td>
                                            <td>2016年10月26日</td>
                                        </tr>
                                        <tr>
                                            <td>生效法律文书确定的义务</td>
                                            <td>关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="person_info_2" class="person_info">
                                <div class="title">
                                    <span>开庭公告</span>
                                    (<span>1</span>)
                                </div>
                                <div>
                                    <table class="first_col_table info_table">
                                        <tr>
                                            <td>执法法院</td>
                                            <td>日照市五莲县法院</td>
                                        </tr>
                                        <tr>
                                            <td>省份</td>
                                            <td>山东</td>
                                        </tr>
                                        <tr>
                                            <td>案号</td>
                                            <td>（2014）莲执字第00954号</td>
                                        </tr>
                                        <tr>
                                            <td>被执行人的履行情况</td>
                                            <td>全部未履行</td>
                                        </tr>
                                        <tr>
                                            <td>失信被执行人行为具体情形</td>
                                            <td>其他有履行能力而拒不履行生效法律文书确定义务</td>
                                        </tr>
                                        <tr>
                                            <td>发布时间</td>
                                            <td>2016年10月26日</td>
                                        </tr>
                                        <tr>
                                            <td>生效法律文书确定的义务</td>
                                            <td>关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="person_info_3" class="person_info">
                                <div class="title">
                                    <span>司法案件</span>
                                    (<span>1</span>)
                                </div>
                                <div>
                                    <table class="first_col_table info_table">
                                        <tr>
                                            <td>执法法院</td>
                                            <td>日照市五莲县法院</td>
                                        </tr>
                                        <tr>
                                            <td>省份</td>
                                            <td>山东</td>
                                        </tr>
                                        <tr>
                                            <td>案号</td>
                                            <td>（2014）莲执字第00954号</td>
                                        </tr>
                                        <tr>
                                            <td>被执行人的履行情况</td>
                                            <td>全部未履行</td>
                                        </tr>
                                        <tr>
                                            <td>失信被执行人行为具体情形</td>
                                            <td>其他有履行能力而拒不履行生效法律文书确定义务</td>
                                        </tr>
                                        <tr>
                                            <td>发布时间</td>
                                            <td>2016年10月26日</td>
                                        </tr>
                                        <tr>
                                            <td>生效法律文书确定的义务</td>
                                            <td>关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="person_info_4" class="person_info">
                                <div class="title">
                                    <span>对外投资</span>
                                    (<span>1</span>)
                                </div>
                                <div>
                                    <table class="first_col_table info_table">
                                        <tr>
                                            <td>执法法院</td>
                                            <td>日照市五莲县法院</td>
                                        </tr>
                                        <tr>
                                            <td>省份</td>
                                            <td>山东</td>
                                        </tr>
                                        <tr>
                                            <td>案号</td>
                                            <td>（2014）莲执字第00954号</td>
                                        </tr>
                                        <tr>
                                            <td>被执行人的履行情况</td>
                                            <td>全部未履行</td>
                                        </tr>
                                        <tr>
                                            <td>失信被执行人行为具体情形</td>
                                            <td>其他有履行能力而拒不履行生效法律文书确定义务</td>
                                        </tr>
                                        <tr>
                                            <td>发布时间</td>
                                            <td>2016年10月26日</td>
                                        </tr>
                                        <tr>
                                            <td>生效法律文书确定的义务</td>
                                            <td>关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="person_info_5" class="person_info">
                                <div class="title">
                                    <span>对外任职</span>
                                    (<span>1</span>)
                                </div>
                                <div>
                                    <table class="first_col_table info_table">
                                        <tr>
                                            <td>执法法院</td>
                                            <td>日照市五莲县法院</td>
                                        </tr>
                                        <tr>
                                            <td>省份</td>
                                            <td>山东</td>
                                        </tr>
                                        <tr>
                                            <td>案号</td>
                                            <td>（2014）莲执字第00954号</td>
                                        </tr>
                                        <tr>
                                            <td>被执行人的履行情况</td>
                                            <td>全部未履行</td>
                                        </tr>
                                        <tr>
                                            <td>失信被执行人行为具体情形</td>
                                            <td>其他有履行能力而拒不履行生效法律文书确定义务</td>
                                        </tr>
                                        <tr>
                                            <td>发布时间</td>
                                            <td>2016年10月26日</td>
                                        </tr>
                                        <tr>
                                            <td>生效法律文书确定的义务</td>
                                            <td>关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="person_info_6" class="person_info">
                                <div class="title">
                                    <span>被执行人</span>
                                    (<span>1</span>)
                                </div>
                                <div>
                                    <table class="first_col_table info_table">
                                        <tr>
                                            <td>执法法院</td>
                                            <td>日照市五莲县法院</td>
                                        </tr>
                                        <tr>
                                            <td>省份</td>
                                            <td>山东</td>
                                        </tr>
                                        <tr>
                                            <td>案号</td>
                                            <td>（2014）莲执字第00954号</td>
                                        </tr>
                                        <tr>
                                            <td>被执行人的履行情况</td>
                                            <td>全部未履行</td>
                                        </tr>
                                        <tr>
                                            <td>失信被执行人行为具体情形</td>
                                            <td>其他有履行能力而拒不履行生效法律文书确定义务</td>
                                        </tr>
                                        <tr>
                                            <td>发布时间</td>
                                            <td>2016年10月26日</td>
                                        </tr>
                                        <tr>
                                            <td>生效法律文书确定的义务</td>
                                            <td>关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <div id="person_info_7" class="person_info">
                                <div class="title">
                                    <span>黄赌毒</span>
                                    (<span>1</span>)
                                </div>
                                <div>
                                    <table class="first_col_table info_table">
                                        <tr>
                                            <td>执法法院</td>
                                            <td>日照市五莲县法院</td>
                                        </tr>
                                        <tr>
                                            <td>省份</td>
                                            <td>山东</td>
                                        </tr>
                                        <tr>
                                            <td>案号</td>
                                            <td>（2014）莲执字第00954号</td>
                                        </tr>
                                        <tr>
                                            <td>被执行人的履行情况</td>
                                            <td>全部未履行</td>
                                        </tr>
                                        <tr>
                                            <td>失信被执行人行为具体情形</td>
                                            <td>其他有履行能力而拒不履行生效法律文书确定义务</td>
                                        </tr>
                                        <tr>
                                            <td>发布时间</td>
                                            <td>2016年10月26日</td>
                                        </tr>
                                        <tr>
                                            <td>生效法律文书确定的义务</td>
                                            <td>关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容关于诉讼内容我是内容</td>
                                        </tr>
                                    </table>
                                </div>
                            </div> --> 
                            
                        </div>
                    </div>
                </div>

                <div class="person_nav">
                    <div class="nav_content">
                        <div class="inline_div content">
                            <div class="mark_selected">
                                <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                            </div>
                            <div class="border_round">
                                <div class="round"></div>
                            </div>
                           <!-- <div class="nav_li selected">
                                <div class="nav_mark">
                                    <div class="mark_line"></div>
                                    <div class="mark_point"></div>
                                    <div class="mark_selected">
                                        <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                                    </div>
                                    <div class="mark_line"></div>
                                </div>
                                <div class="nav_value" index="1">
                                    <span>1</span><span>失信信息</span><span>(0)</span>
                                </div>
                            </div> -->
                            <div class="nav_li selected">
                                <div class="nav_mark">
                                    <div class="mark_line"></div>
                                    <div class="mark_point"></div>
                                    <div class="mark_selected">
                                        <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                                    </div>
                                    <div class="mark_line"></div>
                                </div>
                                <div class="nav_value" index="2" type="30">
                                    <span>1</span><span>开庭公告</span>
                                    <span>
                                       (
                                        <c:if test="${ empty subType30 }">
                                        0
                                        </c:if>
                                        <c:if test="${ not empty subType30 }">
                                        ${subType30 }
                                        </c:if>                                        
                                       )
                                    </span>
                                </div>
                            </div>
                            <div class="nav_li">
                                <div class="nav_mark">
                                    <div class="mark_line"></div>
                                    <div class="mark_point"></div>
                                    <div class="mark_selected">
                                        <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                                    </div>
                                    <div class="mark_line"></div>
                                </div>
                                <div class="nav_value" index="3"  type="31">
                                    <span>2</span><span>司法案件</span>
                                    <span>
                                       (
                                        <c:if test="${ empty subType31 }">
                                        0
                                        </c:if>
                                        <c:if test="${ not empty subType31 }">
                                        ${subType31 }
                                        </c:if>                                        
                                       )
                                    </span>
                                </div>
                            </div>
                         <!--   <div class="nav_li">
                                <div class="nav_mark">
                                    <div class="mark_line"></div>
                                    <div class="mark_point"></div>
                                    <div class="mark_selected">
                                        <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                                    </div>
                                    <div class="mark_line"></div>
                                </div>
                                <div class="nav_value" index="4">
                                    <span>4</span><span>对外投资</span><span>(99+)</span>
                                </div>
                            </div>
                            <div class="nav_li">
                                <div class="nav_mark">
                                    <div class="mark_line"></div>
                                    <div class="mark_point"></div>
                                    <div class="mark_selected">
                                        <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                                    </div>
                                    <div class="mark_line"></div>
                                </div>
                                <div class="nav_value" index="5">
                                    <span>5</span><span>对外任职</span><span>(99+)</span>
                                </div>
                            </div>
                            <div class="nav_li">
                                <div class="nav_mark">
                                    <div class="mark_line"></div>
                                    <div class="mark_point"></div>
                                    <div class="mark_selected">
                                        <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                                    </div>
                                    <div class="mark_line"></div>
                                </div>
                                <div class="nav_value" index="6">
                                    <span>6</span><span>被执行人</span><span>(99+)</span>
                                </div>
                            </div> --> 
                             <!--<div class="nav_li">
                            <div class="nav_mark">
                                    <div class="mark_line"></div>
                                    <div class="mark_point"></div>
                                    <div class="mark_selected">
                                        <img src="${ctx }/images/modules/monitor/nav_arr.png"/>
                                    </div>
                                    <div class="mark_line"></div>
                                </div>
                                <div class="nav_value" index="7">
                                    <span>7</span><span>黄赌毒</span><span>(99+)</span>
                                </div>
                            </div> -->    
                            <div class="border_round">
                                <div class="round"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

    </body>
</html>
