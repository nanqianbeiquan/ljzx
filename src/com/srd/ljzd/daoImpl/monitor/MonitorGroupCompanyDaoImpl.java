package com.srd.ljzd.daoImpl.monitor;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorGroupCompanyDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorGroup;
import com.srd.ljzd.entity.monitor.MonitorGroupCompany;
import com.srd.ljzd.util.LoggerUtils;

@Repository
public class MonitorGroupCompanyDaoImpl extends BaseDaoImpl<MonitorGroupCompany, String> implements
		MonitorGroupCompanyDao {

	@Override
	public List<MonitorGroupCompany> getTodayGroupCompany(String accountId,
			String createTime) {
		String hql = "select gc from MonitorGroupCompany gc where gc.monitorGroup.sysAccount.accountId=? and gc.monitorGroup.groupName='全部' "
				+ "and gc.createTime >= '"+createTime+"' order by createTime";
		return super.getListByHql(hql, accountId);
	}

	@Override
	public boolean deleteByMonitorCompany(MonitorCompany monitorCompany) {
		
		String sql = "delete from monitor_group_company where monitor_id = '" + monitorCompany.getMonitorID() + "'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		try{
			query.executeUpdate();
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}
	
	@Override
	public boolean deleteMonitorGroupCompanyByMonitorId(String monitorId) {
		
		String sql = "delete from monitor_group_company where monitor_id = '" + monitorId + "'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		try{
			query.executeUpdate();
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}
	
	@Override
	public boolean deleteMonitorGroupCompanyByMonitorId(String monitorId, String groupId) {
		String sql = "delete from monitor_group_company where monitor_id = '" + monitorId + "' and group_id != '" + groupId +"'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		try{
			query.executeUpdate();
			
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}

	@Override
	public boolean addGroupCompanyReal(String groupId, String monitorId) {
		
		MonitorGroupCompany monitorGroupCompany=new MonitorGroupCompany();
		
		MonitorCompany monitorCompany=new MonitorCompany();
		monitorCompany.setMonitorID(monitorId);
		monitorGroupCompany.setMonitorCompany(monitorCompany);
		
		MonitorGroup monitorGroup=new MonitorGroup();
		monitorGroup.setGroupId(groupId);
		monitorGroupCompany.setMonitorGroup(monitorGroup);
		
		monitorGroupCompany.setCreateTime(new Date());
		
		return save(monitorGroupCompany);
	}

	@Override
	public boolean deleteAllReal(List<String> groupIdList) {
		
		final String sql="delete from monitor_group_company where group_id in (:list)";
		
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameterList("list", groupIdList);  
		try{
			query.executeUpdate();
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			return false;
		}
		
	}

	@Override
	public boolean deleteByBatch(String groupId, List<String> monitorIdList) {
	
	final String sql="delete from monitor_group_company where group_id=:groupId and monitor_id in (:list)";
		
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter("groupId", groupId);
		query.setParameterList("list", monitorIdList);  
		try{
			query.executeUpdate();
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public boolean deleteMonitorReal(List<String> groupIdList, String monitorID) {

		final String sql="delete from monitor_group_company where group_id in (:list) and monitor_id =:monitor";
		
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter("monitor", monitorID);  
		query.setParameterList("list", groupIdList);  
	
		try{
			query.executeUpdate();
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			return false;
		}
	}

	@Override
	public List<MonitorGroupCompany> getGroupCompany(String monitorId) {
		String hql = "from MonitorGroupCompany where monitorCompany.monitorID = '" + monitorId + "'";
		
		return getListByHql(hql);
	}
	
	@Override
	public Integer getCompanyNumByGroup(String groupId) {
		
		String sql = "SELECT COUNT(1) as CompanyNum from monitor_group_company t1 "
				+ " left join monitor_company t2 on t2.MonitorID = t1.monitor_id "
				+ " where t2.delete_flag = '0' and t1.Group_ID = '" + groupId + "'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		BigInteger count = new BigInteger("0");
		
		try{
			count = (BigInteger) query.uniqueResult();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		return count.intValue();
	}

	@Override
	public boolean deleteMonitorReal(String groupId) {

		String sql="delete from monitor_group_company where group_id = '" + groupId + "'";
			
		Query query = this.getSession().createSQLQuery(sql);
		
		try{
			query.executeUpdate();
			
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}

	@Override
	public Boolean getGroupCompany(String groupId, String monitorId) {
		String hql = "from MonitorGroupCompany where monitorCompany.monitorID =? and monitorGroup.groupID=?";
		MonitorGroupCompany monitorGroupCompany=getByHql(hql, monitorId,groupId);
		if(monitorGroupCompany!=null){
			return true;
		}else{
			return false;
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorGroupCompany> getGroupCompanyList(List<String> groupIdList, String monitorID) {
		
		final String sql="select * from Monitor_Group_Company where group_id in (:list) and monitor_id =:monitor";
		
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter("monitor", monitorID);  
		query.setParameterList("list", groupIdList);  
		
		return query.list();
	}

}
