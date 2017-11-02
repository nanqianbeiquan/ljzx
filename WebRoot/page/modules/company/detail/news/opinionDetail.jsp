<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>
<%@ include file="/page/common/header.jsp"%>
<script>
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
	});

</script>
<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/monitor/company.css?${appVersion }"> 
 <div class="detail_block_2 detail_block news" id="">
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
                            <span class="title">新闻舆情详情
                            </span>
                            <span class="time">发布日期:<span id="publishDate4News">${newsOpinion.pubtime}</span></span>
                        </div>
                        <div class="content_title" id="title4News">
                        ${newsOpinion.title}  
                        </div>
                    </div>
                    <div class="detail_content_block">
                        <div class="content" id="details4News" onclick="return false">
                        ${newsOpinion.content}  
                        </div>
                        &nbsp;<br/>
                        &nbsp;<br/>
                        &nbsp;<br/>
                    </div>
                </div>
</div>
