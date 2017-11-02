package com.srd.ljzd.dao.thirdparty.opreationLog;

import java.util.Map;

import com.srd.ljzd.dao.thirdparty.base.BaseDAO;
import com.srd.ljzd.util.Page;

public interface OpreationLogDAO extends BaseDAO {

	boolean save(String accountId,String accountName,String opType,String opContent,String creatDate);

	Page<Map<String, Object>> getLog(String accountId, String actionType,
			String fromDate, String toDate, Integer pageNo, Integer pageSize);
}
