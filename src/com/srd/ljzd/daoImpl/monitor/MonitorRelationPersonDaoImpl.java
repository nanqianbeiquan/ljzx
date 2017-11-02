package com.srd.ljzd.daoImpl.monitor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorRelationPersonDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;

@Repository
public class MonitorRelationPersonDaoImpl extends BaseDaoImpl<MonitorRelationPerson,String> implements MonitorRelationPersonDao {

	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public List<MonitorRelationPerson> queryMonitorRelationPersonListByMonitorId(String monitorId, Date monitorDate) {
	
		StringBuffer hql = new StringBuffer("from MonitorRelationPerson where deleteFlag = '0' and monitorId = '" + monitorId + "' ");
		
		if(null != monitorDate){
			hql.append(" and monitorDate < '" + sdfDate.format(monitorDate) + "'");
		}
		
		return getListByHql(hql.toString());
	}

	@Override
	public Page<MonitorRelationPerson> getMonitorRelationPersonPage(String monitorId, int currentPageNo, int pageSize) {
		
		String hql = "from MonitorRelationPerson where deleteFlag = '0' and monitorId ='" + monitorId + "' order by createTime desc";
		String countSql = "select count(id) from MonitorRelationPerson where deleteFlag = '0' and monitorId ='" + monitorId + "'";
		
		return findPageByHql(hql, countSql, currentPageNo, pageSize);
	}
	
	@Override
	public boolean addRealPerson(MonitorRelationPerson person) {

		return saveOrUpdate(person);
	}
	
	@Override
	public boolean deleteMonitorRelationPersonByMonitorId(String monitorId) {
		
		String sql = "update monitorrelationperson set delete_flag = '1' where MonitorID = '" + monitorId + "'";
		
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
	public boolean deleteMonitorRelationPersonById(String id) {
		String sql = "update monitorrelationperson set delete_flag = '1' where ID = '" + id + "'";
		
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
	public MonitorRelationPerson getReaPersonInfo(MonitorRelationPerson monitorRelationPerson) {
		String hql = "from MonitorRelationPerson where deleteFlag = '0' and monitorId = '" + monitorRelationPerson.getMonitorId() + "' "
					+ " and name = '" + monitorRelationPerson.getName() + "' "
					+ " and idNumber = '" + monitorRelationPerson.getIdNumber() + "' "
					+ " and province = '" + monitorRelationPerson.getProvince() + "' "
					+ " and city = '" + monitorRelationPerson.getCity() + "'";
		
		return getByHql(hql);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> getRelationPersonNum(List<MonitorCompany> monitorCompanyList) {
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		
		if(monitorCompanyList.size() > 0){
			StringBuffer monitorIds = new StringBuffer("");
			
			for(MonitorCompany monitorCompany : monitorCompanyList){
				monitorIds.append("'" + monitorCompany.getMonitorID() + "',");
			}
			
			String sql = "SELECT t.MonitorID, COUNT(1) as num from monitorrelationperson t "
					+ "where t.delete_flag = '0' and t.MonitorID IN (" + monitorIds.substring(0, monitorIds.length()-1).toString() + ") "
					+ "GROUP BY t.MonitorID";
			
			Query query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			List<Map<String, Object>> list = query.list();
			
			int personNum = 0;
			
			for(MonitorCompany monitorCompany : monitorCompanyList){
				personNum = 0;
				
				for(Map<String, Object> map : list){
					if((monitorCompany.getMonitorID()).equals(map.get("MonitorID").toString())){
						personNum = Integer.valueOf(map.get("num").toString());
						
						break;
					}
				}
				
				resultMap.put(monitorCompany.getMonitorID(), personNum);
			}
		}
		
		return resultMap;
	}

	
	
}
