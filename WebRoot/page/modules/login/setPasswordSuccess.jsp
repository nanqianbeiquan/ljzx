<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%

String action = request.getParameter("action");
request.setAttribute("action", action);

String macAddress = request.getParameter("macAddress");
String cpuId= request.getParameter("cpuId");
String hardId = request.getParameter("hardId");

request.setAttribute("macAddress", macAddress);
request.setAttribute("cpuId", cpuId);
request.setAttribute("hardId", hardId);
 %>
<!DOCTYPE HTML>
<html>
  <head>
    <%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	
    <link href="${ctx }/css/modules/login/setPasswordSuccess.css" type="text/css" media="screen" rel="stylesheet" />
    
    <script type="text/javascript">
      var timer;
      var pointer = 5;
      
      
      $(document).ready(function(){
        $("a[name='loginBtn']").click(function(){
            window.clearInterval(timer);
            $("#successForm").submit();
        });
      
        $("#timer").html(5);
        
        startTimer(5);
      });
      
      function startTimer(seed){
         pointer = seed;
         timer = window.setInterval("countSuccess()",1000);
      }
      
      function countSuccess(){
         if(pointer>0){
             $("#timer").html(--pointer);
         }else{
            window.clearInterval(timer);
            $("#successForm").submit();
         }
      }
    </script>
  </head>
  
<body>
	<form id="successForm" method="post" action="${ctx }/login" style="height:100px;width:700px;margin:0 auto;margin-top: 180px">
    	<fieldset style="min-height:50px;width:650px;margin:0 auto;">
       		<legend>${action}成功</legend>
      	 	<span>${action}成功，<label id="timer" name="timer"></label>秒后将跳转到登录页面，您也可以点击 <a href="#" name="loginBtn">登录</a>跳转到登录页面。</span>
        	
        	<input type="hidden" id="macAddress" name="macAddress" value="${macAddress }"/>
        	<input type="hidden" id="cpuId" name="cpuId" value="${cpuId }"/>
        	<input type="hidden" id="hardId" name="hardId" value="${hardId }"/>
    	</fieldset>
	</form>
</body>
</html>
