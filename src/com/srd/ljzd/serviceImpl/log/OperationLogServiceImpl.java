package com.srd.ljzd.serviceImpl.log;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.thirdparty.opreationLog.OpreationLogDAO;
import com.srd.ljzd.service.log.OperationLogService;
import com.srd.ljzd.util.Page;
@Service("operationLogService")
public class OperationLogServiceImpl implements OperationLogService{
	@Autowired
	OpreationLogDAO opreationLogDAO;
	@Override
	public boolean save(String accountId, String accountName, String opType,
			String opContent, String creatDate) {
		return opreationLogDAO.save(accountId, accountName, opType, opContent, creatDate);
	}
	@Override
	public Page<Map<String, Object>> getLog(String accountId,
			String actionType, String fromDate, String toDate, Integer pageNo,
			Integer pageSize) {
		Page<Map<String, Object>> target = opreationLogDAO.getLog(accountId,actionType,fromDate,toDate,pageNo,pageSize);
		return target;
	}

}
