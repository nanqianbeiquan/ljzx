<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link href="${ctx }/css/modules/info/bootstrap.css?${appVersion }" rel="stylesheet" media="screen">

	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	
    <link href="${ctx }/css/modules/info/styles.css?${appVersion }" rel="stylesheet" media="screen">
    <link href="${ctx }/css/modules/info/animate.min.css?${appVersion }" rel="stylesheet" media="screen">
    <link href="${ctx }/css/modules/info/font-awesome.css?${appVersion }" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/info/aboutus.css?${appVersion }">
	
	<script type="text/javascript" src="${ctx }/js/common/navMenu.js?${appVersion }"></script>
	<script type="text/javascript" src="${ctx }/js/modules/info/aboutus.js?${appVersion }"></script>
    
    <script type="text/javascript">
		var controller;
		
		$(function(){
			var deep = $("#deep").val();
			updateNavMenu(parseInt(deep),"关于我们",window.location.search);
		})
	
	</script>
</head>
<body class="page-introduction">
    <!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp" />
	<input type="hidden" id="deep" value="${deep}" />
		
    <section class="about-count">
        <div class="container" style="text-align:center;">
            <img alt="关于我们" style="width:1140px;" src="${ctx }/images/modules/info/about_photo.png">
            <div class="tx">
                <div class="title" id=""><span>公司介绍</span></div>
                <p>上海风声企业信用征信有限公司是拥有央行备案资质的专业级企业信用服务商，其全资母公司上海斯睿德信息技术有限公司成立于2009年，深耕企业信用管理领域，拥有十分丰富的行业服务经验。总部位于上海，在北京、深圳、南京等地均设有分支机构。</p>
                <p>
                    公司创新运用大数据、语义分析、深度机器学习、计量建模等前沿技术在企业信用数据挖掘、关联、整合以及企业风险分析、风险评级、风险预警等企业征信领域的应用，致力于为企业提供SaaS模式的赊销客户动态监控和智能预警服务。
                </p>
                <p>基于企业在信用管理中面临的最重要的应收账款风险管控问题，公司研发了赊销客户动态监测和智能预警服务产品——RiskRaider风险雷达，全面解决了企业应收账款风险管控中客户资质调查、事后跟踪管理、动态风险监测以及智能预警等需求，为企业信控人员提供了标准化的应收账款风险管控SaaS工具，已经为三菱商事、霍尼韦尔、联想租赁、联想金融、莱德尔电子、富美家等各行业规模以上知名企业提供服务。</p>
                <p>
                    公司拥有数据采集、机器学习、图像识别、数据预处理四大类共计41项专利及软件著作权。公司创始团队曾服务过邓白氏、霍尼韦尔、加拿大皇家银行、上海电气、联想、Euler Hermers、Marsh、新浪、电信等公司，拥有深厚的大数据建模、分析以及企业信用管理经验，开发了数百套经过验证的国际创新风险计量模型。曾为国内外上百家银行及其他数百家金融机构、互联网金融企业，政府、园区提供信用数据服务。公司与张江高科技园区合作开发的“信用张江”模式，成功帮助园区500多家科技企业对接上海银行、浦发银行等银行，实现信用融资60亿元。
                </p>
                <p>
                    过去18个月内，公司先后获得国有投资机构张江火炬创投、知名创投机构飞马基金、杉奇投资、东方富海等每轮次数千万*3轮投资。
                </p>
                <p>
                    公司的使命是利用人工智能技术手段帮助企业信控人员专注风险决策，让企业应收账款风险管控效率更高、成本更低，应收账款周转更快，提升企业盈利能力。
                </p>
            </div>
            <div class="title" id="licheng"><span>发展沿革</span></div>
            <div class="cd-timeline" style="width:1000px;margin:0 auto;text-align:center;padding:0px 0px 85px 0px;">
                <img alt="" src="${ctx }/images/modules/info/about_history.png">
            </div>
        </div>
    </section>
    <script src="${ctx }/js/modules/info/jquery-1.10.2.min.js"></script>
    <script src="${ctx }/js/modules/info/bootstrap.js"></script>
    <script src="${ctx }/js/modules/info/lj-scripts.js"></script>
    <script src="${ctx }/js/modules/info/wow.min.js"></script>
    <script>
        new WOW().init();
    </script> 
<script>

</script>
</body>
</html>