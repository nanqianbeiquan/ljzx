<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<input type="hidden" id="companyName" value="${companyName}">
<script>
var companyName='${companyName}';
getMediaOpinion(companyName);

</script>
				<di id="mediaDiv"></di>
				<div class="page_block">
					<div class="page_plug" id="mediaPageDiv" ></div>
				</div>
                <di id="negativeDiv"></di>
                <div class="page_block">
           			<div class="page_plug" id="negativePageDiv"></div>
       			</div>
       			<!-- style="display:none" -->
       			<div id="newsDetailDiv"></div>
