<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="info_title">
    <div class="inline_div title">登记信息</div>
    <div class="inline_div count hidden"></div>
    <div class="inline_div message hidden"></div>
</div>
<div class="info_table">
    <table class="company_info">
        <tbody><tr>
            <td class="span_title">
                统一社会信用代码<br/>/注册号
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${ not empty brefCompany and not empty brefCompany.tyshxy_code and brefCompany.tyshxy_code !='null'}">
                       <c:out value="${brefCompany.tyshxy_code }"></c:out>
                   </c:when>

                   <c:when test="${not empty brefCompany and not empty brefCompany.registrationno and brefCompany.registrationno !='null'}">
                       <c:out value="${brefCompany.registrationno }"></c:out>
                   </c:when>                
                   <c:when test="${not empty brefCompany and not empty brefCompany.zch and brefCompany.zch !='null'}">
                       <c:out value="${brefCompany.zch }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td class="span_title">
                经营状态
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.registrationstatus and brefCompany.registrationstatus !='null' and brefCompany.registrationstatus !=''}">
                       <c:out value="${brefCompany.registrationstatus }"></c:out>
                   </c:when>
                   <c:when test="${not empty brefCompany and not empty brefCompany.entstatus and brefCompany.entstatus !='null' and brefCompany.entstatus !=''}">
                       <c:out value="${brefCompany.entstatus }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
        </tr>
        
        <tr>
            <td class="span_title">
                公司类型
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.enterprisetype and brefCompany.enterprisetype !='null'}">
                       <c:out value="${brefCompany.enterprisetype }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td class="span_title">
                成立时间
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${ not empty brefCompany and not empty brefCompany.establishmentdate and brefCompany.establishmentdate !='null' and brefCompany.establishmentdate  ne '\\\\N'}">
                       <c:out value="${brefCompany.establishmentdate }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="span_title">
                法定代表人
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${ not empty brefCompany and not empty brefCompany.legalrepresentative and brefCompany.legalrepresentative !='null'}">
                       <c:out value="${brefCompany.legalrepresentative }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td class="span_title">
                营业期限
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.validityfrom and brefCompany.validityfrom !='null'}">
                       <c:out value="${brefCompany.validityfrom }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>~
                 <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.validityto and brefCompany.validityto !='null'}">
                       <c:out value="${brefCompany.validityto }"></c:out>
                   </c:when>
                   <c:otherwise>长期</c:otherwise>
                </c:choose>               
            </td>
        </tr>
        <tr>
            <td class="span_title">
                注册资本
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.orgregisteredcapital and brefCompany.orgregisteredcapital !='null'}">
                       <c:out value="${brefCompany.orgregisteredcapital }"></c:out>
                   </c:when>
                   <c:when test="${not empty brefCompany and not empty brefCompany.registeredcapital and brefCompany.registeredcapital !='null'}">
                       <fmt:formatNumber type="number" pattern="#,##0.00">${brefCompany.registeredcapital }</fmt:formatNumber>万元
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
            <td class="span_title">
                发照日期
            </td>
            <td class="span_value">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.approvaldate and brefCompany.approvaldate !='null'}">
                       <c:out value="${brefCompany.approvaldate }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="span_title">
                登记机关
            </td>
            <td class="span_value range" colspan="3">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.registrationinstitution and brefCompany.registrationinstitution !='null'}">
                       <c:out value="${brefCompany.registrationinstitution }"></c:out>
                   </c:when>
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="span_title">
                企业地址
            </td>
            <td class="span_value range" colspan="3">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.residenceaddress and brefCompany.residenceaddress !='null' and brefCompany.residenceaddress !=''}">
                       <c:out value="${brefCompany.residenceaddress }"></c:out>
                   </c:when>
                   <c:when test="${not empty brefCompany and not empty brefCompany.businessplace and brefCompany.businessplace !='null' and brefCompany.businessplace !=''}">
                       <c:out value="${brefCompany.businessplace }"></c:out>
                   </c:when>
                   <c:when test="${not empty brefCompany and not empty brefCompany.mianbusinessplace and brefCompany.mianbusinessplace !='null' and brefCompany.mianbusinessplace !=''}">
                       <c:out value="${brefCompany.mianbusinessplace }"></c:out>
                   </c:when>                                        
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
        </tr>
        <tr>
            <td class="span_title">
                经营范围
            </td>
            <td class="span_value range" colspan="3">
                <c:choose>
                   <c:when test="${not empty brefCompany and not empty brefCompany.businessscope and brefCompany.businessscope !='null'  and brefCompany.businessscope !=''}">
                       <c:out value="${brefCompany.businessscope }"></c:out>
                   </c:when>
                   <c:when test="${not empty brefCompany and not empty brefCompany.zsopscope and brefCompany.zsopscope !='null'  and brefCompany.zsopscope !=''}">
                       <c:out value="${brefCompany.zsopscope }"></c:out>
                   </c:when>                   
                   <c:otherwise>--</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </tbody></table>
</div>
