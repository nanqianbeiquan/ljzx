package com.srd.ljzd.daoImpl.thirdparty.relationMap;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.srd.ljzd.dao.thirdparty.relationMap.RelationMapDao;
import com.srd.ljzd.daoImpl.thirdparty.base.BaseDAOImpl;
import com.srd.ljzd.util.JerseyUtil;
@Repository("relationMapDAO")
public class RelationMapDaoImpl extends BaseDAOImpl implements RelationMapDao {

	@Override
	public JSONObject invoke(String relativeURI, Map<String, String> params) {
		return JerseyUtil.sendHttpFormRequest(super.baseURL+relativeURI, params);
	}

	@Override
	public JSONArray invokeReturnArray(String relativeURI, Map<String, String> params) {
		return JerseyUtil.sendHttpFormRequestReturnArray(super.baseURL+relativeURI, params);
	}

	@Override
	public String invokeReturnString(String relativeURI,
			Map<String, String> params) {
		return JerseyUtil.sendHttpFormRequestString(super.baseURL+relativeURI, params);
	}

}
