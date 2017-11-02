<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	<%@ include file="/page/common/visitorStatisticsWithBaidu.jsp"%>
	<link type="text/css" rel="stylesheet" href="${ctx }/css/modules/info/questions.css?${appVersion }">
	<script type="text/javascript" src="${ctx }/js/modules/info/questions.js?${appVersion }"></script>
</head>
<body class="ljzx_page back_gray">
	<!-- 引入头部 -->
	<jsp:include page="/page/modules/index/banner.jsp"/>
	<input type="hidden" id="deep" value="${deep}" />
     <div class="body_content_5">
     	
         <div class="body_center ">
             <div class="body_block_2 block_shadow">
                 <div class="nav_bar">
                     <div class="back_block">
                         <div class="inline_div">
                             <div class="for_back_btn" onclick="window.history.back()"></div>
                         </div>
                     </div>
                     <span><a  href="${ctx }/index">首页</a></span>
                     <span>></span>
                     <span class="current_nav">常见问题</span>
                 </div>
                 <div class="question_block">
                     <div class="relative_left_block question_menu_block">
                         <div class="menu_list_title"><div class="title_value">常见问题分类</div></div>
                         <div class="menu_list">
                             <div class="menu menu_1 drop_icon selected_menu_1">
                                 <div class="menu_title"><div class="title_value">目录</div></div>
                                 <div class="menu_content" style="height:80px;">
                                     <div class="menu menu_2">
                                         <div class="menu_title current_question" index="1"><div class="title_point inline_div"></div><div class="title_value inline_div">账号与安全</div></div>
                                     </div>
                                     <div class="menu menu_2">
                                         <div class="menu_title" index="2"><div class="title_point inline_div" index="2"></div><div class="title_value inline_div">功能及使用</div></div>
                                     </div>
                                 </div>
                             </div>
                             <div class="menu menu_1 drop_icon selected_menu_1">
                                 <div class="menu_title"><div class="title_value">产品内容</div></div>
                                 <div class="menu_content" style="height:40px;">
                                     <div class="menu menu_2">
                                         <div class="menu_title" index="3"><div class="title_point inline_div" index="3"></div><div class="title_value inline_div">数据与模型</div></div>
                                     </div>
                                 </div>
                             </div>
                             <div class="menu menu_1 drop_icon selected_menu_1">
                                 <div class="menu_title"><div class="title_value">合作与优惠</div></div>
                                 <div class="menu_content" style="height:120px;">
                                     <div class="menu menu_2">
                                         <div class="menu_title" index="4"><div class="title_point inline_div" index="4"></div><div class="title_value inline_div">合作及条款</div></div>
                                     </div>
                                     <div class="menu menu_2">
                                         <div class="menu_title" index="5"><div class="title_point inline_div" index="5"></div><div class="title_value inline_div">联系我们</div></div>
                                     </div>
                                     <div class="menu menu_2">
                                         <div class="menu_title" index="6"><div class="title_point inline_div" index="6"></div><div class="title_value inline_div">优惠及活动</div></div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <div class="question_list_block">
                         <div id="question_1" class="questions">
                             <div class="question_title">
                                 账号与安全
                             </div>
                             <div class="question_list">
                                 <div class="question">
                                     <div class="question_q">
                                         1.同一账户可否多台设备同时使用？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">一个账号可以在不同设备使用，但同一时刻只能在一个设备上登录。</div>
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         2.怎么更换/解除已验证的手机号？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">请联系风声客服人员帮助您更改设置。</div>
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         3.忘记RiskRaider风险雷达账号密码，怎么找回？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">如果密码忘记了，请在登陆页面点击“忘记密码”，您需要输入最初注册时使用的手机号以及此手机号收取的验证码，并进行新密码设置。</div>
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         4.怎么修改密码？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">如果需要修改密码，请在登陆页面点击“修改密码”，输入原始密码及新密码即可。</div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                         <div id="question_2" class="questions hidden">
                             <div class="question_title">
                                 功能及使用
                             </div>
                             <div class="question_list">
                                 <div class="question">
                                     <div class="question_q">
                                         1.部分银行或单位安装RiskRaider风险雷达后无法登陆，怎么办？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">由于大部分银行是禁止直接连接外网，而是通过代理服务器或者防火墙安全规则进行过滤，并针对性的上网，所以提供以下两种解决方案：</div>
                                         <div class="p">1、建议您申请使用可连接外网的电脑进行登陆和操作。</div>
                                         <div class="p">2、如果不存在可以单独上外网的机器，则请贵单位技术人员将以下域名加入到代理服务器或者防火墙的白名单，即可：</div>
                                         <div class="p">https://fk.riskraider.com/ljzx/login</div>
<!--                                          <div class="p">Ljzd.lengjing.info</div> -->
<!--                                          <div class="p">Ljzd2.lengjing.info</div> -->
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         2.动态监控加入一些企业后为什么显示无监控数据？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">监控后第二天会显示监控结果。</div>
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         3.怎么退出账号？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">鼠标移动至右上角个人中心，点击显示出来的退出按钮即可。</div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                         <div id="question_3" class="questions hidden">
                             <div class="question_title">
                                数据与模型
                             </div>
                             <div class="question_list">
                                 <div class="question">
                                     <div class="question_q">
                                         1.风声征信的数据是怎么来的？跟其它征信公司比有什么特色？
                                     </div>
                                     <div class="question_a article">
                                         <div class="p">这是一个非常专业的问题。</div>
                                         <div class="p">首先，我们是获得央行企业征信管理备案资质的专业征信机构，央行每季度都会对我们进行审查，以确保企业经营健康、合法。</div>
                                         <div class="p">同时，也正是由于获得央行的许可，使得我们比其它企业更能获得国家相关管理部门的支持，以获得更权威、更全面的数据，譬如工商数据，司法数据，海关数据，经营异常数据等等。</div>
                                         <div class="p">具体拿工商数据来说，我们可以跟工商总局的数据实时对接，保证涵盖全国，并实时更新。再拿司法数据来说，目前大部分所谓的大数据公司都是通过高法的网站爬取的，但我们国家有几个省份的数据是没有接入高院网站的，譬如新疆、西藏等。</div>
                                         <div class="p">所以，我们还会跟各省当地专门做司法数据的机构合作，确保获取全国约3500家法院的全面司法数据。</div>
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         2.RiskRaider风险雷达风险评估是否可信，比全国企业信息公示系统有哪些优势？风险状况的依据是什么？
                                     </div>
                                     <div class="question_a article">
                                         <div class="p">我们的风险评估结合了所有的公开和非公开信息，全国企业信息公示系统只是大数据的一个方面。全国企业信息公示系统中是没有行政处罚信息的。</div>
                                         <div class="p">您提的问题是个很好、非常专业的问题。风声征信已经有超过十年的历史，在过去的十几年的时间里，我们有一项非常重要的业务，就是为客户开发专业的信用评估模型，曾帮助银行客户如：上海银行，星展银行，甚至阿里金融，还帮助很多著名的制造企业如海尔、五矿、海信、中化、立邦等国内外企业，根据他们的不同业务类型、客户类型建立针对性的评估模型，目前我们的数据库里有300多个专业模型。现在国际上通常都使用专家法、计量分析法的方法来建立模型，在中国，我们根据实际情况将两种方法结合起来。</div>
                                         <div class="p">在中国，对很多企业我们拿不到财务数据，有的即使拿到了也未必可信，因此我们重点考虑了基于客户的真实的交易数据和背景的建模方法，这些数据恰恰就是我们的优势，我们riskraider信用管理系统过去服务了成百上千家企业，我们积累了将近数百万家企业的数据、违约记录，我们完全有能力、有充分足够的历史交易数据、样本，使我帮助客户开发基于计量分析法的信用评估模型，而且现在我们和上海很多家银行合作，银行依据我们的信用评估模型可以直接放贷，比如上海银行等等。</div>
                                         <div class="p">另外需强调的是，我们在帮助客户开发完成评估模型之后，客户在使用时，我们每年都会帮助客户不断地去重新审核、不断地调整和验证的，保证模型的实用性。而且客户自己也可以时时去验证，我们有一套专业的方法和工具用于验证，客户自己会体会和判断模型好不好用、可不可行。总之我们是目前为止基于计量分析法和企业真实交易数据建模的现行者，第一人。</div>
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         3.舆情的数据是怎么来的？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">我们的舆情来源于全国1千多家主要的新闻媒体网站。</div>
                                         <div class="p">1)全国主流的新闻媒体网站</div>
                                         <div class="p">2)地方主要的的新闻媒体网站</div>
                                         <div class="p">3)我们选取的原则是：有一定的知名度，有一定资质，信誉良好，无恶意虚假报道。</div>
                                     </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         4.有些企业查不到怎么办？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">1)您可以输入企业全称进行搜索；</div>
                                         <div class="p">2)如果您查询的是新注册的企业，由于新注册企业需要先到所在地的区工商局进行工商登记，然后由区工商局报至市局，再上报至省局，最终汇总至全国工商总局，而我们是接全国工商总局数据，可能会差异1-2个月的时间。</div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                         <div id="question_4" class="questions hidden">
                             <div class="question_title">
                                合作及条款
                             </div>
                             <div class="question_list">
                                 <div class="question">
                                     <div class="question_q">
                                         1.试用期为30个自然日，可否延长？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">请联系风声销售人员根据具体情况进行申请。</div>
                                      </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         2.怎么购买？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">联系风声销售人员→签署“RiskRaider风险雷达服务合同”→支付相应费用→风声工作人员帮您开通账号后使用账号密码登录即可。</div>
                                      </div>
                                 </div>
                             </div>
                         </div>
                         <div id="question_5" class="questions hidden">
                             <div class="question_title">
                                联系我们
                             </div>
                             <div class="question_list">
                                 <div class="question">
                                     <div class="question_q">
                                         1.页面下方在线QQ客服，服务时间9:00-18:00
                                     </div>
                                     <div class="question_a">
                                         <div class="p">
                                             <img style="width:800px;" src="${ctx }/images/modules/info/foot_png.png"/>
                                         </div>
                                      </div>
                                 </div>
                                 <div class="question">
                                     <div class="question_q">
                                         2.消费者热线，服务时间9:00-18:00
                                     </div>
                                     <div class="question_a">
                                         <div class="p">电话号码：021-68580928-8011</div>
                                      </div>
                                 </div>
                             </div>
                         </div>
                         <div id="question_6" class="questions hidden">
                             <div class="question_title">
                                优惠及活动
                             </div>
                             <div class="question_list">
                                 <div class="question">
                                     <div class="question_q">
                                         1.近期有什么活动？
                                     </div>
                                     <div class="question_a">
                                         <div class="p">暂无</div>
                                      </div>
                                 </div>
                             </div>
                         </div>
                     </div>
                 </div>
             </div>
         </div>
     </div>	
	
</body>
</html>