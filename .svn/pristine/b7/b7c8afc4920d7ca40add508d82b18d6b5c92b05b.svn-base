/**   
* @Title: InfoMessageAccountDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.info 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2016年11月22日 下午2:48:46 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.info;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.info.InfoMessageAccountDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.info.InfoMessageAccount;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: InfoMessageAccountDaoImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author shiyong
 * @date 2016年11月22日 下午2:48:46
 *  
 */
@Repository("infoMessageAccountDao")
public class InfoMessageAccountDaoImpl extends BaseDaoImpl<InfoMessageAccount, String> implements InfoMessageAccountDao {

	protected static Logger log = LogManager.getLogger(InfoMessageAccountDaoImpl.class.getName());
	
	@Override
	public Page<InfoMessageAccount> findMessageAccountPage(
			InfoMessageAccount infoMessageAccount, int currentPageNo, int pageSize) {
		
		StringBuffer hql = new StringBuffer("select infoMessageAccount from InfoMessageAccount infoMessageAccount where ");
		StringBuffer countHql = new StringBuffer("select count(infoMessageAccount.id) from InfoMessageAccount infoMessageAccount where ");
		
		hql.append(" infoMessageAccount.isDelete = '0' and infoMessageAccount.infoMessage.messageStatus = '1' ");
		countHql.append(" infoMessageAccount.isDelete = '0' and infoMessageAccount.infoMessage.messageStatus = '1' ");
		
		if(StringUtils.isNotEmpty(infoMessageAccount.getAccountId())){
			hql.append(" and infoMessageAccount.accountId = '" + infoMessageAccount.getAccountId() + "' ");
			countHql.append(" and infoMessageAccount.accountId = '" + infoMessageAccount.getAccountId() + "' ");
		}
		
		if(infoMessageAccount.getInfoMessage() != null && StringUtils.isNotEmpty(infoMessageAccount.getInfoMessage().getSender())){
			hql.append(" and infoMessageAccount.infoMessage.sender = '" + infoMessageAccount.getInfoMessage().getSender() + "'");
			countHql.append(" and infoMessageAccount.infoMessage.sender = '" + infoMessageAccount.getInfoMessage().getSender() + "'");
		}
		
		hql.append(" order by infoMessageAccount.createTime desc");
		
		Page<InfoMessageAccount> page = findPageByHql(hql.toString(), countHql.toString(), currentPageNo, pageSize);
		
		return page;
	}

	@Override
	public List<InfoMessageAccount> queryMessageAccountList(InfoMessageAccount infoMessageAccount) {
		
		StringBuffer hql = new StringBuffer("select infoMessageAccount from InfoMessageAccount infoMessageAccount where infoMessageAccount.isDelete = '0' and infoMessageAccount.infoMessage.messageStatus = '1' ");
		
		if(StringUtils.isNotEmpty(infoMessageAccount.getAccountId())){
			hql.append(" and infoMessageAccount.accountId = '" + infoMessageAccount.getAccountId() +"' ");
		}
		
		if(StringUtils.isNotEmpty(infoMessageAccount.getReadStatus())){
			hql.append(" and infoMessageAccount.readStatus = '" + infoMessageAccount.getReadStatus() +"' ");
		}
		
		if(infoMessageAccount.getInfoMessage() != null && StringUtils.isNotEmpty(infoMessageAccount.getInfoMessage().getSender())){
			hql.append(" and infoMessageAccount.infoMessage.sender = '" + infoMessageAccount.getInfoMessage().getSender() + "'");
		}
		
		hql.append(" order by infoMessageAccount.createTime desc");
		
		return getListByHql(hql.toString());
	}

	@Override
	public int getUnreadMessageNum(String accountId) {
		
		String sql = "select count(1) from info_message_account t where t.account_id = '" 
				   + accountId + "' and t.ReadStatus = '0' and t.IsDelete = '0'";
		
		Query query = this.getSession().createSQLQuery(sql);
		
		BigInteger num = null;
		
		try{
			num = (BigInteger) query.uniqueResult();
		}catch(Exception e){
			log.error(e.getMessage(), e);
		}
		
		return num.intValue();
	}

	
}
