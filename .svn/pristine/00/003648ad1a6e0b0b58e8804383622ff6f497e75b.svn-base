/**   
* @Title: BlacklistCompanyDao.java 
* @Package com.srd.ljzd.dao.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:44:37 
* @version V1.0   
*/
package com.srd.ljzd.dao.blacklist;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.blacklist.BlacklistCompany;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: BlacklistCompanyDao
 * @Description: 黑名单企业Dao
 * @author shiyong
 * @date 2017年3月29日 下午4:44:37
 *  
 */
public interface BlacklistCompanyDao extends BaseDao<BlacklistCompany, String> {

	/**
	 * 获取黑名单
	 * @param accountId
	 * @return
	 */
	Page<BlacklistCompany> getBlacklistCompanyByPage(String accountId,Integer currentPageNo,Integer pageSize);
	
	Page<BlacklistCompany> getBlacklistCompanyByPageWithCompanyId(
			String companyId, Integer pageNo, Integer pageSize);
	
	/**
	 * 查询黑名单公司
	 * @param accountId
	 * @param companyName
	 * @return
	 */
	BlacklistCompany getBlacklistCompany(String accountId,String companyName);
	
	Long getBlacklistCompanyNum(String accountId);
	
	/** 
	* @Title: getBlacklistCompanyNumByCompany 
	* @Description: 根据账号企业获取失信企业数量
	* @param @param companyId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月27日 下午4:23:53
	*/
	public int getBlacklistCompanyNumByCompany(String companyId);
	
}
