package com.srd.ljzd.dao.monitor;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskSituation;

public interface MonitorCustomRiskSituationDao extends BaseDao<MonitorCustomRiskSituation, String>{

	List<MonitorCustomRiskSituation> getListByMonitorId(String monitorId);
   
}
