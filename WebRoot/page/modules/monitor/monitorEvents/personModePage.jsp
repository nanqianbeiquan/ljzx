<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/page/common/base.jsp"%>

<div class="person_mode mode_block">
                	<div class="filter_block">
                    	<div class="filter_title_block">
                    		<div class="inline_div title selected">企业法人代表</div>
                    		<div class="inline_div title">股东</div>
                    		<div class="inline_div title">高管</div>
                    		<div class="relative_left_block">
                    			<div class="triangle-down-border filter_arr" style="left:48px;">
                    				<div class="triangle-down-border-content"></div>
                    			</div>
                    		</div>
                    	</div>
                    	<div class="filter_option_block">
                    		<div id="leagalPersonDiv" class="option_list" style="height:70px;overflow:hidden;">
                    			<c:forEach items="${corporationLegalPersonList }" var="record" varStatus="i">
									<c:choose> 
											  <c:when test="${fn:contains(personNameStrs,record.personNameInProperties)}">   
											    <div  add="1" class="inline_div option selected">${record.personNameInProperties}</div>
											  </c:when>
											  <c:otherwise>   
											    <div class="inline_div option">${record.personNameInProperties}</div>
											  </c:otherwise> 
									</c:choose> 
								</c:forEach> 
                    		</div>
                    		<div id="stockHolderDiv" class="option_list hidden" style="height:70px;overflow:hidden;">
                    			<c:forEach items="${stockholderList }" var="record" varStatus="i">
									<c:choose> 
											  <c:when test="${fn:contains(personNameStrs,record.personNameInProperties)}">   
											    <div  add="1" class="inline_div option selected">${record.personNameInProperties}</div>
											  </c:when> 
											  <c:otherwise>   
											    <div class="inline_div option">${record.personNameInProperties}</div>
											  </c:otherwise> 
									</c:choose> 
								</c:forEach>
                    		</div>
                    		<div id="managerDiv" class="option_list hidden" style="height:70px;overflow:hidden;">
                    			<c:forEach items="${managerList}" var="record" varStatus="i">
									 <c:choose> 
											   <c:when test="${fn:contains(personNameStrs,record.personNameInProperties)}">   
											    <div  add="1" class="inline_div option selected">${record.personNameInProperties}</div>
											  </c:when> 
											  <c:otherwise>   
											    <div class="inline_div option">${record.personNameInProperties}</div>
											  </c:otherwise> 
									</c:choose>
								</c:forEach>
                    		</div>
                    		<div class="relative_right_block">
                    			<div class="triangle_up_btn inline_div" id="option_up_btn"></div>
                    			<div class="triangle_down_btn inline_div" id="option_down_btn"></div>
                    		</div>
                    	</div>
                    </div>
                    <div class="input_block">
                        <div class="name_input line">
                            
                            <div class="inline_div span"><span style="color:red;position:relative;top:2px;left:-6px;">*</span>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</div><div class="value inline_div">
                                <input class="long_value" type="text" id="ppersonName"/>
                            </div>
                        </div>
                        <div class="tips_block">
                            
                        </div>
   
           <div class="area_input line">
                            <div class="inline_div span"><span style="color:red;position:relative;top:2px;left:-6px;">*</span>常住地：</div>
                            <div class="value inline_div">
<!--                            <label class="control-label" for="province">请选择省份</label> -->
                                <select class="dist_select" style="height:26px;width:115px;" id="pprovince">
                   				 	<option value="载入中">载入中</option>
                				</select>
<!--           					<label class="control-label" for="city">请选择市</label> -->

								 <select class="dist_select" style="height:26px;margin-left:10px;width:115px;" id="pcity">
      				  				 <option value="载入中">载入中</option>
              				     </select>
                            </div>
                        </div>
                        <div class="tips_block">
                            请尽量填写完整关系人的长期生活或工作地点，我们将为您提供更准确的信息。
                        </div>
                        <div class="card_input line">
                            <div class="inline_div span">身份证：
                            </div><div class="value inline_div">
                                <input class="long_value" type="text" id="pidNumber"/>
                            </div>
                        </div>
                        <div class="tips_block">
                            填写的信息越多,匹配的个人信息越精准
                        </div>
                    </div>
                    <div class="btn_block">
                        <div class="inline_div tianJia" id="addRealPerson" onclick="addRelationPerson();">
                            添加
                        </div>
                    </div>
                </div>