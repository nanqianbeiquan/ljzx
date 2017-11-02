package com.srd.ljzd.controller.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.service.monitor.MonitorCompanyFinanceService;
import com.srd.ljzd.service.monitor.ThirdPartInterfaceService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.LoggerUtils;

@Controller
@RequestMapping("/finance")
public class MonitorCompanyFinanceController extends BaseController{
	//通用
    @Autowired
    ThirdPartInterfaceService thirdPartInterfaceService;
    @Autowired
    MonitorCompanyFinanceService monitorCompanyFinanceService;
    @Autowired
	private OperationLogService operationLogService;
    
    @RequestMapping("/toFinanceView.do")
	public String toFinanceView(String cn,String deep,HttpServletRequest request, HttpServletResponse response, Model model ){
		
    	model.addAttribute("cn", cn);
    	model.addAttribute("deep", deep);
    	//保存用户使用记录
    	ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"查看财务分析","企业名称 "+cn,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return "/modules/finance/finance";
	}
    
	@RequestMapping("/getFinanceData")
	@ResponseBody
	public Object getFinanceData(String companyName){
		
		try {
			if(companyName!=null){
				companyName = URLDecoder.decode(companyName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			
		}
		
		Map<String,String> params = new HashMap<String,String>();
		params.put("companyName", companyName);
		
		JSONObject result = thirdPartInterfaceService.invoke( Global.getConfig("getFinanceData"), params);
		JSONObject resultData = null;
		if(result!=null&&"200".equals(result.getString("respCode")) && "success".equals(result.getString("respMsg"))){
			try{
				resultData = result.getJSONObject("data");
			}catch(JSONException e){
				LoggerUtils.error(Global.getConfig("getFinanceData") + "参数：" +params + "remote error ,result=" + result.toString());
				return null;
			}
		}
		if(resultData!=null){
			//构造分析结论
			Map<String, Object> summaryAnalysisData =  monitorCompanyFinanceService.getSummaryAnalysisData(resultData);
			//System.out.println("---------------------开始分析，companyName="+companyName+"----------------------------------");
			Map<String, Object> summaryAnalysis = monitorCompanyFinanceService.buildSummaryAnalysis(summaryAnalysisData);
			//System.out.println("结果："+summaryAnalysis);
			if(summaryAnalysis!=null&&!summaryAnalysis.isEmpty()){
				resultData.put("summaryAnalysis", summaryAnalysis);
			}else{
				resultData.put("summaryAnalysis", null);
			}
			//构造各维度分析
			Map<String, Object> eachItemAnalysisData = monitorCompanyFinanceService.getEachItemAnalysisData(resultData);
			System.out.println(eachItemAnalysisData);
			//resultData.put("eachItemAnalysisData", eachItemAnalysisData);
			Map<String, Object> eachItemAnalysis = monitorCompanyFinanceService.buildEachItemAnalysis(eachItemAnalysisData);
			
			if(eachItemAnalysis!=null&&!eachItemAnalysis.isEmpty()){
				resultData.put("eachItemAnalysis", eachItemAnalysis);
			}else{
				resultData.put("eachItemAnalysis", null);
			}
			//System.out.println(eachItemAnalysis);
			
		}
		if(result==null){
			return new HashMap<String,Object>();
		}
		return result;
	}
}
