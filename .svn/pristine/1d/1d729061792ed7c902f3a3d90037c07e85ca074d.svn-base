package com.srd.ljzd.serviceImpl.monitor;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorRelationPersonDao;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.service.monitor.MonitorRelationPersonService;
import com.srd.ljzd.util.Page;

@Service
public class MonitorRelationPersonServiceImpl implements MonitorRelationPersonService {
	@Autowired
	private MonitorRelationPersonDao monitorRelationPersonDao;
	
	@Override
	public Page<MonitorRelationPerson> getMonitorRelationPersonPage(
			String monitorId, int currentPageNo, int pageSize) {
		
		Page<MonitorRelationPerson> page = monitorRelationPersonDao.getMonitorRelationPersonPage(monitorId, currentPageNo, pageSize);

		return page;
	}
	
	@Override
	public List<MonitorRelationPerson> getMonitorRelationPersonList(String monitorId) {
		return monitorRelationPersonDao.queryMonitorRelationPersonListByMonitorId(monitorId, null);
	}



	@Override
	public ResultBean deleteMonitorRelationPersons(List<String> relationPersonIdList) {
		ResultBean result = new ResultBean();
		
		boolean flag = false;
		
		for (String id : relationPersonIdList) {
			flag = monitorRelationPersonDao.deleteById(id);
			
			if(!flag){
				break;
			}
		}
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("删除关系自然人成功！");
		}else{
			result.setResultCode("0");
			result.setResultMsg("删除关系自然人失败！");
		}
		
		return result;
	}
	
	@Override
	public MonitorRelationPerson getMonitorRelationPerson(MonitorRelationPerson monitorRelationPerson) {
		return monitorRelationPersonDao.getReaPersonInfo(monitorRelationPerson);
	}
	
	@Override
	public MonitorRelationPerson getMonitorRelationPersonById(String id) {
		return monitorRelationPersonDao.get(id);
	}

	@Override
	public ResultBean saveMonitorRelationPerson(MonitorRelationPerson monitorRelationPerson) {
		ResultBean result = new ResultBean();

		boolean flag = monitorRelationPersonDao.save(monitorRelationPerson);
		
		if(flag){
			result.setResultCode("0");
			result.setResultMsg("添加关联个人成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("添加关联个人失败！");
		}
		
		return result;
	}
	
	@Override
	public Map<String, Integer> getRelationPersonNum(List<MonitorCompany> monitorCompanyList) {
		
		return monitorRelationPersonDao.getRelationPersonNum(monitorCompanyList);
	}
	
	@Override
	public int getMonitorRelationPersonEventNum(String monitorId,
			String[] eventSubTypes, String[] eventLevels) {
		// TODO Auto-generated method stub
		return 0;
	}
}
