/**   
* @Title: BlacklistCompanyDaoImpl.java 
* @Package com.srd.ljzd.daoImpl.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:46:38 
* @version V1.0   
*/
package com.srd.ljzd.daoImpl.blacklist;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.blacklist.BlacklistCompanyDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.blacklist.BlacklistCompany;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: BlacklistCompanyDaoImpl
 * @Description: 黑名单企业Dao实现类
 * @author shiyong
 * @date 2017年3月29日 下午4:46:38
 *  
 */
@Repository("blacklistCompanyDao")
public class BlacklistCompanyDaoImpl extends BaseDaoImpl<BlacklistCompany, String> implements BlacklistCompanyDao{
	protected static Logger logger = LogManager
			.getLogger(BlacklistCompanyDaoImpl.class.getName());
	@Override
	public Page<BlacklistCompany> getBlacklistCompanyByPage(String accountId,Integer currentPageNo,Integer pageSize) {
	    String hql = "select company from BlacklistCompany company where account.accountId=? and deleteFlag='0' order by createTime desc";
	    String countSql = "select count(company.id) from BlacklistCompany company where account.accountId=? and deleteFlag='0'";
		return super.findPageByHql(hql, countSql, currentPageNo, pageSize, accountId);
	}
	@Override
	public Page<BlacklistCompany> getBlacklistCompanyByPageWithCompanyId(
			String companyId, Integer pageNo, Integer pageSize) {
		String hql = "select company from BlacklistCompany company where account.clientCompany.companyId=? and deleteFlag='0' order by createTime desc";
	    String countSql = "select count(company.id) from BlacklistCompany company where account.clientCompany.companyId=? and deleteFlag='0'";
		return super.findPageByHql(hql, countSql, pageNo, pageSize, companyId);
	}
	@Override
	public BlacklistCompany getBlacklistCompany(String accountId,
			String companyName) {
		String hql = "select company from BlacklistCompany company where account.accountId=? and companyName=? and deleteFlag='0' order by createTime desc";
		List<BlacklistCompany> companyList = super.getListByHql(hql, accountId,companyName);
		if(companyList!=null&&companyList.size()>0){
			if(companyList.size()>1){
				logger.error("getBlacklistCompany error,not unique result,param=[accountId="+accountId+",companyName="+companyName+"]");
			}
			return companyList.get(0);
		}
		return null;
	}

	@Override
	public Long getBlacklistCompanyNum(String accountId) {
		String countSql = "select count(company.id) from BlacklistCompany company where account.accountId=? and deleteFlag='0'";
		
		return super.countByHql(countSql, accountId);
	}

	@Override
	public int getBlacklistCompanyNumByCompany(String companyId) {
		Map<String, Object> parameMap = new HashMap<String, Object>();
		
		String hql = "select count(company.id) from BlacklistCompany company where account.clientCompany.companyId = :companyId and deleteFlag='0'";
		
		parameMap.put("companyId", companyId);
		
		int num = countByHql(hql, parameMap).intValue();
		
		return num;
	}

	

	
}
