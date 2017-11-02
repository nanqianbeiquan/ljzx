<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<%@ include file="/page/common/base.jsp"%>
	<%@ include file="/page/common/header.jsp"%>
	
	<link type="text/css" rel="stylesheet" href="${ctx }/css/common/error.css?${appVersion }">
</head>

<body>
	<form style="width:700px;margin-left:auto;margin-right:auto;padding:2px 0px;">
		<fieldset class="form" >
     		<div style=" width:654px; margin:0 auto; margin-top:30px; overflow:hidden;">
			<img src="${ctx }/images/common/baocuo.png" width="652" height="315" />
			<div style="text-align:center; margin-top:15px; margin-bottom:25px;">啊哦~无法找到你访问的页面！</div>
		</fieldset>     
 	</form>
</body>
</html>