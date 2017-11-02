<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
   <!-- 基本信息 -->
   <div class="info_block">
      <jsp:include page="bizDetail/basicInfo.jsp"></jsp:include>
   </div>
   <!-- 股东信息 -->
<div  class="info_block">
   <div id="guDongDiv">
      <jsp:include page="bizDetail/guDong.jsp"></jsp:include>
   </div>
   <div id="guDongPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 主要人员 -->
<div  class="info_block">
   <div id="zhuYaoRenYuanDiv">
      <jsp:include page="bizDetail/zhuYaoRenYuan.jsp"></jsp:include>
   </div>
   <div id="zhuYaoRenYuanPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 分支机构 -->
<div  class="info_block">   
   <div id="fiLiationDiv">
       <jsp:include page="bizDetail/fiLiation.jsp"></jsp:include>
   </div>
   <div id="fiLiationPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 变更记录 -->
<div  class="info_block">
   <div id="bianGengJiLuDiv">
       <jsp:include page="bizDetail/bianGengJiLu.jsp"></jsp:include>
   </div>
   <div id="bianGengJiLuPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 对外投资 -->
<div  class="info_block">
   <div id="entInvDiv">
       <jsp:include page="bizDetail/entInv.jsp"></jsp:include>   
   </div>
   <div id="entInvPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!--股权出质  -->
<div  class="info_block">
   <div id="shareSIMPAWNDiv">
       <jsp:include page="bizDetail/shareSIMPAWN.jsp"></jsp:include>   
   </div>
   <div id="shareSIMPAWNPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 动产质押 -->
<div  class="info_block">
   <div id="dongChanDiYaDiv">
       <jsp:include page="bizDetail/dongChanDiYa.jsp"></jsp:include>   
   </div>
   <div id="dongChanDiYaPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 经营异常 -->
<div  class="info_block">
   <div id="jingYingYiChangDiv">
       <jsp:include page="bizDetail/jingYingYiChang.jsp"></jsp:include>   
   </div>
   <div id="jingYingYiChangPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 行政处罚 -->
<div  class="info_block">
   <div id="administrativePenaltyDiv">
       <jsp:include page="bizDetail/administrativePenalty.jsp"></jsp:include>   
   </div>
   <div id="administrativePenaltyPageNav"  class="page_block" style="display: none;"></div>
</div>
   <!-- 商标 -->
<div  class="info_block">
   <div id="brandDiv">
       <jsp:include page="bizDetail/brand.jsp"></jsp:include>   
   </div>
   <div id="brandPageNav"  class="page_block" style="display: none;"></div>
</div>