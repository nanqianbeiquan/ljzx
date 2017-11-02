package com.srd.ljzd.daoImpl.monitor;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorRelationCompanyDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorRelationCompany;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
/**
 * 
 * 版权所有：
 * 项目名称：lengjingzd2.0 
 *
 * 类描述：动态监控关联企业信息表
 * 类名称：com.srd.ljzd.daoImpl.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月2日上午9:41:52
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
@Repository
public class MonitorRelationCompanyDaoImpl extends BaseDaoImpl<MonitorRelationCompany,String> implements MonitorRelationCompanyDao {
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public List<MonitorRelationCompany> queryMonitorRelationCompanyListByMonitorId(String monitorId) {
		
		String hql = "from MonitorRelationCompany where deleteFlag = '0' and monitorId = '" + monitorId + "'"
				+ " and monitorDate < '" + sdfDate.format(new Date()) + "'";
		
		return getListByHql(hql);
	}


	@Override
	public Page<MonitorRelationCompany> getMonitorRelationCompanyPage(
			String monitorId, int currentPageNo, int pageSize) {

		String hql = "from MonitorRelationCompany where deleteFlag = '0' and monitorId ='" + monitorId + "' order by createTime desc";
		String countSql = "select count(id) from MonitorRelationCompany where deleteFlag = '0' and monitorId ='" + monitorId + "'";
		
		return findPageByHql(hql, countSql, currentPageNo, pageSize) ;
	}

	@Override
	public boolean deleteMonitorRelationCompanyByMonitorId(String monitorId) {
		
		String sql = "update monitorrelationcompany set delete_flag = '1' where MonitorID = '" + monitorId + "'";
		
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
	public boolean deleteMonitorRelationCompanyById(String id) {
		
		String sql = "update monitorrelationcompany set delete_flag = '1' where ID = '" + id + "'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		try{
			query.executeUpdate();
			
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Integer> getRelationCompanyNum(List<MonitorCompany> monitorCompanyList) {
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		
		if(monitorCompanyList.size() > 0){
			StringBuffer monitorIds = new StringBuffer("");
			
			for(MonitorCompany monitorCompany : monitorCompanyList){
				monitorIds.append("'" + monitorCompany.getMonitorID() + "',");
			}
			
			String sql = "SELECT t.MonitorID, COUNT(1) as num from monitorrelationcompany t "
					+ "where t.delete_flag = '0' and t.MonitorID IN (" + monitorIds.substring(0, monitorIds.length()-1).toString() + ") "
					+ "GROUP BY t.MonitorID";
			
			Query query = getSession().createSQLQuery(sql).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			List<Map<String, Object>> list = query.list();
			
			int companyNum = 0;
			
			for(MonitorCompany monitorCompany : monitorCompanyList){
				companyNum = 0;
				
				for(Map<String, Object> map : list){
					if((monitorCompany.getMonitorID()).equals(map.get("MonitorID").toString())){
						companyNum = Integer.valueOf(map.get("num").toString());
						
						break;
					}
				}
				
				resultMap.put(monitorCompany.getMonitorID(), companyNum);
			}
		}
		
		return resultMap;
	}

	@Override
	public int getRelationCompanyNum(String accountId) {
		String sql = "select count(1) from monitorrelationcompany t where t.delete_flag = '0' and t.account_id = '" + accountId + "'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		BigInteger count = new BigInteger("0");
		
		try{
			count = (BigInteger) query.uniqueResult();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		return count.intValue();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<String> queryRelationCompanyNameByMonitorId(String monitorId) {
		String hql = "select companyName from MonitorRelationCompany company where company.deleteFlag = '0' and company.monitorId=:monitorId";
		
		Query query = getSession().createQuery(hql);
		query.setParameter("monitorId", monitorId);  
		
		return query.list();
	}

	@Override
	public int getTotalRelationCompanyNum(List<ClientAccount> clientAccountList) {
		
		int num = 0;
		
		if(null != clientAccountList && clientAccountList.size() > 0){
			StringBuffer accountIds = new StringBuffer("");
			
			for(int i=0;i<clientAccountList.size();i++){
				if(i == clientAccountList.size()-1){
					//最后一个账号ID后面不加逗号
					accountIds.append("'" + clientAccountList.get(i).getAccountId() + "'");
				}else{
					accountIds.append("'" + clientAccountList.get(i).getAccountId() + "',");
				}
			}
			
			String sql = "select count(ID) from monitorrelationcompany t where t.delete_flag = '0' and t.account_id in (" + accountIds + ")";
			
			Query query = this.getSession().createSQLQuery(sql);
			
			try{
				BigInteger count = (BigInteger) query.uniqueResult();
				
				num = count.intValue();
			}catch(Exception e){
				LoggerUtils.error(e.getMessage(), e);
			}
		}
		
		return num;
	}

	@Override
	public MonitorRelationCompany getMonitorRelationCompany(String monitorId, String companyName) {
		String hql = "from MonitorRelationCompany where deleteFlag = '0' and monitorId = '" + monitorId + "' and companyName = '" + companyName + "'";
		
		return getByHql(hql.toString());
	}
	
}
