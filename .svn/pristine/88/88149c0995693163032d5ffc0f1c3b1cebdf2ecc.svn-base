package com.srd.ljzd.service.monitor;

import java.util.List;
import java.util.Set;

import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskInfo;
import com.srd.ljzd.entity.monitor.MonitorCustomRiskSituation;

public interface MonitorCustomRiskSituationService {
	
	List<MonitorCustomRiskSituation> getLCustomRiskList(String monitorId);

	MonitorCustomRiskSituation getById(String customRiskId);

	ResultBean addCustomRisk(String riskSituation, String oldRiskSituation,
			MonitorCompany company, Set<MonitorCustomRiskInfo> riskInfoSet);

	boolean update(MonitorCustomRiskSituation record);

	ResultBean update(MonitorCustomRiskSituation customRisk, String riskSituation,
			Set<MonitorCustomRiskInfo> riskInfoSet);
	
	/**
	 * 构造最新标签
	 * @param riskLabel
	 * @param customRiskCategory
	 * @return
	 */
	public String buildLabel(Set<MonitorCustomRiskInfo> riskInfoSet);

	ResultBean clearCustomRisk(MonitorCompany company);

	boolean deleteCustomRisk(MonitorCustomRiskSituation record);
}
