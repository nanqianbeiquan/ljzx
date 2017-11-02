package com.srd.ljzd.serviceImpl.monitor;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dao.monitor.MonitorPersonEventDao;
import com.srd.ljzd.entity.monitor.MonitorPersonEvent;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.service.monitor.MonitorPersonEventService;
import com.srd.ljzd.util.JerseyUtil;
import com.srd.ljzd.util.Page;


@Service
public class MonitorPersonEventServiceImpl implements MonitorPersonEventService {
	
	protected static Logger logger = LogManager.getLogger(MonitorPersonEventServiceImpl.class.getName());

	@Autowired
	MonitorPersonEventDao monitorPersonEventDao;

	private String dataService = Global.getConfig("dataService");
	private String searchEventsDetails = Global.getConfig("searchEventsDetails");
	
	@Override
	public JSONArray applyEventDetailList(String keys, String type) {
		String url = dataService + searchEventsDetails;

		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("keys", keys);
		paramMap.put("type", type);

		JSONObject jsonResult = JerseyUtil.sendHttpFormRequest(url, paramMap);

		if (jsonResult != null && jsonResult.containsKey("respCode")) {
			if (jsonResult.getString("respCode").equals("200")&& jsonResult.containsKey("data")) {
				if (jsonResult.get("data") != null&& !jsonResult.get("data").equals("")&& !jsonResult.get("data").equals("[]")) {
					JSONArray array = jsonResult.getJSONArray("data");
					return array;
				}
			}
		}
		return null;
	}


	@Override
	public Page<MonitorPersonEvent> getPersonEvents(int currentPageNo,int pageSize, String name, String idNumber, String province,String city, String eventSubType) {
		
		return monitorPersonEventDao.getPersonEvents(currentPageNo,pageSize,name, idNumber, province,city, eventSubType);
		
	}

	@Override
	public Map<String, Object> getPersonEventNumByType(MonitorRelationPerson monitorRelationPerson) {
		
		Map<String, Object> map = monitorPersonEventDao.getPersonEventNumByType(monitorRelationPerson);
		
		return map;
	}
	
	
}
