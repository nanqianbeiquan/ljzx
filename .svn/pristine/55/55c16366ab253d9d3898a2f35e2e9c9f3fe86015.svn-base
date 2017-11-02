/**   
* @Title: BlacklistCompanyDao.java 
* @Package com.srd.ljzd.dao.blacklist 
* @Description: TODO(用一句话描述该文件做什么) 
* @author shiyong
* @date 2017年3月29日 下午4:44:37 
* @version V1.0   
*/
package com.srd.ljzd.dao.blacklist;

import java.util.List;

import com.srd.ljzd.dao.base.BaseDao;
import com.srd.ljzd.entity.blacklist.BlacklistReason;
import com.srd.ljzd.util.BlacklistReasonTypeEnum;

/** 
 * @ClassName: BlacklistCompanyDao
 * @Description: 黑名单企业Dao
 * @author shiyong
 * @date 2017年3月29日 下午4:44:37
 *  
 */
public interface BlacklistReasonDao extends BaseDao<BlacklistReason, String> {
/**
 * 获取原因列表
 * @param type
 * @return
 */
	List<BlacklistReason> getReasonList(BlacklistReasonTypeEnum type);
}
