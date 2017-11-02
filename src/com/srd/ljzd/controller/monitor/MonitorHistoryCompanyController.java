package com.srd.ljzd.controller.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.srd.ljzd.controller.base.BaseController;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.service.company.CompanyNumTrendOfEventService;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.LoggerUtils;

/**
 * 
 * @author vinta
 *
 */
@Controller
@RequestMapping("/hisCompany")
public class MonitorHistoryCompanyController extends BaseController{

	@Autowired
	private CompanyNumTrendOfEventService companyNumService;
	@Autowired
	private OperationLogService operationLogService;
	
	@RequestMapping("/toHisCompanyView.do")
	public String toHisCompanyView(String companyName,String selectedModuleIndex,HttpServletRequest request, Integer deep,HttpServletResponse response,Model model){
		if(companyName!=null){
			try {
				companyName = URLDecoder.decode(companyName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				LoggerUtils.error(e.getMessage(), e);
			}
		}
		
		if(companyName!=null&&companyName.contains("(")||companyName.contains(")")){//如果包含英文小括号
			StringBuffer buf = new StringBuffer(companyName);
			int index = -1;
			if((index=buf.indexOf("(")) >-1){
				buf.replace(index, index+1, "（");
			}
			index=-1;
			if((index=buf.indexOf(")"))>-1){
				buf.replace(index, index+1, "）");
			}
			companyName = buf.toString();
		}
		
		//查询各模块数量
		Map<String, String> companyNum=companyNumService.queryCompanyNum(companyName);
		
		if(null!=selectedModuleIndex){
			model.addAttribute("selectedModuleIndex", selectedModuleIndex);
		}else{
			model.addAttribute("selectedModuleIndex", 0);
		}
		
		model.addAttribute("companyName", companyName);
		model.addAttribute("companyNum",companyNum);
		model.addAttribute("deep",deep);
		
		//保存用户使用记录
    	ClientAccount account = super.getClientAccountFromSession(request);
		operationLogService.save(account.getAccountId(),account.getAccountName()
        		,"查看企业详情","企业名称 "+companyName,DateUtils.getCurrentDateStr(DateUtils.DEFAULT_PATTERN));
		
		return "modules/company/detail/company";
	}
}
