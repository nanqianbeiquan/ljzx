package com.srd.ljzd.serviceImpl.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.monitor.MonitorGroupCompanyDao;
import com.srd.ljzd.dao.monitor.MonitorGroupDao;
import com.srd.ljzd.entity.common.ResultBean;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.service.monitor.MonitorGroupService;

@Service("monitorGroupService")
public class MonitorGroupServiceImpl implements MonitorGroupService {
	
	@Autowired
	private MonitorGroupDao monitorGroupDao;
	
	@Autowired
	private MonitorGroupCompanyDao monitorGroupCompanyDao;

	@Override
	public ResultBean addMonitorGroup(MonitorGroup monitorGroup) {
		ResultBean result = new ResultBean();
		
		int sort = monitorGroupDao.getMaxSortOfGroup(monitorGroup.getClientAccount().getAccountId()) + 1;
		
		monitorGroup.setSort(sort);
		
		boolean resultFlag = monitorGroupDao.save(monitorGroup);
		
		if(resultFlag){
			result.setResultCode("0");
			result.setResultMsg("新增动态监控分组成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("新增动态监控分组失败！");
		}
		
		return result;
	}

	@Override
	public ResultBean editMonitorGroup(MonitorGroup monitorGroup) {
		ResultBean result = new ResultBean();
		
		boolean resultFlag = monitorGroupDao.update(monitorGroup);
		
		if(resultFlag){
			result.setResultCode("0");
			result.setResultMsg("编辑动态监控分组成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("编辑动态监控分组失败！");
		}
		
		return result;
	}

	@Override
	public MonitorGroup getMonitorGroupById(String groupID) {
		MonitorGroup monitorGroup = monitorGroupDao.get(groupID);
		
		return monitorGroup;
	}

	@Override
	public ResultBean deleteMonitorGroupById(String groupID) {
		ResultBean result = new ResultBean();
		
		boolean resultFlag = monitorGroupDao.deleteById(groupID);
		
		if(resultFlag){
			result.setResultCode("0");
			result.setResultMsg("删除动态监控分组成功！");
		}else{
			result.setResultCode("1");
			result.setResultMsg("删除动态监控分组失败！");
		}
		
		return result;
	}

	@Override
	public List<MonitorGroup> queryMonitorGroupListByAccount(String accountId) {
		
		List<MonitorGroup> monitorGroupList = monitorGroupDao.queryMonitorGroupListByAccount(accountId);
		
		int companyNum = 0;
		
		//统计各分组的企业数量
		for(MonitorGroup monitorGroup : monitorGroupList){
			companyNum = monitorGroupCompanyDao.getCompanyNumByGroup(monitorGroup.getGroupId());
			
			monitorGroup.setCompanyNum(companyNum);
		}
		
		return monitorGroupList;
	}
	
	@Override
	public List<MonitorGroup> queryMonitorGroupListByAccountExceptAll(String accountId) {
		List<MonitorGroup> monitorGroupList = monitorGroupDao.queryMonitorGroupListByAccountExceptAll(accountId);
		
		int companyNum = 0;
		
		//统计各分组的企业数量
		for(MonitorGroup monitorGroup : monitorGroupList){
			companyNum = monitorGroupCompanyDao.getCompanyNumByGroup(monitorGroup.getGroupId());
			
			monitorGroup.setCompanyNum(companyNum);
		}
		
		return monitorGroupList;
	}

	@Override
	public MonitorGroup getMonitorGroup(String accountId, String name) {
		return monitorGroupDao.getMonitorGroup(accountId, name);
	}

	@Override
	public boolean isHaveGroup(String accountId, String groupName) {
		
		return monitorGroupDao.isHaveGroup(accountId, groupName);
	}

	@Override
	public ResultBean updateGroup(MonitorGroup monitorGroup) {
		ResultBean result = new ResultBean();
		
		if(monitorGroupDao.isHaveGroup(monitorGroup.getGroupId(), monitorGroup.getClientAccount().getAccountId(), monitorGroup.getGroupName())){
			result.setResultCode("1");
			result.setResultMsg("更新失败，组名已存在！");
			result.setResultData(monitorGroupDao.get(monitorGroup.getGroupId()).getGroupName());
		}else{
			MonitorGroup tempMonitorGroup = monitorGroupDao.get(monitorGroup.getGroupId());
			tempMonitorGroup.setGroupName(monitorGroup.getGroupName());
			
			if(null != monitorGroup.getRemark()){
				tempMonitorGroup.setRemark(monitorGroup.getRemark());
			}
			
			boolean resultFlag = monitorGroupDao.update(tempMonitorGroup);
			
			if(resultFlag){
				result.setResultCode("0");
				result.setResultMsg("更新组名成功！");
			}else{
				result.setResultCode("1");
				result.setResultMsg("更新组名失败！");
			}
		}
		
		return result;
	}

}
