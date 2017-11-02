package com.srd.ljzd.serviceImpl.monitor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.client.ClientAccountDao;
import com.srd.ljzd.dao.monitor.MonitorRelationCompanyDao;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationCompany;
import com.srd.ljzd.service.monitor.MonitorCompanyService;
import com.srd.ljzd.service.monitor.MonitorRelationCompanyService;
import com.srd.ljzd.util.Page;

@Service
public class MonitorRelationCompanyServiceImpl implements MonitorRelationCompanyService {

	@Autowired
	private MonitorRelationCompanyDao monitorRelationCompanyDao;
	
	@Autowired
	private MonitorCompanyService monitorCompanyService;
	
	@Autowired
	private ClientAccountDao clientAccountDao;
	
	@Override
	public Page<MonitorRelationCompany> getMonitorRelationCompanyPage(String monitorId, int currentPageNo, int pageSize) {
		
		Page<MonitorRelationCompany> page= monitorRelationCompanyDao.getMonitorRelationCompanyPage(monitorId, currentPageNo, pageSize);
		 
		return page;
	}
	
	@Override
	public ResultBean deleteMonitorRelationCompanys(List<String> relationCompanyIdList) {
		ResultBean result = new ResultBean();
		
		boolean flag = false;
		
		for (String id : relationCompanyIdList) {
			flag = monitorRelationCompanyDao.deleteMonitorRelationCompanyById(id);
			
			if(!flag){
				break;
			}
		}
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("删除关系企业成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("删除关系企业失败！");
		}
		
		return result;
	}
	
	@Override
	public ResultBean saveMonitorRelationCompany(MonitorRelationCompany monitorRelationCompany) {
		
		ResultBean result = new ResultBean();
		
		boolean flag = monitorRelationCompanyDao.save(monitorRelationCompany);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("添加关系企业成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("添加关系企业失败！");
		}
		
		return result;
	}
	
	/**
	 * 获取关联公司的信息
	 */
	@Override
	public MonitorRelationCompany getMonitorRelationCompany(String monitorId, String companyName) {
		return monitorRelationCompanyDao.getMonitorRelationCompany(monitorId, companyName);
	}
	
	@Override
	public MonitorRelationCompany getMonitorRelationCompanyById(String id) {
		
		return monitorRelationCompanyDao.get(id);
	}
	
	@Override
	public Map<String, Integer> getRelationCompanyNum(List<MonitorCompany> monitorCompanyList) {
		
		return monitorRelationCompanyDao.getRelationCompanyNum(monitorCompanyList);
	}

	@Override
	public int getRelationCompanyNum(String accountId) {
		
		return monitorRelationCompanyDao.getRelationCompanyNum(accountId);
	}
	
	@Override
	public int getTotalRelationCompanyNum(List<ClientAccount> clientAccountList) {
		
		return monitorRelationCompanyDao.getTotalRelationCompanyNum(clientAccountList);
	}

	@Override
	public int getMonitorRelationCompanyEventNum(String monitorId, String[] eventSubTypes, String[] eventLevels) {
		
		String eventSubType = "";
		
		
		
		for(int i=0;i<eventSubTypes.length;i++){
			
		}
		
		for(int i=0;i<eventLevels.length;i++){
			
		}
		
		return 0;
	}

	@Override
	public List<String> queryRelationCompanyNameByMonitorId(String monitorId) {
		return monitorRelationCompanyDao.queryRelationCompanyNameByMonitorId(monitorId);
	}
	
}
