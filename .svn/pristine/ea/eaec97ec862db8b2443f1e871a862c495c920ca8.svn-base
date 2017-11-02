package com.srd.ljzd.service.log;

import java.util.Map;

import com.srd.ljzd.util.Page;

public interface OperationLogService {

	boolean save(String accountId,String accountName,String opType,String opContent,String creatDate);

	Page<Map<String, Object>> getLog(String accountId, String actionType,
			String fromDate, String toDate, Integer pageNo, Integer pageSize);
}
