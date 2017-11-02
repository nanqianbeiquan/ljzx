package com.srd.ljzd.serviceImpl.monitor;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorGroupCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorGroupDao;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.entity.monitor.MonitorGroupCompany;
import com.srd.ljzd.service.monitor.MonitorGroupCompanyService;
import com.srd.ljzd.service.monitor.MonitorGroupService;
import com.srd.ljzd.util.DateUtils;

@Service("monitorGroupCompanyService")
public class MonitorGroupCompanyServiceImpl implements
		MonitorGroupCompanyService {
	@Autowired
	MonitorGroupCompanyDao monitorGroupCompanyDao;
	@Autowired
	private MonitorGroupService monitorGroupService;
	@Autowired
	private MonitorCompanyDao monitorCompanyDao; 
	
	@Autowired
	private MonitorGroupDao monitorGroupDao;

	@Override
	public List<MonitorGroupCompany> getTodayGroupCompany(String accountId) {
		return monitorGroupCompanyDao.getTodayGroupCompany(accountId, DateUtils.getLocalStr(DateUtils.formatPattern, Calendar.getInstance().getTime()));
	}

	@Override
	public boolean updateMonitorCompanyGroupBatch(String accountId, List<String> monitorIdList,List<String> groupIdList) {
		boolean result = true;
		
		MonitorGroup monitorGroup = monitorGroupDao.getMonitorGroup(accountId, "全部");
		
		for(String monitorId : monitorIdList){
			//先删除全部分组之外企业和分组关联
			result = monitorGroupCompanyDao.deleteMonitorGroupCompanyByMonitorId(monitorId, monitorGroup.getGroupId());
			
			if(!result){
				break;
			}
			
			//保存企业和分组的关系
			for(String groupId : groupIdList){
				result = monitorGroupCompanyDao.addGroupCompanyReal(groupId, monitorId);
				
				if(!result){
					break;
				}
			}
		}
		
		return result;
	}

	@Override
	public boolean deleteBatch(String groupId, List<String> monitorIdList) {
		boolean flag = monitorGroupCompanyDao.deleteByBatch(groupId, monitorIdList);
		return flag;
	}

	@Override
	public boolean deleteMonitorReal(List<String> groupIdList, String monitorID) {
		return monitorGroupCompanyDao.deleteMonitorReal(groupIdList, monitorID);
	}

	@Override
	public boolean deleteMonitorReal(String groupId) {
		return monitorGroupCompanyDao.deleteMonitorReal(groupId);
	}

	@Override
	public boolean getGroupCompanyList(List<String> groupIdList, String monitorID) {
		List<MonitorGroupCompany> list=monitorGroupCompanyDao.getGroupCompanyList(groupIdList, monitorID);
		if(null==list ||list.size()==0){
			return false;
		}else{
			return true;
		}
	}

}
