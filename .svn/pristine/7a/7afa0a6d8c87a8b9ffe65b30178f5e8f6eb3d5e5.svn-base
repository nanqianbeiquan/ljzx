/**   
* @Title: MonitorDailyReportController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月15日 下午2:31:10 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.monitor.MonitorUserEventLevelCompanyNumTrend;
import com.srd.ljzd.service.monitor.MonitorUserEventLevelCompanyNumTrendService;
import com.srd.ljzd.util.DateUtils;

/** 
 * @ClassName: MonitorDailyReportController
 * @Description: 动态监控日报Controller
 * @author shiyong
 * @date 2016年11月15日 下午2:31:10
 *  
 */
@Controller
@RequestMapping("/monitorDailyReport")
public class MonitorDailyReportController {
	
	@Autowired
	private MonitorUserEventLevelCompanyNumTrendService monitorUserEventLevelCompanyNumTrendService;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	/** 
	* @Title: getEventNumTrend
	* @Description: 获取各事件等级公司数量趋势
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	* @author shiyong
	* @date 2016年11月15日 下午3:11:11
	*/
	@RequestMapping("/getCompanyNumTrendOfEventLevel")
	@ResponseBody
	public Map<String,Object> getCompanyNumTrendOfEventLevel(HttpServletRequest request, HttpServletResponse response){
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("currentAccount");
		
		String accountId = account.getAccountId();
		
		String cycle = request.getParameter("cycle");
		
		List<MonitorUserEventLevelCompanyNumTrend> monitorUserEventLevelCompanyNumTrendList = monitorUserEventLevelCompanyNumTrendService.queryMonitorUserEventLevelCompanyNumTrendListByCycle(accountId, cycle);
		
		List<Date> dateList = null;
		
		if("3".equals(cycle)){
			dateList = DateUtils.getDateListByMonthNum(new Date(), 12, 1);
		}else if("2".equals(cycle)){
			dateList = DateUtils.getDateListByWeekNum(new Date(), 12, 2);
		}else{
			dateList = DateUtils.getDateListByNum(new Date(), 7);
		}
		
		String warnNumArray = "";
		String attentionNumArray = "";
		String normalNumArray = "";
		String dateArray = "";
		
		boolean flag = true;
		
		for(int i=(dateList.size()-1);i>=0;i--){
			flag = true;
			
			dateArray += sdfDate.format(dateList.get(i)) + ",";
			
			for (MonitorUserEventLevelCompanyNumTrend trend : monitorUserEventLevelCompanyNumTrendList) {
				if(trend.getReportDate().compareTo(dateList.get(i)) == 0){
					warnNumArray += trend.getWarnCompanyNum() + ",";
					attentionNumArray += trend.getAttentionCompanyNum() + ",";
					normalNumArray += trend.getNormalCompanyNum() + ",";
					
					flag = false;
					
					break;
				}
			}
			
			if(flag){
				warnNumArray += ",";
				attentionNumArray += ",";
				normalNumArray += ",";
			}
		}
		
		//去掉最后一个分号
		dateArray = dateArray.substring(0, dateArray.length()-1);
		warnNumArray = warnNumArray.substring(0, warnNumArray.length()-1);
		attentionNumArray = attentionNumArray.substring(0, attentionNumArray.length()-1);
		normalNumArray = normalNumArray.substring(0, normalNumArray.length()-1);
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("dateArray", dateArray);
		map.put("warnNumArray", warnNumArray);
		map.put("attentionNumArray", attentionNumArray);
		map.put("normalNumArray", normalNumArray);
		
		return map;
	}
	
}
