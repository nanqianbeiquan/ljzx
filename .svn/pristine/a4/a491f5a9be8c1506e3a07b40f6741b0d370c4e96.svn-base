<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
.gongshangyuanspdiv {
    overflow: hidden;
    width: 100%;
    max-width: 100%;
    text-align: left;
    margin: 0px auto;
    margin-top: 10px;
}
.gongshangtable {
    width: 100%;
    max-width: 100%;
    min-width: 980px;
    text-align: center;
    border-left: #dfeaf6 solid 1px;
    border-top: #dfeaf6 solid 1px;
    overflow: hidden;
    margin: 0 auto;
}
</style>
</head>
<body>
	<c:if test="${not empty detail}">
		<div class="gongshangtablewaicengdiv">
		    <div class="gongshangyuanspdiv" style="width:95%;max-width:95%;">
<!-- 				<img src="/ljzd/images/qianDai.png" style="width:30px;height:30px;"/> -->
				动产抵押详情<HR style="height:3px;border:none;border-top:3px solid #4A90E2;">
			</div>
			<div class="gongshangyuanspdiv" style="width:95%;max-width:95%;">
				<span class="gongshangyuansp">动产抵押登记信息</span>
<!-- 				<span class="gongshangyuan"></span> -->
			</div>
			<table class="gongshangtable" cellpadding="0" cellspacing="0" style="width:95%;max-width:95%;min-width:95%;">
				<tr>
<!-- 					<td class="td_lableshuzi" style="text-align:center; color:#9a9a9a;">序号</td> -->
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">登记编号</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">登记机关</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">登记日期</td>
				</tr>
				<tr>
<!-- 					<td style="text-align:center;">1</td> -->
					<td style="text-align:center;"><c:out value="${detail.chattelmortgage_registrationno }"></c:out></td>
					<td style="text-align:center;"><c:out value="${detail.chattelmortgage_registrationinstitution }"></c:out></td>
					<td style="text-align:center;"><c:out value="${detail.chattelmortgage_registrationdate }"></c:out></td>
				</tr>
			</table>
		</div>
		<div class="gongshangtablewaicengdiv">
			<div class="gongshangyuanspdiv" style="width:95%;max-width:95%;">
				<span class="gongshangyuansp">抵押权人概况</span>
<!-- 				<span class="gongshangyuan"></span> -->
			</div>
			<table class="gongshangtable" cellpadding="0" cellspacing="0" style="width:95%;max-width:95%;min-width:95%;">
				<tr>
<!-- 					<td class="td_lableshuzi" style="text-align:center; color:#9a9a9a;">序号</td> -->
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">抵押权人名称</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">抵押权人证照/证件类型</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">证照/证件号码</td>
				</tr>
				<tr>
<!-- 					<td style="text-align:center;">1</td> -->
					<td style="text-align:center;"><c:out value="${detail.more }"></c:out></td>
					<td style="text-align:center;"></td>
					<td style="text-align:center;"></td>
				</tr>
			</table>
		</div>
		<div class="gongshangtablewaicengdiv">
			<div class="gongshangyuanspdiv" style="width:95%;max-width:95%;">
				<span class="gongshangyuansp">被担保债权概况</span>
<!-- 				<span class="gongshangyuan"></span> -->
			</div>
			<table class="gongshangtable" cellpadding="0" cellspacing="0" style="width:95%;max-width:95%;min-width:95%;">
				<tr>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">种类</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">数额</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">担保的范围</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">债务人履行债务的期限</td>
				</tr>
				<tr>
					<td style="text-align:center;"><c:out value="${detail.priclaseckind }"></c:out></td>
					<td style="text-align:center;"><c:out value="${detail.chattelmortgage_guaranteedamount }"></c:out></td>
					<td style="text-align:center;"></td>
					<td style="text-align:center;"><c:out value="${detail.pefperform }"></c:out>~<c:out value="${detail.pefperto }"></c:out></td> 
				</tr>
			</table>
		</div>
		<div class="gongshangtablewaicengdiv">
			<div class="gongshangyuanspdiv" style="width:95%;max-width:95%;">
				<span class="gongshangyuansp">抵押物概况</span>
<!-- 				<span class="gongshangyuan">1</span> -->
			</div>
			<table class="gongshangtable" cellpadding="0" cellspacing="0" style="width:95%;max-width:95%;min-width:95%;">
				<tr>
<!-- 					<td class="td_lableshuzi" style="text-align:center; color:#9a9a9a;">序号</td> -->
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">名称</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">所有权归属</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">数量、质量、状况、所在地等情况</td>
					<td class="td_lable" style="color:#9a9a9a; text-align:center;">备注</td>
				</tr>
				<tr>
<!-- 					<td style="text-align:center;">1</td> -->
					<td style="text-align:center;"><c:out value="${detail.guaname }"></c:out></td>
					<td style="text-align:center;"></td>
					<td style="text-align:center;"><c:out value="${detail.quan }"></c:out></td>
					<td style="text-align:center;"><c:out value="${detail.value }"></c:out></td>
				</tr>
			</table>
		</div>
	</c:if>
	<div style="height:30px;"></div>
</body>
</html>