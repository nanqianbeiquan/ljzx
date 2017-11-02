package com.srd.ljzd.daoImpl.thirdparty.opreationLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dao.thirdparty.opreationLog.OpreationLogDAO;
import com.srd.ljzd.daoImpl.thirdparty.base.BaseDAOImpl;
import com.srd.ljzd.util.Page;
@Repository("opreationLogDAO")
public class OpreationLogDAOImpl extends BaseDAOImpl implements OpreationLogDAO{
	public static String SAVE_URL = baseURL + Global.getConfig("saveCustomerOperation");
	public static String SEARCH_URL = baseURL + Global.getConfig("searchCustomerOperation");
	protected static Logger log = LogManager.getLogger(OpreationLogDAOImpl.class.getName());
	@Override
	public boolean save(String accountId, String accountName, String opType,
			String opContent, String creatDate) {
		Map<String, String> params = new HashMap<String, String>();  
		params.put("accountId", accountId);
		params.put("accountName", accountName);  
		params.put("opType", opType); 
		params.put("opContent", opContent); 
		params.put("creatDate", creatDate); 
		String result = super.remoteInvokeWithString(SAVE_URL, params);
		if(result!=null&&"success".equals(result)){
			return true;
		}
		return false;
	}
	@Override
	public Page<Map<String, Object>> getLog(String accountId,
			String actionType, String fromDate, String toDate, Integer pageNo,
			Integer pageSize) {
		Map<String, String> params = new HashMap<String, String>();  
		params.put("accountIds", accountId);
		params.put("startDate", fromDate);  
		params.put("stopDate", toDate); 
		params.put("opType", actionType); 
		params.put("pageSize", pageSize.toString()); 
		params.put("pageNum", pageNo.toString()); 
		params.put("sortType", "desc");
		JSONObject jsonObject = super.remoteInvoke(SEARCH_URL, params);
		
		Page<Map<String,Object>> targetPage = new Page<Map<String,Object>>();
		targetPage.setCurrentPageNo(pageNo);
		targetPage.setPageSize(pageSize);
		targetPage.setResults(new ArrayList<Map<String,Object>>());
		if(jsonObject!=null&&jsonObject.containsKey("count")){
			targetPage.setRecordSum(jsonObject.getLong("count"));
		}else{
			targetPage.setRecordSum((long)0);
		}
		if(jsonObject!=null&&jsonObject.containsKey("data")){
			JSONArray arr = jsonObject.getJSONArray("data");
			if(arr!=null&&arr.size()>0){
				Map<String,Object> record = null;
				for(int i=0;i<arr.size();i++){
					record = new HashMap<String, Object>();
					record.put("accountId", arr.getJSONObject(i).get("operation:accountId"));
					record.put("accountName", arr.getJSONObject(i).get("operation:accountName"));
					record.put("createTime", arr.getJSONObject(i).get("operation:creatDate"));
					record.put("opContent", arr.getJSONObject(i).get("operation:opContent"));
					record.put("actionType", arr.getJSONObject(i).get("operation:opType"));
					targetPage.getResults().add(record);
				}
			}
		}
		
		return targetPage;
	}



}
