package com.srd.ljzd.daoImpl.monitor;

import java.math.BigInteger;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorGroupDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorGroup;

@Repository("monitorGroupDao")
public class MonitorGroupDaoImpl extends BaseDaoImpl<MonitorGroup, String> implements MonitorGroupDao{

	protected static Logger logger = LogManager.getLogger(MonitorGroupDaoImpl.class.getName());
	
	@Override
	public List<MonitorGroup> queryMonitorGroupListByAccount(String accountId) {
		
		String hql = "from MonitorGroup where clientAccount.accountId = '" + accountId + "' and deleteFlag = '0' order by sort ASC";
		
		List<MonitorGroup> monitorGroupList = getListByHql(hql);
		
		return monitorGroupList;
	}
	
	@Override
	public List<MonitorGroup> queryMonitorGroupListByAccountExceptAll(String accountId) {
		String hql = "from MonitorGroup where clientAccount.accountId = '" + accountId + "' and deleteFlag = '0' and groupName != '全部' order by sort ASC";
		
		List<MonitorGroup> monitorGroupList = getListByHql(hql);
		
		return monitorGroupList;
	}

	@Override
	public int getMaxSortOfGroup(String accountId) {
		
		String sql = "SELECT IFNULL(MAX(t.Sort), 0) FROM monitor_group t where t.Account_ID = '" + accountId + "'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		BigInteger result = new BigInteger("0");

		try{
			result = (BigInteger) query.uniqueResult();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		
		return result.intValue();
	}

	@Override
	public MonitorGroup getMonitorGroup(String accountId, String name) {
		String hql = "from MonitorGroup where clientAccount.accountId=? and groupName=? and deleteFlag = '0' ";
		return super.getByHql(hql, accountId,name);
	}

	@Override
	public boolean isHaveGroup(String accountId, String groupName) {
		boolean result = true;
		
		String sql = "select count(1) from monitor_group where account_id = '" + accountId + "' and Group_Name = '" + groupName + "' and Delete_Flag = '0' ";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		BigInteger count = new BigInteger("0");
		
		try{
			count = (BigInteger) query.uniqueResult();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		
		if(count.intValue() == 0){
			return false;
		}
		
		return result;
	}
	
	@Override
	public boolean isHaveGroup(String groupId, String accountId, String groupName) {
		boolean result = true;
		
		String sql = "select count(1) from monitor_group where Group_ID != '" + groupId + "' and account_id = '" + accountId + "' and Group_Name = '" + groupName + "' and Delete_Flag = '0' ";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		BigInteger count = new BigInteger("0");
		
		try{
			count = (BigInteger) query.uniqueResult();
		}catch(Exception e){
			logger.error(e.getMessage(), e);
		}
		
		if(count.intValue() == 0){
			return false;
		}
		
		return result;
	}

	
}
