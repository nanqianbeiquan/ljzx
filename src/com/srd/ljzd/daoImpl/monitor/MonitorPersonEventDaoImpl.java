package com.srd.ljzd.daoImpl.monitor;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorPersonEventDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.monitor.MonitorPersonEvent;
import com.srd.ljzd.entity.monitor.MonitorRelationPerson;
import com.srd.ljzd.util.Page;

@Repository
public class MonitorPersonEventDaoImpl extends BaseDaoImpl<MonitorPersonEvent, String> implements MonitorPersonEventDao {

	@Override
	public Page<MonitorPersonEvent> getPersonEvents(int currentPageNo, int pageSize, String name, String idNumber,String province, String city,String eventSubType) {
		
		
		String hql = "select event from MonitorPersonEvent event where name=? and idNumber=? and province=? and city=? and eventSubType=? order by contentDate desc";
		
		String countSql="select count(eventID) from MonitorPersonEvent event where name=? and idNumber=? and province=? and city=? and eventSubType=?";
				
		return  this.findPageByHql(hql, countSql, currentPageNo, pageSize, name,idNumber,province,city,eventSubType);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getPersonEventNumByType(MonitorRelationPerson monitorRelationPerson) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT ");
		sql.append(" SUM(CASE WHEN t.EventSubType = '30' THEN 1 ELSE 0 END ) AS subType30, ");
		sql.append(" SUM(CASE WHEN t.EventSubType = '31' THEN 1 ELSE 0 END ) AS subType31, ");
		sql.append(" SUM(CASE WHEN t.EventSubType = '32' THEN 1 ELSE 0 END ) AS subType32, ");
		sql.append(" SUM(CASE WHEN t.EventSubType = '33' THEN 1 ELSE 0 END ) AS subType33, ");
		sql.append(" SUM(CASE WHEN t.EventSubType = '34' THEN 1 ELSE 0 END ) AS subType34, ");
		sql.append(" SUM(CASE WHEN t.EventSubType = '35' THEN 1 ELSE 0 END ) AS subType35, ");
		sql.append(" SUM(CASE WHEN t.EventSubType = '36' THEN 1 ELSE 0 END ) AS subType36 ");
		sql.append(" FROM monitorpersonevent t ");
		sql.append(" where t.Name = '" + monitorRelationPerson.getName() + "' ");
		sql.append(" and t.IDNumber = '" + monitorRelationPerson.getIdNumber() + "' ");
		sql.append(" and t.Province = '" + monitorRelationPerson.getProvince() + "' ");
		sql.append(" and t.City = '" + monitorRelationPerson.getCity() + "' ");
		
		Query query = this.getSession().createSQLQuery(sql.toString());
		
		resultMap = (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
		
		if(null == resultMap.get("subType30")){
			resultMap.put("subType30", 0);
			resultMap.put("subType31", 0);
			resultMap.put("subType32", 0);
			resultMap.put("subType33", 0);
			resultMap.put("subType34", 0);
			resultMap.put("subType35", 0);
			resultMap.put("subType36", 0);
		}
		
		return resultMap;
	}

}
