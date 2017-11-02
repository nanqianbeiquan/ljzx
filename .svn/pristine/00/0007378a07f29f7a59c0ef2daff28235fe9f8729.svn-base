package com.srd.ljzd.daoImpl.blacklist;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.srd.ljzd.dao.blacklist.BlacklistReasonDao;
import com.srd.ljzd.daoImpl.base.BaseDaoImpl;
import com.srd.ljzd.entity.blacklist.BlacklistReason;
import com.srd.ljzd.util.BlacklistReasonTypeEnum;
@Repository("blacklistReasonDao")
public class BlacklistReasonDaoImpl extends BaseDaoImpl<BlacklistReason, String> implements BlacklistReasonDao{
	/**
	 * 获取原因列表
	 * @param type
	 * @return
	 */
	@Override
	public List<BlacklistReason> getReasonList(BlacklistReasonTypeEnum type) {
		String sql = "select bReason from BlacklistReason bReason where type=? and deleteFlag='0' order by sort" ;
		
		 List<BlacklistReason> list = super.getListByHql(sql, type.toString());
		
		return list;
	}

}
