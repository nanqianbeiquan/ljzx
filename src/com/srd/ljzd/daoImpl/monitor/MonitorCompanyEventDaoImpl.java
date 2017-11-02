package com.srd.ljzd.daoImpl.monitor;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.srd.ljzd.dao.monitor.MonitorCompanyEventDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.dto.monitor.MonitorComEventDTO;
import com.srd.ljzd.entity.monitor.MonitorCompanyEvent;
import com.srd.ljzd.util.DateUtils;
import com.srd.ljzd.util.Page;

/**
 * 
 * 版权所有：
 * 项目名称：lengjingzd2.0 
 *
 * 类描述：详细界面查询公司事件
 * 类名称：com.srd.ljzd.daoImpl.monitor  
 * 创建人：jiang.zhou
 * 创建时间：20162016年11月1日下午4:45:34
 * 修改人： 
 * 修改时间： 
 * 修改备注：   
 * @version   V2.0
 */
@Repository
public class MonitorCompanyEventDaoImpl extends BaseDaoImpl<MonitorCompanyEvent,String> implements MonitorCompanyEventDao {

	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Override
	public Page<MonitorCompanyEvent> getMonitorComEvent(
			MonitorComEventDTO monitorComEventDTO, String tableName,int currentPageNo,
			int pageSize) {
		List<String> typeList=monitorComEventDTO.getTypeList();
		StringBuffer buf=new StringBuffer();
		
		String head="select * ";
		if("0".equals(monitorComEventDTO.getReadStatus())){
			//表示最新未读事件
			//全部事件
			String sql="from "+tableName+" where CompanyName ="
					+"'"+monitorComEventDTO.getCompanyName()+"'"
					+ " and HappenDate>="
					+ "'"+monitorComEventDTO.getInfoShowDate()+"'"
//					+ " and HappenDate<="
//					+ "'"+monitorComEventDTO.getDueDate()+"'"
					+ " and eventSubType ="
					+ "'"+monitorComEventDTO.getEventSubType()+"'"
					+ " and eventId not in"
					+ "(select eventId from MonitorCompanyEventState where state='1' and companyId='"+monitorComEventDTO.getMonitorCompanyId()+"') ";
			buf.append(sql);
			if(typeList!=null&&typeList.size()>0){
				String sql2=" and eventLevel in (:list) ";
				buf.append(sql2);
			}
			if(monitorComEventDTO.getBeginDate()!=null){
				buf.append(" and contentDate>='").append(monitorComEventDTO.getBeginDate()).append("' ");
			}
			if(monitorComEventDTO.getDueDate()!=null){
				buf.append(" and contentDate<='").append(monitorComEventDTO.getDueDate()).append("' ");
			}
			String sql3= " order by ContentDate desc,eventID asc";		
			buf.append(sql3);
			
		}else{
			//全部事件
			String sql="from "+tableName+" where CompanyName ="
					+"'"+monitorComEventDTO.getCompanyName()+"'"
					+ " and HappenDate>="
					+ "'"+monitorComEventDTO.getInfoShowDate()+"'"
//					+ " and HappenDate<="
//					+ "'"+monitorComEventDTO.getDueDate()+"'"
					+ " and eventSubType ="
					+ "'"+monitorComEventDTO.getEventSubType()+"' ";
			buf.append(sql);
			if(typeList!=null&&typeList.size()>0){
				String sql2=" and eventLevel in (:list) ";
				buf.append(sql2);
			}
			if(monitorComEventDTO.getBeginDate()!=null){
				buf.append(" and contentDate>='").append(monitorComEventDTO.getBeginDate()).append("' ");
			}
			if(monitorComEventDTO.getDueDate()!=null){
				buf.append(" and contentDate<='").append(monitorComEventDTO.getDueDate()).append("' ");
			}
			String sql3= "order by ContentDate desc,eventID asc";		
			buf.append(sql3);
		}

		String countSql="select count(eventID) "+buf.toString();
		if(typeList!=null&&typeList.size()>0){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("list", monitorComEventDTO.getTypeList());
			return findPageBySql2(head+buf.toString(), countSql, currentPageNo, pageSize,MonitorCompanyEvent.class,map);
		}else{
			return findPageBySql2(head+buf.toString(), countSql, currentPageNo, pageSize,MonitorCompanyEvent.class);
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String,Object>> queryUnReadEventCategoryNumList(String accountId, String companyId, String companyName, Date infoShowDate, String tableName) {
		String sql = "SELECT t1.EventSubType as eventSubType, count(1) as eventNum FROM " + tableName + " t1 "
				+ " LEFT JOIN monitorcompanyeventstate t2 ON t2.EventID = t1.EventID and t2.account_id = '" + accountId + "' and t2.CompanyID = '" + companyId + "' "
				+ " where t1.CompanyName= '" + companyName + "' "
				+ " and t1.HappenDate >= '" + sdfDate.format(infoShowDate) + "' "
				+ " and t2.EventID is null "
				+ " group by t1.EventSubType";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		List<Map<String,Object>> list = (List<Map<String,Object>>)query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP).list();
		
		return list;
	}

	@Override
	public List queryEventCategory(String companyName, Date infoShowDate, String tableName) {
		String sql = "SELECT DISTINCT evt.EventSubType FROM "+tableName+" evt where evt.CompanyName=? AND evt.HappenDate>=?";
        
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter(0, companyName);
		query.setParameter(1, DateUtils.getLocalStr(DateUtils.formatPattern, infoShowDate));
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getUnReadEventIdList(String accountId, String monitorId, String companyName, String infoShowDate, String eventSubType, String tableName) {
		
		String sql = "SELECT t1.EventID FROM " + tableName + " t1 "
				+ " LEFT JOIN monitorcompanyeventstate t2 ON t1.EventID = t2.EventID and t2.account_id = :accountId and t2.CompanyID = :monitorId "
				+ " where t1.CompanyName = :companyName "
				+ " and t1.EventSubType = :eventSubType "
				+ " and t1.HappenDate >= :infoShowDate "
				+ " and t2.EventID is null";
		
		Query query = this.getSession().createSQLQuery(sql);
		query.setParameter("accountId", accountId);
		query.setParameter("monitorId", monitorId);
		query.setParameter("companyName", companyName);
		query.setParameter("eventSubType", eventSubType);
		query.setParameter("infoShowDate", infoShowDate);
		
		return query.list();
	}
	
}
