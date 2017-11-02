package com.srd.ljzd.serviceImpl.blacklist;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srd.ljzd.dao.blacklist.BlacklistReasonDao;
import com.srd.ljzd.entity.blacklist.BlacklistReason;
import com.srd.ljzd.service.blacklist.BlacklistReasonService;
import com.srd.ljzd.util.BlacklistReasonTypeEnum;
@Service("blacklistReasonService")
public class BlacklistReasonServiceImpl implements BlacklistReasonService {
	@Autowired
	BlacklistReasonDao blacklistReasonDao;
	
	/**
	 * 获取原因列表
	 * @param type 1是加入原因，  2是删除原因
	 * @return
	 */
	@Override
	public List<BlacklistReason> getReasonList(BlacklistReasonTypeEnum type) {
		return blacklistReasonDao.getReasonList(type);
	}

}
