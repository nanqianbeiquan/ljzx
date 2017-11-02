/**   
* @Title: BlacklistCompanyService.java 
* @Package com.srd.ljzd.service.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:49:03 
* @version V1.0   
*/
package com.srd.ljzd.service.blacklist;

import java.util.Map;

import com.srd.ljzd.entity.blacklist.BlacklistCompany;
import com.srd.ljzd.util.Page;

/** 
 * @ClassName: BlacklistCompanyService
 * @Description: 黑名单企业Service
 * @author shiyong
 * @date 2017年3月29日 下午4:49:03
 *  
 */
public interface BlacklistCompanyService {

	/** 
	* @Title: getBlacklistCompanyNumByCompany 
	* @Description: 根据账号企业获取失信企业数量 
	* @param @param companyId
	* @param @return 设定文件 
	* @return int 返回类型 
	* @throws 
	* @author shiyong
	* @date 2017年9月27日 下午4:32:51
	*/
	public int getBlacklistCompanyNumByCompany(String companyId);
	
	Long getBlacklistCompanyNum(String accountId);
	
	Page<Map<String,Object>> getBlacklistCompanyByPage(String accountId,Integer currentPageNo,Integer pageSize);
	Page<Map<String, Object>> getBlacklistCompanyByPageWithCompanyId(String companyId,
			Integer pageNo, Integer pageSize);
	/**
	 * 添加到黑名单
	 * @param accountId
	 * @param companyName 
	 * @param reason，系统内原因的Id拼接字符串
	 * @param otherReason，其他原因
	 */
	Map<String,Object> addToBlacklist(String accountId,String companyName,String reason,String otherReason);
	/**
	 * 从黑名单移除
	 * @param accountId
	 * @param companyName
	 * @param reason，系统内原因的id拼接字符串
	 * @param otherReason，其他原因
	 */
	Map<String,Object> removeFromBlacklist(String accountId,String companyName,String reason,String otherReason);
	/**
	 * 检查是否在用户黑名单中
	 */
	BlacklistCompany checkCustomBlacklist(String accountId,String companyName);
	/**
	 * 检查是否在系统黑名单中，调用接口
	 */
	Map<String,Object> checkSystemBlacklist(String companyName,String cnt);

	
}
