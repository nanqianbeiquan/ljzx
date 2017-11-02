package com.srd.ljzd.dao.thirdparty.biz;

import java.util.ArrayList;
import java.util.HashMap;

import com.alibaba.fastjson.JSONArray;
import com.srd.ljzd.dao.thirdparty.base.BaseDAO;
import com.srd.ljzd.entity.biz.BizMsg;

public interface BizDAO extends BaseDAO{
	//从大数据本地数据库获取数据
	public BizMsg remoteGongShangMsg(String companyName);
	//第三方获取数据
	public BizMsg remoteGongShangMsgWithThirdParty(String companyName);
	//商标
	public JSONArray getBrand(String companyName);
	
	public BizMsg remoteGongShangMsgWithQiChaCha(String companyName);
}
