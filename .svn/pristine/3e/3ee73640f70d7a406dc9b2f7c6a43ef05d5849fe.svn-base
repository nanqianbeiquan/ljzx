package com.srd.ljzd.daoImpl.thirdparty.Blacklist;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.config.Global;
import com.srd.ljzd.dao.thirdparty.blacklist.BlacklistDAO;
import com.srd.ljzd.daoImpl.thirdparty.base.BaseDAOImpl;
@Repository("blacklistDAO")
public class BlacklistDAOImpl extends BaseDAOImpl implements BlacklistDAO{
	public static String url = baseURL + Global.getConfig("checkSystemBlacklist");
	@Override
	public JSONObject checkSystemBlacklist(String companyName,String cnt) {
		Map<String, String> params = new HashMap<String, String>();  
		params.put("companyName", companyName);
		params.put("cnt", cnt);  
		JSONObject result = super.remoteInvoke(url, params);
		return result;
	}
}
