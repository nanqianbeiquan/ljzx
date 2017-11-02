package com.srd.ljzd.daoImpl.monitor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.monitor.MonitorCompanyDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.dto.monitor.MonitorCompanyDTO;
import com.srd.ljzd.entity.client.ClientAccount;
import com.srd.ljzd.entity.monitor.MonitorCompany;
import com.srd.ljzd.entity.monitor.MonitorCompanyDistribution;
import com.srd.ljzd.util.LoggerUtils;
import com.srd.ljzd.util.Page;
import com.srd.ljzd.util.StringUtils;

/**
 * 
 * 版权所有：
 * 项目名称：lengjingzd2.0 
 *
 * 类描述：TODO
 * 类名称：com.srd.ljzd.daoImpl.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月1日下午6:00:59
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
@Repository
public class MonitorCompanyDaoImpl extends BaseDaoImpl<MonitorCompany,String> implements MonitorCompanyDao {
	
	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCompany> queryRecentMonitorCompanyList(String accountId, int num) {
		
		String hql = "from MonitorCompany where deleteFlag = '0' and clientAccount.accountId = '" + accountId + "' and createTime > '" + sdfDate.format(new Date()) + " 00:00:00' order by createTime desc ";
		
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(num);
		
		List<MonitorCompany> monitorCompanyList = null;
		
		try{
			monitorCompanyList = query.list();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if(monitorCompanyList == null){
			monitorCompanyList = new ArrayList<MonitorCompany>();
		}
		
		return monitorCompanyList;
	}


	@Override
	public Page<MonitorCompany> getMonitorGroupComInfo(String sysAccount,
			String groupName, int currentPageNo, int pageSize) {
		
		String hql = "from MonitorCompany where deleteFlag = '0' and monitorID in (select monitorCompany.monitorID from MonitorGroupCompany where monitorGroup.groupID =(select groupID from MonitorGroup where clientAccount.accountId=? and groupName=?))";

		String countSql="select count(*) "+hql;
		
		return findPageByHql(hql, countSql, currentPageNo, pageSize, sysAccount,groupName);
	}
	
	@Override
	public MonitorCompany getMonitorCompany(MonitorCompany monitorCompany) {
		
		String hql = "from MonitorCompany where deleteFlag = '0' and clientAccount.accountId = '" + monitorCompany.getClientAccount().getAccountId() + "' and companyName = '" + monitorCompany.getCompanyName() + "'";
		
		return getByHql(hql);
	}
	
	@Override
	public MonitorCompany getMonitorCompany(String accountId, String companyName) {
		String hql = "from MonitorCompany where deleteFlag = '0' and clientAccount.accountId = '" + accountId + "' and companyName = '" + companyName + "' ";
		
		return getByHql(hql);
	}

	@Override
	public List<MonitorCompany> getMonitorComListByAccId(String accountId,
			List<String> nameList) {
				//hql语句构造
				final String hql="from MonitorCompany where deleteFlag = '0' and clientAccount.accountId=:accountId and CompanyName in (:list)";
				
				Query query = getSession().createQuery(hql);
				query.setParameter("accountId", accountId);
				query.setParameterList("list", nameList);  
				
		        List<MonitorCompany> companylist = null;
				try{
					companylist = query.list();
				}catch(Exception e){
					//TODO
				}
				return companylist;
	}


	@Override
	public List<MonitorCompany> queryTodayMonitorCompany(String accountId) {
		
		String hql = "from MonitorCompany where deleteFlag = '0' and clientAccount.accountId = '" + accountId + "' and createTime > '" + sdfDate.format(new Date()) + " 00:00:00' order by createTime desc";
		
		return getListByHql(hql);
	}
	
	@Override
	public int queryTodayMonitorCompanyNum(List<ClientAccount> clientAccountList) {
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
			
			String sql = "select count(t.MonitorID) from monitor_company t where t.delete_flag = '0' and t.account_id in (" + accountIds + ") and t.createTime > '" + sdfDate.format(new Date()) + " 00:00:00' ";
			
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
	public int queryMonitorCompanyNum(List<ClientAccount> clientAccountList) {
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
			
			String sql = "select count(t.MonitorID) from monitor_company t where t.delete_flag = '0' and t.account_id in (" + accountIds + ") ";
			
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
	public int queryMonitorCompanyNumByAccountId(String accountId) {
		int num = 0;
		
			
		String sql = "select count(t.MonitorID) from monitor_company t where t.delete_flag = '0' and t.account_id = '" + accountId + "' ";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		try{
			BigInteger count = (BigInteger) query.uniqueResult();
			
			num = count.intValue();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		return num;
	}


	@Override
	public Page<MonitorCompany> findTodayMonitorCompanyPage(MonitorCompany monitorCompany, int currentPageNo, int pageSize) {
		
		StringBuffer hql = new StringBuffer("from MonitorCompany where deleteFlag = '0' and clientAccount.accountId = '" + monitorCompany.getClientAccount().getAccountId() + "' and createTime > '" + sdfDate.format(new Date()) + " 00:00:00' order by createTime desc");
		StringBuffer countHql = new StringBuffer("select count(monitorID) from MonitorCompany deleteFlag = '0' and where clientAccount.accountId = '" + monitorCompany.getClientAccount().getAccountId() + "' and createTime > '" + sdfDate.format(new Date()) + " 00:00:00' order by createTime desc");
		
		Page<MonitorCompany> page = findPageByHql(hql.toString(), countHql.toString(), currentPageNo, pageSize);
		
		return page;
	}
	
	@Override
	public Page<MonitorCompany> findMonitorCompanyPage(MonitorCompanyDTO monitorCompanyDTO, int currentPageNo, int pageSize,String sortAttr,boolean isDesc) {
		
		StringBuffer hql = new StringBuffer("select monitorGroupCompany.monitorCompany from MonitorGroupCompany monitorGroupCompany where monitorGroupCompany.monitorCompany.deleteFlag = '0' ");
		StringBuffer countHql = new StringBuffer("select count(monitorGroupCompany.id) from MonitorGroupCompany monitorGroupCompany where monitorGroupCompany.monitorCompany.deleteFlag = '0' ");
		
		List<String> selectedAccountList =  monitorCompanyDTO.getAccountList();
		if(selectedAccountList==null||selectedAccountList.size()==0){
			//原逻辑不变
			hql.append(" and monitorGroupCompany.monitorCompany.clientAccount.accountId = '" + monitorCompanyDTO.getClientAccount().getAccountId() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.clientAccount.accountId = '" + monitorCompanyDTO.getClientAccount().getAccountId() + "' ");
			
			hql.append(" and monitorGroupCompany.monitorGroup.groupName = '" + monitorCompanyDTO.getGroupName() + "' ");
			countHql.append(" and monitorGroupCompany.monitorGroup.groupName = '" + monitorCompanyDTO.getGroupName() + "' ");
		}else{
			StringBuffer accBuf = new StringBuffer();
			for(int i=0;i<selectedAccountList.size();i++){
				if(i==selectedAccountList.size()-1){
					accBuf.append("'").append(selectedAccountList.get(i)).append("'");
				}else{
					accBuf.append("'").append(selectedAccountList.get(i)).append("'").append(",");
				}
			}
			
			hql.append(" and monitorGroupCompany.monitorCompany.clientAccount.accountId in (" + accBuf + ") ");
			countHql.append(" and monitorGroupCompany.monitorCompany.clientAccount.accountId in (" + accBuf + ") ");
		        //页面上的分组实际是账户，所以要查询该账户下所有分组
			hql.append(" and monitorGroupCompany.monitorGroup.groupName = '全部' ");
			countHql.append(" and monitorGroupCompany.monitorGroup.groupName = '全部' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getRiskSituation())){
			hql.append(" and monitorGroupCompany.monitorCompany.customRiskSituation = '" + monitorCompanyDTO.getRiskSituation() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.customRiskSituation = '" + monitorCompanyDTO.getRiskSituation() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getRiskStatus())){
			hql.append(" and monitorGroupCompany.monitorCompany.riskStatus = '" + monitorCompanyDTO.getRiskStatus() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.riskStatus = '" + monitorCompanyDTO.getRiskStatus() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getEventLevel())){
			hql.append(" and monitorGroupCompany.monitorCompany.eventLevel = '" + monitorCompanyDTO.getEventLevel() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.eventLevel = '" + monitorCompanyDTO.getEventLevel() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getGroupStatus())){
			hql.append(" and monitorGroupCompany.monitorCompany.groupStatus = '" + monitorCompanyDTO.getGroupStatus() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.groupStatus = '" + monitorCompanyDTO.getGroupStatus() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getNewEventFlag())){
			hql.append(" and monitorGroupCompany.monitorCompany.newEventFlag = '" + monitorCompanyDTO.getNewEventFlag() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.newEventFlag = '" + monitorCompanyDTO.getNewEventFlag() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getBeginDate())){
			hql.append(" and monitorGroupCompany.monitorCompany.monitorDate >= '" + monitorCompanyDTO.getBeginDate() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.monitorDate >= '" + monitorCompanyDTO.getBeginDate() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getEndDate())){
			hql.append(" and monitorGroupCompany.monitorCompany.monitorDate <= '" + monitorCompanyDTO.getEndDate() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.monitorDate <= '" + monitorCompanyDTO.getEndDate() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getCompanyName())){
			hql.append(" and monitorGroupCompany.monitorCompany.companyName like '%" + monitorCompanyDTO.getCompanyName() + "%' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.companyName like '%" + monitorCompanyDTO.getCompanyName() + "%' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getCustomRiskFlag())){
			hql.append(" and monitorGroupCompany.monitorCompany.customRiskFlag = '" + monitorCompanyDTO.getCustomRiskFlag() + "' ");
			countHql.append(" and monitorGroupCompany.monitorCompany.customRiskFlag = '" + monitorCompanyDTO.getCustomRiskFlag() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getTodayMonitorFlag())){
			if("1".equals(monitorCompanyDTO.getTodayMonitorFlag())){
				hql.append(" and monitorGroupCompany.monitorCompany.monitorDate = '" + sdfDate.format(new Date()) + "' ");
				countHql.append(" and monitorGroupCompany.monitorCompany.monitorDate = '" + sdfDate.format(new Date()) + "' ");
			}
		}
		
		if(monitorCompanyDTO.getAreaList().size() > 0){
			String areas = "";
			
			for(int i=0;i<monitorCompanyDTO.getAreaList().size();i++){
				if(i>0){
					areas += ",'" + monitorCompanyDTO.getAreaList().get(i) + "'";
				}else{
					areas += "'" + monitorCompanyDTO.getAreaList().get(i) + "'";
				}
			}
			
			hql.append(" and monitorGroupCompany.monitorCompany.province in (" + areas + ") ");
			countHql.append(" and monitorGroupCompany.monitorCompany.province in (" + areas + ") ");
		}
		
		if(monitorCompanyDTO.getLabelList().size() > 0){
			hql.append(" and (");
			countHql.append(" and (");
			
			for(int i=0;i<monitorCompanyDTO.getLabelList().size();i++){
				if(i > 0){
					hql.append(" or monitorGroupCompany.monitorCompany.riskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
					countHql.append(" or monitorGroupCompany.monitorCompany.riskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
				}else{
					hql.append(" monitorGroupCompany.monitorCompany.riskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
					countHql.append(" monitorGroupCompany.monitorCompany.riskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
				}
				
				hql.append(" or monitorGroupCompany.monitorCompany.customRiskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
				countHql.append(" or monitorGroupCompany.monitorCompany.customRiskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
			}
			
			hql.append(") ");
			countHql.append(") ");
		}
		
		
		hql.append(" order by ");
		
		if(!StringUtils.isEmpty(sortAttr)&&!"createTime".equals(sortAttr)){
			hql.append("monitorGroupCompany.monitorCompany.")
				.append(sortAttr)
				.append(" ")
				.append(isDesc?"desc":"asc")
				.append(",");
		}
		hql.append(" monitorGroupCompany.monitorCompany.createTime desc,convert(monitorGroupCompany.monitorCompany.companyName , 'gbk') asc");
		Page<MonitorCompany> page = findPageByHql(hql.toString(), countHql.toString(), currentPageNo, pageSize);
		
		return page;
	}

	@Override
	public List<MonitorCompany> getMonitorCompanyList(MonitorCompanyDTO monitorCompanyDTO) {
		
		StringBuffer hql = new StringBuffer("select monitorGroupCompany.monitorCompany from MonitorGroupCompany monitorGroupCompany where monitorGroupCompany.monitorCompany.deleteFlag = '0' ");
		
		hql.append(" and monitorGroupCompany.monitorCompany.clientAccount.accountId = '" + monitorCompanyDTO.getClientAccount().getAccountId() + "' ");
		
		hql.append(" and monitorGroupCompany.monitorGroup.groupName = '" + monitorCompanyDTO.getGroupName() + "' ");
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getRiskSituation())){
			hql.append(" and monitorGroupCompany.monitorCompany.customRiskSituation = '" + monitorCompanyDTO.getRiskSituation() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getRiskStatus())){
			hql.append(" and monitorGroupCompany.monitorCompany.riskStatus = '" + monitorCompanyDTO.getRiskStatus() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getEventLevel())){
			hql.append(" and monitorGroupCompany.monitorCompany.eventLevel = '" + monitorCompanyDTO.getEventLevel() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getGroupStatus())){
			hql.append(" and monitorGroupCompany.monitorCompany.groupStatus = '" + monitorCompanyDTO.getGroupStatus() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getNewEventFlag())){
			hql.append(" and monitorGroupCompany.monitorCompany.newEventFlag = '" + monitorCompanyDTO.getNewEventFlag() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getBeginDate())){
			hql.append(" and monitorGroupCompany.monitorCompany.monitorDate >= '" + monitorCompanyDTO.getBeginDate() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getEndDate())){
			hql.append(" and monitorGroupCompany.monitorCompany.monitorDate <= '" + monitorCompanyDTO.getEndDate() + "' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getCompanyName())){
			hql.append(" and monitorGroupCompany.monitorCompany.companyName like '%" + monitorCompanyDTO.getCompanyName() + "%' ");
		}
		
		if(StringUtils.isNotEmpty(monitorCompanyDTO.getCustomRiskFlag())){
			hql.append(" and monitorGroupCompany.monitorCompany.customRiskFlag = '" + monitorCompanyDTO.getCustomRiskFlag() + "' ");
		}
		
		if(monitorCompanyDTO.getAreaList().size() > 0){
			String areas = "";
			
			for(int i=0;i<monitorCompanyDTO.getAreaList().size();i++){
				if(i>0){
					areas += ",'" + monitorCompanyDTO.getAreaList().get(i) + "'";
				}else{
					areas += "'" + monitorCompanyDTO.getAreaList().get(i) + "'";
				}
			}
			
			hql.append(" and monitorGroupCompany.monitorCompany.province in (" + areas + ") ");
		}
		
		if(monitorCompanyDTO.getLabelList().size() > 0){
			hql.append(" and (");
			
			for(int i=0;i<monitorCompanyDTO.getLabelList().size();i++){
				if(i > 0){
					hql.append(" or monitorGroupCompany.monitorCompany.riskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
				}else{
					hql.append(" monitorGroupCompany.monitorCompany.riskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
				}
				
				hql.append(" or monitorGroupCompany.monitorCompany.customRiskLabel like '%" + monitorCompanyDTO.getLabelList().get(i) + "%' ");
			}
			
			hql.append(") ");
		}
		
		hql.append(" order by monitorGroupCompany.monitorCompany.createTime desc ");
		
		return getListByHql(hql.toString());
	}
	
	@Override
	public MonitorCompany queryCompanyByName(String name, String accountId) {
		
		String sql = "select company from MonitorCompany company where company.deleteFlag = '0' and company.companyName='"+name+"' and clientAccount.accountId='"+accountId+"'";
		
		return this.getByHql(sql,null);
	}


	@Override
	public boolean updateGroupStatus(String monitorId, String groupStatus) {
		String sql = "update monitor_company " + "set GroupStatus='"+ groupStatus + "' where MonitorID = '" + monitorId + "'";
		
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
	public List<MonitorCompany> getGroupCompanyList(String groupId) {
		String hql = "from MonitorCompany where deleteFlag = '0' and monitorID in (select monitorCompany.monitorID from MonitorGroupCompany where monitorGroup.groupId ='"
				+ groupId + "')";

		return getListByHql(hql);

	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryMonitorCompanySituation(String accountId) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(1) AS totalCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '3' THEN 1 ELSE 0 END ) AS highRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '2' THEN 1 ELSE 0 END ) AS middleRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '1' THEN 1 ELSE 0 END ) AS lowRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '0' THEN 1 ELSE 0 END ) AS noRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' THEN 1 ELSE 0 END ) AS newEventCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.RiskStatus = '1' THEN 1 ELSE 0 END ) AS riskUpCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.RiskStatus = '2' THEN 1 ELSE 0 END ) AS riskDownCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.RiskStatus = '0' THEN 1 ELSE 0 END ) AS riskInvariantCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' and t.EventLevel = '3' THEN 1 ELSE 0 END ) AS newWarnCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' and t.EventLevel = '2' THEN 1 ELSE 0 END ) AS newAttentionCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' and t.EventLevel = '1' THEN 1 ELSE 0 END ) AS newNormalCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.EventLevel = '3' THEN 1 ELSE 0 END ) AS warnCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.EventLevel = '2' THEN 1 ELSE 0 END ) AS attentionCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.EventLevel = '1' THEN 1 ELSE 0 END ) AS normalCompanyNum ");
		sql.append(" FROM monitor_company t where t.delete_flag = '0' and t.CreateTime <  '" + sdfDate.format(new Date()) + " 00:00:00' ");
		
		//如果账号为空，则返回0
		if(StringUtils.isNotEmpty(accountId)){
			sql.append(" and t.account_id = '" + accountId + "' ");
			
			Query query = this.getSession().createSQLQuery(sql.toString());
			
			resultMap = (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
			
			//当未监控企业时，相关数量设置为0
			if(0 == ((BigInteger) resultMap.get("totalCompanyNum")).intValue()){
				resultMap.put("highRiskCompanyNum", 0);
				resultMap.put("middleRiskCompanyNum", 0);
				resultMap.put("lowRiskCompanyNum", 0);
				resultMap.put("noRiskCompanyNum", 0);
				resultMap.put("newEventCompanyNum", 0);
				resultMap.put("riskUpCompanyNum", 0);
				resultMap.put("riskDownCompanyNum", 0);
				resultMap.put("riskInvariantCompanyNum", 0);
				resultMap.put("newWarnCompanyNum", 0);
				resultMap.put("newAttentionCompanyNum", 0);
				resultMap.put("newNormalCompanyNum", 0);
				resultMap.put("warnCompanyNum", 0);
				resultMap.put("attentionCompanyNum", 0);
				resultMap.put("normalCompanyNum", 0);
			}
		}else{
			resultMap.put("totalCompanyNum", 0);
			resultMap.put("highRiskCompanyNum", 0);
			resultMap.put("middleRiskCompanyNum", 0);
			resultMap.put("lowRiskCompanyNum", 0);
			resultMap.put("noRiskCompanyNum", 0);
			resultMap.put("newEventCompanyNum", 0);
			resultMap.put("riskUpCompanyNum", 0);
			resultMap.put("riskDownCompanyNum", 0);
			resultMap.put("riskInvariantCompanyNum", 0);
			resultMap.put("newWarnCompanyNum", 0);
			resultMap.put("newAttentionCompanyNum", 0);
			resultMap.put("newNormalCompanyNum", 0);
			resultMap.put("warnCompanyNum", 0);
			resultMap.put("attentionCompanyNum", 0);
			resultMap.put("normalCompanyNum", 0);
		}
		
		return resultMap;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> queryMonitorCompanySituation(List<ClientAccount> clientAccountList) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT COUNT(1) AS totalCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '3' THEN 1 ELSE 0 END ) AS highRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '2' THEN 1 ELSE 0 END ) AS middleRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '1' THEN 1 ELSE 0 END ) AS lowRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.CustomRiskSituation = '0' THEN 1 ELSE 0 END ) AS noRiskCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' THEN 1 ELSE 0 END ) AS newEventCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.RiskStatus = '1' THEN 1 ELSE 0 END ) AS riskUpCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.RiskStatus = '2' THEN 1 ELSE 0 END ) AS riskDownCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.RiskStatus = '0' THEN 1 ELSE 0 END ) AS riskInvariantCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' and t.EventLevel = '3' THEN 1 ELSE 0 END ) AS newWarnCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' and t.EventLevel = '2' THEN 1 ELSE 0 END ) AS newAttentionCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.NewEventFlag = '1' and t.EventLevel = '1' THEN 1 ELSE 0 END ) AS newNormalCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.EventLevel = '3' THEN 1 ELSE 0 END ) AS warnCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.EventLevel = '2' THEN 1 ELSE 0 END ) AS attentionCompanyNum, ");
		sql.append(" SUM(CASE WHEN t.EventLevel = '1' THEN 1 ELSE 0 END ) AS normalCompanyNum ");
		sql.append(" FROM monitor_company t where t.delete_flag = '0' and t.CreateTime <  '" + sdfDate.format(new Date()) + " 00:00:00' ");
		
		//如果账号为空，则返回0
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
			
			sql.append(" and t.account_id in (" + accountIds + ") ");
			
			Query query = this.getSession().createSQLQuery(sql.toString());
			
			resultMap = (Map<String, Object>) query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).uniqueResult();
			
			//无数据时，sum的结果是null,重新赋值，保证前台正常显示
			if(null == resultMap.get("totalCompanyNum")){
				resultMap.put("totalCompanyNum", 0);
			}
			if(null == resultMap.get("highRiskCompanyNum")){
				resultMap.put("highRiskCompanyNum", 0);
			}
			if(null == resultMap.get("middleRiskCompanyNum")){
				resultMap.put("middleRiskCompanyNum", 0);
			}
			if(null == resultMap.get("lowRiskCompanyNum")){
				resultMap.put("lowRiskCompanyNum", 0);
			}
			if(null == resultMap.get("noRiskCompanyNum")){
				resultMap.put("noRiskCompanyNum", 0);
			}
			if(null == resultMap.get("newEventCompanyNum")){
				resultMap.put("newEventCompanyNum", 0);
			}
			if(null == resultMap.get("riskUpCompanyNum")){
				resultMap.put("riskUpCompanyNum", 0);
			}
			if(null == resultMap.get("riskDownCompanyNum")){
				resultMap.put("riskDownCompanyNum", 0);
			}
			if(null == resultMap.get("riskInvariantCompanyNum")){
				resultMap.put("riskInvariantCompanyNum", 0);
			}
			if(null == resultMap.get("newWarnCompanyNum")){
				resultMap.put("newWarnCompanyNum", 0);
			}
			if(null == resultMap.get("newAttentionCompanyNum")){
				resultMap.put("newAttentionCompanyNum", 0);
			}
			if(null == resultMap.get("newNormalCompanyNum")){
				resultMap.put("newNormalCompanyNum", 0);
			}
			if(null == resultMap.get("warnCompanyNum")){
				resultMap.put("warnCompanyNum", 0);
			}
			if(null == resultMap.get("attentionCompanyNum")){
				resultMap.put("attentionCompanyNum", 0);
			}
			if(null == resultMap.get("normalCompanyNum")){
				resultMap.put("normalCompanyNum", 0);
			}
		}else{
			resultMap.put("totalCompanyNum", 0);
			resultMap.put("highRiskCompanyNum", 0);
			resultMap.put("middleRiskCompanyNum", 0);
			resultMap.put("lowRiskCompanyNum", 0);
			resultMap.put("noRiskCompanyNum", 0);
			resultMap.put("newEventCompanyNum", 0);
			resultMap.put("riskUpCompanyNum", 0);
			resultMap.put("riskDownCompanyNum", 0);
			resultMap.put("riskInvariantCompanyNum", 0);
			resultMap.put("newWarnCompanyNum", 0);
			resultMap.put("newAttentionCompanyNum", 0);
			resultMap.put("newNormalCompanyNum", 0);
			resultMap.put("warnCompanyNum", 0);
			resultMap.put("attentionCompanyNum", 0);
			resultMap.put("normalCompanyNum", 0);
		}
		
		return resultMap;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(String accountId) {
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = new ArrayList<MonitorCompanyDistribution>();
		
		MonitorCompanyDistribution monitorCompanyDistribution = new MonitorCompanyDistribution();
		
		StringBuffer sql = new StringBuffer("");
		
		sql.append("SELECT t.Province AS province,COUNT(1) AS totalCompanyNum,");
		sql.append("SUM(CASE WHEN t.CustomRiskSituation = '3' THEN 1 ELSE 0 END ) AS highRiskCompanyNum ");
		sql.append("from monitor_company t where t.delete_flag = '0' and t.CreateTime <  '" + sdfDate.format(new Date()) + " 00:00:00' ");
		
		//如果账号为空，则返回0
		if(StringUtils.isNotEmpty(accountId)){
			sql.append(" and t.account_id = '" + accountId + "' GROUP BY t.Province");
			
			Query query = this.getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			List<Map<String, Object>> resultList = query.list();
			 
			for(int i=0;i<resultList.size();i++){
				monitorCompanyDistribution = new MonitorCompanyDistribution();
				monitorCompanyDistribution.setAreaName((String) resultList.get(i).get("province"));
				monitorCompanyDistribution.setTotalCompanyNum(((BigInteger) resultList.get(i).get("totalCompanyNum")).intValue());
				monitorCompanyDistribution.setHighRiskCompanyNum(((BigDecimal) resultList.get(i).get("highRiskCompanyNum")).intValue());
				
				monitorCompanyDistributionList.add(monitorCompanyDistribution);
			}
		}
		
		return monitorCompanyDistributionList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCompanyDistribution> queryMonitorCompanyDistributionList(List<ClientAccount> clientAccountList) {
		List<MonitorCompanyDistribution> monitorCompanyDistributionList = new ArrayList<MonitorCompanyDistribution>();
		
		MonitorCompanyDistribution monitorCompanyDistribution = new MonitorCompanyDistribution();
		
		StringBuffer sql = new StringBuffer("");
		
		sql.append("SELECT t.Province AS province,COUNT(1) AS totalCompanyNum,");
		sql.append("SUM(CASE WHEN t.CustomRiskSituation = '3' THEN 1 ELSE 0 END ) AS highRiskCompanyNum ");
		sql.append("from monitor_company t where t.delete_flag = '0' and t.CreateTime <  '" + sdfDate.format(new Date()) + " 00:00:00' ");
		
		//如果账号为空，则返回0
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
			
			sql.append(" and t.account_id in (" + accountIds + ") GROUP BY t.Province");
			
			Query query = this.getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			List<Map<String, Object>> resultList = query.list();
			 
			for(int i=0;i<resultList.size();i++){
				monitorCompanyDistribution = new MonitorCompanyDistribution();
				monitorCompanyDistribution.setAreaName((String) resultList.get(i).get("province"));
				monitorCompanyDistribution.setTotalCompanyNum(((BigInteger) resultList.get(i).get("totalCompanyNum")).intValue());
				monitorCompanyDistribution.setHighRiskCompanyNum(((BigDecimal) resultList.get(i).get("highRiskCompanyNum")).intValue());
				
				monitorCompanyDistributionList.add(monitorCompanyDistribution);
			}
		}
		
		return monitorCompanyDistributionList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MonitorCompany> queryNewEventCompanyList(String accountId, int num) {
		
		String hql = "from MonitorCompany where deleteFlag = '0' and clientAccount.accountId = '" + accountId + "' and newEventFlag = '1'";
		
		Query query = this.getSession().createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(num);
		
		List<MonitorCompany> monitorCompanyList = null;
		
		try{
			monitorCompanyList = query.list();
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
		}
		
		if(monitorCompanyList == null){
			monitorCompanyList = new ArrayList<MonitorCompany>();
		}
		
		return monitorCompanyList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getRiskSituationList(List<ClientAccount> clientAccountList) {
		
		List<Map<String, Object>> riskSituationList = new ArrayList<Map<String,Object>>();
		
		StringBuffer sql = new StringBuffer("");
		
		sql.append("SELECT t1.account_id AS accountId, t2.account_name AS accountName, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '3' THEN 1 ELSE 0 END ) AS highRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '2' THEN 1 ELSE 0 END ) AS middleRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '1' THEN 1 ELSE 0 END ) AS lowRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '0' THEN 1 ELSE 0 END ) AS noRiskCompanyNum ");
		sql.append("from monitor_company t1 ");
		sql.append("left join client_account t2 on t2.account_id = t1.account_id ");
		
		//如果账号为空，则返回0
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
			
			sql.append(" where t1.delete_flag = '0' and t1.account_id in (" + accountIds + ") and t1.CreateTime <  '" + sdfDate.format(new Date()) + " 00:00:00'  GROUP BY t1.account_id");
			
			Query query = this.getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			riskSituationList = query.list();
		}
		
		return riskSituationList;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getSelfRiskSituation(String accountId) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer sql = new StringBuffer("");
		
		sql.append("SELECT t1.account_id AS accountId, t2.account_name AS accountName, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '3' THEN 1 ELSE 0 END ) AS highRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '2' THEN 1 ELSE 0 END ) AS middleRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '1' THEN 1 ELSE 0 END ) AS lowRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '0' THEN 1 ELSE 0 END ) AS noRiskCompanyNum ");
		sql.append("from monitor_company t1 ");
		sql.append("left join client_account t2 on t2.account_id = t1.account_id ");
		sql.append(" where t1.delete_flag = '0' and t1.account_id = '" + accountId + "' and t1.CreateTime < '" + sdfDate.format(new Date()) + " 00:00:00' ");
		
		Query query = this.getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		map = (Map<String, Object>) query.list().get(0);
		
		if(null == map.get("highRiskCompanyNum")){
			map.put("highRiskCompanyNum", 0);
		}
		
		if(null == map.get("middleRiskCompanyNum")){
			map.put("middleRiskCompanyNum", 0);
		}
		
		if(null == map.get("lowRiskCompanyNum")){
			map.put("lowRiskCompanyNum", 0);
		}
		
		if(null == map.get("noRiskCompanyNum")){
			map.put("noRiskCompanyNum", 0);
		}
		
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getRiskSituationByParent(List<ClientAccount> clientAccountList) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		StringBuffer sql = new StringBuffer("");
		
		sql.append("SELECT SUM(CASE WHEN t1.CustomRiskSituation = '3' THEN 1 ELSE 0 END ) AS highRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '2' THEN 1 ELSE 0 END ) AS middleRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '1' THEN 1 ELSE 0 END ) AS lowRiskCompanyNum, ");
		sql.append("SUM(CASE WHEN t1.CustomRiskSituation = '0' THEN 1 ELSE 0 END ) AS noRiskCompanyNum ");
		sql.append("from monitor_company t1 ");
		sql.append("left join client_account t2 on t2.account_id = t1.account_id ");
		
		//如果账号为空，则返回0
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
			
			sql.append(" where t1.delete_flag = '0' and t1.account_id in (" + accountIds + ") and t1.CreateTime < '" + sdfDate.format(new Date()) + " 00:00:00' ");
			
			Query query = this.getSession().createSQLQuery(sql.toString()).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
			
			map = (Map<String, Object>) query.list().get(0);
			
			if(null == map.get("highRiskCompanyNum")){
				map.put("highRiskCompanyNum", 0);
			}
			
			if(null == map.get("middleRiskCompanyNum")){
				map.put("middleRiskCompanyNum", 0);
			}
			
			if(null == map.get("lowRiskCompanyNum")){
				map.put("lowRiskCompanyNum", 0);
			}
			
			if(null == map.get("noRiskCompanyNum")){
				map.put("noRiskCompanyNum", 0);
			}
		}
		
		return map;
	}

	@Override
	public boolean deleteMonitorCompanyById(String monitorId) {
		String sql = "update monitor_company set delete_flag = '1' where MonitorID = '" + monitorId + "'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		try{
			query.executeUpdate();
			return true;
		}catch(Exception e){
			LoggerUtils.error(e.getMessage(), e);
			
			return false;
		}
	}

}
