/**   
* @Title: MonitorRelationPersonController.java 
* @Package com.srd.ljzd.controller.monitor 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年5月4日 下午2:28:57 
* @version V1.0   
*/
package com.srd.ljzd.controller.monitor;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorPersonEvent;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.service.monitor.MonitorPersonEventService;
import com.srd.ljzd.service.monitor.MonitorRelationPersonService;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;

/** 
 * @ClassName: MonitorRelationPersonController
 * @Description: 关系个人Controller
 * @author shiyong
 * @date 2017年5月4日 下午2:28:57
 *  
 */
@Controller
@RequestMapping("/monitorRelationPerson")
public class MonitorRelationPersonController {

	@Autowired
	private MonitorRelationPersonService monitorRelationPersonService;
	
	@Autowired
	private MonitorPersonEventService monitorPersonEventService;
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	/** 
	* @Title: addMonitorRelationPerson 
	* @Description: 添加关系个人
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月4日 下午2:30:14
	*/
	@ResponseBody
	@RequestMapping("/addMonitorRelationPerson")
	public ResultBean addMonitorRelationPerson(HttpServletRequest request, HttpServletResponse response) {
		ResultBean result = new ResultBean();
		
		String monitorId = request.getParameter("monitorId");
		String name = request.getParameter("name");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String idNumber = request.getParameter("idNumber");
		
		try {
			if(name!=null&&!"".equals(name)){
				name = URLDecoder.decode(name, "UTF-8");
			}
			if(province!=null&&!"".equals(province)){
				province = URLDecoder.decode(province, "UTF-8");
			}
			if(city!=null&&!"".equals(city)){
				city = URLDecoder.decode(city, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		
		ClientAccount account = (ClientAccount) request.getSession().getAttribute("account");
		String accountId = account.getAccountId();
		
		MonitorRelationPerson monitorRelationPerson = new MonitorRelationPerson();
		monitorRelationPerson.setMonitorId(monitorId);
		monitorRelationPerson.setAccountId(accountId);
		monitorRelationPerson.setName(name);
		monitorRelationPerson.setIdNumber(idNumber);
		monitorRelationPerson.setCity(city);
		monitorRelationPerson.setProvince(province);
		
		MonitorRelationPerson relationPerson = monitorRelationPersonService.getMonitorRelationPerson(monitorRelationPerson);
		
		if(null != relationPerson){
			//抛出异常重复加入	
			result.setResultCode("1");
			result.setResultMsg("重复加入该个人信息");
		}else{
			monitorRelationPerson.setEventLevel("");
			monitorRelationPerson.setNormalEventNum(0);
			monitorRelationPerson.setAttentionEventNum(0);
			monitorRelationPerson.setWarnEventNum(0);
			monitorRelationPerson.setRiskLabel("");
			
			Calendar cal = Calendar.getInstance();
			monitorRelationPerson.setMonitorDate(cal.getTime());
			monitorRelationPerson.setCreateTime(cal.getTime());
			cal.add(Calendar.YEAR, -1);
			monitorRelationPerson.setInfoShowDate(cal.getTime());
			monitorRelationPerson.setDeleteFlag("0");
			
			result = monitorRelationPersonService.saveMonitorRelationPerson(monitorRelationPerson);
		}
		
		return result;
	}
	
	/** 
	* @Title: deleteMonitorRelationPersons 
	* @Description: 删除关系自然人
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ResultBean 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月26日 上午11:11:32
	*/
	@ResponseBody
	@RequestMapping("/deleteMonitorRelationPersons")
	public ResultBean deleteMonitorRelationPersons(HttpServletRequest request, HttpServletResponse response) {
		
		String relationPersonIds = request.getParameter("relationPersonIds");
		
		List<String> relationPersonIdList = new ArrayList<String>();
		
		if (StringUtils.isNotEmpty(relationPersonIds)) {
			String[] idArr = relationPersonIds.split(",");
			
			relationPersonIdList = Arrays.asList(idArr);
		}

		ResultBean resultBean = monitorRelationPersonService.deleteMonitorRelationPersons(relationPersonIdList);
		
		return resultBean;
	}
	
	/** 
	* @Title: getMonitorRelationPersonPage 
	* @Description: 获取关系自然人风险信息列表
	* @param @param request
	* @param @param response
	* @param @return 设定文件 
	* @return ModelAndView 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 上午9:58:20
	*/
	@RequestMapping("/getMonitorRelationPersonPage")
	public String getMonitorRelationPersonPage(HttpServletRequest request,HttpServletResponse response, Model model){
		String monitorId = request.getParameter("monitorId");
		
		// 从request中获取dto数据
		int currentPageNo = 1;
		if (!StringUtils.isEmpty(request.getParameter("currentPageNo"))) {
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		Page<MonitorRelationPerson> page = monitorRelationPersonService.getMonitorRelationPersonPage(monitorId, currentPageNo, pageSize);

		model.addAttribute("page", page);
		
		Calendar cal = Calendar.getInstance();
		
		String today = sdfDate.format(cal.getTime()) + " 00:00:00.0";
		
		model.addAttribute("today", today);
		
		return "/modules/monitor/monitorCompany/monitorRelationPersonList";
	}
	@RequestMapping("/getMonitorRelationPersonPageWithJOSN")
	@ResponseBody
	public Object getMonitorRelationPersonPageWithJOSN(HttpServletRequest request,HttpServletResponse response, Model model){
		String monitorId = request.getParameter("monitorId");
		
		// 从request中获取dto数据
		int currentPageNo = 1;
		if (!StringUtils.isEmpty(request.getParameter("currentPageNo"))) {
			currentPageNo = Integer.valueOf(request.getParameter("currentPageNo"));
		}
		int pageSize = 6;
		if (!StringUtils.isEmpty(request.getParameter("pageSize"))) {
			pageSize = Integer.valueOf(request.getParameter("pageSize"));
		}
		
		Page<MonitorRelationPerson> page = monitorRelationPersonService.getMonitorRelationPersonPage(monitorId, currentPageNo, pageSize);
		
		return page;
	}
	/** 
	* @Title: viewMonitorRelationPersonDetail 
	* @Description: 查看关系自然人详情页面
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月24日 下午4:25:11
	*/
	@RequestMapping("viewMonitorRelationPersonDetail")
	public String viewMonitorRelationPersonDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
		String deep = request.getParameter("deep");
		String id = request.getParameter("id");
		
		MonitorRelationPerson monitorRelationPerson = monitorRelationPersonService.getMonitorRelationPersonById(id);
		
		List<String> labelList = new ArrayList<String>();
		
		if(StringUtils.isNotEmpty(monitorRelationPerson.getRiskLabel())){
			if (monitorRelationPerson.getRiskLabel().contains(",")) {
				String[] array = monitorRelationPerson.getRiskLabel().split(",");
				
				for (String record : array) {
					if (record != null && !record.trim().equals("")) {
						labelList.add(record.trim());
					}
				}
			}else{
				labelList.add(monitorRelationPerson.getRiskLabel());
			}
		}else{
			labelList.add("无");
		}

		Map<String, Object> map = null;
		
		if (StringUtils.isNotEmpty(id)) {
			map = monitorPersonEventService.getPersonEventNumByType(monitorRelationPerson);
		}
		
		//显示标题栏各分类的数量
		for (String key : map.keySet()) {
			model.addAttribute(key, map.get(key));
		}
		
		model.addAttribute("monitorRelationPerson", monitorRelationPerson);
		model.addAttribute("labelList", labelList);
		model.addAttribute("deep", deep);
		
		return "/modules/monitor/monitorCompany/monitorRelationPersonDetail";
	}
	
	/** 
	* @Title: getRelationPersonEventList 
	* @Description: 获取关系自然人事件列表
	* @param @param name
	* @param @param idNumber
	* @param @param province
	* @param @param city
	* @param @param eventSubType
	* @param @param pageNo
	* @param @param pageSize
	* @param @param model
	* @param @param request
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 上午11:06:46
	*/
	@RequestMapping("getRelationPersonEventList")
	public String getRelationPersonEventList(String name,String idNumber,String province,String city,String eventSubType,Integer pageNo,Integer pageSize,Model model,HttpServletRequest request){
		
		try {
			name = URLDecoder.decode(name, "UTF-8");
			province = URLDecoder.decode(province, "UTF-8");
			city = URLDecoder.decode(city, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if(pageNo==null){
			pageNo=1;
		}
		
		if(pageSize==null){
			pageSize=6;
		}
		
		if(eventSubType==null){
			eventSubType="31";
		}
		
		List<MonitorPersonEvent> events=null;
		
		Page<MonitorPersonEvent> eventsPage= monitorPersonEventService.getPersonEvents(pageNo, pageSize, name, idNumber, province, city, eventSubType);
		
		if(null!=eventsPage&&null!=eventsPage.getResults()&&eventsPage.getResults().size()>0){
			events = eventsPage.getResults();
		}
	
		JSONArray targetArray = new JSONArray();
		
		if(events!=null&&events.size()>0){
			JSONObject item = null;
			Pattern p = null;
			Matcher m = null;
			for(MonitorPersonEvent event : events){
				JSONArray array =  monitorPersonEventService.applyEventDetailList(event.getEventID(), eventSubType);
				if(array!=null&&array.size()>0){
					item = array.getJSONObject(0);
					item.put("eventLevel", event.getEventLevel());
						if (eventSubType.equals("31")) {// 司法案件
							// 当事人高亮
							if (item.containsKey("dangshiren") && item.getString("dangshiren") != null && item.getString("dangshiren").contains(name)) {
								String shenfen=item.getString("dangshiren");
								StringBuffer shenfenNew=null;
								StringBuffer  sf=new StringBuffer(shenfen);
								p = Pattern.compile(name);
								m = p.matcher(sf);
								while(m.find()){
									shenfenNew = sf.replace(m.start(),m.end(),"<span style=\"background:#d1edfe;\">" + name + "</span>");
								}
								item.put("indentity", shenfenNew);
							} else {
								item.put("indentity", "无");
							}
						}
						if (eventSubType.equals("30")) {//开庭公告
							// 当事人高亮
							if (item.containsKey("dang_shi_ren") && item.getString("dang_shi_ren") != null && item.getString("dang_shi_ren").contains(name)) {
								String shenfen=item.getString("dang_shi_ren");
								StringBuffer shenfenNew=null;
								StringBuffer  sf=new StringBuffer(shenfen);
								p = Pattern.compile(name);
								m = p.matcher(sf);
								while(m.find()){
									shenfenNew = sf.replace(m.start(),m.end(),"<span style=\"background:#d1edfe;\">" + name + "</span>");
								}
								item.put("indentity", shenfenNew);
							} else {
								item.put("indentity", "无");
							}
						}
					targetArray.add(item);
				}
			}
		}
		
		String moduleName = "";
		
		if(null!=eventSubType){
			switch(eventSubType){
				case "30"://开庭公告
					moduleName = "kaiTingGongGao";
					break;
				case "31"://司法案件
					moduleName = "siFaAnJian";
					break;
				case "35"://被执行人信息
					moduleName = "personSubject";
					break;
				case "36"://失信信息
					moduleName = "dishonestInfo";
					break;
			}
		}

		model.addAttribute(moduleName+"Array", targetArray);
		model.addAttribute(moduleName+"Page", eventsPage);
		
		return "/modules/monitor/monitorCompany/"+moduleName;
	}
	
	/** 
	* @Title: getInstrumentDetail 
	* @Description: 获取司法文件详情
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return Object 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 上午11:49:21
	*/
	@ResponseBody
	@RequestMapping("/getInstrumentDetail")
	public Object getInstrumentDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
		String key = request.getParameter("key");
		String eventSubType = request.getParameter("eventSubType");
		
		JSONArray array =  monitorPersonEventService.applyEventDetailList(key, eventSubType);
		JSONArray targetArray = new JSONArray();
		JSONObject item=null;
		if(array!=null&&array.size()>0){
			item = array.getJSONObject(0);
			//裁判文书高亮
			if (item.containsKey("quanwen") && item.getString("quanwen") != null) {
				String quanwen=item.getString("quanwen");
				StringBuffer contentNew=null;
				StringBuffer  content=new StringBuffer(quanwen);
				
				String[] keyWords = {"裁定如下","判决如下"};
				
				Pattern p =null;
				Matcher m =null;
				for(int i=0;i<keyWords.length;i++){
					p = Pattern.compile(keyWords[i]);
					m = p.matcher(content);
					while(m.find()){
						contentNew = content.replace(m.start(),m.end(),"<span style=\"background:#d1edfe;\">"+keyWords[i]+"</span>");
					}
				}
				item.put("quanwen", contentNew);
			} else {
				item.put("quanwen", "--");
			}
			targetArray.add(item);
		}
		return array;
	}
	
	/** 
	* @Title: getDishonestInfoDetail
	* @Description: 获取失信信息详情
	* @param @param request
	* @param @param response
	* @param @param model
	* @param @return 设定文件 
	* @return Object 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年5月31日 上午11:48:56
	*/
	@ResponseBody
	@RequestMapping("/getDishonestInfoDetail")
	public Object getDishonestInfoDetail(HttpServletRequest request,HttpServletResponse response, Model model) {
		String key = request.getParameter("key");
		String eventSubType = request.getParameter("eventSubType");
		
		JSONArray array =  monitorPersonEventService.applyEventDetailList(key, eventSubType);
		
		return array;
	}
}
