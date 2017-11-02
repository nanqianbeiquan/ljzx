package com.srd.ljzd.daoImpl.monitor;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorCustomRiskSituationDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskSituation;
@Repository
public class MonitorCustomRiskSituationDaoImpl extends BaseDaoImpl<MonitorCustomRiskSituation, String> implements MonitorCustomRiskSituationDao{

	@Override
	public List<MonitorCustomRiskSituation> getListByMonitorId(String monitorId) {
		String hql = "select risk from MonitorCustomRiskSituation risk where risk.monitorCompany.monitorID=? and deleteFlag='0' order by createTime desc";
		return super.getListByHql(hql, monitorId);
	}

}
